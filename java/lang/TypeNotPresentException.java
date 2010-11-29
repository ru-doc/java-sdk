/*
 * @(#)TypeNotPresentException.java	1.6 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда приложение пытается получить доступ к типу, используя 
 * строковое представление имени типа, но определение для типа с указанным 
 * именем не было найдено. Это исключение отличается от 
 * {@link ClassNotFoundException} тем, что <tt>ClassNotFoundException</tt> 
 * проверяемое исключение, в то время как это исключение не проверяется 
 * компилятором на предмет обязательного перехвата.
 * <p>Заметьте, что это исключение может использоваться, когда к переменным
 * неопределенного типа обращались, в то время как типы (например, классы, 
 * интерфейсы или типы аннотаций) еще загружались.
 *
 * @author  Josh Bloch
 * @since 1.5
 */
public class TypeNotPresentException extends RuntimeException {
    private String typeName;

    /**
     * Конструирует <tt>TypeNotPresentException</tt> для именованного типа
     * с указанной причиной.
     *
     * @param typeName полностью квалифицированное имя недоступного типа.
     * @param cause исключение, которое было брошено, когда система пыталась загрузить
     *              именованный тип, или <tt>null</tt>, если недоступно или неприминимо.
     */
    public TypeNotPresentException(String typeName, Throwable cause) {
        super("Type " + typeName + " not present", cause);
        this.typeName = typeName;
    }

    /**
     * Возвращает полностью квалифицированное имя недоступного типа.
     *
     * @return полностью квалифицированное имя недоступного типа.
     */
    public String typeName() { return typeName;}
}
