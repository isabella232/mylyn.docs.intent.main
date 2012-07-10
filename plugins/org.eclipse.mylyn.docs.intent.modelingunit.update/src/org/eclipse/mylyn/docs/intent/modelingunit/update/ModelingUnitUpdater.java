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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.core.compiler.AttributeChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.ModelElementChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
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
	public void fixSynchronizationStatus(ModelingUnit modelingUnit, CompilationStatus... statusToFix) {
		for (CompilationStatus status : statusToFix) {
			switch (status.eClass().getClassifierID()) {
				case CompilerPackage.MODEL_ELEMENT_CHANGE_STATUS:
					fixModelElementChangeStatus(modelingUnit, (ModelElementChangeStatus)status);
					break;
				case CompilerPackage.ATTRIBUTE_CHANGE_STATUS:
					fixAttributeChangeStatus(modelingUnit, (AttributeChangeStatus)status);
					break;
				case CompilerPackage.REFERENCE_CHANGE_STATUS:
					fixReferenceChangeStatus(modelingUnit, (ReferenceChangeStatus)status);
					break;
				default:
					break;
			}
		}
	}

	private void fixModelElementChangeStatus(final ModelingUnit modelingUnit, ModelElementChangeStatus status) {
		switch (status.getChangeState().getValue()) {
			case SynchronizerChangeState.WORKING_COPY_TARGET_VALUE:
				InstanciationInstruction containerInstanciation = (InstanciationInstruction)status
						.getTarget();

				EObject target = getWorkingCopyEObject(status.getWorkingCopyElementURIFragment());

				final ContributionInstruction contribution = generateContribution(containerInstanciation,
						target);
				repositoryAdapter.execute(new IntentCommand() {

					public void execute() {
						try {
							modelingUnit.getInstructions().add(contribution);
							repositoryAdapter.save();
						} catch (ReadOnlyException e) {
							IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
						} catch (SaveException e) {
							IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
						}
					}

				});
				break;

			default:
				debug(status);
				break;
		}
	}

	private EObject getWorkingCopyEObject(String uri) {
		ResourceSetImpl rs = new ResourceSetImpl();
		EObject target = rs.getEObject(URI.createURI(uri), true);
		return target;
	}

	private void fixAttributeChangeStatus(final ModelingUnit modelingUnit, AttributeChangeStatus status) {
		switch (status.getChangeState().getValue()) {
			case SynchronizerChangeState.UPDATE_VALUE:

				InstanciationInstruction target = (InstanciationInstruction)status.getTarget();
				for (StructuralFeatureAffectation affectation : target.getStructuralFeatures()) {
					if (affectation.getName().equals(status.getFeatureName())) {
						final NativeValueForStructuralFeature value = (NativeValueForStructuralFeature)affectation
								.getValues().get(0);

						EObject element = getWorkingCopyEObject(status.getWorkingCopyElementURIFragment());
						final Object newValue = element.eGet(element.eClass().getEStructuralFeature(
								status.getFeatureName()));
						repositoryAdapter.execute(new IntentCommand() {

							public void execute() {
								try {
									value.setValue("\"" + newValue.toString() + "\"");
									repositoryAdapter.save();
								} catch (ReadOnlyException e) {
									IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
								} catch (SaveException e) {
									IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
								}
							}

						});

					}
				}

				break;

			default:
				debug(status);
				break;
		}
	}

	private void fixReferenceChangeStatus(ModelingUnit modelingUnit, ReferenceChangeStatus status) {
		switch (status.getChangeState().getValue()) {
			default:
				debug(status);
				break;
		}
	}

	private void debug(SynchronizerCompilationStatus status) {
		System.err.println("UNSUPPORTED :");
		System.err.println(status.eClass().getName());
		System.err.println(status.getMessage());
	}

}
