/*
 * @(#)StringValue.java	1.2 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.util.Arrays;

/**
 * Этот класс состоит исключительно из статических методов, работающих с 
 * символьными массивами, используемыми строками для хранения значения.
 */

class StringValue {
    private StringValue() { }

    /**
     * Возвращает массив символов, являющейся копией данного массива символов.
     */
    static char[] from(char[] value) {
        return Arrays.copyOf(value, value.length);
    }   
}
