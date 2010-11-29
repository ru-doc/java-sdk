/*
 * @(#)Readable.java	1.5 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.io.IOException;

/**
 * <tt>Readable</tt> является источником символов. Символы из 
 * <tt>Readable</tt> делаются доступными вызывающим метод чтения
 * через {@link java.nio.CharBuffer CharBuffer}.
 *
 * @version 1.5 10/03/23
 * @since 1.5
 */

public interface Readable {

    /**
     * Пытается прочитать символы в указанный буфер символов.
     * Буфер используется как хранилище символов "как есть": единственные
     * производимые изменения это результат опереции помещения (put). Не 
     * выполняется никаких операций отражения или перемотки.
     *
     * @param cb буфер для чтения в него символов.
     * @return Число значений <tt>char</tt>, добавленных в буфер,
     *         или -1, если источник символов достиг своего конца.
     * @throws IOException, если произошла ошибка ввода/вывода.
     * @throws NullPointerException, если <code>cb</code> равен <code>null</code>
     * @throws ReadOnlyBufferException, если <code>cb</code> является буфером только для чтения.
     */
    public int read(java.nio.CharBuffer cb) throws IOException;

}
