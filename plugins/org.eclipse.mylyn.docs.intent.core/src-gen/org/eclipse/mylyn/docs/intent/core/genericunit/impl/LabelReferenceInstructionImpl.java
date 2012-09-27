/*******************************************************************************
 * Copyright (c) 2010, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.core.genericunit.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReference;
import org.eclipse.mylyn.docs.intent.core.genericunit.LabelReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.genericunit.TypeLabel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Label Reference Instruction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.impl.LabelReferenceInstructionImpl#getIntentHref <em>Intent Href</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.impl.LabelReferenceInstructionImpl#getReferencedElement <em>Referenced Element</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.impl.LabelReferenceInstructionImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabelReferenceInstructionImpl extends UnitInstructionImpl implements LabelReferenceInstruction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LabelReferenceInstructionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenericUnitPackage.Literals.LABEL_REFERENCE_INSTRUCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIntentHref() {
		return (String)eGet(GenericUnitPackage.Literals.INTENT_REFERENCE__INTENT_HREF, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntentHref(String newIntentHref) {
		eSet(GenericUnitPackage.Literals.INTENT_REFERENCE__INTENT_HREF, newIntentHref);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getReferencedElement() {
		return (EObject)eGet(GenericUnitPackage.Literals.INTENT_REFERENCE__REFERENCED_ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedElement(EObject newReferencedElement) {
		eSet(GenericUnitPackage.Literals.INTENT_REFERENCE__REFERENCED_ELEMENT, newReferencedElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeLabel getType() {
		return (TypeLabel)eGet(GenericUnitPackage.Literals.LABEL_REFERENCE_INSTRUCTION__TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeLabel newType) {
		eSet(GenericUnitPackage.Literals.LABEL_REFERENCE_INSTRUCTION__TYPE, newType);
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
				case GenericUnitPackage.LABEL_REFERENCE_INSTRUCTION__INTENT_HREF:
					return GenericUnitPackage.INTENT_REFERENCE__INTENT_HREF;
				case GenericUnitPackage.LABEL_REFERENCE_INSTRUCTION__REFERENCED_ELEMENT:
					return GenericUnitPackage.INTENT_REFERENCE__REFERENCED_ELEMENT;
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
				case GenericUnitPackage.INTENT_REFERENCE__INTENT_HREF:
					return GenericUnitPackage.LABEL_REFERENCE_INSTRUCTION__INTENT_HREF;
				case GenericUnitPackage.INTENT_REFERENCE__REFERENCED_ELEMENT:
					return GenericUnitPackage.LABEL_REFERENCE_INSTRUCTION__REFERENCED_ELEMENT;
				default:
					return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //LabelReferenceInstructionImpl
