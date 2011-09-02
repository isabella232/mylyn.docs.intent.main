/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.test.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * Provide utilities to ease workspace manipulation.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class WorkspaceUtils {

	/**
	 * Prevents instantiation.
	 */
	private WorkspaceUtils() {
	}

	/**
	 * Creates a project using the given name.
	 * 
	 * @param projectName
	 *            the project name
	 * @param monitor
	 *            the progress monitor
	 * @return the newly created project or the existing one if present
	 * @throws CoreException
	 *             if there is an issue creating the project
	 */
	public static IProject createProject(final String projectName, IProgressMonitor monitor)
			throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		if (!project.exists()) {
			project.create(monitor);
			project.open(monitor);
		}
		if (!project.isOpen()) {
			project.open(monitor);
		}
		return project;
	}

	/**
	 * Unzip all the projects contained by the zip.
	 * 
	 * @param bundleName
	 *            the bundle name
	 * @param zipLocation
	 *            the zip location inside of the bundle
	 * @param monitor
	 *            the progress monitor
	 * @throws IOException
	 *             if there is an issue copying a file from the zip
	 * @throws CoreException
	 *             if there is an issue creating one of the projects
	 */
	public static void unzipAllProjects(String bundleName, String zipLocation, IProgressMonitor monitor)
			throws IOException, CoreException {
		final URL interpreterZipUrl = FileLocator.find(Platform.getBundle(bundleName), new Path(zipLocation),
				null);
		final ZipInputStream zipFileStream = new ZipInputStream(interpreterZipUrl.openStream());
		ZipEntry zipEntry = zipFileStream.getNextEntry();

		Set<IProject> projects = new HashSet<IProject>();

		while (zipEntry != null) {
			String projectName = zipEntry.getName().split("/")[0];

			IProject project = createProject(projectName, monitor);
			projects.add(project);

			final File file = new File(project.getLocation().toString(), zipEntry.getName().replaceFirst(
					projectName + "/", "")); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$

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

		for (IProject project : projects) {
			project.open(monitor);
			project.refreshLocal(IResource.DEPTH_INFINITE, monitor);

			// Close and re-open the project to force eclipse to re-evaluate
			// any natures that this project may have.
			project.close(monitor);
			project.open(monitor);
		}
	}
}
