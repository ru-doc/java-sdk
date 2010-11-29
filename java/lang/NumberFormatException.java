/*
 * @(#)NumberFormatException.java	1.22 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, чтобы сказать, что приложение попыталось сконвертировать
 * строку в один из числовых типов, но та строка не имеет соответствующего
 * формата. 
 *
 * @author  unascribed
 * @version 1.22, 03/23/10
 * @see     java.lang.Integer#toString()
 * @since   JDK1.0
 */
public
class NumberFormatException extends IllegalArgumentException {
    static final long serialVersionUID = -2848938806368998894L;

    /**
     * Конструирует <code>NumberFormatException</code> без уточняющего сообщения.
     */
    public NumberFormatException() {
        super();
    }

    /**
     * Конструирует <code>NumberFormatException</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public NumberFormatException(String s) {
        super(s);
    }

    /**
     * Фабричный метод для создания <code>NumberFormatException</code>
     * получает указанный вход, который вызвал ошибку.
     *
     * @param   s   вход, который вызвал ошибку.
     */
    static NumberFormatException forInputString(String s) {
        return new NumberFormatException("For input string: \"" + s + "\"");
    }
}
