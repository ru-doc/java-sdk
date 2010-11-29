/*
 * @(#)InstantiationError.java	1.14 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда приложение пытается использовать конструкцию Java 
 * <code>new</code> для инстанцирования абстрактного класса или интерфейса. 
 * <p>
 * Обычно, эта ошибка перехватывается компилятором; эта ошибка может
 * произойти во время выполнения только если определение класса имеет
 * несовместимые изменения. 
 *
 * @author  unascribed
 * @version 1.14, 03/23/10
 * @since   JDK1.0
 */


public
class InstantiationError extends IncompatibleClassChangeError {
    /**
     * Конструирует <code>InstantiationError</code> без уточняющего сообщения.
     */
    public InstantiationError() {
        super();
    }

    /**
     * Конструирует <code>InstantiationError</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public InstantiationError(String s) {
        super(s);
    }
}
