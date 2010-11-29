/*
 * @(#)ApplicationShutdownHooks.java	1.5 10/03/23
 *
 * Копирайт (c) 2004, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */
package java.lang;

import java.util.*;

/*
 * Класс для отслеживания и запуска ловушек завершения пользовательского 
 * уровня (user level shutdown hooks), зарегистрированные через
 * <tt>{@link Runtime#addShutdownHook Runtime.addShutdownHook}</tt>.
 *
 * @see java.lang.Runtime#addShutdownHook
 * @see java.lang.Runtime#removeShutdownHook
 */
class ApplicationShutdownHooks {
    static {
        Shutdown.add(1 /* порядок вызова ловушек завершения (shutdown hook) */,
            new Runnable() {
                public void run() {
                    runHooks();
                }
            });
    }

    /** Множество зарегистрированных ловушек (hooks) */
    private static IdentityHashMap<Thread, Thread> hooks = new IdentityHashMap<Thread, Thread>();

    private void ApplicationShutdownHooks() {}

    /** Добавляет новую ловушку завершения (shutdown hook). Проверяет статус завершения и
     *  саму ловушку, но не делает каких-либо проверок безопасности.
     */
    static synchronized void add(Thread hook) {
        if (hooks == null)
            throw new IllegalStateException("Shutdown in progress");

        if (hook.isAlive())
            throw new IllegalArgumentException("Hook already running");

        if (hooks.containsKey(hook))
            throw new IllegalArgumentException("Hook previously registered");

        hooks.put(hook, hook);
    }

    /** Удаляет ранее зарегистрированную ловушку. Подобно методу <code>add</code>, 
     *  этот метод не делает каких-либо проверок безопасности.
     */
    static synchronized boolean remove(Thread hook) {
        if (hooks == null)
            throw new IllegalStateException("Shutdown in progress");

        if (hook == null) 
            throw new NullPointerException();

        return hooks.remove(hook) != null;
    }

    /** Проходит по всем ловушкам приложения, создает новый поток для каждой
     *  и запускает его. Ловушки запускаются параллельно и этот метод ждет
     *  их окончания.
     */
    static void runHooks() {
        Collection<Thread> threads;
        synchronized(ApplicationShutdownHooks.class) {
            threads = hooks.keySet();
            hooks = null;
        }

        for (Thread hook : threads) {
            hook.start();
        }
        for (Thread hook : threads) {
            try {
                hook.join();
            } catch (InterruptedException x) { }
        }
    }
}

