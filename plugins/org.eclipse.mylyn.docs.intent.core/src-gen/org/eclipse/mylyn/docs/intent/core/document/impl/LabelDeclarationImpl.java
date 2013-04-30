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
package org.eclipse.mylyn.docs.intent.core.document.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration;
import org.eclipse.mylyn.docs.intent.core.document.TypeLabel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Label Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.LabelDeclarationImpl#getLabelValue <em>Label Value</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.LabelDeclarationImpl#getTextToPrint <em>Text To Print</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.LabelDeclarationImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabelDeclarationImpl extends UnitInstructionImpl implements LabelDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LabelDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IntentDocumentPackage.Literals.LABEL_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabelValue() {
		return (String)eGet(IntentDocumentPackage.Literals.LABEL_DECLARATION__LABEL_VALUE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelValue(String newLabelValue) {
		eSet(IntentDocumentPackage.Literals.LABEL_DECLARATION__LABEL_VALUE, newLabelValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextToPrint() {
		return (String)eGet(IntentDocumentPackage.Literals.LABEL_DECLARATION__TEXT_TO_PRINT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextToPrint(String newTextToPrint) {
		eSet(IntentDocumentPackage.Literals.LABEL_DECLARATION__TEXT_TO_PRINT, newTextToPrint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeLabel getType() {
		return (TypeLabel)eGet(IntentDocumentPackage.Literals.LABEL_DECLARATION__TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeLabel newType) {
		eSet(IntentDocumentPackage.Literals.LABEL_DECLARATION__TYPE, newType);
	}

} //LabelDeclarationImpl
