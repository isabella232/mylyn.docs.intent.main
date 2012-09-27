/**
 */
package org.eclipse.mylyn.docs.intent.core.genericunit.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReference;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReferenceInstruction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intent Reference Instruction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.impl.IntentReferenceInstructionImpl#getIntentHref <em>Intent Href</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.impl.IntentReferenceInstructionImpl#getReferencedElement <em>Referenced Element</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.impl.IntentReferenceInstructionImpl#getTextToPrint <em>Text To Print</em>}</li>
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
		return GenericUnitPackage.Literals.INTENT_REFERENCE_INSTRUCTION;
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
	public String getTextToPrint() {
		return (String)eGet(GenericUnitPackage.Literals.INTENT_REFERENCE_INSTRUCTION__TEXT_TO_PRINT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextToPrint(String newTextToPrint) {
		eSet(GenericUnitPackage.Literals.INTENT_REFERENCE_INSTRUCTION__TEXT_TO_PRINT, newTextToPrint);
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
				case GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__INTENT_HREF:
					return GenericUnitPackage.INTENT_REFERENCE__INTENT_HREF;
				case GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__REFERENCED_ELEMENT:
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
					return GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__INTENT_HREF;
				case GenericUnitPackage.INTENT_REFERENCE__REFERENCED_ELEMENT:
					return GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__REFERENCED_ELEMENT;
				default:
					return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //IntentReferenceInstructionImpl
