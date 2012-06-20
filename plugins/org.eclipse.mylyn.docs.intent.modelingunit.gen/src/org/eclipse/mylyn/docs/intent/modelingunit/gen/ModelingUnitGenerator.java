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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ValueForStructuralFeature;

/**
 * Utility which generates modeling units from existing models.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class ModelingUnitGenerator {
	/**
	 * Generates a contribution to the given instantiation instruction. The contribution build the given root
	 * model fragment.
	 * 
	 * @param instanciation
	 *            the instantiation to contribute
	 * @param root
	 *            the root object to build
	 * @return the contribution
	 */
	public ContributionInstruction generateContribution(InstanciationInstruction instanciation,
			final EObject root) {
		final ContributionInstruction contribution = ModelingUnitFactory.eINSTANCE
				.createContributionInstruction();
		ModelingUnitInstructionReference ref = ModelingUnitFactory.eINSTANCE
				.createModelingUnitInstructionReference();
		ref.setReferencedElement(instanciation);
		ref.setIntentHref(instanciation.getName());
		contribution.setReferencedElement(ref);

		StructuralFeatureAffectation affectation = generateContainerAffectation(root);

		contribution.getContributions().add(affectation);
		return contribution;
	}

	/**
	 * Generates the affectation of the given object to its container.
	 * 
	 * @param root
	 *            the object to instantiate
	 * @return the affectation
	 */
	private StructuralFeatureAffectation generateContainerAffectation(final EObject root) {
		StructuralFeatureAffectation affectation = ModelingUnitFactory.eINSTANCE
				.createStructuralFeatureAffectation();
		EStructuralFeature eContainingFeature = root.eContainingFeature();
		affectation.setName(eContainingFeature.getName());
		AffectationOperator operator = AffectationOperator.SINGLE_VALUED_AFFECTATION;
		if (eContainingFeature.isMany()) {
			operator = AffectationOperator.MULTI_VALUED_AFFECTATION;
		}
		affectation.setUsedOperator(operator);

		NewObjectValueForStructuralFeature value = ModelingUnitFactory.eINSTANCE
				.createNewObjectValueForStructuralFeature();
		value.setValue(genInstanciation(root));

		affectation.getValues().add(value);
		return affectation;
	}

	/**
	 * Generates the modeling unit which build the given resource.
	 * 
	 * @param root
	 *            the root EObject to generate as a modeling unit
	 * @return the generated modeling unit
	 */
	public ModelingUnit generateModelingUnit(EObject root) {
		ModelingUnit res = ModelingUnitFactory.eINSTANCE.createModelingUnit();
		res.getInstructions().add(genInstanciation(root));
		return res;
	}

	/**
	 * Generates the instructions which build the given {@link EObject} and its attributes.
	 * 
	 * @param root
	 *            the root {@link EObject}
	 * @return the generated {@link InstanciationInstruction}
	 */
	public InstanciationInstruction genInstanciation(EObject root) {
		InstanciationInstruction instanciation = ModelingUnitFactory.eINSTANCE
				.createInstanciationInstruction();
		instanciation.setLineBreak(true);

		TypeReference typeReference = ModelingUnitFactory.eINSTANCE.createTypeReference();
		typeReference.setIntentHref(root.eClass().getName());
		typeReference.setResolvedType(root.eClass());

		instanciation.setName(getName(root));
		instanciation.setMetaType(typeReference);
		for (EStructuralFeature feature : root.eClass().getEAllAttributes()) {
			if (feature.isChangeable() && !feature.isDerived()) {
				StructuralFeatureAffectation affectation = genAffectation(root, (EAttribute)feature);
				if (affectation != null) {
					instanciation.getStructuralFeatures().add(affectation);
				}
			}
		}

		for (EObject content : root.eContents()) {
			EStructuralFeature eContainingFeature = content.eContainingFeature();
			if (eContainingFeature.isChangeable() && !eContainingFeature.isDerived()
					&& !eContainingFeature.isUnsettable()) {
				instanciation.getStructuralFeatures().add(generateContainerAffectation(content));
			}
		}

		return instanciation;
	}

	/**
	 * Computes the name of any object.
	 * 
	 * @param eo
	 *            the object
	 * @return the name
	 */
	private static String getName(EObject eo) {
		for (EAttribute attr : eo.eClass().getEAllAttributes()) {
			if ("name".equals(attr.getName())) {
				return eo.eGet(attr).toString();
			}
		}
		return null;
	}

	/**
	 * Generates the {@link StructuralFeatureAffectation} which build the affectation.
	 * 
	 * @param object
	 *            the container object
	 * @param attribute
	 *            the attribute to set
	 * @return the {@link ModelingUnit} instruction
	 */
	private StructuralFeatureAffectation genAffectation(EObject object, EAttribute attribute) {
		StructuralFeatureAffectation affectation = ModelingUnitFactory.eINSTANCE
				.createStructuralFeatureAffectation();
		affectation.setLineBreak(true);
		affectation.setName(attribute.getName());
		Object value = object.eGet(attribute);

		if (value != null) {

			if (attribute.getDefaultValue() != null && value.equals(attribute.getDefaultValue())) {
				return null;
			}

			if (value instanceof Collection) {
				affectation.setUsedOperator(AffectationOperator.MULTI_VALUED_AFFECTATION);
				for (Object element : (Collection<?>)value) {
					affectation.getValues().add(genValue(element, attribute));
				}
			} else {
				affectation.setUsedOperator(AffectationOperator.SINGLE_VALUED_AFFECTATION);
				affectation.getValues().add(genValue(value, attribute));
			}
			return affectation;
		}
		return null;
	}

	/**
	 * Generates the {@link ValueForStructuralFeature} which build the value.
	 * 
	 * @param value
	 *            the value
	 * @param attribute
	 *            the attribute to set
	 * @return the {@link ModelingUnit} instruction
	 */
	private ValueForStructuralFeature genValue(Object value, EAttribute attribute) {
		NativeValueForStructuralFeature res = ModelingUnitFactory.eINSTANCE
				.createNativeValueForStructuralFeature();
		if (value instanceof String) {
			res.setValue("\"" + value.toString() + "\"");
		} else {
			res.setValue(value.toString());
		}
		return res;
	}

}
