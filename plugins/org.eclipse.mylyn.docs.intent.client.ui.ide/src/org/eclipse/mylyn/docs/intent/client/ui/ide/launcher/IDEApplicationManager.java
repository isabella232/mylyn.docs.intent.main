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
package org.eclipse.mylyn.docs.intent.client.ui.ide.launcher;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.collab.utils.RepositoryCreatorHolder;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerFactory;
import org.eclipse.mylyn.docs.intent.parser.IntentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;

/**
 * In charge of creating the repository and launching clients in the context of a Workspace Intent Repository.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class IDEApplicationManager {

	/**
	 * IDEApplicationManager constructor.
	 */
	private IDEApplicationManager() {

	}

	/**
	 * Initializes the project with sample content.
	 * 
	 * @param project
	 *            the project to initialize
	 * @param initialContent
	 *            the initialContent
	 */
	public static void initializeContent(IProject project, String initialContent) {
		try {
			if (project.isAccessible()) {
				IntentProjectManager.getRepository(project).getOrCreateSession();
				if (project.exists()) {
					if (!project.isOpen()) {
						project.open(null);
					}
				}
				initializeWithSampleContent(IntentProjectManager.getRepository(project), initialContent);
			}
		} catch (CoreException e) {
			IntentUiLogger.logError(e);
		} catch (RepositoryConnectionException e) {
			IntentUiLogger.logError(e);
		}
	}

	/**
	 * Initializes the given repository using the given list of files to load.
	 * 
	 * @param repositoryToInitialize
	 *            the repository to initialize
	 * @param initialContent
	 *            initial content as String
	 * @throws RepositoryConnectionException
	 *             if the connection to the repository is invalid
	 */
	private static void initializeWithSampleContent(Repository repositoryToInitialize,
			final String initialContent) throws RepositoryConnectionException {
		final RepositoryAdapter repositoryAdapter = RepositoryCreatorHolder.getCreator()
				.createRepositoryAdapterForRepository(repositoryToInitialize);

		repositoryAdapter.execute(new IntentCommand() {

			public void execute() {
				repositoryAdapter.openSaveContext();
				try {
					initializeInRepository(initialContent, repositoryAdapter);
				} catch (ReadOnlyException e) {
					IntentUiLogger.logError(e);
				} catch (ParseException e) {
					IntentUiLogger.logError(e);
				} catch (SaveException e) {
					IntentUiLogger.logError(e);
				}
				repositoryAdapter.closeContext();
			}
		});
	}

	/**
	 * Initializes the content in the repository using the given repository adapter.
	 * 
	 * @param initialContent
	 *            the initial content
	 * @param repositoryAdapter
	 *            the adapter
	 * @throws ReadOnlyException
	 * @throws ParseException
	 * @throws SaveException
	 */
	private static void initializeInRepository(final String initialContent,
			final RepositoryAdapter repositoryAdapter) throws ReadOnlyException, ParseException,
			SaveException {
		Resource wpResourceIndex = repositoryAdapter.getOrCreateResource(IntentLocations.GENERAL_INDEX_PATH);
		wpResourceIndex.getContents().add(IntentIndexerFactory.eINSTANCE.createIntentIndex());
		Resource wpCompilStatusIndex = repositoryAdapter
				.getOrCreateResource(IntentLocations.COMPILATION_STATUS_INDEX_PATH);
		wpCompilStatusIndex.getContents().add(CompilerFactory.eINSTANCE.createCompilationStatusManager());
		Resource wpTracabilityIndexResource = repositoryAdapter
				.getOrCreateResource(IntentLocations.TRACEABILITY_INFOS_INDEX_PATH);
		wpTracabilityIndexResource.getContents().add(CompilerFactory.eINSTANCE.createTraceabilityIndex());

		Resource repositoryIntentResource;

		repositoryIntentResource = repositoryAdapter.getOrCreateResource(IntentLocations.INTENT_INDEX);

		if (repositoryIntentResource.getContents().size() == 0) {
			repositoryIntentResource.getContents().clear();

			List<EObject> elementsToUpload = new ArrayList<EObject>();

			EObject parsedObject = new IntentParser().parse(initialContent);
			elementsToUpload.add(parsedObject);

			for (EObject objectToCopy : elementsToUpload) {
				repositoryIntentResource.getContents().add(EcoreUtil.copy(objectToCopy));
			}

			// Step : closing the session
			repositoryAdapter.save();
		} else {
			repositoryAdapter.undo();
		}
	}

}
