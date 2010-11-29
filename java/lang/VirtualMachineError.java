/*
 * @(#)VirtualMachineError.java	1.16 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сказать, что виртуальная машина Java нарушена (is broken)
 * или исчерпала ресурсы, необходимые для продолжения операции. 
 *
 *
 * @author  Frank Yellin
 * @version 1.16, 03/23/10
 * @since   JDK1.0
 */
abstract public
class VirtualMachineError extends Error {
    /**
     * Конструирует <code>VirtualMachineError</code> без уточняющего сообщения.
     */
    public VirtualMachineError() {
        super();
    }

    /**
     * Конструирует <code>VirtualMachineError</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public VirtualMachineError(String s) {
        super(s);
    }
}
