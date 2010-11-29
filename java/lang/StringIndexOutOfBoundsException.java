/*
 * @(#)StringIndexOutOfBoundsException.java	1.24 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается методами <code>String</code>, чтобы сказать, что индекс или
 * отрицателен, или больше чем размер строки. Для некоторых методов,
 * таких как метод {@link String#charAt charAt}, это исключение также 
 * бросается когда индекс равен размеру строки.
 *
 * @author  unascribed
 * @version 1.24, 03/23/10
 * @see     java.lang.String#charAt(int)
 * @since   JDK1.0
 */
public
class StringIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Конструирует <code>StringIndexOutOfBoundsException</code> без 
     * уточняющего сообщения.
     *
     * @since   JDK1.0.
     */
    public StringIndexOutOfBoundsException() {
        super();
    }

    /**
     * Конструирует <code>StringIndexOutOfBoundsException</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public StringIndexOutOfBoundsException(String s) {
        super(s);
    }

    /**
     * Конструирует <code>StringIndexOutOfBoundsException</code> 
     * с аргументом, определяющим недопустимый индекс. 
     *
     * @param   index   недопустимый индекс.
     */
    public StringIndexOutOfBoundsException(int index) {
        super("String index out of range: " + index);
    }
}
