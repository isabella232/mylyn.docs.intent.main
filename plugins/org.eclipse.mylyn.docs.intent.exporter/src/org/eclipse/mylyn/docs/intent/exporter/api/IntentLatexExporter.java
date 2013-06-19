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
package org.eclipse.mylyn.docs.intent.exporter.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.exporter.main.HTMLBootstrapGenDocument;
import org.eclipse.mylyn.docs.intent.exporter.main.LatexGenDocument;
import org.osgi.framework.Bundle;

/**
 * Class allowing to make an Latex export of an {@link IntentDocument} 
 *
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentLatexExporter {

	/**
	 * Creates an HTML export of the Intent document located inside the given intent project.
	 * 
	 * @param targetFolderLocation
	 *            the target folder location
	 * @param intentProject
	 *            the intent project to export
	 * @param documentName
	 *            the name to associated to the exported HTML document
	 * @param progressMonitor
	 *            the progress monitor of this export operation
	 */
	public void exportIntentDocumentation(IntentDocument intentElement, String targetFolderLocation,
			String documentName, Monitor progressMonitor) {
		try {
			// Step 1: get the intent document associated to the given project
			Repository repository = IntentRepositoryManager.INSTANCE.getRepository(EcoreUtil.getURI(
					intentElement).toString());
			final RepositoryAdapter repositoryAdapter = repository.createRepositoryAdapter();
			repositoryAdapter.openSaveContext();

			// Step 2: determine target folder
			File targetFolder = new File(targetFolderLocation);
			if (!targetFolder.exists()) {
				targetFolder.mkdirs();
			}

			// Step 3: make sure that the target folder contains all required resources (css,
			// javascript...)
			copyRequiredResourcesToGenerationFolder(targetFolder);

			// Step 4: launch generation
			LatexGenDocument generator = new LatexGenDocument(intentElement, targetFolder,
					new ArrayList<Object>());
			generator.doGenerate(progressMonitor, documentName, repositoryAdapter);

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
				final File file = new File(targetFolder.getAbsolutePath().toString(), zipEntry.getName());

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
