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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.common.query.IntentDocumentQuery;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.exporter.main.HTMLBootstrapGenDocument;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.Bundle;

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
			if (((StructuredSelection)selection).getFirstElement() instanceof IProject) {
				intentProject = (IProject)((StructuredSelection)selection).getFirstElement();
			} else if (((StructuredSelection)selection).getFirstElement() instanceof IntentDocument) {
				// TODO
			}
			// Step 1 : open the export dialog
			ExportOptionsDialog exportOptionsDialog = new ExportOptionsDialog(Display.getCurrent()
					.getActiveShell(), new File(((IProject)intentProject).getLocationURI()).getAbsolutePath()
					+ "/html");

			if (Window.OK == exportOptionsDialog.open()) {
				// Step 2: realize export
				exportIntentDocumentation((IProject)intentProject,
						exportOptionsDialog.getTargetFolderLocation(),
						exportOptionsDialog.getExportedIntentDocumentName(), new BasicMonitor());
			}
		}
		return null;
	}

	/**
	 * Creates an HTML export of the Intent document located inside the given intent project.
	 * 
	 * @param targetFolderLocation
	 *            the target folder location
	 * @param intentProject
	 *            the intent project to export
	 * @param projectName
	 * @param progressMonitor
	 *            the progress monitor of this export operation
	 */
	private void exportIntentDocumentation(IProject intentProject, String targetFolderLocation,
			String projectName, Monitor progressMonitor) {
		try {
			// Step 1: get the intent document associated to the given project
			Repository repository = IntentRepositoryManager.INSTANCE.getRepository(intentProject.getName());
			final RepositoryAdapter repositoryAdapter = repository.createRepositoryAdapter();
			repositoryAdapter.openSaveContext();
			IntentDocument intentDocument = new IntentDocumentQuery(repositoryAdapter)
					.getOrCreateIntentDocument();

			// Step 2: determine target folder
			File targetFolder = new File(targetFolderLocation);
			if (!targetFolder.exists()) {
				targetFolder.mkdirs();
			}

			// Step 3: make sure that the target folder contains all required resources (css,
			// javascript...)
			copyRequiredResourcesToGenerationFolder(targetFolder);

			// Step 4: launch generation
			HTMLBootstrapGenDocument generator = new HTMLBootstrapGenDocument(intentDocument, targetFolder,
					new ArrayList<Object>());
			generator.doGenerate(progressMonitor, projectName, repositoryAdapter);

			// Step 5: if target folder is in workspace, refresh the folder
			String workspaceRelativePath = targetFolder
					.getAbsolutePath()
					.toString()
					.replace(
							new File(ResourcesPlugin.getWorkspace().getRoot().getLocationURI())
									.getAbsolutePath().toString(), "").substring(1);
			IFolder targetFolderInWorkspace = ResourcesPlugin.getWorkspace().getRoot()
					.getFolder(new Path(workspaceRelativePath));
			if (targetFolderInWorkspace.exists()) {
				targetFolderInWorkspace.refreshLocal(IResource.DEPTH_INFINITE, null);
			} else {
				intentProject.refreshLocal(IResource.DEPTH_INFINITE, null);
			}
			repositoryAdapter.closeContext();
		} catch (IOException e) {
			IntentUiLogger.logError(e);
		} catch (CoreException e) {
			IntentUiLogger.logError(e);
		} catch (RepositoryConnectionException e) {
			IntentUiLogger.logError(e);
		} catch (ReadOnlyException e) {
			IntentUiLogger.logError(e);
		}

	}

	/**
	 * Copies in the given target folder all the files (css, javascript...) required by this HTML export
	 * 
	 * @param targetFolder
	 * @throws IOException
	 */
	private void copyRequiredResourcesToGenerationFolder(File targetFolder) throws IOException {
		// Step 1: open input stream on source folder
		Bundle bundle = Platform.getBundle("org.eclipse.mylyn.docs.intent.exporter");
		Path path = new Path("resources/html_bootstrap/html_bootstrap.zip");
		ZipInputStream zipFileStream = new ZipInputStream(FileLocator.find(bundle, path, null).openStream());

		ZipEntry zipEntry = zipFileStream.getNextEntry();

		try {

			while (zipEntry != null) {
				final File file = new File(targetFolder.getAbsolutePath().toString(), zipEntry.getName()); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$

				if (!zipEntry.isDirectory()) {

					/*
					 * Copy files (and make sure parent directory exist)
					 */
					final File parentFile = file.getParentFile();
					if (null != parentFile && !parentFile.exists()) {
						parentFile.mkdirs();
					}

					OutputStream os = null;

					try {
						os = new FileOutputStream(file);

						final int bufferSize = 102400;
						final byte[] buffer = new byte[bufferSize];
						while (true) {
							final int len = zipFileStream.read(buffer);
							if (zipFileStream.available() == 0) {
								break;
							}
							os.write(buffer, 0, len);
						}
					} finally {
						if (null != os) {
							os.close();
						}
					}
				}
				zipFileStream.closeEntry();
				zipEntry = zipFileStream.getNextEntry();
			}
		} finally {
			zipFileStream.close();
		}
	}

}
