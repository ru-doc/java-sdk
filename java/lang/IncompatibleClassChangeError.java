/*
 * @(#)IncompatibleClassChangeError.java	1.20 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда произошли несовместимые изменения класса в некотором
 * определении класса. Определение некоторого класса, от которого в настоящее
 * время зависит исполняемый метод, с тех пор изменилось. 
 *
 * @author  unascribed
 * @version 1.20, 03/23/10
 * @since   JDK1.0
 */
public
class IncompatibleClassChangeError extends LinkageError {
    /**
     * Конструирует <code>IncompatibleClassChangeError</code> без уточняющего 
     * сообщения.
     */
    public IncompatibleClassChangeError () {
        super();
    }

    /**
     * Конструирует <code>IncompatibleClassChangeError</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public IncompatibleClassChangeError(String s) {
        super(s);
    }
}
