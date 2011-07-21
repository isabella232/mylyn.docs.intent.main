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
package org.eclipse.mylyn.docs.intent.client.compiler.repositoryconnection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.client.compiler.ModelingUnitCompiler;
import org.eclipse.mylyn.docs.intent.client.compiler.generator.modellinking.ModelingUnitLinkResolver;
import org.eclipse.mylyn.docs.intent.client.compiler.saver.CompilerInformationsSaver;
import org.eclipse.mylyn.docs.intent.client.compiler.utils.IntentCompilerInformationHolder;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryObjectHandler;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.query.UnitGetter;

/**
 * Represents a compilation operation, that compiles all the modeling units contained in the repository.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class CompilationJob extends Job {
	/**
	 * Name to associate to this job.
	 */
	public static final String COMPILATION_JOB_NAME = "Compiling";

	private Repository repository;

	private RepositoryObjectHandler repositoryObjectHandler;

	/**
	 * CompilationJob constructor.
	 * 
	 * @param repository
	 *            the repository
	 * @param repositoryObjectHandler
	 *            the repository object handler
	 */
	public CompilationJob(Repository repository, RepositoryObjectHandler repositoryObjectHandler) {
		super(COMPILATION_JOB_NAME);
		this.repository = repository;
		this.repositoryObjectHandler = repositoryObjectHandler;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.Imonitor)
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			if (!monitor.isCanceled()) {
				ModelingUnitCompiler compiler = null;
				ModelingUnitLinkResolver resolver = null;
				List<ModelingUnit> modelingUnitsToCompile = new ArrayList<ModelingUnit>();

				// InformationHolder Initialization
				final Resource resourceIndex = repositoryObjectHandler.getRepositoryAdapter().getResource(
						IntentLocations.INTENT_INDEX);

				IntentCompilerInformationHolder informationHolder = IntentCompilerInformationHolder
						.getInstance();
				informationHolder.initialize();

				// LinkResolver initialization

				if (!monitor.isCanceled()) {
					resolver = new ModelingUnitLinkResolver(repository, informationHolder);
				}

				// Compiler initialization
				if (!monitor.isCanceled()) {
					compiler = new ModelingUnitCompiler(repository, resolver, informationHolder,
							BasicMonitor.toMonitor(monitor));

					for (EObject resourceContent : resourceIndex.getContents()) {
						modelingUnitsToCompile.addAll(UnitGetter
								.getAllModelingUnitsContainedInElement(resourceContent));
					}
				}

				// Compilation
				if (!monitor.isCanceled()) {
					compiler.compile(modelingUnitsToCompile);
				}
				// Saving the new compilations errors
				if (!monitor.isCanceled()) {
					System.err.println("[Compiler] compiled : "
							+ informationHolder.getDeclaredResources().size() + " resources. ");
					System.err.println("[Compiler] saving... ("
							+ informationHolder.getCompilationStatusList().size() + " errors detected)");
					saveCompilationInformations(informationHolder, monitor);
					System.err.println("[Compiler] =====================> saved.");
				}
			}
		} catch (RepositoryConnectionException e) {
			e.printStackTrace();
			System.err.println("[Compiler] Compilation  Failed");
		}
		return Status.OK_STATUS;
	}

	/**
	 * Saves the informations calculated during the compilationOperation.
	 * 
	 * @param compilationInformationHolder
	 *            the entity containing all informations needed by this compiler
	 * @param monitor
	 *            The progressMonitor to use for compilation ; if canceled, the compilation will stop
	 *            immediately.
	 */
	public void saveCompilationInformations(IntentCompilerInformationHolder compilationInformationHolder,
			IProgressMonitor monitor) {
		repositoryObjectHandler.getRepositoryAdapter().openSaveContext();
		CompilerInformationsSaver saver = new CompilerInformationsSaver(monitor);
		if (monitor != null && !monitor.isCanceled()) {
			saver.saveOnRepository(compilationInformationHolder, repositoryObjectHandler);
		}
		try {
			repositoryObjectHandler.getRepositoryAdapter().save();
		} catch (ReadOnlyException e) {
			// We are sure that this compiler isn't in read-only mode
		} catch (SaveException e) {
			try {
				repositoryObjectHandler.getRepositoryAdapter().undo();
			} catch (ReadOnlyException e1) {
				// We are sure that this compiler isn't in read-only mode
			}

		}
		repositoryObjectHandler.getRepositoryAdapter().closeContext();

	}
}
