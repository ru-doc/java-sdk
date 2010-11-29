/*
 * @(#)Object.java	1.74 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

package java.lang;

/**
 * Класс <code>Object</code> является корнем иерархии классов. 
 * Каждый класс имеет <code>Object</code> как суперкласс. Все объекты, 
 * включая массивы, реализуют методы этого класса. 
 *
 * @author  unascribed
 * @version 1.74, 03/23/10
 * @see     java.lang.Class
 * @since   JDK1.0
 */
public class Object {

    private static native void registerNatives();
    static {
        registerNatives();
    }

    /**
     * Возвращает класс времени выполнения этого объекта. Возвращаемый объект
     * {@code Class} является объектом, который запирается статическими синхронизированными
     * ({@code static synchronized}) методами представляемого класса.
     *
     * <p><b>The actual result type is {@code Class<? extends |X|>}
     * where {@code |X|} is the erasure of the static type of the
     * expression on which {@code getClass} is called.</b> Например,
     * приведение не требуется для следующего фрагмента кода:</p>
     *
     * <p>
     * {@code Number n = 0;                             }<br>
     * {@code Class<? extends Number> c = n.getClass(); }
     * </p>
     *
     * @return Объект {@code Class}, представляющий класс времени выполнения
     *         этого объекта.
     * @see    <a href="http://java.sun.com/docs/books/jls/">The Java
     *         Language Specification, Third Edition (15.8.2 Class
     *         Literals)</a>
     */
    public final native Class<?> getClass();

    /**
     * Возвращает значение хеш-кода для этого объекта. This method is 
     * supported for the benefit of hashtables such as those provided by 
     * <code>java.util.Hashtable</code>. 
     * <p>
     * The general contract of <code>hashCode</code> is: 
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during 
     *     an execution of a Java application, the <tt>hashCode</tt> method 
     *     must consistently return the same integer, provided no information 
     *     used in <tt>equals</tt> comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application. 
     * <li>If two objects are equal according to the <tt>equals(Object)</tt>
     *     method, then calling the <code>hashCode</code> method on each of 
     *     the two objects must produce the same integer result. 
     * <li>It is <em>not</em> required that if two objects are unequal 
     *     according to the {@link java.lang.Object#equals(java.lang.Object)} 
     *     method, then calling the <tt>hashCode</tt> method on each of the 
     *     two objects must produce distinct integer results.  However, the 
     *     programmer should be aware that producing distinct integer results 
     *     for unequal objects may improve the performance of hashtables.
     * </ul>
     * <p>
     * As much as is reasonably practical, метод {@code hashCode}, определенный
     * классом <tt>Object</tt>, возвращает разные коды для разных объектов.
     * (Он обычно реализован конвертированием внутреннего адреса
     * объекта в код, но эта техника реализации не требуется
     * языком программирования 
     * Java<font size="-2"><sup>TM</sup></font>.)
     *
     * @return  значение хеш-кода для этого объекта.
     * @see     java.lang.Object#equals(java.lang.Object)
     * @see     java.util.Hashtable
     */
    public native int hashCode();

    /**
     * Показывает, что некоторый другой объект "равен" этому.
     * <p>
     * Метод <code>equals</code> реализует отношение эквивалентности
     * на не-null ссылках объектов:
     * <ul>
     * <li>Он <i>рефлексивный</i>: для любого не-null значения ссылки
     *     <code>x</code>, <code>x.equals(x)</code> должен вернуть
     *     <code>true</code>.
     * <li>Он <i>симметричный</i>: для любых не-null значений ссылок
     *     <code>x</code> и <code>y</code>, <code>x.equals(y)</code>
     *     должен вернуть <code>true</code> тогда и только тогда, когда
     *     <code>y.equals(x)</code> возвращает <code>true</code>.
     * <li>Он <i>транзитивный</i>: для любых не-null значений ссылок
     *     <code>x</code>, <code>y</code> и <code>z</code>, если
     *     <code>x.equals(y)</code> возвращает <code>true</code> и
     *     <code>y.equals(z)</code> возвращает <code>true</code>, то
     *     <code>x.equals(z)</code> должен вернуть <code>true</code>.
     * <li>Он <i>consistent</i>: для любых не-null значений ссылок
     *     <code>x</code> и <code>y</code>, несколько вызовов
     *     <tt>x.equals(y)</tt> всегда возвращают <code>true</code>
     *     или всегда возвращают <code>false</code>, provided no
     *     information used in <code>equals</code> comparisons on the
     *     objects is modified.
     * <li>Для любого не-null значения ссылки <code>x</code>,
     *     <code>x.equals(null)</code> должен вернуть <code>false</code>.
     * </ul>
     * <p>
     * The <tt>equals</tt> method for class <code>Object</code> implements 
     * the most discriminating possible equivalence relation on objects; 
     * так, для любых не-null значений ссылок <code>x</code> и <code>y</code>, 
     * этот метод возвращает <code>true</code> тогда и только тогда, когда
     * <code>x</code> и <code>y</code> ссылаются на один и тот же объект
     * (<code>x == y</code> имеет значение <code>true</code>).
     * <p>
     * Note that it is generally necessary to override the <tt>hashCode</tt>
     * method whenever this method is overridden, so as to maintain the
     * general contract for the <tt>hashCode</tt> method, which states
     * that equal objects must have equal hash codes. 
     *
     * @param   obj   ссылка на объект для сравнения.
     * @return  <code>true</code> если этот объетк такой же, как аргумент 
     *          obj; иначе <code>false</code>.
     * @see     #hashCode()
     * @see     java.util.Hashtable
     */
    public boolean equals(Object obj) {
        return (this == obj);
    }

    /**
     * Создает и возвращает копию этого объекта.  The precise meaning 
     * of "copy" may depend on the class of the object. The general 
     * intent is that, для любого объекта <tt>x</tt>, выражение:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * должно быть <code>true</code> и выражение:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * должно быть <tt>true</tt>, но это не абсолютные требования. 
     * В то же время, типичен такой случай:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * будет <tt>true</tt>, это не абсолютное требование. 
     * <p>
     * По соглашению, возвращаемый объект должен быть получен вызовом
     * <tt>super.clone</tt>. Если класс и все его суперклассы (исключая
     * <tt>Object</tt>), следуют этому соглашению, это будет случай, когда
     * <tt>x.clone().getClass() == x.getClass()</tt>.
     * <p>
     * По соглашению, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by <tt>super.clone</tt> before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by <tt>super.clone</tt>
     * need to be modified.
     * <p>
     * Метод <tt>clone</tt> для класса <tt>Object</tt> выполняет особую операцию
     * клонирования. Первое, если класс этого объекта не реализует интерфейс 
     * <tt>Cloneable</tt>, то кидается исключение <tt>CloneNotSupportedException</tt>.
     * Заметьте, что все массивы считаются реализующими интерфейс
     * <tt>Cloneable</tt>. 
     * В противном случае, метод создает новую инстанцию класса этого объекта
     * и инициализирует все ее поля существующим содержимым соответствующих
     * полей этого объекта, словно они были присвоены; само содержимое этих 
     * полей не копируется. Таким образом, этот метод выполняет "поверхностную копию" 
     * ("shallow copy") этого объекта, а не "глубокую копию" ("deep copy").
     * <p>
     * Сам класс <tt>Object</tt> не реализует интерфейс
     * <tt>Cloneable</tt>, поэтому вызов метода <tt>clone</tt> на объекте,
     * чей класс есть <tt>Object</tt>, приведет к выбросу исключения
     * во время выполнения.
     *
     * @return     копия этой инстанции.
     * @exception  CloneNotSupportedException  если класс объекта не 
     *               поддерживает интерфейс <code>Cloneable</code>. Подклассы,
     *               которые переопределяют метод <code>clone</code>, также
     *               могут кидать это исключение, чтобы сообщить, что инстанция
     *               не может быть скопирована.
     * @see java.lang.Cloneable
     */
    protected native Object clone() throws CloneNotSupportedException;

    /**
     * Возвращает строковое представление объекта. В общем случае, метод
     * <code>toString</code> возвращает строку, которая 
     * "в текстовом виде" представляет этот объект. Результат должен быть
     * кратким, но информативным представлением, легко читаемым человеком.
     * 
     * Рекомендуется, чтобы все подклассы переопределяли этот метод.
     * <p>
     * Метод <code>toString</code> для класса <code>Object</code> 
     * возвращает строку, состоящую из имени класса, инстанцией которого 
     * объект является, символа собаки `<code>@</code>' и беззнакового 
     * шестнадцатеричного представления хеш-кода объекта. Другими словами, 
     * метод возвращает строку, эквивалентную значению выражения:
     * 
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return  строковое представление объекта.
     */
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * Wakes up a single thread that is waiting on this object's 
     * monitor. If any threads are waiting on this object, one of them 
     * is chosen to be awakened. The choice is arbitrary and occurs at 
     * the discretion of the implementation. A thread waits on an object's 
     * monitor by calling one of the <code>wait</code> methods.
     * <p>
     * The awakened thread will not be able to proceed until the current 
     * thread relinquishes the lock on this object. The awakened thread will 
     * compete in the usual manner with any other threads that might be 
     * actively competing to synchronize on this object; for example, the 
     * awakened thread enjoys no reliable privilege or disadvantage in being 
     * the next thread to lock this object.
     * <p>
     * This method should only be called by a thread that is the owner 
     * of this object's monitor. A thread becomes the owner of the 
     * object's monitor in one of three ways: 
     * <ul>
     * <li>By executing a synchronized instance method of that object. 
     * <li>By executing the body of a <code>synchronized</code> statement 
     *     that synchronizes on the object. 
     * <li>Для объектов типа <code>Class</code> исполнением 
     *     синхронизированного статического метода этого класса. 
     * </ul>
     * <p>В одно и то же время только один поток может владеть монитором объекта. 
     * <p>Про синхронизацию отлично написано на {@link http://www.skipy.ru/technics/synchronization.html}
     * 
     * @exception  IllegalMonitorStateException  если текущий поток не
     *               является владельцем монитора этого объекта.
     * @see        java.lang.Object#notifyAll()
     * @see        java.lang.Object#wait()
     */
    public final native void notify();

    /**
     * Wakes up all threads that are waiting on this object's monitor. A 
     * thread waits on an object's monitor by calling one of the 
     * <code>wait</code> methods.
     * <p>
     * The awakened threads will not be able to proceed until the current 
     * thread relinquishes the lock on this object. The awakened threads 
     * will compete in the usual manner with any other threads that might 
     * be actively competing to synchronize on this object; for example, 
     * the awakened threads enjoy no reliable privilege or disadvantage in 
     * being the next thread to lock this object.
     * <p>
     * Этот метод должен вызываться только потоком, который владеет 
     * монитором этого объекта. Смотрите метод <code>notify</code> для 
     * описания способов, которыми поток может стать владельцем монитора.
     * <p>Про синхронизацию отлично написано на {@link http://www.skipy.ru/technics/synchronization.html}
     *
     * @exception  IllegalMonitorStateException  если текущий поток не
     *               является владельцем монитора этого объекта.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#wait()
     */
    public final native void notifyAll();

    /**
     * Causes the current thread to wait until either another thread invokes the 
     * {@link java.lang.Object#notify()} method or the 
     * {@link java.lang.Object#notifyAll()} method for this object, or a 
     * specified amount of time has elapsed. 
     * <p>
     * The current thread must own this object's monitor. 
     * <p>
     * This method causes the current thread (call it <var>T</var>) to 
     * place itself in the wait set for this object and then to relinquish 
     * any and all synchronization claims on this object. Thread <var>T</var> 
     * becomes disabled for thread scheduling purposes and lies dormant 
     * until one of four things happens:
     * <ul>
     * <li>Some other thread invokes the <tt>notify</tt> method for this 
     * object and thread <var>T</var> happens to be arbitrarily chosen as 
     * the thread to be awakened. 
     * <li>Some other thread invokes the <tt>notifyAll</tt> method for this 
     * object. 
     * <li>Some other thread {@linkplain Thread#interrupt() interrupts} 
     * thread <var>T</var>. 
     * <li>The specified amount of real time has elapsed, more or less.  If 
     * <tt>timeout</tt> is zero, however, then real time is not taken into 
     * consideration and the thread simply waits until notified. 
     * </ul>
     * The thread <var>T</var> is then removed from the wait set for this 
     * object and re-enabled for thread scheduling. It then competes in the 
     * usual manner with other threads for the right to synchronize on the 
     * object; once it has gained control of the object, all its 
     * synchronization claims on the object are restored to the status quo 
     * ante - that is, to the situation as of the time that the <tt>wait</tt> 
     * method was invoked. Thread <var>T</var> then returns from the 
     * invocation of the <tt>wait</tt> method. Thus, on return from the 
     * <tt>wait</tt> method, the synchronization state of the object and of 
     * thread <tt>T</tt> is exactly as it was when the <tt>wait</tt> method 
     * was invoked. 
     * <p>
     * A thread can also wake up without being notified, interrupted, or
     * timing out, a so-called <i>spurious wakeup</i>.  While this will rarely
     * occur in practice, applications must guard against it by testing for
     * the condition that should have caused the thread to be awakened, and
     * continuing to wait if the condition is not satisfied.  In other words,
     * waits should always occur in loops, like this one:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;условие не выполняется&gt;)
     *             obj.wait();
     *         ... // Выполняем действия, которые приведут к выполнению условия
     *     }
     * </pre>
     * (For more information on this topic, see Section 3.2.3 in Doug Lea's
     * "Concurrent Programming in Java (Second Edition)" (Addison-Wesley,
     * 2000), or Item 50 in Joshua Bloch's "Effective Java Programming
     * Language Guide" (Addison-Wesley, 2001).
     *
     * <p>If the current thread is {@linkplain java.lang.Thread#interrupt()
     * interrupted} by any thread before or while it is waiting, then an
     * <tt>InterruptedException</tt> is thrown.  This exception is not
     * thrown until the lock status of this object has been restored as
     * described above.
     *
     * <p>
     * Note that the <tt>wait</tt> method, as it places the current thread 
     * into the wait set for this object, unlocks only this object; any 
     * other objects on which the current thread may be synchronized remain 
     * locked while the thread waits.
     * <p>
     * Этот метод должен вызываться только потоком, который владеет 
     * монитором этого объекта. Смотрите метод <code>notify</code> для 
     * описания способов, которыми поток может стать владельцем монитора.
     * <p>Про синхронизацию отлично написано на {@link http://www.skipy.ru/technics/synchronization.html}
     *
     * @param      timeout   максимальное время ожидания в миллисекундах.
     * @exception  IllegalArgumentException      если значение таймаута
     *                                           отрицательно.
     * @exception  IllegalMonitorStateException  если текущий поток не
     *               является владельцем монитора этого объекта.
     * @exception  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#notifyAll()
     */
    public final native void wait(long timeout) throws InterruptedException;

    /**
     * Causes the current thread to wait until another thread invokes the 
     * {@link java.lang.Object#notify()} method or the 
     * {@link java.lang.Object#notifyAll()} method for this object, or 
     * some other thread interrupts the current thread, or a certain 
     * amount of real time has elapsed. 
     * <p>
     * This method is similar to the <code>wait</code> method of one 
     * argument, but it allows finer control over the amount of time to 
     * wait for a notification before giving up. The amount of real time, 
     * measured in nanoseconds, is given by:
     * <blockquote>
     * <pre>
     * 1000000*timeout+nanos</pre></blockquote>
     * <p>
     * In all other respects, this method does the same thing as the 
     * method {@link #wait(long)} of one argument. In particular, 
     * <tt>wait(0, 0)</tt> means the same thing as <tt>wait(0)</tt>.
     * <p>
     * The current thread must own this object's monitor. The thread 
     * releases ownership of this monitor and waits until either of the 
     * following two conditions has occurred: 
     * <ul>
     * <li>Another thread notifies threads waiting on this object's monitor 
     *     to wake up either through a call to the <code>notify</code> method 
     *     or the <code>notifyAll</code> method. 
     * <li>The timeout period, specified by <code>timeout</code> 
     *     milliseconds plus <code>nanos</code> nanoseconds arguments, has 
     *     elapsed. 
     * </ul>
     * <p>
     * The thread then waits until it can re-obtain ownership of the 
     * monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;условие не выполняется&gt;)
     *             obj.wait();
     *         ... // Выполняем действия, которые приведут к выполнению условия
     *     }
     * </pre>
     * Этот метод должен вызываться только потоком, который владеет 
     * монитором этого объекта. Смотрите метод <code>notify</code> для 
     * описания способов, которыми поток может стать владельцем монитора.
     * <p>Про синхронизацию отлично написано на {@link http://www.skipy.ru/technics/synchronization.html}
     *
     * @param      timeout   максимальное время для ожидания в миллисекундах.
     * @param      nanos     дополнительное время, в наносекундах в диапазоне
     *                       0-999999.
     * @exception  IllegalArgumentException      если значение таймаута
     *              отрицательно или значения наносекунд не в диапазоне
     *              0-999999.
     * @exception  IllegalMonitorStateException  если текущий поток не
     *               является владельцем монитора этого объекта.
     * @exception  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     */
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                "nanosecond timeout value out of range");
        }

        if (nanos >= 500000 || (nanos != 0 && timeout == 0)) {
            timeout++;
        }

        wait(timeout);
    }

    /**
     * Causes the current thread to wait until another thread invokes the 
     * {@link java.lang.Object#notify()} method or the 
     * {@link java.lang.Object#notifyAll()} method for this object. 
     * In other words, this method behaves exactly as if it simply 
     * performs the call <tt>wait(0)</tt>.
     * <p>
     * The current thread must own this object's monitor. The thread 
     * releases ownership of this monitor and waits until another thread 
     * notifies threads waiting on this object's monitor to wake up 
     * either through a call to the <code>notify</code> method or the 
     * <code>notifyAll</code> method. The thread then waits until it can 
     * re-obtain ownership of the monitor and resumes execution. 
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;условие не выполняется&gt;)
     *             obj.wait();
     *         ... // Выполняем действия, которые приведут к выполнению условия
     *     }
     * </pre>
     * Этот метод должен вызываться только потоком, который владеет 
     * монитором этого объекта. Смотрите метод <code>notify</code> для 
     * описания способов, которыми поток может стать владельцем монитора.
     * <p>Про синхронизацию отлично написано на {@link http://www.skipy.ru/technics/synchronization.html}
     *
     * @exception  IllegalMonitorStateException  если текущий поток не
     *               является владельцем монитора этого объекта.
     * @exception  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#notifyAll()
     */
    public final void wait() throws InterruptedException {
        wait(0);
    }

    /**
     * Вызывается сборщиком мусора на объекте, когда сборщик мусора 
     * определяет, что на объект больше нет ссылок.
     * A subclass overrides the <code>finalize</code> method to dispose of
     * system resources or to perform other cleanup. 
     * <p>
     * The general contract of <tt>finalize</tt> is that it is invoked 
     * if and when the Java<font size="-2"><sup>TM</sup></font> virtual 
     * machine has determined that there is no longer any
     * means by which this object can be accessed by any thread that has
     * not yet died, except as a result of an action taken by the
     * finalization of some other object or class which is ready to be
     * finalized. The <tt>finalize</tt> method may take any action, including
     * making this object available again to other threads; the usual purpose
     * of <tt>finalize</tt>, however, is to perform cleanup actions before 
     * the object is irrevocably discarded. For example, the finalize method 
     * for an object that represents an input/output connection might perform
     * explicit I/O transactions to break the connection before the object is
     * permanently discarded. 
     * <p>
     * The <tt>finalize</tt> method of class <tt>Object</tt> performs no 
     * special action; it simply returns normally. Subclasses of 
     * <tt>Object</tt> may override this definition.
     * <p>
     * The Java programming language does not guarantee which thread will 
     * invoke the <tt>finalize</tt> method for any given object. It is 
     * guaranteed, however, that the thread that invokes finalize will not 
     * be holding any user-visible synchronization locks when finalize is 
     * invoked. If an uncaught exception is thrown by the finalize method, 
     * the exception is ignored and finalization of that object terminates.
     * <p>
     * After the <tt>finalize</tt> method has been invoked for an object, no 
     * further action is taken until the Java virtual machine has again 
     * determined that there is no longer any means by which this object can 
     * be accessed by any thread that has not yet died, including possible
     * actions by other objects or classes which are ready to be finalized, 
     * at which point the object may be discarded.
     * <p>
     * Метод <tt>finalize</tt> никогда не вызывается более одного раза 
     * виртуальной машиной Java для любого объекта.
     * <p>
     * Любые исключения, выкинутые методом <code>finalize</code> приведут к 
     * остановке финализации этого объекта, но иначе будут
     * проигнорированы. 
     *
     * @throws Throwable the <code>Exception</code> raised by this method
     */
    protected void finalize() throws Throwable { }
}
