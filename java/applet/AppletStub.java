/*
 * @(#)AppletStub.java	1.28 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */
package java.applet;

import java.net.URL;

/**
 * Когда апплет создается в первый раз, заглушка апплета (applet stub) 
 * присоединяется к нему, используя метод <code>setStub</code>. Эта 
 * заглушка служит как интерфейс между апплетом и окружением браузера 
 * или просмотрщика апплета, в котором запущено приложение.
 * 
 *
 * @author      Arthur van Hoff
 * @version     1.28, 03/23/10
 * @see         java.applet.Applet#setStub(java.applet.AppletStub)
 * @since       JDK1.0
 */
public interface AppletStub {
    /**
     * Определяет, активен ли апплет. Апплет активен непосредственно до
     * вызова его метода <code>start</code>. Он становится неактивным
     * непосредственно перед вызовом его метода <code>stop</code>.
     *
     * @return  <code>true</code> если апплет активен;
     *          иначе <code>false</code>.
     */
    boolean isActive();

    
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
     * @return  {@link java.net.URL} документа, который содержит
     *          апплет.
     * @see     java.applet.AppletStub#getCodeBase()
     */
    URL getDocumentBase();

    /**
     * Получает базовый URL. Это URL директории, которая содержит апплет.
     *
     * @return  базовый {@link java.net.URL} директории,
     *          в которой содержится апплет.
     * @see     java.applet.AppletStub#getDocumentBase()
     */
    URL getCodeBase();

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
     *
     * @param   name   имя параметра.
     * @return  значение именованного параметра,
     * или <tt>null</tt> если он не установлен.
     */
    String getParameter(String name);

    /**
     * Возвращает контекст апплета.
     *
     * @return  контекст апплета.
     */
    AppletContext getAppletContext();

    /**
     * Вызывается, когда апплет изменил размер.
     *
     * @param   width    новая требуемая величина для апплета.
     * @param   height   новая требуемая высота для апплета.
     */
    void appletResize(int width, int height);
}
