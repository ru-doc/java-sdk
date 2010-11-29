/*
 * @(#)ClassFormatError.java	1.22 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда виртуальная машина Java пытается прочитать файл класса и 
 * определяет, имеет неверный формат или иначе не может интерпретироваться
 * как файл класса. 
 *
 * @author  unascribed
 * @version 1.22, 03/23/10
 * @since   JDK1.0
 */
public
class ClassFormatError extends LinkageError {
    /**
     * Конструирует <code>ClassFormatError</code> без уточняющего сообщения.
     */
    public ClassFormatError() {
        super();
    }

    /**
     * Конструирует <code>ClassFormatError</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public ClassFormatError(String s) {
        super(s);
    }
}
