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
package org.eclipse.mylyn.docs.intent.client.ui.editor.hyperlinks;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IRegion;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentDocumentProvider;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReferenceInstruction;

/**
 * This implementation of an hyperlink allows for the opening of Intent elements declarations.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
class OpenIntentDocumentationHyperLink extends AbstractIntentHyperLink {

	/** EObject that will be opened via this hyperlink. */
	private final EObject target;

	/**
	 * Instantiates an hyperlink given the editor it appears on, the text region it spans to, and the link's
	 * target.
	 * 
	 * @param editor
	 *            Editor on which this hyperlink is shown.
	 * @param region
	 *            Region of the editor where this hyperlink appears.
	 * @param element
	 *            Target of the hyperlink.
	 */
	public OpenIntentDocumentationHyperLink(IntentEditor editor, IRegion region, EObject element) {
		super(editor, region);
		this.target = getTarget(element);
	}

	/**
	 * Indicates if this hyperlink can be applied on the given element.
	 * 
	 * @param element
	 *            the element to test
	 * @return true if this hyperlink can be applied on the given element, false otherwise
	 */
	public static boolean canApply(EObject element) {
		return getTarget(element) != null;
	}

	/**
	 * Returns the target of the given element if supported, null instead.
	 * 
	 * @param element
	 *            the element
	 * @return the target
	 */
	private static EObject getTarget(EObject element) {
		EObject target = null;
		if (element instanceof IntentReferenceInstruction) {
			IntentReferenceInstruction ref = (IntentReferenceInstruction)element;
			target = ref.getReferencedElement();
		}
		return target;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkText()
	 */
	public String getHyperlinkText() {
		return "Open declaration for " + target; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#open()
	 */
	public void open() {
		if (target != null) {
			IntentEditorOpener.openIntentEditor(
					((IntentDocumentProvider)sourceEditor.getDocumentProvider()).getRepository(), target,
					false, target, false);
		}
	}
}
