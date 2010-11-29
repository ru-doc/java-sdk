/*
 * @(#)UnknownError.java	1.15 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда произошло неизвестное, но серьезное исключение в 
 * виртуальной машине Java.
 *
 * @author unascribed
 * @version 1.15, 03/23/10
 * @since   JDK1.0
 */
public
class UnknownError extends VirtualMachineError {
    /**
     * Конструирует <code>UnknownError</code> без уточняющего сообщения. 
     */
    public UnknownError() {
        super();
    }

    /**
     * Конструирует <code>UnknownError</code> с указанным уточняющим сообщением.
     * 
     *
     * @param   s   уточняющее сообщение.
     */
    public UnknownError(String s) {
        super(s);
    }
}
