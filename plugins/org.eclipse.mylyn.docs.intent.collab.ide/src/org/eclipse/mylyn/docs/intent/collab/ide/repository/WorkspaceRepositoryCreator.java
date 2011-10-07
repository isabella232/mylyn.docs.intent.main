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
package org.eclipse.mylyn.docs.intent.collab.ide.repository;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.cdo.eresource.EresourcePackage;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotificationFactoryHolder;
import org.eclipse.mylyn.docs.intent.collab.ide.notification.WorkspaceRepositoryChangeNotificationFactory;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryCreator;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnitPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;

/**
 * Construct Repository according to configuration files.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class WorkspaceRepositoryCreator implements RepositoryCreator {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.repository.RepositoryCreator#createRepository(java.lang.Object,
	 *      org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer)
	 */
	public Repository createRepository(Object artifact, RepositoryStructurer structurer)
			throws RepositoryConnectionException {
		if (artifact instanceof IProject) {
			WorkspaceConfig configurationInformations = new WorkspaceConfig((IProject)artifact,
					IntentLocations.INDEXES_LIST);
			Repository repository = new WorkspaceRepository((WorkspaceConfig)configurationInformations);

			// Initialize the Notification Factory
			if (RepositoryChangeNotificationFactoryHolder.getChangeNotificationFactory() == null) {
				RepositoryChangeNotificationFactoryHolder
						.setChangeNotificationFactory(new WorkspaceRepositoryChangeNotificationFactory());
			}

			initializePackageRegistry(repository);
			repository.setRepositoryStructurer(structurer);
			return repository;
		} else {
			throw new RepositoryConnectionException("The given configuration artifact are invalid.");
		}
	}

	/**
	 * Initializes the package registry of the given repository ; subClass should override this method.
	 * 
	 * @param repository
	 *            the repository containing the package registry to initialize
	 * @throws RepositoryConnectionException
	 *             if the repository connection cannot be established
	 */
	protected void initializePackageRegistry(Repository repository) throws RepositoryConnectionException {
		// Getting all registered EPackages and add them to the repository package registry
		for (String epackageURI : EPackage.Registry.INSTANCE.keySet()) {
			repository.getPackageRegistry().put(epackageURI, EPackage.Registry.INSTANCE.get(epackageURI));
		}
		// Step 5 : initialize the Repository's Package registry
		repository.getPackageRegistry().put(IntentIndexerPackage.eNS_URI, IntentIndexerPackage.eINSTANCE);
		repository.getPackageRegistry().put(CompilerPackage.eNS_URI, CompilerPackage.eINSTANCE);
		repository.getPackageRegistry().put(IntentDocumentPackage.eNS_URI, IntentDocumentPackage.eINSTANCE);
		repository.getPackageRegistry().put(ModelingUnitPackage.eNS_URI, ModelingUnitPackage.eINSTANCE);
		repository.getPackageRegistry().put(DescriptionUnitPackage.eNS_URI, DescriptionUnitPackage.eINSTANCE);
		repository.getPackageRegistry().put(GenericUnitPackage.eNS_URI, GenericUnitPackage.eINSTANCE);
		repository.getPackageRegistry().put(EresourcePackage.eNS_URI, EresourcePackage.eINSTANCE);
	}
}
