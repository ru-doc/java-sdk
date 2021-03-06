/*
 * @(#)AnnotationType.java	1.3 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 */

package com.sun.mirror.type;


import com.sun.mirror.declaration.AnnotationTypeDeclaration;


/**
 * Represents an annotation type.
 *
 * @author Joseph D. Darcy
 * @author Scott Seligman
 * @version 1.3 10/03/23
 * @since 1.5
 */

public interface AnnotationType extends InterfaceType {

    /**
     * {@inheritDoc}
     */
    AnnotationTypeDeclaration getDeclaration();
}
