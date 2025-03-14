/*
 * Copyright (c) 2006, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Oracle - initial API and implementation
//
package org.eclipse.persistence.jpa.jpql.tools.spi;

/**
 * The external representation of the managed type that is annotated with
 * {@link jakarta.persistence.Entity &#64;Entity}.
 * <p>
 * Provisional API: This interface is part of an interim API that is still under development and
 * expected to change significantly before reaching stability. It is available at this early stage
 * to solicit feedback from pioneering adopters on the understanding that any code that uses this
 * API will almost certainly be broken (repeatedly) as the API evolves.
 *
 * @version 2.3
 * @since 2.3
 * @author Pascal Filion
 */
public interface IEntity extends IManagedType {

    /**
     * Returns the name of this entity.
     *
     * @return The non-default name or the short class name of this entity
     */
    String getName();

    /**
     * Returns the external form of the given named query;
     *
     * @param queryName The name of the JPQL query to retrieve
     * @return The {@link IQuery} representing the JPQL query named with the given name; or
     * <code>null</code> if none could be found
     */
    IQuery getNamedQuery(String queryName);
}
