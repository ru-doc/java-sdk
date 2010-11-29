/*
 * @(#)IllegalMonitorStateException.java	1.14 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сказать, что поток пытался ожидать на мониторе объекта или уведомить
 * другие потоки, ожидающие на мониторе объекта без владения указанным монитором. 
 * <p>Про синхронизацию отлично написано на {@link http://www.skipy.ru/technics/synchronization.html}
 * 
 * @author  unascribed
 * @version 1.14, 03/23/10
 * @see     java.lang.Object#notify()
 * @see     java.lang.Object#notifyAll()
 * @see     java.lang.Object#wait() 
 * @see     java.lang.Object#wait(long) 
 * @see     java.lang.Object#wait(long, int) 
 * @since   JDK1.0
 */
public
class IllegalMonitorStateException extends RuntimeException {
    /**
     * Конструирует <code>IllegalMonitorStateException</code> без 
     * уточняющего сообщения. 
     */
    public IllegalMonitorStateException() {
        super();
    }

    /**
     * Конструирует <code>IllegalMonitorStateException</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public IllegalMonitorStateException(String s) {
        super(s);
    }
}
