/*
 * @(#)NullPointerException.java	1.21 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Кидается, когда приложение пытается использовать <code>null</code> там, 
 * где требуется объект. Эти ситуации включают: 
 * <ul>
 * <li>Вызов метода инстанции объекта <code>null</code>. 
 * <li>Получение доступа или изменение поля объекта <code>null</code>. 
 * <li>Взятие длины <code>null</code>, как если бы это был массив. 
 * <li>Получение доступа или изменение слотов <code>null</code>, как если
 *     бы это был массив (доступ к <code>null</code> по индексу). 
 * <li>Кидание <code>null</code> как если бы это было значение 
 *     <code>Throwable</code> . 
 * </ul>
 * <p>
 * Приложения должны кидать инстанцию этого класса для уведомления о 
 * других недопустимых использованиях объекта <code>null</code>. 
 *
 * @author  unascribed
 * @version 1.21, 03/23/10
 * @since   JDK1.0
 */
public
class NullPointerException extends RuntimeException {
    /**
     * Конструирует <code>NullPointerException</code> без уточняющего сообщения.
     */
    public NullPointerException() {
        super();
    }

    /**
     * Конструирует <code>NullPointerException</code> с указанным 
     * уточняющим сообщением. 
     *
     * @param   s   уточняющее сообщение.
     */
    public NullPointerException(String s) {
        super(s);
    }
}
