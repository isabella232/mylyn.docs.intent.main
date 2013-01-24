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
package org.eclipse.mylyn.docs.intent.core.compiler.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.mylyn.docs.intent.core.compiler.AttributeChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationInformationHolder;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationMessageType;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusManager;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusSeverity;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry;
import org.eclipse.mylyn.docs.intent.core.compiler.ModelElementChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.StringToEObjectMap;
import org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndexEntry;
import org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedContributionHolder;
import org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedReferenceHolder;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnitPackage;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.impl.DescriptionUnitPackageImpl;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.impl.IntentDocumentPackageImpl;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.genericunit.impl.GenericUnitPackageImpl;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerPackage;
import org.eclipse.mylyn.docs.intent.core.indexer.impl.IntentIndexerPackageImpl;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ModelingUnitPackageImpl;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class CompilerPackageImpl extends EPackageImpl implements CompilerPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eStringToEObjectEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textualReferenceToContributionsEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToEObjectMapEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eTypeToStringToEObjectMapEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eObjectToUnresolvedReferencesListEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceToContainedElementsMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelingUnitToStatusListEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass createdElementToInstructionMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedReferenceHolderEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compilationStatusEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compilationStatusManagerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compilationInformationHolderEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedContributionHolderEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceabilityIndexEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceabilityIndexEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compiledElementToInstructionEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instructionTraceabilityEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureToAffectationEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceChangeStatusEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementChangeStatusEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structuralFeatureChangeStatusEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceChangeStatusEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeChangeStatusEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass synchronizerCompilationStatusEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum compilationStatusSeverityEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum compilationMessageTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum synchronizerResourceStateEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum synchronizerChangeStateEEnum = null;

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
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CompilerPackageImpl() {
		super(eNS_URI, CompilerFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CompilerPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CompilerPackage init() {
		if (isInited)
			return (CompilerPackage)EPackage.Registry.INSTANCE.getEPackage(CompilerPackage.eNS_URI);

		// Obtain or create and register package
		CompilerPackageImpl theCompilerPackage = (CompilerPackageImpl)(EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof CompilerPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new CompilerPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MarkupPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		IntentDocumentPackageImpl theIntentDocumentPackage = (IntentDocumentPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(IntentDocumentPackage.eNS_URI) instanceof IntentDocumentPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(IntentDocumentPackage.eNS_URI) : IntentDocumentPackage.eINSTANCE);
		IntentIndexerPackageImpl theIntentIndexerPackage = (IntentIndexerPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(IntentIndexerPackage.eNS_URI) instanceof IntentIndexerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(IntentIndexerPackage.eNS_URI) : IntentIndexerPackage.eINSTANCE);
		GenericUnitPackageImpl theGenericUnitPackage = (GenericUnitPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(GenericUnitPackage.eNS_URI) instanceof GenericUnitPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(GenericUnitPackage.eNS_URI) : GenericUnitPackage.eINSTANCE);
		DescriptionUnitPackageImpl theDescriptionUnitPackage = (DescriptionUnitPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(DescriptionUnitPackage.eNS_URI) instanceof DescriptionUnitPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(DescriptionUnitPackage.eNS_URI) : DescriptionUnitPackage.eINSTANCE);
		ModelingUnitPackageImpl theModelingUnitPackage = (ModelingUnitPackageImpl)(EPackage.Registry.INSTANCE
				.getEPackage(ModelingUnitPackage.eNS_URI) instanceof ModelingUnitPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(ModelingUnitPackage.eNS_URI) : ModelingUnitPackage.eINSTANCE);

		// Create package meta-data objects
		theCompilerPackage.createPackageContents();
		theIntentDocumentPackage.createPackageContents();
		theIntentIndexerPackage.createPackageContents();
		theGenericUnitPackage.createPackageContents();
		theDescriptionUnitPackage.createPackageContents();
		theModelingUnitPackage.createPackageContents();

		// Initialize created meta-data
		theCompilerPackage.initializePackageContents();
		theIntentDocumentPackage.initializePackageContents();
		theIntentIndexerPackage.initializePackageContents();
		theGenericUnitPackage.initializePackageContents();
		theDescriptionUnitPackage.initializePackageContents();
		theModelingUnitPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCompilerPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CompilerPackage.eNS_URI, theCompilerPackage);
		return theCompilerPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEStringToEObject() {
		return eStringToEObjectEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEStringToEObject_Key() {
		return (EAttribute)eStringToEObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEStringToEObject_Value() {
		return (EReference)eStringToEObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextualReferenceToContributions() {
		return textualReferenceToContributionsEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextualReferenceToContributions_Key() {
		return (EAttribute)textualReferenceToContributionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTextualReferenceToContributions_Value() {
		return (EReference)textualReferenceToContributionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToEObjectMap() {
		return stringToEObjectMapEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToEObjectMap_NameToElement() {
		return (EReference)stringToEObjectMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getETypeToStringToEObjectMap() {
		return eTypeToStringToEObjectMapEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getETypeToStringToEObjectMap_Key() {
		return (EReference)eTypeToStringToEObjectMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getETypeToStringToEObjectMap_Value() {
		return (EReference)eTypeToStringToEObjectMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEObjectToUnresolvedReferencesList() {
		return eObjectToUnresolvedReferencesListEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEObjectToUnresolvedReferencesList_Key() {
		return (EReference)eObjectToUnresolvedReferencesListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEObjectToUnresolvedReferencesList_Value() {
		return (EReference)eObjectToUnresolvedReferencesListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceToContainedElementsMapEntry() {
		return resourceToContainedElementsMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceToContainedElementsMapEntry_Key() {
		return (EReference)resourceToContainedElementsMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceToContainedElementsMapEntry_Value() {
		return (EReference)resourceToContainedElementsMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelingUnitToStatusList() {
		return modelingUnitToStatusListEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelingUnitToStatusList_Key() {
		return (EReference)modelingUnitToStatusListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelingUnitToStatusList_Value() {
		return (EReference)modelingUnitToStatusListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreatedElementToInstructionMapEntry() {
		return createdElementToInstructionMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCreatedElementToInstructionMapEntry_Key() {
		return (EReference)createdElementToInstructionMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCreatedElementToInstructionMapEntry_Value() {
		return (EReference)createdElementToInstructionMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedReferenceHolder() {
		return unresolvedReferenceHolderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnresolvedReferenceHolder_TextualReference() {
		return (EAttribute)unresolvedReferenceHolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnresolvedReferenceHolder_ContainmentReference() {
		return (EAttribute)unresolvedReferenceHolderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnresolvedReferenceHolder_InstructionContainer() {
		return (EReference)unresolvedReferenceHolderEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnresolvedReferenceHolder_ConcernedFeature() {
		return (EReference)unresolvedReferenceHolderEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompilationStatus() {
		return compilationStatusEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationStatus_Target() {
		return (EReference)compilationStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompilationStatus_Message() {
		return (EAttribute)compilationStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompilationStatus_Severity() {
		return (EAttribute)compilationStatusEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompilationStatus_Type() {
		return (EAttribute)compilationStatusEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompilationStatusManager() {
		return compilationStatusManagerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationStatusManager_CompilationStatusList() {
		return (EReference)compilationStatusManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationStatusManager_ModelingUnitToStatusList() {
		return (EReference)compilationStatusManagerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompilationStatusManager_ValidationTime() {
		return (EAttribute)compilationStatusManagerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompilationInformationHolder() {
		return compilationInformationHolderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationInformationHolder_GeneratedElementList() {
		return (EReference)compilationInformationHolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationInformationHolder_ElementToUnresolvedReferenceMap() {
		return (EReference)compilationInformationHolderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationInformationHolder_TypeToNameToElementsMap() {
		return (EReference)compilationInformationHolderEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationInformationHolder_CreatedElementsToInstructions() {
		return (EReference)compilationInformationHolderEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationInformationHolder_CurrentGeneratedElementList() {
		return (EReference)compilationInformationHolderEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationInformationHolder_ResourceToContainedElements() {
		return (EReference)compilationInformationHolderEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationInformationHolder_UnresolvedContributions() {
		return (EReference)compilationInformationHolderEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedContributionHolder() {
		return unresolvedContributionHolderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnresolvedContributionHolder_Resolved() {
		return (EAttribute)unresolvedContributionHolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnresolvedContributionHolder_ReferencedContribution() {
		return (EReference)unresolvedContributionHolderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTraceabilityIndex() {
		return traceabilityIndexEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceabilityIndex_Entries() {
		return (EReference)traceabilityIndexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTraceabilityIndexEntry() {
		return traceabilityIndexEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTraceabilityIndexEntry_GeneratedResourcePath() {
		return (EAttribute)traceabilityIndexEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTraceabilityIndexEntry_CompilationTime() {
		return (EAttribute)traceabilityIndexEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceabilityIndexEntry_ResourceDeclaration() {
		return (EReference)traceabilityIndexEntryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceabilityIndexEntry_ContainedElementToInstructions() {
		return (EReference)traceabilityIndexEntryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompiledElementToInstructionEntry() {
		return compiledElementToInstructionEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompiledElementToInstructionEntry_Key() {
		return (EReference)compiledElementToInstructionEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompiledElementToInstructionEntry_Value() {
		return (EReference)compiledElementToInstructionEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstructionTraceabilityEntry() {
		return instructionTraceabilityEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstructionTraceabilityEntry_Instruction() {
		return (EReference)instructionTraceabilityEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstructionTraceabilityEntry_Features() {
		return (EReference)instructionTraceabilityEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeatureToAffectationEntry() {
		return featureToAffectationEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureToAffectationEntry_Key() {
		return (EAttribute)featureToAffectationEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeatureToAffectationEntry_Value() {
		return (EReference)featureToAffectationEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceChangeStatus() {
		return resourceChangeStatusEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceChangeStatus_WorkingCopyResourceState() {
		return (EAttribute)resourceChangeStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceChangeStatus_CompiledResourceState() {
		return (EAttribute)resourceChangeStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelElementChangeStatus() {
		return modelElementChangeStatusEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelElementChangeStatus_ChangeState() {
		return (EAttribute)modelElementChangeStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElementChangeStatus_CompiledParent() {
		return (EReference)modelElementChangeStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElementChangeStatus_CompiledElement() {
		return (EReference)modelElementChangeStatusEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelElementChangeStatus_WorkingCopyParentURIFragment() {
		return (EAttribute)modelElementChangeStatusEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelElementChangeStatus_WorkingCopyElementURIFragment() {
		return (EAttribute)modelElementChangeStatusEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructuralFeatureChangeStatus() {
		return structuralFeatureChangeStatusEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStructuralFeatureChangeStatus_ChangeState() {
		return (EAttribute)structuralFeatureChangeStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStructuralFeatureChangeStatus_FeatureName() {
		return (EAttribute)structuralFeatureChangeStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuralFeatureChangeStatus_CompiledElement() {
		return (EReference)structuralFeatureChangeStatusEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStructuralFeatureChangeStatus_WorkingCopyElementURIFragment() {
		return (EAttribute)structuralFeatureChangeStatusEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceChangeStatus() {
		return referenceChangeStatusEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceChangeStatus_CompiledTarget() {
		return (EReference)referenceChangeStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceChangeStatus_WorkingCopyTargetURIFragment() {
		return (EAttribute)referenceChangeStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeChangeStatus() {
		return attributeChangeStatusEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSynchronizerCompilationStatus() {
		return synchronizerCompilationStatusEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSynchronizerCompilationStatus_WorkingCopyResourceURI() {
		return (EAttribute)synchronizerCompilationStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSynchronizerCompilationStatus_CompiledResourceURI() {
		return (EAttribute)synchronizerCompilationStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCompilationStatusSeverity() {
		return compilationStatusSeverityEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCompilationMessageType() {
		return compilationMessageTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSynchronizerResourceState() {
		return synchronizerResourceStateEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSynchronizerChangeState() {
		return synchronizerChangeStateEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CompilerFactory getCompilerFactory() {
		return (CompilerFactory)getEFactoryInstance();
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
		eStringToEObjectEClass = createEClass(ESTRING_TO_EOBJECT);
		createEAttribute(eStringToEObjectEClass, ESTRING_TO_EOBJECT__KEY);
		createEReference(eStringToEObjectEClass, ESTRING_TO_EOBJECT__VALUE);

		textualReferenceToContributionsEClass = createEClass(TEXTUAL_REFERENCE_TO_CONTRIBUTIONS);
		createEAttribute(textualReferenceToContributionsEClass, TEXTUAL_REFERENCE_TO_CONTRIBUTIONS__KEY);
		createEReference(textualReferenceToContributionsEClass, TEXTUAL_REFERENCE_TO_CONTRIBUTIONS__VALUE);

		stringToEObjectMapEClass = createEClass(STRING_TO_EOBJECT_MAP);
		createEReference(stringToEObjectMapEClass, STRING_TO_EOBJECT_MAP__NAME_TO_ELEMENT);

		eTypeToStringToEObjectMapEClass = createEClass(ETYPE_TO_STRING_TO_EOBJECT_MAP);
		createEReference(eTypeToStringToEObjectMapEClass, ETYPE_TO_STRING_TO_EOBJECT_MAP__KEY);
		createEReference(eTypeToStringToEObjectMapEClass, ETYPE_TO_STRING_TO_EOBJECT_MAP__VALUE);

		eObjectToUnresolvedReferencesListEClass = createEClass(EOBJECT_TO_UNRESOLVED_REFERENCES_LIST);
		createEReference(eObjectToUnresolvedReferencesListEClass, EOBJECT_TO_UNRESOLVED_REFERENCES_LIST__KEY);
		createEReference(eObjectToUnresolvedReferencesListEClass,
				EOBJECT_TO_UNRESOLVED_REFERENCES_LIST__VALUE);

		resourceToContainedElementsMapEntryEClass = createEClass(RESOURCE_TO_CONTAINED_ELEMENTS_MAP_ENTRY);
		createEReference(resourceToContainedElementsMapEntryEClass,
				RESOURCE_TO_CONTAINED_ELEMENTS_MAP_ENTRY__KEY);
		createEReference(resourceToContainedElementsMapEntryEClass,
				RESOURCE_TO_CONTAINED_ELEMENTS_MAP_ENTRY__VALUE);

		modelingUnitToStatusListEClass = createEClass(MODELING_UNIT_TO_STATUS_LIST);
		createEReference(modelingUnitToStatusListEClass, MODELING_UNIT_TO_STATUS_LIST__KEY);
		createEReference(modelingUnitToStatusListEClass, MODELING_UNIT_TO_STATUS_LIST__VALUE);

		createdElementToInstructionMapEntryEClass = createEClass(CREATED_ELEMENT_TO_INSTRUCTION_MAP_ENTRY);
		createEReference(createdElementToInstructionMapEntryEClass,
				CREATED_ELEMENT_TO_INSTRUCTION_MAP_ENTRY__KEY);
		createEReference(createdElementToInstructionMapEntryEClass,
				CREATED_ELEMENT_TO_INSTRUCTION_MAP_ENTRY__VALUE);

		unresolvedReferenceHolderEClass = createEClass(UNRESOLVED_REFERENCE_HOLDER);
		createEAttribute(unresolvedReferenceHolderEClass, UNRESOLVED_REFERENCE_HOLDER__TEXTUAL_REFERENCE);
		createEAttribute(unresolvedReferenceHolderEClass, UNRESOLVED_REFERENCE_HOLDER__CONTAINMENT_REFERENCE);
		createEReference(unresolvedReferenceHolderEClass, UNRESOLVED_REFERENCE_HOLDER__INSTRUCTION_CONTAINER);
		createEReference(unresolvedReferenceHolderEClass, UNRESOLVED_REFERENCE_HOLDER__CONCERNED_FEATURE);

		compilationStatusEClass = createEClass(COMPILATION_STATUS);
		createEReference(compilationStatusEClass, COMPILATION_STATUS__TARGET);
		createEAttribute(compilationStatusEClass, COMPILATION_STATUS__MESSAGE);
		createEAttribute(compilationStatusEClass, COMPILATION_STATUS__SEVERITY);
		createEAttribute(compilationStatusEClass, COMPILATION_STATUS__TYPE);

		compilationStatusManagerEClass = createEClass(COMPILATION_STATUS_MANAGER);
		createEReference(compilationStatusManagerEClass, COMPILATION_STATUS_MANAGER__COMPILATION_STATUS_LIST);
		createEReference(compilationStatusManagerEClass,
				COMPILATION_STATUS_MANAGER__MODELING_UNIT_TO_STATUS_LIST);
		createEAttribute(compilationStatusManagerEClass, COMPILATION_STATUS_MANAGER__VALIDATION_TIME);

		compilationInformationHolderEClass = createEClass(COMPILATION_INFORMATION_HOLDER);
		createEReference(compilationInformationHolderEClass,
				COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST);
		createEReference(compilationInformationHolderEClass,
				COMPILATION_INFORMATION_HOLDER__ELEMENT_TO_UNRESOLVED_REFERENCE_MAP);
		createEReference(compilationInformationHolderEClass,
				COMPILATION_INFORMATION_HOLDER__TYPE_TO_NAME_TO_ELEMENTS_MAP);
		createEReference(compilationInformationHolderEClass,
				COMPILATION_INFORMATION_HOLDER__CREATED_ELEMENTS_TO_INSTRUCTIONS);
		createEReference(compilationInformationHolderEClass,
				COMPILATION_INFORMATION_HOLDER__CURRENT_GENERATED_ELEMENT_LIST);
		createEReference(compilationInformationHolderEClass,
				COMPILATION_INFORMATION_HOLDER__RESOURCE_TO_CONTAINED_ELEMENTS);
		createEReference(compilationInformationHolderEClass,
				COMPILATION_INFORMATION_HOLDER__UNRESOLVED_CONTRIBUTIONS);

		unresolvedContributionHolderEClass = createEClass(UNRESOLVED_CONTRIBUTION_HOLDER);
		createEAttribute(unresolvedContributionHolderEClass, UNRESOLVED_CONTRIBUTION_HOLDER__RESOLVED);
		createEReference(unresolvedContributionHolderEClass,
				UNRESOLVED_CONTRIBUTION_HOLDER__REFERENCED_CONTRIBUTION);

		traceabilityIndexEClass = createEClass(TRACEABILITY_INDEX);
		createEReference(traceabilityIndexEClass, TRACEABILITY_INDEX__ENTRIES);

		traceabilityIndexEntryEClass = createEClass(TRACEABILITY_INDEX_ENTRY);
		createEAttribute(traceabilityIndexEntryEClass, TRACEABILITY_INDEX_ENTRY__GENERATED_RESOURCE_PATH);
		createEAttribute(traceabilityIndexEntryEClass, TRACEABILITY_INDEX_ENTRY__COMPILATION_TIME);
		createEReference(traceabilityIndexEntryEClass, TRACEABILITY_INDEX_ENTRY__RESOURCE_DECLARATION);
		createEReference(traceabilityIndexEntryEClass,
				TRACEABILITY_INDEX_ENTRY__CONTAINED_ELEMENT_TO_INSTRUCTIONS);

		compiledElementToInstructionEntryEClass = createEClass(COMPILED_ELEMENT_TO_INSTRUCTION_ENTRY);
		createEReference(compiledElementToInstructionEntryEClass, COMPILED_ELEMENT_TO_INSTRUCTION_ENTRY__KEY);
		createEReference(compiledElementToInstructionEntryEClass,
				COMPILED_ELEMENT_TO_INSTRUCTION_ENTRY__VALUE);

		instructionTraceabilityEntryEClass = createEClass(INSTRUCTION_TRACEABILITY_ENTRY);
		createEReference(instructionTraceabilityEntryEClass, INSTRUCTION_TRACEABILITY_ENTRY__INSTRUCTION);
		createEReference(instructionTraceabilityEntryEClass, INSTRUCTION_TRACEABILITY_ENTRY__FEATURES);

		featureToAffectationEntryEClass = createEClass(FEATURE_TO_AFFECTATION_ENTRY);
		createEAttribute(featureToAffectationEntryEClass, FEATURE_TO_AFFECTATION_ENTRY__KEY);
		createEReference(featureToAffectationEntryEClass, FEATURE_TO_AFFECTATION_ENTRY__VALUE);

		resourceChangeStatusEClass = createEClass(RESOURCE_CHANGE_STATUS);
		createEAttribute(resourceChangeStatusEClass, RESOURCE_CHANGE_STATUS__WORKING_COPY_RESOURCE_STATE);
		createEAttribute(resourceChangeStatusEClass, RESOURCE_CHANGE_STATUS__COMPILED_RESOURCE_STATE);

		modelElementChangeStatusEClass = createEClass(MODEL_ELEMENT_CHANGE_STATUS);
		createEAttribute(modelElementChangeStatusEClass, MODEL_ELEMENT_CHANGE_STATUS__CHANGE_STATE);
		createEReference(modelElementChangeStatusEClass, MODEL_ELEMENT_CHANGE_STATUS__COMPILED_PARENT);
		createEReference(modelElementChangeStatusEClass, MODEL_ELEMENT_CHANGE_STATUS__COMPILED_ELEMENT);
		createEAttribute(modelElementChangeStatusEClass,
				MODEL_ELEMENT_CHANGE_STATUS__WORKING_COPY_PARENT_URI_FRAGMENT);
		createEAttribute(modelElementChangeStatusEClass,
				MODEL_ELEMENT_CHANGE_STATUS__WORKING_COPY_ELEMENT_URI_FRAGMENT);

		structuralFeatureChangeStatusEClass = createEClass(STRUCTURAL_FEATURE_CHANGE_STATUS);
		createEAttribute(structuralFeatureChangeStatusEClass, STRUCTURAL_FEATURE_CHANGE_STATUS__CHANGE_STATE);
		createEAttribute(structuralFeatureChangeStatusEClass, STRUCTURAL_FEATURE_CHANGE_STATUS__FEATURE_NAME);
		createEReference(structuralFeatureChangeStatusEClass,
				STRUCTURAL_FEATURE_CHANGE_STATUS__COMPILED_ELEMENT);
		createEAttribute(structuralFeatureChangeStatusEClass,
				STRUCTURAL_FEATURE_CHANGE_STATUS__WORKING_COPY_ELEMENT_URI_FRAGMENT);

		referenceChangeStatusEClass = createEClass(REFERENCE_CHANGE_STATUS);
		createEReference(referenceChangeStatusEClass, REFERENCE_CHANGE_STATUS__COMPILED_TARGET);
		createEAttribute(referenceChangeStatusEClass,
				REFERENCE_CHANGE_STATUS__WORKING_COPY_TARGET_URI_FRAGMENT);

		attributeChangeStatusEClass = createEClass(ATTRIBUTE_CHANGE_STATUS);

		synchronizerCompilationStatusEClass = createEClass(SYNCHRONIZER_COMPILATION_STATUS);
		createEAttribute(synchronizerCompilationStatusEClass,
				SYNCHRONIZER_COMPILATION_STATUS__COMPILED_RESOURCE_URI);
		createEAttribute(synchronizerCompilationStatusEClass,
				SYNCHRONIZER_COMPILATION_STATUS__WORKING_COPY_RESOURCE_URI);

		// Create enums
		compilationStatusSeverityEEnum = createEEnum(COMPILATION_STATUS_SEVERITY);
		compilationMessageTypeEEnum = createEEnum(COMPILATION_MESSAGE_TYPE);
		synchronizerResourceStateEEnum = createEEnum(SYNCHRONIZER_RESOURCE_STATE);
		synchronizerChangeStateEEnum = createEEnum(SYNCHRONIZER_CHANGE_STATE);
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
		ModelingUnitPackage theModelingUnitPackage = (ModelingUnitPackage)EPackage.Registry.INSTANCE
				.getEPackage(ModelingUnitPackage.eNS_URI);
		GenericUnitPackage theGenericUnitPackage = (GenericUnitPackage)EPackage.Registry.INSTANCE
				.getEPackage(GenericUnitPackage.eNS_URI);
		IntentDocumentPackage theIntentDocumentPackage = (IntentDocumentPackage)EPackage.Registry.INSTANCE
				.getEPackage(IntentDocumentPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		resourceChangeStatusEClass.getESuperTypes().add(this.getSynchronizerCompilationStatus());
		modelElementChangeStatusEClass.getESuperTypes().add(this.getSynchronizerCompilationStatus());
		structuralFeatureChangeStatusEClass.getESuperTypes().add(this.getSynchronizerCompilationStatus());
		referenceChangeStatusEClass.getESuperTypes().add(this.getStructuralFeatureChangeStatus());
		attributeChangeStatusEClass.getESuperTypes().add(this.getStructuralFeatureChangeStatus());
		synchronizerCompilationStatusEClass.getESuperTypes().add(this.getCompilationStatus());

		// Initialize classes and features; add operations and parameters
		initEClass(eStringToEObjectEClass, Map.Entry.class, "EStringToEObject", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEStringToEObject_Key(), ecorePackage.getEString(), "key", null, 0, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEStringToEObject_Value(), ecorePackage.getEObject(), null, "value", null, 0, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textualReferenceToContributionsEClass, Map.Entry.class, "TextualReferenceToContributions",
				!IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextualReferenceToContributions_Key(), ecorePackage.getEString(), "key", null, 0,
				1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTextualReferenceToContributions_Value(), this.getUnresolvedContributionHolder(),
				null, "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToEObjectMapEClass, StringToEObjectMap.class, "StringToEObjectMap", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStringToEObjectMap_NameToElement(), this.getEStringToEObject(), null,
				"nameToElement", null, 0, -1, StringToEObjectMap.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(eTypeToStringToEObjectMapEClass, Map.Entry.class, "ETypeToStringToEObjectMap",
				!IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getETypeToStringToEObjectMap_Key(), ecorePackage.getEClassifier(), null, "key", null,
				0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getETypeToStringToEObjectMap_Value(), this.getStringToEObjectMap(), null, "value",
				null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eObjectToUnresolvedReferencesListEClass, Map.Entry.class,
				"EObjectToUnresolvedReferencesList", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEObjectToUnresolvedReferencesList_Key(), ecorePackage.getEObject(), null, "key",
				null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEObjectToUnresolvedReferencesList_Value(), this.getUnresolvedReferenceHolder(),
				null, "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceToContainedElementsMapEntryEClass, Map.Entry.class,
				"ResourceToContainedElementsMapEntry", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceToContainedElementsMapEntry_Key(),
				theModelingUnitPackage.getResourceDeclaration(), null, "key", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceToContainedElementsMapEntry_Value(), ecorePackage.getEObject(), null,
				"value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelingUnitToStatusListEClass, Map.Entry.class, "ModelingUnitToStatusList", !IS_ABSTRACT,
				!IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelingUnitToStatusList_Key(), theModelingUnitPackage.getModelingUnit(), null,
				"key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelingUnitToStatusList_Value(), this.getCompilationStatus(), null, "value", null,
				0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(createdElementToInstructionMapEntryEClass, Map.Entry.class,
				"CreatedElementToInstructionMapEntry", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCreatedElementToInstructionMapEntry_Key(), ecorePackage.getEObject(), null, "key",
				null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCreatedElementToInstructionMapEntry_Value(),
				theGenericUnitPackage.getUnitInstruction(), null, "value", null, 0, -1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unresolvedReferenceHolderEClass, UnresolvedReferenceHolder.class,
				"UnresolvedReferenceHolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnresolvedReferenceHolder_TextualReference(), ecorePackage.getEString(),
				"textualReference", null, 1, 1, UnresolvedReferenceHolder.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnresolvedReferenceHolder_ContainmentReference(), ecorePackage.getEBoolean(),
				"containmentReference", null, 1, 1, UnresolvedReferenceHolder.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnresolvedReferenceHolder_InstructionContainer(),
				theGenericUnitPackage.getUnitInstruction(), null, "instructionContainer", null, 1, 1,
				UnresolvedReferenceHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnresolvedReferenceHolder_ConcernedFeature(), ecorePackage.getEStructuralFeature(),
				null, "concernedFeature", null, 1, 1, UnresolvedReferenceHolder.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(compilationStatusEClass, CompilationStatus.class, "CompilationStatus", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompilationStatus_Target(), theIntentDocumentPackage.getIntentGenericElement(),
				theIntentDocumentPackage.getIntentGenericElement_CompilationStatus(), "target", null, 1, 1,
				CompilationStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompilationStatus_Message(), ecorePackage.getEString(), "message", null, 1, 1,
				CompilationStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompilationStatus_Severity(), this.getCompilationStatusSeverity(), "severity",
				null, 1, 1, CompilationStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompilationStatus_Type(), this.getCompilationMessageType(), "type", null, 1, 1,
				CompilationStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compilationStatusManagerEClass, CompilationStatusManager.class,
				"CompilationStatusManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompilationStatusManager_CompilationStatusList(), this.getCompilationStatus(),
				null, "compilationStatusList", null, 0, -1, CompilationStatusManager.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationStatusManager_ModelingUnitToStatusList(),
				this.getModelingUnitToStatusList(), null, "modelingUnitToStatusList", null, 0, -1,
				CompilationStatusManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompilationStatusManager_ValidationTime(), ecorePackage.getEBigInteger(),
				"validationTime", null, 0, 1, CompilationStatusManager.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compilationInformationHolderEClass, CompilationInformationHolder.class,
				"CompilationInformationHolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompilationInformationHolder_GeneratedElementList(), ecorePackage.getEObject(),
				null, "generatedElementList", null, 0, -1, CompilationInformationHolder.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationInformationHolder_ElementToUnresolvedReferenceMap(),
				this.getEObjectToUnresolvedReferencesList(), null, "elementToUnresolvedReferenceMap", null,
				0, -1, CompilationInformationHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationInformationHolder_TypeToNameToElementsMap(),
				this.getETypeToStringToEObjectMap(), null, "typeToNameToElementsMap", null, 0, -1,
				CompilationInformationHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationInformationHolder_CreatedElementsToInstructions(),
				this.getCreatedElementToInstructionMapEntry(), null, "createdElementsToInstructions", null,
				0, -1, CompilationInformationHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationInformationHolder_CurrentGeneratedElementList(),
				ecorePackage.getEObject(), null, "currentGeneratedElementList", null, 0, -1,
				CompilationInformationHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationInformationHolder_ResourceToContainedElements(),
				this.getResourceToContainedElementsMapEntry(), null, "resourceToContainedElements", null, 0,
				-1, CompilationInformationHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationInformationHolder_UnresolvedContributions(),
				this.getTextualReferenceToContributions(), null, "unresolvedContributions", null, 0, -1,
				CompilationInformationHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unresolvedContributionHolderEClass, UnresolvedContributionHolder.class,
				"UnresolvedContributionHolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnresolvedContributionHolder_Resolved(), ecorePackage.getEBoolean(), "resolved",
				null, 1, 1, UnresolvedContributionHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnresolvedContributionHolder_ReferencedContribution(),
				theModelingUnitPackage.getContributionInstruction(), null, "referencedContribution", null, 1,
				1, UnresolvedContributionHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(traceabilityIndexEClass, TraceabilityIndex.class, "TraceabilityIndex", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTraceabilityIndex_Entries(), this.getTraceabilityIndexEntry(), null, "entries",
				null, 0, -1, TraceabilityIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(traceabilityIndexEntryEClass, TraceabilityIndexEntry.class, "TraceabilityIndexEntry",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTraceabilityIndexEntry_GeneratedResourcePath(), ecorePackage.getEString(),
				"generatedResourcePath", null, 1, 1, TraceabilityIndexEntry.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTraceabilityIndexEntry_CompilationTime(), ecorePackage.getEBigInteger(),
				"compilationTime", null, 1, 1, TraceabilityIndexEntry.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraceabilityIndexEntry_ResourceDeclaration(),
				theModelingUnitPackage.getResourceDeclaration(), null, "resourceDeclaration", null, 1, 1,
				TraceabilityIndexEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraceabilityIndexEntry_ContainedElementToInstructions(),
				this.getCompiledElementToInstructionEntry(), null, "containedElementToInstructions", null, 0,
				-1, TraceabilityIndexEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compiledElementToInstructionEntryEClass, Map.Entry.class,
				"CompiledElementToInstructionEntry", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompiledElementToInstructionEntry_Key(), ecorePackage.getEObject(), null, "key",
				null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompiledElementToInstructionEntry_Value(), this.getInstructionTraceabilityEntry(),
				null, "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(instructionTraceabilityEntryEClass, InstructionTraceabilityEntry.class,
				"InstructionTraceabilityEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstructionTraceabilityEntry_Instruction(),
				theIntentDocumentPackage.getIntentGenericElement(), null, "instruction", null, 1, 1,
				InstructionTraceabilityEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstructionTraceabilityEntry_Features(), this.getFeatureToAffectationEntry(), null,
				"features", null, 0, -1, InstructionTraceabilityEntry.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(featureToAffectationEntryEClass, Map.Entry.class, "FeatureToAffectationEntry",
				!IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeatureToAffectationEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeatureToAffectationEntry_Value(),
				theModelingUnitPackage.getValueForStructuralFeature(), null, "value", null, 1, -1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceChangeStatusEClass, ResourceChangeStatus.class, "ResourceChangeStatus",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceChangeStatus_WorkingCopyResourceState(),
				this.getSynchronizerResourceState(), "workingCopyResourceState", null, 0, 1,
				ResourceChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceChangeStatus_CompiledResourceState(), this.getSynchronizerResourceState(),
				"compiledResourceState", "", 0, 1, ResourceChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelElementChangeStatusEClass, ModelElementChangeStatus.class,
				"ModelElementChangeStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelElementChangeStatus_ChangeState(), this.getSynchronizerChangeState(),
				"changeState", null, 0, 1, ModelElementChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElementChangeStatus_CompiledParent(), ecorePackage.getEObject(), null,
				"compiledParent", null, 0, 1, ModelElementChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getModelElementChangeStatus_CompiledElement(), ecorePackage.getEObject(), null,
				"compiledElement", null, 0, 1, ModelElementChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getModelElementChangeStatus_WorkingCopyParentURIFragment(), ecorePackage.getEString(),
				"workingCopyParentURIFragment", null, 0, 1, ModelElementChangeStatus.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElementChangeStatus_WorkingCopyElementURIFragment(),
				ecorePackage.getEString(), "workingCopyElementURIFragment", null, 0, 1,
				ModelElementChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(structuralFeatureChangeStatusEClass, StructuralFeatureChangeStatus.class,
				"StructuralFeatureChangeStatus", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStructuralFeatureChangeStatus_ChangeState(), this.getSynchronizerChangeState(),
				"changeState", null, 0, 1, StructuralFeatureChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStructuralFeatureChangeStatus_FeatureName(), ecorePackage.getEString(),
				"featureName", null, 0, 1, StructuralFeatureChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStructuralFeatureChangeStatus_CompiledElement(), ecorePackage.getEObject(), null,
				"compiledElement", null, 0, 1, StructuralFeatureChangeStatus.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getStructuralFeatureChangeStatus_WorkingCopyElementURIFragment(),
				ecorePackage.getEString(), "workingCopyElementURIFragment", null, 0, 1,
				StructuralFeatureChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceChangeStatusEClass, ReferenceChangeStatus.class, "ReferenceChangeStatus",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferenceChangeStatus_CompiledTarget(), ecorePackage.getEObject(), null,
				"compiledTarget", null, 0, 1, ReferenceChangeStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getReferenceChangeStatus_WorkingCopyTargetURIFragment(), ecorePackage.getEString(),
				"workingCopyTargetURIFragment", null, 0, 1, ReferenceChangeStatus.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeChangeStatusEClass, AttributeChangeStatus.class, "AttributeChangeStatus",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(synchronizerCompilationStatusEClass, SynchronizerCompilationStatus.class,
				"SynchronizerCompilationStatus", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSynchronizerCompilationStatus_CompiledResourceURI(), ecorePackage.getEString(),
				"compiledResourceURI", null, 0, 1, SynchronizerCompilationStatus.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSynchronizerCompilationStatus_WorkingCopyResourceURI(), ecorePackage.getEString(),
				"workingCopyResourceURI", null, 0, 1, SynchronizerCompilationStatus.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(compilationStatusSeverityEEnum, CompilationStatusSeverity.class,
				"CompilationStatusSeverity");
		addEEnumLiteral(compilationStatusSeverityEEnum, CompilationStatusSeverity.WARNING);
		addEEnumLiteral(compilationStatusSeverityEEnum, CompilationStatusSeverity.ERROR);
		addEEnumLiteral(compilationStatusSeverityEEnum, CompilationStatusSeverity.INFO);
		addEEnumLiteral(compilationStatusSeverityEEnum, CompilationStatusSeverity.OK);

		initEEnum(compilationMessageTypeEEnum, CompilationMessageType.class, "CompilationMessageType");
		addEEnumLiteral(compilationMessageTypeEEnum, CompilationMessageType.RESOLVE_ERROR);
		addEEnumLiteral(compilationMessageTypeEEnum, CompilationMessageType.PACKAGE_NOT_FOUND_ERROR);
		addEEnumLiteral(compilationMessageTypeEEnum, CompilationMessageType.PACKAGE_REGISTRATION_ERROR);
		addEEnumLiteral(compilationMessageTypeEEnum, CompilationMessageType.INVALID_REFERENCE_ERROR);
		addEEnumLiteral(compilationMessageTypeEEnum, CompilationMessageType.VALIDATION_ERROR);
		addEEnumLiteral(compilationMessageTypeEEnum, CompilationMessageType.INVALID_VALUE_ERROR);
		addEEnumLiteral(compilationMessageTypeEEnum, CompilationMessageType.GENERAL_WARNING);
		addEEnumLiteral(compilationMessageTypeEEnum, CompilationMessageType.SYNCHRONIZER_WARNING);
		addEEnumLiteral(compilationMessageTypeEEnum, CompilationMessageType.SYNCHRONIZER_INFO);

		initEEnum(synchronizerResourceStateEEnum, SynchronizerResourceState.class,
				"SynchronizerResourceState");
		addEEnumLiteral(synchronizerResourceStateEEnum, SynchronizerResourceState.DEFAULT);
		addEEnumLiteral(synchronizerResourceStateEEnum, SynchronizerResourceState.NULL);
		addEEnumLiteral(synchronizerResourceStateEEnum, SynchronizerResourceState.EMPTY);

		initEEnum(synchronizerChangeStateEEnum, SynchronizerChangeState.class, "SynchronizerChangeState");
		addEEnumLiteral(synchronizerChangeStateEEnum, SynchronizerChangeState.UPDATE);
		addEEnumLiteral(synchronizerChangeStateEEnum, SynchronizerChangeState.ORDER);
		addEEnumLiteral(synchronizerChangeStateEEnum, SynchronizerChangeState.WORKING_COPY_TARGET);
		addEEnumLiteral(synchronizerChangeStateEEnum, SynchronizerChangeState.COMPILED_TARGET);

		// Create resource
		createResource(eNS_URI);
	}

} // CompilerPackageImpl
