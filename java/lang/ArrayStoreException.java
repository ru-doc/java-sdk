/*
 * @(#)ArrayStoreException.java	1.13 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сказать, что совершается попытка сохранить неверный
 * тип объекта в массиве объектов. Например, следующий код 
 * генерирует <code>ArrayStoreException</code>: 
 * <p><blockquote><pre>
 *     Object x[] = new String[3];
 *     x[0] = new Integer(0);
 * </pre></blockquote>
 *
 * @author  unascribed
 * @version 1.13, 03/23/10
 * @since   JDK1.0
 */
public
class ArrayStoreException extends RuntimeException {
    /**
     * Конструирует <code>ArrayStoreException</code> без уточняющего сообщения.
     */
    public ArrayStoreException() {
        super();
    }

    /**
     * Конструирует <code>ArrayStoreException</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public ArrayStoreException(String s) {
        super(s);
    }
}

