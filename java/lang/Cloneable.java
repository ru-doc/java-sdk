/*
 * @(#)Cloneable.java	1.18 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Класс, реализующий интерфейс <code>Cloneable</code>, сообщает методу
 * {@link java.lang.Object#clone()}, что этому методу допустимо 
 * делать побитовую (field-for-field) копию инстанций этого класса.
 * 
 * <p>
 * Вызов метода <code>clone</code> <code>Object</code>'а на инстанции,
 * которая не реализует интерфейс <code>Cloneable</code>, приведет к 
 * выбросу исключения <code>CloneNotSupportedException</code>.
 * <p>
 * По соглашению, классы, реализующие этот интерфейс, должны переопределить
 * метод <tt>Object.clone</tt> (являющийся защищенным) как публичный (public).
 * Смотрите {@link java.lang.Object#clone()} для подробностей относительно 
 * переопределения этого метода.
 * <p>
 * Заметьте, что этот интерфейс <i>не</i> содержит метода <tt>clone</tt>.
 * Поэтому невозможно скопировать объект просто на основании того факта,
 * что он реализует этот интерфейс. Доже если метод копирования вызывается
 * рефлексивно, это не гарантирует, что он будет успешен.
 *
 * @author  unascribed
 * @version 1.18, 03/23/10
 * @see     java.lang.CloneNotSupportedException
 * @see     java.lang.Object#clone()
 * @since   JDK1.0
 */
public interface Cloneable { 
}
