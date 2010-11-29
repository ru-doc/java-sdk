/*
 * @(#)NoSuchMethodException.java	1.15 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда указанный метод не может быть найден.
 *
 * @author     unascribed
 * @version    1.15, 03/23/10
 * @since      JDK1.0
 */
public
class NoSuchMethodException extends Exception {
    /**
     * Конструирует <code>NoSuchMethodException</code> без уточняющего сообщения.
     */
    public NoSuchMethodException() {
        super();
    }

    /**
     * Конструирует <code>NoSuchMethodException</code> с уточняющим сообщением. 
     *
     * @param      s   уточняющее сообщение.
     */
    public NoSuchMethodException(String s) {
        super(s);
    }
}
