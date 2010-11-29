/*
 * @(#)InstantiationException.java	1.20 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда приложение пытается создать инстанцию класса,
 * используя метод {@code newInstance} в классе 
 * {@code Class}, но указанный объект класса не может быть инстанцирован.
 * Инстанцирование может потерпеть неудачу по различным причинам,
 *  включая, но не ограничиваясь:
 *
 * <ul>
 * <li> объект класса представляет абстрактный класс, интерфейс или 
 *      класс массива, иримитивный тип или {@code void};
 * <li> класс не имеет безаргументного конструктора.
 *</ul>
 *
 * @author  unascribed
 * @version 1.20, 03/23/10
 * @see     java.lang.Class#newInstance()
 * @since   JDK1.0
 */
public
class InstantiationException extends Exception {
    /**
     * Конструирует {@code InstantiationException} без уточняющего сообщения.
     */
    public InstantiationException() {
        super();
    }

    /**
     * Конструирует {@code InstantiationException} с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public InstantiationException(String s) {
        super(s);
    }
}
