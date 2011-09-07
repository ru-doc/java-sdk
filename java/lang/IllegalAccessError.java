/*
 * @(#)IllegalAccessError.java	1.18 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, если приложение пытается получить доступ или модифицировать поле или 
 * вызвать метод, к которому не имеет доступа (из-за квалификаторов private или protected). 
 * <p>
 * Обычно эта ошибка перехватывается компилятором; эта ошибка может
 * произойти во время выполнения только если определение класса имеет
 * несовместимые изменения. 
 *
 * @author  unascribed
 * @version 1.18, 03/23/10
 * @since   JDK1.0
 */
public class IllegalAccessError extends IncompatibleClassChangeError {
    /**
     * Конструирует <code>IllegalAccessError</code> без уточняющего сообщения.
     */
    public IllegalAccessError() {
        super();
    }

    /**
     * Конструирует <code>IllegalAccessError</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public IllegalAccessError(String s) {
        super(s);
    }
}
