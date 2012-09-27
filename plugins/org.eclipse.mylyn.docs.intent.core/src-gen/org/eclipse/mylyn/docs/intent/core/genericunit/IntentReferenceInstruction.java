/**
 */
package org.eclipse.mylyn.docs.intent.core.genericunit;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intent Reference Instruction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.genericunit.IntentReferenceInstruction#getTextToPrint <em>Text To Print</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage#getIntentReferenceInstruction()
 * @model
 * @generated
 */
public interface IntentReferenceInstruction extends UnitInstruction, IntentReference {
	/**
	 * Returns the value of the '<em><b>Text To Print</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text To Print</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text To Print</em>' attribute.
	 * @see #setTextToPrint(String)
	 * @see org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage#getIntentReferenceInstruction_TextToPrint()
	 * @model
	 * @generated
	 */
	String getTextToPrint();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.genericunit.IntentReferenceInstruction#getTextToPrint <em>Text To Print</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text To Print</em>' attribute.
	 * @see #getTextToPrint()
	 * @generated
	 */
	void setTextToPrint(String value);

} // IntentReferenceInstruction
