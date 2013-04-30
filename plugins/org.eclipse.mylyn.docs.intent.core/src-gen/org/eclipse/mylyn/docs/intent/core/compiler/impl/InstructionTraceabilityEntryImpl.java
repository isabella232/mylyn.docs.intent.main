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
package org.eclipse.mylyn.docs.intent.core.compiler.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry;

import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;

import org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instruction Traceability Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.InstructionTraceabilityEntryImpl#getInstruction <em>Instruction</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.InstructionTraceabilityEntryImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstructionTraceabilityEntryImpl extends CDOObjectImpl implements InstructionTraceabilityEntry {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstructionTraceabilityEntryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompilerPackage.Literals.INSTRUCTION_TRACEABILITY_ENTRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentGenericElement getInstruction() {
		return (IntentGenericElement)eGet(
				CompilerPackage.Literals.INSTRUCTION_TRACEABILITY_ENTRY__INSTRUCTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstruction(IntentGenericElement newInstruction) {
		eSet(CompilerPackage.Literals.INSTRUCTION_TRACEABILITY_ENTRY__INSTRUCTION, newInstruction);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EMap<String, EList<AbstractValue>> getFeatures() {
		return (EMap<String, EList<AbstractValue>>)eGet(
				CompilerPackage.Literals.INSTRUCTION_TRACEABILITY_ENTRY__FEATURES, true);
	}

} //InstructionTraceabilityEntryImpl
