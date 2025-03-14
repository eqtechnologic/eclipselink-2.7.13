/*
 * Copyright (c) 2011, 2018 Oracle and/or its affiliates. All rights reserved.
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
//     Gordon Yorke - Initial development
//
package org.eclipse.persistence.internal.jpa.querydef;

import jakarta.persistence.criteria.CollectionJoin;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.MapJoin;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.SetJoin;
import jakarta.persistence.metamodel.Bindable;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.MapAttribute;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.persistence.metamodel.PluralAttribute;
import jakarta.persistence.metamodel.SingularAttribute;

import org.eclipse.persistence.internal.localization.ExceptionLocalization;

/**
 * <p>
 * <b>Purpose</b>: Represents a Join to a ElementCollection of basics.
 * <p>
 * <b>Description</b>: Represents a Join to a ElementCollection of basics.
 * Special type of Join that does not allow further joins.
 * <p>
 *
 * @see jakarta.persistence.criteria ListJoin
 *
 * @author gyorke
 * @since EclipseLink 1.2
 */
@SuppressWarnings("hiding")

public class BasicListJoinImpl<Z, E> extends ListJoinImpl<Z, E> {

    public <T> BasicListJoinImpl(Path<Z> parentPath, Metamodel metamodel, Class<E> javaClass, org.eclipse.persistence.expressions.Expression expressionNode, Bindable<T> modelArtifact){
        this(parentPath, metamodel, javaClass, expressionNode, modelArtifact,JoinType.INNER);
    }

    public <C, T> BasicListJoinImpl(Path<Z> parentPath, Metamodel metamodel, Class<E> javaClass, org.eclipse.persistence.expressions.Expression expressionNode, Bindable<T> modelArtifact, JoinType joinType){
        super(parentPath, null, metamodel, javaClass, expressionNode, modelArtifact, joinType);
    }

    /**
     * Return the path corresponding to the referenced non-collection valued
     * attribute.
     *
     * @param att
     *            attribute
     * @return path corresponding to the referenced attribute
     */
    @Override
    public <Y> Path<Y> get(SingularAttribute<? super E, Y> att){
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    /**
     * Return the path corresponding to the referenced collection-valued
     * attribute.
     *
     * @param collection
     *            collection-valued attribute
     * @return expression corresponding to the referenced attribute
     */
    @Override
    public <Y, C extends java.util.Collection<Y>> Expression<C> get(PluralAttribute<E, C, Y> collection){
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    /**
     * Return the path corresponding to the referenced map-valued attribute.
     *
     * @param map
     *            map-valued attribute
     * @return expression corresponding to the referenced attribute
     */
    @Override
    public <L, W, M extends java.util.Map<L, W>> Expression<M> get(MapAttribute<E, L, W> map){
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    /**
     * Return an expression corresponding to the type of the path.
     *
     * @return expression corresponding to the type of the path
     */
    @Override
    public Expression<Class<? extends E>> type(){
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_type_does_not_apply"));
    }

    @Override
    public <Y> Path<Y> get(String attName) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <Y> Join<E, Y> join(SingularAttribute<? super E, Y> attribute, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <Y> CollectionJoin<E, Y> join(CollectionAttribute<? super E, Y> collection, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <Y> SetJoin<E, Y> join(jakarta.persistence.metamodel.SetAttribute<? super E, Y> set, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <Y> ListJoin<E, Y> join(ListAttribute<? super E, Y> list, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <L, W> MapJoin<E, L, W> join(MapAttribute<? super E, L, W> map, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <E, Y> Join<E, Y> join(String attributeName, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <E, Y> CollectionJoin<E, Y> joinCollection(String attributeName, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <E, Y> ListJoin<E, Y> joinList(String attributeName, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <E, L, W> MapJoin<E, L, W> joinMap(String attributeName, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

    @Override
    public <E, Y> SetJoin<E, Y> joinSet(String attributeName, JoinType jt) {
        throw new IllegalStateException(ExceptionLocalization.buildMessage("pathnode_is_primitive_node"));
    }

}
