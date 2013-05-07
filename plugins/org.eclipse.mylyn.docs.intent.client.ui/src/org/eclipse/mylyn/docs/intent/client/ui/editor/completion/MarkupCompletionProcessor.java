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
import java.util.Collection;

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

		// Step 1: get last relevant char
		String documentText = document.get().substring(0, offset);
		int i = documentText.length() - 1;
		ArrayList<Character> lastRelevantChar = new ArrayList<Character>();
		while (i > 2) {
			Character relevantChar = documentText.charAt(i);
			i--;
			if (relevantChar == '\n') {
				break;
			}
			if (isRelevantChar(relevantChar)) {
				if (lastRelevantChar.contains(relevantChar)) {
					lastRelevantChar.remove(relevantChar);
				} else {
					lastRelevantChar.add(relevantChar);
				}
			}
		}

		// Step 2: add matching proposals
		char relevantChar = '\n';
		if (!lastRelevantChar.isEmpty()) {
			relevantChar = lastRelevantChar.iterator().next();
		}
		addProposal(proposals, relevantChar, "Emphasis", "_");
		addProposal(proposals, relevantChar, "Strong", "*");
		addProposal(proposals, relevantChar, "Image", "!", "imagePath", true);
		addProposal(proposals, relevantChar, "List (buletted)", "*", "", false);
		addProposal(proposals, relevantChar, "List (numeric)", "#", "", false);
		addProposal(proposals, relevantChar, "Code", "@");
		addProposal(proposals, relevantChar, "Italic", "__ ");
		addProposal(proposals, relevantChar, "Bold", "** ");
		addProposal(proposals, relevantChar, "List (level 2, buletted)", "** ", "", false);
		addProposal(proposals, relevantChar, "List (level 2, numeric)", "## ", "", false);
		addProposal(proposals, relevantChar, "Citation", "??");
		addProposal(proposals, relevantChar, "Deleted", "-");
		addProposal(proposals, relevantChar, "Inserted", "+");
		addProposal(proposals, relevantChar, "Superscript", "^");
		addProposal(proposals, relevantChar, "Span", "%");

		// Todo : subscript, link, footnote & table
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	/**
	 * Indicates if the given char indicates the beginning of a completion proposal.
	 * 
	 * @param currentChar
	 *            the current char
	 * @return true if the given char indicates the beginning of a completion proposal, false otherwise
	 */
	private boolean isRelevantChar(char currentChar) {
		boolean isRelevantChar = currentChar == '_' || currentChar == '@' || currentChar == '*'
				|| currentChar == '?';
		isRelevantChar = isRelevantChar || currentChar == '%' || currentChar == '+' || currentChar == '^';
		isRelevantChar = isRelevantChar || currentChar == '-' || currentChar == '#' || currentChar == '!';
		return isRelevantChar;
	}

	/**
	 * Creates a font decoration proposal according to the given font decoration name (e.g. 'Bold') and the
	 * given font decoration Syntax (e.g. '*'), only if the given relevant char is compatible with this
	 * proposal.
	 * 
	 * @param proposals
	 *            the current proposals
	 * @param relevantChar
	 *            the relevant char
	 * @param fontDecorationName
	 *            the name of the font decoration (e.g. 'Bold')
	 * @param fontDecorationSyntax
	 *            the syntax of the font decoration (e.g. '*')
	 * @param variableName
	 *            the name of the variable to be display as ${variable} (can be empty)
	 * @return a font decoration proposal according to the given font decoration name (e.g. 'Bold') and the
	 *         given font decoration Syntax (e.g. '*')
	 */
	private void addProposal(Collection<ICompletionProposal> proposals, char relevantChar,
			String fontDecorationName, String fontDecorationSyntax) {
		addProposal(proposals, relevantChar, fontDecorationName, fontDecorationSyntax, "", true);
	}

	/**
	 * Creates a font decoration proposal according to the given font decoration name (e.g. 'Bold') and the
	 * given font decoration Syntax (e.g. '*'), only if the given relevant char is compatible with this
	 * proposal.
	 * 
	 * @param proposals
	 *            the current proposals
	 * @param relevantChar
	 *            the relevant char
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
	private void addProposal(Collection<ICompletionProposal> proposals, char relevantChar,
			String fontDecorationName, String fontDecorationSyntax, String variableName,
			boolean biDirectionalSyntax) {
		if (relevantChar == '\n') {
			proposals.add(createFontDecorationProposal(fontDecorationName, fontDecorationSyntax,
					variableName, biDirectionalSyntax));
		} else if (biDirectionalSyntax && fontDecorationSyntax.charAt(0) == relevantChar) {
			String currenText = document.get().substring(0, offset);
			currenText = currenText.substring(currenText.lastIndexOf(relevantChar));
			proposals.add(createFontDecorationProposal(fontDecorationName, fontDecorationSyntax,
					variableName, currenText));
		}
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
	 * @param beginningText
	 *            the beginning text
	 * @return a font decoration proposal according to the given font decoration name (e.g. 'Bold') and the
	 *         given font decoration Syntax (e.g. '*')
	 */
	private ICompletionProposal createFontDecorationProposal(String fontDecorationName,
			String fontDecorationSyntax, String variableName, String beginningText) {
		String templateName = fontDecorationSyntax + fontDecorationName.toLowerCase() + fontDecorationSyntax;
		String templatePattern = "";
		String actualBeginningText = beginningText;
		if (actualBeginningText.contains(" ")) {
			actualBeginningText = actualBeginningText.substring(actualBeginningText.lastIndexOf(' ')).trim();
		}
		if (actualBeginningText.contains(fontDecorationSyntax)
				&& !(isSkipped(fontDecorationSyntax.charAt(0)))) {
			actualBeginningText = actualBeginningText.substring(
					actualBeginningText.lastIndexOf(fontDecorationSyntax.charAt(0))
							+ fontDecorationSyntax.length()).trim();
		}
		templatePattern = actualBeginningText + fontDecorationSyntax;
		return createTemplateProposal(templateName, fontDecorationName, templatePattern, null);
	}

	/**
	 * Indicates if the completion consider given char as part of the proposal or not.
	 * 
	 * @param currentChar
	 *            the current char
	 * @return true if the completion consider given char as part of the proposal or not, false otherwise
	 */
	private boolean isSkipped(char currentChar) {
		return currentChar == '_' || currentChar == '@';
	}

}
