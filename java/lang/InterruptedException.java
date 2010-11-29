/*
 * @(#)InterruptedException.java	1.18 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда поток ожидает, спит или занят чем-то другим,
 * и поток прерывается, или до, или во время активности.
 * Иногда метод может пожелать проверить, был ли текущий поток прерван
 * и если да, то немедленно бросает это исключение.
 * Следующий код может использоваться для достижения этого эффекта:
 * 
 * <pre>
 *  if (Thread.interrupted())  // Очищает статус прерывания!
 *      throw new InterruptedException();
 * </pre>
 *
 * @author  Frank Yellin
 * @version 1.18, 03/23/10
 * @see     java.lang.Object#wait()
 * @see     java.lang.Object#wait(long)
 * @see     java.lang.Object#wait(long, int)
 * @see     java.lang.Thread#sleep(long)
 * @see     java.lang.Thread#interrupt()
 * @see     java.lang.Thread#interrupted()
 * @since   JDK1.0
 */
public
class InterruptedException extends Exception {
    /**
     * Конструирует <code>InterruptedException</code> без уточняющего сообщения.
     */
    public InterruptedException() {
        super();
    }

    /**
     * Конструирует <code>InterruptedException</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public InterruptedException(String s) {
        super(s);
    }
}
