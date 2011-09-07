/*
 * @(#)Appendable.java	1.5 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

import java.io.IOException;

/**
 * Объект, к которому могут быть добавлены символы и последовательности 
 * символов (<tt>char</tt>). Интерфейс <tt>Appendable</tt> должен быть
 * реализован любым классом, экземпляры которого предназначены для получения
 * форматированного вывода от {@link java.util.Formatter}.
 *
 * <p> Добавляемые символы должны быть допустимыми символами Юникода, как
 * описано в <a href="Character.html#unicode">Представлении символов Юникода</a>.
 * Заметьте, что дополнительные символы (supplementary characters) могут быть 
 * составлены из нескольких 16-битных <tt>char</tt> значений.
 *
 * <p> Объекты Appendable не обязательно безопасны для многопоточного доступа.
 * Потокобезопасность ложится на плечи классов, которые расширяют и 
 * реализуют этот интерфейс.
 *
 * <p> Так как этот интерфейс может быть реализован существующими классами 
 * с различными стилями обработки ошибок, нет никакой гарантии, что ошибки 
 * будут распространены на вызывающего.
 *
 * @version 	1.5, 03/23/10
 * @since 1.5
 */
public interface Appendable {

    /**
     * Добавляет указанную последовательность символов к этому объекту <tt>Appendable</tt>.
     *
     * <p> В зависимости от того, какой класс реализует последовательность 
     * символов <tt>csq</tt>, вся последовательность может не быть добавлена.
     * Например, если <tt>csq</tt> является {@link java.nio.CharBuffer}-ом, то
     * подпоследовательность для добавления определяется позицией в буфере и его размером.
     *
     * @param  csq
     *         Последовательность символов для добавления. Если <tt>csq</tt> 
     *         есть <tt>null</tt>, то к указанному <tt>Appendable</tt> 
     *         добавляются четыре символа <tt>"null"</tt>.
     *
     * @return  Ссылка на этот объект <tt>Appendable</tt>.
     *
     * @throws  IOException
     *          Если произошла ошибка ввода/вывода.
     */
    Appendable append(CharSequence csq) throws IOException;

    /**
     * Добавляет подпоследовательность указанной последовательности символов
     * к этому объекту <tt>Appendable</tt>.
     *
     * <p> Вызов этого метода в форме <tt>out.append(csq, start, end)</tt>, 
     * когда <tt>csq</tt> не <tt>null</tt>, ведет себя точно таким же образом,
     * как вызов
     * <pre>
     *     out.append(csq.subSequence(start, end))
     * </pre>
     *
     * @param  csq
     *         Последовательность символов, из которой будет добавлена
     *         подпоследовательность. Если <tt>csq</tt> есть <tt>null</tt>, 
     *         то к последовательности будут добавлены четыре символа 
     *         <tt>"null"</tt>.
     *
     * @param  start
     *         Индекс первого символа в подпоследовательности.
     *
     * @param  end
     *         Индекс символа, следующего за последним символов в 
     *         подпоследовательности.
     *
     * @return  Ссылка на этот объект <tt>Appendable</tt>.
     *
     * @throws  IndexOutOfBoundsException
     *          Если <tt>start</tt> или <tt>end</tt> отрицательны, <tt>start</tt>
     *          больше, чем <tt>end</tt>, или <tt>end</tt> больше, чем
     *          <tt>csq.length()</tt>
     *
     * @throws  IOException
     *          Если произошла ошибка ввода/вывода.
     */
    Appendable append(CharSequence csq, int start, int end) throws IOException;

    /**
     * Добавляет указанный символ к этому объекту <tt>Appendable</tt>.
     *
     * @param  c
     *         Символ для добавления.
     *
     * @return  Ссылка на этот объект <tt>Appendable</tt>.
     *
     * @throws  IOException
     *          Если произошла ошибка ввода/вывода.
     */
    Appendable append(char c) throws IOException;
}
