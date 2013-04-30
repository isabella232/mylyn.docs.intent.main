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
package org.eclipse.mylyn.docs.intent.core.document;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;

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
 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory
 * @model kind="package"
 * @generated
 */
public interface IntentDocumentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "document";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/intent/intentdocument/0.8";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "intentDocument";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IntentDocumentPackage eINSTANCE = org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentGenericElementImpl <em>Intent Generic Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentGenericElementImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentGenericElement()
	 * @generated
	 */
	int INTENT_GENERIC_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_GENERIC_ELEMENT__COMPILATION_STATUS = 0;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_GENERIC_ELEMENT__INDEX_ENTRY = 1;

	/**
	 * The number of structural features of the '<em>Intent Generic Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_GENERIC_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentStructuredElementImpl <em>Intent Structured Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentStructuredElementImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentStructuredElement()
	 * @generated
	 */
	int INTENT_STRUCTURED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_STRUCTURED_ELEMENT__CONTENT = MarkupPackage.SECTION__CONTENT;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_STRUCTURED_ELEMENT__ATTRIBUTES = MarkupPackage.SECTION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Title</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_STRUCTURED_ELEMENT__TITLE = MarkupPackage.SECTION__TITLE;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_STRUCTURED_ELEMENT__LEVEL = MarkupPackage.SECTION__LEVEL;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_STRUCTURED_ELEMENT__COMPILATION_STATUS = MarkupPackage.SECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_STRUCTURED_ELEMENT__INDEX_ENTRY = MarkupPackage.SECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Complete Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_STRUCTURED_ELEMENT__COMPLETE_LEVEL = MarkupPackage.SECTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Intent Structured Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_STRUCTURED_ELEMENT_FEATURE_COUNT = MarkupPackage.SECTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentSectionImpl <em>Intent Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentSectionImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentSection()
	 * @generated
	 */
	int INTENT_SECTION = 2;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__CONTENT = INTENT_STRUCTURED_ELEMENT__CONTENT;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__ATTRIBUTES = INTENT_STRUCTURED_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Title</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__TITLE = INTENT_STRUCTURED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__LEVEL = INTENT_STRUCTURED_ELEMENT__LEVEL;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__COMPILATION_STATUS = INTENT_STRUCTURED_ELEMENT__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__INDEX_ENTRY = INTENT_STRUCTURED_ELEMENT__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Complete Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__COMPLETE_LEVEL = INTENT_STRUCTURED_ELEMENT__COMPLETE_LEVEL;

	/**
	 * The feature id for the '<em><b>Intent Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__INTENT_CONTENT = INTENT_STRUCTURED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sub Sections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__SUB_SECTIONS = INTENT_STRUCTURED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Units</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__UNITS = INTENT_STRUCTURED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Description Units</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__DESCRIPTION_UNITS = INTENT_STRUCTURED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Modeling Units</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION__MODELING_UNITS = INTENT_STRUCTURED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Intent Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_SECTION_FEATURE_COUNT = INTENT_STRUCTURED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentImpl <em>Intent Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentDocument()
	 * @generated
	 */
	int INTENT_DOCUMENT = 3;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__CONTENT = INTENT_SECTION__CONTENT;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__ATTRIBUTES = INTENT_SECTION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Title</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__TITLE = INTENT_SECTION__TITLE;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__LEVEL = INTENT_SECTION__LEVEL;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__COMPILATION_STATUS = INTENT_SECTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__INDEX_ENTRY = INTENT_SECTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Complete Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__COMPLETE_LEVEL = INTENT_SECTION__COMPLETE_LEVEL;

	/**
	 * The feature id for the '<em><b>Intent Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__INTENT_CONTENT = INTENT_SECTION__INTENT_CONTENT;

	/**
	 * The feature id for the '<em><b>Sub Sections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__SUB_SECTIONS = INTENT_SECTION__SUB_SECTIONS;

	/**
	 * The feature id for the '<em><b>Units</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__UNITS = INTENT_SECTION__UNITS;

	/**
	 * The feature id for the '<em><b>Description Units</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__DESCRIPTION_UNITS = INTENT_SECTION__DESCRIPTION_UNITS;

	/**
	 * The feature id for the '<em><b>Modeling Units</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT__MODELING_UNITS = INTENT_SECTION__MODELING_UNITS;

	/**
	 * The number of structural features of the '<em>Intent Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_DOCUMENT_FEATURE_COUNT = INTENT_SECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.GenericUnitImpl <em>Generic Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.GenericUnitImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getGenericUnit()
	 * @generated
	 */
	int GENERIC_UNIT = 4;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_UNIT__COMPILATION_STATUS = INTENT_GENERIC_ELEMENT__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_UNIT__INDEX_ENTRY = INTENT_GENERIC_ELEMENT__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Instructions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_UNIT__INSTRUCTIONS = INTENT_GENERIC_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_UNIT__NAME = INTENT_GENERIC_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_UNIT_FEATURE_COUNT = INTENT_GENERIC_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.UnitInstructionImpl <em>Unit Instruction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.UnitInstructionImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getUnitInstruction()
	 * @generated
	 */
	int UNIT_INSTRUCTION = 5;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_INSTRUCTION__COMPILATION_STATUS = INTENT_GENERIC_ELEMENT__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_INSTRUCTION__INDEX_ENTRY = INTENT_GENERIC_ELEMENT__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_INSTRUCTION__UNIT = INTENT_GENERIC_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_INSTRUCTION__LINE_BREAK = INTENT_GENERIC_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unit Instruction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_INSTRUCTION_FEATURE_COUNT = INTENT_GENERIC_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceInstructionImpl <em>Intent Reference Instruction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceInstructionImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentReferenceInstruction()
	 * @generated
	 */
	int INTENT_REFERENCE_INSTRUCTION = 6;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE_INSTRUCTION__COMPILATION_STATUS = UNIT_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE_INSTRUCTION__INDEX_ENTRY = UNIT_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE_INSTRUCTION__UNIT = UNIT_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE_INSTRUCTION__LINE_BREAK = UNIT_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Intent Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE_INSTRUCTION__INTENT_HREF = UNIT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE_INSTRUCTION__REFERENCED_ELEMENT = UNIT_INSTRUCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Text To Print</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE_INSTRUCTION__TEXT_TO_PRINT = UNIT_INSTRUCTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Intent Reference Instruction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE_INSTRUCTION_FEATURE_COUNT = UNIT_INSTRUCTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.LabelDeclarationImpl <em>Label Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.LabelDeclarationImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getLabelDeclaration()
	 * @generated
	 */
	int LABEL_DECLARATION = 7;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_DECLARATION__COMPILATION_STATUS = UNIT_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_DECLARATION__INDEX_ENTRY = UNIT_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_DECLARATION__UNIT = UNIT_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_DECLARATION__LINE_BREAK = UNIT_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Label Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_DECLARATION__LABEL_VALUE = UNIT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text To Print</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_DECLARATION__TEXT_TO_PRINT = UNIT_INSTRUCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_DECLARATION__TYPE = UNIT_INSTRUCTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Label Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_DECLARATION_FEATURE_COUNT = UNIT_INSTRUCTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.LabelReferenceInstructionImpl <em>Label Reference Instruction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.LabelReferenceInstructionImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getLabelReferenceInstruction()
	 * @generated
	 */
	int LABEL_REFERENCE_INSTRUCTION = 8;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_REFERENCE_INSTRUCTION__COMPILATION_STATUS = UNIT_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_REFERENCE_INSTRUCTION__INDEX_ENTRY = UNIT_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_REFERENCE_INSTRUCTION__UNIT = UNIT_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_REFERENCE_INSTRUCTION__LINE_BREAK = UNIT_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Intent Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_REFERENCE_INSTRUCTION__INTENT_HREF = UNIT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_REFERENCE_INSTRUCTION__REFERENCED_ELEMENT = UNIT_INSTRUCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_REFERENCE_INSTRUCTION__TYPE = UNIT_INSTRUCTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Label Reference Instruction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_REFERENCE_INSTRUCTION_FEATURE_COUNT = UNIT_INSTRUCTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceImpl <em>Intent Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceImpl
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentReference()
	 * @generated
	 */
	int INTENT_REFERENCE = 9;

	/**
	 * The feature id for the '<em><b>Intent Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE__INTENT_HREF = 0;

	/**
	 * The feature id for the '<em><b>Referenced Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE__REFERENCED_ELEMENT = 1;

	/**
	 * The number of structural features of the '<em>Intent Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.document.TypeLabel <em>Type Label</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.document.TypeLabel
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getTypeLabel()
	 * @generated
	 */
	int TYPE_LABEL = 10;

	/**
	 * The meta object id for the '<em>URI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.URI
	 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getURI()
	 * @generated
	 */
	int URI = 11;

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement <em>Intent Generic Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intent Generic Element</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement
	 * @generated
	 */
	EClass getIntentGenericElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement#getCompilationStatus <em>Compilation Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Compilation Status</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement#getCompilationStatus()
	 * @see #getIntentGenericElement()
	 * @generated
	 */
	EReference getIntentGenericElement_CompilationStatus();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement#getIndexEntry <em>Index Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Index Entry</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement#getIndexEntry()
	 * @see #getIntentGenericElement()
	 * @generated
	 */
	EReference getIntentGenericElement_IndexEntry();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement <em>Intent Structured Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intent Structured Element</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement
	 * @generated
	 */
	EClass getIntentStructuredElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement#getCompleteLevel <em>Complete Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Complete Level</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement#getCompleteLevel()
	 * @see #getIntentStructuredElement()
	 * @generated
	 */
	EAttribute getIntentStructuredElement_CompleteLevel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection <em>Intent Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intent Section</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentSection
	 * @generated
	 */
	EClass getIntentSection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getIntentContent <em>Intent Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Intent Content</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentSection#getIntentContent()
	 * @see #getIntentSection()
	 * @generated
	 */
	EReference getIntentSection_IntentContent();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getSubSections <em>Sub Sections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Sections</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentSection#getSubSections()
	 * @see #getIntentSection()
	 * @generated
	 */
	EReference getIntentSection_SubSections();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getUnits <em>Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Units</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentSection#getUnits()
	 * @see #getIntentSection()
	 * @generated
	 */
	EReference getIntentSection_Units();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getDescriptionUnits <em>Description Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Description Units</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentSection#getDescriptionUnits()
	 * @see #getIntentSection()
	 * @generated
	 */
	EReference getIntentSection_DescriptionUnits();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.mylyn.docs.intent.core.document.IntentSection#getModelingUnits <em>Modeling Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Modeling Units</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentSection#getModelingUnits()
	 * @see #getIntentSection()
	 * @generated
	 */
	EReference getIntentSection_ModelingUnits();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.IntentDocument <em>Intent Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intent Document</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocument
	 * @generated
	 */
	EClass getIntentDocument();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.GenericUnit <em>Generic Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Unit</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.GenericUnit
	 * @generated
	 */
	EClass getGenericUnit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.core.document.GenericUnit#getInstructions <em>Instructions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Instructions</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.GenericUnit#getInstructions()
	 * @see #getGenericUnit()
	 * @generated
	 */
	EReference getGenericUnit_Instructions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.document.GenericUnit#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.GenericUnit#getName()
	 * @see #getGenericUnit()
	 * @generated
	 */
	EAttribute getGenericUnit_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.UnitInstruction <em>Unit Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unit Instruction</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.UnitInstruction
	 * @generated
	 */
	EClass getUnitInstruction();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.mylyn.docs.intent.core.document.UnitInstruction#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Unit</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.UnitInstruction#getUnit()
	 * @see #getUnitInstruction()
	 * @generated
	 */
	EReference getUnitInstruction_Unit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.document.UnitInstruction#isLineBreak <em>Line Break</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Break</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.UnitInstruction#isLineBreak()
	 * @see #getUnitInstruction()
	 * @generated
	 */
	EAttribute getUnitInstruction_LineBreak();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction <em>Intent Reference Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intent Reference Instruction</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction
	 * @generated
	 */
	EClass getIntentReferenceInstruction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction#getTextToPrint <em>Text To Print</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text To Print</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction#getTextToPrint()
	 * @see #getIntentReferenceInstruction()
	 * @generated
	 */
	EAttribute getIntentReferenceInstruction_TextToPrint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration <em>Label Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label Declaration</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration
	 * @generated
	 */
	EClass getLabelDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration#getLabelValue <em>Label Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label Value</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration#getLabelValue()
	 * @see #getLabelDeclaration()
	 * @generated
	 */
	EAttribute getLabelDeclaration_LabelValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration#getTextToPrint <em>Text To Print</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text To Print</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration#getTextToPrint()
	 * @see #getLabelDeclaration()
	 * @generated
	 */
	EAttribute getLabelDeclaration_TextToPrint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration#getType()
	 * @see #getLabelDeclaration()
	 * @generated
	 */
	EAttribute getLabelDeclaration_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.LabelReferenceInstruction <em>Label Reference Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label Reference Instruction</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.LabelReferenceInstruction
	 * @generated
	 */
	EClass getLabelReferenceInstruction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.document.LabelReferenceInstruction#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.LabelReferenceInstruction#getType()
	 * @see #getLabelReferenceInstruction()
	 * @generated
	 */
	EAttribute getLabelReferenceInstruction_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.document.IntentReference <em>Intent Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intent Reference</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentReference
	 * @generated
	 */
	EClass getIntentReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.document.IntentReference#getIntentHref <em>Intent Href</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Intent Href</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentReference#getIntentHref()
	 * @see #getIntentReference()
	 * @generated
	 */
	EAttribute getIntentReference_IntentHref();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.mylyn.docs.intent.core.document.IntentReference#getReferencedElement <em>Referenced Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Element</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentReference#getReferencedElement()
	 * @see #getIntentReference()
	 * @generated
	 */
	EReference getIntentReference_ReferencedElement();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.mylyn.docs.intent.core.document.TypeLabel <em>Type Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Label</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.document.TypeLabel
	 * @generated
	 */
	EEnum getTypeLabel();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.URI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see org.eclipse.emf.common.util.URI
	 * @model instanceClass="org.eclipse.emf.common.util.URI"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IntentDocumentFactory getIntentDocumentFactory();

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
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentGenericElementImpl <em>Intent Generic Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentGenericElementImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentGenericElement()
		 * @generated
		 */
		EClass INTENT_GENERIC_ELEMENT = eINSTANCE.getIntentGenericElement();

		/**
		 * The meta object literal for the '<em><b>Compilation Status</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT_GENERIC_ELEMENT__COMPILATION_STATUS = eINSTANCE
				.getIntentGenericElement_CompilationStatus();

		/**
		 * The meta object literal for the '<em><b>Index Entry</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT_GENERIC_ELEMENT__INDEX_ENTRY = eINSTANCE.getIntentGenericElement_IndexEntry();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentStructuredElementImpl <em>Intent Structured Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentStructuredElementImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentStructuredElement()
		 * @generated
		 */
		EClass INTENT_STRUCTURED_ELEMENT = eINSTANCE.getIntentStructuredElement();

		/**
		 * The meta object literal for the '<em><b>Complete Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENT_STRUCTURED_ELEMENT__COMPLETE_LEVEL = eINSTANCE
				.getIntentStructuredElement_CompleteLevel();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentSectionImpl <em>Intent Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentSectionImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentSection()
		 * @generated
		 */
		EClass INTENT_SECTION = eINSTANCE.getIntentSection();

		/**
		 * The meta object literal for the '<em><b>Intent Content</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT_SECTION__INTENT_CONTENT = eINSTANCE.getIntentSection_IntentContent();

		/**
		 * The meta object literal for the '<em><b>Sub Sections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT_SECTION__SUB_SECTIONS = eINSTANCE.getIntentSection_SubSections();

		/**
		 * The meta object literal for the '<em><b>Units</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT_SECTION__UNITS = eINSTANCE.getIntentSection_Units();

		/**
		 * The meta object literal for the '<em><b>Description Units</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT_SECTION__DESCRIPTION_UNITS = eINSTANCE.getIntentSection_DescriptionUnits();

		/**
		 * The meta object literal for the '<em><b>Modeling Units</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT_SECTION__MODELING_UNITS = eINSTANCE.getIntentSection_ModelingUnits();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentImpl <em>Intent Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentDocument()
		 * @generated
		 */
		EClass INTENT_DOCUMENT = eINSTANCE.getIntentDocument();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.GenericUnitImpl <em>Generic Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.GenericUnitImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getGenericUnit()
		 * @generated
		 */
		EClass GENERIC_UNIT = eINSTANCE.getGenericUnit();

		/**
		 * The meta object literal for the '<em><b>Instructions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_UNIT__INSTRUCTIONS = eINSTANCE.getGenericUnit_Instructions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERIC_UNIT__NAME = eINSTANCE.getGenericUnit_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.UnitInstructionImpl <em>Unit Instruction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.UnitInstructionImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getUnitInstruction()
		 * @generated
		 */
		EClass UNIT_INSTRUCTION = eINSTANCE.getUnitInstruction();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNIT_INSTRUCTION__UNIT = eINSTANCE.getUnitInstruction_Unit();

		/**
		 * The meta object literal for the '<em><b>Line Break</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIT_INSTRUCTION__LINE_BREAK = eINSTANCE.getUnitInstruction_LineBreak();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceInstructionImpl <em>Intent Reference Instruction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceInstructionImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentReferenceInstruction()
		 * @generated
		 */
		EClass INTENT_REFERENCE_INSTRUCTION = eINSTANCE.getIntentReferenceInstruction();

		/**
		 * The meta object literal for the '<em><b>Text To Print</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENT_REFERENCE_INSTRUCTION__TEXT_TO_PRINT = eINSTANCE
				.getIntentReferenceInstruction_TextToPrint();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.LabelDeclarationImpl <em>Label Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.LabelDeclarationImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getLabelDeclaration()
		 * @generated
		 */
		EClass LABEL_DECLARATION = eINSTANCE.getLabelDeclaration();

		/**
		 * The meta object literal for the '<em><b>Label Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_DECLARATION__LABEL_VALUE = eINSTANCE.getLabelDeclaration_LabelValue();

		/**
		 * The meta object literal for the '<em><b>Text To Print</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_DECLARATION__TEXT_TO_PRINT = eINSTANCE.getLabelDeclaration_TextToPrint();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_DECLARATION__TYPE = eINSTANCE.getLabelDeclaration_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.LabelReferenceInstructionImpl <em>Label Reference Instruction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.LabelReferenceInstructionImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getLabelReferenceInstruction()
		 * @generated
		 */
		EClass LABEL_REFERENCE_INSTRUCTION = eINSTANCE.getLabelReferenceInstruction();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_REFERENCE_INSTRUCTION__TYPE = eINSTANCE.getLabelReferenceInstruction_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceImpl <em>Intent Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentReferenceImpl
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getIntentReference()
		 * @generated
		 */
		EClass INTENT_REFERENCE = eINSTANCE.getIntentReference();

		/**
		 * The meta object literal for the '<em><b>Intent Href</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENT_REFERENCE__INTENT_HREF = eINSTANCE.getIntentReference_IntentHref();

		/**
		 * The meta object literal for the '<em><b>Referenced Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT_REFERENCE__REFERENCED_ELEMENT = eINSTANCE.getIntentReference_ReferencedElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.document.TypeLabel <em>Type Label</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.document.TypeLabel
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getTypeLabel()
		 * @generated
		 */
		EEnum TYPE_LABEL = eINSTANCE.getTypeLabel();

		/**
		 * The meta object literal for the '<em>URI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.util.URI
		 * @see org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();

	}

} //IntentDocumentPackage
