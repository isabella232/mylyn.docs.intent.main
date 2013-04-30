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
import org.eclipse.emf.ecore.EObject;

import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ReferenceValueImpl#getInstanciationReference <em>Instanciation Reference</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ReferenceValueImpl#getReferenceType <em>Reference Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceValueImpl extends AbstractValueImpl implements ReferenceValue {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelingUnitPackage.Literals.REFERENCE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanciationInstructionReference getInstanciationReference() {
		return (InstanciationInstructionReference)eGet(
				ModelingUnitPackage.Literals.REFERENCE_VALUE__INSTANCIATION_REFERENCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanciationReference(InstanciationInstructionReference newInstanciationReference) {
		eSet(ModelingUnitPackage.Literals.REFERENCE_VALUE__INSTANCIATION_REFERENCE, newInstanciationReference);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getReferenceType() {
		return (EObject)eGet(ModelingUnitPackage.Literals.REFERENCE_VALUE__REFERENCE_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceType(EObject newReferenceType) {
		eSet(ModelingUnitPackage.Literals.REFERENCE_VALUE__REFERENCE_TYPE, newReferenceType);
	}

} //ReferenceValueImpl
