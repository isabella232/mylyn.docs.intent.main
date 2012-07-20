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
package org.eclipse.mylyn.docs.intent.modelingunit.update;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.ModelElementChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.modelingunit.gen.ModelingUnitGenerator;

/**
 * Utility which updates modeling units according to existing models.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class ModelingUnitUpdater extends ModelingUnitGenerator {

	private RepositoryAdapter repositoryAdapter;

	/**
	 * Creates a modeling unit updater.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
	public ModelingUnitUpdater(RepositoryAdapter repositoryAdapter) {
		this.repositoryAdapter = repositoryAdapter;
	}

	/**
	 * Fixes the given statuses by updating the given modeling unit.
	 * 
	 * @param modelingUnit
	 *            the modeling unit to update
	 * @param statusToFix
	 *            the statuses to fix
	 */
	public void fixSynchronizationStatus(final ModelingUnit modelingUnit,
			final CompilationStatus... statusToFix) {
		repositoryAdapter.execute(new IntentCommand() {
			public void execute() {
				for (CompilationStatus status : statusToFix) {
					switch (status.eClass().getClassifierID()) {
						case CompilerPackage.MODEL_ELEMENT_CHANGE_STATUS:
							fixModelElementChangeStatus(modelingUnit, (ModelElementChangeStatus)status);
							break;
						case CompilerPackage.ATTRIBUTE_CHANGE_STATUS:
						case CompilerPackage.REFERENCE_CHANGE_STATUS:
							fixStructuralFeatureChangeStatus(modelingUnit,
									(StructuralFeatureChangeStatus)status);
							break;
						default:
							break;
					}
				}
				try {
					repositoryAdapter.save();
				} catch (ReadOnlyException e) {
					IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
				} catch (SaveException e) {
					IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
				}
			}
		});
	}

	/**
	 * Fixes the given statuses by updating the given modeling unit.
	 * 
	 * @param modelingUnit
	 *            the modeling unit to update
	 * @param status
	 *            the status to fix
	 */
	private void fixModelElementChangeStatus(ModelingUnit modelingUnit, ModelElementChangeStatus status) {
		switch (status.getChangeState().getValue()) {
			case SynchronizerChangeState.WORKING_COPY_TARGET_VALUE:
				EObject container = getContainer(status, ModelingUnitPackage.CONTRIBUTION_INSTRUCTION,
						ModelingUnitPackage.INSTANCIATION_INSTRUCTION);
				if (container instanceof ContributionInstruction) {
					ContributionInstruction contribution = (ContributionInstruction)container;
					container = contribution.getReferencedElement().getReferencedElement();
				}

				if (container instanceof InstanciationInstruction) {
					InstanciationInstruction instanciation = (InstanciationInstruction)container;

					// computes additions
					EObject workingCopyObject = getWorkingCopyEObject(status
							.getWorkingCopyElementURIFragment());
					ContributionInstruction contribution = generateContribution(instanciation,
							workingCopyObject);

					// updates the modeling unit
					modelingUnit.getInstructions().add(contribution);
				}
				break;

			default:
				IntentLogger.getInstance().log(
						LogType.INFO,
						"UNSUPPORTED MODEL ELEMENT CHANGE: " + status.getMessage() + '('
								+ ((ModelElementChangeStatus)status).getChangeState() + ')');
				break;
		}
	}

	/**
	 * Fixes the given statuses by updating the given modeling unit.
	 * 
	 * @param modelingUnit
	 *            the modeling unit to update
	 * @param status
	 *            the status to fix
	 */
	private void fixStructuralFeatureChangeStatus(ModelingUnit modelingUnit,
			StructuralFeatureChangeStatus status) {
		switch (status.getChangeState().getValue()) {
			case SynchronizerChangeState.UPDATE_VALUE:
				EObject container = getContainer(status.getTarget(),
						ModelingUnitPackage.STRUCTURAL_FEATURE_AFFECTATION);
				if (container instanceof StructuralFeatureAffectation) {
					StructuralFeatureAffectation affectation = (StructuralFeatureAffectation)container;

					// computes new value
					EObject element = getWorkingCopyEObject(status.getWorkingCopyElementURIFragment());
					Object newValue = element.eGet(element.eClass().getEStructuralFeature(
							status.getFeatureName()));

					// updates the modeling unit
					ValueForStructuralFeature value = affectation.getValues().get(0);
					setValue(value, newValue);
				}
				break;

			case SynchronizerChangeState.COMPILED_TARGET_VALUE:
				// TODO value added in document

			case SynchronizerChangeState.WORKING_COPY_TARGET_VALUE:
				// TODO value removed from document

			default:
				IntentLogger.getInstance().log(
						LogType.INFO,
						"UNSUPPORTED CHANGE: " + status.getMessage() + " ("
								+ ((StructuralFeatureChangeStatus)status).getChangeState() + ')');
				break;
		}
	}

	/**
	 * Sets the correct value in the given value instruction.
	 * 
	 * @param valueInstruction
	 *            the value instruction
	 * @param newValue
	 *            the value to set
	 */
	private static void setValue(ValueForStructuralFeature valueInstruction, Object newValue) {
		switch (valueInstruction.eClass().getClassifierID()) {
			case ModelingUnitPackage.NATIVE_VALUE_FOR_STRUCTURAL_FEATURE:
				((NativeValueForStructuralFeature)valueInstruction).setValue("\"" + newValue.toString()
						+ "\"");
				break;
			// TODO manage all other cases
			default:
				break;
		}
	}

	/**
	 * Returns the container of the given type from a root element.
	 * 
	 * @param object
	 *            the root
	 * @param classifierId
	 *            the classifier ids to consider
	 * @return the parent instruction
	 */
	private static EObject getContainer(EObject object, int... classifierId) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Integer id : classifierId) {
			ids.add(id);
		}

		EObject tmp = object;
		while (tmp != null && !ids.contains(tmp.eClass().getClassifierID())) {
			tmp = tmp.eContainer();
		}
		if (ids.contains(tmp.eClass().getClassifierID())) {
			return tmp;
		}
		return null;
	}

	/**
	 * Load the object at the given uri.
	 * 
	 * @param uri
	 *            the uri
	 * @return the loaded object
	 */
	private static EObject getWorkingCopyEObject(String uri) {
		return new ResourceSetImpl().getEObject(URI.createURI(uri), true);
	}

}
