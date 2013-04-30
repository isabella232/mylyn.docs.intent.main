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
package org.eclipse.mylyn.docs.intent.core.compiler;

import org.eclipse.emf.cdo.CDOObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;

import org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instruction Traceability Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry#getInstruction <em>Instruction</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getInstructionTraceabilityEntry()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface InstructionTraceabilityEntry extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Instruction</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instruction</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instruction</em>' reference.
	 * @see #setInstruction(IntentGenericElement)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getInstructionTraceabilityEntry_Instruction()
	 * @model required="true"
	 * @generated
	 */
	IntentGenericElement getInstruction();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry#getInstruction <em>Instruction</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instruction</em>' reference.
	 * @see #getInstruction()
	 * @generated
	 */
	void setInstruction(IntentGenericElement value);

	/**
	 * Returns the value of the '<em><b>Features</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type list of {@link org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractValue},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' map.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getInstructionTraceabilityEntry_Features()
	 * @model mapType="org.eclipse.mylyn.docs.intent.core.compiler.FeatureToAffectationEntry<org.eclipse.emf.ecore.EString, org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractValue>"
	 * @generated
	 */
	EMap<String, EList<AbstractValue>> getFeatures();

} // InstructionTraceabilityEntry
