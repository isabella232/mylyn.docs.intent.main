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
package org.eclipse.mylyn.docs.intent.core.modelingunit.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instanciation Instruction Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionReferenceImpl#getInstanceName <em>Instance Name</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionReferenceImpl#getInstanciation <em>Instanciation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstanciationInstructionReferenceImpl extends CDOObjectImpl implements InstanciationInstructionReference {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanciationInstructionReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelingUnitPackage.Literals.INSTANCIATION_INSTRUCTION_REFERENCE;
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
	public String getInstanceName() {
		return (String)eGet(ModelingUnitPackage.Literals.INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCE_NAME,
				true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanceName(String newInstanceName) {
		eSet(ModelingUnitPackage.Literals.INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCE_NAME, newInstanceName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanciationInstruction getInstanciation() {
		return (InstanciationInstruction)eGet(
				ModelingUnitPackage.Literals.INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCIATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanciation(InstanciationInstruction newInstanciation) {
		eSet(ModelingUnitPackage.Literals.INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCIATION,
				newInstanciation);
	}

} //InstanciationInstructionReferenceImpl
