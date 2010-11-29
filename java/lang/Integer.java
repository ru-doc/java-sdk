/*
 * @(#)Integer.java	1.94 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.util.Properties;

/**
 * Класс <code>Integer</code> обертывает значение примитивного типа
 * <code>int</code> в объект. Объект типа
 * <code>Integer</code> содержит одно поле, имеющее тип
 * <code>int</code>. 
 *
 *  <p>
 * 
 * В дополнение, класс предоставляет несколько методов для преобразования
 * значений <code>int</code> в строки и строк в <code>int</code>, 
 * равно как и другие константы и методы, полезные, когда имеешь 
 * дело с <code>int</code>. 
 *
 * <p>Implementation note: The implementations of the "bit twiddling"
 * methods (такие как {@link #highestOneBit(int) highestOneBit} и
 * {@link #numberOfTrailingZeros(int) numberOfTrailingZeros}) 
 * основано на материалах из Henry S. Warren, Jr.'s <i>Hacker's
 * Delight</i>, (Addison Wesley, 2002).
 *
 * @author  Lee Boynton
 * @author  Arthur van Hoff
 * @author  Josh Bloch
 * @version 1.94, 03/23/10
 * @since JDK1.0
 */
public final class Integer extends Number implements Comparable<Integer> {
    /**
     * Константа, хранящая минимальное значение для типа <code>int</code>,
     * -2<sup>31</sup>.
     */
    public static final int   MIN_VALUE = 0x80000000;

    /**
     * Константа, хранящая максимальное значение для типа <code>int</code>,
     * 2<sup>31</sup>-1.
     */
    public static final int   MAX_VALUE = 0x7fffffff;

    /**
     * Инстанция <code>Class</code>, представляющая примитивный тип 
     * <code>int</code>.
     *
     * @since   JDK1.1
     */
    public static final Class<Integer>  TYPE = (Class<Integer>) Class.getPrimitiveClass("int");

    /**
     * Все возможные символы для представления числа как строки
     */
    final static char[] digits = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'a' , 'b' ,
        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    /**
     * Returns a string representation of the first argument in the
     * radix specified by the second argument.
     * <p>
     * If the radix is smaller than <code>Character.MIN_RADIX</code>
     * or larger than <code>Character.MAX_RADIX</code>, then the radix
     * <code>10</code> is used instead.
     * <p>
     * If the first argument is negative, the first element of the
     * result is the ASCII minus character <code>'-'</code>
     * (<code>'&#92;u002D'</code>). If the first argument is not
     * negative, no sign character appears in the result.
     * <p>
     * The remaining characters of the result represent the magnitude
     * of the first argument. If the magnitude is zero, it is
     * represented by a single zero character <code>'0'</code>
     * (<code>'&#92;u0030'</code>); otherwise, the first character of
     * the representation of the magnitude will not be the zero
     * character.  The following ASCII characters are used as digits: 
     * <blockquote><pre>
     *   0123456789abcdefghijklmnopqrstuvwxyz
     * </pre></blockquote>
     * These are <code>'&#92;u0030'</code> through
     * <code>'&#92;u0039'</code> and <code>'&#92;u0061'</code> through
     * <code>'&#92;u007A'</code>. If <code>radix</code> is
     * <var>N</var>, then the first <var>N</var> of these characters
     * are used as radix-<var>N</var> digits in the order shown. Thus,
     * the digits for hexadecimal (radix 16) are
     * <code>0123456789abcdef</code>. If uppercase letters are
     * desired, the {@link java.lang.String#toUpperCase()} method may
     * be called on the result:
     * <blockquote><pre>
     * Integer.toString(n, 16).toUpperCase()
     * </pre></blockquote>
     *
     * @param   i       an integer to be converted to a string.
     * @param   radix   the radix to use in the string representation.
     * @return  a string representation of the argument in the specified radix.
     * @see     java.lang.Character#MAX_RADIX
     * @see     java.lang.Character#MIN_RADIX
     */
    public static String toString(int i, int radix) {

        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
            radix = 10;

        /* Используем более быструю версию */
        if (radix == 10) {
            return toString(i);
        }

        char buf[] = new char[33];
        boolean negative = (i < 0);
        int charPos = 32;

        if (!negative) {
            i = -i;
        }

        while (i <= -radix) {
            buf[charPos--] = digits[-(i % radix)];
            i = i / radix;
        }
        buf[charPos] = digits[-i];

        if (negative) {
            buf[--charPos] = '-';
        }

        return new String(buf, charPos, (33 - charPos));
    }

    /**
     * Returns a string representation of the integer argument as an
     * unsigned integer in base&nbsp;16.
     * <p>
     * The unsigned integer value is the argument plus 2<sup>32</sup>
     * if the argument is negative; otherwise, it is equal to the
     * argument.  This value is converted to a string of ASCII digits
     * in hexadecimal (base&nbsp;16) with no extra leading
     * <code>0</code>s. If the unsigned magnitude is zero, it is
     * represented by a single zero character <code>'0'</code>
     * (<code>'&#92;u0030'</code>); otherwise, the first character of
     * the representation of the unsigned magnitude will not be the
     * zero character. The following characters are used as
     * hexadecimal digits:
     * <blockquote><pre>
     * 0123456789abcdef
     * </pre></blockquote>
     * These are the characters <code>'&#92;u0030'</code> through
     * <code>'&#92;u0039'</code> and <code>'&#92;u0061'</code> through
     * <code>'&#92;u0066'</code>. If uppercase letters are
     * desired, the {@link java.lang.String#toUpperCase()} method may
     * be called on the result:
     * <blockquote><pre>
     * Integer.toHexString(n).toUpperCase()
     * </pre></blockquote>
     *
     * @param   i   an integer to be converted to a string.
     * @return  the string representation of the unsigned integer value
     *          represented by the argument in hexadecimal (base&nbsp;16).
     * @since   JDK1.0.2
     */
    public static String toHexString(int i) {
        return toUnsignedString(i, 4);
    }

    /**
     * Returns a string representation of the integer argument as an
     * unsigned integer in base&nbsp;8.
     * <p>
     * The unsigned integer value is the argument plus 2<sup>32</sup>
     * if the argument is negative; otherwise, it is equal to the
     * argument.  This value is converted to a string of ASCII digits
     * in octal (base&nbsp;8) with no extra leading <code>0</code>s.
     * <p>
     * If the unsigned magnitude is zero, it is represented by a
     * single zero character <code>'0'</code>
     * (<code>'&#92;u0030'</code>); otherwise, the first character of
     * the representation of the unsigned magnitude will not be the
     * zero character. The following characters are used as octal
     * digits:
     * <blockquote><pre>
     * 01234567
     * </pre></blockquote>
     * These are the characters <code>'&#92;u0030'</code> through
     * <code>'&#92;u0037'</code>.
     *
     * @param   i   an integer to be converted to a string.
     * @return  the string representation of the unsigned integer value
     *          represented by the argument in octal (base&nbsp;8).
     * @since   JDK1.0.2
     */
    public static String toOctalString(int i) {
        return toUnsignedString(i, 3);
    }

    /**
     * Returns a string representation of the integer argument as an
     * unsigned integer in base&nbsp;2.
     * <p>
     * The unsigned integer value is the argument plus 2<sup>32</sup>
     * if the argument is negative; otherwise it is equal to the
     * argument.  This value is converted to a string of ASCII digits
     * in binary (base&nbsp;2) with no extra leading <code>0</code>s.
     * If the unsigned magnitude is zero, it is represented by a
     * single zero character <code>'0'</code>
     * (<code>'&#92;u0030'</code>); otherwise, the first character of
     * the representation of the unsigned magnitude will not be the
     * zero character. The characters <code>'0'</code>
     * (<code>'&#92;u0030'</code>) and <code>'1'</code>
     * (<code>'&#92;u0031'</code>) are used as binary digits.
     *
     * @param   i   an integer to be converted to a string.
     * @return  the string representation of the unsigned integer value
     *          represented by the argument in binary (base&nbsp;2).
     * @since   JDK1.0.2
     */
    public static String toBinaryString(int i) {
        return toUnsignedString(i, 1);
    }

    /**
     * Конвертирует integer в беззнаковое число.
     */
    private static String toUnsignedString(int i, int shift) {
        char[] buf = new char[32];
        int charPos = 32;
        int radix = 1 << shift;
        int mask = radix - 1;
        do {
            buf[--charPos] = digits[i & mask];
            i >>>= shift;
        } while (i != 0);

        return new String(buf, charPos, (32 - charPos));
    }


    final static char [] DigitTens = {
        '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
        '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
        '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
        '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
        '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
        '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
        '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
        '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
        '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
    } ; 

    final static char [] DigitOnes = { 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    } ;

    // I use the "invariant division by multiplication" trick to
    // accelerate Integer.toString.  In particular we want to
    // avoid division by 10.
    //
    // The "trick" has roughly the same performance characteristics
    // as the "classic" Integer.toString code on a non-JIT VM.
    // The trick avoids .rem and .div calls but has a longer code
    // path and is thus dominated by dispatch overhead.  In the
    // JIT case the dispatch overhead doesn't exist and the
    // "trick" is considerably faster than the classic code.
    //
    // TODO-FIXME: convert (x * 52429) into the equiv shift-add
    // sequence.
    //
    // RE:  Division by Invariant Integers using Multiplication
    //      T Gralund, P Montgomery
    //      ACM PLDI 1994
    //

    /**
     * Returns a <code>String</code> object representing the
     * specified integer. The argument is converted to signed decimal
     * representation and returned as a string, exactly as if the
     * argument and radix 10 were given as arguments to the {@link
     * #toString(int, int)} method.
     *
     * @param   i   an integer to be converted.
     * @return  a string representation of the argument in base&nbsp;10.
     */
    public static String toString(int i) {
        if (i == Integer.MIN_VALUE)
            return "-2147483648";
        int size = (i < 0) ? stringSize(-i) + 1 : stringSize(i);
        char[] buf = new char[size];
        getChars(i, size, buf);
        return new String(0, size, buf);
    }

    /**
     * Places characters representing the integer i into the
     * character array buf. The characters are placed into
     * the buffer backwards starting with the least significant
     * digit at the specified index (exclusive), and working
     * backwards from there.
     *
     * Потерпит неудачу, если i == Integer.MIN_VALUE
     */
    static void getChars(int i, int index, char[] buf) {
        int q, r;
        int charPos = index;
        char sign = 0;

        if (i < 0) { 
            sign = '-';
            i = -i;
        }

        // Generate two digits per iteration
        while (i >= 65536) {
            q = i / 100;
        // really: r = i - (q * 100);
            r = i - ((q << 6) + (q << 5) + (q << 2));
            i = q;
            buf [--charPos] = DigitOnes[r];
            buf [--charPos] = DigitTens[r];
        }

        // Fall thru to fast mode for smaller numbers
        // assert(i <= 65536, i);
        for (;;) { 
            q = (i * 52429) >>> (16+3);
            r = i - ((q << 3) + (q << 1));  // r = i-(q*10) ...
            buf [--charPos] = digits [r];
            i = q;
            if (i == 0) break;
        }
        if (sign != 0) {
            buf [--charPos] = sign;
        }
    }

    final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
                                      99999999, 999999999, Integer.MAX_VALUE };

    /** Требует положительного x */
    static int stringSize(int x) {
        for (int i=0; ; i++)
            if (x <= sizeTable[i])
                return i+1;
    }
    
    /**
     * Parses the string argument as a signed integer in the radix 
     * specified by the second argument. The characters in the string 
     * must all be digits of the specified radix (as determined by 
     * whether {@link java.lang.Character#digit(char, int)} returns a 
     * nonnegative value), except that the first character may be an 
     * ASCII minus sign <code>'-'</code> (<code>'&#92;u002D'</code>) to 
     * indicate a negative value. The resulting integer value is returned. 
     * <p>
     * An exception of type <code>NumberFormatException</code> is
     * thrown if any of the following situations occurs:
     * <ul>
     * <li>The first argument is <code>null</code> or is a string of
     * length zero.
     * <li>The radix is either smaller than 
     * {@link java.lang.Character#MIN_RADIX} or
     * larger than {@link java.lang.Character#MAX_RADIX}. 
     * <li>Any character of the string is not a digit of the specified
     * radix, except that the first character may be a minus sign
     * <code>'-'</code> (<code>'&#92;u002D'</code>) provided that the
     * string is longer than length 1.
     * <li>The value represented by the string is not a value of type
     * <code>int</code>. 
     * </ul><p>
     * Примеры:
     * <blockquote><pre>
     * parseInt("0", 10) вернет 0
     * parseInt("473", 10) вернет 473
     * parseInt("-0", 10) вернет 0
     * parseInt("-FF", 16) вернет -255
     * parseInt("1100110", 2) вернет 102
     * parseInt("2147483647", 10) вернет 2147483647
     * parseInt("-2147483648", 10) вернет -2147483648
     * parseInt("2147483648", 10) выкинет NumberFormatException
     * parseInt("99", 8) выкинет NumberFormatException
     * parseInt("Kona", 10) выкинет NumberFormatException
     * parseInt("Kona", 27) вернет 411787
     * </pre></blockquote>
     *
     * @param      s    the <code>String</code> containing the integer 
     *                  representation to be parsed
     * @param      radix   the radix to be used while parsing <code>s</code>.
     * @return     the integer represented by the string argument in the
     *             specified radix.
     * @exception  NumberFormatException if the <code>String</code>
     *             does not contain a parsable <code>int</code>.
     */
    public static int parseInt(String s, int radix)
        throws NumberFormatException
    {
        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                            " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                            " greater than Character.MAX_RADIX");
        }

        int result = 0;
        boolean negative = false;
        int i = 0, max = s.length();
        int limit;
        int multmin;
        int digit;

        if (max > 0) {
            if (s.charAt(0) == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
                i++;
            } else {
                limit = -Integer.MAX_VALUE;
            }
            multmin = limit / radix;
            if (i < max) {
                digit = Character.digit(s.charAt(i++),radix);
                if (digit < 0) {
                    throw NumberFormatException.forInputString(s);
                } else {
                    result = -digit;
                }
            }
            while (i < max) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++),radix);
                if (digit < 0) {
                    throw NumberFormatException.forInputString(s);
                }
                if (result < multmin) {
                    throw NumberFormatException.forInputString(s);
                }
                result *= radix;
                if (result < limit + digit) {
                    throw NumberFormatException.forInputString(s);
                }
                result -= digit;
            }
        } else {
            throw NumberFormatException.forInputString(s);
        }
        if (negative) {
            if (i > 1) {
                return result;
            } else {    /* Only got "-" */
                throw NumberFormatException.forInputString(s);
            }
        } else {
            return -result;
        }
    }

    /**
     * Parses the string argument as a signed decimal integer. The 
     * characters in the string must all be decimal digits, except that 
     * the first character may be an ASCII minus sign <code>'-'</code> 
     * (<code>'&#92;u002D'</code>) to indicate a negative value. The resulting 
     * integer value is returned, exactly as if the argument and the radix 
     * 10 were given as arguments to the 
     * {@link #parseInt(java.lang.String, int)} method.
     *
     * @param s	   a <code>String</code> containing the <code>int</code>
     *             representation to be parsed
     * @return     the integer value represented by the argument in decimal.
     * @exception  NumberFormatException  if the string does not contain a
     *               parsable integer.
     */
    public static int parseInt(String s) throws NumberFormatException {
        return parseInt(s,10);
    }

    /**
     * Returns an <code>Integer</code> object holding the value
     * extracted from the specified <code>String</code> when parsed
     * with the radix given by the second argument. The first argument
     * is interpreted as representing a signed integer in the radix
     * specified by the second argument, exactly as if the arguments
     * were given to the {@link #parseInt(java.lang.String, int)}
     * method. The result is an <code>Integer</code> object that
     * represents the integer value specified by the string.
     * <p>
     * In other words, this method returns an <code>Integer</code>
     * object equal to the value of:
     *
     * <blockquote><code>
     * new Integer(Integer.parseInt(s, radix))  
     * </code></blockquote>
     *
     * @param      s   the string to be parsed.
     * @param      radix the radix to be used in interpreting <code>s</code>
     * @return     an <code>Integer</code> object holding the value
     *             represented by the string argument in the specified
     *             radix.
     * @exception NumberFormatException if the <code>String</code>
     *            does not contain a parsable <code>int</code>.
     */
    public static Integer valueOf(String s, int radix) throws NumberFormatException {
        return Integer.valueOf(parseInt(s,radix));
    }

    /**
     * Returns an <code>Integer</code> object holding the
     * value of the specified <code>String</code>. The argument is
     * interpreted as representing a signed decimal integer, exactly
     * as if the argument were given to the {@link
     * #parseInt(java.lang.String)} method. The result is an
     * <code>Integer</code> object that represents the integer value
     * specified by the string.
     * <p>
     * In other words, this method returns an <code>Integer</code>
     * object equal to the value of:
     *
     * <blockquote><code>
     * new Integer(Integer.parseInt(s))
     * </code></blockquote>
     *
     * @param      s   the string to be parsed.
     * @return     an <code>Integer</code> object holding the value
     *             represented by the string argument.
     * @exception  NumberFormatException  if the string cannot be parsed 
     *             as an integer.
     */
    public static Integer valueOf(String s) throws NumberFormatException {
        return Integer.valueOf(parseInt(s, 10));
    }

    /**
     * Cache to support the object identity semantics of autoboxing for values between 
     * -128 and 127 (inclusive) as required by JLS.
     *
     * The cache is initialized on first usage. During VM initialization the
     * getAndRemoveCacheProperties method may be used to get and remove any system
     * properites that configure the cache size. At this time, the size of the
     * cache may be controlled by the vm option -XX:AutoBoxCacheMax=<size>.
     */

    // значение свойства java.lang.Integer.IntegerCache.high (obtained during VM init)
    private static String integerCacheHighPropValue;

    static void getAndRemoveCacheProperties() {
        if (!sun.misc.VM.isBooted()) {
            Properties props = System.getProperties();
            integerCacheHighPropValue =
                (String)props.remove("java.lang.Integer.IntegerCache.high");
            if (integerCacheHighPropValue != null)
                System.setProperties(props);  // удаляем из системынх свойств
        }
    }

    private static class IntegerCache {
        static final int high;
        static final Integer cache[];

        static {
            final int low = -128;

            // верхнее значение может быть настроено через свойство
            int h = 127;
            if (integerCacheHighPropValue != null) {
                // Use Long.decode here to avoid invoking methods that
                // require Integer's autoboxing cache to be initialized
                int i = Long.decode(integerCacheHighPropValue).intValue();
                i = Math.max(i, 127);
                // Максимальный размер массива равен Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - -low);
            }
            high = h;

            cache = new Integer[(high - low) + 1];
            int j = low;
            for(int k = 0; k < cache.length; k++)
                cache[k] = new Integer(j++);
        }

        private IntegerCache() {}
    }

    /**
     * Возвращает инстанцию <tt>Integer</tt>, представляющую указанное 
     * значение <tt>int</tt>.
     * Если новая инстанция <tt>Integer</tt> не требуется, этот метод
     * должен обычно использоваться вместо конструктора
     * {@link #Integer(int)}, так как этот метод, вероятно, использует 
     * существенно меньше места и лучше по производительности, из-за 
     * кеширования часто требуемых значений.
     *
     * @param  i значение <code>int</code>.
     * @return инстанция <tt>Integer</tt>, представляющая <tt>i</tt>.
     * @since  1.5
     */
    public static Integer valueOf(int i) {
        if(i >= -128 && i <= IntegerCache.high)
            return IntegerCache.cache[i + 128];
        else
            return new Integer(i);
    }

    /**
     * Значение <code>Integer</code>.
     *
     * @serial
     */
    private final int value;

    /**
     * Конструирует и размещает в памяти новый объект <code>Integer</code>,
     * представляющий указанное значение <code>int</code>'а.
     *
     * @param value значение, которое будет представлено
     *              <code>Integer</code>'ом.
     */
    public Integer(int value) {
        this.value = value;
    }

    /**
     * Constructs a newly allocated <code>Integer</code> object that
     * represents the <code>int</code> value indicated by the
     * <code>String</code> parameter. The string is converted to an
     * <code>int</code> value in exactly the manner used by the
     * <code>parseInt</code> method for radix 10.
     *
     * @param      s   строка, конвертируемая в 
     *                 <code>Integer</code>.
     * @exception   NumberFormatException Если строка не содержит
     *              распознаваемое значение <code>int</code>.
     * @see         java.lang.Integer#parseInt(java.lang.String, int)
     */
    public Integer(String s) throws NumberFormatException {
        this.value = parseInt(s, 10);
    }

    /**
     * Возвращает значение этого объекта <code>Integer</code> как
     * <code>byte</code>.
     */
    public byte byteValue() {
        return (byte)value;
    }

    /**
     * Возвращает значение этого объекта <code>Integer</code> как
     * <code>short</code>.
     */
    public short shortValue() {
        return (short)value;
    }

    /**
     * Возвращает значение этого объекта <code>Integer</code> как
     * <code>int</code>.
     */
    public int intValue() {
        return value;
    }

    /**
     * Возвращает значение этого объекта <code>Integer</code> как
     * <code>long</code>.
     */
    public long longValue() {
        return (long)value;
    }

    /**
     * Возвращает значение этого объекта <code>Integer</code> как
     * <code>float</code>.
     */
    public float floatValue() {
        return (float)value;
    }

    /**
     * Возвращает значение этого объекта <code>Integer</code> как
     * <code>double</code>.
     */
    public double doubleValue() {
        return (double)value;
    }

    /**
     * Возвращает объект <code>String</code>, представляющий это значение 
     * <code>Integer</code>'а. Значение преобразуется в знаковое десятичное
     * представление и возвращается как строка, точно так же, как если бы
     * значение <code>int</code> было передано в метод 
     * {@link java.lang.Integer#toString(int)}.
     *
     * @return  строковое представление значения этого объекта по 
     *          основанию&nbsp;10.
     */
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Возвращает хеш-код для этого объекта <code>Integer</code>.
     *
     * @return  значение хеш-кода для этого объекта равно
     *          примитивному значению <code>int</code>, представляемому этим 
     *          объектом <code>Integer</code>. 
     */
    public int hashCode() {
        return value;
    }

    /**
     * Сравнивает этот объект с указанным объектом. Результат равен
     * <code>true</code> тогда и только тогда, когда аргумент не
     * <code>null</code> и объект <code>Integer</code> содержит
     * такое же <code>int</code> значение, как и этот объект.
     *
     * @param obj   объект для сравнения.
     * @return      <code>true</code> если объекты одинаковые;
     *              иначе <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Integer) {
            return value == ((Integer)obj).intValue();
        }
        return false;
    }

    /**
     * Determines the integer value of the system property with the
     * specified name.
     * <p>
     * The first argument is treated as the name of a system property. 
     * System properties are accessible through the 
     * {@link java.lang.System#getProperty(java.lang.String)} method. The 
     * string value of this property is then interpreted as an integer 
     * value and an <code>Integer</code> object representing this value is 
     * returned. Details of possible numeric formats can be found with 
     * the definition of <code>getProperty</code>. 
     * <p>
     * If there is no property with the specified name, if the specified name
     * is empty or <code>null</code>, or if the property does not have 
     * the correct numeric format, then <code>null</code> is returned.
     * <p>
     * In other words, this method returns an <code>Integer</code>
     * object equal to the value of:
     *
     * <blockquote><code>
     * getInteger(nm, null)
     * </code></blockquote>
     *
     * @param   nm   property name.
     * @return  the <code>Integer</code> value of the property.
     * @see     java.lang.System#getProperty(java.lang.String)
     * @see     java.lang.System#getProperty(java.lang.String, java.lang.String)
     */
    public static Integer getInteger(String nm) {
        return getInteger(nm, null);
    }

    /**
     * Determines the integer value of the system property with the
     * specified name.
     * <p>
     * The first argument is treated as the name of a system property.
     * System properties are accessible through the {@link
     * java.lang.System#getProperty(java.lang.String)} method. The 
     * string value of this property is then interpreted as an integer 
     * value and an <code>Integer</code> object representing this value is 
     * returned. Details of possible numeric formats can be found with 
     * the definition of <code>getProperty</code>. 
     * <p>
     * The second argument is the default value. An <code>Integer</code> object
     * that represents the value of the second argument is returned if there
     * is no property of the specified name, if the property does not have
     * the correct numeric format, or if the specified name is empty or
     *  <code>null</code>.
     * <p>
     * In other words, this method returns an <code>Integer</code> object 
     * equal to the value of:
     * <blockquote><code>
     * getInteger(nm, new Integer(val))
     * </code></blockquote>
     * but in practice it may be implemented in a manner such as: 
     * <blockquote><pre>
     * Integer result = getInteger(nm, null);
     * return (result == null) ? new Integer(val) : result;
     * </pre></blockquote>
     * to avoid the unnecessary allocation of an <code>Integer</code> 
     * object when the default value is not needed. 
     *
     * @param   nm   property name.
     * @param   val   default value.
     * @return  the <code>Integer</code> value of the property.
     * @see     java.lang.System#getProperty(java.lang.String)
     * @see     java.lang.System#getProperty(java.lang.String, java.lang.String)
     */
    public static Integer getInteger(String nm, int val) {
        Integer result = getInteger(nm, null);
        return (result == null) ? Integer.valueOf(val) : result;
    }

    /**
     * Returns the integer value of the system property with the
     * specified name.  The first argument is treated as the name of a
     * system property.  System properties are accessible through the
     * {@link java.lang.System#getProperty(java.lang.String)} method.
     * The string value of this property is then interpreted as an
     * integer value, as per the <code>Integer.decode</code> method,
     * and an <code>Integer</code> object representing this value is
     * returned.
     * <p>
     * <ul><li>If the property value begins with the two ASCII characters 
     *         <code>0x</code> or the ASCII character <code>#</code>, not 
     *      followed by a minus sign, then the rest of it is parsed as a 
     *      hexadecimal integer exactly as by the method 
     *      {@link #valueOf(java.lang.String, int)} with radix 16. 
     * <li>If the property value begins with the ASCII character 
     *     <code>0</code> followed by another character, it is parsed as an 
     *     octal integer exactly as by the method 
     *     {@link #valueOf(java.lang.String, int)} with radix 8. 
     * <li>Otherwise, the property value is parsed as a decimal integer 
     * exactly as by the method {@link #valueOf(java.lang.String, int)} 
     * with radix 10. 
     * </ul><p>
     * The second argument is the default value. The default value is
     * returned if there is no property of the specified name, if the
     * property does not have the correct numeric format, or if the
     * specified name is empty or <code>null</code>.
     *
     * @param   nm   property name.
     * @param   val   default value.
     * @return  the <code>Integer</code> value of the property.
     * @see     java.lang.System#getProperty(java.lang.String)
     * @see java.lang.System#getProperty(java.lang.String, java.lang.String)
     * @see java.lang.Integer#decode
     */
    public static Integer getInteger(String nm, Integer val) {
        String v = null;
        try {
            v = System.getProperty(nm);
        } catch (IllegalArgumentException e) {
        } catch (NullPointerException e) {
        }
        if (v != null) {
            try {
                return Integer.decode(v);
            } catch (NumberFormatException e) {
            }
        }
        return val;
    }

    /**
     * Декодирует строку в объект <code>Integer</code>.
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
     * ведущий ноль), разбирается так же, как методом <code>Integer.parseInt</code>
     * с указанием основания системы счисления (10, 16, или 8). Эта последовательность 
     * символов должна представлять положительное значение или будет выкинуто
     * исключение {@link NumberFormatException}. Результат отрицателен, если
     * первый символ в указанной строке является знаком минус.
     * Пробельные символы в строке не допускаются.
     * 
     *
     * @param     nm строка для декодирования.
     * @return    объект <code>Integer</code>, хранящий значение <code>int</code>,
     *            представляемое <code>nm</code>
     * @exception NumberFormatException, если строка не содержит
     *            распозноваемого значения <code>int</code>.
     * @see java.lang.Integer#parseInt(java.lang.String, int)
     */
    public static Integer decode(String nm) throws NumberFormatException {
        int radix = 10;
        int index = 0;
        boolean negative = false;
        Integer result;

        // Обрабатываем знас минус, если есть
        if (nm.startsWith("-")) {
            negative = true;
            index++;
        }

        // Обрабатываем спецификатор основания системы счисления, если есть
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
            result = Integer.valueOf(nm.substring(index), radix);
            result = negative ? Integer.valueOf(-result.intValue()) : result;
        } catch (NumberFormatException e) {
            // Если число равно Integer.MIN_VALUE, мы попадем сюда. Следующая
            // строка обрабатывает этот случай, и при любой настоящей ошибке
            // формата повторно кидает исключение.
            String constant = negative ? "-" + nm.substring(index)
                                       : nm.substring(index);
            result = Integer.valueOf(constant, radix);
        }
        return result;
    }

    /**
     * Сравнивает два объекта <code>Integer</code> в числовом смысле.
     *
     * @param   anotherInteger   <code>Integer</code> для сравнения.
     * @return  значение <code>0</code>, если этот объект <code>Integer</code> 
     *          равен аргументу <code>Integer</code>; значение, меньшее 
     *          <code>0</code>, если объект <code>Integer</code> в числовом смысле 
     *          меньше, чем аргумент <code>Integer</code>; и значение большее
     *          <code>0</code>, если этот объект <code>Integer</code> в числовом
     *          смысле больше, чем аргумент <code>Integer</code> (знаковое 
     *          сравнение).
     * @since   1.2
     */
    public int compareTo(Integer anotherInteger) {
        int thisVal = this.value;
        int anotherVal = anotherInteger.value;
        return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
    }


    // Bit twiddling

    /**
     * Число бит, представляющих значение <tt>int</tt> 
     * in two's complement binary form.
     *
     * @since 1.5
     */
    public static final int SIZE = 32;
 
    /**
     * Returns an <tt>int</tt> value with at most a single one-bit, in the
     * position of the highest-order ("leftmost") one-bit in the specified
     * <tt>int</tt> value.  Returns zero if the specified value has no
     * one-bits in its two's complement binary representation, that is, if it
     * is equal to zero.
     *
     * @return an <tt>int</tt> value with a single one-bit, in the position
     *     of the highest-order one-bit in the specified value, or zero if
     *     the specified value is itself equal to zero.
     * @since 1.5
     */
    public static int highestOneBit(int i) {
        // HD, Figure 3-1
        i |= (i >>  1);
        i |= (i >>  2);
        i |= (i >>  4);
        i |= (i >>  8);
        i |= (i >> 16);
        return i - (i >>> 1);
    }
 
    /**
     * Returns an <tt>int</tt> value with at most a single one-bit, in the
     * position of the lowest-order ("rightmost") one-bit in the specified
     * <tt>int</tt> value.  Returns zero if the specified value has no
     * one-bits in its two's complement binary representation, that is, if it
     * is equal to zero.
     *
     * @return an <tt>int</tt> value with a single one-bit, in the position
     *     of the lowest-order one-bit in the specified value, or zero if
     *     the specified value is itself equal to zero.
     * @since 1.5
     */
    public static int lowestOneBit(int i) {
        // HD, Section 2-1
        return i & -i;
    }
 
    /**
     * Returns the number of zero bits preceding the highest-order
     * ("leftmost") one-bit in the two's complement binary representation
     * of the specified <tt>int</tt> value.  Returns 32 if the
     * specified value has no one-bits in its two's complement representation,
     * in other words if it is equal to zero.
     *
     * <p>Note that this method is closely related to the logarithm base 2.
     * For all positive <tt>int</tt> values x:
     * <ul>
     * <li>floor(log<sub>2</sub>(x)) = <tt>31 - numberOfLeadingZeros(x)</tt>
     * <li>ceil(log<sub>2</sub>(x)) = <tt>32 - numberOfLeadingZeros(x - 1)</tt>
     * </ul>
     *
     * @return the number of zero bits preceding the highest-order
     *     ("leftmost") one-bit in the two's complement binary representation
     *     of the specified <tt>int</tt> value, or 32 if the value
     *     is equal to zero.
     * @since 1.5
     */
    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        return n;
    }
 
    /**
     * Returns the number of zero bits following the lowest-order ("rightmost")
     * one-bit in the two's complement binary representation of the specified
     * <tt>int</tt> value.  Returns 32 if the specified value has no
     * one-bits in its two's complement representation, in other words if it is
     * equal to zero.
     *
     * @return the number of zero bits following the lowest-order ("rightmost")
     *     one-bit in the two's complement binary representation of the
     *     specified <tt>int</tt> value, or 32 if the value is equal
     *     to zero.
     * @since 1.5
     */
    public static int numberOfTrailingZeros(int i) {
        // HD, Figure 5-14
        int y;
        if (i == 0) return 32;
        int n = 31;
        y = i <<16; if (y != 0) { n = n -16; i = y; }
        y = i << 8; if (y != 0) { n = n - 8; i = y; }
        y = i << 4; if (y != 0) { n = n - 4; i = y; }
        y = i << 2; if (y != 0) { n = n - 2; i = y; }
        return n - ((i << 1) >>> 31);
    }
 
    /**
     * Returns the number of one-bits in the two's complement binary
     * representation of the specified <tt>int</tt> value.  This function is
     * sometimes referred to as the <i>population count</i>.
     *
     * @return the number of one-bits in the two's complement binary
     *     representation of the specified <tt>int</tt> value.
     * @since 1.5
     */
    public static int bitCount(int i) {
        // HD, Figure 5-2
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }
 
    /**
     * Returns the value obtained by rotating the two's complement binary
     * representation of the specified <tt>int</tt> value left by the
     * specified number of bits.  (Bits shifted out of the left hand, or
     * high-order, side reenter on the right, or low-order.)
     *
     * <p>Note that left rotation with a negative distance is equivalent to
     * right rotation: <tt>rotateLeft(val, -distance) == rotateRight(val,
     * distance)</tt>.  Note also that rotation by any multiple of 32 is a
     * no-op, so all but the last five bits of the rotation distance can be
     * ignored, even if the distance is negative: <tt>rotateLeft(val,
     * distance) == rotateLeft(val, distance & 0x1F)</tt>.
     *
     * @return the value obtained by rotating the two's complement binary
     *     representation of the specified <tt>int</tt> value left by the
     *     specified number of bits.
     * @since 1.5
     */
    public static int rotateLeft(int i, int distance) {
        return (i << distance) | (i >>> -distance);
    }

    /**
     * Returns the value obtained by rotating the two's complement binary
     * representation of the specified <tt>int</tt> value right by the
     * specified number of bits.  (Bits shifted out of the right hand, or
     * low-order, side reenter on the left, or high-order.)
     *
     * <p>Note that right rotation with a negative distance is equivalent to
     * left rotation: <tt>rotateRight(val, -distance) == rotateLeft(val,
     * distance)</tt>.  Note also that rotation by any multiple of 32 is a
     * no-op, so all but the last five bits of the rotation distance can be
     * ignored, even if the distance is negative: <tt>rotateRight(val,
     * distance) == rotateRight(val, distance & 0x1F)</tt>.
     *
     * @return the value obtained by rotating the two's complement binary
     *     representation of the specified <tt>int</tt> value right by the
     *     specified number of bits.
     * @since 1.5
     */
    public static int rotateRight(int i, int distance) {
        return (i >>> distance) | (i << -distance);
    }
 
    /**
     * Returns the value obtained by reversing the order of the bits in the
     * two's complement binary representation of the specified <tt>int</tt>
     * value.
     *
     * @return the value obtained by reversing order of the bits in the
     *     specified <tt>int</tt> value.
     * @since 1.5
     */
    public static int reverse(int i) {
        // HD, Figure 7-1
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i << 24) | ((i & 0xff00) << 8) |
            ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }
 
    /**
     * Returns the signum function of the specified <tt>int</tt> value.  (The
     * return value is -1 if the specified value is negative; 0 if the
     * specified value is zero; and 1 if the specified value is positive.)
     *
     * @return the signum function of the specified <tt>int</tt> value.
     * @since 1.5
     */
    public static int signum(int i) {
        // HD, Section 2-7
        return (i >> 31) | (-i >>> 31);
    }
 
    /**
     * Returns the value obtained by reversing the order of the bytes in the
     * two's complement representation of the specified <tt>int</tt> value.
     *
     * @return the value obtained by reversing the bytes in the specified
     *     <tt>int</tt> value.
     * @since 1.5
     */
    public static int reverseBytes(int i) {
        return ((i >>> 24)           ) |
               ((i >>   8) &   0xFF00) |
               ((i <<   8) & 0xFF0000) |
               ((i << 24));
    }

    /** используется serialVersionUID из JDK 1.0.2 для функциональной совместимости */
    private static final long serialVersionUID = 1360826667806852920L;
}
