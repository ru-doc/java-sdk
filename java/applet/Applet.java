/*
 * @(#)Applet.java	1.84 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */
package java.applet;

import java.awt.*;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Locale;
import javax.accessibility.*;

/**
 * Апплет представляет собой маленькую программу, которая предназначена не 
 * для самостоятельноо выполнения, а для внедрения в другое приложение.
 * <p>
 * Класс <code>Applet</code> должен быть суперклассом любого апплета, 
 * который должен быть внедрен на веб-страницу или просмотрен с помощью
 * Java Applet Viewer. Класс <code>Applet</code> предоставляет стандартный 
 * интерфейс между апплетами и их окружением.
 *
 * @author      Arthur van Hoff
 * @author      Chris Warth
 * @version     1.84, 03/23/10
 * @since       JDK1.0
 */
public class Applet extends Panel {
    
    /**
     * Конструирует новый апплет. 
     * <p>
     * Примечание: Многие методы в <code>java.applet.Applet</code> 
     * могут быть вызваны апплетом только после того, как апплет будет
     * полностью сконструирован; апплет должен избегать вызовов методов
     * <code>java.applet.Applet</code> в конструкторе. 
     *
     * @exception HeadlessException, если <code>GraphicsEnvironment.isHeadless()</code>
     * вернет <code>true</code>.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @since 1.4
     */
    public Applet() throws HeadlessException {
        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException();
        }
    }
    
    /**
     * Апплеты могут сериализоваться, но ДОЛЖНЫ выполняться следующие условия:
     *
     * До сериализации:
     * Апплет должен быть в состоянии ОСТАНОВЛЕН (STOPPED).
     *
     * После десериализации:
     * Апплет должен быть восстановлен в состояние ОСТАНОВЛЕН (STOPPED) (и
     * большинство клиентов скорее всего переведет его в состояние ЗАПУЩЕН 
     * (RUNNING)). Поле stub (заглушка) будет восстановлено читателем.
     */
    transient private AppletStub stub;

    /* version ID для сериализованной формы. */
    private static final long serialVersionUID = -5836846270535785031L;

    /**
     * Читает апплет из входного потока объектов.
     * @exception HeadlessException, если
     * <code>GraphicsEnvironment.isHeadless()</code> вернет
     * <code>true</code>
     * @serial
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @since 1.4
     */
    private void readObject(ObjectInputStream s)
        throws ClassNotFoundException, IOException, HeadlessException {
        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException();
        }
        s.defaultReadObject();
    }

    /**
     * Устанавливает заглушку (stub) апплету. Это делается автоматически системой.
     * <p>Если имеется менеджер безопасности, будет вызван его метод 
     * <code>checkPermission</code> с разрешением 
     * <code>AWTPermission("setAppletStub")</code>,
     * если заглушка уже была установлен.
     * @param   stub   новая заглушка.
     * @exception SecurityException, если вызывающий не может установить заглушку.
     */
    public final void setStub(AppletStub stub) {
        if (this.stub != null) {
            SecurityManager s = System.getSecurityManager();
            if (s != null) {
                s.checkPermission(new AWTPermission("setAppletStub"));
            }
        }
        this.stub = (AppletStub)stub;
    }

    /**
     * Определяет, активен ли апплет. Апплет помечается активным непосредственно 
     * до вызова его метода <code>start</code>. Он становится неактивным
     * непосредственно перед вызовом его метода <code>stop</code>.
     *
     * @return  <code>true</code> если апплет активен;
     *          иначе <code>false</code>.
     * @see     java.applet.Applet#start()
     * @see     java.applet.Applet#stop()
     */
    public boolean isActive() {
        if (stub != null) {
            return stub.isActive();
        } else {	// Если поле stub не заполнено, апплет никогда не будет активен
            return false;
        }
    }

    /**
     * Получает URL документа, в который внедрен этот апплет.
     * Например, пусть апплет содержится в документе:
     * 
     * <blockquote><pre>
     *    http://java.sun.com/products/jdk/1.2/index.html
     * </pre></blockquote>
     * Базой документа будет:
     * <blockquote><pre>
     *    http://java.sun.com/products/jdk/1.2/index.html
     * </pre></blockquote>
     *
     * @return  {@link java.net.URL} документа, который содержит этот
     *          апплет.
     * @see     java.applet.Applet#getCodeBase()
     */
    public URL getDocumentBase() {
        return stub.getDocumentBase();
    }

    /**
     * Получает базовый URL. Это URL директории, которая содержит апплет.
     *
     * @return  базовый {@link java.net.URL} директории,
     *          в которой содержится апплет.
     * @see     java.applet.Applet#getDocumentBase()
     */
    public URL getCodeBase() {
        return stub.getCodeBase();
    }

    /**
     * Возвращает значение именованного параметра в HTML теге. Например,
     * если апплет определен как
     * <blockquote><pre>
     * &lt;applet code="Clock" width=50 height=50&gt;
     * &lt;param name=Color value="blue"&gt;
     * &lt;/applet&gt;
     * </pre></blockquote>
     * <p>
     * то вызов <code>getParameter("Color")</code> вернет
     * значение <code>"blue"</code>.
     * <p>
     * Аргумент <code>name</code> регистронезависим.
     *
     * @param   name   имя параметра.
     * @return  значение именованного параметра,
     *          или <tt>null</tt> если он не установлен.
     */
    public String getParameter(String name) {
        return stub.getParameter(name);
    }

    /**
     * Определяет контекст этого апплета, который позволяет, апплету
     * запрашивать и воздействовать на окружение, в котором он запущен.
     * <p>
     * Это окружение апплета представляет документ, который содержит 
     * апплет.
     *
     * @return  контекст апплета.
     */
    public AppletContext getAppletContext() {
        return stub.getAppletContext();
    }

    /**
     * Вызывается, когда апплет изменил размер.
     *
     * @param   width    новая требуемая величина для апплета.
     * @param   height   новая требуемая высота для апплета.
     */
    public void resize(int width, int height) {
        Dimension d = size();
        if ((d.width != width) || (d.height != height)) {
            super.resize(width, height);
            if (stub != null) {
                stub.appletResize(width, height);
            }
        }
    }

    /**
     * Вызывается, когда апплет изменил размер.
     *
     * @param   d   объект, определяющий новую ширину и высоту.
     */
    public void resize(Dimension d) {
        resize(d.width, d.height);
    }

    /**
     * Запрашивает отображение строкового аргумента в "статусной строке".
     * Многие браузеры и просмотрщики апплетов предоставляют такую
     * строку, где приложение может информировать пользователей
     * о своем текущем состоянии.
     *
     * @param   msg   строка для отображения в статусной строке.
     */
    public void showStatus(String msg) {
        getAppletContext().showStatus(msg);
    }

    /**
     * Возвращает объект <code>Image</code>, который может быть нарисован
     * на экране. Передаваемый аргумент <code>url</code> должен определять
     * абсолютный URL.
     * <p>
     * Этот метод всегда возвращает управление немедленно, независимо от того,
     * существует ли изображение. Когда апплет попытается нарисовать изображение
     * на экране, данные будут загружены. Графические примитивы, которые рисуют
     * изображение, будут инкрементально закрашивать экран.
     **
     * @param   url   абсолютный URL, содержащий местоположение изображения.
     * @return  изображение по указанному URL.
     * @see     java.awt.Image
     */
    public Image getImage(URL url) {
        return getAppletContext().getImage(url);
    }

    /**
     * Возвращает объект <code>Image</code>, который может быть нарисован
     * на экране. Передаваемый аргумент <code>url</code> должен определять
     * абсолютный URL. Аргумент <code>name</code> указывается относительно 
     * аргумента <code>url</code>.
     * <p>
     * Этот метод всегда возвращает управление немедленно, независимо от того,
     * существует ли изображение. Когда апплет попытается нарисовать изображение
     * на экране, данные будут загружены. Графические примитивы, которые рисуют
     * изображение, будут инкрементально закрашивать экран.
     **
     * @param   url   абсолютный URL, содержащий местоположение изображения.
     * @param   name  местоположение изображения относительно аргумента
     *                <code>url</code>.
     * @return  изображение по указанному URL.
     * @see     java.awt.Image
     */
    public Image getImage(URL url, String name) {
        try {
            return getImage(new URL(url, name));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /**
     * Получает аудиоклип по указанному URL.
     *
     * @param url указавает на аудиоклип
     * @return аудиоклип по указанному URL.
     *
     * @since       1.2
     */
    public final static AudioClip newAudioClip(URL url) {
        return new sun.applet.AppletAudioClip(url);
    }

    /**
     * Возвращает объект <code>AudioClip</code>, определяемый аргументом
     * <code>URL</code>.
     * <p>
     * Этот метод всегда возвращает управление немедленно, независимо от 
     * того, существует ли аудиоклип. Когда этот апплет попытается проиграть 
     * аудиоклип, данные будут загружены.
     *
     * @param   url   абсолютный URL, содержащий местоположение аудиоклипа.
     * @return  аудиоклип по указанному URL.
     * @see     java.applet.AudioClip
     */
    public AudioClip getAudioClip(URL url) {
        return getAppletContext().getAudioClip(url);
    }

    /**
     * Возвращает объект <code>AudioClip</code>, указанный аргументами
     * <code>URL</code> и <code>name</code>.
     * <p>
     * Этот метод всегда возвращает управление немедленно, независимо от того,
     * существует или нет аудиоклип. Когда этот апплет попытается воспроизвести
     * аудиоклип, данные будут загружены.
     *
     * @param   url    абсолютный URL, определяющий базовое местоположение
     *                 аудиоклипа.
     * @param   name   местоположение аудиоклипа, относительно аргумента
     *                 <code>url</code>.
     * @return  аудиоклип по указанному URL.
     * @see     java.applet.AudioClip
     */
    public AudioClip getAudioClip(URL url, String name) {
        try {
            return getAudioClip(new URL(url, name));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /**
     * Возвращает информацию об этом апплете. Апплет должен перегрузить этот 
     * метод, чтобы возвратить строку, содержащую информацию об авторе,
     * версии и копирайте апплета.
     * <p>
     * Реализация этого метода, предоставляемая классом <code>Applet</code>,
     * возвращает <code>null</code>.
     *
     * @return  строка, содержащая информацию об авторе, версии и копирайте
     *          апплета.
     */
    public String getAppletInfo() {
        return null;
    }

    /**
     * Получает локаль апплета. Она позволяет апплету
     * поддерживать свою собственную локаль, независимую от локали
     * браузера или просмотрщика апплетов.
     *
     * @return  локаль апплета; если никакая локаль не была установлена,
     *          возвращается локаль по умолчанию.
     * @since   JDK1.1
     */
    public Locale getLocale() {
        Locale locale = super.getLocale();
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    /**
     * Возвращает информацию о параметрах, которые понимает этот
     * апплет. Апплет должен перегрузить этот метод, чтобы вернуть 
     * массив строк, описывающих эти параметры.
     * <p>
     * Каждый элемент массива должен быть набором трех строк,
     * содержащих имя, тип и описание.
     * Например:
     * <p><blockquote><pre>
     * String pinfo[][] = {
     *	 {"fps",    "1-10",    "кадров в секунду"},
     *	 {"repeat", "boolean", "повторять изображения в цикле"},
     *	 {"imgs",   "url",     "директория с изображениями"}
     * };
     * </pre></blockquote>
     * <p>
     * Реализация этого метода, предоставляемая классом
     * <code>Applet</code>, возвращает <code>null</code>.
     *
     * @return  массив, описывающий параметры, которые ищет этот апплет.
     */
    public String[][] getParameterInfo() {
        return null;
    }

    /**
     * Проигрывает аудиоклип по указанному абсолютному URL. Если аудиоклип
     * не будет найден, ничего не произойдет.
     *
     * @param   url   абсолютный URL, содержащий местоположение аудиоклипа.
     */
    public void play(URL url) {
        AudioClip clip = getAudioClip(url);
        if (clip != null) {
            clip.play();
        }
    }

    /**
     * Проигрывает аудиоклип по указанному URL и спецификатору, относительному
     * к нему. Если аудиоклип не будет найден, ничего не произойдет.
     *
     * @param   url    абсолютный URL, содержащий базовое местоположение
     *                 аудиоклипа.
     * @param   name   местоположение аудиоклипа относительно аргумента
     *                 <code>url</code>.
     */
    public void play(URL url, String name) {
        AudioClip clip = getAudioClip(url, name);
        if (clip != null) {
            clip.play();
        }
    }

    /**
     * Вызывается браузером или просмотрщиком апплетов, чтобы сообщить
     * этому апплету, что он был загружен в систему. Он всегда вызыватеся
     * до первого вызова метода <code>start</code>.
     * 
     * <p>
     * Подкласс <code>Applet</code>-а должен перегрузить этот метод, если
     * он нуждаются в выполнении инициализации. Например, апплет с потоками
     * использовал бы метод <code>init</code> для создания потоков и 
     * метод <code>destroy</code> для их убиения.
     * <p>
     * Реализация этого метода, предоставляемая классом
     * <code>Applet</code>, ничего не делает.
     *
     * @see     java.applet.Applet#destroy()
     * @see     java.applet.Applet#start()
     * @see     java.applet.Applet#stop()
     */
    public void init() {
    }

    /**
     * Вызывается браузером или просмотрщиком апплетов, чтобы сообщить
     * этому апплету, что он должен начать исполняться. Он вызывается после 
     * метода <code>init</code> и каждый раз, когда апплет вновь посещается
     * на веб-странице.
     * <p>
     * Подкласс <code>Applet</code>-а должен перегрузить этот метод, если
     * он имеет какую-нибудь операцию, которую надо выполнять каждый раз 
     * при посещении веб-страницы, содержащей его. Например, апплет с 
     * анимацией может захотеть использовать метод <code>start</code> для 
     * возобновления анимации и метод <code>stop</code> для приостановки 
     * анимации.
     * <p>
     * Примечание: некоторые методы, такие как <code>getLocationOnScreen</code>,
     * могут обеспечивать значимые результаты только если апплет показыватся. 
     * Поскольку <code>isShowing</code> возвращает <code>false</code> когда 
     * метод <code>start</code> вызван впервые, методы, требующие от 
     * <code>isShowing</code> возврата <code>true</code> должны вызываться 
     * из <code>ComponentListener</code>-а.
     * <p>
     * Реализация этого метода, предоставляемая классом
     * <code>Applet</code>, ничего не делает.
     *
     * @see     java.applet.Applet#destroy()
     * @see     java.applet.Applet#init()
     * @see     java.applet.Applet#stop()
     * @see     java.awt.Component#isShowing()
     * @see     java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    public void start() {
    }

    /**
     * Вызывается браузером или просмотрщиком апплетов, чтобы сообщить
     * этому апплету, что он должен прекратить свое исполнение. Он вызывается,
     * когда веб-страница, содержащая этот апплет, была заменена другой 
     * страницей, а также непосредственно перед тем, как апплет будет уничтожен.
     * <p>
     * Подкласс <code>Applet</code>-а должен перегрузить этот метод, если
     * он имеет какую-нибудь операцию, которую надо выполнять каждый раз,
     * когда содержащая страница становится невидимой. Например, апплет с 
     * анимацией может захотеть использовать метод <code>start</code> для 
     * возобновления анимации и метод <code>stop</code> для приостановки 
     * анимации.
     * <p>
     * Реализация этого метода, предоставляемая классом
     * <code>Applet</code>, ничего не делает.
     *
     * @see     java.applet.Applet#destroy()
     * @see     java.applet.Applet#init()
     */
    public void stop() {
    }

    /**
     * Вызывается браузером или просмотрщиком апплетов, чтобы сообщить
     * этому апплету, что он больше не требуется и он должен уничтожить
     * любые ресурсы, которые он выделил. Метод <code>stop</code> всегда 
     * вызывается до метода <code>destroy</code>.
     * <p>
     * Подкласс <code>Applet</code>-а должен перегрузить этот метод, если
     * он имеет какую-нибудь операцию, которую он должен выполнить до 
     * уничтожения. Например, апплет с потоками использовал бы метод
     * <code>init</code> для создания потоков и метод <code>destroy</code> 
     * для их убиения.
     * <p>
     * Реализация этого метода, предоставляемая классом
     * <code>Applet</code>, ничего не делает.
     *
     * @see     java.applet.Applet#init()
     * @see     java.applet.Applet#start()
     * @see     java.applet.Applet#stop()
     */
    public void destroy() {
    }

    //
    // Поддержка Accessibility
    //

    AccessibleContext accessibleContext = null;

    /**
     * Получает AccessibleContext, ассоциированный с этим апплетом. 
     * Для апплетов AccessibleContext берет форму 
     * AccessibleApplet. 
     * Новая инстанция AccessibleApplet создается при необходимости.
     *
     * @return AccessibleApplet, который служит как
     *         AccessibleContext этого апплета.
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleApplet();
        }
        return accessibleContext;
    }

    /**
     * Этот класс реализует поддержку accessibility (для людей с физическими недостатками)
     * для класса <code>Applet</code>. Он обеспечивает реализацию Java Accessibility API, 
     * соответствующую элементам пользовательского интерфейса апплета.
     * @since 1.3
     */
    protected class AccessibleApplet extends AccessibleAWTPanel {

        private static final long serialVersionUID = 8127374778187708896L;

        /**
         * Получает роль этого объекта.
         *
         * @return инстанция объетка AccessibleRole, описывающего роль
         * объекта.
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.FRAME;
        }

        /**
         * Получает состояние этого объекта.
         *
         * @return инстанция объекта AccessibleStateSet, содержащего текущее
         * множество состояний объекта.
         * @see AccessibleState
         */
        public AccessibleStateSet getAccessibleStateSet() {
            AccessibleStateSet states = super.getAccessibleStateSet();
            states.add(AccessibleState.ACTIVE);
            return states;
        }

    }
}
