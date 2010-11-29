/*
 * @(#)EnumConstantNotPresentException.java	1.3 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда приложение пытается получить доступ к константе перечисления
 * по имени и тип константы перечисления не содержит константы с указанным именем.
 *
 * @author  Josh Bloch
 * @since   1.5
 */
public class EnumConstantNotPresentException extends RuntimeException {
    /**
     * Тип отсутствующей константы перечисления.
     */
    private Class<? extends Enum> enumType;

    /**
     * Имя отсутствующей константы перечисления.
     */
    private String constantName;

    /**
     * Конструирует <tt>EnumConstantNotPresentException</tt> для 
     * указанной константы.
     *
     * @param enumType тип отсутствующей константы перечисления.
     * @param constantName имя отсутствующей константы перечисления.
     */
    public EnumConstantNotPresentException(Class<? extends Enum> enumType,
                                           String constantName) {
        super(enumType.getName() + "." + constantName);
        this.enumType = enumType;
        this.constantName  = constantName;
    }

    /**
     * Возвращает тип отсутствующей константы перечисления.
     *
     * @return тип отсутствующей константы перечисления.
     */
    public Class<? extends Enum> enumType() { return enumType; }

    /**
     * Возвращает имя отсутствующей константы перечисления.
     *
     * @return имя отсутствующей константы перечисления.
     */
    public String constantName() { return constantName; }
}
