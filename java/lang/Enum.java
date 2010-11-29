/*
 * @(#)Enum.java	1.17 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.io.Serializable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;

/**
 * Это общий базовый класс для всех типов перечисления языка Java.
 *
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @version 1.17, 03/23/10
 * @since   1.5
 */
public abstract class Enum<E extends Enum<E>>
        implements Comparable<E>, Serializable {
    /**
     * Имя этой константы перечисления, как объявлено в объявлении перечисления.
     * Большинство программистов должны использовать метод {@link #toString} 
     * вместо получения доступа к этому полю.
     */
    private final String name;

    /**
     * Возвращает имя этой константы перечисления, точно как объявлено в 
     * ее объявлении перечисления.
     * 
     * <b>Большинство программистов должны использовать метод {@link #toString} 
     * вместо этого, так как метод {@code toString} может вернуть более
     * дружественное-к-пользователю имя.</b> Этот метод предназначен прежде
     * всего для использования в специализированных ситуациях, где правильность
     * зависит от получения точного имени, которое не будет изменяться от релиза к релизу.
     *
     * @return имя этой константы перечисления
     */
    public final String name() {
        return name;
    }

    /**
     * Порядковый номер этой константы в перечислении (ее позиция
     * в объявлении перечисления, где начальной константе присваивается
     * нулевое порядковое значение).
     * 
     * Большинству программистов не нужно использовать это поле. Оно разработано 
     * для использования изощренными, основанными на перечислениях структурами
     * данных, таких как {@link java.util.EnumSet} и {@link java.util.EnumMap}.
     */
    private final int ordinal;

    /**
     * Возвращает порядковый номер этой константы в перечислении (ее позиция
     * в объявлении перечисления, где начальной константе присваивается
     * нулевое порядковое значение).
     * 
     * Большинству программистов не нужно использовать этот метод. Он разработан 
     * для использования изощренными, основанными на перечислениях структурами
     * данных, таких как {@link java.util.EnumSet} и {@link java.util.EnumMap}.
     *
     * @return порядковое значение этой константы в перечислении
     */
    public final int ordinal() {
        return ordinal;
    }

    /**
     * Единственный конструктор. Программисты не могут вызвать этот конструктор.
     * Он для использования кодом, выдаваемым компилятором в ответ на 
     * объявления перечисляемого типа.
     *
     * @param name Имя этой константы перечисления, являющееся идентификатором,
     *             использованном при ее объявлении.
     * @param ordinal Порядковый номер этой константы в перечислении (ее позиция
     *         в объявлении перечисления, где начальной константе присваивается
     *         нулевое порядковое значение).
     */
    protected Enum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    /**
     * Возвращает имя этой константы перечисления, как содержится в объявлении. 
     * Этот метод может быть переопределен, хотя это обычно не требуется 
     * или нежелательно. Тип перечисления должен переопределить этот метод, 
     * когда существует более "дружественная-к-программисту" строковая форма.
     *
     * @return имя этой константы перечисления.
     */
    public String toString() {
        return name;
    }

    /**
     * Возвращает <code>true</code> если указанный объект равен этой константе 
     * перечисления.
     *
     * @param other объект для сравнения на эквивалентность с этим объектом.
     * @return  <code>true</code>, если указанный объект равен этой константе
     *          перечисления.
     */
    public final boolean equals(Object other) { 
        return this==other;
    }

    /**
     * Возвращает хеш-код для этой константы перечисления.
     *
     * @return хеш-код для этой константы перечисления.
     */
    public final int hashCode() {
        return super.hashCode();
    }

    /**
     * Кидает CloneNotSupportedException. Это гарантирует, что перечисления
     * никогда не копируются, что необходимо для сохранения их статуса
     * "синглетона" (одиночки).
     *
     * @return (никогда не возвращается)
     */
    protected final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Сравнивает это перечисление с указанным объектом по порядковому номеру. 
     * Возвращает отрицательное число, ноль или положительное число, если этот
     * объект меньше, равен или больше, чем указанный объект.
     * 
     * Константы перечисления сравнимы только с другими константами перечисления 
     * того же самого типа перечисления. Естественный порядок, реализованный этим
     * методом, является порядком, в котором константы были объявлены.
     */
    public final int compareTo(E o) {
        Enum other = (Enum)o;
        Enum self = this;
        if (self.getClass() != other.getClass() && // оптимизация
            self.getDeclaringClass() != other.getDeclaringClass())
            throw new ClassCastException();
        return self.ordinal - other.ordinal;
    }

    /**
     * Возвращает объект класса, сответствующий типу перечисления этой константы
     * перечисления. Две константы перечисления <code>e1</code> и <code>e2</code> 
     * одного и того же типа перечисления тогда и только тогда, когда
     *   <code>e1.getDeclaringClass() == e2.getDeclaringClass()</code>.
     * (Значение, возвращаемое этим методом, может отличаться от значения, 
     * возвращаемого методом {@link Object#getClass} для констант перечисления
     * с constant-specific телами класса.)
     *
     * @return объект класса, соответствующий типу перечисления этой константы
     *         перечисления.
     */
    public final Class<E> getDeclaringClass() {
        Class clazz = getClass();
        Class zuper = clazz.getSuperclass();
        return (zuper == Enum.class) ? clazz : zuper;
    }

    /**
     * Возвращает константу перечисления указанного типа перечисления с
     * указанным именем. Имя должно точно соответствовать идентификатору,
     * использованном при объявлении константы этого типа. (Лишние
     * пробельные символы не допускаются.) 
     *
     * @param enumType объект <tt>Class</tt> типа перечисления, из которого
     *                 возвращается константа.
     * @param name имя константы для возврата.
     * @return константа перечисления указанного типа перечисления с 
     *         указанным именем.
     * @throws IllegalArgumentException, если указанный тип перечисления не
     *         имеет константы с указаным именем или указанный объект
     *         класса не представляет тип перечисления.
     * @throws NullPointerException, если <tt>enumType</tt> или <tt>name</tt>
     *         равны <code>null</code>.
     * @since 1.5
     */
    public static <T extends Enum<T>> T valueOf(Class<T> enumType,
                                                String name) {
        T result = enumType.enumConstantDirectory().get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException(
            "No enum const " + enumType +"." + name);
    }

    /**
      * Предотвращает десериализацию по умолчанию.
      */
    private void readObject(ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        throw new InvalidObjectException("can't deserialize enum");
    }

    private void readObjectNoData() throws ObjectStreamException {
        throw new InvalidObjectException("can't deserialize enum");
    }

    /**
     * Классы перечислений не могут иметь методов финализации.
     */
    protected final void finalize() { }
}
