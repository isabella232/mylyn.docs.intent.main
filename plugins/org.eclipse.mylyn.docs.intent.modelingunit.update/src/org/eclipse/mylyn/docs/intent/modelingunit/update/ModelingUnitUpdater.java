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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationMessageType;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.modelingunit.gen.ModelingUnitGenerator;

/**
 * Utility which updates modeling units according to existing models.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class ModelingUnitUpdater extends ModelingUnitGenerator {

	private RepositoryAdapter repositoryAdapter;

	private ModelingUnit modelingUnit;

	/**
	 * Creates a modeling unit updater.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 * @param modelingUnitToUpdate
	 *            the modeling unit to update
	 */
	public ModelingUnitUpdater(RepositoryAdapter repositoryAdapter, ModelingUnit modelingUnitToUpdate) {
		this.repositoryAdapter = repositoryAdapter;
		this.modelingUnit = modelingUnitToUpdate;
	}

	/**
	 * Fixes the given statuses by updating the current modeling unit.
	 * 
	 * @param statusToFix
	 *            the statuses to fix
	 */
	public void fixSynchronizationStatus(CompilationStatus... statusToFix) {
		for (CompilationStatus status : statusToFix) {
			switch (status.getType().getValue()) {
			// TODO extend synchronization statuses qualification
				case CompilationMessageType.SYNCHRONIZER_WARNING_VALUE:
					InstanciationInstruction instanciation = (InstanciationInstruction)((SynchronizerCompilationStatus)status)
							.getTarget();
					create(instanciation, getRootEObjectToGenerate((SynchronizerCompilationStatus)status));
					break;

				default:
					break;
			}
		}
	}

	/**
	 * Creates or update the given target element.
	 * 
	 * @param containerInstanciation
	 *            the instruction that defines the containment of the target element
	 * @param target
	 *            the element to create or update
	 */
	private void create(InstanciationInstruction containerInstanciation, EObject target) {
		final ContributionInstruction contribution = generateContribution(containerInstanciation, target);
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
	}

	/**
	 * Returns the root EObject to generate.
	 * 
	 * @param status
	 *            the sync status
	 * @return the root EObject to generate
	 */
	public static EObject getRootEObjectToGenerate(SynchronizerCompilationStatus status) {
		String workingCopyResourceURI = status.getWorkingCopyResourceURI().replace("\"", "");
		ResourceSetImpl rs = new ResourceSetImpl();
		Resource workingCopyResource = rs.getResource(URI.createURI(workingCopyResourceURI), true);
		String workingCopyElementURIFragment = status.getWorkingCopyElementURIFragment().replace("\"", "");
		return workingCopyResource.getEObject(workingCopyElementURIFragment);
	}
}
