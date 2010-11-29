/*
 * @(#)Override.java	1.7 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.lang.annotation.*;

/**
 * Указывает, что объявление метода предназначено для переопределения
 * определения методы в суперклассе. Если метод аннотирован этим
 * типом аннотаций, но не переопределяет метод суперкласса,
 * требуется, чтобы компиляторы генерировали собщение об ошибке.
 *
 * @author  Joshua Bloch
 * @since 1.5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
