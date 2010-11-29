/*
 * @(#)SecurityException.java	1.18 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */
package java.lang;

/**
 * Кидается менеджером безопасности для указания на нарушение безопасности. 
 *
 * @author  unascribed
 * @version 1.18, 03/23/10
 * @see     java.lang.SecurityManager
 * @since   JDK1.0
 */
public class SecurityException extends RuntimeException {

    private static final long serialVersionUID = 6878364983674394167L;

    /**
     * Конструирует <code>SecurityException</code> без уточняющего сообщения.
     */
    public SecurityException() {
        super();
    }

    /**
     * Конструирует <code>SecurityException</code> с указанным
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public SecurityException(String s) {
        super(s);
    }

    /**
     * Создает <code>SecurityException</code> с указанным
     * уточняющим сообщением и причиной.
     *
     * @param message уточняющее сообщение (которое сохраняется для 
     *        последующего получения методом {@link #getMessage()}).
     * @param cause причина(которая сохраняется для последующего получения
     *        методом {@link #getCause()}). (Значение <tt>null</tt> допустимо,
     *        и указывает, что причина не существует или неизвестна.)
     * @since 1.5
     */
    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Создает <code>SecurityException</code> с указанной причиной
     * и уточняющим сообщением <tt>(cause==null ? null : cause.toString())</tt>
     * (которое обычно содержит класс и уточняющее сообщение причины
     * (<tt>cause</tt>)).
     *
     * @param cause причина (которая сохраняется для последующего получения
     *        методом {@link #getCause()}). (Значение <tt>null</tt> допустимо,
     *        и указывает, что причина не существует или неизвестна.)
     * @since 1.5
     */
    public SecurityException(Throwable cause) {
        super(cause);
    }
}
