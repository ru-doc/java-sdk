/*
 * @(#)ReferenceObjectCopierImpl.java	1.9 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sun.corba.se.impl.copyobject ;

import com.sun.corba.se.spi.copyobject.ObjectCopier ;

public class ReferenceObjectCopierImpl implements ObjectCopier
{
    public Object copy( Object obj )
    {
	return obj ;
    }
}

