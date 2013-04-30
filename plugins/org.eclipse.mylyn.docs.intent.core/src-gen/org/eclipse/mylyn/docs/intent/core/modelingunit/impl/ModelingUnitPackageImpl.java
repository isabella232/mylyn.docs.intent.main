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
package org.eclipse.mylyn.docs.intent.core.modelingunit.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.impl.CompilerPackageImpl;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnitPackage;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.impl.DescriptionUnitPackageImpl;
import org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerPackage;
import org.eclipse.mylyn.docs.intent.core.indexer.impl.IntentIndexerPackageImpl;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractMetaTypeInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.IntentReferenceInModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.LabelInModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class ModelingUnitPackageImpl extends EPackageImpl implements ModelingUnitPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass modelingUnitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass modelingUnitInstructionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass resourceDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass abstractMetaTypeInstructionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass typeReferenceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass instanciationInstructionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass structuralFeatureAffectationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass abstractValueEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass nativeValueEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass newObjectValueEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass referenceValueEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass instanciationInstructionReferenceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass contributionInstructionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass externalContentReferenceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass modelingUnitInstructionReferenceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass intentReferenceInModelingUnitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass annotationDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass labelInModelingUnitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass keyValForAnnotationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum affectationOperatorEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()},
	 * which also performs initialization of the package, or returns the registered package, if one already
	 * exists. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModelingUnitPackageImpl() {
		super(eNS_URI, ModelingUnitFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it
	 * depends.
	 * <p>
	 * This method is used to initialize {@link ModelingUnitPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the
	 * package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModelingUnitPackage init() {
		if (isInited)
			return (ModelingUnitPackage)EPackage.Registry.INSTANCE.getEPackage(ModelingUnitPackage.eNS_URI);

		// Obtain or create and register package
		ModelingUnitPackageImpl theModelingUnitPackage = (ModelingUnitPackageImpl)(EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof ModelingUnitPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new ModelingUnitPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MarkupPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CompilerPackageImpl theCompilerPackage = (CompilerPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(CompilerPackage.eNS_URI) instanceof CompilerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(CompilerPackage.eNS_URI) : CompilerPackage.eINSTANCE);
		IntentDocumentPackageImpl theIntentDocumentPackage = (IntentDocumentPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(IntentDocumentPackage.eNS_URI) instanceof IntentDocumentPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(IntentDocumentPackage.eNS_URI) : IntentDocumentPackage.eINSTANCE);
		DescriptionUnitPackageImpl theDescriptionUnitPackage = (DescriptionUnitPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(DescriptionUnitPackage.eNS_URI) instanceof DescriptionUnitPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(DescriptionUnitPackage.eNS_URI) : DescriptionUnitPackage.eINSTANCE);
		IntentIndexerPackageImpl theIntentIndexerPackage = (IntentIndexerPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(IntentIndexerPackage.eNS_URI) instanceof IntentIndexerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(IntentIndexerPackage.eNS_URI) : IntentIndexerPackage.eINSTANCE);

		// Create package meta-data objects
		theModelingUnitPackage.createPackageContents();
		theCompilerPackage.createPackageContents();
		theIntentDocumentPackage.createPackageContents();
		theDescriptionUnitPackage.createPackageContents();
		theIntentIndexerPackage.createPackageContents();

		// Initialize created meta-data
		theModelingUnitPackage.initializePackageContents();
		theCompilerPackage.initializePackageContents();
		theIntentDocumentPackage.initializePackageContents();
		theDescriptionUnitPackage.initializePackageContents();
		theIntentIndexerPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelingUnitPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModelingUnitPackage.eNS_URI, theModelingUnitPackage);
		return theModelingUnitPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getModelingUnit() {
		return modelingUnitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getModelingUnitInstruction() {
		return modelingUnitInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getResourceDeclaration() {
		return resourceDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getResourceDeclaration_Uri() {
		return (EAttribute)resourceDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getResourceDeclaration_Name() {
		return (EAttribute)resourceDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getResourceDeclaration_ContentType() {
		return (EAttribute)resourceDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourceDeclaration_Content() {
		return (EReference)resourceDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAbstractMetaTypeInstruction() {
		return abstractMetaTypeInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAbstractMetaTypeInstruction_MetaType() {
		return (EReference)abstractMetaTypeInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getTypeReference() {
		return typeReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getTypeReference_TypeName() {
		return (EAttribute)typeReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getTypeReference_ResolvedType() {
		return (EReference)typeReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getInstanciationInstruction() {
		return instanciationInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getInstanciationInstruction_Name() {
		return (EAttribute)instanciationInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getInstanciationInstruction_StructuralFeatures() {
		return (EReference)instanciationInstructionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getStructuralFeatureAffectation() {
		return structuralFeatureAffectationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getStructuralFeatureAffectation_Name() {
		return (EAttribute)structuralFeatureAffectationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getStructuralFeatureAffectation_UsedOperator() {
		return (EAttribute)structuralFeatureAffectationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getStructuralFeatureAffectation_Values() {
		return (EReference)structuralFeatureAffectationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAbstractValue() {
		return abstractValueEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getNativeValue() {
		return nativeValueEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNativeValue_Value() {
		return (EAttribute)nativeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getNewObjectValue() {
		return newObjectValueEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getNewObjectValue_Value() {
		return (EReference)newObjectValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getReferenceValue() {
		return referenceValueEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReferenceValue_InstanciationReference() {
		return (EReference)referenceValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReferenceValue_ReferenceType() {
		return (EReference)referenceValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getInstanciationInstructionReference() {
		return instanciationInstructionReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getInstanciationInstructionReference_InstanceName() {
		return (EAttribute)instanciationInstructionReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getInstanciationInstructionReference_Instanciation() {
		return (EReference)instanciationInstructionReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getContributionInstruction() {
		return contributionInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContributionInstruction_ContributionReference() {
		return (EReference)contributionInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContributionInstruction_Contributions() {
		return (EReference)contributionInstructionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExternalContentReference() {
		return externalContentReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getExternalContentReference_ExternalContent() {
		return (EReference)externalContentReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getExternalContentReference_MarkedAsMerged() {
		return (EAttribute)externalContentReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getModelingUnitInstructionReference() {
		return modelingUnitInstructionReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModelingUnitInstructionReference_IntentHref() {
		return (EAttribute)modelingUnitInstructionReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModelingUnitInstructionReference_ReferencedInstruction() {
		return (EReference)modelingUnitInstructionReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getIntentReferenceInModelingUnit() {
		return intentReferenceInModelingUnitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAnnotationDeclaration() {
		return annotationDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAnnotationDeclaration_AnnotationID() {
		return (EAttribute)annotationDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAnnotationDeclaration_Map() {
		return (EReference)annotationDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getLabelInModelingUnit() {
		return labelInModelingUnitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getKeyValForAnnotation() {
		return keyValForAnnotationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getKeyValForAnnotation_Key() {
		return (EAttribute)keyValForAnnotationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getKeyValForAnnotation_Value() {
		return (EAttribute)keyValForAnnotationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getAffectationOperator() {
		return affectationOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ModelingUnitFactory getModelingUnitFactory() {
		return (ModelingUnitFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on any
	 * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		modelingUnitEClass = createEClass(MODELING_UNIT);

		modelingUnitInstructionEClass = createEClass(MODELING_UNIT_INSTRUCTION);

		resourceDeclarationEClass = createEClass(RESOURCE_DECLARATION);
		createEAttribute(resourceDeclarationEClass, RESOURCE_DECLARATION__URI);
		createEAttribute(resourceDeclarationEClass, RESOURCE_DECLARATION__NAME);
		createEAttribute(resourceDeclarationEClass, RESOURCE_DECLARATION__CONTENT_TYPE);
		createEReference(resourceDeclarationEClass, RESOURCE_DECLARATION__CONTENT);

		abstractMetaTypeInstructionEClass = createEClass(ABSTRACT_META_TYPE_INSTRUCTION);
		createEReference(abstractMetaTypeInstructionEClass, ABSTRACT_META_TYPE_INSTRUCTION__META_TYPE);

		typeReferenceEClass = createEClass(TYPE_REFERENCE);
		createEAttribute(typeReferenceEClass, TYPE_REFERENCE__TYPE_NAME);
		createEReference(typeReferenceEClass, TYPE_REFERENCE__RESOLVED_TYPE);

		instanciationInstructionEClass = createEClass(INSTANCIATION_INSTRUCTION);
		createEAttribute(instanciationInstructionEClass, INSTANCIATION_INSTRUCTION__NAME);
		createEReference(instanciationInstructionEClass, INSTANCIATION_INSTRUCTION__STRUCTURAL_FEATURES);

		structuralFeatureAffectationEClass = createEClass(STRUCTURAL_FEATURE_AFFECTATION);
		createEAttribute(structuralFeatureAffectationEClass, STRUCTURAL_FEATURE_AFFECTATION__NAME);
		createEAttribute(structuralFeatureAffectationEClass, STRUCTURAL_FEATURE_AFFECTATION__USED_OPERATOR);
		createEReference(structuralFeatureAffectationEClass, STRUCTURAL_FEATURE_AFFECTATION__VALUES);

		abstractValueEClass = createEClass(ABSTRACT_VALUE);

		nativeValueEClass = createEClass(NATIVE_VALUE);
		createEAttribute(nativeValueEClass, NATIVE_VALUE__VALUE);

		newObjectValueEClass = createEClass(NEW_OBJECT_VALUE);
		createEReference(newObjectValueEClass, NEW_OBJECT_VALUE__VALUE);

		referenceValueEClass = createEClass(REFERENCE_VALUE);
		createEReference(referenceValueEClass, REFERENCE_VALUE__INSTANCIATION_REFERENCE);
		createEReference(referenceValueEClass, REFERENCE_VALUE__REFERENCE_TYPE);

		instanciationInstructionReferenceEClass = createEClass(INSTANCIATION_INSTRUCTION_REFERENCE);
		createEAttribute(instanciationInstructionReferenceEClass,
				INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCE_NAME);
		createEReference(instanciationInstructionReferenceEClass,
				INSTANCIATION_INSTRUCTION_REFERENCE__INSTANCIATION);

		contributionInstructionEClass = createEClass(CONTRIBUTION_INSTRUCTION);
		createEReference(contributionInstructionEClass, CONTRIBUTION_INSTRUCTION__CONTRIBUTION_REFERENCE);
		createEReference(contributionInstructionEClass, CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS);

		externalContentReferenceEClass = createEClass(EXTERNAL_CONTENT_REFERENCE);
		createEReference(externalContentReferenceEClass, EXTERNAL_CONTENT_REFERENCE__EXTERNAL_CONTENT);
		createEAttribute(externalContentReferenceEClass, EXTERNAL_CONTENT_REFERENCE__MARKED_AS_MERGED);

		modelingUnitInstructionReferenceEClass = createEClass(MODELING_UNIT_INSTRUCTION_REFERENCE);
		createEAttribute(modelingUnitInstructionReferenceEClass,
				MODELING_UNIT_INSTRUCTION_REFERENCE__INTENT_HREF);
		createEReference(modelingUnitInstructionReferenceEClass,
				MODELING_UNIT_INSTRUCTION_REFERENCE__REFERENCED_INSTRUCTION);

		intentReferenceInModelingUnitEClass = createEClass(INTENT_REFERENCE_IN_MODELING_UNIT);

		annotationDeclarationEClass = createEClass(ANNOTATION_DECLARATION);
		createEAttribute(annotationDeclarationEClass, ANNOTATION_DECLARATION__ANNOTATION_ID);
		createEReference(annotationDeclarationEClass, ANNOTATION_DECLARATION__MAP);

		labelInModelingUnitEClass = createEClass(LABEL_IN_MODELING_UNIT);

		keyValForAnnotationEClass = createEClass(KEY_VAL_FOR_ANNOTATION);
		createEAttribute(keyValForAnnotationEClass, KEY_VAL_FOR_ANNOTATION__KEY);
		createEAttribute(keyValForAnnotationEClass, KEY_VAL_FOR_ANNOTATION__VALUE);

		// Create enums
		affectationOperatorEEnum = createEEnum(AFFECTATION_OPERATOR);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect
	 * on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
		modelingUnitEClass.getESuperTypes().add(theIntentDocumentPackage.getGenericUnit());
		modelingUnitInstructionEClass.getESuperTypes().add(theIntentDocumentPackage.getUnitInstruction());
		resourceDeclarationEClass.getESuperTypes().add(this.getModelingUnitInstruction());
		abstractMetaTypeInstructionEClass.getESuperTypes().add(this.getModelingUnitInstruction());
		instanciationInstructionEClass.getESuperTypes().add(this.getAbstractMetaTypeInstruction());
		structuralFeatureAffectationEClass.getESuperTypes().add(this.getAbstractMetaTypeInstruction());
		abstractValueEClass.getESuperTypes().add(this.getModelingUnitInstruction());
		nativeValueEClass.getESuperTypes().add(this.getAbstractValue());
		newObjectValueEClass.getESuperTypes().add(this.getAbstractValue());
		referenceValueEClass.getESuperTypes().add(this.getAbstractValue());
		contributionInstructionEClass.getESuperTypes().add(this.getModelingUnitInstruction());
		externalContentReferenceEClass.getESuperTypes().add(this.getResourceDeclaration());
		intentReferenceInModelingUnitEClass.getESuperTypes().add(
				theIntentDocumentPackage.getIntentReferenceInstruction());
		intentReferenceInModelingUnitEClass.getESuperTypes().add(this.getModelingUnitInstruction());
		annotationDeclarationEClass.getESuperTypes().add(this.getModelingUnitInstruction());
		annotationDeclarationEClass.getESuperTypes().add(theIntentDocumentPackage.getIntentReference());
		labelInModelingUnitEClass.getESuperTypes().add(theIntentDocumentPackage.getLabelDeclaration());
		labelInModelingUnitEClass.getESuperTypes().add(this.getModelingUnitInstruction());
		labelInModelingUnitEClass.getESuperTypes().add(theIntentDocumentPackage.getIntentReference());

		// Initialize classes and features; add operations and parameters
		initEClass(modelingUnitEClass, ModelingUnit.class, "ModelingUnit", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelingUnitInstructionEClass, ModelingUnitInstruction.class, "ModelingUnitInstruction",
				IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(resourceDeclarationEClass, ResourceDeclaration.class, "ResourceDeclaration", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceDeclaration_Uri(), theIntentDocumentPackage.getURI(), "uri", null, 0, 1,
				ResourceDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceDeclaration_Name(), ecorePackage.getEString(), "name", null, 0, 1,
				ResourceDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceDeclaration_ContentType(), ecorePackage.getEString(), "contentType", null,
				0, 1, ResourceDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceDeclaration_Content(), this.getModelingUnitInstructionReference(), null,
				"content", null, 0, -1, ResourceDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(abstractMetaTypeInstructionEClass, AbstractMetaTypeInstruction.class,
				"AbstractMetaTypeInstruction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractMetaTypeInstruction_MetaType(), this.getTypeReference(), null, "metaType",
				null, 0, 1, AbstractMetaTypeInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeReferenceEClass, TypeReference.class, "TypeReference", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTypeReference_TypeName(), ecorePackage.getEString(), "typeName", null, 0, 1,
				TypeReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeReference_ResolvedType(), ecorePackage.getEClass(), null, "resolvedType", null,
				0, 1, TypeReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(instanciationInstructionEClass, InstanciationInstruction.class,
				"InstanciationInstruction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInstanciationInstruction_Name(), ecorePackage.getEString(), "name", null, 0, 1,
				InstanciationInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstanciationInstruction_StructuralFeatures(),
				this.getStructuralFeatureAffectation(), null, "structuralFeatures", null, 0, -1,
				InstanciationInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(structuralFeatureAffectationEClass, StructuralFeatureAffectation.class,
				"StructuralFeatureAffectation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStructuralFeatureAffectation_Name(), ecorePackage.getEString(), "name", null, 0, 1,
				StructuralFeatureAffectation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStructuralFeatureAffectation_UsedOperator(), this.getAffectationOperator(),
				"usedOperator", "SINGLE_VALUED_AFFECTATION", 1, 1, StructuralFeatureAffectation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getStructuralFeatureAffectation_Values(), this.getAbstractValue(), null, "values",
				null, 1, -1, StructuralFeatureAffectation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractValueEClass, AbstractValue.class, "AbstractValue", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(nativeValueEClass, NativeValue.class, "NativeValue", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNativeValue_Value(), ecorePackage.getEString(), "value", null, 0, 1,
				NativeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(newObjectValueEClass, NewObjectValue.class, "NewObjectValue", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNewObjectValue_Value(), this.getInstanciationInstruction(), null, "value", null, 1,
				1, NewObjectValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceValueEClass, ReferenceValue.class, "ReferenceValue", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferenceValue_InstanciationReference(),
				this.getInstanciationInstructionReference(), null, "instanciationReference", null, 1, 1,
				ReferenceValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReferenceValue_ReferenceType(), ecorePackage.getEObject(), null, "referenceType",
				null, 0, 1, ReferenceValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(instanciationInstructionReferenceEClass, InstanciationInstructionReference.class,
				"InstanciationInstructionReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInstanciationInstructionReference_InstanceName(), ecorePackage.getEString(),
				"instanceName", null, 0, 1, InstanciationInstructionReference.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstanciationInstructionReference_Instanciation(),
				this.getInstanciationInstruction(), null, "instanciation", null, 0, 1,
				InstanciationInstructionReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contributionInstructionEClass, ContributionInstruction.class, "ContributionInstruction",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContributionInstruction_ContributionReference(),
				this.getModelingUnitInstructionReference(), null, "contributionReference", null, 1, 1,
				ContributionInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionInstruction_Contributions(), this.getModelingUnitInstruction(), null,
				"contributions", null, 0, -1, ContributionInstruction.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(externalContentReferenceEClass, ExternalContentReference.class,
				"ExternalContentReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalContentReference_ExternalContent(), ecorePackage.getEObject(), null,
				"externalContent", null, 0, 1, ExternalContentReference.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getExternalContentReference_MarkedAsMerged(), ecorePackage.getEBoolean(),
				"markedAsMerged", null, 0, 1, ExternalContentReference.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelingUnitInstructionReferenceEClass, ModelingUnitInstructionReference.class,
				"ModelingUnitInstructionReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelingUnitInstructionReference_IntentHref(), ecorePackage.getEString(),
				"intentHref", null, 0, 1, ModelingUnitInstructionReference.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelingUnitInstructionReference_ReferencedInstruction(),
				this.getModelingUnitInstruction(), null, "referencedInstruction", null, 0, 1,
				ModelingUnitInstructionReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intentReferenceInModelingUnitEClass, IntentReferenceInModelingUnit.class,
				"IntentReferenceInModelingUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(annotationDeclarationEClass, AnnotationDeclaration.class, "AnnotationDeclaration",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnnotationDeclaration_AnnotationID(), ecorePackage.getEString(), "annotationID",
				null, 1, 1, AnnotationDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnnotationDeclaration_Map(), this.getKeyValForAnnotation(), null, "map", null, 0,
				-1, AnnotationDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelInModelingUnitEClass, LabelInModelingUnit.class, "LabelInModelingUnit", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(keyValForAnnotationEClass, Map.Entry.class, "KeyValForAnnotation", !IS_ABSTRACT,
				!IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKeyValForAnnotation_Key(), ecorePackage.getEString(), "key", null, 0, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyValForAnnotation_Value(), ecorePackage.getEString(), "value", null, 0, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(affectationOperatorEEnum, AffectationOperator.class, "AffectationOperator");
		addEEnumLiteral(affectationOperatorEEnum, AffectationOperator.SINGLE_VALUED_AFFECTATION);
		addEEnumLiteral(affectationOperatorEEnum, AffectationOperator.MULTI_VALUED_AFFECTATION);

		// Create resource
		createResource(eNS_URI);
	}

} // ModelingUnitPackageImpl
