/**
 */
package org.eclipse.mylyn.docs.intent.export;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.mylyn.docs.intent.export.ExportFactory
 * @model kind="package"
 * @generated
 */
public interface ExportPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "export";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/intent/document/export/0.8";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "export";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExportPackage eINSTANCE = org.eclipse.mylyn.docs.intent.export.impl.ExportPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.export.impl.IntentGenImpl <em>Intent Gen</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.export.impl.IntentGenImpl
	 * @see org.eclipse.mylyn.docs.intent.export.impl.ExportPackageImpl#getIntentGen()
	 * @generated
	 */
	int INTENT_GEN = 0;

	/**
	 * The feature id for the '<em><b>Documents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_GEN__DOCUMENTS = 0;

	/**
	 * The number of structural features of the '<em>Intent Gen</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_GEN_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.export.impl.LatexDocumentImpl <em>Latex Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.export.impl.LatexDocumentImpl
	 * @see org.eclipse.mylyn.docs.intent.export.impl.ExportPackageImpl#getLatexDocument()
	 * @generated
	 */
	int LATEX_DOCUMENT = 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATEX_DOCUMENT__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATEX_DOCUMENT__AUTHORS = 1;

	/**
	 * The feature id for the '<em><b>Roots</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATEX_DOCUMENT__ROOTS = 2;

	/**
	 * The feature id for the '<em><b>Document Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATEX_DOCUMENT__DOCUMENT_CLASS = 3;

	/**
	 * The feature id for the '<em><b>Replace Sub Sub Section By Para</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATEX_DOCUMENT__REPLACE_SUB_SUB_SECTION_BY_PARA = 4;

	/**
	 * The number of structural features of the '<em>Latex Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATEX_DOCUMENT_FEATURE_COUNT = 5;


	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.export.IntentGen <em>Intent Gen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intent Gen</em>'.
	 * @see org.eclipse.mylyn.docs.intent.export.IntentGen
	 * @generated
	 */
	EClass getIntentGen();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.export.IntentGen#getDocuments <em>Documents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documents</em>'.
	 * @see org.eclipse.mylyn.docs.intent.export.IntentGen#getDocuments()
	 * @see #getIntentGen()
	 * @generated
	 */
	EReference getIntentGen_Documents();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.export.LatexDocument <em>Latex Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Latex Document</em>'.
	 * @see org.eclipse.mylyn.docs.intent.export.LatexDocument
	 * @generated
	 */
	EClass getLatexDocument();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see org.eclipse.mylyn.docs.intent.export.LatexDocument#getTitle()
	 * @see #getLatexDocument()
	 * @generated
	 */
	EAttribute getLatexDocument_Title();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getAuthors <em>Authors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Authors</em>'.
	 * @see org.eclipse.mylyn.docs.intent.export.LatexDocument#getAuthors()
	 * @see #getLatexDocument()
	 * @generated
	 */
	EAttribute getLatexDocument_Authors();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getRoots <em>Roots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Roots</em>'.
	 * @see org.eclipse.mylyn.docs.intent.export.LatexDocument#getRoots()
	 * @see #getLatexDocument()
	 * @generated
	 */
	EReference getLatexDocument_Roots();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#getDocumentClass <em>Document Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Document Class</em>'.
	 * @see org.eclipse.mylyn.docs.intent.export.LatexDocument#getDocumentClass()
	 * @see #getLatexDocument()
	 * @generated
	 */
	EAttribute getLatexDocument_DocumentClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.export.LatexDocument#isReplaceSubSubSectionByPara <em>Replace Sub Sub Section By Para</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Replace Sub Sub Section By Para</em>'.
	 * @see org.eclipse.mylyn.docs.intent.export.LatexDocument#isReplaceSubSubSectionByPara()
	 * @see #getLatexDocument()
	 * @generated
	 */
	EAttribute getLatexDocument_ReplaceSubSubSectionByPara();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExportFactory getExportFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.export.impl.IntentGenImpl <em>Intent Gen</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.export.impl.IntentGenImpl
		 * @see org.eclipse.mylyn.docs.intent.export.impl.ExportPackageImpl#getIntentGen()
		 * @generated
		 */
		EClass INTENT_GEN = eINSTANCE.getIntentGen();

		/**
		 * The meta object literal for the '<em><b>Documents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT_GEN__DOCUMENTS = eINSTANCE.getIntentGen_Documents();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.export.impl.LatexDocumentImpl <em>Latex Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.export.impl.LatexDocumentImpl
		 * @see org.eclipse.mylyn.docs.intent.export.impl.ExportPackageImpl#getLatexDocument()
		 * @generated
		 */
		EClass LATEX_DOCUMENT = eINSTANCE.getLatexDocument();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LATEX_DOCUMENT__TITLE = eINSTANCE.getLatexDocument_Title();

		/**
		 * The meta object literal for the '<em><b>Authors</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LATEX_DOCUMENT__AUTHORS = eINSTANCE.getLatexDocument_Authors();

		/**
		 * The meta object literal for the '<em><b>Roots</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LATEX_DOCUMENT__ROOTS = eINSTANCE.getLatexDocument_Roots();

		/**
		 * The meta object literal for the '<em><b>Document Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LATEX_DOCUMENT__DOCUMENT_CLASS = eINSTANCE.getLatexDocument_DocumentClass();

		/**
		 * The meta object literal for the '<em><b>Replace Sub Sub Section By Para</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LATEX_DOCUMENT__REPLACE_SUB_SUB_SECTION_BY_PARA = eINSTANCE.getLatexDocument_ReplaceSubSubSectionByPara();

	}

} //ExportPackage
