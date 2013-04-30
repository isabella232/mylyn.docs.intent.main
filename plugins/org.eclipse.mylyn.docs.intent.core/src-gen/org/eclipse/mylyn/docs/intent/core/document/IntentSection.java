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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intent Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getIntentContent <em>Intent Content</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getSubSections <em>Sub Sections</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getUnits <em>Units</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getDescriptionUnits <em>Description Units</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getModelingUnits <em>Modeling Units</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getIntentSection()
 * @model
 * @generated
 */
public interface IntentSection extends IntentStructuredElement {
	/**
	 * Returns the value of the '<em><b>Intent Content</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intent Content</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intent Content</em>' containment reference list.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getIntentSection_IntentContent()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<EObject> getIntentContent();

	/**
	 * Returns the value of the '<em><b>Sub Sections</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.docs.intent.core.document.IntentSection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Sections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Sections</em>' reference list.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getIntentSection_SubSections()
	 * @model volatile="true" derived="true"
	 * @generated
	 */
	EList<IntentSection> getSubSections();

	/**
	 * Returns the value of the '<em><b>Units</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.docs.intent.core.document.GenericUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Units</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Units</em>' reference list.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getIntentSection_Units()
	 * @model volatile="true"
	 * @generated
	 */
	EList<GenericUnit> getUnits();

	/**
	 * Returns the value of the '<em><b>Description Units</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description Units</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description Units</em>' reference list.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getIntentSection_DescriptionUnits()
	 * @model volatile="true" derived="true"
	 * @generated
	 */
	EList<DescriptionUnit> getDescriptionUnits();

	/**
	 * Returns the value of the '<em><b>Modeling Units</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modeling Units</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modeling Units</em>' reference list.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#getIntentSection_ModelingUnits()
	 * @model volatile="true" derived="true"
	 * @generated
	 */
	EList<ModelingUnit> getModelingUnits();

} // IntentSection
