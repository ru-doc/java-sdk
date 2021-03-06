/*
 * @(#)CMMException.java	1.13 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * @(#)JavaCMMException.java    @(#)JavaCMMException.java	1.2    11/04/97

    Created by gbp, October 25, 1997

 * 
 */
/**********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (c) Eastman Kodak Company, 1997                      ***
 *** As  an unpublished  work pursuant to Title 17 of the United    ***
 *** States Code.  All rights reserved.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/


package java.awt.color;


/**
 * This exception is thrown if the native CMM returns an error.
 */

public class CMMException extends java.lang.RuntimeException {

    /**
     *  Constructs a CMMException with the specified detail message.
     *  @param s the specified detail message
     */
    public CMMException (String s) {
        super (s);
    }
}
