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
package org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;

/**
 * Proposal used to fix a Synchronization issue by copying the compiled resource.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class MergeEmptyResourceFix extends AbstractIntentFix {

	/**
	 * Default constructor.
	 * 
	 * @param annotation
	 *            the annotation describing the synchronization issue.
	 */
	public MergeEmptyResourceFix(IntentAnnotation annotation) {
		super(annotation);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.AbstractIntentFix#applyFix(org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter,
	 *      org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument)
	 */
	@Override
	protected void applyFix(RepositoryAdapter repositoryAdapter, IntentEditorDocument document) {
		// Step 1 : getting the resources to compare URI
		SynchronizerCompilationStatus status = (SynchronizerCompilationStatus)syncAnnotation
				.getCompilationStatus();
		String workingCopyResourceURI = status.getWorkingCopyResourceURI().replace("\"", "");
		String generatedResourceURI = status.getCompiledResourceURI().replace("\"", "");

		// Step 2 : loading the resources
		Resource generatedResource = repositoryAdapter.getResource(generatedResourceURI);

		ResourceSet rs = new ResourceSetImpl();
		Resource workingCopyResource = rs.getResource(URI.createURI(workingCopyResourceURI), true);

		workingCopyResource.getContents().addAll(EcoreUtil.copyAll(generatedResource.getContents()));

		try {
			workingCopyResource.save(null);
		} catch (IOException e) {
			IntentUiLogger.logError(e);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		return "Fill the working copy with the document content";
	}

}
