/*
 * @(#)StackTraceElement.java	1.12 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Элемент в трассе стека, как возвращается {@link Throwable#getStackTrace()}.
 * Каждый элемент представляет один кадр стека.
 * Все кадры стека, исключая один на самом верху, представляют вызовы методов.
 * Кадр на верху стека представляет точку исполнения, в которой была
 * сгенерирован трасса стека. Как правило, эта точка, в которой был создан
 * throwable, соответвующий трассе стека.
 * 
 *
 * @since  1.4
 * @author Josh Bloch
 */
public final class StackTraceElement implements java.io.Serializable {
    // Обычно инициализируется VM (публичный конструктор добавлен в 1.5)
    private String declaringClass;
    private String methodName;
    private String fileName;
    private int    lineNumber;

    /**
     * Создает элемент трассы стека, представляющий указанную точку
     * исполнения.
     *
     * @param declaringClass полностью квалифицированное имя класса, содержащего
     *        точку исполнения, представляемую элементом трассы стека.
     * @param methodName имя метода, содержащего точку исполнения, 
     *        представляемую элементом трассы стека.
     * @param fileName имя файла, содержащего точку исполнения, 
     *         представляемую элементом трассы стека, или {@code null}, если
     *         эта информация недоступна.
     * @param lineNumber номер строки в исходном файле, содержащая точку
     *         исполнения, представляемую элементом трассы стека или
     *         отрицательное число, если эта информация недоступна. Значение
     *         -2 означает, что метод содержит точку исполнеия в родном методе.
     *
     * @throws NullPointerException если <tt>declaringClass</tt> или
     *         <tt>methodName</tt> есть {@code null}.
     * @since 1.5
     */
    public StackTraceElement(String declaringClass, String methodName,
                             String fileName, int lineNumber) {
        if (declaringClass == null)
            throw new NullPointerException("Declaring class is null");
        if (methodName == null)
            throw new NullPointerException("Method name is null");
 
        this.declaringClass = declaringClass;
        this.methodName     = methodName;
        this.fileName       = fileName;
        this.lineNumber     = lineNumber;
    }

    /**
     * Возвращает имя исходного файла, содержащего точку исполнения, представляемую
     * этим элементом трассы стека. Обычно, оно соответствует атрибуту <tt>SourceFile</tt>
     * соответствующего <tt>class</tt>-файла (как указано в <i>Спецификации виртуальной
     * машины Java</i>, Раздел 4.7.7 (<i>The Java Virtual Machine Specification</i>,
     * Section 4.7.8)). На некоторых системах, имя может ссылаться на некоторый модуль
     * исходного кода, отличного от файла, например, на запись в репозитарии исходников.
     *
     * @return имя файла, содержащего точку исполнения, представляемую
     *         этим элементом трассы стека, или {@code null}, если эта
     *         информация недоступна.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Возвращает номер строки исходного файла, содержащей точку исполнения,
     * представляемую этим элементом трассы стека. Обычно, она получается из
     * атрибута <tt>LineNumberTable</tt> соответсвующего <tt>class</tt>-файла
     * (как указано в <i>Спецификации виртуальной машины Java</i>, Раздел 4.7.8
     * (<i>The Java Virtual Machine Specification</i>, Section 4.7.8)).
     *
     * @return номер строки исходного файла, содержащей точку исполнения,
     *         представляемую этим элементом трассы стека или отрицательное
     *         число, если эта информация недоступна.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Возвращает полностью квалифицированное имя класса, содержащего
     * точку исполнения, представляемую этим элементом трассы стека.
     *
     * @return полностью квалифицированное имя класса, содержащего
     *         точку исполнения, представляемую этим элементом трассы стека.
     */
    public String getClassName() {
        return declaringClass;
    }

    /**
     * Возвращает имя метода, содержащего точку исполнения, представляемую
     * этим элементом трассы стека. Если точка исполнения содержится в 
     * инициализаторе инстанции или класса, этот метод возвратит соответствующее
     * <i>специальное имя метода</i>, <tt>&lt;init&gt;</tt> или <tt>&lt;clinit&gt;</tt>, 
     * как указано в Разделе 3.9 <i>Спецификации виртуальной машины Java</i> 
     * (Section 3.9 <i>The Java Virtual Machine Specification</i>).
     *
     * @return имя метода, содержащего точку исполнения, представляемую
     *         этим элементом трассы стека.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Возвращает {@code true}, если метод, содержащий точку исполнения,
     * представляемую этим элементом трассы стека, является родным.
     *
     * @return {@code true} если метод, содержащий точку исполнения,
     *         представленную этим элементом трассы стека, является родным.
     */
    public boolean isNativeMethod() {
        return lineNumber == -2;
    }

    /**
     * Возвращает строковое представление этого элемента трассы стека. Формат
     * этой строки зависит от реализации, но следующие примеры могут быть
     * расценены, как типичные:
     * <ul>
     * <li>
     *   <tt>"MyClass.mash(MyClass.java:9)"</tt> - Здесь, <tt>"MyClass"</tt>
     *   это <i>полностью квалифицированное имя</i> класса, содержащего 
     *   точку исполнения, представляемую этим элементом трассы стека,
     *   <tt>"mash"</tt> это имя метода, содержащего точку исполнения,
     *   <tt>"MyClass.java"</tt> это исходный файл, содержащий точку 
     *   исполнения, и <tt>"9"</tt> это номер строки исходного файла,
     *   содержащая точку исполнения.
     * <li>
     *   <tt>"MyClass.mash(MyClass.java)"</tt> - То же, что и выше, но
     *   номер строки недоступен.
     * <li>
     *   <tt>"MyClass.mash(Unknown Source)"</tt> - То же, что и выше, но 
     *   ни имя файла, ни номер строки недоступны.
     * <li>
     *   <tt>"MyClass.mash(Native Method)"</tt> - То же, что и выше, но ни
     *   имя файла, ни номер строки недоступны, и метод содержит точку
     *   исполнения, известную, как родной метод.
     * </ul>
     * @see    Throwable#printStackTrace()
     */
    public String toString() {
        return getClassName() + "." + methodName +
            (isNativeMethod() ? "(Native Method)" :
             (fileName != null && lineNumber >= 0 ?
              "(" + fileName + ":" + lineNumber + ")" :
              (fileName != null ?  "("+fileName+")" : "(Unknown Source)")));
    }

    /**
     * Возвращает {@code true}, если указанный объект является другой инстанцией
     * {@code StackTraceElement}, представляющей ту же точку исполнения, что
     * и эта инстанция. Два элемента трассы стека <tt>a</tt> и
     * <tt>b</tt> равны тогда и только тогда, когда:
     * <pre>
     *     equals(a.getFileName(), b.getFileName()) &&
     *     a.getLineNumber() == b.getLineNumber()) &&
     *     equals(a.getClassName(), b.getClassName()) &&
     *     equals(a.getMethodName(), b.getMethodName())
     * </pre>
     * где {@code equals} определена, как:
     * <pre>
     *     static boolean equals(Object a, Object b) {
     *         return a==b || (a != null && a.equals(b));
     *     }
     * </pre>
     * 
     * @param  obj объект для сравнения с этим элементом трассы стека.
     * @return {@code true}, если указанный объект является другой инстанцией
     *         {@code StackTraceElement}, представляющей ту же точку исполнения,
     *         что и эта инстанция.
     */
    public boolean equals(Object obj) {
        if (obj==this)
            return true;
        if (!(obj instanceof StackTraceElement))
            return false;
        StackTraceElement e = (StackTraceElement)obj;
        return e.declaringClass.equals(declaringClass) && e.lineNumber == lineNumber
            && eq(methodName, e.methodName) && eq(fileName, e.fileName);
    }

    private static boolean eq(Object a, Object b) {
        return a==b || (a != null && a.equals(b));
    }

    /**
     * Возвращает значение хеш-кода для этого элемента трассы стека.
     */
    public int hashCode() {
        int result = 31*declaringClass.hashCode() + methodName.hashCode();
        result = 31*result + (fileName == null ?   0 : fileName.hashCode());
        result = 31*result + lineNumber;
        return result;
    }

    private static final long serialVersionUID = 6992337162326171013L;
}
