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

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;

/**
 * An abstract {@link IHyperlink} extended by all Intent hyper links.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public abstract class AbstractIntentHyperLink implements IHyperlink {

	/** Region of this hyperlink. */
	protected final IRegion hyperLinkRegion;

	/** Editor on which this link appears. */
	protected final IntentEditor sourceEditor;

	/**
	 * Instantiates an hyperlink given the editor it appears on and the text region it spans to.
	 * 
	 * @param editor
	 *            Editor on which this hyperlink is shown.
	 * @param region
	 *            Region of the editor where this hyperlink appears.
	 */
	public AbstractIntentHyperLink(IntentEditor editor, IRegion region) {
		this.hyperLinkRegion = region;
		this.sourceEditor = editor;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkRegion()
	 */
	public IRegion getHyperlinkRegion() {
		return hyperLinkRegion;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getTypeLabel()
	 */
	public String getTypeLabel() {
		return getHyperlinkText();
	}

}
