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

import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Synchronizer Compilation Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.SynchronizerCompilationStatusImpl#getCompiledResourceURI <em>Compiled Resource URI</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.SynchronizerCompilationStatusImpl#getWorkingCopyResourceURI <em>Working Copy Resource URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SynchronizerCompilationStatusImpl extends CompilationStatusImpl implements SynchronizerCompilationStatus {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SynchronizerCompilationStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompilerPackage.Literals.SYNCHRONIZER_COMPILATION_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCompiledResourceURI() {
		return (String)eGet(CompilerPackage.Literals.SYNCHRONIZER_COMPILATION_STATUS__COMPILED_RESOURCE_URI,
				true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompiledResourceURI(String newCompiledResourceURI) {
		eSet(CompilerPackage.Literals.SYNCHRONIZER_COMPILATION_STATUS__COMPILED_RESOURCE_URI,
				newCompiledResourceURI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkingCopyResourceURI() {
		return (String)eGet(
				CompilerPackage.Literals.SYNCHRONIZER_COMPILATION_STATUS__WORKING_COPY_RESOURCE_URI, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingCopyResourceURI(String newWorkingCopyResourceURI) {
		eSet(CompilerPackage.Literals.SYNCHRONIZER_COMPILATION_STATUS__WORKING_COPY_RESOURCE_URI,
				newWorkingCopyResourceURI);
	}

} //SynchronizerCompilationStatusImpl
