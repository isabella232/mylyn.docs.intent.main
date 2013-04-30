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
package org.eclipse.mylyn.docs.intent.core.document;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intent Generic Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement#getCompilationStatus <em>Compilation Status</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement#getIndexEntry <em>Index Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getIntentGenericElement()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface IntentGenericElement extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Compilation Status</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compilation Status</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compilation Status</em>' containment reference list.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getIntentGenericElement_CompilationStatus()
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus#getTarget
	 * @model opposite="target" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<CompilationStatus> getCompilationStatus();

	/**
	 * Returns the value of the '<em><b>Index Entry</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry#getReferencedElement <em>Referenced Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Entry</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Entry</em>' reference.
	 * @see #setIndexEntry(IntentIndexEntry)
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getIntentGenericElement_IndexEntry()
	 * @see org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry#getReferencedElement
	 * @model opposite="referencedElement"
	 * @generated
	 */
	IntentIndexEntry getIndexEntry();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement#getIndexEntry <em>Index Entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Entry</em>' reference.
	 * @see #getIndexEntry()
	 * @generated
	 */
	void setIndexEntry(IntentIndexEntry value);

} // IntentGenericElement
