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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.ModelElementChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ValueForStructuralFeature;

/**
 * Utility which updates modeling units according to synchronization status.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class SyncStatusUpdater extends AbstractModelingUnitUpdater {

	/**
	 * Creates a modeling unit updater.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
	public SyncStatusUpdater(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * Initializes the match from a status.
	 * 
	 * @param status
	 *            the status
	 */
	private void initMatch(SynchronizerCompilationStatus status) {
		Resource compiledResource = repositoryAdapter.getResource(status.getCompiledResourceURI());
		Resource workingCopyResource = resourceSet.getResource(
				URI.createURI(status.getWorkingCopyResourceURI().replaceAll("\"", "")), true);
		includeMatch(compiledResource, workingCopyResource);
	}

	/**
	 * Fixes the given statuses by updating the given modeling unit.
	 * 
	 * @param statusToFix
	 *            the statuses to fix
	 */
	public void fixSynchronizationStatus(final SynchronizerCompilationStatus... statusToFix) {
		repositoryAdapter.execute(new IntentCommand() {
			public void execute() {
				for (SynchronizerCompilationStatus status : statusToFix) {
					initMatch(status);
					switch (status.eClass().getClassifierID()) {
						case CompilerPackage.MODEL_ELEMENT_CHANGE_STATUS:
							fixModelElementChange((ModelElementChangeStatus)status);
							break;
						case CompilerPackage.ATTRIBUTE_CHANGE_STATUS:
						case CompilerPackage.REFERENCE_CHANGE_STATUS:
							fixStructuralFeatureChange((StructuralFeatureChangeStatus)status);
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
	 * @param status
	 *            the status to fix
	 */
	private void fixModelElementChange(ModelElementChangeStatus status) {
		IntentGenericElement target = status.getTarget();
		switch (status.getChangeState().getValue()) {
			case SynchronizerChangeState.WORKING_COPY_TARGET_VALUE:
				EObject container = getContainer(target, ModelingUnitPackage.CONTRIBUTION_INSTRUCTION,
						ModelingUnitPackage.INSTANCIATION_INSTRUCTION);
				if (container instanceof ContributionInstruction) {
					ContributionInstruction contribution = (ContributionInstruction)container;
					container = contribution.getContributionReference().getReferencedInstruction();
				}

				if (container instanceof InstanciationInstruction) {
					InstanciationInstruction instanciation = (InstanciationInstruction)container;

					// computes additions
					EObject workingCopyObject = getWorkingCopyEObject(status
							.getWorkingCopyElementURIFragment());

					List<EObject> newObjects = new ArrayList<EObject>();
					for (Iterator<EObject> iterator = workingCopyObject.eAllContents(); iterator.hasNext();) {
						newObjects.add(iterator.next());
					}
					setNewObjects(newObjects);

					StructuralFeatureAffectation affectation = generateAffectation(
							workingCopyObject.eContainingFeature(), workingCopyObject);
					if (affectation != null) {
						instanciation.getStructuralFeatures().add(affectation);
					}
				}
				break;
			case SynchronizerChangeState.COMPILED_TARGET_VALUE:
				IntentGenericElement affectation = getContainer(target,
						ModelingUnitPackage.STRUCTURAL_FEATURE_AFFECTATION);
				if (affectation != null) {
					removeFromContainer(affectation);
				} else if (target instanceof InstanciationInstruction) {
					// in this case, the instanciation is not contained by the affectation so we need to
					// remove it separately
					InstanciationInstruction instanciation = (InstanciationInstruction)target;
					ModelingUnitInstructionReference reference = query
							.getModelingUnitInstructionReference(instanciation);
					if (reference != null) {
						removeFromContainer(reference);
					}
					removeFromContainer(instanciation);
				}
				// TODO remove related contributions
				// TODO remove related references
				break;
			default:
				IntentLogger.getInstance().log(
						LogType.INFO,
						"UNSUPPORTED MODEL ELEMENT CHANGE: " + status.getMessage() + '('
								+ status.getChangeState() + ')');
				break;
		}
	}

	/**
	 * Fixes the given statuses.
	 * 
	 * @param status
	 *            the status to fix
	 */
	private void fixStructuralFeatureChange(StructuralFeatureChangeStatus status) {
		EObject element = getWorkingCopyEObject(status.getWorkingCopyElementURIFragment());
		EStructuralFeature feature = element.eClass().getEStructuralFeature(status.getFeatureName());
		Object newValue = null;
		if (status instanceof ReferenceChangeStatus) {
			// in case of collections, we only want the value related to the status
			newValue = getWorkingCopyEObject(((ReferenceChangeStatus)status)
					.getWorkingCopyTargetURIFragment());
		}

		if (newValue == null) {
			newValue = element.eGet(feature);
		}

		switch (status.getChangeState().getValue()) {
			case SynchronizerChangeState.UPDATE_VALUE:
				if (status.getTarget() instanceof ValueForStructuralFeature) {
					if (newValue == null) {
						EObject affectation = getContainer(status.getTarget(),
								ModelingUnitPackage.STRUCTURAL_FEATURE_AFFECTATION);
						if (affectation instanceof StructuralFeatureAffectation) {
							removeFromContainer((StructuralFeatureAffectation)affectation);
						}
					} else {
						setValue((ValueForStructuralFeature)status.getTarget(), newValue);
					}
				} else if (status.getTarget() instanceof InstanciationInstruction) {
					StructuralFeatureAffectation newAffectation = generateAffectation(feature, newValue);
					if (newAffectation != null) {
						addAffectation(status.getTarget(), newAffectation);
					}
				}
				break;

			case SynchronizerChangeState.COMPILED_TARGET_VALUE:
				EObject affectation = getContainer(status.getTarget(),
						ModelingUnitPackage.STRUCTURAL_FEATURE_AFFECTATION);
				if (affectation instanceof StructuralFeatureAffectation) {
					removeFromContainer((StructuralFeatureAffectation)affectation);
				}
				break;

			case SynchronizerChangeState.WORKING_COPY_TARGET_VALUE:
				StructuralFeatureAffectation newAffectation = generateAffectation(feature, newValue);
				IntentGenericElement container = getContainer(status.getTarget(),
						ModelingUnitPackage.CONTRIBUTION_INSTRUCTION,
						ModelingUnitPackage.INSTANCIATION_INSTRUCTION);
				addAffectation(container, newAffectation);
				break;

			default:
				IntentLogger.getInstance().log(LogType.INFO,
						"UNSUPPORTED CHANGE: " + status.getMessage() + " (" + status.getChangeState() + ')');
				break;
		}
	}

	/**
	 * Adds the given affectation in the container.
	 * 
	 * @param container
	 *            the container
	 * @param affectation
	 *            the affectation
	 */
	private void addAffectation(IntentGenericElement container, StructuralFeatureAffectation affectation) {
		if (container instanceof ContributionInstruction) {
			ContributionInstruction contribution = (ContributionInstruction)container;
			contribution.getContributions().add(affectation);
		} else if (container instanceof InstanciationInstruction) {
			InstanciationInstruction instanciation = (InstanciationInstruction)container;
			instanciation.getStructuralFeatures().add(affectation);
		}
	}

}
