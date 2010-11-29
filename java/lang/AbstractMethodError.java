/*
 * @(#)AbstractMethodError.java	1.21 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда приложение пытается вызвать абстрактный метод.
 * Обычно, эта ошибка перехватывается компилятором; эта ошибка может
 * произойти во время выполнениия только если определение некоторого класса
 * имеет несовместимые изменения со времени последней компиляции текущего 
 * выполняемого метода.
 *
 * @author  unascribed
 * @version 1.21, 03/23/10
 * @since   JDK1.0
 */
public
class AbstractMethodError extends IncompatibleClassChangeError {
    /**
     * Конструирует <code>AbstractMethodError</code> без уточняющего сообщения.
     */
    public AbstractMethodError() {
        super();
    }

    /**
     * Конструирует <code>AbstractMethodError</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public AbstractMethodError(String s) {
        super(s);
    }
}
