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
package org.eclipse.mylyn.docs.intent.exporter.ui.popup.actions;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.common.query.IntentDocumentQuery;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * An handler allowing to export an intent document / Intent Project as an HTML Document.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ExportIntentDocumentationAction extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof StructuredSelection) {
			IProject intentProject = null;
			IntentStructuredElement intentElement = null;
			try {
				if (((StructuredSelection)selection).getFirstElement() instanceof IProject) {
					intentProject = (IProject)((StructuredSelection)selection).getFirstElement();
					intentElement = new IntentDocumentQuery(IntentRepositoryManager.INSTANCE.getRepository(
							intentProject.getName()).createRepositoryAdapter()).getOrCreateIntentDocument();
				} else {
					intentElement = null;

					if (((StructuredSelection)selection).getFirstElement() instanceof IntentStructuredElement) {
						intentElement = (IntentStructuredElement)((StructuredSelection)selection)
								.getFirstElement();
					}
					if (((StructuredSelection)selection).getFirstElement() instanceof IntentIndexEntry) {
						intentElement = (IntentStructuredElement)((IntentIndexEntry)((StructuredSelection)selection)
								.getFirstElement()).getReferencedElement();
					}
					if (intentElement != null) {

						String projectName = IntentRepositoryManager.INSTANCE.getRepository(
								EcoreUtil.getURI((EObject)((StructuredSelection)selection).getFirstElement())
										.toString()).getIdentifier();
						intentProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
					}
				}
				if (intentProject != null) {
					// Step 1 : open the export dialog
					final ExportOptionsDialog exportOptionsDialog = new ExportOptionsDialog(Display
							.getCurrent().getActiveShell(),
							new File(intentProject.getLocationURI()).getAbsolutePath() + "/generated",
							intentElement);

					if (Window.OK == exportOptionsDialog.open()) {
						// Step 2: realize export
						Job exportJob = new ExportIntentDocumentationJob(intentElement, intentProject,
								exportOptionsDialog.getTargetFolderLocation(),
								exportOptionsDialog.getExportedIntentDocumentName(),
								exportOptionsDialog.shouldShowTableOfContent());
						exportJob.schedule();
					}
				}
			} catch (RepositoryConnectionException e) {
				IntentUiLogger.logError(e);
			} catch (CoreException e) {
				IntentUiLogger.logError(e);
			}
		}
		return null;
	}
}
