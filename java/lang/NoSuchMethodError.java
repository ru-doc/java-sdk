/*
 * @(#)NoSuchMethodError.java	1.23 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, если приложение пытается вызвать указанный метод класса
 * (или статический, или инстанции), а этот класс больше не имеет 
 * определения этого метода. 
 * <p>
 * Обычно, эта ошибка перехватывается компилятором; эта ошибка может
 * произойти во время выполнения только если определение класса имеет
 * несовместимые изменения. 
 *
 * @author  unascribed
 * @version 1.23, 03/23/10
 * @since   JDK1.0
 */
public
class NoSuchMethodError extends IncompatibleClassChangeError {
    /**
     * Конструирует <code>NoSuchMethodError</code> без уточняющего сообщения. 
     */
    public NoSuchMethodError() {
        super();
    }

    /**
     * Конструирует <code>NoSuchMethodError</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public NoSuchMethodError(String s) {
        super(s);
    }
}
