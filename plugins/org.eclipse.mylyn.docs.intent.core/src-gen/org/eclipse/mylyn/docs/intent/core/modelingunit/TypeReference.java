/*******************************************************************************
 * Copyright (c) 2010, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.core.modelingunit;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference#getResolvedType <em>Resolved Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getTypeReference()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface TypeReference extends CDOObject {

	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getTypeReference_TypeName()
	 * @model
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

	/**
	 * Returns the value of the '<em><b>Resolved Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolved Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolved Type</em>' reference.
	 * @see #setResolvedType(EClass)
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getTypeReference_ResolvedType()
	 * @model
	 * @generated
	 */
	EClass getResolvedType();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference#getResolvedType <em>Resolved Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolved Type</em>' reference.
	 * @see #getResolvedType()
	 * @generated
	 */
	void setResolvedType(EClass value);
} // TypeReference
