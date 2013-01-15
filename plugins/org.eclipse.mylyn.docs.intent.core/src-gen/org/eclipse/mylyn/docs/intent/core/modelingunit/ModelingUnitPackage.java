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
package org.eclipse.mylyn.docs.intent.core.modelingunit;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;

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
 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory
 * @model kind="package"
 * @generated
 */
public interface ModelingUnitPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "modelingunit";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/intent/modelingunit/0.7";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "intentMU";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelingUnitPackage eINSTANCE = org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.KeyValForAnnotationImpl <em>Key Val For Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.KeyValForAnnotationImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getKeyValForAnnotation()
	 * @generated
	 */
	int KEY_VAL_FOR_ANNOTATION = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_VAL_FOR_ANNOTATION__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_VAL_FOR_ANNOTATION__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Key Val For Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_VAL_FOR_ANNOTATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitImpl <em>Modeling Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getModelingUnit()
	 * @generated
	 */
	int MODELING_UNIT = 1;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT__COMPILATION_STATUS = GenericUnitPackage.GENERIC_UNIT__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT__INDEX_ENTRY = GenericUnitPackage.GENERIC_UNIT__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Instructions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT__INSTRUCTIONS = GenericUnitPackage.GENERIC_UNIT__INSTRUCTIONS;

	/**
	 * The feature id for the '<em><b>Unit Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT__UNIT_NAME = GenericUnitPackage.GENERIC_UNIT__UNIT_NAME;

	/**
	 * The feature id for the '<em><b>Used By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT__USED_BY = GenericUnitPackage.GENERIC_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>See Also</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT__SEE_ALSO = GenericUnitPackage.GENERIC_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT__RESOURCE = GenericUnitPackage.GENERIC_UNIT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Modeling Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT_FEATURE_COUNT = GenericUnitPackage.GENERIC_UNIT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitInstructionImpl <em>Instruction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitInstructionImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getModelingUnitInstruction()
	 * @generated
	 */
	int MODELING_UNIT_INSTRUCTION = 2;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT_INSTRUCTION__COMPILATION_STATUS = GenericUnitPackage.UNIT_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT_INSTRUCTION__INDEX_ENTRY = GenericUnitPackage.UNIT_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT_INSTRUCTION__UNIT = GenericUnitPackage.UNIT_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT_INSTRUCTION__LINE_BREAK = GenericUnitPackage.UNIT_INSTRUCTION__LINE_BREAK;

	/**
	 * The number of structural features of the '<em>Instruction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT_INSTRUCTION_FEATURE_COUNT = GenericUnitPackage.UNIT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ResourceReferenceImpl <em>Resource Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ResourceReferenceImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getResourceReference()
	 * @generated
	 */
	int RESOURCE_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Resource Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REFERENCE__RESOURCE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REFERENCE__DECLARATION = 1;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REFERENCE__LINE_BREAK = 2;

	/**
	 * The number of structural features of the '<em>Resource Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REFERENCE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitInstructionReferenceImpl <em>Instruction Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitInstructionReferenceImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getModelingUnitInstructionReference()
	 * @generated
	 */
	int MODELING_UNIT_INSTRUCTION_REFERENCE = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionReferenceImpl <em>Instanciation Instruction Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionReferenceImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getInstanciationInstructionReference()
	 * @generated
	 */
	int INSTANCIATION_INSTRUCTION_REFERENCE = 4;

	/**
	 * The feature id for the '<em><b>Instance Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Instanciation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCIATION = 1;

	/**
	 * The number of structural features of the '<em>Instanciation Instruction Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Intent Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT_INSTRUCTION_REFERENCE__INTENT_HREF = 0;

	/**
	 * The feature id for the '<em><b>Referenced Instruction</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT_INSTRUCTION_REFERENCE__REFERENCED_INSTRUCTION = 1;

	/**
	 * The number of structural features of the '<em>Instruction Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_UNIT_INSTRUCTION_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ResourceDeclarationImpl <em>Resource Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ResourceDeclarationImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getResourceDeclaration()
	 * @generated
	 */
	int RESOURCE_DECLARATION = 6;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DECLARATION__COMPILATION_STATUS = MODELING_UNIT_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DECLARATION__INDEX_ENTRY = MODELING_UNIT_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DECLARATION__UNIT = MODELING_UNIT_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DECLARATION__LINE_BREAK = MODELING_UNIT_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DECLARATION__URI = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DECLARATION__NAME = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DECLARATION__CONTENT_TYPE = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DECLARATION__CONTENT = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Resource Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DECLARATION_FEATURE_COUNT = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.IntentReferenceinModelingUnitImpl <em>Intent Referencein Modeling Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.IntentReferenceinModelingUnitImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getIntentReferenceinModelingUnit()
	 * @generated
	 */
	int INTENT_REFERENCEIN_MODELING_UNIT = 7;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCEIN_MODELING_UNIT__COMPILATION_STATUS = GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCEIN_MODELING_UNIT__INDEX_ENTRY = GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCEIN_MODELING_UNIT__UNIT = GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCEIN_MODELING_UNIT__LINE_BREAK = GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Intent Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCEIN_MODELING_UNIT__INTENT_HREF = GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__INTENT_HREF;

	/**
	 * The feature id for the '<em><b>Referenced Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCEIN_MODELING_UNIT__REFERENCED_ELEMENT = GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__REFERENCED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Text To Print</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCEIN_MODELING_UNIT__TEXT_TO_PRINT = GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION__TEXT_TO_PRINT;

	/**
	 * The number of structural features of the '<em>Intent Referencein Modeling Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_REFERENCEIN_MODELING_UNIT_FEATURE_COUNT = GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.LabelinModelingUnitImpl <em>Labelin Modeling Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.LabelinModelingUnitImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getLabelinModelingUnit()
	 * @generated
	 */
	int LABELIN_MODELING_UNIT = 8;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT__COMPILATION_STATUS = GenericUnitPackage.LABEL_DECLARATION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT__INDEX_ENTRY = GenericUnitPackage.LABEL_DECLARATION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT__UNIT = GenericUnitPackage.LABEL_DECLARATION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT__LINE_BREAK = GenericUnitPackage.LABEL_DECLARATION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Label Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT__LABEL_VALUE = GenericUnitPackage.LABEL_DECLARATION__LABEL_VALUE;

	/**
	 * The feature id for the '<em><b>Text To Print</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT__TEXT_TO_PRINT = GenericUnitPackage.LABEL_DECLARATION__TEXT_TO_PRINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT__TYPE = GenericUnitPackage.LABEL_DECLARATION__TYPE;

	/**
	 * The feature id for the '<em><b>Intent Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT__INTENT_HREF = GenericUnitPackage.LABEL_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT__REFERENCED_ELEMENT = GenericUnitPackage.LABEL_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Labelin Modeling Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELIN_MODELING_UNIT_FEATURE_COUNT = GenericUnitPackage.LABEL_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.AnnotationDeclarationImpl <em>Annotation Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.AnnotationDeclarationImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getAnnotationDeclaration()
	 * @generated
	 */
	int ANNOTATION_DECLARATION = 9;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DECLARATION__COMPILATION_STATUS = MODELING_UNIT_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DECLARATION__INDEX_ENTRY = MODELING_UNIT_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DECLARATION__UNIT = MODELING_UNIT_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DECLARATION__LINE_BREAK = MODELING_UNIT_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Intent Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DECLARATION__INTENT_HREF = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DECLARATION__REFERENCED_ELEMENT = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Annotation ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DECLARATION__ANNOTATION_ID = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DECLARATION__MAP = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Annotation Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DECLARATION_FEATURE_COUNT = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.TypeReferenceImpl <em>Type Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.TypeReferenceImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getTypeReference()
	 * @generated
	 */
	int TYPE_REFERENCE = 10;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REFERENCE__TYPE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Resolved Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REFERENCE__RESOLVED_TYPE = 1;

	/**
	 * The number of structural features of the '<em>Type Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanceLevelInstructionImpl <em>Instance Level Instruction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanceLevelInstructionImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getInstanceLevelInstruction()
	 * @generated
	 */
	int INSTANCE_LEVEL_INSTRUCTION = 11;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_LEVEL_INSTRUCTION__COMPILATION_STATUS = MODELING_UNIT_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_LEVEL_INSTRUCTION__INDEX_ENTRY = MODELING_UNIT_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_LEVEL_INSTRUCTION__UNIT = MODELING_UNIT_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_LEVEL_INSTRUCTION__LINE_BREAK = MODELING_UNIT_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Meta Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_LEVEL_INSTRUCTION__META_TYPE = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Instance Level Instruction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_LEVEL_INSTRUCTION_FEATURE_COUNT = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionImpl <em>Instanciation Instruction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getInstanciationInstruction()
	 * @generated
	 */
	int INSTANCIATION_INSTRUCTION = 12;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION__COMPILATION_STATUS = INSTANCE_LEVEL_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION__INDEX_ENTRY = INSTANCE_LEVEL_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION__UNIT = INSTANCE_LEVEL_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION__LINE_BREAK = INSTANCE_LEVEL_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Meta Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION__META_TYPE = INSTANCE_LEVEL_INSTRUCTION__META_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION__NAME = INSTANCE_LEVEL_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Structural Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION__STRUCTURAL_FEATURES = INSTANCE_LEVEL_INSTRUCTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Instanciation Instruction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATION_INSTRUCTION_FEATURE_COUNT = INSTANCE_LEVEL_INSTRUCTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.StructuralFeatureAffectationImpl <em>Structural Feature Affectation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.StructuralFeatureAffectationImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getStructuralFeatureAffectation()
	 * @generated
	 */
	int STRUCTURAL_FEATURE_AFFECTATION = 13;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_AFFECTATION__COMPILATION_STATUS = INSTANCE_LEVEL_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_AFFECTATION__INDEX_ENTRY = INSTANCE_LEVEL_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_AFFECTATION__UNIT = INSTANCE_LEVEL_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_AFFECTATION__LINE_BREAK = INSTANCE_LEVEL_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Meta Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_AFFECTATION__META_TYPE = INSTANCE_LEVEL_INSTRUCTION__META_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_AFFECTATION__NAME = INSTANCE_LEVEL_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Used Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_AFFECTATION__USED_OPERATOR = INSTANCE_LEVEL_INSTRUCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_AFFECTATION__VALUES = INSTANCE_LEVEL_INSTRUCTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Structural Feature Affectation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_AFFECTATION_FEATURE_COUNT = INSTANCE_LEVEL_INSTRUCTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ValueForStructuralFeatureImpl <em>Value For Structural Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ValueForStructuralFeatureImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getValueForStructuralFeature()
	 * @generated
	 */
	int VALUE_FOR_STRUCTURAL_FEATURE = 14;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FOR_STRUCTURAL_FEATURE__COMPILATION_STATUS = MODELING_UNIT_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FOR_STRUCTURAL_FEATURE__INDEX_ENTRY = MODELING_UNIT_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FOR_STRUCTURAL_FEATURE__UNIT = MODELING_UNIT_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FOR_STRUCTURAL_FEATURE__LINE_BREAK = MODELING_UNIT_INSTRUCTION__LINE_BREAK;

	/**
	 * The number of structural features of the '<em>Value For Structural Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.NativeValueForStructuralFeatureImpl <em>Native Value For Structural Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.NativeValueForStructuralFeatureImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getNativeValueForStructuralFeature()
	 * @generated
	 */
	int NATIVE_VALUE_FOR_STRUCTURAL_FEATURE = 15;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_VALUE_FOR_STRUCTURAL_FEATURE__COMPILATION_STATUS = VALUE_FOR_STRUCTURAL_FEATURE__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_VALUE_FOR_STRUCTURAL_FEATURE__INDEX_ENTRY = VALUE_FOR_STRUCTURAL_FEATURE__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_VALUE_FOR_STRUCTURAL_FEATURE__UNIT = VALUE_FOR_STRUCTURAL_FEATURE__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_VALUE_FOR_STRUCTURAL_FEATURE__LINE_BREAK = VALUE_FOR_STRUCTURAL_FEATURE__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_VALUE_FOR_STRUCTURAL_FEATURE__VALUE = VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Native Value For Structural Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT = VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.NewObjectValueForStructuralFeatureImpl <em>New Object Value For Structural Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.NewObjectValueForStructuralFeatureImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getNewObjectValueForStructuralFeature()
	 * @generated
	 */
	int NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE = 16;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE__COMPILATION_STATUS = VALUE_FOR_STRUCTURAL_FEATURE__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE__INDEX_ENTRY = VALUE_FOR_STRUCTURAL_FEATURE__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE__UNIT = VALUE_FOR_STRUCTURAL_FEATURE__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE__LINE_BREAK = VALUE_FOR_STRUCTURAL_FEATURE__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE__VALUE = VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>New Object Value For Structural Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT = VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ReferenceValueForStructuralFeatureImpl <em>Reference Value For Structural Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ReferenceValueForStructuralFeatureImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getReferenceValueForStructuralFeature()
	 * @generated
	 */
	int REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE = 17;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__COMPILATION_STATUS = VALUE_FOR_STRUCTURAL_FEATURE__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__INDEX_ENTRY = VALUE_FOR_STRUCTURAL_FEATURE__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__UNIT = VALUE_FOR_STRUCTURAL_FEATURE__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__LINE_BREAK = VALUE_FOR_STRUCTURAL_FEATURE__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Instanciation Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__INSTANCIATION_REFERENCE = VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced Meta Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__REFERENCED_META_TYPE = VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Reference Value For Structural Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT = VALUE_FOR_STRUCTURAL_FEATURE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ContributionInstructionImpl <em>Contribution Instruction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ContributionInstructionImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getContributionInstruction()
	 * @generated
	 */
	int CONTRIBUTION_INSTRUCTION = 18;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_INSTRUCTION__COMPILATION_STATUS = MODELING_UNIT_INSTRUCTION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_INSTRUCTION__INDEX_ENTRY = MODELING_UNIT_INSTRUCTION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_INSTRUCTION__UNIT = MODELING_UNIT_INSTRUCTION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_INSTRUCTION__LINE_BREAK = MODELING_UNIT_INSTRUCTION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Contribution Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_INSTRUCTION__CONTRIBUTION_REFERENCE = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contributions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Contribution Instruction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_INSTRUCTION_FEATURE_COUNT = MODELING_UNIT_INSTRUCTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ExternalContentReferenceImpl <em>External Content Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ExternalContentReferenceImpl
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getExternalContentReference()
	 * @generated
	 */
	int EXTERNAL_CONTENT_REFERENCE = 19;

	/**
	 * The feature id for the '<em><b>Compilation Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__COMPILATION_STATUS = RESOURCE_DECLARATION__COMPILATION_STATUS;

	/**
	 * The feature id for the '<em><b>Index Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__INDEX_ENTRY = RESOURCE_DECLARATION__INDEX_ENTRY;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__UNIT = RESOURCE_DECLARATION__UNIT;

	/**
	 * The feature id for the '<em><b>Line Break</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__LINE_BREAK = RESOURCE_DECLARATION__LINE_BREAK;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__URI = RESOURCE_DECLARATION__URI;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__NAME = RESOURCE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__CONTENT_TYPE = RESOURCE_DECLARATION__CONTENT_TYPE;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__CONTENT = RESOURCE_DECLARATION__CONTENT;

	/**
	 * The feature id for the '<em><b>External Content</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__EXTERNAL_CONTENT = RESOURCE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Marked As Merged</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE__MARKED_AS_MERGED = RESOURCE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>External Content Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTENT_REFERENCE_FEATURE_COUNT = RESOURCE_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator <em>Affectation Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getAffectationOperator()
	 * @generated
	 */
	int AFFECTATION_OPERATOR = 20;

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Key Val For Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Key Val For Annotation</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getKeyValForAnnotation();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getKeyValForAnnotation()
	 * @generated
	 */
	EAttribute getKeyValForAnnotation_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getKeyValForAnnotation()
	 * @generated
	 */
	EAttribute getKeyValForAnnotation_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit <em>Modeling Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modeling Unit</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit
	 * @generated
	 */
	EClass getModelingUnit();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit#getUsedBy <em>Used By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Used By</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit#getUsedBy()
	 * @see #getModelingUnit()
	 * @generated
	 */
	EReference getModelingUnit_UsedBy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit#getSeeAlso <em>See Also</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>See Also</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit#getSeeAlso()
	 * @see #getModelingUnit()
	 * @generated
	 */
	EReference getModelingUnit_SeeAlso();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit#getResource()
	 * @see #getModelingUnit()
	 * @generated
	 */
	EReference getModelingUnit_Resource();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction <em>Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instruction</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction
	 * @generated
	 */
	EClass getModelingUnitInstruction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference <em>Resource Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Reference</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference
	 * @generated
	 */
	EClass getResourceReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference#getResourceName <em>Resource Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Name</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference#getResourceName()
	 * @see #getResourceReference()
	 * @generated
	 */
	EAttribute getResourceReference_ResourceName();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Declaration</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference#getDeclaration()
	 * @see #getResourceReference()
	 * @generated
	 */
	EReference getResourceReference_Declaration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference#isLineBreak <em>Line Break</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Break</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference#isLineBreak()
	 * @see #getResourceReference()
	 * @generated
	 */
	EAttribute getResourceReference_LineBreak();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference <em>Instruction Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instruction Reference</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference
	 * @generated
	 */
	EClass getModelingUnitInstructionReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference#getIntentHref <em>Intent Href</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Intent Href</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference#getIntentHref()
	 * @see #getModelingUnitInstructionReference()
	 * @generated
	 */
	EAttribute getModelingUnitInstructionReference_IntentHref();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference#getReferencedInstruction <em>Referenced Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Instruction</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference#getReferencedInstruction()
	 * @see #getModelingUnitInstructionReference()
	 * @generated
	 */
	EReference getModelingUnitInstructionReference_ReferencedInstruction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference <em>Instanciation Instruction Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instanciation Instruction Reference</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference
	 * @generated
	 */
	EClass getInstanciationInstructionReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference#getInstanceName <em>Instance Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instance Name</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference#getInstanceName()
	 * @see #getInstanciationInstructionReference()
	 * @generated
	 */
	EAttribute getInstanciationInstructionReference_InstanceName();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference#getInstanciation <em>Instanciation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instanciation</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference#getInstanciation()
	 * @see #getInstanciationInstructionReference()
	 * @generated
	 */
	EReference getInstanciationInstructionReference_Instanciation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration <em>Resource Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Declaration</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration
	 * @generated
	 */
	EClass getResourceDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration#getUri()
	 * @see #getResourceDeclaration()
	 * @generated
	 */
	EAttribute getResourceDeclaration_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration#getName()
	 * @see #getResourceDeclaration()
	 * @generated
	 */
	EAttribute getResourceDeclaration_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration#getContentType <em>Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content Type</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration#getContentType()
	 * @see #getResourceDeclaration()
	 * @generated
	 */
	EAttribute getResourceDeclaration_ContentType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration#getContent()
	 * @see #getResourceDeclaration()
	 * @generated
	 */
	EReference getResourceDeclaration_Content();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.IntentReferenceinModelingUnit <em>Intent Referencein Modeling Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intent Referencein Modeling Unit</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.IntentReferenceinModelingUnit
	 * @generated
	 */
	EClass getIntentReferenceinModelingUnit();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.LabelinModelingUnit <em>Labelin Modeling Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Labelin Modeling Unit</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.LabelinModelingUnit
	 * @generated
	 */
	EClass getLabelinModelingUnit();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration <em>Annotation Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation Declaration</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration
	 * @generated
	 */
	EClass getAnnotationDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration#getAnnotationID <em>Annotation ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Annotation ID</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration#getAnnotationID()
	 * @see #getAnnotationDeclaration()
	 * @generated
	 */
	EAttribute getAnnotationDeclaration_AnnotationID();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Map</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration#getMap()
	 * @see #getAnnotationDeclaration()
	 * @generated
	 */
	EReference getAnnotationDeclaration_Map();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference <em>Type Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Reference</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference
	 * @generated
	 */
	EClass getTypeReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference#getTypeName <em>Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Name</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference#getTypeName()
	 * @see #getTypeReference()
	 * @generated
	 */
	EAttribute getTypeReference_TypeName();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference#getResolvedType <em>Resolved Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resolved Type</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference#getResolvedType()
	 * @see #getTypeReference()
	 * @generated
	 */
	EReference getTypeReference_ResolvedType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanceLevelInstruction <em>Instance Level Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance Level Instruction</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanceLevelInstruction
	 * @generated
	 */
	EClass getInstanceLevelInstruction();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanceLevelInstruction#getMetaType <em>Meta Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Meta Type</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanceLevelInstruction#getMetaType()
	 * @see #getInstanceLevelInstruction()
	 * @generated
	 */
	EReference getInstanceLevelInstruction_MetaType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction <em>Instanciation Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instanciation Instruction</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction
	 * @generated
	 */
	EClass getInstanciationInstruction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction#getName()
	 * @see #getInstanciationInstruction()
	 * @generated
	 */
	EAttribute getInstanciationInstruction_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction#getStructuralFeatures <em>Structural Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Structural Features</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction#getStructuralFeatures()
	 * @see #getInstanciationInstruction()
	 * @generated
	 */
	EReference getInstanciationInstruction_StructuralFeatures();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation <em>Structural Feature Affectation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Structural Feature Affectation</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation
	 * @generated
	 */
	EClass getStructuralFeatureAffectation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation#getName()
	 * @see #getStructuralFeatureAffectation()
	 * @generated
	 */
	EAttribute getStructuralFeatureAffectation_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation#getUsedOperator <em>Used Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Used Operator</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation#getUsedOperator()
	 * @see #getStructuralFeatureAffectation()
	 * @generated
	 */
	EAttribute getStructuralFeatureAffectation_UsedOperator();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation#getValues()
	 * @see #getStructuralFeatureAffectation()
	 * @generated
	 */
	EReference getStructuralFeatureAffectation_Values();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ValueForStructuralFeature <em>Value For Structural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value For Structural Feature</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ValueForStructuralFeature
	 * @generated
	 */
	EClass getValueForStructuralFeature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature <em>Native Value For Structural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Native Value For Structural Feature</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature
	 * @generated
	 */
	EClass getNativeValueForStructuralFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature#getValue()
	 * @see #getNativeValueForStructuralFeature()
	 * @generated
	 */
	EAttribute getNativeValueForStructuralFeature_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature <em>New Object Value For Structural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Object Value For Structural Feature</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature
	 * @generated
	 */
	EClass getNewObjectValueForStructuralFeature();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature#getValue()
	 * @see #getNewObjectValueForStructuralFeature()
	 * @generated
	 */
	EReference getNewObjectValueForStructuralFeature_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature <em>Reference Value For Structural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Value For Structural Feature</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature
	 * @generated
	 */
	EClass getReferenceValueForStructuralFeature();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature#getInstanciationReference <em>Instanciation Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Instanciation Reference</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature#getInstanciationReference()
	 * @see #getReferenceValueForStructuralFeature()
	 * @generated
	 */
	EReference getReferenceValueForStructuralFeature_InstanciationReference();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature#getReferencedMetaType <em>Referenced Meta Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Meta Type</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature#getReferencedMetaType()
	 * @see #getReferenceValueForStructuralFeature()
	 * @generated
	 */
	EReference getReferenceValueForStructuralFeature_ReferencedMetaType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction <em>Contribution Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contribution Instruction</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction
	 * @generated
	 */
	EClass getContributionInstruction();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction#getContributionReference <em>Contribution Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Contribution Reference</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction#getContributionReference()
	 * @see #getContributionInstruction()
	 * @generated
	 */
	EReference getContributionInstruction_ContributionReference();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction#getContributions <em>Contributions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contributions</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction#getContributions()
	 * @see #getContributionInstruction()
	 * @generated
	 */
	EReference getContributionInstruction_Contributions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference <em>External Content Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Content Reference</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference
	 * @generated
	 */
	EClass getExternalContentReference();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference#getExternalContent <em>External Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>External Content</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference#getExternalContent()
	 * @see #getExternalContentReference()
	 * @generated
	 */
	EReference getExternalContentReference_ExternalContent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference#isMarkedAsMerged <em>Marked As Merged</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Marked As Merged</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference#isMarkedAsMerged()
	 * @see #getExternalContentReference()
	 * @generated
	 */
	EAttribute getExternalContentReference_MarkedAsMerged();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator <em>Affectation Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Affectation Operator</em>'.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator
	 * @generated
	 */
	EEnum getAffectationOperator();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelingUnitFactory getModelingUnitFactory();

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
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.KeyValForAnnotationImpl <em>Key Val For Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.KeyValForAnnotationImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getKeyValForAnnotation()
		 * @generated
		 */
		EClass KEY_VAL_FOR_ANNOTATION = eINSTANCE.getKeyValForAnnotation();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEY_VAL_FOR_ANNOTATION__KEY = eINSTANCE.getKeyValForAnnotation_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEY_VAL_FOR_ANNOTATION__VALUE = eINSTANCE.getKeyValForAnnotation_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitImpl <em>Modeling Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getModelingUnit()
		 * @generated
		 */
		EClass MODELING_UNIT = eINSTANCE.getModelingUnit();

		/**
		 * The meta object literal for the '<em><b>Used By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_UNIT__USED_BY = eINSTANCE.getModelingUnit_UsedBy();

		/**
		 * The meta object literal for the '<em><b>See Also</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_UNIT__SEE_ALSO = eINSTANCE.getModelingUnit_SeeAlso();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_UNIT__RESOURCE = eINSTANCE.getModelingUnit_Resource();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitInstructionImpl <em>Instruction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitInstructionImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getModelingUnitInstruction()
		 * @generated
		 */
		EClass MODELING_UNIT_INSTRUCTION = eINSTANCE.getModelingUnitInstruction();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ResourceReferenceImpl <em>Resource Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ResourceReferenceImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getResourceReference()
		 * @generated
		 */
		EClass RESOURCE_REFERENCE = eINSTANCE.getResourceReference();

		/**
		 * The meta object literal for the '<em><b>Resource Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_REFERENCE__RESOURCE_NAME = eINSTANCE.getResourceReference_ResourceName();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_REFERENCE__DECLARATION = eINSTANCE.getResourceReference_Declaration();

		/**
		 * The meta object literal for the '<em><b>Line Break</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_REFERENCE__LINE_BREAK = eINSTANCE.getResourceReference_LineBreak();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitInstructionReferenceImpl <em>Instruction Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitInstructionReferenceImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getModelingUnitInstructionReference()
		 * @generated
		 */
		EClass MODELING_UNIT_INSTRUCTION_REFERENCE = eINSTANCE.getModelingUnitInstructionReference();

		/**
		 * The meta object literal for the '<em><b>Intent Href</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODELING_UNIT_INSTRUCTION_REFERENCE__INTENT_HREF = eINSTANCE
				.getModelingUnitInstructionReference_IntentHref();

		/**
		 * The meta object literal for the '<em><b>Referenced Instruction</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_UNIT_INSTRUCTION_REFERENCE__REFERENCED_INSTRUCTION = eINSTANCE
				.getModelingUnitInstructionReference_ReferencedInstruction();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionReferenceImpl <em>Instanciation Instruction Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionReferenceImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getInstanciationInstructionReference()
		 * @generated
		 */
		EClass INSTANCIATION_INSTRUCTION_REFERENCE = eINSTANCE.getInstanciationInstructionReference();

		/**
		 * The meta object literal for the '<em><b>Instance Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCE_NAME = eINSTANCE
				.getInstanciationInstructionReference_InstanceName();

		/**
		 * The meta object literal for the '<em><b>Instanciation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCIATION = eINSTANCE
				.getInstanciationInstructionReference_Instanciation();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ResourceDeclarationImpl <em>Resource Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ResourceDeclarationImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getResourceDeclaration()
		 * @generated
		 */
		EClass RESOURCE_DECLARATION = eINSTANCE.getResourceDeclaration();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_DECLARATION__URI = eINSTANCE.getResourceDeclaration_Uri();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_DECLARATION__NAME = eINSTANCE.getResourceDeclaration_Name();

		/**
		 * The meta object literal for the '<em><b>Content Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_DECLARATION__CONTENT_TYPE = eINSTANCE.getResourceDeclaration_ContentType();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_DECLARATION__CONTENT = eINSTANCE.getResourceDeclaration_Content();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.IntentReferenceinModelingUnitImpl <em>Intent Referencein Modeling Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.IntentReferenceinModelingUnitImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getIntentReferenceinModelingUnit()
		 * @generated
		 */
		EClass INTENT_REFERENCEIN_MODELING_UNIT = eINSTANCE.getIntentReferenceinModelingUnit();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.LabelinModelingUnitImpl <em>Labelin Modeling Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.LabelinModelingUnitImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getLabelinModelingUnit()
		 * @generated
		 */
		EClass LABELIN_MODELING_UNIT = eINSTANCE.getLabelinModelingUnit();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.AnnotationDeclarationImpl <em>Annotation Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.AnnotationDeclarationImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getAnnotationDeclaration()
		 * @generated
		 */
		EClass ANNOTATION_DECLARATION = eINSTANCE.getAnnotationDeclaration();

		/**
		 * The meta object literal for the '<em><b>Annotation ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION_DECLARATION__ANNOTATION_ID = eINSTANCE.getAnnotationDeclaration_AnnotationID();

		/**
		 * The meta object literal for the '<em><b>Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION_DECLARATION__MAP = eINSTANCE.getAnnotationDeclaration_Map();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.TypeReferenceImpl <em>Type Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.TypeReferenceImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getTypeReference()
		 * @generated
		 */
		EClass TYPE_REFERENCE = eINSTANCE.getTypeReference();

		/**
		 * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_REFERENCE__TYPE_NAME = eINSTANCE.getTypeReference_TypeName();

		/**
		 * The meta object literal for the '<em><b>Resolved Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_REFERENCE__RESOLVED_TYPE = eINSTANCE.getTypeReference_ResolvedType();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanceLevelInstructionImpl <em>Instance Level Instruction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanceLevelInstructionImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getInstanceLevelInstruction()
		 * @generated
		 */
		EClass INSTANCE_LEVEL_INSTRUCTION = eINSTANCE.getInstanceLevelInstruction();

		/**
		 * The meta object literal for the '<em><b>Meta Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_LEVEL_INSTRUCTION__META_TYPE = eINSTANCE.getInstanceLevelInstruction_MetaType();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionImpl <em>Instanciation Instruction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.InstanciationInstructionImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getInstanciationInstruction()
		 * @generated
		 */
		EClass INSTANCIATION_INSTRUCTION = eINSTANCE.getInstanciationInstruction();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCIATION_INSTRUCTION__NAME = eINSTANCE.getInstanciationInstruction_Name();

		/**
		 * The meta object literal for the '<em><b>Structural Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCIATION_INSTRUCTION__STRUCTURAL_FEATURES = eINSTANCE
				.getInstanciationInstruction_StructuralFeatures();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.StructuralFeatureAffectationImpl <em>Structural Feature Affectation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.StructuralFeatureAffectationImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getStructuralFeatureAffectation()
		 * @generated
		 */
		EClass STRUCTURAL_FEATURE_AFFECTATION = eINSTANCE.getStructuralFeatureAffectation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURAL_FEATURE_AFFECTATION__NAME = eINSTANCE.getStructuralFeatureAffectation_Name();

		/**
		 * The meta object literal for the '<em><b>Used Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURAL_FEATURE_AFFECTATION__USED_OPERATOR = eINSTANCE
				.getStructuralFeatureAffectation_UsedOperator();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURAL_FEATURE_AFFECTATION__VALUES = eINSTANCE
				.getStructuralFeatureAffectation_Values();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ValueForStructuralFeatureImpl <em>Value For Structural Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ValueForStructuralFeatureImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getValueForStructuralFeature()
		 * @generated
		 */
		EClass VALUE_FOR_STRUCTURAL_FEATURE = eINSTANCE.getValueForStructuralFeature();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.NativeValueForStructuralFeatureImpl <em>Native Value For Structural Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.NativeValueForStructuralFeatureImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getNativeValueForStructuralFeature()
		 * @generated
		 */
		EClass NATIVE_VALUE_FOR_STRUCTURAL_FEATURE = eINSTANCE.getNativeValueForStructuralFeature();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NATIVE_VALUE_FOR_STRUCTURAL_FEATURE__VALUE = eINSTANCE
				.getNativeValueForStructuralFeature_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.NewObjectValueForStructuralFeatureImpl <em>New Object Value For Structural Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.NewObjectValueForStructuralFeatureImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getNewObjectValueForStructuralFeature()
		 * @generated
		 */
		EClass NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE = eINSTANCE.getNewObjectValueForStructuralFeature();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE__VALUE = eINSTANCE
				.getNewObjectValueForStructuralFeature_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ReferenceValueForStructuralFeatureImpl <em>Reference Value For Structural Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ReferenceValueForStructuralFeatureImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getReferenceValueForStructuralFeature()
		 * @generated
		 */
		EClass REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE = eINSTANCE.getReferenceValueForStructuralFeature();

		/**
		 * The meta object literal for the '<em><b>Instanciation Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__INSTANCIATION_REFERENCE = eINSTANCE
				.getReferenceValueForStructuralFeature_InstanciationReference();

		/**
		 * The meta object literal for the '<em><b>Referenced Meta Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__REFERENCED_META_TYPE = eINSTANCE
				.getReferenceValueForStructuralFeature_ReferencedMetaType();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ContributionInstructionImpl <em>Contribution Instruction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ContributionInstructionImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getContributionInstruction()
		 * @generated
		 */
		EClass CONTRIBUTION_INSTRUCTION = eINSTANCE.getContributionInstruction();

		/**
		 * The meta object literal for the '<em><b>Contribution Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_INSTRUCTION__CONTRIBUTION_REFERENCE = eINSTANCE
				.getContributionInstruction_ContributionReference();

		/**
		 * The meta object literal for the '<em><b>Contributions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS = eINSTANCE
				.getContributionInstruction_Contributions();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ExternalContentReferenceImpl <em>External Content Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ExternalContentReferenceImpl
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getExternalContentReference()
		 * @generated
		 */
		EClass EXTERNAL_CONTENT_REFERENCE = eINSTANCE.getExternalContentReference();

		/**
		 * The meta object literal for the '<em><b>External Content</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_CONTENT_REFERENCE__EXTERNAL_CONTENT = eINSTANCE
				.getExternalContentReference_ExternalContent();

		/**
		 * The meta object literal for the '<em><b>Marked As Merged</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_CONTENT_REFERENCE__MARKED_AS_MERGED = eINSTANCE
				.getExternalContentReference_MarkedAsMerged();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator <em>Affectation Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator
		 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl#getAffectationOperator()
		 * @generated
		 */
		EEnum AFFECTATION_OPERATOR = eINSTANCE.getAffectationOperator();

	}

} //ModelingUnitPackage
