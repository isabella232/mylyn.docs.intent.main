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
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;

/**
 * Proposal used to fix a Synchronization issue by opening the compare Editor.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class CreateResourceFix extends AbstractIntentFix {

	/**
	 * Default constructor.
	 * 
	 * @param annotation
	 *            the annotation describing the synchronization issue.
	 */
	public CreateResourceFix(Annotation annotation) {
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
		String workingCopyResourceURI = getWorkingCopyResourceURI();
		String compiledResourceURI = ((SynchronizerCompilationStatus)syncAnnotation.getCompilationStatus())
				.getCompiledResourceURI();
		if (compiledResourceURI != null) {
			compiledResourceURI = compiledResourceURI.replace("\"", "");

			// Step 2 : loading the resources
			Resource generatedResource = repositoryAdapter.getResource(compiledResourceURI);

			ResourceSetImpl rs = new ResourceSetImpl();
			Resource workingCopyResource = rs.createResource(URI.createURI(workingCopyResourceURI));

			// Step 3 : Copy the content
			workingCopyResource.getContents().addAll(EcoreUtil.copyAll(generatedResource.getContents()));

			try {
				workingCopyResource.save(null);
			} catch (IOException e) {
				IntentUiLogger.logError(e);
			}
		} else {
			IntentLogger.getInstance().log(LogType.ERROR,
					"Invalid URI : cannot create resource at " + workingCopyResourceURI);
		}
	}

	private String getWorkingCopyResourceURI() {
		return ((SynchronizerCompilationStatus)syncAnnotation.getCompilationStatus())
				.getWorkingCopyResourceURI().replace("\"", "");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		return "Create the " + getWorkingCopyResourceURI()
				+ " resource, initialized with working copy content";
	}

}
