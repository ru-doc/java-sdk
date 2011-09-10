/*
 * @(#)ClassNotFoundException.java	1.22 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда приложение пытается загрузить класс по его имени,
 * используя:
 * <ul>
 * <li>Метод {@code forName} в классе {@code Class}.
 * <li>Метод {@code findSystemClass} в классе {@code ClassLoader}.
 * <li>Метод {@code loadClass} в классе {@code ClassLoader}.
 * </ul>
 * <p>
 * но определение для класса с указанным именем не может быть найдено.
 *
 * <p>
 * С релиза 1.4, это исключение было модифицировано для соответствия унифицированному
 * механизму обработки цепочки исключений.  "Необязательное исключение, которое было
 * испущено при загрузке класса" которое можно было предоставить на этапе конструирования
 * и к которому можно было получить доступ через метод {@link #getException()}, теперь 
 * известно, как <i>причина</i>, и его можно получить через метод {@linkThrowable#getCause()},
 * точно также, как через вышеупомянутый "унаследованный метод".
 *
 * @author  unascribed
 * @version 1.22, 03/23/10
 * @see     java.lang.Class#forName(java.lang.String)
 * @see     java.lang.ClassLoader#findSystemClass(java.lang.String)
 * @see     java.lang.ClassLoader#loadClass(java.lang.String, boolean)
 * @since   JDK1.0
 */
public class ClassNotFoundException extends Exception {
    /**
     * Используем serialVersionUID из JDK 1.1.X для функциональной совместимости.
     */
    private static final long serialVersionUID = 9176873029745254542L;

    /**
     * Это поле хранит исключение {@code ex}, если для инстанцирования 
     * этого объекта использовался конструктор 
     * {@code ClassNotFoundException(String s, Throwable ex)}.
     * @serial 
     * @since 1.2
     */
    private Throwable ex;

    /**
     * Конструирует {@code ClassNotFoundException} без уточняющего сообщения.
     */
    public ClassNotFoundException() {
        super((Throwable)null);  // Запрещаем initCause
    }

    /**
     * Конструирует {@code ClassNotFoundException} с указанным уточняющим
     * сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public ClassNotFoundException(String s) {
        super(s, null);  // Запрещаем initCause
    }

    /**
     * Конструирует {@code ClassNotFoundException} с указанным уточняющим
     * сообщением и необязательным исключением, которое было испущено при
     * загрузке класса.
     *
     * @param s уточняющее сообщение.
     * @param ex исключение, которое ыбло испущено при загрузке класса.
     * @since 1.2
     */
    public ClassNotFoundException(String s, Throwable ex) {
        super(s, null);  // Запрещаем initCause
        this.ex = ex;
    }

    /**
     * Возвращает исключение, которое было испущено, если ошибка произошла 
     * при попытке загрузить класс. Иначе возвращает <tt>null</tt>.
     *
     * <p>Этот метод появился до унифицированного метода обработки цепочки
     * исключений. Теперь для получания этой информации должен использоваться 
     * метод {@link Throwable#getCause()}.
     *
     * @return исключение, которое было испущено при загрузке класса.
     * @since 1.2
     */
    public Throwable getException() {
        return ex;
    }

    /**
     * Возвращает причину этого исключения (исключение, которое было 
     * выкинуто, если ошибка произошла при попытке загрузить класс; иначе
     * <tt>null</tt>).
     *
     * @return  причина этого исключения.
     * @since   1.4
     */
    public Throwable getCause() {
        return ex;
    }
}
