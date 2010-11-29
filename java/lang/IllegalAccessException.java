/*
 * @(#)IllegalAccessException.java	1.16 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * <code>IllegalAccessException</code> кидается, когда приложение пытается 
 * рефлексивно создать инстанцию (не являющуюся массивом), 
 * установить или получить поле или вызвать метод, но выполняемый метод
 * не имеет доступа к определению указанного класса, поля, метода или
 * конструктора.
 *
 * @author  unascribed
 * @version 1.16, 03/23/10
 * @see     Class#newInstance()
 * @see     java.lang.reflect.Field#set(Object, Object)
 * @see     java.lang.reflect.Field#setBoolean(Object, boolean)
 * @see     java.lang.reflect.Field#setByte(Object, byte)
 * @see     java.lang.reflect.Field#setShort(Object, short)
 * @see     java.lang.reflect.Field#setChar(Object, char)
 * @see     java.lang.reflect.Field#setInt(Object, int)
 * @see     java.lang.reflect.Field#setLong(Object, long)
 * @see     java.lang.reflect.Field#setFloat(Object, float)
 * @see     java.lang.reflect.Field#setDouble(Object, double)
 * @see     java.lang.reflect.Field#get(Object)
 * @see     java.lang.reflect.Field#getBoolean(Object)
 * @see     java.lang.reflect.Field#getByte(Object)
 * @see     java.lang.reflect.Field#getShort(Object)
 * @see     java.lang.reflect.Field#getChar(Object)
 * @see     java.lang.reflect.Field#getInt(Object)
 * @see     java.lang.reflect.Field#getLong(Object)
 * @see     java.lang.reflect.Field#getFloat(Object)
 * @see     java.lang.reflect.Field#getDouble(Object)
 * @see     java.lang.reflect.Method#invoke(Object, Object[])
 * @see     java.lang.reflect.Constructor#newInstance(Object[])
 * @since   JDK1.0
 */
public class IllegalAccessException extends Exception {
    /**
     * Конструирует <code>IllegalAccessException</code> без 
     * уточняющего сообщения.
     */
    public IllegalAccessException() {
        super();
    }

    /**
     * Конструирует <code>IllegalAccessException</code> с указанным уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public IllegalAccessException(String s) {
        super(s);
    }
}
