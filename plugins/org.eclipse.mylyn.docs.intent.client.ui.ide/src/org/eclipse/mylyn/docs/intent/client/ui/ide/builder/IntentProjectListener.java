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
package org.eclipse.mylyn.docs.intent.client.ui.ide.builder;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorInput;
import org.eclipse.mylyn.docs.intent.client.ui.ide.projectmanager.IntentProjectManager;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * A {@link IResourceChangeListener} that reacts to the creation or opening of Intent projects by creating
 * Repository and launching clients.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentProjectListener implements IResourceChangeListener {

	private Map<String, IntentProjectManager> projectManagers = new HashMap<String, IntentProjectManager>();

	/**
	 * Default constructor.
	 */
	public IntentProjectListener() {

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE
				|| event.getType() == IResourceChangeEvent.PRE_DELETE) {
			IResource resource = event.getResource();
			try {
				// TODO check if there is a repository associated to this project, even if not accessible
				if (resource instanceof IProject && resource.isAccessible()
						&& ((IProject)resource).hasNature(IntentNature.NATURE_ID)) {
					handleClosedProject((IProject)resource);
				}
			} catch (CoreException e) {
				IntentUiLogger.logError(e);
			}
		} else {
			// We want to be notified AFTER any changed that occurred
			if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
				final IResourceDelta rootDelta = event.getDelta();
				// If any resource of the repository has changed
				if (rootDelta != null) {
					// We launch the analysis of the delta in a new thread
					analyseWorkspaceDelta(rootDelta);
				}
			}
		}
	}

	/**
	 * Analyzes the given {@link IResourceDelta}.
	 * 
	 * @param repositoryDelta
	 *            the {@link IResourceDelta} to analyze
	 */
	protected void analyseWorkspaceDelta(IResourceDelta repositoryDelta) {
		try {
			// Step 1 : We visit the given delta using a IntentBuilderDeltaVisitor visitor
			final IntentBuilderDeltaVisitor visitor = new IntentBuilderDeltaVisitor();
			repositoryDelta.accept(visitor);

			// Step 2 : if any project has been opened, we handle this creation
			for (IProject project : visitor.getOpenedProjects()) {
				handleOpenedProject(project);
			}

			// Step 3 : if any project has been closed, we handle this creation
			for (IProject project : visitor.getClosedProjects()) {
				handleClosedProject(project);
			}

		} catch (CoreException e) {
			IntentUiLogger.logError(e);
		}

	}

	/**
	 * Handles the creation or opening of Intent projects by launching all clients.
	 * 
	 * @param project
	 *            the created or opened project to handle
	 */
	public void handleOpenedProject(IProject project) {
		// Step 1: determine if the opened project is a valid Intent project
		try {
			if (project.isAccessible() && project.hasNature(IntentNature.NATURE_ID)) {
				// Step 2: determine if it is already handled by this project listener
				if (projectManagers.get(project.getName()) == null) {
					// Step 3: if not, create an Intent project manager for this project
					IntentProjectManager projectManager = getIntentProjectManager(project);
					try {
						IntentLogger.getInstance().log(LogType.LIFECYCLE,
								"[IntentProjectListener] Handling project " + project.getName());
						projectManager.connect();
						projectManagers.put(project.getName(), projectManager);
					} catch (RepositoryConnectionException e) {
						IntentUiLogger.logError(e);
					}
				}
			}
		} catch (CoreException e) {
			IntentUiLogger.logError(e);
		}
	}

	/**
	 * Handles the deletion or closing of Intent projects by stopping all clients and closing repository.
	 * 
	 * @param project
	 *            the deleted or closed project to handle
	 */
	public void handleClosedProject(IProject project) {
		IntentProjectManager projectManager = getIntentProjectManager(project);
		// Closing all intent editors opened of this project
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (activeWorkbenchWindow == null && PlatformUI.getWorkbench().getWorkbenchWindowCount() == 1) {
			activeWorkbenchWindow = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
		}
		if (activeWorkbenchWindow != null) {
			final IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
			if (activePage != null) {
				final Collection<IEditorReference> activeEditorsToClose = Sets.newLinkedHashSet();
				for (IEditorReference activeEditor : activePage.getEditorReferences()) {
					try {
						if (activeEditor.getEditorInput() instanceof IntentEditorInput
								&& ((IntentEditorInput)activeEditor.getEditorInput()).getRepository() != null
								&& project.getName().equals(
										((IntentEditorInput)activeEditor.getEditorInput()).getRepository()
												.getIdentifier())) {
							activeEditorsToClose.add(activeEditor);
						}
					} catch (PartInitException e) {
						// Silent catch
					}
				}
				Display.getDefault().asyncExec(new Runnable() {

					public void run() {
						activePage.closeEditors(activeEditorsToClose
								.toArray(new IEditorReference[activeEditorsToClose.size()]), false);
					}

				});

			}
		}

		// Closing project manager
		if (projectManager != null) { // should not happen
			try {
				projectManager.disconnect();
				projectManagers.remove(project.getName());
			} catch (RepositoryConnectionException e) {
				IntentUiLogger.logError(e);
			}
		}
	}

	/**
	 * Returns the project manager associated to the given project.
	 * 
	 * @param project
	 *            the intent project
	 * @return the project manager associated to the given project
	 */
	private IntentProjectManager getIntentProjectManager(IProject project) {
		IntentProjectManager projectManager = projectManagers.get(project.getName());
		if (projectManager == null) {
			projectManager = new IntentProjectManager(project);
		}
		return projectManager;
	}

}
