/*
 * @(#)NoSuchFieldException.java	1.16 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Сообщает, что класс не имеет поля с указанным именем.
 *
 * @author  unascribed
 * @version 1.16, 03/23/10
 * @since   JDK1.1
 */
public class NoSuchFieldException extends Exception {
    /**
     * Конструктор.
     */
    public NoSuchFieldException() {
        super();
    }

    /**
     * Конструктор с уточняющим сообщением.
     *
     * @param s уточняющее сообщение.
     */
    public NoSuchFieldException(String s) {
        super(s);
    }
}
