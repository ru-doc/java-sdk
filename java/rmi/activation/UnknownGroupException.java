/*
 * @(#)UnknownGroupException.java	1.15 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.rmi.activation;

/**
 * An <code>UnknownGroupException</code> is thrown by methods of classes and
 * interfaces in the <code>java.rmi.activation</code> package when the
 * <code>ActivationGroupID</code> parameter to the method is determined to be
 * invalid, i.e., not known by the <code>ActivationSystem</code>.  An
 * <code>UnknownGroupException</code> is also thrown if the
 * <code>ActivationGroupID</code> in an <code>ActivationDesc</code> refers to
 * a group that is not registered with the <code>ActivationSystem</code>
 * 
 * @version 1.15, 03/23/10
 * @author  Ann Wollrath
 * @since   1.2
 * @see     java.rmi.activation.Activatable
 * @see     java.rmi.activation.ActivationGroup
 * @see     java.rmi.activation.ActivationGroupID
 * @see     java.rmi.activation.ActivationMonitor
 * @see     java.rmi.activation.ActivationSystem
 */
public class UnknownGroupException extends ActivationException {

    /** indicate compatibility with the Java 2 SDK v1.2 version of class */
    private static final long serialVersionUID = 7056094974750002460L;

    /**
     * Constructs an <code>UnknownGroupException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     * @since 1.2
     */
    public UnknownGroupException(String s) {
	super(s);
    }
}