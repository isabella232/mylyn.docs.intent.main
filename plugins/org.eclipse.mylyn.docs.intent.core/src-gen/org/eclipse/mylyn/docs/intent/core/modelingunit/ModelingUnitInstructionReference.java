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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instruction Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference#getIntentHref <em>Intent Href</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference#getReferencedInstruction <em>Referenced Instruction</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getModelingUnitInstructionReference()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface ModelingUnitInstructionReference extends CDOObject {

	/**
	 * Returns the value of the '<em><b>Intent Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intent Href</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intent Href</em>' attribute.
	 * @see #setIntentHref(String)
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getModelingUnitInstructionReference_IntentHref()
	 * @model
	 * @generated
	 */
	String getIntentHref();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference#getIntentHref <em>Intent Href</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intent Href</em>' attribute.
	 * @see #getIntentHref()
	 * @generated
	 */
	void setIntentHref(String value);

	/**
	 * Returns the value of the '<em><b>Referenced Instruction</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Instruction</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Instruction</em>' reference.
	 * @see #setReferencedInstruction(ModelingUnitInstruction)
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getModelingUnitInstructionReference_ReferencedInstruction()
	 * @model
	 * @generated
	 */
	ModelingUnitInstruction getReferencedInstruction();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference#getReferencedInstruction <em>Referenced Instruction</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Instruction</em>' reference.
	 * @see #getReferencedInstruction()
	 * @generated
	 */
	void setReferencedInstruction(ModelingUnitInstruction value);

} // ModelingUnitInstructionReference
