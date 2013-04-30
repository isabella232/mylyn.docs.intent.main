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
package org.eclipse.mylyn.docs.intent.core.modelingunit.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractMetaTypeInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Meta Type Instruction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.AbstractMetaTypeInstructionImpl#getMetaType <em>Meta Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractMetaTypeInstructionImpl extends ModelingUnitInstructionImpl implements AbstractMetaTypeInstruction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractMetaTypeInstructionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelingUnitPackage.Literals.ABSTRACT_META_TYPE_INSTRUCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeReference getMetaType() {
		return (TypeReference)eGet(ModelingUnitPackage.Literals.ABSTRACT_META_TYPE_INSTRUCTION__META_TYPE,
				true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetaType(TypeReference newMetaType) {
		eSet(ModelingUnitPackage.Literals.ABSTRACT_META_TYPE_INSTRUCTION__META_TYPE, newMetaType);
	}

} //AbstractMetaTypeInstructionImpl
