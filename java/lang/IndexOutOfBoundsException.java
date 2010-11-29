/*
 * @(#)IndexOutOfBoundsException.java	1.13 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается для сообщения о том, что индекс некоторого рода (такой, как массива,
 * строки или вектора) вне диапазона. 
 * <p>
 * Приложения могут подклассифицировать этот класс для сообщения о подобных исключениях. 
 *
 * @author  Frank Yellin
 * @version 1.13, 03/23/10
 * @since   JDK1.0
 */
public
class IndexOutOfBoundsException extends RuntimeException {
    /**
     * Конструирует <code>IndexOutOfBoundsException</code> без уточняющего 
     * сообщения.
     */
    public IndexOutOfBoundsException() {
        super();
    }

    /**
     * Конструирует <code>IndexOutOfBoundsException</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public IndexOutOfBoundsException(String s) {
        super(s);
    }
}
