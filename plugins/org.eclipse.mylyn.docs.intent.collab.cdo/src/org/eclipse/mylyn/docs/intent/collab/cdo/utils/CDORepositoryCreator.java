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
package org.eclipse.mylyn.docs.intent.collab.cdo.utils;

import org.eclipse.mylyn.docs.intent.collab.cdo.notification.CDORepositoryChangeNotificationFactory;
import org.eclipse.mylyn.docs.intent.collab.cdo.repository.CDOConfig;
import org.eclipse.mylyn.docs.intent.collab.cdo.repository.CDORepository;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotificationFactoryHolder;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryCreator;

/**
 * Construct Repository according to configuration files.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class CDORepositoryCreator implements RepositoryCreator {

	/**
	 * RepositoryCreator constructor.
	 */
	public CDORepositoryCreator() {

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.repository.RepositoryCreator#createRepository(java.lang.Object,
	 *      org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer)
	 */
	public Repository createRepository(Object configurationInformations, RepositoryStructurer structurer)
			throws RepositoryConnectionException {
		if (!(configurationInformations instanceof CDOConfig)) {
			throw new RepositoryConnectionException("The given configuration informations are invalid.");
		}

		if (!(RepositoryChangeNotificationFactoryHolder.getChangeNotificationFactory() instanceof CDORepositoryChangeNotificationFactory)) {
			RepositoryChangeNotificationFactoryHolder
					.setChangeNotificationFactory(new CDORepositoryChangeNotificationFactory());
		}
		Repository repository = new CDORepository((CDOConfig)configurationInformations);
		repository.setRepositoryStructurer(structurer);
		initialisePackageRegistry(repository);
		return repository;
	}

	/**
	 * Initializes the package registry of the given repository ; subClass should override this method.
	 * 
	 * @param repository
	 *            the repository containing the package registry to initialize
	 * @throws RepositoryConnectionException
	 *             if the repository connection cannot be established
	 */
	protected void initialisePackageRegistry(Repository repository) throws RepositoryConnectionException {

	}

}
