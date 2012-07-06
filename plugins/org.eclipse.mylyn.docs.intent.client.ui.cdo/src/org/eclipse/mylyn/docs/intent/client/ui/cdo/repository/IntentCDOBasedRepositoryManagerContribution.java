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
package org.eclipse.mylyn.docs.intent.client.ui.cdo.repository;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.docs.intent.collab.cdo.repository.CDOConfig;
import org.eclipse.mylyn.docs.intent.collab.common.repository.contribution.IntentRepositoryManagerContribution;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryCreator;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryRegistry;

/**
 * An {@link IntentRepositoryManagerContribution} allowing to create an Intent Repository from CDO Repository
 * name.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentCDOBasedRepositoryManagerContribution implements IntentRepositoryManagerContribution {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.repository.contribution.IntentRepositoryManagerContribution#canCreateRepository(java.lang.String)
	 */
	public boolean canCreateRepository(String identifier) {
		return identifier.contains("cdo:/");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.repository.contribution.IntentRepositoryManagerContribution#createRepository(java.lang.String)
	 */
	public Repository createRepository(String identifier) throws RepositoryConnectionException {
		String repositoryType = "org.eclipse.mylyn.docs.intent.collab.cdo.repository";
		RepositoryCreator repositoryCreator;
		try {
			repositoryCreator = RepositoryRegistry.INSTANCE.getRepositoryCreator(repositoryType);

			RepositoryStructurer repositoryStructurer = RepositoryRegistry.INSTANCE
					.getRepositoryStructurer(repositoryType);
			if (repositoryCreator == null) {
				throw new RepositoryConnectionException("Cannot instantiate a repository of type:"
						+ repositoryType);
			}
			CDOConfig config = new CDOConfig("localhost:2036", getRepositoryName(identifier));
			return repositoryCreator.createRepository(config, repositoryStructurer);
		} catch (CoreException e) {
			throw new RepositoryConnectionException(e.getMessage());
		}
	}

	private String getRepositoryName(String identifier) {
		String repositoryName = identifier.replaceFirst("cdo:/", "");
		repositoryName = repositoryName.split("/")[0];
		return repositoryName;
	}

}
