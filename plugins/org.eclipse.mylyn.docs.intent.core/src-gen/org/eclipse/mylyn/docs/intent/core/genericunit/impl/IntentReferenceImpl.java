/**
 */
package org.eclipse.mylyn.docs.intent.core.genericunit.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intent Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.impl.IntentReferenceImpl#getIntentHref <em>Intent Href</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.impl.IntentReferenceImpl#getReferencedElement <em>Referenced Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IntentReferenceImpl extends CDOObjectImpl implements IntentReference {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntentReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenericUnitPackage.Literals.INTENT_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
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

} //IntentReferenceImpl
