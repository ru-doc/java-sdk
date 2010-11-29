/*
 * @(#)VerifyError.java	1.15 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда "верификатор" обнаруживает, что файл класса, хотя и имеет 
 * верный формат, содержит некоторого рода внутреннюю несогласованность
 * или проблему безопасности. 
 *
 * @author  unascribed
 * @version 1.15, 03/23/10
 * @since   JDK1.0
 */
public
class VerifyError extends LinkageError {
    /**
     * Конструирует <code>VerifyError</code> без уточняющего сообщения.
     */
    public VerifyError() {
        super();
    }

    /**
     * Конструирует <code>VerifyError</code> с указанным уточняющим сообщением.
     * 
     * @param   s   уточняющее сообщение.
     */
    public VerifyError(String s) {
        super(s);
    }
}
