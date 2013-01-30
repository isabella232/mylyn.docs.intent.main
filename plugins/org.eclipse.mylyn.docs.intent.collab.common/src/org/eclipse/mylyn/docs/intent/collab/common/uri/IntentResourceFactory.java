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

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.collab.common.internal.uri.contribution.IntentResourceInitializerRegistry;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
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
	 * The scheme associated to this resource factory.
	 */
	public static String INTENT_FACTORY_SCHEME = "intent";

	/**
	 * A tag allowing to indicate through the URI whether resource should be created if it does not exists.
	 */
	public static String CREATE_RESOURCE_IF_NEEDED_TAG = "[create]";

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.resource.Resource.Factory#createResource(org.eclipse.emf.common.util.URI)
	 */
	public Resource createResource(URI uri) {
		boolean createResourceIFNeeded = uri.toString().endsWith(CREATE_RESOURCE_IF_NEEDED_TAG);
		URI intentURI = URI.createURI(uri.toString().replace(CREATE_RESOURCE_IF_NEEDED_TAG, ""));
		String intentRepositoryIdentifier = extractRepositoryIdentifier(intentURI);

		try {
			// Step 1: get the Intent repository indicated by this URI
			Repository repository = IntentRepositoryManager.INSTANCE
					.getRepository(intentRepositoryIdentifier);

			// Step 2: open a repository adapter
			String compiledResourceIdentifier = extractCompiledResourceIdentifier(intentURI);
			String referencedResourcePath = null;
			// Step 3: get the repository path of the referenced resource to load
			// if the URI contains a resource identifier
			if (compiledResourceIdentifier != null && compiledResourceIdentifier.length() > 0) {
				// we return the compiled resource corresponding to this fragment
				referencedResourcePath = IntentLocations.GENERATED_RESOURCES_FOLDER_PATH
						+ compiledResourceIdentifier;
			} else {
				// otherwise, we return the document
				referencedResourcePath = IntentLocations.INTENT_INDEX;
			}

			// Step 4: load the resource and return it
			RepositoryAdapter repositoryAdapter = repository.createRepositoryAdapter();
			Resource resource = null;
			if (createResourceIFNeeded) {
				repositoryAdapter.openSaveContext();
				try {
					resource = repositoryAdapter.getOrCreateResource(referencedResourcePath);
					if (resource.getContents().isEmpty()) {
						fillResource(intentURI, resource, repositoryAdapter);
					}
				} finally {
					repositoryAdapter.closeContext();
				}
			} else {
				resource = repositoryAdapter.getResource(referencedResourcePath);
			}
			return resource;
		} catch (RepositoryConnectionException e) {
			throw new RuntimeException(e);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		} catch (ReadOnlyException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Initializes the given resource content it using the {@link IIntentResourceInitializer}s contributed
	 * through extension points.
	 * 
	 * @param intentURI
	 *            the intent {@link URI} corresponding to the empty {@link Resource}
	 * @param resource
	 *            the empty {@link Resource}
	 * @param repositoryAdapter
	 *            the repository adapter to use for creating the resource
	 * @return the created and filled resource
	 * @throws ReadOnlyException
	 *             if rights are not sufficient to create a save context to create the resource
	 * @throws IOException
	 *             if resource cannot be saved
	 */
	private void fillResource(final URI intentURI, final Resource resource,
			RepositoryAdapter repositoryAdapter) throws ReadOnlyException, IOException {
		EObject initialResourceContent = null;
		String fileExtension = intentURI.fileExtension();
		for (IIntentResourceInitializer intentResourceInitializer : IntentResourceInitializerRegistry
				.getIntentResourceInitializers(fileExtension)) {
			if (initialResourceContent == null) {
				initialResourceContent = intentResourceInitializer.getInitialContent(intentURI);
			} else {
				IntentLogger.getInstance().log(
						LogType.WARNING,
						"More than one IntentResourceInitializers have been found for the '" + fileExtension
								+ "' file extension. Only the first one will be considered");
			}
		}
		if (initialResourceContent != null) {
			final EObject root = initialResourceContent;
			repositoryAdapter.execute(new IntentCommand() {

				public void execute() {
					resource.getContents().add(root);
				}
			});
			resource.save(null);
		} else {
			IntentLogger
					.getInstance()
					.log(LogType.ERROR,
							"Could not find any IIntentResourceInitializer for the '"
									+ fileExtension
									+ "' file extension. You can register new ones using the org.eclipse.mylyn.docs.intent.collab.common.intentresourceinitializer extension point");
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
		String uriWithoutFragment = uri.trimFragment().toString();
		String[] paths = uriWithoutFragment.split("/");
		if (paths.length > 2) {
			String compiledResourceIdentifier = paths[2];
			for (int i = 3; i < paths.length; i++) {
				compiledResourceIdentifier += paths[i];
			}
			if (uri.hasFragment()) {
				compiledResourceIdentifier += "_" + uri.fragment().replace("/", "@");
			}
			return compiledResourceIdentifier;
		}
		return "";
	}
}
