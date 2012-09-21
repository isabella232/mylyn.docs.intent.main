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
package org.eclipse.mylyn.docs.intent.client.ui.ide.navigator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentSubSectionContainer;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.views.markers.MarkerItem;

/**
 * An handler allowing to show a problem from problem view inside an Intent editor.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ShowProblemInEditorHandler extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection)currentSelection).getFirstElement();
			try {
				if (firstElement instanceof MarkerItem
						&& "Intent".equals(((MarkerItem)firstElement)
								.getAttributeValue(IMarker.SOURCE_ID, ""))) {

					doOpenIntentEditorOnProblem((MarkerItem)firstElement);
				}
			} catch (RepositoryConnectionException e) {
				IntentUiLogger.logError(e);
			} catch (CoreException e) {
				IntentUiLogger.logError(e);
			}
		}
		return null;
	}

	/**
	 * Opens an Intent Editor on the problem described by the given {@link MarkerItem}.
	 * 
	 * @param markerItem
	 *            the marker Item containing the problem to open
	 * @throws RepositoryConnectionException
	 *             if cannot access to the repository corresponding to this problem
	 * @throws CoreException
	 *             if marker is invalid
	 */
	private void doOpenIntentEditorOnProblem(MarkerItem markerItem) throws RepositoryConnectionException,
			CoreException {
		String location = markerItem.getLocation();
		if (location.startsWith("platform:/resource/")) {
			location = location.replaceFirst("platform:/resource/", "");
			location = location.substring(0, location.indexOf("/"));
			Repository repository = IntentRepositoryManager.INSTANCE.getRepository(location);
			RepositoryAdapter adapter = repository.createRepositoryAdapter();
			adapter.openReadOnlyContext();
			EObject elementToOpen = adapter.getResource(IntentLocations.INTENT_INDEX).getResourceSet()
					.getEObject(URI.createURI(markerItem.getLocation()), true);
			if (elementToOpen instanceof CompilationStatus) {
				elementToOpen = ((CompilationStatus)elementToOpen).getTarget();
			}
			EObject elementToSelect = elementToOpen;
			while (elementToOpen != null && !(elementToOpen instanceof IntentSubSectionContainer)
					&& !(elementToOpen instanceof IntentDocument)) {
				elementToOpen = elementToOpen.eContainer();
			}
			if (elementToOpen != null) {
				IntentEditorOpener.openIntentEditor(repository, elementToOpen, false, elementToSelect, false);
			}
			adapter.closeContext();
		}
	}
}
