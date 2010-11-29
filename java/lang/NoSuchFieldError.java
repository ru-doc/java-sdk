/*
 * @(#)NoSuchFieldError.java	1.14 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, если приложение пытается получить доступ или модифицировать
 * указанное поле объекта, и объект больше не имеет такого поля.
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
class NoSuchFieldError extends IncompatibleClassChangeError {
    /**
     * Конструирует <code>NoSuchFieldError</code> без уточняющего сообщения.
     */
    public NoSuchFieldError() {
        super();
    }

    /**
     * Конструирует <code>NoSuchFieldError</code> с указанным 
     * уточняющим сообщением.
     *
     * @param   s   уточняющее сообщение.
     */
    public NoSuchFieldError(String s) {
        super(s);
    }
}
