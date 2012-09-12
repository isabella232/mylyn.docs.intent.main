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
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Type Reference</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.TypeReferenceImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.TypeReferenceImpl#getResolvedType <em>Resolved Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeReferenceImpl extends CDOObjectImpl implements TypeReference {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelingUnitPackage.Literals.TYPE_REFERENCE;
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
	public String getTypeName() {
		return (String)eGet(ModelingUnitPackage.Literals.TYPE_REFERENCE__TYPE_NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeName(String newTypeName) {
		eSet(ModelingUnitPackage.Literals.TYPE_REFERENCE__TYPE_NAME, newTypeName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResolvedType() {
		return (EClass)eGet(ModelingUnitPackage.Literals.TYPE_REFERENCE__RESOLVED_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolvedType(EClass newResolvedType) {
		eSet(ModelingUnitPackage.Literals.TYPE_REFERENCE__RESOLVED_TYPE, newResolvedType);
	}

} // TypeReferenceImpl
