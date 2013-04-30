/**
 * Copyright (c) 2010, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.mylyn.docs.intent.core.modelingunit;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Meta Type Instruction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractMetaTypeInstruction#getMetaType <em>Meta Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getAbstractMetaTypeInstruction()
 * @model abstract="true"
 * @generated
 */
public interface AbstractMetaTypeInstruction extends ModelingUnitInstruction {
	/**
	 * Returns the value of the '<em><b>Meta Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Meta Type</em>' containment reference.
	 * @see #setMetaType(TypeReference)
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getAbstractMetaTypeInstruction_MetaType()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	TypeReference getMetaType();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractMetaTypeInstruction#getMetaType <em>Meta Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Meta Type</em>' containment reference.
	 * @see #getMetaType()
	 * @generated
	 */
	void setMetaType(TypeReference value);

} // AbstractMetaTypeInstruction
