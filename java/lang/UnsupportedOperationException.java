/*
 * @(#)UnsupportedOperationException.java	1.22 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сказать, что запрошенная операция не поддерживается.<p>
 *
 * Этот класс член
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Фреймворка коллекций Java (Java Collections Framework)</a>.
 *
 * @author  Josh Bloch
 * @version 1.22, 03/23/10
 * @since   1.2
 */
public class UnsupportedOperationException extends RuntimeException {
    /**
     * Конструирует <code>UnsupportedOperationException</code> без уточняющего собщения.
     */
    public UnsupportedOperationException() {
    }

    /**
     * Конструирует <code>UnsupportedOperationException</code> с указанным
     * уточняющим сообщением.
     *
     * @param message уточняющее сообщение.
     */
    public UnsupportedOperationException(String message) {
        super(message);
    }

    /**
     * Конструирует новое исключение с указанным уточняющим собщением и 
     * причиной.
     *
     * <p>Заметьте, что уточняющее сообщение, ассоциированное с <code>cause</code>, 
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
    public UnsupportedOperationException(String message, Throwable cause) {
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
     * @param  cause причина (которая сохраняется для последующего получения
     *         методом {@link #getCause()}). (Значение <tt>null</tt> допустимо,
     *         и указывает, что причина не существует или неизвестна.)
     *
     * @since  1.5
     */
    public UnsupportedOperationException(Throwable cause) {
        super(cause);
    }

    static final long serialVersionUID = -1242599979055084673L;
}
