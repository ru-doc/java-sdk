/*
 * @(#)StringBuilder.java	1.12 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;


/**
 * Изменяемая последовательность символов. Этот класс предоставляет API,
 * совместимое с {@code StringBuffer}, но без гарантий синхронизации.
 * Этот класс разработан для использования вместо {@code StringBuffer}
 * простой заменой, в местах, где строковый буфер будет использоваться
 * одним потоком (как обычно и бывает). Рекомендуется использовать этот
 * класс где только возможно, вместо {@code StringBuffer}, так как он
 * будет быстрее на большинстве реализаций.
 * 
 * <p>Основыне операции над {@code StringBuilder}-ом это методы
 * {@code append} и {@code insert}, которые перегружены для принятия
 * данных любого типа. Каждый эффективно преобразует полученную величину
 * в строку и добавляет или вставляет символы этой строки в построитель
 * строк. Метод {@code append} всегда добавляет эти символы в конец построителя
 * строк; метод {@code insert} добавляет символы, начиная с указанной точки.
 * 
 * 
 * <p>
 * Например, если {@code z} ссылается на объект построителя строк, чье
 * текущее содержимое равно "{@code start}", то вызов метода
 * {@code z.append("le")} привел бы к тому, что построитель строк
 * содержал бы "{@code startle}", тогда как
 * {@code z.insert(4, "le")} привело бы содержание строкового построителя
 * к "{@code starlet}". 
 * <p>
 * В общем, если {@code sb} ссылается на инстанцию {@code StringBuilder}, 
 * то {@code sb.append(x)} имеет тот же эффект, что и
 * {@code sb.insert(sb.length(),&nbsp;x)}.
 *
 * Каждый строковый построитель имеет вместимость. Пока длина
 * последовательности символов, содержащаяся в построителе строк, не
 * превышает вместимости, нет необходимости выделять новый внутренний
 * буфер. Если внутренний буфер переполняется, он автоматически увеличивается.
 *
 * <p>Инстанции {@code StringBuilder} небезопасны для использования
 * несколькими потоками. Если требуется такая синхронизация, то рекомендуется
 * использовать класс {@link java.lang.StringBuffer}.
 *
 * @author      Michael McCloskey
 * @version     1.12, 03/23/10
 * @see         java.lang.StringBuffer
 * @see         java.lang.String
 * @since       1.5
 */
public final class StringBuilder
    extends AbstractStringBuilder
    implements java.io.Serializable, CharSequence
{

    /** Используем serialVersionUID для функциональной совместимости. */
    static final long serialVersionUID = 4383685877147921099L;

    /**
     * Конструирует построитель строк без содержимого и начальной
     * вместимостью в 16 символов. 
     */
    public StringBuilder() {
        super(16);
    }

    /**
     * Конструирует построитель строк без содержимого и начальной
     * вместимостью, указанной в аргументе {@code capacity}.
     *
     * @param      capacity  начальная вместимость.
     * @throws     NegativeArraySizeException  если аргумент {@code capacity}
     *             меньше, чем {@code 0}.
     */
    public StringBuilder(int capacity) {
        super(capacity);
    }

    /**
     * Конструирует построитель строк, инициализированный содержимым
     * указанной строки. Начальная вместимость построителя строки будет
     * {@code 16} плюс длина строки.
     *
     * @param     str   начальное содержимое буфера.
     * @throws    NullPointerException если {@code str} есть {@code null}.
     */
    public StringBuilder(String str) {
        super(str.length() + 16);
        append(str);
    }

    /**
     * Конструирует построитель строк, содержащий те же символы, что и
     * указанный {@code CharSequence}. Начальная вместимость построителя 
     * строк будет равна {@code 16} плюс длина аргумента
     * {@code CharSequence}.
     *
     * @param     seq   последовательность для копирования.
     * @throws    NullPointerException если {@code seq} есть {@code null}.
     */
    public StringBuilder(CharSequence seq) {
        this(seq.length() + 16);
        append(seq);
    }

    /**
     * @see     java.lang.String#valueOf(java.lang.Object)
     * @see     #append(java.lang.String)
     */
    public StringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    public StringBuilder append(String str) {
        super.append(str);
        return this;
    }

    /** Добавляет указанный построитель строк к этой последовательности. */
    private StringBuilder append(StringBuilder sb) {
        if (sb == null)
            return append("null");
        int len = sb.length();
        int newcount = count + len;
        if (newcount > value.length)
            expandCapacity(newcount);
        sb.getChars(0, len, value, count);
        count = newcount;
        return this;
    }

    /**
     * Добавляет указанный <tt>StringBuffer</tt> к этой последовательности.
     * <p>
     * Символы агрумента <tt>StringBuffer</tt> присоединяются к этой 
     * последовательности, увеличивая длину этой последовательности
     * на длину агрумента.
     * Если <tt>sb</tt> есть <tt>null</tt>, то к последовательности будут
     * добавлены четыре символа <tt>"null"</tt>.
     * <p>
     * Пусть <i>n</i> это длина этой последовательности символов непосредственно
     * до выполнения метода <tt>append</tt>. Тогда символ по индексу <i>k</i>
     * в новой последовательности символов равен символу по индексу <i>k</i>
     * в старой последовательности символов, если <i>k</i> меньше чем
     * <i>n</i>; иначе, он равен символу по индексу <i>k-n</i> 
     * в аргументе {@code sb}.
     *
     * @param   sb   <tt>StringBuffer</tt> для присоединения.
     * @return  ссылка на этот же объект.
     */
    public StringBuilder append(StringBuffer sb) {
        super.append(sb);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilder append(CharSequence s) {
        if (s == null)
            s = "null";
        if (s instanceof String)
            return this.append((String)s);
        if (s instanceof StringBuffer)
            return this.append((StringBuffer)s);
        if (s instanceof StringBuilder)
            return this.append((StringBuilder)s);
        return this.append(s, 0, s.length());
    }

    /**
     * @throws     IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilder append(CharSequence s, int start, int end) {
        super.append(s, start, end);
        return this;
    }

    public StringBuilder append(char str[]) { 
        super.append(str);
        return this;
    }

    public StringBuilder append(char str[], int offset, int len) {
        super.append(str, offset, len);
        return this;
    }

    /**
     * @see     java.lang.String#valueOf(boolean)
     * @see     #append(java.lang.String)
     */
    public StringBuilder append(boolean b) {
        super.append(b);
        return this;
    }

    public StringBuilder append(char c) {
        super.append(c);
        return this;
    }

    /**
     * @see     java.lang.String#valueOf(int)
     * @see     #append(java.lang.String)
     */
    public StringBuilder append(int i) {
        super.append(i);
        return this;
    }

    /**
     * @see     java.lang.String#valueOf(long)
     * @see     #append(java.lang.String)
     */
    public StringBuilder append(long lng) {
        super.append(lng);
        return this;
    }

    /**
     * @see     java.lang.String#valueOf(float)
     * @see     #append(java.lang.String)
     */
    public StringBuilder append(float f) {
        super.append(f);
        return this;
    }

    /**
     * @see     java.lang.String#valueOf(double)
     * @see     #append(java.lang.String)
     */
    public StringBuilder append(double d) {
        super.append(d);
        return this;
    }

    /**
     * @since 1.5
     */
    public StringBuilder appendCodePoint(int codePoint) {
        super.appendCodePoint(codePoint);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilder delete(int start, int end) {
        super.delete(start, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilder deleteCharAt(int index) {
        super.deleteCharAt(index);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilder replace(int start, int end, String str) {
        super.replace(start, end, str);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilder insert(int index, char str[], int offset,
                                int len) 
    {
        super.insert(index, str, offset, len);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @see        java.lang.String#valueOf(java.lang.Object)
     * @see        #insert(int, java.lang.String)
     * @see        #length()
     */
    public StringBuilder insert(int offset, Object obj) {
        return insert(offset, String.valueOf(obj));
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @see        #length()
     */
    public StringBuilder insert(int offset, String str) {
        super.insert(offset, str);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilder insert(int offset, char str[]) {
        super.insert(offset, str);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilder insert(int dstOffset, CharSequence s) {
        if (s == null)
            s = "null";
        if (s instanceof String)
            return this.insert(dstOffset, (String)s);
        return this.insert(dstOffset, s, 0, s.length());
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilder insert(int dstOffset, CharSequence s,
                int start, int end)
    {
        super.insert(dstOffset, s, start, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @see        java.lang.String#valueOf(boolean)
     * @see        #insert(int, java.lang.String)
     * @see        #length()
     */
    public StringBuilder insert(int offset, boolean b) {
        super.insert(offset, b);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see        #length()
     */
    public StringBuilder insert(int offset, char c) {
        super.insert(offset, c);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @see        java.lang.String#valueOf(int)
     * @see        #insert(int, java.lang.String)
     * @see        #length()
     */
    public StringBuilder insert(int offset, int i) {
        return insert(offset, String.valueOf(i));
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @see        java.lang.String#valueOf(long)
     * @see        #insert(int, java.lang.String)
     * @see        #length()
     */
    public StringBuilder insert(int offset, long l) {
        return insert(offset, String.valueOf(l));
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @see        java.lang.String#valueOf(float)
     * @see        #insert(int, java.lang.String)
     * @see        #length()
     */
    public StringBuilder insert(int offset, float f) {
        return insert(offset, String.valueOf(f));
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @see        java.lang.String#valueOf(double)
     * @see        #insert(int, java.lang.String)
     * @see        #length()
     */
    public StringBuilder insert(int offset, double d) {
        return insert(offset, String.valueOf(d));
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public int indexOf(String str, int fromIndex) {
        return String.indexOf(value, 0, count,
                              str.toCharArray(), 0, str.length(), fromIndex);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public int lastIndexOf(String str) {
        return lastIndexOf(str, count);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public int lastIndexOf(String str, int fromIndex) {
        return String.lastIndexOf(value, 0, count,
                              str.toCharArray(), 0, str.length(), fromIndex);
    }

    public StringBuilder reverse() {
        super.reverse();
        return this;
    }

    public String toString() {
        // Создаем копию, а не расшариваем массив
        return new String(value, 0, count);
    }

    /**
     * Сохраняет состояние инстанции {@code StringBuilder} в поток
     * (таким образом, серилизуя ее).
     *
     * @serialData число символов, в данный момент хранящихся в построителе
     *             строки (<tt>int</tt>), за которым следует символы построителя
     *             строки (<tt>char[]</tt>). Длина массива
     *             <tt>char</tt> может быть больше, чем число символов,
     *             в данный момент хранящихся в построителе строки, в этом
     *             случае дополнительные символы игнорируются.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        s.defaultWriteObject();
        s.writeInt(count);
        s.writeObject(value);
    }

    /**
     * {@code readObject} dspsdftncz для восстановления состояния 
     * {@code StringBuffer}-а из потока.
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        count = s.readInt();
        value = (char[]) s.readObject();
    }

}
