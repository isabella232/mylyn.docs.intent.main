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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Label Reference Instruction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.LabelReferenceInstruction#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getLabelReferenceInstruction()
 * @model
 * @generated
 */
public interface LabelReferenceInstruction extends UnitInstruction, IntentReference {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.mylyn.docs.intent.core.document.TypeLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.document.TypeLabel
	 * @see #setType(TypeLabel)
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getLabelReferenceInstruction_Type()
	 * @model required="true"
	 * @generated
	 */
	TypeLabel getType();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.document.LabelReferenceInstruction#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.document.TypeLabel
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeLabel value);

} // LabelReferenceInstruction
