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

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;

/**
 * In charge of communication between the repository and the compiler ; launch a compilation operation each
 * time a modification on modeling units is detected.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class CompilerRepositoryClient extends AbstractRepositoryClient {

	/**
	 * The repository to use for access to package registry and several informations.
	 */
	private Repository repository;

	/**
	 * Sets the repository to use for saving and closing getConnexion.
	 * 
	 * @param repository
	 *            the repository to use for saving and closing getConnexion
	 */
	public void setRepository(Repository repository) {
		this.repository = repository;
		this.repository.register(this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient#createNotificationJob(org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification)
	 */
	@Override
	protected Job createNotificationJob(RepositoryChangeNotification notification) {
		return new CompilationJob(this.repository, this.repositoryObjectHandler);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryClient#dispose()
	 */
	public void dispose() {
		this.repository.unregister(this);
		super.dispose();
	}
}
