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
package org.eclipse.mylyn.docs.intent.client.ui.editor.completion;

import com.google.common.collect.Lists;

import java.util.ArrayList;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.IntentPartitionScanner;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;

/**
 * Computes the completion proposal for pure documentation zones.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class MarkupCompletionProcessor extends AbstractIntentCompletionProcessor {

	/**
	 * Default constructor.
	 * 
	 * @param repositoryAdapter
	 */
	public MarkupCompletionProcessor(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.completion.AbstractIntentCompletionProcessor#computeCompletionProposals()
	 */
	@Override
	protected ICompletionProposal[] computeCompletionProposals() {
		ArrayList<ICompletionProposal> proposals = Lists.newArrayList();
		proposals.add(createFontDecorationProposal("Emphasis", "_"));
		proposals.add(createFontDecorationProposal("Strong", "*"));
		proposals.add(createFontDecorationProposal("Image", "!", "imagePath", true));
		proposals.add(createFontDecorationProposal("List (buletted)", "*"));
		proposals.add(createFontDecorationProposal("List (numeric)", "#"));
		proposals.add(createFontDecorationProposal("Code", "@"));
		proposals.add(createFontDecorationProposal("Italic", "__"));
		proposals.add(createFontDecorationProposal("Bold", "**"));
		proposals.add(createFontDecorationProposal("List (level 2, buletted)", "*"));
		proposals.add(createFontDecorationProposal("List (level 2, numeric)", "#"));
		proposals.add(createFontDecorationProposal("Citation", "??"));
		proposals.add(createFontDecorationProposal("Deleted", "-"));
		proposals.add(createFontDecorationProposal("Inserted", "+"));
		proposals.add(createFontDecorationProposal("Superscript", "^"));
		proposals.add(createFontDecorationProposal("Span", "%"));

		// Todo : subscript, link, footnote & table
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.completion.AbstractIntentCompletionProcessor#getContextType()
	 */
	@Override
	public String getContextType() {
		return IntentPartitionScanner.INTENT_DESCRIPTIONUNIT;
	}

	/**
	 * Creates a font decoration proposal according to the given font decoration name (e.g. 'Bold') and the
	 * given font decoration Syntax (e.g. '*').
	 * 
	 * @param fontDecorationName
	 *            the name of the font decoration (e.g. 'Bold')
	 * @param fontDecorationSyntax
	 *            the syntax of the font decoration (e.g. '*')
	 * @return a font decoration proposal according to the given font decoration name (e.g. 'Bold') and the
	 *         given font decoration Syntax (e.g. '*')
	 */
	private ICompletionProposal createFontDecorationProposal(String fontDecorationName,
			String fontDecorationSyntax) {
		return createFontDecorationProposal(fontDecorationName, fontDecorationSyntax, "", true);
	}

	/**
	 * Creates a font decoration proposal according to the given font decoration name (e.g. 'Bold') and the
	 * given font decoration Syntax (e.g. '*').
	 * 
	 * @param fontDecorationName
	 *            the name of the font decoration (e.g. 'Bold')
	 * @param fontDecorationSyntax
	 *            the syntax of the font decoration (e.g. '*')
	 * @param variableName
	 *            the name of the variable to be display as ${variable} (can be empty)
	 * @param biDirectionalSyntax
	 *            indicates if the fontDecorationSyntax should be displayed before and after text (e.g.
	 *            '*bold*') or just before (e.g. '# bullet')
	 * @return a font decoration proposal according to the given font decoration name (e.g. 'Bold') and the
	 *         given font decoration Syntax (e.g. '*')
	 */
	private ICompletionProposal createFontDecorationProposal(String fontDecorationName,
			String fontDecorationSyntax, String variableName, boolean biDirectionalSyntax) {
		String templateName = "";
		String templatePattern = "";
		if (biDirectionalSyntax) {
			templateName = fontDecorationSyntax + fontDecorationName.toLowerCase() + fontDecorationSyntax;
			templatePattern = fontDecorationSyntax + "${ " + variableName + "}" + fontDecorationSyntax;
		} else {
			templateName = fontDecorationSyntax + " " + fontDecorationName.toLowerCase();
			templatePattern = fontDecorationSyntax + " ${ " + variableName + "}";
		}
		return createTemplateProposal(templateName, fontDecorationName, templatePattern, null);
	}

}
