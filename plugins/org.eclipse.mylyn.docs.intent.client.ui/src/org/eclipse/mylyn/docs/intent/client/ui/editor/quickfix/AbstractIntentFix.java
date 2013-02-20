/*******************************************************************************
 * Copyright (c) 2012 Obeo.

 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentDocumentProvider;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * {@link ICompletionProposal} used to fix an Intent issue.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractIntentFix implements ICompletionProposal {

	/**
	 * The annotation to fix.
	 */
	protected IntentAnnotation syncAnnotation;

	/**
	 * Default constructor.
	 * 
	 * @param annotation
	 *            the {@link IntentAnnotation} describing the synchronization issue.
	 */
	public AbstractIntentFix(IntentAnnotation annotation) {
		if (!(annotation instanceof IntentAnnotation)) {
			throw new IllegalArgumentException(
					"Cannot apply an Intent fix on the given annotation: should be an IntentAnnotation "
							+ annotation.toString());
		}
		this.syncAnnotation = (IntentAnnotation)annotation;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#apply(org.eclipse.jface.text.IDocument)
	 */
	public final void apply(IDocument document) {
		if (!(document instanceof IntentEditorDocument)) {
			throw new IllegalArgumentException(
					"Cannot apply an Intent fix on the given document: should be an IntentEditorDocument "
							+ document.toString());
		}
		IntentEditorDocument intentEditorDocument = (IntentEditorDocument)document;
		IntentEditor editor = intentEditorDocument.getIntentEditor();
		IntentDocumentProvider documentProvider = (IntentDocumentProvider)editor.getDocumentProvider();
		final RepositoryAdapter repositoryAdapter = documentProvider.getListenedElementsHandler()
				.getRepositoryAdapter();
		applyFix(repositoryAdapter, intentEditorDocument);
	}

	/**
	 * Applies the fix using the given repository adapter.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 * @param document
	 *            the intent editor document
	 */
	protected abstract void applyFix(RepositoryAdapter repositoryAdapter, IntentEditorDocument document);

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getSelection(org.eclipse.jface.text.IDocument)
	 */
	public final Point getSelection(IDocument document) {
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
	public final IContextInformation getContextInformation() {
		return null;
	}

}
