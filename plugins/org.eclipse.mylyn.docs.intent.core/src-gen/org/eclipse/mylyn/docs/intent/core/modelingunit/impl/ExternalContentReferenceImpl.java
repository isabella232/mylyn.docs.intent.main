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
package org.eclipse.mylyn.docs.intent.core.modelingunit.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Content Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ExternalContentReferenceImpl#getExternalContent <em>External Content</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ExternalContentReferenceImpl#isMarkedAsMerged <em>Marked As Merged</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalContentReferenceImpl extends ResourceDeclarationImpl implements ExternalContentReference {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalContentReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelingUnitPackage.Literals.EXTERNAL_CONTENT_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getExternalContent() {
		return (EObject)eGet(ModelingUnitPackage.Literals.EXTERNAL_CONTENT_REFERENCE__EXTERNAL_CONTENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalContent(EObject newExternalContent) {
		eSet(ModelingUnitPackage.Literals.EXTERNAL_CONTENT_REFERENCE__EXTERNAL_CONTENT, newExternalContent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMarkedAsMerged() {
		return (Boolean)eGet(ModelingUnitPackage.Literals.EXTERNAL_CONTENT_REFERENCE__MARKED_AS_MERGED, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMarkedAsMerged(boolean newMarkedAsMerged) {
		eSet(ModelingUnitPackage.Literals.EXTERNAL_CONTENT_REFERENCE__MARKED_AS_MERGED, newMarkedAsMerged);
	}

} //ExternalContentReferenceImpl
