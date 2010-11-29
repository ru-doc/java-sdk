/*
 * @(#)Iterable.java	1.6 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.util.Iterator;

/** Реализация этого интерфейса позволяет объекту быть целью выражения
 *  "foreach".
 * @since 1.5
 */
public interface Iterable<T> {

    /**
     * Возвращает итератор по множеству элементов типа T.
     * 
     * @return итератор.
     */
    Iterator<T> iterator();
}
