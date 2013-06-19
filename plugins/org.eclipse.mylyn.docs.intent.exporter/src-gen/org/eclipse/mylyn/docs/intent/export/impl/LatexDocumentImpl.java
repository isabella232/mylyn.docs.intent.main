/**
 */
package org.eclipse.mylyn.docs.intent.export.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.export.ExportPackage;
import org.eclipse.mylyn.docs.intent.export.LatexDocument;

import org.eclipse.mylyn.docs.intent.markup.markup.Section;
import org.eclipse.mylyn.docs.intent.markup.markup.Container;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Latex Document</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.impl.LatexDocumentImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.impl.LatexDocumentImpl#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.impl.LatexDocumentImpl#getRoots <em>Roots</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.impl.LatexDocumentImpl#getDocumentClass <em>Document Class</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.export.impl.LatexDocumentImpl#isReplaceSubSubSectionByPara <em>Replace Sub Sub Section By Para</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LatexDocumentImpl extends EObjectImpl implements LatexDocument {
	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAuthors() <em>Authors</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthors()
	 * @generated
	 * @ordered
	 */
	protected EList<String> authors;

	/**
	 * The cached value of the '{@link #getRoots() <em>Roots</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoots()
	 * @generated
	 * @ordered
	 */
	protected EList<IntentSection> roots;

	/**
	 * The default value of the '{@link #getDocumentClass() <em>Document Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentClass()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENT_CLASS_EDEFAULT = "book";

	/**
	 * The cached value of the '{@link #getDocumentClass() <em>Document Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentClass()
	 * @generated
	 * @ordered
	 */
	protected String documentClass = DOCUMENT_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #isReplaceSubSubSectionByPara() <em>Replace Sub Sub Section By Para</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReplaceSubSubSectionByPara()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REPLACE_SUB_SUB_SECTION_BY_PARA_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReplaceSubSubSectionByPara() <em>Replace Sub Sub Section By Para</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReplaceSubSubSectionByPara()
	 * @generated
	 * @ordered
	 */
	protected boolean replaceSubSubSectionByPara = REPLACE_SUB_SUB_SECTION_BY_PARA_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LatexDocumentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExportPackage.Literals.LATEX_DOCUMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.LATEX_DOCUMENT__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAuthors() {
		if (authors == null) {
			authors = new EDataTypeUniqueEList<String>(String.class, this, ExportPackage.LATEX_DOCUMENT__AUTHORS);
		}
		return authors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IntentSection> getRoots() {
		if (roots == null) {
			roots = new EObjectResolvingEList<IntentSection>(IntentSection.class, this, ExportPackage.LATEX_DOCUMENT__ROOTS);
		}
		return roots;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDocumentClass() {
		return documentClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentClass(String newDocumentClass) {
		String oldDocumentClass = documentClass;
		documentClass = newDocumentClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.LATEX_DOCUMENT__DOCUMENT_CLASS, oldDocumentClass, documentClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReplaceSubSubSectionByPara() {
		return replaceSubSubSectionByPara;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReplaceSubSubSectionByPara(boolean newReplaceSubSubSectionByPara) {
		boolean oldReplaceSubSubSectionByPara = replaceSubSubSectionByPara;
		replaceSubSubSectionByPara = newReplaceSubSubSectionByPara;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.LATEX_DOCUMENT__REPLACE_SUB_SUB_SECTION_BY_PARA, oldReplaceSubSubSectionByPara, replaceSubSubSectionByPara));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExportPackage.LATEX_DOCUMENT__TITLE:
				return getTitle();
			case ExportPackage.LATEX_DOCUMENT__AUTHORS:
				return getAuthors();
			case ExportPackage.LATEX_DOCUMENT__ROOTS:
				return getRoots();
			case ExportPackage.LATEX_DOCUMENT__DOCUMENT_CLASS:
				return getDocumentClass();
			case ExportPackage.LATEX_DOCUMENT__REPLACE_SUB_SUB_SECTION_BY_PARA:
				return isReplaceSubSubSectionByPara();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExportPackage.LATEX_DOCUMENT__TITLE:
				setTitle((String)newValue);
				return;
			case ExportPackage.LATEX_DOCUMENT__AUTHORS:
				getAuthors().clear();
				getAuthors().addAll((Collection<? extends String>)newValue);
				return;
			case ExportPackage.LATEX_DOCUMENT__ROOTS:
				getRoots().clear();
				getRoots().addAll((Collection<? extends IntentSection>)newValue);
				return;
			case ExportPackage.LATEX_DOCUMENT__DOCUMENT_CLASS:
				setDocumentClass((String)newValue);
				return;
			case ExportPackage.LATEX_DOCUMENT__REPLACE_SUB_SUB_SECTION_BY_PARA:
				setReplaceSubSubSectionByPara((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ExportPackage.LATEX_DOCUMENT__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case ExportPackage.LATEX_DOCUMENT__AUTHORS:
				getAuthors().clear();
				return;
			case ExportPackage.LATEX_DOCUMENT__ROOTS:
				getRoots().clear();
				return;
			case ExportPackage.LATEX_DOCUMENT__DOCUMENT_CLASS:
				setDocumentClass(DOCUMENT_CLASS_EDEFAULT);
				return;
			case ExportPackage.LATEX_DOCUMENT__REPLACE_SUB_SUB_SECTION_BY_PARA:
				setReplaceSubSubSectionByPara(REPLACE_SUB_SUB_SECTION_BY_PARA_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExportPackage.LATEX_DOCUMENT__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case ExportPackage.LATEX_DOCUMENT__AUTHORS:
				return authors != null && !authors.isEmpty();
			case ExportPackage.LATEX_DOCUMENT__ROOTS:
				return roots != null && !roots.isEmpty();
			case ExportPackage.LATEX_DOCUMENT__DOCUMENT_CLASS:
				return DOCUMENT_CLASS_EDEFAULT == null ? documentClass != null : !DOCUMENT_CLASS_EDEFAULT.equals(documentClass);
			case ExportPackage.LATEX_DOCUMENT__REPLACE_SUB_SUB_SECTION_BY_PARA:
				return replaceSubSubSectionByPara != REPLACE_SUB_SUB_SECTION_BY_PARA_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (title: ");
		result.append(title);
		result.append(", authors: ");
		result.append(authors);
		result.append(", documentClass: ");
		result.append(documentClass);
		result.append(", replaceSubSubSectionByPara: ");
		result.append(replaceSubSubSectionByPara);
		result.append(')');
		return result.toString();
	}

} //LatexDocumentImpl
