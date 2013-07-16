/*******************************************************************************
 * Copyright (c) 2013 Obeo.
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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.exporter.api.IntentHTMLExporter;

/**
 * A {@link Job} allowing to export an {@link IntentStructuredElement}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ExportIntentDocumentationJob extends Job {

	/**
	 * The job name.
	 */
	private static final String JOB_NAME = "Exporting documentation as HTML";

	/**
	 * The {@link IntentStructuredElement} to export.
	 */
	private IntentStructuredElement intentElementToExport;

	/**
	 * The corresponding {@link IProject}.
	 */
	private IProject project;

	/**
	 * Path of the folder in which export will be performed.
	 */
	private String targetFolderLocation;

	/**
	 * Name to associate to the main exported file.
	 */
	private String exportedIntentDocumentName;

	/**
	 * Indicates whether export should show table of contents by default or not.
	 */
	private boolean shouldShowTableOfContent;

	/**
	 * Default constructor.
	 * 
	 * @param intentElementToExport
	 *            the {@link IntentStructuredElement} to export
	 * @param project
	 *            the corresponding {@link IProject}
	 * @param targetFolderLocation
	 *            path of the folder in which export will be performed
	 * @param exportedIntentDocumentName
	 *            name to associate to the main exported file
	 * @param shouldShowtableOfContent
	 *            indicates whether export should show table of contents by default or not
	 */
	public ExportIntentDocumentationJob(IntentStructuredElement intentElementToExport, IProject project,
			String targetFolderLocation, String exportedIntentDocumentName, boolean shouldShowtableOfContent) {
		super(JOB_NAME);
		this.intentElementToExport = intentElementToExport;
		this.project = project;
		this.targetFolderLocation = targetFolderLocation;
		this.exportedIntentDocumentName = exportedIntentDocumentName;
		this.shouldShowTableOfContent = shouldShowtableOfContent;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		new IntentHTMLExporter().exportIntentDocumentation(intentElementToExport, targetFolderLocation,
				exportedIntentDocumentName, shouldShowTableOfContent, BasicMonitor.toMonitor(monitor));

		// Step 3: if target folder is in workspace, refresh the folder
		File targetFolder = new File(targetFolderLocation);
		String projectRelativePath = targetFolder.getAbsolutePath().toString()
				.replace(new File(project.getLocationURI()).getAbsolutePath().toString(), "").substring(1);
		IFolder targetFolderInWorkspace = project.getFolder(new Path(projectRelativePath));
		try {
			if (targetFolderInWorkspace.exists()) {
				targetFolderInWorkspace.refreshLocal(IResource.DEPTH_INFINITE, null);
			} else {
				project.refreshLocal(IResource.DEPTH_INFINITE, null);
			}
		} catch (CoreException e) {
			IntentUiLogger.logError(e);
		}
		return Status.OK_STATUS;
	}

}
