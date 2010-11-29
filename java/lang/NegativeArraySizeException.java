/*
 * @(#)NegativeArraySizeException.java	1.21 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, если приложение пытается создать массив с отрицательным размером.
 *
 * @author  unascribed
 * @version 1.21, 03/23/10
 * @since   JDK1.0
 */
public
class NegativeArraySizeException extends RuntimeException {
    /**
     * Конструирует <code>NegativeArraySizeException</code> без 
     * уточняющего сообщения.
     */
    public NegativeArraySizeException() {
        super();
    }

    /**
     * Конструирует <code>NegativeArraySizeException</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public NegativeArraySizeException(String s) {
        super(s);
    }
}
