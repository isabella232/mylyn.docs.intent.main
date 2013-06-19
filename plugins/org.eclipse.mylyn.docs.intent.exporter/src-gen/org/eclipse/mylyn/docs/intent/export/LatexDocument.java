/**
 */
package org.eclipse.mylyn.docs.intent.export;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.markup.markup.Section;
import org.eclipse.mylyn.docs.intent.markup.markup.Container;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Latex Document</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getRoots <em>Roots</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getDocumentClass <em>Document Class</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#isReplaceSubSubSectionByPara <em>Replace Sub Sub Section By Para</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage#getLatexDocument()
 * @model
 * @generated
 */
public interface LatexDocument extends EObject {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage#getLatexDocument_Title()
	 * @model required="true"
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Authors</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authors</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authors</em>' attribute list.
	 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage#getLatexDocument_Authors()
	 * @model required="true"
	 * @generated
	 */
	EList<String> getAuthors();

	/**
	 * Returns the value of the '<em><b>Roots</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.docs.intent.core.document.IntentSection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roots</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roots</em>' reference list.
	 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage#getLatexDocument_Roots()
	 * @model
	 * @generated
	 */
	EList<IntentSection> getRoots();

	/**
	 * Returns the value of the '<em><b>Document Class</b></em>' attribute.
	 * The default value is <code>"book"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Document Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Document Class</em>' attribute.
	 * @see #setDocumentClass(String)
	 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage#getLatexDocument_DocumentClass()
	 * @model default="book" required="true"
	 * @generated
	 */
	String getDocumentClass();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getDocumentClass <em>Document Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Document Class</em>' attribute.
	 * @see #getDocumentClass()
	 * @generated
	 */
	void setDocumentClass(String value);

	/**
	 * Returns the value of the '<em><b>Replace Sub Sub Section By Para</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replace Sub Sub Section By Para</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Replace Sub Sub Section By Para</em>' attribute.
	 * @see #setReplaceSubSubSectionByPara(boolean)
	 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage#getLatexDocument_ReplaceSubSubSectionByPara()
	 * @model
	 * @generated
	 */
	boolean isReplaceSubSubSectionByPara();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#isReplaceSubSubSectionByPara <em>Replace Sub Sub Section By Para</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Replace Sub Sub Section By Para</em>' attribute.
	 * @see #isReplaceSubSubSectionByPara()
	 * @generated
	 */
	void setReplaceSubSubSectionByPara(boolean value);

} // LatexDocument
