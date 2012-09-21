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
package org.eclipse.mylyn.docs.intent.collab.common.query;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusManager;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;

/**
 * An utility class allowing to query the {@link CompilationStatusManager} to get useful informations about
 * compilation statuses.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class CompilationStatusQuery extends AbstractIntentQuery {

	private CompilationStatusManager statusManager;

	/**
	 * Creates the query.
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the repository.
	 */
	public CompilationStatusQuery(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * Returns the {@link CompilationStatusManager} of the current repository. If none find, creates it.
	 * 
	 * @return the {@link CompilationStatusManager} of the current repository. If none find, creates it
	 */
	public CompilationStatusManager getOrCreateCompilationStatusManager() {
		if (statusManager == null) {
			try {
				final Resource resource = repositoryAdapter
						.getOrCreateResource(IntentLocations.COMPILATION_STATUS_INDEX_PATH);

				if (resource.getContents().isEmpty()) {
					repositoryAdapter.execute(new IntentCommand() {

						public void execute() {
							resource.getContents().add(
									CompilerFactory.eINSTANCE.createCompilationStatusManager());
						}
					});
				}
				statusManager = (CompilationStatusManager)resource.getContents().get(0);
			} catch (ReadOnlyException e) {
				throw new RuntimeException(e);
			}
		}
		return statusManager;
	}
}
