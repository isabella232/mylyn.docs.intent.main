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
package org.eclipse.mylyn.docs.intent.core.compiler.util;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.mylyn.docs.intent.core.compiler.AttributeChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationInformationHolder;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusManager;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry;
import org.eclipse.mylyn.docs.intent.core.compiler.ModelElementChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.StringToEObjectMap;
import org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndexEntry;
import org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedContributionHolder;
import org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedReferenceHolder;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ValueForStructuralFeature;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the
 * model, starting with the actual class of the object and proceeding up the inheritance hierarchy until a
 * non-null result is returned, which is the result of the switch. <!-- end-user-doc -->
 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage
 * @generated
 */
public class CompilerSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static CompilerPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CompilerSwitch() {
		if (modelPackage == null) {
			modelPackage = CompilerPackage.eINSTANCE;
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CompilerPackage.ESTRING_TO_EOBJECT: {
				@SuppressWarnings("unchecked")
				Map.Entry<String, EObject> eStringToEObject = (Map.Entry<String, EObject>)theEObject;
				T result = caseEStringToEObject(eStringToEObject);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.TEXTUAL_REFERENCE_TO_CONTRIBUTIONS: {
				@SuppressWarnings("unchecked")
				Map.Entry<String, EList<UnresolvedContributionHolder>> textualReferenceToContributions = (Map.Entry<String, EList<UnresolvedContributionHolder>>)theEObject;
				T result = caseTextualReferenceToContributions(textualReferenceToContributions);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.STRING_TO_EOBJECT_MAP: {
				StringToEObjectMap stringToEObjectMap = (StringToEObjectMap)theEObject;
				T result = caseStringToEObjectMap(stringToEObjectMap);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.ETYPE_TO_STRING_TO_EOBJECT_MAP: {
				@SuppressWarnings("unchecked")
				Map.Entry<EClassifier, StringToEObjectMap> eTypeToStringToEObjectMap = (Map.Entry<EClassifier, StringToEObjectMap>)theEObject;
				T result = caseETypeToStringToEObjectMap(eTypeToStringToEObjectMap);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.EOBJECT_TO_UNRESOLVED_REFERENCES_LIST: {
				@SuppressWarnings("unchecked")
				Map.Entry<EObject, EList<UnresolvedReferenceHolder>> eObjectToUnresolvedReferencesList = (Map.Entry<EObject, EList<UnresolvedReferenceHolder>>)theEObject;
				T result = caseEObjectToUnresolvedReferencesList(eObjectToUnresolvedReferencesList);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.RESOURCE_TO_CONTAINED_ELEMENTS_MAP_ENTRY: {
				@SuppressWarnings("unchecked")
				Map.Entry<ResourceDeclaration, EList<EObject>> resourceToContainedElementsMapEntry = (Map.Entry<ResourceDeclaration, EList<EObject>>)theEObject;
				T result = caseResourceToContainedElementsMapEntry(resourceToContainedElementsMapEntry);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.MODELING_UNIT_TO_STATUS_LIST: {
				@SuppressWarnings("unchecked")
				Map.Entry<ModelingUnit, EList<CompilationStatus>> modelingUnitToStatusList = (Map.Entry<ModelingUnit, EList<CompilationStatus>>)theEObject;
				T result = caseModelingUnitToStatusList(modelingUnitToStatusList);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.CREATED_ELEMENT_TO_INSTRUCTION_MAP_ENTRY: {
				@SuppressWarnings("unchecked")
				Map.Entry<EObject, EList<UnitInstruction>> createdElementToInstructionMapEntry = (Map.Entry<EObject, EList<UnitInstruction>>)theEObject;
				T result = caseCreatedElementToInstructionMapEntry(createdElementToInstructionMapEntry);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.UNRESOLVED_REFERENCE_HOLDER: {
				UnresolvedReferenceHolder unresolvedReferenceHolder = (UnresolvedReferenceHolder)theEObject;
				T result = caseUnresolvedReferenceHolder(unresolvedReferenceHolder);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.COMPILATION_STATUS: {
				CompilationStatus compilationStatus = (CompilationStatus)theEObject;
				T result = caseCompilationStatus(compilationStatus);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.COMPILATION_STATUS_MANAGER: {
				CompilationStatusManager compilationStatusManager = (CompilationStatusManager)theEObject;
				T result = caseCompilationStatusManager(compilationStatusManager);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.COMPILATION_INFORMATION_HOLDER: {
				CompilationInformationHolder compilationInformationHolder = (CompilationInformationHolder)theEObject;
				T result = caseCompilationInformationHolder(compilationInformationHolder);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.UNRESOLVED_CONTRIBUTION_HOLDER: {
				UnresolvedContributionHolder unresolvedContributionHolder = (UnresolvedContributionHolder)theEObject;
				T result = caseUnresolvedContributionHolder(unresolvedContributionHolder);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.TRACEABILITY_INDEX: {
				TraceabilityIndex traceabilityIndex = (TraceabilityIndex)theEObject;
				T result = caseTraceabilityIndex(traceabilityIndex);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.TRACEABILITY_INDEX_ENTRY: {
				TraceabilityIndexEntry traceabilityIndexEntry = (TraceabilityIndexEntry)theEObject;
				T result = caseTraceabilityIndexEntry(traceabilityIndexEntry);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.COMPILED_ELEMENT_TO_INSTRUCTION_ENTRY: {
				@SuppressWarnings("unchecked")
				Map.Entry<EObject, EList<InstructionTraceabilityEntry>> compiledElementToInstructionEntry = (Map.Entry<EObject, EList<InstructionTraceabilityEntry>>)theEObject;
				T result = caseCompiledElementToInstructionEntry(compiledElementToInstructionEntry);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.INSTRUCTION_TRACEABILITY_ENTRY: {
				InstructionTraceabilityEntry instructionTraceabilityEntry = (InstructionTraceabilityEntry)theEObject;
				T result = caseInstructionTraceabilityEntry(instructionTraceabilityEntry);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.FEATURE_TO_AFFECTATION_ENTRY: {
				@SuppressWarnings("unchecked")
				Map.Entry<String, EList<ValueForStructuralFeature>> featureToAffectationEntry = (Map.Entry<String, EList<ValueForStructuralFeature>>)theEObject;
				T result = caseFeatureToAffectationEntry(featureToAffectationEntry);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.RESOURCE_CHANGE_STATUS: {
				ResourceChangeStatus resourceChangeStatus = (ResourceChangeStatus)theEObject;
				T result = caseResourceChangeStatus(resourceChangeStatus);
				if (result == null)
					result = caseSynchronizerCompilationStatus(resourceChangeStatus);
				if (result == null)
					result = caseCompilationStatus(resourceChangeStatus);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.MODEL_ELEMENT_CHANGE_STATUS: {
				ModelElementChangeStatus modelElementChangeStatus = (ModelElementChangeStatus)theEObject;
				T result = caseModelElementChangeStatus(modelElementChangeStatus);
				if (result == null)
					result = caseSynchronizerCompilationStatus(modelElementChangeStatus);
				if (result == null)
					result = caseCompilationStatus(modelElementChangeStatus);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.STRUCTURAL_FEATURE_CHANGE_STATUS: {
				StructuralFeatureChangeStatus structuralFeatureChangeStatus = (StructuralFeatureChangeStatus)theEObject;
				T result = caseStructuralFeatureChangeStatus(structuralFeatureChangeStatus);
				if (result == null)
					result = caseSynchronizerCompilationStatus(structuralFeatureChangeStatus);
				if (result == null)
					result = caseCompilationStatus(structuralFeatureChangeStatus);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.REFERENCE_CHANGE_STATUS: {
				ReferenceChangeStatus referenceChangeStatus = (ReferenceChangeStatus)theEObject;
				T result = caseReferenceChangeStatus(referenceChangeStatus);
				if (result == null)
					result = caseStructuralFeatureChangeStatus(referenceChangeStatus);
				if (result == null)
					result = caseSynchronizerCompilationStatus(referenceChangeStatus);
				if (result == null)
					result = caseCompilationStatus(referenceChangeStatus);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.ATTRIBUTE_CHANGE_STATUS: {
				AttributeChangeStatus attributeChangeStatus = (AttributeChangeStatus)theEObject;
				T result = caseAttributeChangeStatus(attributeChangeStatus);
				if (result == null)
					result = caseStructuralFeatureChangeStatus(attributeChangeStatus);
				if (result == null)
					result = caseSynchronizerCompilationStatus(attributeChangeStatus);
				if (result == null)
					result = caseCompilationStatus(attributeChangeStatus);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CompilerPackage.SYNCHRONIZER_COMPILATION_STATUS: {
				SynchronizerCompilationStatus synchronizerCompilationStatus = (SynchronizerCompilationStatus)theEObject;
				T result = caseSynchronizerCompilationStatus(synchronizerCompilationStatus);
				if (result == null)
					result = caseCompilationStatus(synchronizerCompilationStatus);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			default:
				return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EString To EObject</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
	 * switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EString To EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEStringToEObject(Map.Entry<String, EObject> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Textual Reference To Contributions</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Textual Reference To Contributions</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTextualReferenceToContributions(Map.Entry<String, EList<UnresolvedContributionHolder>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To EObject Map</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
	 * switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To EObject Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToEObjectMap(StringToEObjectMap object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EType To String To EObject Map</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EType To String To EObject Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseETypeToStringToEObjectMap(Map.Entry<EClassifier, StringToEObjectMap> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject To Unresolved References List</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject To Unresolved References List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEObjectToUnresolvedReferencesList(Map.Entry<EObject, EList<UnresolvedReferenceHolder>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource To Contained Elements Map Entry</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource To Contained Elements Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceToContainedElementsMapEntry(Map.Entry<ResourceDeclaration, EList<EObject>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modeling Unit To Status List</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modeling Unit To Status List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelingUnitToStatusList(Map.Entry<ModelingUnit, EList<CompilationStatus>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Created Element To Instruction Map Entry</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Created Element To Instruction Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreatedElementToInstructionMapEntry(Map.Entry<EObject, EList<UnitInstruction>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unresolved Reference Holder</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unresolved Reference Holder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnresolvedReferenceHolder(UnresolvedReferenceHolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Compilation Status</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
	 * switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Compilation Status</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompilationStatus(CompilationStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Compilation Status Manager</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Compilation Status Manager</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompilationStatusManager(CompilationStatusManager object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Compilation Information Holder</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Compilation Information Holder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompilationInformationHolder(CompilationInformationHolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unresolved Contribution Holder</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unresolved Contribution Holder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnresolvedContributionHolder(UnresolvedContributionHolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Traceability Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Traceability Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTraceabilityIndex(TraceabilityIndex object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Traceability Index Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Traceability Index Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTraceabilityIndexEntry(TraceabilityIndexEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Compiled Element To Instruction Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Compiled Element To Instruction Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompiledElementToInstructionEntry(
			Map.Entry<EObject, EList<InstructionTraceabilityEntry>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instruction Traceability Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instruction Traceability Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstructionTraceabilityEntry(InstructionTraceabilityEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature To Affectation Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature To Affectation Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureToAffectationEntry(Map.Entry<String, EList<ValueForStructuralFeature>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Change Status</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Change Status</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceChangeStatus(ResourceChangeStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element Change Status</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element Change Status</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElementChangeStatus(ModelElementChangeStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structural Feature Change Status</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structural Feature Change Status</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructuralFeatureChangeStatus(StructuralFeatureChangeStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Change Status</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Change Status</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceChangeStatus(ReferenceChangeStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Change Status</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Change Status</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeChangeStatus(AttributeChangeStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Synchronizer Compilation Status</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Synchronizer Compilation Status</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSynchronizerCompilationStatus(SynchronizerCompilationStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch, but this
	 * is the last case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // CompilerSwitch
