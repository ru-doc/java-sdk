/*
 * @(#)Short.java	1.46 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Класс <code>Short</code> обертывает значение примитивного типа
 * <code>short</code> в объект. Объект типа
 * <code>Short</code> содержит одно поле, имеющее тип
 * <code>short</code>. 
 *
 * <p>
 * 
 * В дополнение, класс предоставляет много методов для преобразования
 * значений <code>short</code> в строки и строк в <code>short</code>, 
 * равно как и другие константы и методы, полезные, когда имеешь 
 * дело с <code>short</code>. 
 *
 * @author  Nakul Saraiya
 * @version 1.46, 03/23/10
 * @see     java.lang.Number
 * @since   JDK1.1
 */
public final class Short extends Number implements Comparable<Short> {

    /**
     * Константа, хранящая минимальное значение для типа <code>short</code>,
     * -2<sup>15</sup>.
     */
    public static final short   MIN_VALUE = -32768;

    /**
     * Константа, хранящая максимальное значение для типа <code>short</code>
     * 2<sup>15</sup>-1.
     */
    public static final short   MAX_VALUE = 32767;

    /**
     * Инстанция <code>Class</code>, представляющая примитивный тип 
     * <code>short</code>.
     */
    public static final Class<Short>    TYPE = (Class<Short>) Class.getPrimitiveClass("short");

    /**
     * Возвращает новый объект <code>String</code>, представляющий указанный
     * <code>short</code>. Основание системы счисления принимается за 10.
     *
     * @param s <code>short</code> для преобразования.
     * @return строка, представляющая указанный <code>short</code>.
     * @see java.lang.Integer#toString(int)
     */
    public static String toString(short s) {
        return Integer.toString((int)s, 10);
    }

    /**
     * Разбирает строковый аргумент как знаковое десятичное число типа
     * <code>short</code>. Все символы в строке должны быть десятичными 
     * цифрами, исключая первый символ, который может быть ASCII-символом
     * знака минус <code>'-'</code> (<code>'&#92;u002D'</code>) для 
     * указания на отрицательное значение. Возвращает такое же значение типа 
     * <code>short</code>, как если бы был вызван метод 
     * {@link #parseShort(java.lang.String, int)} со вторым аргументом, 
     * равным 10.
     *
     * @param s     строка для разбора, содержащая представление значения 
     *              типа <code>short</code> 
     * @return      значение типа <code>short</code>, представляющее 
     *              аргумент в десятичном виде
     * @exception   NumberFormatException Если строка не содержит
     *              распознаваемое значение <code>short</code>.
     */
    public static short parseShort(String s) throws NumberFormatException {
        return parseShort(s, 10);
    }

    /**
     * Разбирает строковый аргумент как знаковое число типа <code>short</code> по
     * основанию, указанному вторым аргументом. Все символы в строке должны
     * быть цифрами в указанной системе счисления (что определяется 
     * неотрицательностью результата, возвращеного методом 
     * {@link java.lang.Character#digit(char, int)}), исключая первый символ, 
     * который может быть ASCII-символом знака минус <code>'-'</code>
     * (<code>'&#92;u002D'</code>) для указания на отрицательное значение.
     * В результате возвращает значение <code>short</code>.
     * <p>
     * При возникновении одной из следующих ситуаций кидается исключение 
     * типа <code>NumberFormatException</code>:
     * <ul>
     * <li> Первый аргумент равен <code>null</code> или строка нулевой 
     * длины.
     *
     * <li> Основание системы счисления меньше, чем {@link
     * java.lang.Character#MIN_RADIX} или больше, чем {@link
     * java.lang.Character#MAX_RADIX}.
     *
     * <li> Какой-то символ строки не является цифрой в указанной системе 
     * счисления, исключая первый символ, который может быть знаком 
     * <code>'-'</code> (<code>'&#92;u002D'</code>), при условии того, что
     * длина строки больше, чем 1.
     *
     * <li> Значение, представляемое строкой, не является значением типа 
     * <code>short</code>.
     * </ul>
     *
     * @param s     строка, содержащая представление 
     *              <code>short</code>, которая должна быть разобрана
     * @param radix основание системы счисления, используемое при интерпретации <code>s</code>
     * @return      значение <code>short</code>, представляемое строковым аргументом 
     *              в указанной системе счисления
     * @exception   NumberFormatException Если строка не содержит
     *              распознаваемое значение <code>short</code>.
     */
    public static short parseShort(String s, int radix)
        throws NumberFormatException {
        int i = Integer.parseInt(s, radix);
        if (i < MIN_VALUE || i > MAX_VALUE)
            throw new NumberFormatException(
                    "Value out of range. Value:\"" + s + "\" Radix:" + radix);
        return (short)i;
    }

    /**
     * Возвращает объект <code>Short</code>, хранящий значение, извлеченное
     * из указанной строки при ее разборе в системе счисления с основанием,
     * равным второму аргументу. Первый аргумент интерпретируется как 
     * представление знакового <code>short</code> в системе счисления, указанной
     * вторым аргументом, точно так же, как если бы аргумент был передан в
     * метод {@link #parseShort(java.lang.String, int)}. Результатом является 
     * объект <code>Short</code>, представляющий значение <code>short</code>,
     * представленное строкой.
     * <p> Другими словами, этот метод возвращает объект <code>Short</code>,
     * тождественный значению выражения:
     *
     * <blockquote><code>
     * new Short(Short.parseShort(s, radix))
     * </code></blockquote>
     *
     * @param s     строка для разбора
     * @param radix the radix to be used in interpreting <code>s</code>
     * @return      объект <code>Short</code>, хранящий значение,
     *              представленное аргументом-строкой
     *              в указанной системе счисления
     * @exception   NumberFormatException Если строка не содержит
     *              распознаваемое значение <code>short</code>.
     */
    public static Short valueOf(String s, int radix)
        throws NumberFormatException {
        return new Short(parseShort(s, radix));
    }

    /**
     * Возвращает объект <code>Short</code>, хранящий значение, 
     * представленное указанной строкой. Аргумент интерпретируется как
     * представление знакового десятичного <code>short</code>-а,
     * точно так же, как если аргумент был передан в метод {@link
     * #parseShort(java.lang.String)}. Результатом является объект 
     * <code>Short</code>, представляющий значение <code>short</code>
     * представленное строкой. 
     * <p> Другими словами, этот метод возвращает 
     * объект <code>Short</code>, тождественный значению выражения:
     *
     * <blockquote><code>
     * new Short(Short.parseShort(s))
     * </code></blockquote>
     *
     * @param s     строка для разбора
     * @return      объект <code>Short</code>, хранящий значение,
     *              представляемое строковым аргументом.
     * @exception   NumberFormatException Если строка не содержит
     *              распознаваемое значение <code>short</code>.
     */
    public static Short valueOf(String s) throws NumberFormatException {
        return valueOf(s, 10);
    }

    private static class ShortCache {
        private ShortCache(){}

        static final Short cache[] = new Short[-(-128) + 127 + 1];

        static {
            for(int i = 0; i < cache.length; i++)
            cache[i] = new Short((short)(i - 128));
        }
    }

    /**
     * Возвращает инстанцию <tt>Short</tt>, представляющую указанное значение
     * <tt>short</tt>.
     * Если новая инстанция <tt>Short</tt> не требуется, обычно должен 
     * использоваться этот метод вместо конструктора
     * {@link #Short(short)}, так как этот метод, вероятно, использует 
     * существенно меньше места и лучше по производительности, благодаря 
     * кешированию часто требуемых значений.
     *
     * @param  s значение <tt>short</tt>.
     * @return инстанция <tt>Short</tt>, представляющая <tt>s</tt>.
     * @since  1.5
     */
    public static Short valueOf(short s) {
        final int offset = 128;
        int sAsInt = s;
        if (sAsInt >= -128 && sAsInt <= 127) { // must cache 
            return ShortCache.cache[sAsInt + offset];
        }
        return new Short(s);
    }

    /**
     * Декодирует строку в объект <code>Short</code>.
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
     * ведущий ноль), разбирается так же, как методом <code>Short.parseShort</code>
     * с указанием основания системы счисления (10, 16, или 8). Эта последовательность 
     * символов должна представлять положительное значение или будет выкинуто
     * исключение {@link NumberFormatException}. Результат отрицателен, если
     * первый символ в указанной строке является знаком минус.
     * Пробельные символы в строке не допускаются.
     * 
     *
     * @param     nm строка для декодирования.
     * @return    объект <code>Short</code>, хранящий значение <code>short</code>,
     *            представляемое <code>nm</code>
     * @exception NumberFormatException, если строка не содержит
     *            распозноваемого значения <code>short</code>.
     * @see java.lang.Short#parseShort(java.lang.String, int)
     */
    public static Short decode(String nm) throws NumberFormatException {
        int radix = 10;
        int index = 0;
        boolean negative = false;
        Short result;

        // Обрабатываем знак минус, если есть
        if (nm.startsWith("-")) {
            negative = true;
            index++;
        }

        // Обрабатываем основание системы счисления, если есть
        if (nm.startsWith("0x", index) || nm.startsWith("0X", index)) {
            index += 2;
            radix = 16;
        }
        else if (nm.startsWith("#", index)) {
            index ++;
            radix = 16;
        }
        else if (nm.startsWith("0", index) && nm.length() > 1 + index) {
            index ++;
            radix = 8;
        }

        if (nm.startsWith("-", index))
            throw new NumberFormatException("Negative sign in wrong position");

        try {
            result = Short.valueOf(nm.substring(index), radix);
            result = negative ? new Short((short)-result.shortValue()) :result;
        } catch (NumberFormatException e) {
            // Если число равно Short.MIN_VALUE, мы попадем сюда. Следующая
            // строка обрабатывает этот случай, и при любой настоящей ошибке
            // формата повторно кидает исключение.
            String constant = negative ? new String("-" + nm.substring(index))
                                       : nm.substring(index);
            result = Short.valueOf(constant, radix);
        }
        return result;
    }

    /**
     * Значение <code>Short</code>.
     *
     * @serial
     */
    private final short value;

    /**
     * Конструирует и размещает в памяти новый объект <code>Short</code>,
     * представляющий указанное значение <code>short</code>'а.
     *
     * @param value значение, которое будет представлено
     *              <code>Short</code>'ом.
     */
    public Short(short value) {
        this.value = value;
    }

    /**
     * Создает в динамической памяти (а не берет из кеша) объект <code>Short</code>, 
     * представляющий значение <code>short</code>, представленного в виде строкового
     * параметра. Строка преобразуется в значение <code>short</code> в 
     * точности так, как оисано в методе <code>parseShort</code>, принимая
     * за основание системы счисления число 10.
     *
     * @param s     строка, конвертируемая в 
     *              <code>Short</code>
     * @exception   NumberFormatException Если строка не содержит
     *              распознаваемое значение <code>short</code>.
     * @see         java.lang.Short#parseShort(java.lang.String, int)
     */
    public Short(String s) throws NumberFormatException {
        this.value = parseShort(s, 10);
    }

    /**
     * Возвращает значение этого объекта <code>Short</code> как
     * <code>byte</code>.
     */
    public byte byteValue() {
        return (byte)value;
    }

    /**
     * Возвращает значение этого объекта <code>Short</code> как
     * <code>short</code>.
     */
    public short shortValue() {
        return value;
    }

    /**
     * Возвращает значение этого объекта <code>Short</code> как
     * <code>int</code>.
     */
    public int intValue() {
        return (int)value;
    }

    /**
     * Возвращает значение этого объекта <code>Short</code> как
     * <code>long</code>.
     */
    public long longValue() {
        return (long)value;
    }

    /**
     * Возвращает значение этого объекта <code>Short</code> как
     * <code>float</code>.
     */
    public float floatValue() {
        return (float)value;
    }

    /**
     * Возвращает значение этого объекта <code>Short</code> как
     * <code>double</code>.
     */
    public double doubleValue() {
        return (double)value;
    }

    /**
     * Возвращает объект <code>String</code>, представляющий это значение 
     * <code>Short</code>'а. Значение преобразуется в знаковое десятичное
     * представление и возвращается как строка, точно так же, как если бы
     * значение <code>short</code> было передано в метод 
     * {@link java.lang.Short#toString(short)}.
     *
     * @return  строковое представление значения этого объекта по 
     *          основанию&nbsp;10.
     */
    public String toString() {
        return String.valueOf((int)value);
    }

    /**
     * Возвращает хеш-код для этого объекта <code>Short</code>.
     */
    public int hashCode() {
        return (int)value;
    }

    /**
     * Сравнивает этот объект с указанным объектом. Результат равен
     * <code>true</code> тогда и только тогда, когда аргумент не
     * <code>null</code> и объект <code>Short</code> содержит
     * такое же <code>short</code> значение, как и этот объект.
     *
     * @param obj   объект для сравнения.
     * @return      <code>true</code> если объекты одинаковые;
     *              иначе <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Short) {
            return value == ((Short)obj).shortValue();
        }
        return false;
    }

    /**
     * Сравнивает два объекта <code>Short</code> в числовом смысле.
     *
     * @param   anotherShort   <code>Short</code> для сравнения.
     * @return  значение <code>0</code>, если этот объект <code>Short</code> 
     *          равен аргументу <code>Short</code>; значение, меньшее 
     *          <code>0</code>, если объект <code>Short</code> в числовом смысле 
     *          меньше, чем аргумент <code>Short</code>; и значение большее
     *          <code>0</code>, если этот объект <code>Short</code> в числовом
     *          смысле больше, чем аргумент <code>Short</code> (знаковое 
     *          сравнение).
     * @since   1.2
     */
    public int compareTo(Short anotherShort) {
        return this.value - anotherShort.value;
    }

    /**
     * Число бит, используемых для представления значения <tt>short</tt> 
     * в дополнительном коде.
     * @since 1.5
     */
    public static final int SIZE = 16;
 
    /**
     * Возвращает значение, получаемое обращением порядка байт
     * дополнительного кода представлении указанного значения <tt>short</tt>.
     *
     * @return значение, получаемое обращением (или, эквивалентно, обменом)
     *         байт в указанном значении <tt>short</tt>.
     * @since 1.5
     */
    public static short reverseBytes(short i) {
        return (short) (((i & 0xFF00) >> 8) | (i << 8));
    }

    /** используется serialVersionUID из JDK 1.0.2 для функциональной совместимости */
    private static final long serialVersionUID = 7515723908773894738L;
}
