/**
 */
package org.eclipse.mylyn.docs.intent.core.genericunit;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intent Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.IntentReference#getIntentHref <em>Intent Href</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.IntentReference#getReferencedElement <em>Referenced Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage#getIntentReference()
 * @model abstract="true"
 * @extends CDOObject
 * @generated
 */
public interface IntentReference extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Intent Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intent Href</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intent Href</em>' attribute.
	 * @see #setIntentHref(String)
	 * @see org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage#getIntentReference_IntentHref()
	 * @model
	 * @generated
	 */
	String getIntentHref();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.genericunit.IntentReference#getIntentHref <em>Intent Href</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intent Href</em>' attribute.
	 * @see #getIntentHref()
	 * @generated
	 */
	void setIntentHref(String value);

	/**
	 * Returns the value of the '<em><b>Referenced Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Element</em>' reference.
	 * @see #setReferencedElement(EObject)
	 * @see org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage#getIntentReference_ReferencedElement()
	 * @model
	 * @generated
	 */
	EObject getReferencedElement();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.genericunit.IntentReference#getReferencedElement <em>Referenced Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Element</em>' reference.
	 * @see #getReferencedElement()
	 * @generated
	 */
	void setReferencedElement(EObject value);

} // IntentReference
