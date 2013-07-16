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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorInput;
import org.eclipse.mylyn.docs.intent.client.ui.ide.builder.IntentNature;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.collab.common.query.IndexQuery;
import org.eclipse.mylyn.docs.intent.collab.common.query.IntentDocumentQuery;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.ide.repository.WorkspaceConfig;
import org.eclipse.mylyn.docs.intent.collab.ide.repository.WorkspaceRepository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ILinkHelper;

/**
 * An {@link ILinkHelper} allowing to link Intent Editors with the Intent Document structure displayed in the
 * Project explorer.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentLinkHelper implements ILinkHelper {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.navigator.ILinkHelper#findSelection(org.eclipse.ui.IEditorInput)
	 */
	public IStructuredSelection findSelection(IEditorInput anInput) {
		ArrayList<Object> elementsToSelect = new ArrayList<Object>();

		// To avoid expanding the whole document hierarchy, always return the IndexEntry referencing the
		// document
		RepositoryAdapter repositoryAdapter = ((IntentEditorInput)anInput).getRepositoryAdapter();
		URI editorURI = ((IntentEditorInput)anInput).getURI();
		Object[] index = new IndexQuery(repositoryAdapter).getOrCreateIntentIndex().getEntries().toArray();
		for (int i = 0; i < index.length; i++) {
			elementsToSelect.addAll(getIndexEntryToSelectionFromEditorURI((IntentIndexEntry)index[i],
					editorURI));
		}

		// Also add the Intent project to the selection
		if (repositoryAdapter.getRepository() instanceof WorkspaceRepository) {
			WorkspaceConfig workspaceConfig = ((WorkspaceRepository)repositoryAdapter.getRepository())
					.getWorkspaceConfig();
			String projectName = workspaceConfig.getRepositoryAbsolutePath()
					.replace(workspaceConfig.getRepositoryRelativePath(), "").replaceFirst("/", "");
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			if (project.exists()) {
				elementsToSelect.add(project);
			}
		}
		return new StructuredSelection(elementsToSelect.toArray(new Object[elementsToSelect.size()]));
	}

	/**
	 * Returns the {@link IntentIndexEntry}s corresponding to the given editor URI.
	 * 
	 * @param indexEntry
	 *            the scope in which searching for the entry
	 * @param editorURI
	 *            the searched editor URI
	 * @return the {@link IntentIndexEntry}s corresponding to the given editor URI
	 */
	private Collection<? extends IntentIndexEntry> getIndexEntryToSelectionFromEditorURI(
			IntentIndexEntry indexEntry, URI editorURI) {
		ArrayList<IntentIndexEntry> elementsToSelect = new ArrayList<IntentIndexEntry>();
		if (indexEntry.getReferencedElement() != null) {
			if (editorURI.equals(EcoreUtil.getURI(indexEntry.getReferencedElement()))) {
				elementsToSelect.add(indexEntry);
			} else {
				for (IntentIndexEntry subEntry : indexEntry.getSubEntries()) {
					elementsToSelect.addAll(getIndexEntryToSelectionFromEditorURI(subEntry, editorURI));
				}
			}
		}
		return elementsToSelect;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.navigator.ILinkHelper#activateEditor(org.eclipse.ui.IWorkbenchPage,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void activateEditor(IWorkbenchPage aPage, IStructuredSelection aSelection) {
		Object selectedElement = aSelection.getFirstElement();
		IntentEditor alreadyOpenedEditor = null;

		if (selectedElement instanceof IntentIndexEntry) {
			alreadyOpenedEditor = IntentEditorOpener
					.getAlreadyOpenedEditor(((IntentIndexEntry)selectedElement).getReferencedElement());
		}
		// This case can never happen if jdt is installed, as LinkEditorAction.activateEditorJob only takes
		// the first linkhelper available (if installed, the JDT for an IProject)
		else if (selectedElement instanceof IProject) {
			try {
				if (((IProject)selectedElement).hasNature(IntentNature.NATURE_ID)) {

					RepositoryAdapter adapter = IntentRepositoryManager.INSTANCE.getRepository(
							((IProject)selectedElement).getName()).createRepositoryAdapter();
					alreadyOpenedEditor = IntentEditorOpener.getAlreadyOpenedEditor(new IntentDocumentQuery(
							adapter).getOrCreateIntentDocument());

				}
			} catch (RepositoryConnectionException e) {
				// Silent catch
			} catch (CoreException e) {
				// Silent catch
			}
		}

		if (alreadyOpenedEditor != null) {
			aPage.bringToTop(alreadyOpenedEditor);
		}
	}
}
