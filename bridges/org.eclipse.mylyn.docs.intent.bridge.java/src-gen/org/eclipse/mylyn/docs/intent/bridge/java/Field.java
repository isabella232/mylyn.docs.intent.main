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
package org.eclipse.mylyn.docs.intent.bridge.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.Field#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.Field#getQualifiedType <em>Qualified Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getField()
 * @model
 * @generated
 */
public interface Field extends VisibleElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getField_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.bridge.java.Field#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Qualified Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualified Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualified Type</em>' attribute.
	 * @see #setQualifiedType(String)
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getField_QualifiedType()
	 * @model
	 * @generated
	 */
	String getQualifiedType();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.bridge.java.Field#getQualifiedType <em>Qualified Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualified Type</em>' attribute.
	 * @see #getQualifiedType()
	 * @generated
	 */
	void setQualifiedType(String value);

} // Field
