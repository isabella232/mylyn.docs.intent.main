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
package org.eclipse.mylyn.docs.intent.collab.common.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryCreator;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryRegistry;

/**
 * Handles an Intent Project lifecycle :
 * <ul>
 * <li>Create/Delete the Repository</li>
 * <li>Launch/Stop Intent Clients</li>.
 * </ul>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class IntentRepositoryManagerImpl implements IntentRepositoryManager {

	/**
	 * The list of created repositories, associated to the corresponding project.
	 */
	private Map<String, Repository> repositoriesByProject = new HashMap<String, Repository>();

	private boolean lock;

	/**
	 * Gets or creates the {@link Repository} associated to the considered project.
	 * 
	 * @param project
	 *            the project
	 * @return the {@link Repository} associated to the considered project
	 * @throws RepositoryConnectionException
	 *             if the {@link Repository} cannot be created or accessed
	 */
	private Repository createRepository(IProject project) throws RepositoryConnectionException, CoreException {
		Repository repository = null;
		if (project.hasNature("org.eclipse.mylyn.docs.intent.client.ui.ide.intentNature")) {
			String repositoryType = getRepositoryType(project);
			RepositoryCreator repositoryCreator = RepositoryRegistry.INSTANCE
					.getRepositoryCreator(repositoryType);
			RepositoryStructurer repositoryStructurer = RepositoryRegistry.INSTANCE
					.getRepositoryStructurer(repositoryType);
			if (repositoryCreator == null) {
				throw new RepositoryConnectionException("Cannot instantiate a repository of type:"
						+ repositoryType);
			}
			repository = repositoryCreator.createRepository(project, repositoryStructurer);
		}
		return repository;
	}

	/**
	 * Returns the {@link Repository} associated to the given Intent project.
	 * 
	 * @param project
	 *            the Intent project to get the Repository from
	 * @return the {@link Repository} associated to the given Intent project
	 * @throws RepositoryConnectionException
	 *             if the repository cannot be created
	 * @throws CoreException
	 */
	private Repository getRepository(IProject project) throws RepositoryConnectionException, CoreException {
		Repository repository = repositoriesByProject.get(project.getName());
		if (repository == null) {
			repository = createRepository(project);
			repositoriesByProject.put(project.getName(), repository);
		}
		return repository;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws CoreException
	 * @see org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager#getRepository(java.lang.String)
	 */
	public synchronized Repository getRepository(String projectName) throws RepositoryConnectionException,
			CoreException {
		Assert.isTrue(!lock);
		lock = true;
		Repository repository = null;
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		if (project != null && project.isAccessible()) {
			repository = getRepository(project);
		}
		lock = false;
		return repository;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager#isManagedProject(java.lang.String)
	 */
	public synchronized boolean isManagedProject(String projectName) {
		return repositoriesByProject.get(projectName) != null;
	}

	private static String getRepositoryType(IProject project) throws CoreException {
		for (ICommand command : project.getDescription().getBuildSpec()) {
			if (command.getBuilderName().equals("org.eclipse.mylyn.docs.intent.client.ui.ide.intentBuilder")) {
				return command.getArguments().get("type");
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager#deleteRepository(java.lang.String)
	 */
	public synchronized void deleteRepository(String projectName) {
		repositoriesByProject.remove(projectName);
	}

}
