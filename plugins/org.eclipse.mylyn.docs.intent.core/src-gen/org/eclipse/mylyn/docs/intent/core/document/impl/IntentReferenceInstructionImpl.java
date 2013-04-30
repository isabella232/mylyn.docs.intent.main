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
import org.eclipse.emf.ecore.EObject;

import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentReference;
import org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intent Reference Instruction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceInstructionImpl#getIntentHref <em>Intent Href</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceInstructionImpl#getReferencedElement <em>Referenced Element</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceInstructionImpl#getTextToPrint <em>Text To Print</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentReferenceInstructionImpl extends UnitInstructionImpl implements IntentReferenceInstruction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntentReferenceInstructionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IntentDocumentPackage.Literals.INTENT_REFERENCE_INSTRUCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIntentHref() {
		return (String)eGet(IntentDocumentPackage.Literals.INTENT_REFERENCE__INTENT_HREF, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntentHref(String newIntentHref) {
		eSet(IntentDocumentPackage.Literals.INTENT_REFERENCE__INTENT_HREF, newIntentHref);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getReferencedElement() {
		return (EObject)eGet(IntentDocumentPackage.Literals.INTENT_REFERENCE__REFERENCED_ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedElement(EObject newReferencedElement) {
		eSet(IntentDocumentPackage.Literals.INTENT_REFERENCE__REFERENCED_ELEMENT, newReferencedElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextToPrint() {
		return (String)eGet(IntentDocumentPackage.Literals.INTENT_REFERENCE_INSTRUCTION__TEXT_TO_PRINT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextToPrint(String newTextToPrint) {
		eSet(IntentDocumentPackage.Literals.INTENT_REFERENCE_INSTRUCTION__TEXT_TO_PRINT, newTextToPrint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IntentReference.class) {
			switch (derivedFeatureID) {
				case IntentDocumentPackage.INTENT_REFERENCE_INSTRUCTION__INTENT_HREF:
					return IntentDocumentPackage.INTENT_REFERENCE__INTENT_HREF;
				case IntentDocumentPackage.INTENT_REFERENCE_INSTRUCTION__REFERENCED_ELEMENT:
					return IntentDocumentPackage.INTENT_REFERENCE__REFERENCED_ELEMENT;
				default:
					return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IntentReference.class) {
			switch (baseFeatureID) {
				case IntentDocumentPackage.INTENT_REFERENCE__INTENT_HREF:
					return IntentDocumentPackage.INTENT_REFERENCE_INSTRUCTION__INTENT_HREF;
				case IntentDocumentPackage.INTENT_REFERENCE__REFERENCED_ELEMENT:
					return IntentDocumentPackage.INTENT_REFERENCE_INSTRUCTION__REFERENCED_ELEMENT;
				default:
					return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //IntentReferenceInstructionImpl
