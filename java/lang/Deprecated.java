/*
 * @(#)Deprecated.java	1.6 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.lang.annotation.*;

/**
 * Элемент программы, аннотированный элементом &#64;Deprecated (Устаревший), говорит,
 * что программисты не должны его использовать, обычно потому, что это опасно или 
 * потому, что существует лучшая альтернатива. Компиляторы предупреждают, когда устаревший 
 * элемент программы используется или переопределяется в не-устаревшем коде.
 *
 * @author  Neal Gafter
 * @version 1.6, 03/23/10
 * @since 1.5
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Deprecated {
}
