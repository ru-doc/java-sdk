/*
 * @(#)Byte.java	1.42 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 *
 * Класс <code>Byte</code> обертывает значение примитивного типа
 * <code>byte</code> в объект. Объект типа
 * <code>Byte</code> содержит одно поле, имеющее тип
 * <code>byte</code>. 
 *
 * <p>
 *
 * В дополнение, класс предоставляет много методов для преобразования
 * значений <code>byte</code> в строки и строк в <code>byte</code>, 
 * равно как и другие константы и методы, полезные, когда имеешь 
 * дело с <code>byte</code>. 
 *
 * @author  Nakul Saraiya
 * @version 1.42, 03/23/10
 * @see     java.lang.Number
 * @since   JDK1.1
 */
public final class Byte extends Number implements Comparable<Byte> {

    /**
     * Константа, хранящая минимальное значение для типа <code>byte</code>,
     * -2<sup>7</sup>.
     */
    public static final byte   MIN_VALUE = -128;

    /**
     * AКонстанта, хранящая максимальное значение для типа <code>byte</code>,
     * 2<sup>7</sup>-1.
     */
    public static final byte   MAX_VALUE = 127;

    /**
     * Инстанция <code>Class</code>, представляющая примитивный тип 
     * <code>byte</code>.
     */
    public static final Class<Byte>	TYPE = (Class<Byte>) Class.getPrimitiveClass("byte");

    /**
     * Возвращает новый объект <code>String</code>, представляющий указанный
     * <code>byte</code>. Основание системы счисления принимается за 10.
     *
     * @param b <code>byte</code> для преобразования.
     * @return строка, представляющая указанный <code>byte</code>.
     * @see java.lang.Integer#toString(int)
     */
    public static String toString(byte b) {
        return Integer.toString((int)b, 10);
    }

    private static class ByteCache {
        private ByteCache(){}

        static final Byte cache[] = new Byte[-(-128) + 127 + 1];

        static {
            for(int i = 0; i < cache.length; i++)
                cache[i] = new Byte((byte)(i - 128));
        }
    }

    /**
     * Возвращает инстанцию <tt>Byte</tt>, представляющую указанное значение
     * <tt>byte</tt>.
     * Если новая инстанция <tt>Byte</tt> не требуется, этот метод
     * должен обычно использоваться вместо конструктора
     * {@link #Byte(byte)}, так как этот метод, вероятно, использует 
     * существенно меньше места и лучше по производительности, из-за 
     * кеширования часто требуемых значений.
     *
     * @param  b байтовое значение.
     * @return инстанция <tt>Byte</tt>, представляющая <tt>b</tt>.
     * @since  1.5
     */
    public static Byte valueOf(byte b) {
        final int offset = 128;
        return ByteCache.cache[(int)b + offset];
    }

    /**
     * Parses the string argument as a signed decimal
     * <code>byte</code>. The characters in the string must all be
     * decimal digits, except that the first character may be an ASCII
     * minus sign <code>'-'</code> (<code>'&#92;u002D'</code>) to
     * indicate a negative value. The resulting <code>byte</code> value is
     * returned, exactly as if the argument and the radix 10 were
     * given as arguments to the {@link #parseByte(java.lang.String,
     * int)} method.
     *
     * @param s     a <code>String</code> containing the 
     *              <code>byte</code> representation to be parsed
     * @return      the <code>byte</code> value represented by the 
     *              argument in decimal
     * @exception   NumberFormatException if the string does not
     *              contain a parsable <code>byte</code>.
     */
    public static byte parseByte(String s) throws NumberFormatException {
        return parseByte(s, 10);
    }

    /**
     * Parses the string argument as a signed <code>byte</code> in the
     * radix specified by the second argument. The characters in the
     * string must all be digits, of the specified radix (as
     * determined by whether {@link java.lang.Character#digit(char,
     * int)} returns a nonnegative value) except that the first
     * character may be an ASCII minus sign <code>'-'</code>
     * (<code>'&#92;u002D'</code>) to indicate a negative value.  The
     * resulting <code>byte</code> value is returned.
     * <p>
     * An exception of type <code>NumberFormatException</code> is
     * thrown if any of the following situations occurs:
     * <ul>
     * <li> The first argument is <code>null</code> or is a string of
     * length zero.
     *
     * <li> The radix is either smaller than {@link
     * java.lang.Character#MIN_RADIX} or larger than {@link
     * java.lang.Character#MAX_RADIX}.
     *
     * <li> Any character of the string is not a digit of the specified
     * radix, except that the first character may be a minus sign
     * <code>'-'</code> (<code>'&#92;u002D'</code>) provided that the
     * string is longer than length 1.
     *
     * <li> The value represented by the string is not a value of type
     * <code>byte</code>.
     * </ul>
     *
     * @param s     the <code>String</code> containing the 
     *              <code>byte</code>
     *              representation to be parsed
     * @param radix the radix to be used while parsing <code>s</code>
     * @return      the <code>byte</code> value represented by the string 
     *                   argument in the specified radix
     * @exception   NumberFormatException If the string does
     *              not contain a parsable <code>byte</code>.
     */
    public static byte parseByte(String s, int radix)
        throws NumberFormatException {
        int i = Integer.parseInt(s, radix);
        if (i < MIN_VALUE || i > MAX_VALUE)
            throw new NumberFormatException(
                    "Value out of range. Value:\"" + s + "\" Radix:" + radix);
        return (byte)i;
    }

    /**
     * Returns a <code>Byte</code> object holding the value
     * extracted from the specified <code>String</code> when parsed
     * with the radix given by the second argument. The first argument
     * is interpreted as representing a signed <code>byte</code> in
     * the radix specified by the second argument, exactly as if the
     * argument were given to the {@link #parseByte(java.lang.String,
     * int)} method. The result is a <code>Byte</code> object that
     * represents the <code>byte</code> value specified by the string.
     * <p> In other words, this method returns a <code>Byte</code> object
     * equal to the value of:
     *
     * <blockquote><code>
     * new Byte(Byte.parseByte(s, radix))
     * </code></blockquote>
     *
     * @param s     the string to be parsed
     * @param radix the radix to be used in interpreting <code>s</code>
     * @return      a <code>Byte</code> object holding the value 
     *              represented by the string argument in the 
     *              specified radix.
     * @exception   NumberFormatException If the <code>String</code> does 
     *              not contain a parsable <code>byte</code>.
     */
    public static Byte valueOf(String s, int radix)
        throws NumberFormatException {
        return new Byte(parseByte(s, radix));
    }

    /**
     * Returns a <code>Byte</code> object holding the value
     * given by the specified <code>String</code>. The argument is
     * interpreted as representing a signed decimal <code>byte</code>,
     * exactly as if the argument were given to the {@link
     * #parseByte(java.lang.String)} method. The result is a
     * <code>Byte</code> object that represents the <code>byte</code>
     * value specified by the string.  <p> In other words, this method
     * returns a <code>Byte</code> object equal to the value of:
     *
     * <blockquote><code>
     * new Byte(Byte.parseByte(s))
     * </code></blockquote>
     *
     * @param s     the string to be parsed
     * @return      a <code>Byte</code> object holding the value
     *              represented by the string argument
     * @exception   NumberFormatException If the <code>String</code> does
     *              not contain a parsable <code>byte</code>.
     */
    public static Byte valueOf(String s) throws NumberFormatException {
        return valueOf(s, 10);
    }

    /**
     * Декодирует строку в объект <code>Byte</code>.
     * Принимает десятичные, шестнадцатеричные и восьмеричные числа,
     * представленные следующей грамматикой:
     *
     * <blockquote>
     * <dl>
     * <dt><i>DecodableString:</i>
     * <dd><i>Знак<sub>необ.</sub> DecimalNumeral</i>
     * <dd><i>Знак<sub>необ.</sub></i> <code>0x</code> <i>HexDigits</i>
     * <dd><i>Знак<sub>необ.</sub></i> <code>0X</code> <i>HexDigits</i>
     * <dd><i>Знак<sub>необ.</sub></i> <code>#</code> <i>HexDigits</i>
     * <dd><i>Знак<sub>необ.</sub></i> <code>0</code> <i>OctalDigits</i>
     * <p>
     * <dt><i>Знак:</i>
     * <dd><code>-</code>
     * </dl>
     * </blockquote>
     *
     * <i>DecimalNumeral</i>, <i>HexDigits</i>, и <i>OctalDigits</i>
     * определены в <a href="http://java.sun.com/docs/books/jls/second_edition/html/lexical.doc.html#48282">&sect;3.10.1</a> 
     * <a href="http://java.sun.com/docs/books/jls/html/">Java 
     * Language Specification</a>.
     * <p>
     * Последовательность символов, следующая после (необязательного) знака
     * отрицания и/или спецификатора основания системы счисления (&quot;<code>0x</code>&quot;,
     * &quot;<code>0X</code>&quot;, &quot;<code>#</code>&quot; или
     * ведущий ноль), разбирается так же, как методом <code>Byte.parseByte</code>
     * с указанием основания системы счисления (10, 16, или 8). Эта последовательность 
     * символов должна представлять положительное значение или будет выкинуто
     * исключение {@link NumberFormatException}. Результат отрицателен, если
     * первый символ в указанной строке является знаком минус.
     * Пробельные символы в строке не допускаются.
     * 
     *
     * @param     nm строка для декодирования.
     * @return    объект <code>Byte</code>, хранящий значение <code>byte</code>,
     *            представляемое <code>nm</code>
     * @exception NumberFormatException, если строка не содержит
     *            распозноваемого значения <code>byte</code>.
     * @see java.lang.Byte#parseByte(java.lang.String, int)
     */
    public static Byte decode(String nm) throws NumberFormatException {
        int radix = 10;
        int index = 0;
        boolean negative = false;
        Byte result;

        // Обрабатываем знак минус, если есть
        if (nm.startsWith("-")) {
            negative = true;
            index++;
        }

        if (nm.startsWith("0x", index) || nm.startsWith("0X", index)) {
                index += 2;
                radix = 16;
        } else if (nm.startsWith("#", index)) {
            index++;
                radix = 16;
        } else if (nm.startsWith("0", index) && nm.length() > 1 + index) {
            index++;
            radix = 8;
        }

        if (nm.startsWith("-", index))
            throw new NumberFormatException("Negative sign in wrong position");

        try {
            result = Byte.valueOf(nm.substring(index), radix);
            result = negative ? new Byte((byte)-result.byteValue()) : result;
        } catch (NumberFormatException e) {
            // Если число равно Byte.MIN_VALUE, мы попадем сюда. Следующая
            // строка обрабатывает этот случай, и при любой настоящей ошибке
            // формата повторно кидает исключение.
            String constant = negative ? new String("-" + nm.substring(index))
                                       : nm.substring(index);
            result = Byte.valueOf(constant, radix);
        }
        return result;
    }

    /**
     * Значение <code>Byte</code>.
     *
     * @serial
     */
    private final byte value;

    /**
     * Конструирует и размещает в памяти новый объект <code>Byte</code>,
     * представляющий указанное значение <code>byte</code>'а.
     *
     * @param value значение, которое будет представлено
     *              <code>Byte</code>'ом.
     */
    public Byte(byte value) {
        this.value = value;
    }

    /**
     * Constructs a newly allocated <code>Byte</code> object that
     * represents the <code>byte</code> value indicated by the
     * <code>String</code> parameter. The string is converted to a
     * <code>byte</code> value in exactly the manner used by the
     * <code>parseByte</code> method for radix 10.
     *
     * @param s     строка, конвертируемая в 
     *              <code>Byte</code>
     * @exception   NumberFormatException Если строка не содержит
     *              распознаваемое значение <code>byte</code>.
     * @see         java.lang.Byte#parseByte(java.lang.String, int)
     */
    public Byte(String s) throws NumberFormatException {
        this.value = parseByte(s, 10);
    }

    /**
     * Возвращает значение этого объекта <code>Byte</code> как
     * <code>byte</code>.
     */
    public byte byteValue() {
        return value;
    }

    /**
     * Возвращает значение этого объекта <code>Byte</code> как
     * <code>short</code>.
     */
    public short shortValue() {
        return (short)value;
    }

    /**
     * Возвращает значение этого объекта <code>Byte</code> как
     * <code>int</code>.
     */
    public int intValue() {
        return (int)value;
    }

    /**
     * Возвращает значение этого объекта <code>Byte</code> как
     * <code>long</code>.
     */
    public long longValue() {
        return (long)value;
    }

    /**
     * Возвращает значение этого объекта <code>Byte</code> как
     * <code>float</code>.
     */
    public float floatValue() {
        return (float)value;
    }

    /**
     * Возвращает значение этого объекта <code>Byte</code> как
     * <code>double</code>.
     */
    public double doubleValue() {
        return (double)value;
    }

    /**
     * Возвращает объект <code>String</code>, представляющий это значение 
     * <code>Byte</code>'а. Значение преобразуется в знаковое десятичное
     * представление и возвращается как строка, точно так же, как если бы
     * значение <code>byte</code> было передано в метод 
     * {@link java.lang.Byte#toString(byte)}.
     *
     * @return  строковое представление значения этого объекта по 
     *          основанию&nbsp;10.
     */
    public String toString() {
        return String.valueOf((int)value);
    }

    /**
     * Возвращает хеш-код для этого <code>Byte</code>.
     */
    public int hashCode() {
        return (int)value;
    }

    /**
     * Сравнивает этот объект с указанным объектом. Результат равен
     * <code>true</code> тогда и только тогда, когда аргумент не
     * <code>null</code> и объект <code>Byte</code> содержит
     * такое же <code>byte</code> значение, как и этот объект.
     *
     * @param obj   объект для сравнения.
     * @return      <code>true</code> если объекты одинаковые;
     *              иначе <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Byte) {
            return value == ((Byte)obj).byteValue();
        }
        return false;
    }

    /**
     * Сравнивает два объекта <code>Byte</code> в числовом смысле.
     *
     * @param   anotherByte   <code>Byte</code> для сравнения.
     * @return  значение <code>0</code>, если этот объект <code>Byte</code> 
     *          равен аргументу <code>Byte</code>; значение, меньшее 
     *          <code>0</code>, если объект <code>Byte</code> в числовом смысле 
     *          меньше, чем аргумент <code>Byte</code>; и значение большее
     *          <code>0</code>, если этот объект <code>Byte</code> в числовом
     *          смысле больше, чем аргумент <code>Byte</code> (знаковое 
     *          сравнение).
     * @since   1.2
     */
    public int compareTo(Byte anotherByte) {
        return this.value - anotherByte.value;
    }

    /**
     * Число бит, используемых для представления значения <tt>byte</tt> 
     * in two's complement binary form.
     *
     * @since 1.5
     */
    public static final int SIZE = 8;

    /** используется serialVersionUID из JDK 1.0.2 для функциональной совместимости */
    private static final long serialVersionUID = -7183698231559129828L;

}
