/*
 * @(#)OutOfMemoryError.java	1.23 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда виртуальная машина Java не может разместить объект,
 * потому что закончилась память и больше нет памяти, которую мог бы
 * сделать доступной сборщик мусора. 
 *
 * @author  unascribed
 * @version 1.23, 03/23/10
 * @since   JDK1.0
 */
public
class OutOfMemoryError extends VirtualMachineError {
    /**
     * Конструирует <code>OutOfMemoryError</code> без уточняющего сообщения.
     */
    public OutOfMemoryError() {
        super();
    }

    /**
     * Конструирует <code>OutOfMemoryError</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public OutOfMemoryError(String s) {
        super(s);
    }
}
