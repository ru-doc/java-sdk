/*
 * @(#)ECKey.java	1.4 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package java.security.interfaces;

import java.security.spec.ECParameterSpec;

/**
 * The interface to an elliptic curve (EC) key.
 *
 * @author Valerie Peng
 *
 * @version 1.4, 03/23/10
 * @since 1.5
 */
public interface ECKey {
    /**
     * Returns the domain parameters associated
     * with this key. The domain parameters are 
     * either explicitly specified or implicitly 
     * created during key generation.
     * @return the associated domain parameters.
     */
    ECParameterSpec getParams();
}
