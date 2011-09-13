/*
 * @(#)ProcessEnvironment.java	1.10 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

/** Мы используем API, которое получает доступ к так называемому "Блоку окружения"
 * Windows, который выглядит как массив из jchar вроде этого:
 *
 * FOO=BAR\u0000 ... GORP=QUUX\u0000\u0000
 *
 * У этой структуры данных есть много особенностей, которые пришлось учитывать:
 * (см.: http://windowssdk.msdn.microsoft.com/ru-ru/library/ms682009.aspx)
 * - Разделители в виде jchar NUL и два jchar NUL символа в конце.
 *   Кажется, что реализация Windows требует двойного NUL окончания, даже
 *   если окружение пустое. Мы должны всегда генерировать окружение с
 *   двойным NUL завершением, при принятии пустых окружений,
 *   содержащие один NUL.
 * - на Windows9x, это на самом деле массив 8-битовых char-ов, а не jchar-ов,
 *   закодированный в кодировке системы по умолчанию.
 * - Блок должен быть отсортирован по значению Юникода, регистронезависимо,
 *   как если бы все было в верхнем регистре.
 * - Есть специальные переменные окружения, поддерживаемые Windows,
 *   начинающиеся с символа `=' (!). Они используются для текущего каталога
 *   диска Windows (т.е. "=C:=C:\WINNT") или кода выхода последней команды
 *   (т.е. "=ExitCode=0000001").
 *
 * Так как Java и не-9x Windows гаворят одним и тем же набором символов и
 * даже одной и той же кодировкой, мы не имеем дела с ненадежным преобразованием
 * потоков байт. Просто добавляем нескольно завершающих символов NUL.
 *
 * System.getenv(String) регистронезависимо, в то время, как System.getenv()
 * возвращает карту, которая регистрозависима, что согласуется с родным
 * API Windows.
 *
 * Неприватные методы в этом классе не для общего использования даже
 * внутри этого пакета. Вместо этого они - системно-зависимые части
 * системно-независиморго метода с таким же именем. Даже не думайте
 * использовать этот класс, если имя требуемого метода появится ниже.
 *
 * @author Martin Buchholz
 * @version 1.10, 10/03/23
 * @since 1.5
 */

package java.lang;

import java.io.*;
import java.util.*;

final class ProcessEnvironment extends HashMap<String,String>
{
    private static String validateName(String name) {
        // Начальный символ `=' указывает на специальное имя переменной Windows -- OK
        if (name.indexOf('=', 1)   != -1 ||
            name.indexOf('\u0000') != -1)
            throw new IllegalArgumentException
				("Invalid environment variable name: \"" + name + "\"");
        return name;
    }

    private static String validateValue(String value) {
        if (value.indexOf('\u0000') != -1)
            throw new IllegalArgumentException
				("Invalid environment variable value: \"" + value + "\"");
        return value;
    }

    private static String nonNullString(Object o) {
        if (o == null)
            throw new NullPointerException();
        return (String) o;
    }

    public String put(String key, String value) {
        return super.put(validateName(key), validateValue(value));
    }

    public String get(Object key) {
        return super.get(nonNullString(key));
    }

    public boolean containsKey(Object key) {
        return super.containsKey(nonNullString(key));
    }

    public boolean containsValue(Object value) {
        return super.containsValue(nonNullString(value));
    }

    public String remove(Object key) {
        return super.remove(nonNullString(key));
    }

    private static class CheckedEntry
        implements Map.Entry<String,String>
    {
        private final Map.Entry<String,String> e;
        public CheckedEntry(Map.Entry<String,String> e) {this.e = e;}
        public String getKey()   { return e.getKey();}
        public String getValue() { return e.getValue();}
        public String setValue(String value) {
            return e.setValue(validateValue(value));
        }
        public String toString() { return getKey() + "=" + getValue();}
        public boolean equals(Object o) {return e.equals(o);}
        public int hashCode()    {return e.hashCode();}
    }

    private static class CheckedEntrySet
        extends AbstractSet<Map.Entry<String,String>>
    {
        private final Set<Map.Entry<String,String>> s;
        public CheckedEntrySet(Set<Map.Entry<String,String>> s) {this.s = s;}
        public int size()        {return s.size();}
        public boolean isEmpty() {return s.isEmpty();}
        public void clear()      {       s.clear();}
        public Iterator<Map.Entry<String,String>> iterator() {
            return new Iterator<Map.Entry<String,String>>() {
                Iterator<Map.Entry<String,String>> i = s.iterator();
                public boolean hasNext() { return i.hasNext();}
                public Map.Entry<String,String> next() {
                    return new CheckedEntry(i.next());
                }
                public void remove() { i.remove();}
            };
        }
        private static Map.Entry<String,String> checkedEntry (Object o) {
            Map.Entry<String,String> e = (Map.Entry<String,String>) o;
            nonNullString(e.getKey());
            nonNullString(e.getValue());
            return e;
        }
        public boolean contains(Object o) {return s.contains(checkedEntry(o));}
        public boolean remove(Object o)   {return s.remove(checkedEntry(o));}
    }

    private static class CheckedValues extends AbstractCollection<String> {
        private final Collection<String> c;
        public CheckedValues(Collection<String> c) {this.c = c;}
        public int size()                  {return c.size();}
        public boolean isEmpty()           {return c.isEmpty();}
        public void clear()                {       c.clear();}
        public Iterator<String> iterator() {return c.iterator();}
        public boolean contains(Object o)  {return c.contains(nonNullString(o));}
        public boolean remove(Object o)    {return c.remove(nonNullString(o));}
    }

    private static class CheckedKeySet extends AbstractSet<String> {
        private final Set<String> s;
        public CheckedKeySet(Set<String> s) {this.s = s;}
        public int size()                  {return s.size();}
        public boolean isEmpty()           {return s.isEmpty();}
        public void clear()                {       s.clear();}
        public Iterator<String> iterator() {return s.iterator();}
        public boolean contains(Object o)  {return s.contains(nonNullString(o));}
        public boolean remove(Object o)    {return s.remove(nonNullString(o));}
    }

    public Set<String> keySet() {
        return new CheckedKeySet(super.keySet());
    }

    public Collection<String> values() {
        return new CheckedValues(super.values());
    }

    public Set<Map.Entry<String,String>> entrySet() {
        return new CheckedEntrySet(super.entrySet());
    }


    private static final class NameComparator
        implements Comparator<String> {
        public int compare(String s1, String s2) {
            // Мы не можем использовать String.compareToIgnoreCase так как
            // она канонизирует к нижнему регистру, в то время как Windows
            // канонизирует к верхнему!  Например, "_" должно при сортировке 
            // располагаться *после* "Z", а не до.
            int n1 = s1.length();
            int n2 = s2.length();
            int min = Math.min(n1, n2);
            for (int i = 0; i < min; i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                if (c1 != c2) {
                    c1 = Character.toUpperCase(c1);
                    c2 = Character.toUpperCase(c2);
                    if (c1 != c2)
						// Переполнения не будет из-за расширения числа
						return c1 - c2;
                }
            }
            return n1 - n2;
        }
    }

    private static final class EntryComparator
        implements Comparator<Map.Entry<String,String>> {
        public int compare(Map.Entry<String,String> e1,
                   Map.Entry<String,String> e2) {
            return nameComparator.compare(e1.getKey(), e2.getKey());
        }
    }

    // Позволяет `=' как первый символ в имени, т.е. =C:=C:\DIR
    static final int MIN_NAME_LENGTH = 1;

    private static final NameComparator nameComparator;
    private static final EntryComparator entryComparator;
    private static final ProcessEnvironment theEnvironment;
    private static final Map<String,String> theUnmodifiableEnvironment;
    private static final Map<String,String> theCaseInsensitiveEnvironment;

    static {
        nameComparator  = new NameComparator();
        entryComparator = new EntryComparator();
        theEnvironment  = new ProcessEnvironment();
        theUnmodifiableEnvironment
            = Collections.unmodifiableMap(theEnvironment);

        String envblock = environmentBlock();
        int beg, end, eql;
        for (beg = 0;
             ((end = envblock.indexOf('\u0000', beg  )) != -1 &&
              // Начальный символ `=' указывает на специальное имя переменной Windows -- OK
              (eql = envblock.indexOf('='     , beg+1)) != -1);
             beg = end + 1) {
            // Игнорируем неверные строки окружения.
            if (eql < end)
				theEnvironment.put(envblock.substring(beg, eql),
						   envblock.substring(eql+1,end));
        }

        theCaseInsensitiveEnvironment
            = new TreeMap<String,String>(nameComparator);
        theCaseInsensitiveEnvironment.putAll(theEnvironment);
    }

    private ProcessEnvironment() {
        super();
    }

    private ProcessEnvironment(int capacity) {
        super(capacity);
    }

    // Только для использования System.getenv(String)
    static String getenv(String name) {
        // Оригинальная реализация использует родной вызов _wgetenv,
        // но оказалось, что _wgetenv непротиворечива только с
        // GetEnvironmentStringsW (для не-ASCII), если вместо `main' 
        // используется `wmain', даже если процесс создан с использованием 
        // CREATE_UNICODE_ENVIRONMENT. Вместо этого мы выполняем 
        // регистронезависимое сравнение самостоятельно. По крайней мере,
        // это гарантирует, что System.getenv().get(String) будет 
        // соответсвовать System.getenv(String).
        return theCaseInsensitiveEnvironment.get(name);
    }

    // Только для использования System.getenv()
    static Map<String,String> getenv() {
        return theUnmodifiableEnvironment;
    }

    // Только для использования ProcessBuilder.environment()
    static Map<String,String> environment() {
        return (Map<String,String>) theEnvironment.clone();
    }

    // Только для использования Runtime.exec(...String[]envp...)
    static Map<String,String> emptyEnvironment(int capacity) {
        return new ProcessEnvironment(capacity);
    }

    private static native String environmentBlock();

    // Только для использования ProcessImpl.start()
    String toEnvironmentBlock() {
        // Сортируем Юникодовые строки регистронезависимо по имени
        List<Map.Entry<String,String>> list
            = new ArrayList<Map.Entry<String,String>>(entrySet());
        Collections.sort(list, entryComparator);

        StringBuilder sb = new StringBuilder(size()*30);
        for (Map.Entry<String,String> e : list)
            sb.append(e.getKey())
              .append('=')
              .append(e.getValue())
              .append('\u0000');
        // Гарантируем двойной NUL в конце,
        // даже если окружение пустое.
        if (sb.length() == 0)
            sb.append('\u0000');
        sb.append('\u0000');
        return sb.toString();
    }

    static String toEnvironmentBlock(Map<String,String> map) {
        return map == null ? null :
            ((ProcessEnvironment)map).toEnvironmentBlock();
    }
}
