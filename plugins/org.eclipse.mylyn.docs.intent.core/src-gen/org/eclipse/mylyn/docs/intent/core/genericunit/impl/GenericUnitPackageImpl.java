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
package org.eclipse.mylyn.docs.intent.core.genericunit.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.impl.CompilerPackageImpl;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnitPackage;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.impl.DescriptionUnitPackageImpl;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl;
import org.eclipse.mylyn.docs.intent.core.genericunit.AdressedAnnotation;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnit;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitFactory;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReference;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.genericunit.LabelDeclaration;
import org.eclipse.mylyn.docs.intent.core.genericunit.LabelReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.genericunit.TypeLabel;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerPackage;
import org.eclipse.mylyn.docs.intent.core.indexer.impl.IntentIndexerPackageImpl;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GenericUnitPackageImpl extends EPackageImpl implements GenericUnitPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unitInstructionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentReferenceInstructionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelReferenceInstructionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adressedAnnotationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeLabelEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GenericUnitPackageImpl() {
		super(eNS_URI, GenericUnitFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link GenericUnitPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GenericUnitPackage init() {
		if (isInited)
			return (GenericUnitPackage)EPackage.Registry.INSTANCE.getEPackage(GenericUnitPackage.eNS_URI);

		// Obtain or create and register package
		GenericUnitPackageImpl theGenericUnitPackage = (GenericUnitPackageImpl)(EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof GenericUnitPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new GenericUnitPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MarkupPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		IntentDocumentPackageImpl theIntentDocumentPackage = (IntentDocumentPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(IntentDocumentPackage.eNS_URI) instanceof IntentDocumentPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(IntentDocumentPackage.eNS_URI) : IntentDocumentPackage.eINSTANCE);
		CompilerPackageImpl theCompilerPackage = (CompilerPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(CompilerPackage.eNS_URI) instanceof CompilerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(CompilerPackage.eNS_URI) : CompilerPackage.eINSTANCE);
		IntentIndexerPackageImpl theIntentIndexerPackage = (IntentIndexerPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(IntentIndexerPackage.eNS_URI) instanceof IntentIndexerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(IntentIndexerPackage.eNS_URI) : IntentIndexerPackage.eINSTANCE);
		DescriptionUnitPackageImpl theDescriptionUnitPackage = (DescriptionUnitPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(DescriptionUnitPackage.eNS_URI) instanceof DescriptionUnitPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(DescriptionUnitPackage.eNS_URI) : DescriptionUnitPackage.eINSTANCE);
		ModelingUnitPackageImpl theModelingUnitPackage = (ModelingUnitPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(ModelingUnitPackage.eNS_URI) instanceof ModelingUnitPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(ModelingUnitPackage.eNS_URI) : ModelingUnitPackage.eINSTANCE);

		// Create package meta-data objects
		theGenericUnitPackage.createPackageContents();
		theIntentDocumentPackage.createPackageContents();
		theCompilerPackage.createPackageContents();
		theIntentIndexerPackage.createPackageContents();
		theDescriptionUnitPackage.createPackageContents();
		theModelingUnitPackage.createPackageContents();

		// Initialize created meta-data
		theGenericUnitPackage.initializePackageContents();
		theIntentDocumentPackage.initializePackageContents();
		theCompilerPackage.initializePackageContents();
		theIntentIndexerPackage.initializePackageContents();
		theDescriptionUnitPackage.initializePackageContents();
		theModelingUnitPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGenericUnitPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GenericUnitPackage.eNS_URI, theGenericUnitPackage);
		return theGenericUnitPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericUnit() {
		return genericUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericUnit_Instructions() {
		return (EReference)genericUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGenericUnit_UnitName() {
		return (EAttribute)genericUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnitInstruction() {
		return unitInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnitInstruction_Unit() {
		return (EReference)unitInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnitInstruction_LineBreak() {
		return (EAttribute)unitInstructionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentReferenceInstruction() {
		return intentReferenceInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntentReferenceInstruction_TextToPrint() {
		return (EAttribute)intentReferenceInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabelDeclaration() {
		return labelDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelDeclaration_LabelValue() {
		return (EAttribute)labelDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelDeclaration_TextToPrint() {
		return (EAttribute)labelDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelDeclaration_Type() {
		return (EAttribute)labelDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabelReferenceInstruction() {
		return labelReferenceInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelReferenceInstruction_Type() {
		return (EAttribute)labelReferenceInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdressedAnnotation() {
		return adressedAnnotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdressedAnnotation_Receiver() {
		return (EAttribute)adressedAnnotationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdressedAnnotation_Source() {
		return (EAttribute)adressedAnnotationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdressedAnnotation_Message() {
		return (EAttribute)adressedAnnotationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdressedAnnotation_Type() {
		return (EAttribute)adressedAnnotationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentReference() {
		return intentReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntentReference_IntentHref() {
		return (EAttribute)intentReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentReference_ReferencedElement() {
		return (EReference)intentReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeLabel() {
		return typeLabelEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericUnitFactory getGenericUnitFactory() {
		return (GenericUnitFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		genericUnitEClass = createEClass(GENERIC_UNIT);
		createEReference(genericUnitEClass, GENERIC_UNIT__INSTRUCTIONS);
		createEAttribute(genericUnitEClass, GENERIC_UNIT__UNIT_NAME);

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

		adressedAnnotationEClass = createEClass(ADRESSED_ANNOTATION);
		createEAttribute(adressedAnnotationEClass, ADRESSED_ANNOTATION__RECEIVER);
		createEAttribute(adressedAnnotationEClass, ADRESSED_ANNOTATION__SOURCE);
		createEAttribute(adressedAnnotationEClass, ADRESSED_ANNOTATION__MESSAGE);
		createEAttribute(adressedAnnotationEClass, ADRESSED_ANNOTATION__TYPE);

		intentReferenceEClass = createEClass(INTENT_REFERENCE);
		createEAttribute(intentReferenceEClass, INTENT_REFERENCE__INTENT_HREF);
		createEReference(intentReferenceEClass, INTENT_REFERENCE__REFERENCED_ELEMENT);

		// Create enums
		typeLabelEEnum = createEEnum(TYPE_LABEL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		IntentDocumentPackage theIntentDocumentPackage = (IntentDocumentPackage)EPackage.Registry.INSTANCE
				.getEPackage(IntentDocumentPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		genericUnitEClass.getESuperTypes().add(theIntentDocumentPackage.getIntentGenericElement());
		unitInstructionEClass.getESuperTypes().add(theIntentDocumentPackage.getIntentGenericElement());
		intentReferenceInstructionEClass.getESuperTypes().add(this.getUnitInstruction());
		intentReferenceInstructionEClass.getESuperTypes().add(this.getIntentReference());
		labelDeclarationEClass.getESuperTypes().add(this.getUnitInstruction());
		labelReferenceInstructionEClass.getESuperTypes().add(this.getUnitInstruction());
		labelReferenceInstructionEClass.getESuperTypes().add(this.getIntentReference());
		adressedAnnotationEClass.getESuperTypes().add(this.getUnitInstruction());

		// Initialize classes and features; add operations and parameters
		initEClass(genericUnitEClass, GenericUnit.class, "GenericUnit", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericUnit_Instructions(), this.getUnitInstruction(),
				this.getUnitInstruction_Unit(), "instructions", null, 0, -1, GenericUnit.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenericUnit_UnitName(), ecorePackage.getEString(), "unitName", null, 0, 1,
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

		initEClass(adressedAnnotationEClass, AdressedAnnotation.class, "AdressedAnnotation", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdressedAnnotation_Receiver(), ecorePackage.getEString(), "receiver", null, 1, -1,
				AdressedAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdressedAnnotation_Source(), ecorePackage.getEString(), "source", null, 1, 1,
				AdressedAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdressedAnnotation_Message(), ecorePackage.getEString(), "message", null, 1, 1,
				AdressedAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdressedAnnotation_Type(), ecorePackage.getEString(), "type", null, 1, 1,
				AdressedAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

		// Create resource
		createResource(eNS_URI);
	}

} //GenericUnitPackageImpl
