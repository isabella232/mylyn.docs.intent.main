/*******************************************************************************
 * Copyright (c) 2012 Obeo.

 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.modelingunit.gen;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ValueForStructuralFeature;

/**
 * Utility which generates modeling units from existing models.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractModelingUnitGenerator {

	/**
	 * The repository adapter.
	 */
	protected RepositoryAdapter repositoryAdapter;

	protected ResourceSet resourceSet = new ResourceSetImpl();

	private List<EObject> newObjects;

	/**
	 * Creates a modeling unit updater.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
	public AbstractModelingUnitGenerator(RepositoryAdapter repositoryAdapter) {
		this.repositoryAdapter = repositoryAdapter;
	}

	/**
	 * Sets the new objects lists, i.e. objects created during the same process which can reference each
	 * other.
	 * 
	 * @param newObjects
	 *            the objects available during the creation process
	 */
	public void setNewObjects(List<EObject> newObjects) {
		this.newObjects = newObjects;
	}

	/**
	 * Generates a contribution to the given instantiation instruction. The contribution build the given root
	 * model fragment.
	 * 
	 * @param instanciation
	 *            the instantiation to contribute
	 * @return the contribution
	 */
	public ContributionInstruction generateContribution(InstanciationInstruction instanciation) {
		ContributionInstruction contribution = ModelingUnitFactory.eINSTANCE.createContributionInstruction();
		ModelingUnitInstructionReference ref = ModelingUnitFactory.eINSTANCE
				.createModelingUnitInstructionReference();
		ref.setReferencedElement(instanciation);
		if (instanciation.getName() == null) {
			EObject generated = getGeneratedElement(instanciation);
			instanciation.setName(generateReferenceName(generated));
		}
		ref.setIntentHref(instanciation.getName());
		contribution.setReferencedElement(ref);
		return contribution;
	}

	/**
	 * Generates the instructions which build the given {@link EObject} and its attributes.
	 * 
	 * @param root
	 *            the root {@link EObject}
	 * @return the generated {@link InstanciationInstruction}
	 */
	public InstanciationInstruction generateInstanciation(EObject root) {
		InstanciationInstruction instanciation = ModelingUnitFactory.eINSTANCE
				.createInstanciationInstruction();
		instanciation.setLineBreak(true);
		instanciation.setName(generateReferenceName(root));

		TypeReference typeReference = ModelingUnitFactory.eINSTANCE.createTypeReference();
		typeReference.setIntentHref(root.eClass().getName());
		typeReference.setResolvedType(root.eClass());

		instanciation.setMetaType(typeReference);

		for (EStructuralFeature feature : root.eClass().getEAllStructuralFeatures()) {
			if (!filter(feature)) {
				Object value = root.eGet(feature);
				if (value instanceof Collection<?>) {
					for (Object singleValue : (Collection<?>)value) {
						StructuralFeatureAffectation affectation = generateAffectation(feature, singleValue);
						if (affectation != null) {
							instanciation.getStructuralFeatures().add(affectation);
						}
					}
				} else {
					StructuralFeatureAffectation affectation = generateAffectation(feature, value);
					if (affectation != null) {
						instanciation.getStructuralFeatures().add(affectation);
					}
				}
			}
		}
		return instanciation;
	}

	/**
	 * Generates the {@link StructuralFeatureAffectation} which build the affectation.
	 * 
	 * @param feature
	 *            the feature to set
	 * @param workingCopyValue
	 *            the value to set
	 * @return the affectation instruction
	 */
	public StructuralFeatureAffectation generateAffectation(EStructuralFeature feature,
			Object workingCopyValue) {
		StructuralFeatureAffectation affectation = null;
		if (workingCopyValue != null
				&& !(feature.getDefaultValue() != null && workingCopyValue.equals(feature.getDefaultValue()))) {
			affectation = ModelingUnitFactory.eINSTANCE.createStructuralFeatureAffectation();
			affectation.setLineBreak(true);
			affectation.setName(feature.getName());
			if (feature.isMany()) {
				affectation.setUsedOperator(AffectationOperator.MULTI_VALUED_AFFECTATION);
			} else {
				affectation.setUsedOperator(AffectationOperator.SINGLE_VALUED_AFFECTATION);
			}
			ValueForStructuralFeature generateValue = generateValue(feature, workingCopyValue);
			if (generateValue != null) {
				affectation.getValues().add(generateValue);
			} else {
				affectation = null;
			}
		}
		return affectation;
	}

	/**
	 * Generates the {@link ValueForStructuralFeature} which build the value.
	 * 
	 * @param feature
	 *            the feature to set
	 * @param value
	 *            the value
	 * @return the value instruction
	 */
	public ValueForStructuralFeature generateValue(EStructuralFeature feature, Object value) {
		ValueForStructuralFeature res = null;
		if (feature instanceof EAttribute) {
			res = ModelingUnitFactory.eINSTANCE.createNativeValueForStructuralFeature();
		} else if (feature instanceof EReference) {
			if (((EReference)feature).isContainment()) {
				res = ModelingUnitFactory.eINSTANCE.createNewObjectValueForStructuralFeature();
			} else {
				res = ModelingUnitFactory.eINSTANCE.createReferenceValueForStructuralFeature();
			}
		}
		if (setValue(res, value)) {
			return res;
		} else {
			return null;
		}
	}

	/**
	 * Sets the correct value in the given value instruction.
	 * 
	 * @param valueInstruction
	 *            the value instruction
	 * @param newValue
	 *            the value to set
	 * @return true if the value has been set
	 */
	public boolean setValue(ValueForStructuralFeature valueInstruction, Object newValue) {
		boolean res = true;
		switch (valueInstruction.eClass().getClassifierID()) {
			case ModelingUnitPackage.NATIVE_VALUE_FOR_STRUCTURAL_FEATURE:
				((NativeValueForStructuralFeature)valueInstruction).setValue("\"" + newValue.toString()
						+ "\"");
				break;
			case ModelingUnitPackage.NEW_OBJECT_VALUE_FOR_STRUCTURAL_FEATURE:
				((NewObjectValueForStructuralFeature)valueInstruction)
						.setValue(generateInstanciation((EObject)newValue));
				break;
			case ModelingUnitPackage.REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE:
				if (newValue instanceof EDataType) {
					InstanciationInstructionReference reference = ModelingUnitFactory.eINSTANCE
							.createInstanciationInstructionReference();
					reference.setIntentHref(((EDataType)newValue).getName());
					((ReferenceValueForStructuralFeature)valueInstruction)
							.setReferencedMetaType((EDataType)newValue);
					((ReferenceValueForStructuralFeature)valueInstruction).setReferencedElement(reference);
				} else {
					InstanciationInstructionReference reference = ModelingUnitFactory.eINSTANCE
							.createInstanciationInstructionReference();

					InstanciationInstruction instanciation = getExistingInstanciationFor((EObject)newValue);
					if (instanciation != null) {
						if (instanciation.getName() == null) {
							instanciation.setName(generateReferenceName(instanciation));
						}
						reference.setIntentHref(instanciation.getName());
						((ReferenceValueForStructuralFeature)valueInstruction)
								.setReferencedElement(reference);
					} else if (newObjects != null && newObjects.contains(newValue)) {
						reference.setIntentHref(generateReferenceName((EObject)newValue));
						((ReferenceValueForStructuralFeature)valueInstruction)
								.setReferencedElement(reference);
					} else {
						res = false;
					}
				}
				break;
			default:
				break;
		}
		return res;
	}

	/**
	 * Filters the feature to generate as affectations.
	 * 
	 * @param feature
	 *            the feature
	 * @return true if an affectation must be generated
	 */
	protected boolean filter(EStructuralFeature feature) {
		boolean isUnsettable = feature instanceof EReference && ((EReference)feature).isContainment()
				&& feature.isUnsettable();
		return !feature.isChangeable() || feature.isDerived() || isUnsettable;
	}

	/**
	 * Retrieves the object generated by the given instanciation.
	 * 
	 * @param instanciation
	 *            the instanciation instruction
	 * @return the generated object
	 */
	protected abstract EObject getGeneratedElement(InstanciationInstruction instanciation);

	/**
	 * Retrieves, if exists, the instanciation of the given working copy object in the existing modeling unit.
	 * 
	 * @param o
	 *            the object
	 * @return the instanciation instruction or null
	 */
	protected abstract InstanciationInstruction getExistingInstanciationFor(EObject o);

	/**
	 * Generates a reference name.
	 * 
	 * @param eObject
	 *            the object to reference
	 * @return the id
	 */
	private String generateReferenceName(EObject eObject) {
		// TODO find a more convenient id ?
		return "REF" + eObject.hashCode();
	}
}
