/*
 * @(#)CloneNotSupportedException.java	1.13 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сказать, что метод <code>clone</code> в классе
 * <code>Object</code> был вызван для копирования объекта, но класс этого
 * объекта не реализует интерфейс <code>Cloneable</code>.
 * 
 * <p>
 * Приложения, которые переопределяют метод <code>clone</code> также могут
 * кидать это исключение, чтобы сказать, что объект не может или не должен
 * копироваться.
 *
 * @author  unascribed
 * @version 1.13, 03/23/10
 * @see     java.lang.Cloneable
 * @see     java.lang.Object#clone()
 * @since   JDK1.0
 */

public
class CloneNotSupportedException extends Exception {
    /**
     * Конструирует <code>CloneNotSupportedException</code> без 
     * уточняющего сообщения.
     */
    public CloneNotSupportedException() {
        super();
    }

    /**
     * Конструирует <code>CloneNotSupportedException</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public CloneNotSupportedException(String s) {
        super(s);
    }
}
