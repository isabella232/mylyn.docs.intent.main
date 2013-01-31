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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getImplements <em>Implements</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getFields <em>Fields</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getMethods <em>Methods</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getClassifier()
 * @model
 * @generated
 */
public interface Classifier extends AbstractCapableElement {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind
	 * @see #setKind(ClassifierKind)
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getClassifier_Kind()
	 * @model required="true"
	 * @generated
	 */
	ClassifierKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(ClassifierKind value);

	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.docs.intent.bridge.java.Field}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getClassifier_Fields()
	 * @model containment="true" keys="name"
	 * @generated
	 */
	EList<Field> getFields();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.docs.intent.bridge.java.Method}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getClassifier_Methods()
	 * @model containment="true" keys="name"
	 * @generated
	 */
	EList<Method> getMethods();

	/**
	 * Returns the value of the '<em><b>Extends</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extends</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extends</em>' attribute.
	 * @see #setExtends(String)
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getClassifier_Extends()
	 * @model
	 * @generated
	 */
	String getExtends();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getExtends <em>Extends</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extends</em>' attribute.
	 * @see #getExtends()
	 * @generated
	 */
	void setExtends(String value);

	/**
	 * Returns the value of the '<em><b>Implements</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implements</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implements</em>' attribute list.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getClassifier_Implements()
	 * @model
	 * @generated
	 */
	EList<String> getImplements();

} // Classifier
