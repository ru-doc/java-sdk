/*
 * @(#)ClassCastException.java	1.22 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы указать, что код пытается привести тип объекта
 * к подклассу, инстанцией которого он не является. Например, 
 * следующий код генерирует <code>ClassCastException</code>: 
 * <p><blockquote><pre>
 *     Object x = new Integer(0);
 *     System.out.println((String)x);
 * </pre></blockquote>
 *
 * @author  unascribed
 * @version 1.22, 03/23/10
 * @since   JDK1.0
 */
public
class ClassCastException extends RuntimeException {
    /**
     * Конструирует <code>ClassCastException</code> без уточняющего сообщения. 
     */
    public ClassCastException() {
        super();
    }

    /**
     * Конструирует <code>ClassCastException</code> с указанным уточняющим
     * сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public ClassCastException(String s) {
        super(s);
    }
}
