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
package org.eclipse.mylyn.docs.intent.retro;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.mylyn.docs.intent.client.synchronizer.SynchronizerRepositoryClient;
import org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtension;
import org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtensionRegistry;
import org.eclipse.mylyn.docs.intent.client.synchronizer.listeners.AbstractGeneratedElementListener;

public class RetroGeneratedElementListener extends AbstractGeneratedElementListener implements ISynchronizerExtension, IResourceChangeListener {

	public static final String RETRO_SCHEME = "retro";

	protected Map<URI, Set<SynchronizerRepositoryClient>> uriToSynchronizers = Maps.newLinkedHashMap();

	private ArrayList<URI> resourcesToIgnore;

	/**
	 * Default constructor.
	 */
	public RetroGeneratedElementListener() {
		super();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(this);
		resourcesToIgnore = new ArrayList<URI>();
	}

	/**
	 * Returns the active instance.
	 * 
	 * @return the active instance
	 */
	public static RetroGeneratedElementListener getInstance() {
		Iterable<RetroGeneratedElementListener> synchronizerExtensions = Iterables.filter(
				ISynchronizerExtensionRegistry.getSynchronizerExtensions(RETRO_SCHEME),
				RetroGeneratedElementListener.class);
		if (synchronizerExtensions.iterator().hasNext()) {
			return synchronizerExtensions.iterator().next();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtension#addListenedElements(org.eclipse.mylyn.docs.intent.client.synchronizer.SynchronizerRepositoryClient,
	 *      java.util.Set)
	 */
	public void addListenedElements(SynchronizerRepositoryClient synchronizer, Set<URI> listenedElementsURIs) {
		for (URI uri : listenedElementsURIs) {
			if (!(uriToSynchronizers.containsKey(uri))) {
				uriToSynchronizers.put(uri, Sets.<SynchronizerRepositoryClient> newLinkedHashSet());
			}
			uriToSynchronizers.get(uri).add(synchronizer);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtension#removeListenedElements(org.eclipse.mylyn.docs.intent.client.synchronizer.SynchronizerRepositoryClient,
	 *      java.util.Set)
	 */
	public void removeListenedElements(SynchronizerRepositoryClient synchronizer,
			Set<URI> listenedElementsURIs) {
		for (URI uri : listenedElementsURIs) {
			uriToSynchronizers.remove(uri);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
			final IResourceDelta rootDelta = event.getDelta();
			// We get the delta related to the Repository (if any)

			// If any resource of the repository has changed
			if (rootDelta != null) {

				// We launch the analysis of the delta in a new thread
				Runnable runnable = new Runnable() {

					public void run() {
						analyseWorkspaceDelta(rootDelta);
					}
				};
				Thread t = new Thread(runnable);
				t.start();

			}
		}
	}

	/**
	 * Analyzes the given IResourceDelta in a new thread ; reloads the resources if needed and send
	 * notification to the registered Session listeners.
	 * 
	 * @param repositoryDelta
	 *            the IResourceDelta to analyse
	 */
	private void analyseWorkspaceDelta(IResourceDelta repositoryDelta) {

		// We first create a DeltaVisitor on the repository Path
		final RetroGeneratedElementListenerDeltaVisitor visitor = new RetroGeneratedElementListenerDeltaVisitor(
				listenedElementsURIs);
		try {
			// We visit the given delta using this visitor
			repositoryDelta.accept(visitor);

			// We get the changed and removed Resources
			Collection<URI> changedResources = new ArrayList<URI>();

			if (!visitor.getRemovedResources().isEmpty()) {
				changedResources.addAll(visitor.getRemovedResources());
			}

			for (URI changedResource : visitor.getChangedResources()) {
				// If the resource is contained in the resourcesToIgnore list, it means
				// that we should ignore this notification ; however we remove this resource
				// from this list so that we'll treat the next notifications
				if (!resourcesToIgnore.contains(changedResource)) {
					changedResources.add(changedResource);
					// resourcesToIgnore.add(changedResource);
				} else {
					resourcesToIgnore.remove(changedResource);
				}
			}

			// Finally, we treat each removed or changed resource.
			treatChangedResources(changedResources);

		} catch (CoreException e) {
			// TODO define a standard reaction to this exception :
			// - relaunch the session
			// - try to visit the delta again
			// - do nothing
		}
	}

	protected void treatChangedResources(Collection<URI> changedResources) {
		Set<SynchronizerRepositoryClient> synchronizersToNotify = Sets.newLinkedHashSet();
		for (URI uri : changedResources) {
			Set<SynchronizerRepositoryClient> listeningSynchronizers = uriToSynchronizers.get(uri);
			if (listeningSynchronizers != null) {
				synchronizersToNotify.addAll(listeningSynchronizers);
			}
		}
		for (SynchronizerRepositoryClient listeningSynchronizer : synchronizersToNotify) {
			listeningSynchronizer.handleChangeNotification(null);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.listeners.GeneratedElementListener#dispose()
	 */
	public void dispose() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.removeResourceChangeListener(this);
	}

}
