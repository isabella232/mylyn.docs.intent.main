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
package org.eclipse.mylyn.docs.intent.core.document.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.impl.CompilerPackageImpl;
import org.eclipse.mylyn.docs.intent.core.document.GenericUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.document.IntentReference;
import org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration;
import org.eclipse.mylyn.docs.intent.core.document.LabelReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.document.TypeLabel;
import org.eclipse.mylyn.docs.intent.core.document.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnitPackage;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.impl.DescriptionUnitPackageImpl;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerPackage;
import org.eclipse.mylyn.docs.intent.core.indexer.impl.IntentIndexerPackageImpl;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class IntentDocumentPackageImpl extends EPackageImpl implements IntentDocumentPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentGenericElementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentStructuredElementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentSectionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentDocumentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericUnitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unitInstructionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentReferenceInstructionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelReferenceInstructionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentReferenceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeLabelEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uriEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IntentDocumentPackageImpl() {
		super(eNS_URI, IntentDocumentFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link IntentDocumentPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IntentDocumentPackage init() {
		if (isInited)
			return (IntentDocumentPackage)EPackage.Registry.INSTANCE
					.getEPackage(IntentDocumentPackage.eNS_URI);

		// Obtain or create and register package
		IntentDocumentPackageImpl theIntentDocumentPackage = (IntentDocumentPackageImpl)(EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof IntentDocumentPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new IntentDocumentPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MarkupPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CompilerPackageImpl theCompilerPackage = (CompilerPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(CompilerPackage.eNS_URI) instanceof CompilerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(CompilerPackage.eNS_URI) : CompilerPackage.eINSTANCE);
		DescriptionUnitPackageImpl theDescriptionUnitPackage = (DescriptionUnitPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(DescriptionUnitPackage.eNS_URI) instanceof DescriptionUnitPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(DescriptionUnitPackage.eNS_URI) : DescriptionUnitPackage.eINSTANCE);
		IntentIndexerPackageImpl theIntentIndexerPackage = (IntentIndexerPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(IntentIndexerPackage.eNS_URI) instanceof IntentIndexerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(IntentIndexerPackage.eNS_URI) : IntentIndexerPackage.eINSTANCE);
		ModelingUnitPackageImpl theModelingUnitPackage = (ModelingUnitPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(ModelingUnitPackage.eNS_URI) instanceof ModelingUnitPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(ModelingUnitPackage.eNS_URI) : ModelingUnitPackage.eINSTANCE);

		// Create package meta-data objects
		theIntentDocumentPackage.createPackageContents();
		theCompilerPackage.createPackageContents();
		theDescriptionUnitPackage.createPackageContents();
		theIntentIndexerPackage.createPackageContents();
		theModelingUnitPackage.createPackageContents();

		// Initialize created meta-data
		theIntentDocumentPackage.initializePackageContents();
		theCompilerPackage.initializePackageContents();
		theDescriptionUnitPackage.initializePackageContents();
		theIntentIndexerPackage.initializePackageContents();
		theModelingUnitPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIntentDocumentPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IntentDocumentPackage.eNS_URI, theIntentDocumentPackage);
		return theIntentDocumentPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentGenericElement() {
		return intentGenericElementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentGenericElement_CompilationStatus() {
		return (EReference)intentGenericElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentGenericElement_IndexEntry() {
		return (EReference)intentGenericElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentStructuredElement() {
		return intentStructuredElementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntentStructuredElement_CompleteLevel() {
		return (EAttribute)intentStructuredElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentSection() {
		return intentSectionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentSection_IntentContent() {
		return (EReference)intentSectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentSection_SubSections() {
		return (EReference)intentSectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentSection_Units() {
		return (EReference)intentSectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentSection_DescriptionUnits() {
		return (EReference)intentSectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentSection_ModelingUnits() {
		return (EReference)intentSectionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentDocument() {
		return intentDocumentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericUnit() {
		return genericUnitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericUnit_Instructions() {
		return (EReference)genericUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGenericUnit_Name() {
		return (EAttribute)genericUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnitInstruction() {
		return unitInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnitInstruction_Unit() {
		return (EReference)unitInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnitInstruction_LineBreak() {
		return (EAttribute)unitInstructionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentReferenceInstruction() {
		return intentReferenceInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntentReferenceInstruction_TextToPrint() {
		return (EAttribute)intentReferenceInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabelDeclaration() {
		return labelDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelDeclaration_LabelValue() {
		return (EAttribute)labelDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelDeclaration_TextToPrint() {
		return (EAttribute)labelDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelDeclaration_Type() {
		return (EAttribute)labelDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabelReferenceInstruction() {
		return labelReferenceInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelReferenceInstruction_Type() {
		return (EAttribute)labelReferenceInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentReference() {
		return intentReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntentReference_IntentHref() {
		return (EAttribute)intentReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentReference_ReferencedElement() {
		return (EReference)intentReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeLabel() {
		return typeLabelEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getURI() {
		return uriEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IntentDocumentFactory getIntentDocumentFactory() {
		return (IntentDocumentFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		intentGenericElementEClass = createEClass(INTENT_GENERIC_ELEMENT);
		createEReference(intentGenericElementEClass, INTENT_GENERIC_ELEMENT__COMPILATION_STATUS);
		createEReference(intentGenericElementEClass, INTENT_GENERIC_ELEMENT__INDEX_ENTRY);

		intentStructuredElementEClass = createEClass(INTENT_STRUCTURED_ELEMENT);
		createEAttribute(intentStructuredElementEClass, INTENT_STRUCTURED_ELEMENT__COMPLETE_LEVEL);

		intentSectionEClass = createEClass(INTENT_SECTION);
		createEReference(intentSectionEClass, INTENT_SECTION__INTENT_CONTENT);
		createEReference(intentSectionEClass, INTENT_SECTION__SUB_SECTIONS);
		createEReference(intentSectionEClass, INTENT_SECTION__UNITS);
		createEReference(intentSectionEClass, INTENT_SECTION__DESCRIPTION_UNITS);
		createEReference(intentSectionEClass, INTENT_SECTION__MODELING_UNITS);

		intentDocumentEClass = createEClass(INTENT_DOCUMENT);

		genericUnitEClass = createEClass(GENERIC_UNIT);
		createEReference(genericUnitEClass, GENERIC_UNIT__INSTRUCTIONS);
		createEAttribute(genericUnitEClass, GENERIC_UNIT__NAME);

		unitInstructionEClass = createEClass(UNIT_INSTRUCTION);
		createEReference(unitInstructionEClass, UNIT_INSTRUCTION__UNIT);
		createEAttribute(unitInstructionEClass, UNIT_INSTRUCTION__LINE_BREAK);

		intentReferenceInstructionEClass = createEClass(INTENT_REFERENCE_INSTRUCTION);
		createEAttribute(intentReferenceInstructionEClass, INTENT_REFERENCE_INSTRUCTION__TEXT_TO_PRINT);

		labelDeclarationEClass = createEClass(LABEL_DECLARATION);
		createEAttribute(labelDeclarationEClass, LABEL_DECLARATION__LABEL_VALUE);
		createEAttribute(labelDeclarationEClass, LABEL_DECLARATION__TEXT_TO_PRINT);
		createEAttribute(labelDeclarationEClass, LABEL_DECLARATION__TYPE);

		labelReferenceInstructionEClass = createEClass(LABEL_REFERENCE_INSTRUCTION);
		createEAttribute(labelReferenceInstructionEClass, LABEL_REFERENCE_INSTRUCTION__TYPE);

		intentReferenceEClass = createEClass(INTENT_REFERENCE);
		createEAttribute(intentReferenceEClass, INTENT_REFERENCE__INTENT_HREF);
		createEReference(intentReferenceEClass, INTENT_REFERENCE__REFERENCED_ELEMENT);

		// Create enums
		typeLabelEEnum = createEEnum(TYPE_LABEL);

		// Create data types
		uriEDataType = createEDataType(URI);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		DescriptionUnitPackage theDescriptionUnitPackage = (DescriptionUnitPackage)EPackage.Registry.INSTANCE
				.getEPackage(DescriptionUnitPackage.eNS_URI);
		CompilerPackage theCompilerPackage = (CompilerPackage)EPackage.Registry.INSTANCE
				.getEPackage(CompilerPackage.eNS_URI);
		IntentIndexerPackage theIntentIndexerPackage = (IntentIndexerPackage)EPackage.Registry.INSTANCE
				.getEPackage(IntentIndexerPackage.eNS_URI);
		MarkupPackage theMarkupPackage = (MarkupPackage)EPackage.Registry.INSTANCE
				.getEPackage(MarkupPackage.eNS_URI);
		ModelingUnitPackage theModelingUnitPackage = (ModelingUnitPackage)EPackage.Registry.INSTANCE
				.getEPackage(ModelingUnitPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theDescriptionUnitPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		intentStructuredElementEClass.getESuperTypes().add(theMarkupPackage.getSection());
		intentStructuredElementEClass.getESuperTypes().add(this.getIntentGenericElement());
		intentSectionEClass.getESuperTypes().add(this.getIntentStructuredElement());
		intentDocumentEClass.getESuperTypes().add(this.getIntentSection());
		genericUnitEClass.getESuperTypes().add(this.getIntentGenericElement());
		unitInstructionEClass.getESuperTypes().add(this.getIntentGenericElement());
		intentReferenceInstructionEClass.getESuperTypes().add(this.getUnitInstruction());
		intentReferenceInstructionEClass.getESuperTypes().add(this.getIntentReference());
		labelDeclarationEClass.getESuperTypes().add(this.getUnitInstruction());
		labelReferenceInstructionEClass.getESuperTypes().add(this.getUnitInstruction());
		labelReferenceInstructionEClass.getESuperTypes().add(this.getIntentReference());

		// Initialize classes and features; add operations and parameters
		initEClass(intentGenericElementEClass, IntentGenericElement.class, "IntentGenericElement",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIntentGenericElement_CompilationStatus(),
				theCompilerPackage.getCompilationStatus(), theCompilerPackage.getCompilationStatus_Target(),
				"compilationStatus", null, 0, -1, IntentGenericElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getIntentGenericElement_IndexEntry(), theIntentIndexerPackage.getIntentIndexEntry(),
				theIntentIndexerPackage.getIntentIndexEntry_ReferencedElement(), "indexEntry", null, 0, 1,
				IntentGenericElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intentStructuredElementEClass, IntentStructuredElement.class, "IntentStructuredElement",
				IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntentStructuredElement_CompleteLevel(), ecorePackage.getEString(),
				"completeLevel", null, 0, 1, IntentStructuredElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intentSectionEClass, IntentSection.class, "IntentSection", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIntentSection_IntentContent(), ecorePackage.getEObject(), null, "intentContent",
				null, 0, -1, IntentSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntentSection_SubSections(), this.getIntentSection(), null, "subSections", null, 0,
				-1, IntentSection.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getIntentSection_Units(), this.getGenericUnit(), null, "units", null, 0, -1,
				IntentSection.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntentSection_DescriptionUnits(), theDescriptionUnitPackage.getDescriptionUnit(),
				null, "descriptionUnits", null, 0, -1, IntentSection.class, !IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEReference(getIntentSection_ModelingUnits(), theModelingUnitPackage.getModelingUnit(), null,
				"modelingUnits", null, 0, -1, IntentSection.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(intentDocumentEClass, IntentDocument.class, "IntentDocument", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericUnitEClass, GenericUnit.class, "GenericUnit", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericUnit_Instructions(), this.getUnitInstruction(),
				this.getUnitInstruction_Unit(), "instructions", null, 0, -1, GenericUnit.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenericUnit_Name(), ecorePackage.getEString(), "name", null, 0, 1,
				GenericUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unitInstructionEClass, UnitInstruction.class, "UnitInstruction", IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnitInstruction_Unit(), this.getGenericUnit(), this.getGenericUnit_Instructions(),
				"unit", null, 0, 1, UnitInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnitInstruction_LineBreak(), ecorePackage.getEBoolean(), "lineBreak", "false", 0,
				1, UnitInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intentReferenceInstructionEClass, IntentReferenceInstruction.class,
				"IntentReferenceInstruction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntentReferenceInstruction_TextToPrint(), ecorePackage.getEString(), "textToPrint",
				null, 0, 1, IntentReferenceInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelDeclarationEClass, LabelDeclaration.class, "LabelDeclaration", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabelDeclaration_LabelValue(), ecorePackage.getEString(), "labelValue", null, 1, 1,
				LabelDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelDeclaration_TextToPrint(), ecorePackage.getEString(), "textToPrint", null, 1,
				1, LabelDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelDeclaration_Type(), this.getTypeLabel(), "type", null, 1, 1,
				LabelDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelReferenceInstructionEClass, LabelReferenceInstruction.class,
				"LabelReferenceInstruction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabelReferenceInstruction_Type(), this.getTypeLabel(), "type", null, 1, 1,
				LabelReferenceInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intentReferenceEClass, IntentReference.class, "IntentReference", IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntentReference_IntentHref(), ecorePackage.getEString(), "intentHref", null, 0, 1,
				IntentReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntentReference_ReferencedElement(), ecorePackage.getEObject(), null,
				"referencedElement", null, 0, 1, IntentReference.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(typeLabelEEnum, TypeLabel.class, "TypeLabel");
		addEEnumLiteral(typeLabelEEnum, TypeLabel.LAZY);
		addEEnumLiteral(typeLabelEEnum, TypeLabel.EXPLICIT);

		// Initialize data types
		initEDataType(uriEDataType, org.eclipse.emf.common.util.URI.class, "URI", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // IntentDocumentPackageImpl
