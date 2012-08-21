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
package org.eclipse.mylyn.docs.intent.client.compiler.generator.modelgeneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.CompilationErrorType;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.CompilationException;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.InvalidValueException;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.ResolveException;
import org.eclipse.mylyn.docs.intent.client.compiler.generator.modellinking.ModelingUnitLinkResolver;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationMessageType;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusSeverity;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedReferenceHolder;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ValueForStructuralFeature;

/**
 * Associates the correct value to structural features described by a structural feature affectation.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class StructuralFeatureGenerator {

	/**
	 * StructuralFeatureGenerator constructor.
	 */
	private StructuralFeatureGenerator() {

	}

	/**
	 * Generate the feature described in the given StructuralFeatureAffectation and assign its correct value.
	 * 
	 * @param affectation
	 *            the structural affectation to inspect
	 * @param eClass
	 *            the object containing the described structural feature
	 * @param linkResolver
	 *            the linkResolver to use
	 * @param modelingUnitGenerator
	 *            the dispatcher to call for generating sub-elements
	 */
	public static void generateFeatureAndAddToClass(StructuralFeatureAffectation affectation, EObject eClass,
			ModelingUnitLinkResolver linkResolver, ModelingUnitGenerator modelingUnitGenerator) {
		ModelingUnitGenerator.clearCompilationStatus(affectation);

		// Step 1 : resolve the feature reference
		try {
			EStructuralFeature feature = linkResolver.resolveEStructuralFeature(affectation, eClass.eClass());
			if (feature == null || feature.getEType() == null) {
				modelingUnitGenerator.getInformationHolder().registerCompilationExceptionAsCompilationStatus(
						new CompilationException(affectation, CompilationErrorType.INVALID_REFERENCE_ERROR,
								"The feature " + feature.getName() + " is derived and cannot be set."));
				// TODO externalize message
				feature.setEType(EcorePackage.eINSTANCE.getEString());
			}
			TypeReference resolvedMetaType = ModelingUnitFactory.eINSTANCE.createTypeReference();
			resolvedMetaType.setIntentHref(feature.getEType().getName());
			affectation.setMetaType(resolvedMetaType);
			if (!feature.isDerived()) {

				// Step 2 : we get the values to generate and assign to this structural feature thanks to
				// the modelingUnit generator.
				List<Object> generatedValues = new ArrayList<Object>();
				for (ValueForStructuralFeature value : affectation.getValues()) {
					for (Object generatedValue : modelingUnitGenerator.doSwitch(value)) {

						// If the value is an instance of UnresolvedReferenceHolder,
						// it means that the link was not resolved
						// We have to register this feature as unresolved.
						if (generatedValue instanceof UnresolvedReferenceHolder) {

							UnresolvedReferenceHolder referenceHolder = (UnresolvedReferenceHolder)generatedValue;
							referenceHolder.setConcernedFeature(feature);
							if (feature instanceof EReference) {
								referenceHolder
										.setContainmentReference(((EReference)feature).isContainment());
							} else {
								// If not a refence, it's an attribute
								referenceHolder.setContainmentReference(true);
							}

							// We register this unresolved reference in the information holder
							modelingUnitGenerator.getInformationHolder().addUnresolvedReference(eClass,
									referenceHolder);
						} else {
							// Otherwise, we simply add this value to the generatedValues list.
							generatedValues.add(generatedValue);
						}
					}
				}

				// Step 3 : we finally set the generated value to the given feature
				setFeatureValueInElement(affectation, eClass, feature, generatedValues);

			} else {
				CompilationStatus status = CompilerFactory.eINSTANCE.createCompilationStatus();
				status.setMessage("The feature " + affectation.getName() + " undefined for type "
						+ eClass.eClass());
				status.setTarget(affectation);
				status.setSeverity(CompilationStatusSeverity.ERROR);
				status.setType(CompilationMessageType.VALIDATION_ERROR);
				affectation.getCompilationStatus().add(status);
			}
		} catch (InvalidValueException e) {
			// If the feature reference cannot be resolved
			// we add a CompilationSats
			modelingUnitGenerator.getInformationHolder().registerCompilationExceptionAsCompilationStatus(
					new CompilationException(e.getInvalidInstruction(),
							CompilationErrorType.INVALID_VALUE_ERROR, e.getMessage()));
		} catch (ResolveException e) {
			// If the feature reference cannot be resolved
			// we add a CompilationSats
			modelingUnitGenerator.getInformationHolder().registerCompilationExceptionAsCompilationStatus(
					new CompilationException(e.getInvalidInstruction(),
							CompilationErrorType.INVALID_REFERENCE_ERROR, e.getMessage()));
		}

	}

	/**
	 * Used to set the given value to the given element.
	 * 
	 * @param unitInstruction
	 *            the unit instruction calling the set
	 * @param element
	 *            The element to which assign the given value.
	 * @param feature
	 *            The feature conserned by this affectation
	 * @param values
	 *            a list of the values to set
	 * @throws InvalidValueException
	 *             if the values cannot be set in the given reference
	 */
	// This suppressWarning is added to avoid the warning about the cast in EList<Object> in the
	// element.eGet(feature) for multi-valued features
	@SuppressWarnings("unchecked")
	public static void setFeatureValueInElement(UnitInstruction unitInstruction, EObject element,
			EStructuralFeature feature, List<Object> values) throws InvalidValueException {

		// Step 1 : cardinality management
		Object finalValueToSet = null;
		if (values.size() > 0) {
			// If we have a multi-valued Feature
			if ((feature.getUpperBound() > 1) || (feature.getUpperBound() == -1)) {
				// We add the new values to the possible previous ones
				EList<Object> newValuesList = new BasicEList<Object>();
				if (element.eGet(feature) != null) {
					newValuesList.addAll((EList<Object>)element.eGet(feature));
				}
				newValuesList.addAll(values);
				finalValueToSet = newValuesList;
			} else {
				// If it is a single valued feature, we just set the first value of the list
				finalValueToSet = values.get(0);
			}

			// Step 2 : we set the value
			if (finalValueToSet != null) {
				checkValueType(unitInstruction, feature, finalValueToSet);
				try {
					element.eSet(feature, finalValueToSet);
				} catch (NullPointerException e) {
					System.err.println("FOR ELEMENT " + feature.getName() + "-" + "/" + finalValueToSet + "-"
							+ feature.getContainerClass().getName());
					// TODO HANDLE THIS NPE
					System.err.println(element.eGet(feature));
				}
			}
		}
	}

	/**
	 * Checks if the value can be set in the given reference.
	 * 
	 * @param unitInstruction
	 *            the unit instruction calling the set
	 * @param feature
	 *            the feature
	 * @param value
	 *            the value to test
	 * @throws InvalidValueException
	 *             if the values cannot be set in the given reference
	 */
	private static void checkValueType(UnitInstruction unitInstruction, EStructuralFeature feature,
			Object value) throws InvalidValueException {
		EClassifier type = feature.getEType();
		if (!(type instanceof EDataType)) {
			// we do not check native values as the value is created and checked by the compiler itself
			if (value instanceof Collection) {
				for (Object element : (Collection<?>)value) {
					if (element instanceof EObject && !isInstanceOf((EObject)element, type)) {
						throw new InvalidValueException(unitInstruction, "The feature " + feature.getName()
								+ " cannot handle type " + element.getClass().getSimpleName() + ". ");
					}
				}
			} else if (value instanceof EObject && !isInstanceOf((EObject)value, type)) {
				throw new InvalidValueException(unitInstruction, "The feature " + feature.getName()
						+ " cannot handle type " + value.getClass().getSimpleName() + ". ");
			}
		}
	}

	/**
	 * Checks if the object is an instance of the given type.
	 * 
	 * @param object
	 *            the object to test
	 * @param type
	 *            the type
	 * @return true if the object is an instance of the given type
	 */
	private static boolean isInstanceOf(EObject object, EClassifier type) {
		EClass actualType = object.eClass();
		if (type.equals(actualType) || actualType.getEAllSuperTypes().contains(type)) {
			return true;
		}
		return false;
	}
}
