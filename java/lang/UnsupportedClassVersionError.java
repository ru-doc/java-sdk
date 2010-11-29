/*
 * @(#)UnsupportedClassVersionError.java	1.12 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда виртуальная машина Java пытается прочитать файл класса
 * и определяет, что старший и младший номера версий в файле не 
 * поддерживаются.
 *
 * @since   1.2
 */
public
class UnsupportedClassVersionError extends ClassFormatError {
    /**
     * Конструирует <code>UnsupportedClassVersionError</code> без уточняющего 
     * сообщения. 
     */
    public UnsupportedClassVersionError() {
        super();
    }

    /**
     * Конструирует <code>UnsupportedClassVersionError</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public UnsupportedClassVersionError(String s) {
        super(s);
    }
}
