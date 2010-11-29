/*
 * @(#)IllegalArgumentException.java	1.25 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сказать, что методу передали некорректный или 
 * несоответствующей аргумент.
 *
 * @author  unascribed
 * @version 1.25, 03/23/10
 * @see	    java.lang.Thread#setPriority(int)
 * @since   JDK1.0
 */
public
class IllegalArgumentException extends RuntimeException {
    /**
     * Конструирует <code>IllegalArgumentException</code> без 
     * уточняющего сообщения.
     */
    public IllegalArgumentException() {
        super();
    }

    /**
     * Конструирует <code>IllegalArgumentException</code> с указанным
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public IllegalArgumentException(String s) {
        super(s);
    }

    /**
     * Конструирует новое исключение с указанным уточняющим собщением и 
     * причиной.
     *
     * <p>Заметьте, что уточняющее сообщение, ассоциированное с <code>cause</code> 
     * автоматически <i>не</i> включается в уточняющее сообщение этого 
     * исключения.
     *
     * @param  message уточняющее сообщение (которое сохраняется для последующего 
     *         получения методом {@link Throwable#getMessage()}).
     * @param  cause причина (которая сохраняется для последующего получения
     *         методом {@link Throwable#getCause()}). (Значение <tt>null</tt>
     *         допускается и сообщает, что причина несуществует или 
     *         неизвестна.)
     * @since 1.5
     */
    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
 
    /**
     * Конструирует новое исключение с указанной причиной и уточняющим 
     * сообщением <tt>(cause==null ? null : cause.toString())</tt>
     * (которое обычно содержит класс и уточняющее сообщение причины 
     * (<tt>cause</tt>)). Этот конструктор полезен для исключений, которые 
     * являются несколько большим, чем обертками над другими кидаемыми объектами. 
     * (например, {@link java.security.PrivilegedActionException}).
     *
     * @param  cause причина(которая сохраняется для последующего получения
     *         методом {@link #getCause()}). (Значение <tt>null</tt> допустимо,
     *         и указывает, что причина не существует или неизвестна.)
     *
     * @since  1.5
     */
    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }

    private static final long serialVersionUID = -5365630128856068164L;
}
