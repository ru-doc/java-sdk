/*
 * @(#)BeanContextServicesListener.java	1.11 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.beans.beancontext;

import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.beans.beancontext.BeanContextServiceRevokedListener;

/**
 * The listener interface for receiving 
 * <code>BeanContextServiceAvailableEvent</code> objects. 
 * A class that is interested in processing a 
 * <code>BeanContextServiceAvailableEvent</code> implements this interface. 
 */
public interface BeanContextServicesListener extends BeanContextServiceRevokedListener {

    /**
     * The service named has been registered. getService requests for
     * this service may now be made.
     * @param bcsae the <code>BeanContextServiceAvailableEvent</code> 
     */
    void serviceAvailable(BeanContextServiceAvailableEvent bcsae);
}
