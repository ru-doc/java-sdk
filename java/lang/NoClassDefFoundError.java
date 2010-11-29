/*
 * @(#)NoClassDefFoundError.java	1.24 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, если виртуальная машина Java или инстанция <code>ClassLoader</code> 
 * (загрузчика класса) пытается загрузить определение класса (как часть нормального 
 * вызова метода или как часть создания новой инстанции, используя выражение 
 * <code>new</code>), а определение класса не было найдено. 
 * <p>
 * Разыскиваемое определение класса существовало, когда текущий исполняемый
 * класс компилировался, но определение более не может быть найдено.
 * 
 *
 * @author  unascribed
 * @version 1.24, 03/23/10
 * @since   JDK1.0
 */
public
class NoClassDefFoundError extends LinkageError {
    /**
     * Конструирует <code>NoClassDefFoundError</code> без уточняющего сообщения. 
     */
    public NoClassDefFoundError() {
        super();
    }

    /**
     * Конструирует <code>NoClassDefFoundError</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public NoClassDefFoundError(String s) {
        super(s);
    }
}
