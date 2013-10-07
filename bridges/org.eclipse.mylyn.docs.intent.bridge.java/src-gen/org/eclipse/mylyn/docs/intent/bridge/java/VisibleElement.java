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
 * A representation of the model object '<em><b>Visible Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#getClassifierPath <em>Classifier Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getVisibleElement()
 * @model
 * @generated
 */
public interface VisibleElement extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind
	 * @see #setVisibility(VisibilityKind)
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getVisibleElement_Visibility()
	 * @model required="true"
	 * @generated
	 */
	VisibilityKind getVisibility();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(VisibilityKind value);

	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(boolean)
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getVisibleElement_Static()
	 * @model
	 * @generated
	 */
	boolean isStatic();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);

	/**
	 * Returns the value of the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Final</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Final</em>' attribute.
	 * @see #setFinal(boolean)
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getVisibleElement_Final()
	 * @model
	 * @generated
	 */
	boolean isFinal();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#isFinal <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final</em>' attribute.
	 * @see #isFinal()
	 * @generated
	 */
	void setFinal(boolean value);

	/**
	 * Returns the value of the '<em><b>Classifier Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classifier Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classifier Path</em>' attribute.
	 * @see #setClassifierPath(String)
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getVisibleElement_ClassifierPath()
	 * @model
	 * @generated
	 */
	String getClassifierPath();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#getClassifierPath <em>Classifier Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier Path</em>' attribute.
	 * @see #getClassifierPath()
	 * @generated
	 */
	void setClassifierPath(String value);

} // VisibleElement
