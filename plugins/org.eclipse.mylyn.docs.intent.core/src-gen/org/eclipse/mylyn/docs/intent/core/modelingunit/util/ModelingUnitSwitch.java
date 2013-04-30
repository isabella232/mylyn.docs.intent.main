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
package org.eclipse.mylyn.docs.intent.core.modelingunit.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.mylyn.docs.intent.core.document.GenericUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.document.IntentReference;
import org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration;
import org.eclipse.mylyn.docs.intent.core.document.UnitInstruction;

import org.eclipse.mylyn.docs.intent.core.modelingunit.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage
 * @generated
 */
public class ModelingUnitSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelingUnitPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingUnitSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelingUnitPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelingUnitPackage.MODELING_UNIT: {
				ModelingUnit modelingUnit = (ModelingUnit)theEObject;
				T result = caseModelingUnit(modelingUnit);
				if (result == null)
					result = caseGenericUnit(modelingUnit);
				if (result == null)
					result = caseIntentGenericElement(modelingUnit);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.MODELING_UNIT_INSTRUCTION: {
				ModelingUnitInstruction modelingUnitInstruction = (ModelingUnitInstruction)theEObject;
				T result = caseModelingUnitInstruction(modelingUnitInstruction);
				if (result == null)
					result = caseUnitInstruction(modelingUnitInstruction);
				if (result == null)
					result = caseIntentGenericElement(modelingUnitInstruction);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.RESOURCE_DECLARATION: {
				ResourceDeclaration resourceDeclaration = (ResourceDeclaration)theEObject;
				T result = caseResourceDeclaration(resourceDeclaration);
				if (result == null)
					result = caseModelingUnitInstruction(resourceDeclaration);
				if (result == null)
					result = caseUnitInstruction(resourceDeclaration);
				if (result == null)
					result = caseIntentGenericElement(resourceDeclaration);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.ABSTRACT_META_TYPE_INSTRUCTION: {
				AbstractMetaTypeInstruction abstractMetaTypeInstruction = (AbstractMetaTypeInstruction)theEObject;
				T result = caseAbstractMetaTypeInstruction(abstractMetaTypeInstruction);
				if (result == null)
					result = caseModelingUnitInstruction(abstractMetaTypeInstruction);
				if (result == null)
					result = caseUnitInstruction(abstractMetaTypeInstruction);
				if (result == null)
					result = caseIntentGenericElement(abstractMetaTypeInstruction);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.TYPE_REFERENCE: {
				TypeReference typeReference = (TypeReference)theEObject;
				T result = caseTypeReference(typeReference);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.INSTANCIATION_INSTRUCTION: {
				InstanciationInstruction instanciationInstruction = (InstanciationInstruction)theEObject;
				T result = caseInstanciationInstruction(instanciationInstruction);
				if (result == null)
					result = caseAbstractMetaTypeInstruction(instanciationInstruction);
				if (result == null)
					result = caseModelingUnitInstruction(instanciationInstruction);
				if (result == null)
					result = caseUnitInstruction(instanciationInstruction);
				if (result == null)
					result = caseIntentGenericElement(instanciationInstruction);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.STRUCTURAL_FEATURE_AFFECTATION: {
				StructuralFeatureAffectation structuralFeatureAffectation = (StructuralFeatureAffectation)theEObject;
				T result = caseStructuralFeatureAffectation(structuralFeatureAffectation);
				if (result == null)
					result = caseAbstractMetaTypeInstruction(structuralFeatureAffectation);
				if (result == null)
					result = caseModelingUnitInstruction(structuralFeatureAffectation);
				if (result == null)
					result = caseUnitInstruction(structuralFeatureAffectation);
				if (result == null)
					result = caseIntentGenericElement(structuralFeatureAffectation);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.ABSTRACT_VALUE: {
				AbstractValue abstractValue = (AbstractValue)theEObject;
				T result = caseAbstractValue(abstractValue);
				if (result == null)
					result = caseModelingUnitInstruction(abstractValue);
				if (result == null)
					result = caseUnitInstruction(abstractValue);
				if (result == null)
					result = caseIntentGenericElement(abstractValue);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.NATIVE_VALUE: {
				NativeValue nativeValue = (NativeValue)theEObject;
				T result = caseNativeValue(nativeValue);
				if (result == null)
					result = caseAbstractValue(nativeValue);
				if (result == null)
					result = caseModelingUnitInstruction(nativeValue);
				if (result == null)
					result = caseUnitInstruction(nativeValue);
				if (result == null)
					result = caseIntentGenericElement(nativeValue);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.NEW_OBJECT_VALUE: {
				NewObjectValue newObjectValue = (NewObjectValue)theEObject;
				T result = caseNewObjectValue(newObjectValue);
				if (result == null)
					result = caseAbstractValue(newObjectValue);
				if (result == null)
					result = caseModelingUnitInstruction(newObjectValue);
				if (result == null)
					result = caseUnitInstruction(newObjectValue);
				if (result == null)
					result = caseIntentGenericElement(newObjectValue);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.REFERENCE_VALUE: {
				ReferenceValue referenceValue = (ReferenceValue)theEObject;
				T result = caseReferenceValue(referenceValue);
				if (result == null)
					result = caseAbstractValue(referenceValue);
				if (result == null)
					result = caseModelingUnitInstruction(referenceValue);
				if (result == null)
					result = caseUnitInstruction(referenceValue);
				if (result == null)
					result = caseIntentGenericElement(referenceValue);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.INSTANCIATION_INSTRUCTION_REFERENCE: {
				InstanciationInstructionReference instanciationInstructionReference = (InstanciationInstructionReference)theEObject;
				T result = caseInstanciationInstructionReference(instanciationInstructionReference);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.CONTRIBUTION_INSTRUCTION: {
				ContributionInstruction contributionInstruction = (ContributionInstruction)theEObject;
				T result = caseContributionInstruction(contributionInstruction);
				if (result == null)
					result = caseModelingUnitInstruction(contributionInstruction);
				if (result == null)
					result = caseUnitInstruction(contributionInstruction);
				if (result == null)
					result = caseIntentGenericElement(contributionInstruction);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.EXTERNAL_CONTENT_REFERENCE: {
				ExternalContentReference externalContentReference = (ExternalContentReference)theEObject;
				T result = caseExternalContentReference(externalContentReference);
				if (result == null)
					result = caseResourceDeclaration(externalContentReference);
				if (result == null)
					result = caseModelingUnitInstruction(externalContentReference);
				if (result == null)
					result = caseUnitInstruction(externalContentReference);
				if (result == null)
					result = caseIntentGenericElement(externalContentReference);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.MODELING_UNIT_INSTRUCTION_REFERENCE: {
				ModelingUnitInstructionReference modelingUnitInstructionReference = (ModelingUnitInstructionReference)theEObject;
				T result = caseModelingUnitInstructionReference(modelingUnitInstructionReference);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.INTENT_REFERENCE_IN_MODELING_UNIT: {
				IntentReferenceInModelingUnit intentReferenceInModelingUnit = (IntentReferenceInModelingUnit)theEObject;
				T result = caseIntentReferenceInModelingUnit(intentReferenceInModelingUnit);
				if (result == null)
					result = caseIntentReferenceInstruction(intentReferenceInModelingUnit);
				if (result == null)
					result = caseModelingUnitInstruction(intentReferenceInModelingUnit);
				if (result == null)
					result = caseUnitInstruction(intentReferenceInModelingUnit);
				if (result == null)
					result = caseIntentReference(intentReferenceInModelingUnit);
				if (result == null)
					result = caseIntentGenericElement(intentReferenceInModelingUnit);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.ANNOTATION_DECLARATION: {
				AnnotationDeclaration annotationDeclaration = (AnnotationDeclaration)theEObject;
				T result = caseAnnotationDeclaration(annotationDeclaration);
				if (result == null)
					result = caseModelingUnitInstruction(annotationDeclaration);
				if (result == null)
					result = caseIntentReference(annotationDeclaration);
				if (result == null)
					result = caseUnitInstruction(annotationDeclaration);
				if (result == null)
					result = caseIntentGenericElement(annotationDeclaration);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.LABEL_IN_MODELING_UNIT: {
				LabelInModelingUnit labelInModelingUnit = (LabelInModelingUnit)theEObject;
				T result = caseLabelInModelingUnit(labelInModelingUnit);
				if (result == null)
					result = caseLabelDeclaration(labelInModelingUnit);
				if (result == null)
					result = caseModelingUnitInstruction(labelInModelingUnit);
				if (result == null)
					result = caseIntentReference(labelInModelingUnit);
				if (result == null)
					result = caseUnitInstruction(labelInModelingUnit);
				if (result == null)
					result = caseIntentGenericElement(labelInModelingUnit);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ModelingUnitPackage.KEY_VAL_FOR_ANNOTATION: {
				@SuppressWarnings("unchecked")
				Map.Entry<String, String> keyValForAnnotation = (Map.Entry<String, String>)theEObject;
				T result = caseKeyValForAnnotation(keyValForAnnotation);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			default:
				return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modeling Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modeling Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelingUnit(ModelingUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instruction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instruction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelingUnitInstruction(ModelingUnitInstruction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceDeclaration(ResourceDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Meta Type Instruction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Meta Type Instruction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractMetaTypeInstruction(AbstractMetaTypeInstruction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeReference(TypeReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instanciation Instruction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instanciation Instruction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstanciationInstruction(InstanciationInstruction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structural Feature Affectation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structural Feature Affectation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructuralFeatureAffectation(StructuralFeatureAffectation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractValue(AbstractValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Native Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Native Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNativeValue(NativeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>New Object Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>New Object Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNewObjectValue(NewObjectValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceValue(ReferenceValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instanciation Instruction Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instanciation Instruction Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstanciationInstructionReference(InstanciationInstructionReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contribution Instruction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contribution Instruction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContributionInstruction(ContributionInstruction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Content Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Content Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalContentReference(ExternalContentReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instruction Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instruction Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelingUnitInstructionReference(ModelingUnitInstructionReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intent Reference In Modeling Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intent Reference In Modeling Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntentReferenceInModelingUnit(IntentReferenceInModelingUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotationDeclaration(AnnotationDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label In Modeling Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label In Modeling Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabelInModelingUnit(LabelInModelingUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Key Val For Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Key Val For Annotation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKeyValForAnnotation(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intent Generic Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intent Generic Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntentGenericElement(IntentGenericElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericUnit(GenericUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unit Instruction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unit Instruction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnitInstruction(UnitInstruction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intent Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intent Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntentReference(IntentReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intent Reference Instruction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intent Reference Instruction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntentReferenceInstruction(IntentReferenceInstruction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabelDeclaration(LabelDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //ModelingUnitSwitch
