/*
 * @(#)Terminator.java	1.12 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import sun.misc.Signal;
import sun.misc.SignalHandler;


/**
 * Внутрипакетный вспомогательный класс для установки и снятия специфичной 
 * для платформы поддержки выключения при по сигналу завершения (termination-triggered shutdowns).
 *
 * @author   Mark Reinhold
 * @version  1.12, 10/03/23
 * @since    1.3
 */

class Terminator {

    private static SignalHandler handler = null;

    /* Вызовы setup и teardown уже синхронизированы на блокировке 
     * shutdown, так что дополнительная синхронизация здесь не требуется. 
     */

    static void setup() {
        if (handler != null) return;
        SignalHandler sh = new SignalHandler() {
            public void handle(Signal sig) {
                Shutdown.exit(sig.getNumber() + 0200);
            }
        };
        handler = sh;
        try {
            Signal.handle(new Signal("INT"), sh);
            Signal.handle(new Signal("TERM"), sh);
        } catch (IllegalArgumentException e) {
            // При указании -Xrs пользователь отвечает за 
            // обеспечение того, что ловушки при выключении (shutdown hooks)  
            // запустятся при вызове System.exit()
        }
    }

    static void teardown() {
        /* В настоящее время класс sun.misc.Signal не поддерживает
         * отмену обработчиков.
         */
    }

}
