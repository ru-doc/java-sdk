/*
 * @(#)Compiler.java	1.23 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Класс {@code Compiler} (компилятор) предоставляется для поддержки
 * компиляторов Java-в-native-код и связанных сервисов. По замыслу, класс
 * {@code Compiler} ничего не делает; он служит как место для реализации
 * JIT компилятора.
 * <p>
 * Когда виртуальная машина Java стартует впервые, она определяет, 
 * существует ли системное свойство {@code java.compiler}. (Системные 
 * свойства получаются через {@code getProperty}, метод, определенный 
 * в классе {@code System}.) Если да, она предполагает, что это имя 
 * библиотеки (с платформо-зависимым точным расположением и типом);
 * метод {@code loadLibrary} в классе {@code System} вызывается для 
 * загрузки этой библиотеки. Если загрузка прошла успешно, в библиотеке
 * вызывается функция {@code java_lang_Compiler_start()}.
 * 
 * <p>
 * Если компилятор недоступен, эти методы ничего не делают.
 *
 * @author  Frank Yellin
 * @version 1.23, 03/23/10
 * @see     java.lang.System#getProperty(java.lang.String)
 * @see     java.lang.System#getProperty(java.lang.String, java.lang.String)
 * @see     java.lang.System#loadLibrary(java.lang.String)
 * @since   JDK1.0
 */
public final class Compiler  {
    private Compiler() {}		// нельзя инстанцировать

    private static native void initialize();

    private static native void registerNatives();

    static {
        registerNatives();
		java.security.AccessController.doPrivileged
			(new java.security.PrivilegedAction() {
			public Object run() {
				boolean loaded = false;
				String jit = System.getProperty("java.compiler");
				if ((jit != null) && (!jit.equals("NONE")) &&
					(!jit.equals("")))
				{
					try {
						System.loadLibrary(jit);
						initialize();
						loaded = true;
					} catch (UnsatisfiedLinkError e) {
						System.err.println("Warning: JIT compiler \"" +
						  jit + "\" not found. Will use interpreter.");
					}
				}
				String info = System.getProperty("java.vm.info");
				if (loaded) {
					System.setProperty("java.vm.info", info + ", " + jit);
				} else {
					System.setProperty("java.vm.info", info + ", nojit");
				}
				return null;
			}
	    });
    }

    /**
     * Компилирует указанный класс.
     *
     * @param   clazz   класс для компиляции.
     * @return  {@code true}, если компиляция успешна;
     *          {@code false} если компиляция неудачна или компилятор
     *          недоступен.
     * @exception NullPointerException если {@code clazz} равен
     *          {@code null}.
     */
    public static native boolean compileClass(Class<?> clazz);

    /**
     * Компилирует все классы, чьи имена соответствуют указанной строке.
     *
     * @param   string   имя классов для компиляции.
     * @return  {@code true}, если компиляция успешна;
     *          {@code false} если компиляция неудачна или компилятор
     *          недоступен.
     * @exception NullPointerException если {@code string} равен 
     *          {@code null}.
     */
    public static native boolean compileClasses(String string);

    /**
     * Исследует тип агрумента и его поля и выполняет некоторую
     * документированную операцию. Не требует особых операций.
     *
     * @param   any   аргумент.
     * @return  зависимое от компилятора значение, или {@code null} если 
     *          компилятор недоступен.
     * @exception NullPointerException если {@code any} равен
     *          {@code null}.
     */
    public static native Object command(Object any);

    /**
     * Заставляет компилятор продолжить работу (по JIT компиляции).
     */
    public static native void enable();

    /**
     * Заставляет компилятор прекратить работу (по JIT компиляции).
     */
    public static native void disable();
}
