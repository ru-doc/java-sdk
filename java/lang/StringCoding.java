/*
 * @(#)StringCoding.java	1.21	10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.io.CharConversionException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import sun.misc.MessageUtils;
import sun.nio.cs.HistoricallyNamedCharset;

/**
 * Вспомогательный класс для кодирования и декодирования строк.
 */

class StringCoding {

    private StringCoding() { }

    /** Закешированные кодеки для каждого потока
     */
    private static ThreadLocal decoder = new ThreadLocal();
    private static ThreadLocal encoder = new ThreadLocal();

    private static boolean warnUnsupportedCharset = true;

    private static Object deref(ThreadLocal tl) {
        SoftReference sr = (SoftReference)tl.get();
        if (sr == null)
            return null;
        return sr.get();
    }

    private static void set(ThreadLocal tl, Object ob) {
        tl.set(new SoftReference(ob));
    }

    /** Обрезает данный байтовый массив до указанной длины.
    */
    private static byte[] safeTrim(byte[] ba, int len, Charset cs) {
        if (len == ba.length 
            && (System.getSecurityManager() == null
            || cs.getClass().getClassLoader0() == null))
            return ba;
        else
            return Arrays.copyOf(ba, len);
    }

    /** Обрезает данный символьный массив до указанной длины.
    */
    private static char[] safeTrim(char[] ca, int len, Charset cs) {
        if (len == ca.length 
            && (System.getSecurityManager() == null
            || cs.getClass().getClassLoader0() == null))
            return ca;
        else
            return Arrays.copyOf(ca, len);
    }

    private static int scale(int len, float expansionFactor) {
        // Мы должны использовать арифметику двойной точности; в противном случае
        // мы потеряем младшие биты, когда len больше чем 2**24.
        return (int)(len * (double)expansionFactor);
    }

    private static Charset lookupCharset(String csn) {
        if (Charset.isSupported(csn)) {
            try {
                return Charset.forName(csn);
            } catch (UnsupportedCharsetException x) {
                throw new Error(x);
            }
        }
        return null;
    }

    private static void warnUnsupportedCharset(String csn) {
        if (warnUnsupportedCharset) {
            // Используем sun.misc.MessageUtils вместо API логирования или
            // System.err так как этот метод может вызываться во время 
            // инициализации VM до того, как они станут доступны.
            MessageUtils.err("WARNING: Default charset " + csn +
                     " not supported, using ISO-8859-1 instead");
            warnUnsupportedCharset = false;
        }
    }


    // -- Декодирование --
    private static class StringDecoder {
        private final String requestedCharsetName;
        private final Charset cs;
        private final CharsetDecoder cd;

        private StringDecoder(Charset cs, String rcn) {
            this.requestedCharsetName = rcn;
            this.cs = cs;
            this.cd = cs.newDecoder()
                .onMalformedInput(CodingErrorAction.REPLACE)
                .onUnmappableCharacter(CodingErrorAction.REPLACE);
        }

        String charsetName() {
            if (cs instanceof HistoricallyNamedCharset)
                return ((HistoricallyNamedCharset)cs).historicalName();
            return cs.name();
        }

        final String requestedCharsetName() {
            return requestedCharsetName;
        }

        char[] decode(byte[] ba, int off, int len) {
            int en = scale(len, cd.maxCharsPerByte());
            char[] ca = new char[en];
            if (len == 0)
                return ca;
            cd.reset();
            ByteBuffer bb = ByteBuffer.wrap(ba, off, len);
            CharBuffer cb = CharBuffer.wrap(ca);
            try {
                CoderResult cr = cd.decode(bb, cb, true);
                if (!cr.isUnderflow())
                    cr.throwException();
                cr = cd.flush(cb);
                if (!cr.isUnderflow())
                    cr.throwException();
            } catch (CharacterCodingException x) {
                // Подстановка всегда включена,
                // так что это никогда не должно случиться
                throw new Error(x);
            }
            return safeTrim(ca, cb.position(), cs);
        }

    }

    static char[] decode(String charsetName, byte[] ba, int off, int len)
        throws UnsupportedEncodingException
    {
        StringDecoder sd = (StringDecoder)deref(decoder);
        String csn = (charsetName == null) ? "ISO-8859-1" : charsetName;
        if ((sd == null) || !(csn.equals(sd.requestedCharsetName())
                           || csn.equals(sd.charsetName()))) {
            sd = null;
            try {
                Charset cs = lookupCharset(csn);
                if (cs != null)
                    sd = new StringDecoder(cs, csn);
            } catch (IllegalCharsetNameException x) {}
            if (sd == null)
                throw new UnsupportedEncodingException(csn);
            set(decoder, sd);
        }
        return sd.decode(ba, off, len);
    }

    static char[] decode(Charset cs, byte[] ba, int off, int len) {
        StringDecoder sd = new StringDecoder(cs, cs.name());
        byte[] b = Arrays.copyOf(ba, ba.length);
        return sd.decode(b, off, len);
    }

    static char[] decode(byte[] ba, int off, int len) {
        String csn = Charset.defaultCharset().name();
        try {
            return decode(csn, ba, off, len);
        } catch (UnsupportedEncodingException x) {
            warnUnsupportedCharset(csn);
        }
        try {
            return decode("ISO-8859-1", ba, off, len);
        } catch (UnsupportedEncodingException x) {
            // Если этот код выполнится во время инициализации VM, MessageUtils является 
            // единственным путем, которым можно получить любой вид сообщений об ошибках.
            MessageUtils.err("ISO-8859-1 charset not available: "
                     + x.toString());
            // Если мы не смогли найти ISO-8859-1 (требуемую кодировку) это 
            // говорит о серьезных неполадках с инсталляцией.
            System.exit(1);
            return null;
        }
    }




    // -- Кодирование --
    private static class StringEncoder {
        private Charset cs;
        private CharsetEncoder ce;
        private final String requestedCharsetName;

        private StringEncoder(Charset cs, String rcn) {
            this.requestedCharsetName = rcn;
            this.cs = cs;
            this.ce = cs.newEncoder()
                .onMalformedInput(CodingErrorAction.REPLACE)
                .onUnmappableCharacter(CodingErrorAction.REPLACE);
        }

        String charsetName() {
            if (cs instanceof HistoricallyNamedCharset)
                return ((HistoricallyNamedCharset)cs).historicalName();
            return cs.name();
        }

        final String requestedCharsetName() {
            return requestedCharsetName;
        }

        byte[] encode(char[] ca, int off, int len) {
            int en = scale(len, ce.maxBytesPerChar());
            byte[] ba = new byte[en];
            if (len == 0)
                return ba;

            ce.reset();
            ByteBuffer bb = ByteBuffer.wrap(ba);
            CharBuffer cb = CharBuffer.wrap(ca, off, len);
            try {
                CoderResult cr = ce.encode(cb, bb, true);
                if (!cr.isUnderflow())
                    cr.throwException();
                cr = ce.flush(bb);
                if (!cr.isUnderflow())
                    cr.throwException();
            } catch (CharacterCodingException x) {
                // Подстановка всегда включена,
                // так что это никогда не должно случиться
                throw new Error(x);
            }
            return safeTrim(ba, bb.position(), cs);
        }
    }

    static byte[] encode(String charsetName, char[] ca, int off, int len)
        throws UnsupportedEncodingException
    {
        StringEncoder se = (StringEncoder)deref(encoder);
        String csn = (charsetName == null) ? "ISO-8859-1" : charsetName;
        if ((se == null) || !(csn.equals(se.requestedCharsetName())
                           || csn.equals(se.charsetName()))) {
            se = null;
            try {
                Charset cs = lookupCharset(csn);
                if (cs != null)
                    se = new StringEncoder(cs, csn);
            } catch (IllegalCharsetNameException x) {}
            if (se == null)
                throw new UnsupportedEncodingException (csn);
            set(encoder, se);
        }
        return se.encode(ca, off, len);
    }

    static byte[] encode(Charset cs, char[] ca, int off, int len) {
        StringEncoder se = new StringEncoder(cs, cs.name());
        char[] c = Arrays.copyOf(ca, ca.length);
        return se.encode(c, off, len);
    }

    static byte[] encode(char[] ca, int off, int len) {
        String csn = Charset.defaultCharset().name();
        try {
            return encode(csn, ca, off, len);
        } catch (UnsupportedEncodingException x) {
            warnUnsupportedCharset(csn);
        }
        try {
            return encode("ISO-8859-1", ca, off, len);
        } catch (UnsupportedEncodingException x) {
            // Если этот код выполнится во время инициализации VM, MessageUtils является 
            // единственным путем, которым можно получить любой вид сообщений об ошибках.
            MessageUtils.err("ISO-8859-1 charset not available: "
                     + x.toString());
            // Если мы не смогли найти ISO-8859-1 (требуемую кодировку) это 
            // говорит о серьезных неполадках с инсталляцией.
            System.exit(1);
            return null;
        }
    }
}
