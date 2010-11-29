/*
 * @(#)ArithmeticException.java	1.24 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда возникает исключительное арифметическое условие. 
 * Например, целочисленное "деление на ноль" кидается как
 * инстанция этого класса. 
 *
 * @author  unascribed
 * @version 1.24, 03/23/10
 * @since   JDK1.0
 */
public
class ArithmeticException extends RuntimeException {
    /**
     * Конструирует <code>ArithmeticException</code> без уточняющего 
     * сообщения.
     */
    public ArithmeticException() {
        super();
    }

    /**
     * Конструирует <code>ArithmeticException</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public ArithmeticException(String s) {
        super(s);
    }
}
