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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documented Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.DocumentedElement#getJavadoc <em>Javadoc</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getDocumentedElement()
 * @model
 * @generated
 */
public interface DocumentedElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Javadoc</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Javadoc</em>' containment reference.
	 * @see #setJavadoc(Javadoc)
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage#getDocumentedElement_Javadoc()
	 * @model containment="true"
	 * @generated
	 */
	Javadoc getJavadoc();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.bridge.java.DocumentedElement#getJavadoc <em>Javadoc</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Javadoc</em>' containment reference.
	 * @see #getJavadoc()
	 * @generated
	 */
	void setJavadoc(Javadoc value);

} // DocumentedElement
