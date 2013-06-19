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
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.exporter.main.LatexBatchExport;
import org.eclipse.mylyn.docs.intent.exporter.main.LatexGenDocument;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * An handler allowing to export an intent document as a batch action.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class RunBatchExportAction extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof StructuredSelection) {
			final List<IFile> selectedFiles = Lists.newArrayList(Iterators
					.filter(((StructuredSelection) selection).iterator(),
							IFile.class));
			Job exportJob = new Job("Exporting documentations") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					Set<IContainer> outputFolders = Sets.newLinkedHashSet();
					try {
						for (IFile iFile : selectedFiles) {
							outputFolders.add(iFile.getParent());
							URI modelURI = URI.createPlatformResourceURI(iFile
									.getFullPath().toString(), true);
							new LatexBatchExport(modelURI, new File(iFile
									.getParent().getLocation().toOSString()),
									Collections.EMPTY_LIST)
									.generate(new BasicMonitor());
						}

						for (IContainer parent : outputFolders) {
							if (parent.exists()) {
								parent.refreshLocal(IResource.DEPTH_INFINITE,
										null);
							}
						}
					} catch (CoreException e) {
						IntentUiLogger.logError(e);
					} catch (IOException e) {
						IntentUiLogger.logError(e);
					}

					return Status.OK_STATUS;
				}
			};
			exportJob.schedule();
		}
		return null;
	}
}
