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
package org.eclipse.mylyn.docs.intent.core.document.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;

import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;

import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intent Generic Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentGenericElementImpl#getCompilationStatus <em>Compilation Status</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentGenericElementImpl#getIndexEntry <em>Index Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentGenericElementImpl extends CDOObjectImpl implements IntentGenericElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntentGenericElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT;
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
	@SuppressWarnings("unchecked")
	public EList<CompilationStatus> getCompilationStatus() {
		return (EList<CompilationStatus>)eGet(
				IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__COMPILATION_STATUS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentIndexEntry getIndexEntry() {
		return (IntentIndexEntry)eGet(IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__INDEX_ENTRY,
				true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexEntry(IntentIndexEntry newIndexEntry) {
		eSet(IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__INDEX_ENTRY, newIndexEntry);
	}

} //IntentGenericElementImpl
