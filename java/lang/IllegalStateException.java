/*
 * @(#)IllegalStateException.java	1.17 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Сообщает о том, что метод был вызван в некорректное или несоответствующее
 * время. Другими словами, окружение Java или приложение Java не в
 * соответствующем состоянии для требуемой операции.
 *
 *
 * @author  Jonni Kanerva
 * @version 1.17, 03/23/10
 * @since   JDK1.1
 */
public
class IllegalStateException extends RuntimeException {
    /**
     * Конструирует <code>IllegalStateException</code> без уточняющего сообщения.
     * Уточняющее сообщение, это строка, описывающая это конкретное исключение.
     */
    public IllegalStateException() {
        super();
    }

    /**
     * Конструирует <code>IllegalStateException</code> с указанным уточняющим
     * сообщением. Уточняющее сообщение, это строка, описывающая это конкретное
     * исключение.
     *
     * @param s the String that contains a detailed message
     */
    public IllegalStateException(String s) {
        super(s);
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
    public IllegalStateException(String message, Throwable cause) {
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
    public IllegalStateException(Throwable cause) {
        super(cause);
    }

    static final long serialVersionUID = -1848914673093119416L;
}
