/*
 * @(#)InternalError.java	1.23 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сообщить о некоторой неожиданной внутренней ошибке,
 * произошедшей в виртуальной машине Java. 
 *
 * @author  unascribed
 * @version 1.23, 03/23/10
 * @since   JDK1.0
 */
public
class InternalError extends VirtualMachineError {
    /**
     * Конструирует <code>InternalError</code> без уточняющего сообщения.
     */
    public InternalError() {
        super();
    }

    /**
     * Конструирует <code>InternalError</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public InternalError(String s) {
        super(s);
    }
}
