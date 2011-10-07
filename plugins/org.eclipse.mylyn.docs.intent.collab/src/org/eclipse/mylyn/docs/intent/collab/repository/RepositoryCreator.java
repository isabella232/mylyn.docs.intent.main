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
package org.eclipse.mylyn.docs.intent.collab.repository;

import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;

/**
 * Providing facilities for creating Repository, RepositoryAdapter, RepositoryObjectHandlers.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public interface RepositoryCreator {

	/**
	 * Constructs an Intent Repository according to configuration files.
	 * 
	 * @param artifact
	 *            the artifact symbolizing the intent repository needed by the creator
	 * @param structurer
	 *            the repository structurer (optional, can be null)
	 * @return the constructed repository
	 * @throws RepositoryConnectionException
	 *             if the repository connection cannot be established
	 */
	Repository createRepository(Object artifact, RepositoryStructurer structurer)
			throws RepositoryConnectionException;

}
