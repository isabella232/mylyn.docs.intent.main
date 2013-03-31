package org.eclipse.mylyn.docs.intent.exporter.client;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.exporter.api.IntentHTMLExporter;

/*******************************************************************************
 * Copyright (c) 2010, 2011 Obeo. All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html Contributors: Obeo - initial API and implementation
 *******************************************************************************/

public class IntentExporterJob extends Job {

	private static final String JOB_NAME = "Creating HTML preview";

	private IntentHTMLExporter htmlExporter;

	private IntentDocument intentDocument;

	private String targetFolderLocation;

	/**
	 * Default constructor.
	 * 
	 * @param intentDocument
	 *            the {@link IntentDocument} to export
	 * @param targetFolderLocation
	 *            the location of the folder used to store the HTML preview
	 */
	public IntentExporterJob(IntentDocument intentDocument, String targetFolderLocation) {
		super(JOB_NAME);
		this.intentDocument = intentDocument;
		this.htmlExporter = new IntentHTMLExporter();
		this.targetFolderLocation = targetFolderLocation;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		htmlExporter.exportIntentDocumentation(intentDocument, targetFolderLocation, "IntentDocumentation",
				false, BasicMonitor.toMonitor(monitor));
		return Status.OK_STATUS;
	}

}
