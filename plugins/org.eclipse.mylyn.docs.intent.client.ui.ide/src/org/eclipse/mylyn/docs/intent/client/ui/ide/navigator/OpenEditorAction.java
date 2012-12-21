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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry;

/**
 * Action that opens editor on the selected Project Explorer elements.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class OpenEditorAction extends Action {

	protected TreeViewer viewer;

	private boolean forceNewEditor;

	/**
	 * OpenEditorAction constructor.
	 * 
	 * @param viewer
	 *            the Common viewer
	 */
	public OpenEditorAction(TreeViewer viewer) {
		this.viewer = viewer;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run() {
		// We first test if the current selection is correct
		final IntentGenericElement element = getIndexEntryFromSelection();

		if (element != null && element.eResource() != null) {
			try {
				IntentEditorOpener.openIntentEditor(
						IntentRepositoryManager.INSTANCE.getRepository(findProjectForModelURI(
								element.eResource().getURI()).getName()), element, false, element,
						forceNewEditor);
			} catch (RepositoryConnectionException e) {
				IntentUiLogger.logError(e);
			} catch (CoreException e) {
				IntentUiLogger.logError(e);
			}
		}

	}

	/**
	 * Returns the project containing the model.
	 * 
	 * @param modelURI
	 *            the model uri
	 * @return the project containing the model
	 */
	private IProject findProjectForModelURI(URI modelURI) {
		return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(modelURI.toPlatformString(true)))
				.getProject();
	}

	/**
	 * Returns the indexEntry corresponding to the current Selection (can be null).
	 * 
	 * @return the indexEntry corresponding to the current Selection (can be null)
	 */
	private IntentGenericElement getIndexEntryFromSelection() {
		IntentGenericElement element = null;
		if (viewer.getSelection() instanceof TreeSelection) {
			TreeSelection selectedElements = (TreeSelection)viewer.getSelection();
			Object firstElement = selectedElements.getFirstElement();
			if (firstElement instanceof IntentIndexEntry) {
				element = ((IntentIndexEntry)firstElement).getReferencedElement();
			}
		}
		return element;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.action.Action#getText()
	 */
	@Override
	public String getText() {
		return "Open in a new Intent editor";
	}

	/**
	 * Setter for forceNewEditor.
	 * 
	 * @param forceNewEditor
	 *            if true, will open in a new editor anyway. If false, will open in a new editor or select
	 *            inside of an already opened editor
	 */
	public void setForceNewEditor(boolean forceNewEditor) {
		this.forceNewEditor = forceNewEditor;
	}
}
