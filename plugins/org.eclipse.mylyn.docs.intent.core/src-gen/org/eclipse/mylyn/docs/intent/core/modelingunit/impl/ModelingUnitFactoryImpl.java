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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.mylyn.docs.intent.core.modelingunit.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelingUnitFactoryImpl extends EFactoryImpl implements ModelingUnitFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelingUnitFactory init() {
		try {
			ModelingUnitFactory theModelingUnitFactory = (ModelingUnitFactory)EPackage.Registry.INSTANCE
					.getEFactory("http://www.eclipse.org/intent/modelingunit/0.8");
			if (theModelingUnitFactory != null) {
				return theModelingUnitFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelingUnitFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingUnitFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelingUnitPackage.MODELING_UNIT:
				return (EObject)createModelingUnit();
			case ModelingUnitPackage.RESOURCE_DECLARATION:
				return (EObject)createResourceDeclaration();
			case ModelingUnitPackage.TYPE_REFERENCE:
				return (EObject)createTypeReference();
			case ModelingUnitPackage.INSTANCIATION_INSTRUCTION:
				return (EObject)createInstanciationInstruction();
			case ModelingUnitPackage.STRUCTURAL_FEATURE_AFFECTATION:
				return (EObject)createStructuralFeatureAffectation();
			case ModelingUnitPackage.NATIVE_VALUE:
				return (EObject)createNativeValue();
			case ModelingUnitPackage.NEW_OBJECT_VALUE:
				return (EObject)createNewObjectValue();
			case ModelingUnitPackage.REFERENCE_VALUE:
				return (EObject)createReferenceValue();
			case ModelingUnitPackage.INSTANCIATION_INSTRUCTION_REFERENCE:
				return (EObject)createInstanciationInstructionReference();
			case ModelingUnitPackage.CONTRIBUTION_INSTRUCTION:
				return (EObject)createContributionInstruction();
			case ModelingUnitPackage.EXTERNAL_CONTENT_REFERENCE:
				return (EObject)createExternalContentReference();
			case ModelingUnitPackage.MODELING_UNIT_INSTRUCTION_REFERENCE:
				return (EObject)createModelingUnitInstructionReference();
			case ModelingUnitPackage.INTENT_REFERENCE_IN_MODELING_UNIT:
				return (EObject)createIntentReferenceInModelingUnit();
			case ModelingUnitPackage.ANNOTATION_DECLARATION:
				return (EObject)createAnnotationDeclaration();
			case ModelingUnitPackage.LABEL_IN_MODELING_UNIT:
				return (EObject)createLabelInModelingUnit();
			case ModelingUnitPackage.KEY_VAL_FOR_ANNOTATION:
				return (EObject)createKeyValForAnnotation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName()
						+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ModelingUnitPackage.AFFECTATION_OPERATOR:
				return createAffectationOperatorFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName()
						+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ModelingUnitPackage.AFFECTATION_OPERATOR:
				return convertAffectationOperatorToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName()
						+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingUnit createModelingUnit() {
		ModelingUnitImpl modelingUnit = new ModelingUnitImpl();
		return modelingUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceDeclaration createResourceDeclaration() {
		ResourceDeclarationImpl resourceDeclaration = new ResourceDeclarationImpl();
		return resourceDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeReference createTypeReference() {
		TypeReferenceImpl typeReference = new TypeReferenceImpl();
		return typeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanciationInstruction createInstanciationInstruction() {
		InstanciationInstructionImpl instanciationInstruction = new InstanciationInstructionImpl();
		return instanciationInstruction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeatureAffectation createStructuralFeatureAffectation() {
		StructuralFeatureAffectationImpl structuralFeatureAffectation = new StructuralFeatureAffectationImpl();
		return structuralFeatureAffectation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NativeValue createNativeValue() {
		NativeValueImpl nativeValue = new NativeValueImpl();
		return nativeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewObjectValue createNewObjectValue() {
		NewObjectValueImpl newObjectValue = new NewObjectValueImpl();
		return newObjectValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceValue createReferenceValue() {
		ReferenceValueImpl referenceValue = new ReferenceValueImpl();
		return referenceValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanciationInstructionReference createInstanciationInstructionReference() {
		InstanciationInstructionReferenceImpl instanciationInstructionReference = new InstanciationInstructionReferenceImpl();
		return instanciationInstructionReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionInstruction createContributionInstruction() {
		ContributionInstructionImpl contributionInstruction = new ContributionInstructionImpl();
		return contributionInstruction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalContentReference createExternalContentReference() {
		ExternalContentReferenceImpl externalContentReference = new ExternalContentReferenceImpl();
		return externalContentReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingUnitInstructionReference createModelingUnitInstructionReference() {
		ModelingUnitInstructionReferenceImpl modelingUnitInstructionReference = new ModelingUnitInstructionReferenceImpl();
		return modelingUnitInstructionReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentReferenceInModelingUnit createIntentReferenceInModelingUnit() {
		IntentReferenceInModelingUnitImpl intentReferenceInModelingUnit = new IntentReferenceInModelingUnitImpl();
		return intentReferenceInModelingUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationDeclaration createAnnotationDeclaration() {
		AnnotationDeclarationImpl annotationDeclaration = new AnnotationDeclarationImpl();
		return annotationDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelInModelingUnit createLabelInModelingUnit() {
		LabelInModelingUnitImpl labelInModelingUnit = new LabelInModelingUnitImpl();
		return labelInModelingUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createKeyValForAnnotation() {
		KeyValForAnnotationImpl keyValForAnnotation = new KeyValForAnnotationImpl();
		return keyValForAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AffectationOperator createAffectationOperatorFromString(EDataType eDataType, String initialValue) {
		AffectationOperator result = AffectationOperator.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue
					+ "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAffectationOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingUnitPackage getModelingUnitPackage() {
		return (ModelingUnitPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelingUnitPackage getPackage() {
		return ModelingUnitPackage.eINSTANCE;
	}

} //ModelingUnitFactoryImpl
