/*
 * @(#)Shutdown.java	1.15 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.util.ArrayList;


/**
 * Приватный для пакета вспомогательный класс, содержащий структуры данных
 * и логику, управляющую последовательностью завершения работы виртуальный 
 * машины.
 *
 * @author   Mark Reinhold
 * @version  1.15, 10/03/23
 * @since    1.3
 */
class Shutdown {

    /* Статус завершения */
    private static final int RUNNING = 0;
    private static final int HOOKS = 1;
    private static final int FINALIZERS = 2;
    private static int state = RUNNING;

    /** Должны ли мы запустить все финализаторы при выходе? */
    private static boolean runFinalizersOnExit = false;

    // Системные ловушки завершения работы регистрируются в предопределеных слотах.
    // Список ловушек завершения следующий:
    // (0) Ловушка (hook) восстановления консоли
    // (1) Ловушки (hooks) приложения 
    // (2) Ловушка (hook) DeleteOnExit (удалить при выходе)
    private static final int MAX_SYSTEM_HOOKS = 10;
    private static final Runnable[] hooks = new Runnable[MAX_SYSTEM_HOOKS];

    /* Предыдущие статические поля защищены этой блокировкой. */
    private static class Lock { };
    private static Object lock = new Lock();

    /** Объект блокировки для родного (native) метода halt (останов [<чего-то>]). */
    private static Object haltLock = new Lock();

    /** Вызывается в Runtime.runFinalizersOnExit */
    static void setRunFinalizersOnExit(boolean run) {
        synchronized (lock) {
            runFinalizersOnExit = run;
        }
    }


    /** Добавляет новую ловушку завершения. Проверяет состояние завершения
     * и собствено ловушки, но не делает никаких проверок безопасности.
     */
    static void add(int slot, Runnable hook) {
        synchronized (lock) {
            if (state > RUNNING)
                throw new IllegalStateException("Shutdown in progress");

            if (hooks[slot] != null)
                throw new InternalError("Shutdown hook at slot " + slot + " already registered");

            hooks[slot] = hook;
        }
    }

    /** Запускает все зарегистрированные ловушки завершения.
     */
    private static void runHooks() {
        /* Нам не нужно получать блокировку, если мы хотим только читать поле 
         * ловушек, так как ловушки не могут меняться после начала завершения работы.
         */
        for (Runnable hook : hooks) {
            try {
                if (hook != null) hook.run();
            } catch(Throwable t) { 
                if (t instanceof ThreadDeath) {
                    ThreadDeath td = (ThreadDeath)t;
                    throw td;
                }
            }
        }
    }

    /* Метод {@code halt} синхронизируется на блокировке останова для 
     * предупреждения повреждения списка файлов, удаляемых при завершении 
     * работы. Он вызывает реальный родной (native) метод останова.
     */
    static void halt(int status) {
        synchronized (haltLock) {
            halt0(status);
        }
    }

    static native void halt0(int status);

    /* Червоточина для вызова java.lang.ref.Finalizer.runAllFinalizers */
    private static native void runAllFinalizers();


    /** Настоящая последовательность завершения работы определена здесь.
     *
     * Если бы не {@code runFinalizersOnExit}, это было бы просто -- мы бы просто выполнили
     * ловушки а затем остановились бы. Вместо этого мы должны отследить, выполняем ли мы 
     * ловушки или финализаторы. В последнем случае финализатор мог вызвать {@code exit(1)}, 
     * чтобы вызвать немедленное завершение, в то время, как в первом случае любые вызовы 
     * {@code exit(n)}, для любого {@code n}, просто не проходят. Заметьте, что если 
     * финализаторы при выходе включены, то они запускаются тогда и только тогда, когда завершение
     * работы вызвано вызовом {@code exit(0)}; они никогда не запустятся при {@code exit(n)} для 
     * {@code n != 0} или в ответ на {@code SIGINT}, {@code SIGTERM} и т.д.
     */
    private static void sequence() {
        synchronized (lock) {
            /* Страж против возможности потока-демона вызвать {@code exit}
             * после того, как DestroyJavaVM запустит процесс завершения работы.
             */
            if (state != HOOKS) return;
        }
        runHooks();
        boolean rfoe;
        synchronized (lock) {
            state = FINALIZERS;
            rfoe = runFinalizersOnExit;
        }
        if (rfoe) runAllFinalizers();
    }


    /** Вызывается в Runtime.exit, которая делает все проверки безопасности.
     * Также вызывается обработчиками для предоставляемых системой соьытий
     * завершения, которые должны передать ненулевой статус завершения.
     */
    static void exit(int status) {
        boolean runMoreFinalizers = false;
        synchronized (lock) {
            if (status != 0) runFinalizersOnExit = false;
            switch (state) {
                case RUNNING:   /* Начало завершения */
                    state = HOOKS;
                    break;
                case HOOKS:     /* Стопор (Stall) и останов (halt) */
                    break;
                case FINALIZERS:
                    if (status != 0) {
                        /* Останов немедленно при ненулевом статусе */
                        halt(status);
                    } else {
                        /* Совместимо со старым поведением:
                         * запуск остальных финализаторов с последующим остановом
                         */
                        runMoreFinalizers = runFinalizersOnExit;
                    }
                    break;
            }
        }
        if (runMoreFinalizers) {
            runAllFinalizers();
            halt(status);
        }
        synchronized (Shutdown.class) {
            /* Синхронизация на объекте класса по причине того, что любой другой
             * поток, который попытается начать завершение, навсегда стопорится
             */
            sequence();
            halt(status);
        }
    }


    /** Вызывается в процедуре JNI DestroyJavaVM, когда последний поток, не
     * являющийся демоном, завершится. В отличие от метода {@code exit}, 
     * этот метод на самом деле не останавливает VM.
     */
    static void shutdown() {
        synchronized (lock) {
            switch (state) {
                case RUNNING:   /* Начало завершения */
                    state = HOOKS;
                    break;
                case HOOKS:     /* Стопор и последующий выход из функции */
                case FINALIZERS:
                    break;
            }
        }
        synchronized (Shutdown.class) {
            sequence();
        }
    }

}
