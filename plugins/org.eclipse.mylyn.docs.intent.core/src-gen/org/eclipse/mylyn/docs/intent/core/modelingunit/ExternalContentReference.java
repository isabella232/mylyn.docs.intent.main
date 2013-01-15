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
package org.eclipse.mylyn.docs.intent.core.modelingunit;

import org.eclipse.mylyn.docs.intent.core.compiler.ExternalContent;
import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Content Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference#getExternalContent <em>External Content</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference#isMarkedAsMerged <em>Marked As Merged</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getExternalContentReference()
 * @model
 * @generated
 */
public interface ExternalContentReference extends ResourceDeclaration {
	/**
	 * Returns the value of the '<em><b>External Content</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Content</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Content</em>' reference.
	 * @see #setExternalContent(ExternalContent)
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getExternalContentReference_ExternalContent()
	 * @model
	 * @generated
	 */
	ExternalContent getExternalContent();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference#getExternalContent <em>External Content</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Content</em>' reference.
	 * @see #getExternalContent()
	 * @generated
	 */
	void setExternalContent(ExternalContent value);

	/**
	 * Returns the value of the '<em><b>Marked As Merged</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Marked As Merged</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Marked As Merged</em>' attribute.
	 * @see #setMarkedAsMerged(boolean)
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#getExternalContentReference_MarkedAsMerged()
	 * @model
	 * @generated
	 */
	boolean isMarkedAsMerged();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference#isMarkedAsMerged <em>Marked As Merged</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Marked As Merged</em>' attribute.
	 * @see #isMarkedAsMerged()
	 * @generated
	 */
	void setMarkedAsMerged(boolean value);

} // ExternalContentReference
