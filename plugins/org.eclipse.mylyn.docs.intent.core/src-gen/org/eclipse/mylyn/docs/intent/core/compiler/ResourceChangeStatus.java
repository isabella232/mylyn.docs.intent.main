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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Change Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus#getWorkingCopyResourceState <em>Working Copy Resource State</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus#getCompiledResourceState <em>Compiled Resource State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getResourceChangeStatus()
 * @model
 * @generated
 */
public interface ResourceChangeStatus extends SynchronizerCompilationStatus {
	/**
	 * Returns the value of the '<em><b>Working Copy Resource State</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Working Copy Resource State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Copy Resource State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState
	 * @see #setWorkingCopyResourceState(SynchronizerResourceState)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getResourceChangeStatus_WorkingCopyResourceState()
	 * @model
	 * @generated
	 */
	SynchronizerResourceState getWorkingCopyResourceState();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus#getWorkingCopyResourceState <em>Working Copy Resource State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Copy Resource State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState
	 * @see #getWorkingCopyResourceState()
	 * @generated
	 */
	void setWorkingCopyResourceState(SynchronizerResourceState value);

	/**
	 * Returns the value of the '<em><b>Compiled Resource State</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * The literals are from the enumeration {@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compiled Resource State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compiled Resource State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState
	 * @see #setCompiledResourceState(SynchronizerResourceState)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getResourceChangeStatus_CompiledResourceState()
	 * @model default=""
	 * @generated
	 */
	SynchronizerResourceState getCompiledResourceState();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus#getCompiledResourceState <em>Compiled Resource State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compiled Resource State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState
	 * @see #getCompiledResourceState()
	 * @generated
	 */
	void setCompiledResourceState(SynchronizerResourceState value);

} // ResourceChangeStatus
