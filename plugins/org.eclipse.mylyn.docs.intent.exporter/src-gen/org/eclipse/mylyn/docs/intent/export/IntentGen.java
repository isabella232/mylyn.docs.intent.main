/**
 */
package org.eclipse.mylyn.docs.intent.export;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intent Gen</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.IntentGen#getDocuments <em>Documents</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage#getIntentGen()
 * @model
 * @generated
 */
public interface IntentGen extends EObject {
	/**
	 * Returns the value of the '<em><b>Documents</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.docs.intent.export.LatexDocument}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documents</em>' containment reference list.
	 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage#getIntentGen_Documents()
	 * @model containment="true"
	 * @generated
	 */
	EList<LatexDocument> getDocuments();

} // IntentGen
