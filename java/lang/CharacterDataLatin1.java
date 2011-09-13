// Этот файл сгенерирован АВТОМАТИЧЕСКИ из файла шаблона Tue Jun 22 01:04:30 GMT-08:00 2010

/* @(#)CharacterDataLatin1.java.template	1.8 10/04/01
 *
 * Копирайт (c) 1994, 2002, Oracle и/или его филиалы. Все права защищены.
 *
 * Это программное обеспечение - проприетарная информация Oracle.
 * Использовать в соответствии с лицензией.
 *
 */

package java.lang;

/** Класс CharacterData инкапсулирует большие таблицы, найденные в 
    Java.lang.Character. */

class CharacterDataLatin1 {

    /* Свойства символов в настоящее время закодированы в 32 битах в следующем порядке:
        1 бит   свойство отражения (при смене направления письма символ можно отразить)
        4 бита  свойство направления
        9 битов смещение, используемое при изменении регистра
        1 бит   если 1, добавление смещения преобразует символ в нижний регистр
        1 бит   если 1, вычитание смещения преобразует символ в верхний регистр
        1 бит   если 1, этот символ имеет titlecase эквивалент (возможно он сам)
        3 бита  0  не может быть частью идентификатора
                1  игнорируемый управляющий символ; может продолжить идентификатор Юникода или Java
                2  может продолжить идентификатор Java, но не Юникода (не используется)
                3  может продолжить идентификатор Юникода или Java
                4  это пробельный символ Java
                5  может начать или продолжить идентификатор Java;
                   может продолжить но не начать идентификатор Юникода (подчеркивания)
                6  может начать или продолжить идентификатор Java но не Юникода ($)
                7  может начать или продолжить идентификатор Юникода или Java
                Таким образом:
                   5, 6, 7 может начать идентификатор Java
                   1, 2, 3, 5, 6, 7 может продолжить идентификатор Java
                   7 может начать идентификатор Юникода
                   1, 3, 5, 7 может продолжить идентификатор Юникода
                   1 игнорируется внутри идентификатора
                   4 является пробельным символом Java
        2 бита  0  этот символ не имеет числового свойства
                1  добавление цифрового смещения к коду символа и последующее
                   объединение с маской 0x1F даст требуемое числовое значение
                2  этот символ имеет "странное" числовое значение
                3  сверхчисло (supradecimal) Java: добавление цифрового смещения к
                   коду символа, объединение с маской 0x1F и добавление 10
                   даст требуемое числовое значение
        5 бит   цифровое смещение
        5 бит   тип символа

        Кодирование свойств символов может измениться в любое время.
     */

    static int getProperties(int ch) {
        char offset = (char)ch;
        int props = A[offset];
        return props;
    }

    static int getType(int ch) {
        int props = getProperties(ch);
        return (props & 0x1F);
    }

    static boolean isLowerCase(int ch) {
        int type = getType(ch);
        return (type == Character.LOWERCASE_LETTER);
    }

    static boolean isUpperCase(int ch) {
        int type = getType(ch);
        return (type == Character.UPPERCASE_LETTER);
    }

    static boolean isTitleCase(int ch) {
        return false;
    }

    static boolean isDigit(int ch) {
        int type = getType(ch);
        return (type == Character.DECIMAL_DIGIT_NUMBER);
    }

    static boolean isDefined(int ch) {
        int type = getType(ch);
        return (type != Character.UNASSIGNED);
    }

    static boolean isLetter(int ch) {
        int type = getType(ch);
        return (((((1 << Character.UPPERCASE_LETTER) |
            (1 << Character.LOWERCASE_LETTER) |
            (1 << Character.TITLECASE_LETTER) |
            (1 << Character.MODIFIER_LETTER) |
            (1 << Character.OTHER_LETTER)) >> type) & 1) != 0);
    }

    static boolean isLetterOrDigit(int ch) {
        int type = getType(ch);
        return (((((1 << Character.UPPERCASE_LETTER) |
            (1 << Character.LOWERCASE_LETTER) |
            (1 << Character.TITLECASE_LETTER) |
            (1 << Character.MODIFIER_LETTER) |
            (1 << Character.OTHER_LETTER) |
            (1 << Character.DECIMAL_DIGIT_NUMBER)) >> type) & 1) != 0);
    }

    static boolean isSpaceChar(int ch) {
        int type = getType(ch);
        return (((((1 << Character.SPACE_SEPARATOR) |
            (1 << Character.LINE_SEPARATOR) |
            (1 << Character.PARAGRAPH_SEPARATOR)) >> type) & 1) != 0);
    }


    static boolean isJavaIdentifierStart(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00007000) >= 0x00005000);
    }

    static boolean isJavaIdentifierPart(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00003000) != 0);
    }

    static boolean isUnicodeIdentifierStart(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00007000) == 0x00007000);
    }

    static boolean isUnicodeIdentifierPart(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00001000) != 0);
    }

    static boolean isIdentifierIgnorable(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00007000) == 0x00001000);
    }

    static int toLowerCase(int ch) {
        int mapChar = ch;
        int val = getProperties(ch);

        if (((val & 0x00020000) != 0) && 
            ((val & 0x07FC0000) != 0x07FC0000)) {
            int offset = val << 5 >> (5+18);
            mapChar = ch + offset;
        }
        return mapChar;
    }

    static int toUpperCase(int ch) {
        int mapChar = ch;
        int val = getProperties(ch);

        if ((val & 0x00010000) != 0) {
            if ((val & 0x07FC0000) != 0x07FC0000) {
                int offset = val  << 5 >> (5+18);
                mapChar =  ch - offset;
            } else if (ch == 0x00B5) {
                mapChar = 0x039C;
            }
        }
        return mapChar;
    }

    static int toTitleCase(int ch) {
        return toUpperCase(ch);
    }

    static int digit(int ch, int radix) {
        int value = -1;
        if (radix >= Character.MIN_RADIX && radix <= Character.MAX_RADIX) {
            int val = getProperties(ch);
            int kind = val & 0x1F;
            if (kind == Character.DECIMAL_DIGIT_NUMBER) {
                value = ch + ((val & 0x3E0) >> 5) & 0x1F;
            }
            else if ((val & 0xC00) == 0x00000C00) {
                // сверхчисло (supradecimal) Java
                value = (ch + ((val & 0x3E0) >> 5) & 0x1F) + 10;
            }
        }
        return (value < radix) ? value : -1;
    }

    static int getNumericValue(int ch) {
        int val = getProperties(ch);
        int retval = -1;

        switch (val & 0xC00) {
            default: // не может возникнуть
            case (0x00000000):          // не число
                retval = -1;
                break;
            case (0x00000400):          // простое число
                retval = ch + ((val & 0x3E0) >> 5) & 0x1F;
                break;
            case (0x00000800):          // "странное" число
                retval = -2; 
                break;
            case (0x00000C00):          // сверхчисло (supradecimal) Java
                retval = (ch + ((val & 0x3E0) >> 5) & 0x1F) + 10;
                break;
        }
        return retval;
    }

    static boolean isWhitespace(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00007000) == 0x00004000);
    }

    static byte getDirectionality(int ch) {
        int val = getProperties(ch);
        byte directionality = (byte)((val & 0x78000000) >> 27);

        if (directionality == 0xF ) {
            directionality = -1;
        }
        return directionality;
    }

    static boolean isMirrored(int ch) {
        int props = getProperties(ch);
        return ((props & 0x80000000) != 0);
    }

    static int toUpperCaseEx(int ch) {
        int mapChar = ch;
        int val = getProperties(ch);

        if ((val & 0x00010000) != 0) {
            if ((val & 0x07FC0000) != 0x07FC0000) {
                int offset = val  << 5 >> (5+18);
                mapChar =  ch - offset;
            }
            else {
                switch(ch) {
                    // отображаем символы за границами
                    case 0x00B5 : mapChar = 0x039C; break;
                    default     : mapChar = Character.ERROR; break;
                }
            }
        }
        return mapChar;
    }

    static char[] sharpsMap = new char[] {'S', 'S'};

    static char[] toUpperCaseCharArray(int ch) {
        char[] upperMap = {(char)ch};
        if (ch == 0x00DF) {
            upperMap = sharpsMap;
        }
        return upperMap;
    }


    // Следующие таблицы и код сгенерированы, используя:
    // java GenerateCharacter -template ../../tools/GenerateCharacter/CharacterDataLatin1.java.template -spec ../../tools/GenerateCharacter/UnicodeData.txt -specialcasing ../../tools/GenerateCharacter/SpecialCasing.txt -o C:/BUILD_~1/jdk6_21/control/build/WINDOW~1/gensrc/java/lang/CharacterDataLatin1.java -string -usecharforbyte -latin1 8
    // Таблица имеет 256 записей и занимает 1024 байт.

    static final int A[] = new int[256];
    static final String A_DATA =
    "\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800"+
    "\u100F\u4800\u100F\u4800\u100F\u5800\u400F\u5000\u400F\u5800\u400F\u6000\u400F"+
    "\u5000\u400F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800"+
    "\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F"+
    "\u4800\u100F\u4800\u100F\u5000\u400F\u5000\u400F\u5000\u400F\u5800\u400F\u6000"+
    "\u400C\u6800\030\u6800\030\u2800\030\u2800\u601A\u2800\030\u6800\030\u6800"+
    "\030\uE800\025\uE800\026\u6800\030\u2800\031\u3800\030\u2800\024\u3800\030"+
    "\u2000\030\u1800\u3609\u1800\u3609\u1800\u3609\u1800\u3609\u1800\u3609\u1800"+
    "\u3609\u1800\u3609\u1800\u3609\u1800\u3609\u1800\u3609\u3800\030\u6800\030"+
    "\uE800\031\u6800\031\uE800\031\u6800\030\u6800\030\202\u7FE1\202\u7FE1\202"+
    "\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1"+
    "\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202"+
    "\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1\202\u7FE1"+
    "\202\u7FE1\uE800\025\u6800\030\uE800\026\u6800\033\u6800\u5017\u6800\033\201"+
    "\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2"+
    "\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201"+
    "\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2\201\u7FE2"+
    "\201\u7FE2\201\u7FE2\201\u7FE2\uE800\025\u6800\031\uE800\026\u6800\031\u4800"+
    "\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u5000\u100F"+
    "\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800"+
    "\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F"+
    "\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800"+
    "\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F\u4800\u100F"+
    "\u3800\014\u6800\030\u2800\u601A\u2800\u601A\u2800\u601A\u2800\u601A\u6800"+
    "\034\u6800\034\u6800\033\u6800\034\000\u7002\uE800\035\u6800\031\u6800\u1010"+
    "\u6800\034\u6800\033\u2800\034\u2800\031\u1800\u060B\u1800\u060B\u6800\033"+
    "\u07FD\u7002\u6800\034\u6800\030\u6800\033\u1800\u050B\000\u7002\uE800\036"+
    "\u6800\u080B\u6800\u080B\u6800\u080B\u6800\030\202\u7001\202\u7001\202\u7001"+
    "\202\u7001\202\u7001\202\u7001\202\u7001\202\u7001\202\u7001\202\u7001\202"+
    "\u7001\202\u7001\202\u7001\202\u7001\202\u7001\202\u7001\202\u7001\202\u7001"+
    "\202\u7001\202\u7001\202\u7001\202\u7001\202\u7001\u6800\031\202\u7001\202"+
    "\u7001\202\u7001\202\u7001\202\u7001\202\u7001\202\u7001\u07FD\u7002\201\u7002"+
    "\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201"+
    "\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002"+
    "\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\u6800"+
    "\031\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002\201\u7002"+
    "\u061D\u7002";

    // Итого, таблицы свойств символов требуют 1024 байт.

    static {
        { // ЭТОТ КОД СОЗДАН АВТОМАТИЧЕСКИ GenerateCharacter:
            char[] data = A_DATA.toCharArray();
            assert (data.length == (256 * 2));
            int i = 0, j = 0;
            while (i < (256 * 2)) {
                int entry = data[i++] << 16;
                A[j++] = entry | data[i++];
            }
        }

    }
}

