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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Change Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ReferenceChangeStatusImpl#getCompiledTarget <em>Compiled Target</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ReferenceChangeStatusImpl#getWorkingCopyTargetURIFragment <em>Working Copy Target URI Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceChangeStatusImpl extends StructuralFeatureChangeStatusImpl implements ReferenceChangeStatus {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceChangeStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompilerPackage.Literals.REFERENCE_CHANGE_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getCompiledTarget() {
		return (EObject)eGet(CompilerPackage.Literals.REFERENCE_CHANGE_STATUS__COMPILED_TARGET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompiledTarget(EObject newCompiledTarget) {
		eSet(CompilerPackage.Literals.REFERENCE_CHANGE_STATUS__COMPILED_TARGET, newCompiledTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkingCopyTargetURIFragment() {
		return (String)eGet(
				CompilerPackage.Literals.REFERENCE_CHANGE_STATUS__WORKING_COPY_TARGET_URI_FRAGMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingCopyTargetURIFragment(String newWorkingCopyTargetURIFragment) {
		eSet(CompilerPackage.Literals.REFERENCE_CHANGE_STATUS__WORKING_COPY_TARGET_URI_FRAGMENT,
				newWorkingCopyTargetURIFragment);
	}

} //ReferenceChangeStatusImpl
