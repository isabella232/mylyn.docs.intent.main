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
package org.eclipse.mylyn.docs.intent.collab.common.uri;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;

/**
 * A ResourceFactory allowing to resolve {@link URI}s of the following forms :
 * <ul>
 * <li>intent:/INTENT_REPOSITORY_IDENTIFIER returns a Resource containing the IntentDocument</li>
 * <li>intent:/INTENT_REPOSITORY_IDENTIFIER#/ returns the IntentDocument</li>
 * <li>intent:/INTENT_REPOSITORY_IDENTIFIER/abstractResource returns a Resource containing the compiled
 * resource entitled 'abstractResource'</li>
 * <li>intent:/INTENT_REPOSITORY_IDENTIFIER/abstractResource#/ returns the content of the Resource containing
 * the compiled resource entitled 'abstractResource'</li>
 * </ul>
 * .
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentResourceFactory implements Resource.Factory {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.resource.Resource.Factory#createResource(org.eclipse.emf.common.util.URI)
	 */
	public Resource createResource(URI uri) {

		String intentRepositoryIdentifier = extractRepositoryIdentifier(uri);

		try {
			Resource resource = null;
			// Step 1: get the Intent repository indicated by this URI
			Repository repository = IntentRepositoryManager.INSTANCE
					.getRepository(intentRepositoryIdentifier);

			// Step 2: open a repository adapter
			RepositoryAdapter repositoryAdapter = repository.createRepositoryAdapter();

			// Step 3: if the URI contains a resource identifier
			String compiledResourceIdentifier = extractCompiledResourceIdentifier(uri);
			if (compiledResourceIdentifier != null && compiledResourceIdentifier.length() > 0) {
				// we return the compiled resource corresponding to this fragment
				resource = repositoryAdapter.getResource(IntentLocations.GENERATED_RESOURCES_FOLDER_PATH
						+ compiledResourceIdentifier);
			} else {
				// otherwise, we return the document
				resource = repositoryAdapter.getResource(IntentLocations.INTENT_INDEX);
			}
			return resource;
		} catch (RepositoryConnectionException e) {
			throw new RuntimeException(e);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Intent repository identifier contained in the given {@link URI}.
	 * 
	 * @param uri
	 *            the {@link URI} to inspect
	 * @return the Intent repository identifier contained in the given {@link URI}
	 */
	private String extractRepositoryIdentifier(URI uri) {
		String[] paths = uri.path().split("/");
		if (paths.length > 0) {
			return paths[1];
		}
		return "";
	}

	/**
	 * Returns the compiled resource identifier (if any) contained in the given {@link URI}.
	 * 
	 * @param uri
	 *            the {@link URI} to inspect
	 * @return the compiled resource identifier (if any) contained in the given {@link URI}
	 */
	private String extractCompiledResourceIdentifier(URI uri) {
		String[] paths = uri.path().split("/");
		if (paths.length > 2) {
			String compiledResourceIdentifier = paths[2];
			for (int i = 3; i < paths.length; i++) {
				compiledResourceIdentifier += paths[i];
			}
			return compiledResourceIdentifier;
		}
		return "";
	}
}
