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
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * {@link ICompletionProposal} used to fix a Synchronization issue by copying the compiled resource.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class MergeEmptyResourceFix implements ICompletionProposal {

	private IntentAnnotation syncAnnotation;

	/**
	 * Default constructor.
	 * 
	 * @param annotation
	 *            the {@link IntentAnnotation} describing the synchronization issue.
	 */
	public MergeEmptyResourceFix(Annotation annotation) {
		this.syncAnnotation = (IntentAnnotation)annotation;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#apply(org.eclipse.jface.text.IDocument)
	 */
	public void apply(IDocument document) {
		// Step 1 : getting the resources to compare URI
		String workingCopyResourceURI = syncAnnotation.getAdditionalInformations().iterator().next()
				.replace("\"", "");
		String generatedResourceURI = ((String)syncAnnotation.getAdditionalInformations().toArray()[1])
				.replace("\"", "");

		// Step 2 : loading the resources
		ResourceSetImpl rs = new ResourceSetImpl();
		Resource generatedResource = rs.getResource(URI.createURI(generatedResourceURI), true);
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
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getSelection(org.eclipse.jface.text.IDocument)
	 */
	public Point getSelection(IDocument document) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getAdditionalProposalInfo()
	 */
	public String getAdditionalProposalInfo() {
		return "";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		return "Fill the working copy with the document content";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getImage()
	 */
	public Image getImage() {
		return IntentEditorActivator.getDefault().getImage("icon/annotation/sync-warning.gif");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getContextInformation()
	 */
	public IContextInformation getContextInformation() {
		return null;
	}

}
