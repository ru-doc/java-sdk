/*
 * @(#)IllegalThreadStateException.java	1.23 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сказать, что поток не в соответствующем состоянии 
 * для запрашиваемой операции. Смотрите, например, методы
 * <code>suspend</code> и <code>resume</code> с классе
 * <code>Thread</code>. 
 *
 * @author  unascribed
 * @version 1.23, 03/23/10
 * @see     java.lang.Thread#resume()
 * @see     java.lang.Thread#suspend()
 * @since   JDK1.0
 */
public class IllegalThreadStateException extends IllegalArgumentException {
    /**
     * Конструирует <code>IllegalThreadStateException</code> без 
     * уточняющего сообщения. 
     */
    public IllegalThreadStateException() {
        super();
    }

    /**
     * Конструирует <code>IllegalThreadStateException</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public IllegalThreadStateException(String s) {
        super(s);
    }
}
