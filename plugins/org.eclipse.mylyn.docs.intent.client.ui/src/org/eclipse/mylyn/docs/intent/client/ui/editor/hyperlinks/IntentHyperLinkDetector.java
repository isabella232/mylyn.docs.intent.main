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
package org.eclipse.mylyn.docs.intent.client.ui.editor.hyperlinks;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentDocumentProvider;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.serializer.ParsedElementPosition;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * This will allow us to plug the CTRL+click "open declaration" into Intent editors.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentHyperLinkDetector extends AbstractHyperlinkDetector {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.hyperlink.IHyperlinkDetector#detectHyperlinks(org.eclipse.jface.text.ITextViewer,
	 *      org.eclipse.jface.text.IRegion, boolean)
	 */
	public IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region,
			boolean canShowMultipleHyperlinks) {
		ITextEditor textEditor = (ITextEditor)getAdapter(ITextEditor.class);
		if (!textEditor.isDirty()) {
			// NOTE: the hovered element should not be computed using the position manager as it doesn't
			// reflects the latest changes made by the user.
			// WORKAROUND: hyperlinks are desactivated when the editor is dirty
			if (region != null && textEditor instanceof IntentEditor && textEditor.getEditorInput() != null) {
				IntentEditorDocument document = (IntentEditorDocument)((IntentDocumentProvider)textEditor
						.getDocumentProvider()).getDocument(textEditor.getEditorInput());
				EObject element = document.getElementAtOffset(region.getOffset());
				element = getMostSpecificElement(element);
				List<IHyperlink> hyperLinks = Lists.newArrayList();
				if (element != null) {
					// a link can be set to a target
					Region hyperlinkRegion = getHyperlinkRegion(document, element);
					hyperLinks.addAll(doDetectHyperlinks((IntentEditor)textEditor, hyperlinkRegion, element,
							((IntentDocumentProvider)document.getIntentEditor().getDocumentProvider())
									.getListenedElementsHandler().getRepositoryAdapter()));
				}
				if (!hyperLinks.isEmpty()) {
					return hyperLinks.toArray(new IHyperlink[hyperLinks.size()]);
				}
			}
		}
		return null;
	}

	/**
	 * Gets the most specific element from focused element (e.g. the ReferenceValueForStructuralFeature
	 * associated to a StructuralFeatureAffectation).
	 * 
	 * @param element
	 *            the currently focused element
	 * @return the most specific element from focused element
	 */
	private EObject getMostSpecificElement(EObject element) {
		EObject mostSpecificElement = element;
		if (element instanceof StructuralFeatureAffectation) {
			if (((StructuralFeatureAffectation)element).getValues().size() > 0
					&& ((StructuralFeatureAffectation)element).getValues().iterator().next() instanceof ReferenceValue) {
				mostSpecificElement = (ReferenceValue)((StructuralFeatureAffectation)element).getValues()
						.iterator().next();
			}
		}
		return mostSpecificElement;
	}

	/**
	 * From the given editor, region and currently focused element, return the hyperlinks that can be applied.
	 * 
	 * @param textEditor
	 *            Editor on which this hyperlink is shown.
	 * @param hyperlinkRegion
	 *            Region of the editor where this hyperlink appears.
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the Intent document
	 * @param element
	 *            the currently focused instruction
	 * @return the hyperlinks that can be applied
	 */
	private Collection<? extends IHyperlink> doDetectHyperlinks(IntentEditor textEditor,
			IRegion hyperlinkRegion, EObject element, RepositoryAdapter repositoryAdapter) {
		List<IHyperlink> hyperLinks = Lists.newArrayList();
		if (OpenIntentDocumentationHyperLink.canApply(element)) {
			hyperLinks.add(new OpenIntentDocumentationHyperLink((IntentEditor)textEditor, hyperlinkRegion,
					element));
		}
		if (OpenWorkingCopyResourceHyperLink.canApply(repositoryAdapter, element)) {
			hyperLinks.add(new OpenWorkingCopyResourceHyperLink((IntentEditor)textEditor, hyperlinkRegion,
					repositoryAdapter, element));
		}
		return hyperLinks;
	}

	/**
	 * Returns the adapted region for the hyperlink, according to the element type.
	 * 
	 * @param document
	 *            the document
	 * @param element
	 *            the element to link
	 * @return the hyperlink region
	 */
	private Region getHyperlinkRegion(IntentEditorDocument document, EObject element) {
		ParsedElementPosition actualPosition = document.getIntentPosition(element);
		int offset = actualPosition.getOffset();
		int length = actualPosition.getLength();
		if (element instanceof IntentReferenceInstruction) {
			try {
				String text = document.get(offset, length);
				int refStart = text.indexOf("\"") + 1;
				offset += refStart;
				length = text.indexOf("\"", refStart) - refStart;
			} catch (BadLocationException e) {
				// fail silently
			}
		}
		// TODO manage labels
		Region hyperlinkRegion = new Region(Math.max(0, offset), Math.max(0, length));
		return hyperlinkRegion;
	}

}
