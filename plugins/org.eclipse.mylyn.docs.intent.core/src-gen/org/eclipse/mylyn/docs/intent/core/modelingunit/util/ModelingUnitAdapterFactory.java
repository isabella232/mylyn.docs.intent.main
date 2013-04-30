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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.mylyn.docs.intent.core.document.GenericUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.document.IntentReference;
import org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration;
import org.eclipse.mylyn.docs.intent.core.document.UnitInstruction;

import org.eclipse.mylyn.docs.intent.core.modelingunit.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage
 * @generated
 */
public class ModelingUnitAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelingUnitPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingUnitAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModelingUnitPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelingUnitSwitch<Adapter> modelSwitch = new ModelingUnitSwitch<Adapter>() {
		@Override
		public Adapter caseModelingUnit(ModelingUnit object) {
			return createModelingUnitAdapter();
		}

		@Override
		public Adapter caseModelingUnitInstruction(ModelingUnitInstruction object) {
			return createModelingUnitInstructionAdapter();
		}

		@Override
		public Adapter caseResourceDeclaration(ResourceDeclaration object) {
			return createResourceDeclarationAdapter();
		}

		@Override
		public Adapter caseAbstractMetaTypeInstruction(AbstractMetaTypeInstruction object) {
			return createAbstractMetaTypeInstructionAdapter();
		}

		@Override
		public Adapter caseTypeReference(TypeReference object) {
			return createTypeReferenceAdapter();
		}

		@Override
		public Adapter caseInstanciationInstruction(InstanciationInstruction object) {
			return createInstanciationInstructionAdapter();
		}

		@Override
		public Adapter caseStructuralFeatureAffectation(StructuralFeatureAffectation object) {
			return createStructuralFeatureAffectationAdapter();
		}

		@Override
		public Adapter caseAbstractValue(AbstractValue object) {
			return createAbstractValueAdapter();
		}

		@Override
		public Adapter caseNativeValue(NativeValue object) {
			return createNativeValueAdapter();
		}

		@Override
		public Adapter caseNewObjectValue(NewObjectValue object) {
			return createNewObjectValueAdapter();
		}

		@Override
		public Adapter caseReferenceValue(ReferenceValue object) {
			return createReferenceValueAdapter();
		}

		@Override
		public Adapter caseInstanciationInstructionReference(InstanciationInstructionReference object) {
			return createInstanciationInstructionReferenceAdapter();
		}

		@Override
		public Adapter caseContributionInstruction(ContributionInstruction object) {
			return createContributionInstructionAdapter();
		}

		@Override
		public Adapter caseExternalContentReference(ExternalContentReference object) {
			return createExternalContentReferenceAdapter();
		}

		@Override
		public Adapter caseModelingUnitInstructionReference(ModelingUnitInstructionReference object) {
			return createModelingUnitInstructionReferenceAdapter();
		}

		@Override
		public Adapter caseIntentReferenceInModelingUnit(IntentReferenceInModelingUnit object) {
			return createIntentReferenceInModelingUnitAdapter();
		}

		@Override
		public Adapter caseAnnotationDeclaration(AnnotationDeclaration object) {
			return createAnnotationDeclarationAdapter();
		}

		@Override
		public Adapter caseLabelInModelingUnit(LabelInModelingUnit object) {
			return createLabelInModelingUnitAdapter();
		}

		@Override
		public Adapter caseKeyValForAnnotation(Map.Entry<String, String> object) {
			return createKeyValForAnnotationAdapter();
		}

		@Override
		public Adapter caseIntentGenericElement(IntentGenericElement object) {
			return createIntentGenericElementAdapter();
		}

		@Override
		public Adapter caseGenericUnit(GenericUnit object) {
			return createGenericUnitAdapter();
		}

		@Override
		public Adapter caseUnitInstruction(UnitInstruction object) {
			return createUnitInstructionAdapter();
		}

		@Override
		public Adapter caseIntentReference(IntentReference object) {
			return createIntentReferenceAdapter();
		}

		@Override
		public Adapter caseIntentReferenceInstruction(IntentReferenceInstruction object) {
			return createIntentReferenceInstructionAdapter();
		}

		@Override
		public Adapter caseLabelDeclaration(LabelDeclaration object) {
			return createLabelDeclarationAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit <em>Modeling Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit
	 * @generated
	 */
	public Adapter createModelingUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction <em>Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction
	 * @generated
	 */
	public Adapter createModelingUnitInstructionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration <em>Resource Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration
	 * @generated
	 */
	public Adapter createResourceDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractMetaTypeInstruction <em>Abstract Meta Type Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractMetaTypeInstruction
	 * @generated
	 */
	public Adapter createAbstractMetaTypeInstructionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference <em>Type Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference
	 * @generated
	 */
	public Adapter createTypeReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction <em>Instanciation Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction
	 * @generated
	 */
	public Adapter createInstanciationInstructionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation <em>Structural Feature Affectation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation
	 * @generated
	 */
	public Adapter createStructuralFeatureAffectationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractValue <em>Abstract Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractValue
	 * @generated
	 */
	public Adapter createAbstractValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValue <em>Native Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValue
	 * @generated
	 */
	public Adapter createNativeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValue <em>New Object Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValue
	 * @generated
	 */
	public Adapter createNewObjectValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValue <em>Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValue
	 * @generated
	 */
	public Adapter createReferenceValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference <em>Instanciation Instruction Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference
	 * @generated
	 */
	public Adapter createInstanciationInstructionReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction <em>Contribution Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction
	 * @generated
	 */
	public Adapter createContributionInstructionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference <em>External Content Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference
	 * @generated
	 */
	public Adapter createExternalContentReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference <em>Instruction Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference
	 * @generated
	 */
	public Adapter createModelingUnitInstructionReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.IntentReferenceInModelingUnit <em>Intent Reference In Modeling Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.IntentReferenceInModelingUnit
	 * @generated
	 */
	public Adapter createIntentReferenceInModelingUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration <em>Annotation Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration
	 * @generated
	 */
	public Adapter createAnnotationDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.modelingunit.LabelInModelingUnit <em>Label In Modeling Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.LabelInModelingUnit
	 * @generated
	 */
	public Adapter createLabelInModelingUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Key Val For Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createKeyValForAnnotationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement <em>Intent Generic Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement
	 * @generated
	 */
	public Adapter createIntentGenericElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.document.GenericUnit <em>Generic Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.document.GenericUnit
	 * @generated
	 */
	public Adapter createGenericUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.document.UnitInstruction <em>Unit Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.document.UnitInstruction
	 * @generated
	 */
	public Adapter createUnitInstructionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.document.IntentReference <em>Intent Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentReference
	 * @generated
	 */
	public Adapter createIntentReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction <em>Intent Reference Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction
	 * @generated
	 */
	public Adapter createIntentReferenceInstructionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration <em>Label Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration
	 * @generated
	 */
	public Adapter createLabelDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ModelingUnitAdapterFactory
