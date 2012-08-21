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
package org.eclipse.mylyn.docs.intent.client.ui.ide.repository;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.docs.intent.collab.common.repository.contribution.IntentRepositoryManagerContribution;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryCreator;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryRegistry;

/**
 * An {@link IntentRepositoryManagerContribution} allowing to create an Intent Repository from an
 * {@link IProject} name.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentProjectBasedRepositoryManagerContribution implements IntentRepositoryManagerContribution {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.repository.contribution.IntentRepositoryManagerContribution#canCreateRepository(java.lang.String)
	 */
	public boolean canCreateRepository(String identifier) {
		return !identifier.contains("/");
	}

	/**
	 * Creates the {@link Repository} associated to the given identifier.
	 * 
	 * @param identifier
	 *            the repository identifier (can be an IProject name, a CDO Repository name...).
	 * @return the {@link Repository} associated to the considered project
	 * @throws RepositoryConnectionException
	 *             if the {@link Repository} cannot be created
	 */
	public Repository createRepository(String identifier) throws RepositoryConnectionException {
		Repository repository = null;
		try {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(identifier);
			if (project != null) {
				if (project.exists() && project.isAccessible()) {
					if (!project.isOpen()) {
						project.open(null);
					}

					if (project.hasNature("org.eclipse.mylyn.docs.intent.client.ui.ide.intentNature")) {
						String repositoryType = getRepositoryType(project);
						RepositoryCreator repositoryCreator = RepositoryRegistry.INSTANCE
								.getRepositoryCreator(repositoryType);
						RepositoryStructurer repositoryStructurer = RepositoryRegistry.INSTANCE
								.getRepositoryStructurer(repositoryType);
						if (repositoryCreator == null) {
							throw new RepositoryConnectionException(
									"Cannot instantiate a repository of type:" + repositoryType);
						}
						repository = repositoryCreator.createRepository(project, repositoryStructurer);
					}
				}
			}
		} catch (CoreException e) {
			throw new RepositoryConnectionException(e.getMessage());
		}
		return repository;
	}

	private static String getRepositoryType(IProject project) throws CoreException {
		for (ICommand command : project.getDescription().getBuildSpec()) {
			if ("org.eclipse.mylyn.docs.intent.client.ui.ide.intentBuilder".equals(command.getBuilderName())) {
				return command.getArguments().get("type");
			}
		}
		return null;
	}

}
