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
package org.eclipse.mylyn.docs.intent.collab.repository.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryCreator;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryRegistry;

/**
 * The Intent repositories registry.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class RepositoryRegistryImpl implements RepositoryRegistry {
	private static final String REPOSITORY_CREATOR_CLASS_TAG = "repositoryCreatorClass";

	private static final String REPOSITORY_STRUCTURER_CLASS_TAG = "repositoryStructurerClass";

	private static final String REPOSITORY_EXTENSION_POINT = "org.eclipse.mylyn.docs.intent.collab.repository.extension"; //$NON-NLS-1$

	private Map<String, IConfigurationElement> repositoryExtensionsByName;

	/**
	 * Initializes the registry.
	 */
	private void initializeRegistry() {
		if (repositoryExtensionsByName == null) {
			repositoryExtensionsByName = new HashMap<String, IConfigurationElement>();
			IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(
					REPOSITORY_EXTENSION_POINT);
			for (IConfigurationElement element : elements) {
				String name = element.getAttribute("id");
				repositoryExtensionsByName.put(name, element);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.repository.RepositoryRegistry#getRepositoryCreator(java.lang.String)
	 */
	public synchronized RepositoryCreator getRepositoryCreator(String repositoryType) throws CoreException {
		if (repositoryExtensionsByName == null) {
			initializeRegistry();
		}
		IConfigurationElement element = repositoryExtensionsByName.get(repositoryType);
		if (element != null) {
			return (RepositoryCreator)element.createExecutableExtension(REPOSITORY_CREATOR_CLASS_TAG);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.repository.RepositoryRegistry#getRepositoryStructurer(java.lang.String)
	 */
	public synchronized RepositoryStructurer getRepositoryStructurer(String repositoryType)
			throws CoreException {
		if (repositoryExtensionsByName == null) {
			initializeRegistry();
		}
		IConfigurationElement element = repositoryExtensionsByName.get(repositoryType);
		if (element != null) {
			if (element.getAttribute(REPOSITORY_STRUCTURER_CLASS_TAG) != null) {
				return (RepositoryStructurer)element
						.createExecutableExtension(REPOSITORY_STRUCTURER_CLASS_TAG);
			}
		}
		return null;
	}

}
