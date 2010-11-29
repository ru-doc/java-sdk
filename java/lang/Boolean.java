/*
 * @(#)Boolean.java	1.54 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Класс <code>Boolean</code> обертывает значение примитивного типа
 * <code>boolean</code> в объект. Объект типа
 * <code>Boolean</code> содержит одно поле, имеющее тип
 * <code>boolean</code>. 
 * <p>
 * В дополнение, класс предоставляет много методов для преобразования
 * примитивных булевых значений в строки и 
 * строк в примитивные булевы значения, равно как и другие
 * константы и методы, полезные, когда имеешь дело с
 * <code>boolean</code>. 
 *
 * @author  Arthur van Hoff
 * @version 1.54, 03/23/10
 * @since   JDK1.0
 */
public final class Boolean implements java.io.Serializable,
                                      Comparable<Boolean>
{
    /** 
     * Объект <code>Boolean</code>, соответствующий примитивному
     * значению <code>true</code>. 
     */
    public static final Boolean TRUE = new Boolean(true);

    /** 
     * Объект <code>Boolean</code>, соответствующий примитивному
     * значению <code>false</code>. 
     */
    public static final Boolean FALSE = new Boolean(false);

    /**
     * Объект Class, представляющий примитивный тип boolean.
     *
     * @since   JDK1.1
     */
    public static final Class<Boolean> TYPE = Class.getPrimitiveClass("boolean");

    /**
     * Значение <code>Boolean</code>.
     *
     * @serial
     */
    private final boolean value;

    /** используется serialVersionUID из JDK 1.0.2 для функциональной совместимости */
    private static final long serialVersionUID = -3665804199014368530L;

    /**
     * Выделяет память под объект <code>Boolean</code>, представляющий аргумент
     * <code>value</code>. 
     *
     * <p><b>Примечание: Редка когда следует использовать этот конструктор.
     * Если не требуется <i>новая</i> инстанция, статическая фабрика 
     * {@link #valueOf(boolean)} обычно лучший выбор. Она, вероятно, займет 
     * существенно меньше места и будет производительнее по времени.</b>
     * 
     * @param   value   значение <code>Boolean</code>.
     */
    public Boolean(boolean value) {
        this.value = value;
    }

    /**
     * Выделяет память под объект <code>Boolean</code>, представляющий значение 
     * <code>true</code>, если строковый аргумент не <code>null</code> 
     * и равен, игнорируя регистр, строке {@code "true"}. 
     * В противном случае выделяет память под объект <code>Boolean</code>, 
     * представляющий значение <code>false</code>. Примеры:<p>
     * {@code new Boolean("True")} создаст объект <tt>Boolean</tt>, 
     * представляющий <tt>true</tt>.<br>
     * {@code new Boolean("yes")} создаст объект <tt>Boolean</tt>, 
     * представляющий <tt>false</tt>.
     *
     * @param   s   строка для конвертации в <code>Boolean</code>.
     */
    public Boolean(String s) {
        this(toBoolean(s));
    }

    /**
     * Парсит строковый аргумент как булево значение. Возвращается <code>boolean</code> 
     * представляющий значение <code>true</code>, если строковый аргумент 
     * не <code>null</code> и равен, игнорируя регистр, строке
     * {@code "true"}. <p>
     * Пример: {@code Boolean.parseBoolean("True")} возвращает <tt>true</tt>.<br>
     * Пример: {@code Boolean.parseBoolean("yes")} возвращает <tt>false</tt>.
     *
     * @param      s   строка, содержащая булево представление,
     *                 для разбора.
     * @return     булево значение, представляющее строковый аргумент.
     * @since 1.5
     */
    public static boolean parseBoolean(String s) {
        return toBoolean(s);
    }

    /**
     * Возвращает значение этого объекта <tt>Boolean</tt> как <code>boolean</code> 
     * примитив.
     *
     * @return  примитивное значение <code>boolean</code> этого объекта.
     */
    public boolean booleanValue() {
        return value;
    }

    /**
     * Возвращает инстанцию <tt>Boolean</tt>, представляющую указанное значение 
     * <tt>boolean</tt>. Если указанное <tt>boolean</tt> значение равно
     * <tt>true</tt>, этот метод возвращает <tt>Boolean.TRUE</tt>;
     * если оно равно <tt>false</tt>, этот метод возвращает <tt>Boolean.FALSE</tt>.
     * Если новая инстанция <tt>Boolean</tt> не требуется, этот метод
     * должен обычно использоваться вместо конструктора {@link #Boolean(boolean)},
     * так как этот метод, вероятно, займет существенно меньше места и
     * будет производительнее по времени.
     *
     * @param  b булево значение.
     * @return инстанция <tt>Boolean</tt>, представляющая <tt>b</tt>.
     * @since  1.4
     */
    public static Boolean valueOf(boolean b) {
        return (b ? TRUE : FALSE);
    }

    /**
     * Возвращает <code>Boolean</code> со значением, представляемым указанной 
     * строкой.  Возвращенный <code>Boolean</code> представляет значение 
     * <code>true</code>, если строковый аргумент не <code>null</code>
     * и равен, игнорируя регистр, строке {@code "true"}.
     *
     * @param   s   строка.
     * @return  значение <code>Boolean</code>, представляемое строкой.
     */
    public static Boolean valueOf(String s) {
        return toBoolean(s) ? TRUE : FALSE;
    }

    /**
     * Возвращает объект <tt>String</tt>, представляющий это <code>Boolean</code>
     * значение. Если этот объект возвращает значение <code>true</code>,
     * возвращаемая строка равна {@code "true"}. Иначе возвращаемая строка
     * равна {@code "false"}.
     *
     * @param b	<code>boolean</code> значение для конвертирования
     * @return строка, представляющая указанный <code>boolean</code>
     * @since 1.4
     */
    public static String toString(boolean b) {
        return b ? "true" : "false";
    }

    /**
     * Возвращает объект <tt>String</tt>, представляющий это <code>Boolean</code>
     * значение. Если этот объект возвращает значение <code>true</code>,
     * возвращаемая строка равна {@code "true"}. Иначе возвращаемая строка
     * равна {@code "false"}.
     *
     * @return  строковое представление этого объекта. 
     */
    public String toString() {
        return value ? "true" : "false";
    }

    /**
     * Возвращает хеш-код для этого объекта <tt>Boolean</tt>.
     *
     * @return  число <tt>1231</tt>, елси этот объект представляет
     * <tt>true</tt>; возвращает число <tt>1237</tt>, если этот объект
     * представляет <tt>false</tt>. 
     */
    public int hashCode() {
        return value ? 1231 : 1237;
    }

    /**
     * Возвращает <code>true</code> тогда и только тогда, когда аргумент не
     * <code>null</code> и объект <code>Boolean</code>, представляет
     * такое же булево значение, как и этот объект. 
     *
     * @param   obj   объект для сравнения.
     * @return  <code>true</code>, если объект <code>Boolean</code> представляет
     *          такое же значение; иначе <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Boolean) {
            return value == ((Boolean)obj).booleanValue();
        } 
        return false;
    }

    /**
     * Возвращает <code>true</code> тогда и только тогда, когда системное 
     * свойство, именуемое аргументом, существует и равняется строке
     * {@code "true"}. (Начиная с версии 1.0.2 платформы 
     * Java<small><sup>TM</sup></small>, проверка этой строки 
     * регистронезависимо.) Системное свойство получается
     * через <code>getProperty</code>, метод, определенный в классе
     * <code>System</code>.
     * <p>
     * Если нет свойства с указанным именем, или если указанное имя
     * пустое или равно <code>null</code>, возвращается <code>false</code>.
     *
     * @param   name   имя системного свойства.
     * @return  булево значение системного свояства.
     * @see     java.lang.System#getProperty(java.lang.String)
     * @see     java.lang.System#getProperty(java.lang.String, java.lang.String)
     */
    public static boolean getBoolean(String name) {
        boolean result = false;
        try {
            result = toBoolean(System.getProperty(name));
        } catch (IllegalArgumentException e) {
        } catch (NullPointerException e) {
        }
        return result;
    }

    /**
     * Сравнивает эту инстанцию <tt>Boolean</tt> с другой.
     *
     * @param   b инстанция <tt>Boolean</tt> для сравнения
     * @return  ноль, есль этот объект представляет такое же булево значение, как 
     *          аргумент; положительное число, если этот объект представляет <code>true</code>
     *          и аргумент представляет <code>false</code>; и отрицательное значение, если
     *          этот объект представляет <code>false</code> и аргумент представляет <code>true</code>.
     * @throws  NullPointerException если аргумент равен <tt>null</tt>
     * @see     Comparable
     * @since  1.5
     */
    public int compareTo(Boolean b) {
        return (b.value == value ? 0 : (value ? 1 : -1));
    }

    private static boolean toBoolean(String name) { 
        return ((name != null) && name.equalsIgnoreCase("true"));
    }
}
