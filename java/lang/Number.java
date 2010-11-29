/*
 * @(#)Number.java	1.31 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Абстрактный класс <code>Number</code> является суперклассом классов
 * <code>BigDecimal</code>, <code>BigInteger</code>,
 * <code>Byte</code>, <code>Double</code>, <code>Float</code>,
 * <code>Integer</code>, <code>Long</code>, и <code>Short</code>.
 * <p>
 * Подклассы <code>Number</code> должны предоставить методы для преобразования
 * представляемого числового значения в <code>byte</code>, <code>double</code>,
 * <code>float</code>, <code>int</code>, <code>long</code>, и
 * <code>short</code>.
 *
 * @author	Lee Boynton
 * @author	Arthur van Hoff
 * @version 1.31, 03/23/10
 * @see     java.lang.Byte
 * @see     java.lang.Double
 * @see     java.lang.Float
 * @see     java.lang.Integer
 * @see     java.lang.Long
 * @see     java.lang.Short
 * @since   JDK1.0
 */
public abstract class Number implements java.io.Serializable {
    /**
     * Возвращает значение указанного числа как <code>int</code>.
     * Это может привести к округлению или урезанию.
     *
     * @return  числовое значение, представляющее этот объект после 
     *          преобразования к типу <code>int</code>.
     */
    public abstract int intValue();

    /**
     * Возвращает значение указанного числа как <code>long</code>.
     * Это может привести к округлению или урезанию.
     *
     * @return  числовое значение, представляющее этот объект после 
     *          преобразования к типу <code>long</code>.
     */
    public abstract long longValue();

    /**
     * Возвращает значение указанного числа как <code>float</code>.
     * Это может привести к округлению.
     *
     * @return  числовое значение, представляющее этот объект после 
     *          преобразования к типу <code>float</code>.
     */
    public abstract float floatValue();

    /**
     * Возвращает значение указанного числа как <code>double</code>.
     * Это может привести к округлению.
     *
     * @return  числовое значение, представляющее этот объект после 
     *          преобразования к типу <code>double</code>.
     */
    public abstract double doubleValue();

    /**
     * Возвращает значение указанного числа как <code>byte</code>.
     * Это может привести к округлению или урезанию.
     *
     * @return  числовое значение, представляющее этот объект после 
     *          преобразования к типу <code>byte</code>.
     * @since   JDK1.1
     */
    public byte byteValue() {
        return (byte)intValue();
    }

    /**
     * Возвращает значение указанного числа как <code>short</code>.
     * Это может привести к округлению или урезанию.
     *
     * @return  числовое значение, представляющее этот объект после 
     *          преобразования к типу <code>short</code>.
     * @since   JDK1.1
     */
    public short shortValue() {
        return (short)intValue();
    }

    /** используется serialVersionUID из JDK 1.0.2 для функциональной совместимости */
    private static final long serialVersionUID = -8742448824652078965L;
}
