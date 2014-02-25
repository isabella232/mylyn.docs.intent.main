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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.mylyn.docs.intent.client.ui.ide.Activator;
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
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.repository.contribution.IntentRepositoryManagerContribution#createRepository(java.lang.String)
	 */
	public Repository createRepository(String repositoryIdentifier) throws RepositoryConnectionException {
		Repository repository = null;
		String identifier = normalizeIdentifier(repositoryIdentifier);
		try {
			final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(identifier);
			if (project != null) {
				if (project.exists() && project.isAccessible()) {
					if (!project.isOpen()) {
						project.open(null);
					}
					RepositoryCreator repositoryCreator = null;
					String repositoryType = null;
					if (project.hasNature("org.eclipse.mylyn.docs.intent.client.ui.ide.intentNature")) {
						repositoryType = getRepositoryType(project);
						repositoryCreator = RepositoryRegistry.INSTANCE.getRepositoryCreator(repositoryType);
					}
					if (repositoryCreator == null) {
						throw new RepositoryConnectionException("Cannot instantiate a repository of type:"
								+ repositoryType);
					}
					RepositoryStructurer repositoryStructurer = RepositoryRegistry.INSTANCE
							.getRepositoryStructurer(repositoryType);
					repository = repositoryCreator.createRepository(project, repositoryStructurer);

					// Trigger asynchronously the Intent clients
					Job triggerClientLaunching = new Job("Opening Intent project") {

						@Override
						protected IStatus run(IProgressMonitor monitor) {
							Activator.getDefault().getIntentProjectListener().handleOpenedProject(project);
							return Status.OK_STATUS;
						}

					};
					triggerClientLaunching.schedule();
				}
			}
		} catch (CoreException e) {
			throw new RepositoryConnectionException(e.getMessage());
		}
		return repository;
	}

	/**
	 * Returns the repository type associated to the given {@link IProject}.
	 * 
	 * @param project
	 *            an intent project
	 * @return the repository type associated to the given {@link IProject}
	 * @throws CoreException
	 *             if project description cannot be properly accessed
	 */
	private static String getRepositoryType(IProject project) throws CoreException {
		for (ICommand command : project.getDescription().getBuildSpec()) {
			if ("org.eclipse.mylyn.docs.intent.client.ui.ide.intentBuilder".equals(command.getBuilderName())) {
				return command.getArguments().get("type");
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.repository.contribution.IntentRepositoryManagerContribution#normalizeIdentifier(java.lang.String)
	 */
	public String normalizeIdentifier(String identifier) {
		String normalizedIdentifier = identifier;
		if (identifier.startsWith("platform:/resource")) {
			normalizedIdentifier = identifier.toString().replaceFirst("platform:/resource/", "");
			normalizedIdentifier = normalizedIdentifier.split("/")[0];
		}
		return normalizedIdentifier;
	}

}
