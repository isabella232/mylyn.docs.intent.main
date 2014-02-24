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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.notification.elementList.ElementListAdapter;
import org.eclipse.mylyn.docs.intent.collab.ide.adapters.WorkspaceAdapter;
import org.eclipse.mylyn.docs.intent.collab.ide.adapters.WorkspaceAdapter.InternalModificationAdapter;
import org.eclipse.mylyn.docs.intent.collab.ide.notification.WorkspaceTypeListener;

/**
 * Represents a Session that will notify any listening entities about changes on Workspace resources
 * corresponding to repository resources.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class WorkspaceSession implements IResourceChangeListener {

	/**
	 * Resources that had been saved but for which we didn't receive any notification yet.
	 */
	protected Collection<Resource> savedResources = Lists.newArrayList();

	/**
	 * The {@link WorkspaceRepository} associated to this session.
	 */
	private WorkspaceRepository repository;

	/**
	 * The path of the listened repository.
	 */
	private final Path repositoryPath;

	/**
	 * The WorkspaceRepository resource set used to access the EMF Resources.
	 */
	private WorkspaceAdapter repositoryAdapter;

	/**
	 * List of all registered listeners that should be notified of each modification on a resource.
	 */
	private final List<WorkspaceTypeListener> workspaceSessionListeners;

	/**
	 * Indicates if the session is still reacting to changes on resources ; in this case the repository's
	 * resource set shouldn't be modified.
	 */
	private boolean isBusy;

	/**
	 * WPSession constructor.
	 * 
	 * @param repository
	 *            The {@link WorkspaceRepository} associated to this session
	 */
	public WorkspaceSession(WorkspaceRepository repository) {
		this.repository = repository;
		this.repositoryPath = new Path(repository.getWorkspaceConfig().getRepositoryAbsolutePath());
		this.repositoryAdapter = new WorkspaceAdapter(repository);
		this.workspaceSessionListeners = new ArrayList<WorkspaceTypeListener>();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		// We want to be notified AFTER any changed that occurred
		if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
			IResourceDelta rootDelta = event.getDelta();
			// We get the delta related to the Repository (if any)
			final IResourceDelta repositoryDelta = rootDelta.findMember(repositoryPath);
			// If any resource of the repository has changed
			if (repositoryDelta != null) {

				// We launch the analysis of the delta in a new thread
				Job job = new Job("Notifying Intent clients") {
					protected org.eclipse.core.runtime.IStatus run(
							org.eclipse.core.runtime.IProgressMonitor monitor) {
						isBusy = true;
						analyseWorkspaceDelta(repositoryDelta);
						isBusy = false;
						return Status.OK_STATUS;
					};
				};
				job.setSystem(true);
				job.schedule();

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
	private synchronized void analyseWorkspaceDelta(IResourceDelta repositoryDelta) {
		if (repositoryAdapter != null) {
			// We first create a DeltaVisitor on the repository Path
			final WorkspaceSessionDeltaVisitor visitor = new WorkspaceSessionDeltaVisitor(repositoryAdapter,
					repositoryPath);
			try {
				// We visit the given delta using this visitor
				repositoryDelta.accept(visitor);

				// We get the changed and removed Resources
				Collection<Resource> removedResources = new ArrayList<Resource>();
				Collection<Resource> changedResources = new ArrayList<Resource>();
				Collection<Resource> changedResourcesToReload = new ArrayList<Resource>();

				if (!visitor.getRemovedResources().isEmpty()) {
					removedResources.addAll(visitor.getRemovedResources());
				}

				for (Resource changedResource : visitor.getChangedResources()) {
					Iterator<InternalModificationAdapter> internalModificationAdapters = Iterables.filter(
							changedResource.eAdapters(), InternalModificationAdapter.class).iterator();

					// If the resource is contained in the savedResources list, it means
					// that we should ignore this notification ; however we remove this resource
					// from this list so that we'll treat the next notifications
					if (!savedResources.contains(changedResource)) {
						changedResources.add(changedResource);

						// If the resource is not associated to any InternalModificationAdapter, it means that
						// it has been saved without using the Workspace, and hence that we should reload the
						// resource to handle this external modification
						if (!internalModificationAdapters.hasNext()) {
							changedResourcesToReload.add(changedResource);
						}
					} else {
						savedResources.remove(changedResource);
					}
					if (internalModificationAdapters.hasNext()) {
						changedResource.eAdapters().remove(internalModificationAdapters.next());
					}
				}

				// Finally, we treat each removed or changed resource.
				treatRemovedResources(removedResources);
				treatChangedResources(changedResources, changedResourcesToReload);

			} catch (CoreException e) {
				// TODO define a standard reaction to this exception :
				// - relaunch the session
				// - try to visit the delta again
				// - do nothing
			}
		}
	}

	/**
	 * Treat the resources that has just been changed : reload them and send notifications to register
	 * listeners.
	 * 
	 * @param changedResources
	 *            the list of the recently changed resources
	 * @param changedResourcesToReload
	 *            a subset of changedResources indicating resources that have been externally modified (and
	 *            hence should be reloaded)
	 */
	private void treatChangedResources(Collection<Resource> changedResources,
			final Collection<Resource> changedResourcesToReload) {
		// For each changed resources
		for (final Resource changedResource : changedResources) {
			if (repositoryAdapter != null) {
				repositoryAdapter.execute(new IntentCommand() {

					public void execute() {
						// Reload resource only in case of an external modification
						if (changedResourcesToReload.contains(changedResource)) {
							reloadResource(changedResource);
						}

						// Finally, we notify the listeners of this session
						notifyListeners(changedResource);
					}

				});
			}
		}
	}

	/**
	 * Reloads the given resource.
	 * 
	 * @param changedResource
	 *            the changed resource
	 */
	private void reloadResource(Resource changedResource) {
		// We get the adapters defined on the roots (in order to re-attach them after this
		// resource will be reloaded)
		final Collection<Adapter> oldAdaptersList = new ArrayList<Adapter>();
		for (EObject root : changedResource.getContents()) {
			oldAdaptersList.addAll(root.eAdapters());
		}
		changedResource.unload();
		try {
			changedResource.load(WorkspaceAdapter.getLoadOptions());
		} catch (IOException e) {
			// TODO Handle this I/O Exception
		}
		// We re-attach the eAdapters to the roots
		for (EObject root : changedResource.getContents()) {
			for (Adapter adapter : oldAdaptersList) {
				root.eAdapters().add(adapter);
				if (!savedResources.contains(changedResource)) {
					if (adapter instanceof ElementListAdapter) {
						// We notify each Intent adapter of the changes
						((ElementListAdapter)adapter).notifyChangesOnElement(root);
					}
				}
			}
		}
	}

	/**
	 * Treat the resources that has just been removed : unload them and send notifications to register
	 * listeners.
	 * 
	 * @param removedResources
	 *            the list of the recently removed resources
	 */
	private void treatRemovedResources(Collection<Resource> removedResources) {
		for (Resource removedResource : removedResources) {

			// For each adapter of each roots
			for (EObject root : removedResource.getContents()) {
				for (Adapter adapter : root.eAdapters()) {
					// If the adapter is an Intent elementList adapter
					if (adapter instanceof ElementListAdapter) {
						// we notify it about the deletion of this element
						((ElementListAdapter)adapter).notifyChangesOnElement(null);
					}
				}
			}

			// We unload the removeResource
			removedResource.unload();
			this.repository.getResourceSet().getResources().remove(removedResource);

			// Finally, we notify the listeners of this session
			notifyListeners(removedResource);
		}
	}

	/**
	 * Notifies all the registered session listeners that the given resource has changed.
	 * 
	 * @param resource
	 *            the resource that has changed
	 */
	private void notifyListeners(Resource resource) {

		// Step 1 : notifying type listeners
		for (WorkspaceTypeListener listener : Sets.newLinkedHashSet(this.workspaceSessionListeners)) {
			listener.notifyResourceChanged(resource);
		}

		// Step 2 : notifying element listeners
		for (EObject root : Sets.newLinkedHashSet(resource.getContents())) {
			for (ElementListAdapter elementListAdapter : Sets.newLinkedHashSet(Iterables.filter(
					root.eAdapters(), ElementListAdapter.class))) {
				elementListAdapter.notifyChangesOnElement(root);
			}
		}
	}

	/**
	 * Indicates if the given IResource represents a Intent Repository resource.
	 * 
	 * @param resource
	 *            the IResource to inspect
	 * @return true if the given IResource represents a Intent Repository resource, false otherwise.
	 */
	public boolean isRepositoryResource(IResource resource) {
		boolean isRepositoryResource = false;
		isRepositoryResource = WorkspaceRepository.getWorkspaceResourceExtension().equals(
				resource.getFileExtension());
		isRepositoryResource = isRepositoryResource
				&& repository.isInRepositoryPath(resource.getFullPath().toString());
		return isRepositoryResource;
	}

	/**
	 * Adds the given resource to the saved resource list ; in consequence, the next modification delta
	 * concerning this resource should be ignored.
	 * 
	 * @param savedResource
	 *            the new save resource
	 */
	public void addSavedResource(Resource savedResource) {
		savedResources.add(savedResource);
	}

	/**
	 * Adds the given Listener to the list of sessionListeners to notify if any changes detected.
	 * 
	 * @param typeListener
	 *            the WorkspaceTypeListener to add
	 */
	public void addListener(WorkspaceTypeListener typeListener) {
		this.workspaceSessionListeners.add(typeListener);

	}

	/**
	 * Removes the given Listener from the list of sessionListeners to notify if any changes detected.
	 * 
	 * @param typeListener
	 *            the WorkspaceTypeListener to remove
	 */
	public void removeListener(WorkspaceTypeListener typeListener) {
		this.workspaceSessionListeners.remove(typeListener);
	}

	/**
	 * Indicates if this Workspace Session is currently processing a Workspace Delta (used to avoid concurrent
	 * modifications exceptions, typically " The resource tree is locked for modifications.").
	 * 
	 * @return true if the WorkspaceSession has locked the ResourceTree for processing delta, false otherwise.
	 */
	public boolean isProcessingDelta() {
		return ResourcesPlugin.getWorkspace().isTreeLocked() && !isBusy;
	}

	/**
	 * Closes this Workspace Session.
	 */
	public void close() {
		// Nothing to do as the repository removed this WorkspaceSession from the workspace listeners
		repository = null;
		repositoryAdapter = null;
	}

}
