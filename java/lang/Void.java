/*
 * @(#)Void.java	1.16 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Класс Void является неинстанцируемым классом-заглушкой, хранящим
 * ссылку на объект Class, представляющий ключевое слово Java 
 * <code>void</code>.
 *
 * @author  unascribed
 * @version 1.16, 03/23/10
 * @since   JDK1.1
 */
public final
class Void {

    /**
     * Объект Class, представляющий псевдо-тип, соответствующий
     * ключевому слову <code>void</code>.
     */
    public static final Class<Void> TYPE = Class.getPrimitiveClass("void");

    /*
     * Класс Void не может быть инстанцирован.
     */
    private Void() {}
}
