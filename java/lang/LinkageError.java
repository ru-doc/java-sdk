/*
 * @(#)LinkageError.java	1.15 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Подклассы <code>LinkageError</code> сообщают, что класс имеет некоторые
 * зависимости от другого класса; однако, последний класс имеет
 * несовместимые изменения после компиляции прежнего класса. 
 *
 *
 * @author  Frank Yellin
 * @version 1.15, 03/23/10
 * @since   JDK1.0
 */
public
class LinkageError extends Error {
    /**
     * Конструирует <code>LinkageError</code> без уточняющего сообщения.
     */
    public LinkageError() {
        super();
    }

    /**
     * Конструирует <code>LinkageError</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public LinkageError(String s) {
        super(s);
    }
}
