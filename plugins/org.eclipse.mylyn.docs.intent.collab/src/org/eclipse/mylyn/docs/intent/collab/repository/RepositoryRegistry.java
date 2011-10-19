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
package org.eclipse.mylyn.docs.intent.collab.repository;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;
import org.eclipse.mylyn.docs.intent.collab.repository.internal.RepositoryRegistryImpl;

/**
 * Registers repository accessors.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public interface RepositoryRegistry {

	RepositoryRegistry INSTANCE = new RepositoryRegistryImpl();

	/**
	 * Returns the repository creator associated with the given repository type.
	 * 
	 * @param repositoryType
	 *            the repository type
	 * @return the repository creator
	 * @throws CoreException
	 */
	RepositoryCreator getRepositoryCreator(String repositoryType) throws CoreException;

	/**
	 * Returns the repository structurer associated with the given repository type.
	 * 
	 * @param repositoryType
	 *            the repository type
	 * @return the repository structurer
	 * @throws CoreException
	 */
	RepositoryStructurer getRepositoryStructurer(String repositoryType) throws CoreException;

}
