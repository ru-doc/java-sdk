/*
 * @(#)StackOverflowError.java	1.23 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда переполняется стек из-за того, что рекурсия в приложении
 * слишком глубокая. 
 *
 * @author unascribed
 * @version 1.23, 03/23/10
 * @since   JDK1.0
 */
public
class StackOverflowError extends VirtualMachineError {
    /**
     * Конструирует <code>StackOverflowError</code> без уточняющего сообщения.
     */
    public StackOverflowError() {
        super();
    }

    /**
     * Конструирует <code>StackOverflowError</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public StackOverflowError(String s) {
        super(s);
    }
}
