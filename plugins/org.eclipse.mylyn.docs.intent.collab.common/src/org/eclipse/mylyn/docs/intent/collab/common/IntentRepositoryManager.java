/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.collab.common;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.docs.intent.collab.common.internal.IntentRepositoryManagerImpl;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;

/**
 * Manages the creation of repositories.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public interface IntentRepositoryManager {
	IntentRepositoryManager INSTANCE = new IntentRepositoryManagerImpl();

	/**
	 * Returns the {@link Repository} associated to the given Intent project name.
	 * 
	 * @param projectName
	 *            the Intent project name to get the Repository from
	 * @return the {@link Repository} associated to the given Intent project name
	 * @throws RepositoryConnectionException
	 *             if the repository cannot be created
	 * @throws CoreException
	 *             if the repository type cannot be found
	 */
	Repository getRepository(String projectName) throws RepositoryConnectionException, CoreException;

	/**
	 * Indicates if the given project is handled by a {@link Repository}.
	 * 
	 * @param projectName
	 *            the project to test
	 * @return true if the given project is handled by a {@link Repository}, false otherwise
	 */
	boolean isManagedProject(String projectName);

	/**
	 * Removes the repository from the registry.
	 * 
	 * @param projectName
	 *            the projectName associated to the repository
	 */
	void deleteRepository(String projectName);

}
