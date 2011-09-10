/*
 * @(#)ExceptionInInitializerError.java	1.19 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Сигнализирует, что неожиданное исключение произошло в статическом инициализаторе.
 * {@code ExceptionInInitializerError} кидается для указания того, что
 * исключение произошло во время вычисления статического инициализатора или
 * инициализации статической переменной.
 *
 * <p>С релиза 1.4, это исключение было модифицировано для соответствия унифицированному
 * механизму обработки цепочки исключений.  "Необязательное исключение, которое было
 * испущено при загрузке класса" которое можно было предоставить на этапе конструирования
 * и к которому можно было получить доступ через метод {@link #getException()}, теперь 
 * известно, как <i>причина</i>, и его можно получить через метод {@linkThrowable#getCause()},
 * точно также, как через вышеупомянутый "унаследованный метод".
 *
 * @author  Frank Yellin
 * @version 1.19, 03/23/10
 * @since   JDK1.1
 */
public class ExceptionInInitializerError extends LinkageError {
    /**
     * Используем serialVersionUID из JDK 1.1.X для для функциональной совместимости.
     */
    private static final long serialVersionUID = 1521711792217232256L;

    /**
     * Это поле хранит исключение, если для инстанцирования 
     * этого объекта использовался конструктор 
     * ExceptionInInitializerError(Throwable thrown).
     * 
     * @serial 
     * 
     */
    private Throwable exception;

    /**
     * Конструирует {@code ExceptionInInitializerError} без уточняющего
     * сообщения и без сохраненного throwable объекта.
     * 
     * Уточняющее сообщение - строка, описывающее это конкретное исключение.
     */
    public ExceptionInInitializerError() {
        initCause(null);  // Запрещаем последующее initCause
    }

    /**
     * Конструирует новый объект {@code ExceptionInInitializerError} с
     * сохранением ссылки на выброшенный объект {@code Throwable} для 
     * последующего получения его через метод {@link #getException()}.
     * Уточняющее сообщение будет установлено в {@code null}.
     *
     * @param thrown Выкинутое исключение.
     */
    public ExceptionInInitializerError(Throwable thrown) {
        initCause(null);  // Запрещаем последующее initCause
        this.exception = thrown;
    }

    /**
     * Конструирует {@code ExceptionInInitializerError} с указанным уточняющим
     * сообщением. Уточняющее сообщение - строка, описывающее это конкретное 
     * исключение. Уточняющее сообщение сохраняется для последующего получения
     * через метод {@link Throwable#getMessage()}. Этот конструктор не
     * сохраняет throwable объект.
     *
     *
     * @param s Уточняющее сообщение.
     */
    public ExceptionInInitializerError(String s) {
        super(s);
        initCause(null);  // Запрещаем последующее initCause
    }

    /**
     * Возвращает исключение, которое произошло при во время статической 
     * инициализации и привело к созданию этого исключения.
     *
     * <p>Этот метод появился до унифицированного метода обработки цепочки
     * исключений. Теперь для получания этой информации должен использоваться 
     * метод {@link Throwable#getCause()}.
     * 
     * @return Сохраненный throwable объект этого объекта
     *         {@code ExceptionInInitializerError}, или {@code null} 
     *         если этот объект {@code ExceptionInInitializerError} не имеет
     *         сохраненного throwable объекта. 
     */
    public Throwable getException() {
        return exception;
    }

    /**
     * Возвращает причину этой ошибки (исключение, которое произошло во
     * время статической инициализации и ставшее причиной этого исключения).
     * 
     * @return  Причина этой ошибки или {@code null}, если причина не
     *          существует или неизвестна.
     * @since   1.4
     */
    public Throwable getCause() {
        return exception;
    }
}
