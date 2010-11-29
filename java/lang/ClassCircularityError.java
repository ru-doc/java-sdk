/*
 * @(#)ClassCircularityError.java	1.17 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда была обнаружена циклическая зависимость при инициализации класса.
 *
 * @author     unascribed
 * @version    1.17, 03/23/10
 * @since      JDK1.0
 */
public class ClassCircularityError extends LinkageError {
    /**
     * Конструирует <code>ClassCircularityError</code> без уточняющего сообщения.
     */
    public ClassCircularityError() {
        super();
    }

    /**
     * Конструирует <code>ClassCircularityError</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public ClassCircularityError(String s) {
        super(s);
    }
}
