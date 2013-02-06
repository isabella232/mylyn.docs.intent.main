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
package org.eclipse.mylyn.docs.intent.client.ui.actions;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Iterator;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.hyperlinks.IntentHyperLinkDetector;
import org.eclipse.mylyn.docs.intent.client.ui.editor.hyperlinks.OpenWorkingCopyResourceHyperLink;

/**
 * Computes the current {@link IHyperlink}s of the active {@link IntentEditor} and applies them if any found.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class HyperlinkAction extends QuickOutlineAction {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		IntentEditor currentEditor = getCurrentEditor();
		ISelection selection = currentEditor.getSelectionProvider().getSelection();
		if (selection instanceof TextSelection) {
			int offset = ((TextSelection)selection).getOffset();
			Region region = new Region(offset, 1);
			IntentHyperLinkDetector intentHyperlinkDetector = new IntentHyperLinkDetector();
			intentHyperlinkDetector.setContext(currentEditor);
			IHyperlink[] hyperlinks = intentHyperlinkDetector.detectHyperlinks(
					currentEditor.getProjectionViewer(), region, true);

			if (hyperlinks != null) {
				Iterator<OpenWorkingCopyResourceHyperLink> detectedHyperlinks = Iterables.filter(
						Lists.newArrayList(hyperlinks), OpenWorkingCopyResourceHyperLink.class).iterator();
				if (detectedHyperlinks.hasNext()) {
					detectedHyperlinks.next().open();
				}
			}
		}
	}
}
