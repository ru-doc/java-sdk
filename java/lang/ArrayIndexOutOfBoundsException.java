/*
 * @(#)ArrayIndexOutOfBoundsException.java	1.23 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сказать, что к массиву обратились по неверному индексу.
 * Индекс или отрицателен, или больше или равен размеру массива.
 * 
 *
 * @author  unascribed
 * @version 1.23, 03/23/10
 * @since   JDK1.0
 */
public
class ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Конструирует <code>VerifyError</code> без уточняющего сообщения.
     *  
     */
    public ArrayIndexOutOfBoundsException() {
        super();
    }

    /**
     * Конструирует <code>ArrayIndexOutOfBoundsException</code> 
     * с аргументом, определяющим недопустимый индекс. 
     *
     * @param   index   недопустимый индекс.
     */
    public ArrayIndexOutOfBoundsException(int index) {
        super("Array index out of range: " + index);
    }

    /**
     * Конструирует <code>ArrayIndexOutOfBoundsException</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public ArrayIndexOutOfBoundsException(String s) {
        super(s);
    }
}
