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
package org.eclipse.mylyn.docs.intent.collab.common.repository.contribution;

import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;

/**
 * A contribution used by the
 * {@link org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager} to create
 * repositories from an identifier (i.e. create a repository from an IProject name, create a repository from a
 * cdo Repository name...).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public interface IntentRepositoryManagerContribution {

	/**
	 * Indicates if an Intent Repository can be created from the given identifier.
	 * 
	 * @param identifier
	 *            the repository identifier (can be an IProject name, a CDO Repository name...)
	 * @return trueif an Intent Repository can be created from the given identifier, false otherwise
	 */
	boolean canCreateRepository(String identifier);

	/**
	 * Creates the {@link Repository} associated to the given identifier.
	 * 
	 * @param identifier
	 *            the repository identifier (can be an IProject name, a CDO Repository name...).
	 * @return the {@link Repository} associated to the considered project
	 * @throws RepositoryConnectionException
	 *             if the {@link Repository} cannot be created
	 */
	Repository createRepository(String identifier) throws RepositoryConnectionException;
}
