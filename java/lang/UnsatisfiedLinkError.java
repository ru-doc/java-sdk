/*
 * @(#)UnsatisfiedLinkError.java	1.23 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда виртуальная машина Java не может найти соответствующее
 * определение метода, объявленного как <code>native</code>, на "родном" языке.
 *
 * @author unascribed
 * @version 1.23, 03/23/10
 * @see     java.lang.Runtime
 * @since   JDK1.0
 */
public
class UnsatisfiedLinkError extends LinkageError {
    /**
     * Конструирует <code>UnsatisfiedLinkError</code> без уточняющего сообщения.
     */
    public UnsatisfiedLinkError() {
        super();
    }

    /**
     * Конструирует <code>UnsatisfiedLinkError</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public UnsatisfiedLinkError(String s) {
        super(s);
    }
}
