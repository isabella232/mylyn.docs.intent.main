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
package org.eclipse.mylyn.docs.intent.client.ui.editor.scanner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.ColorManager;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.IntentColorConstants;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.IntentFontConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * Scanner for detecting Modeling Units.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentModelingUnitScanner extends AbstractIntentScanner {

	/**
	 * Represents the keyword of a Modeling Unit.
	 */
	public static final String KEYWORD_ATTRIBUTE = "__Intent_keyword";

	/**
	 * Represents all the other words of a Modeling Unit.
	 */
	public static final String DEFAULT_ATTRIBUTE = "__Intent_default";

	/**
	 * Keywords supported by this scanner.
	 */
	private static final String[] MU_KEYWORDS = new String[] {"new", "Resource", "@M", "M@", "@Annotation",
			"@ref",
	};

	/**
	 * Default foreground color.
	 */
	private Color defaultforeGroundColor;

	/**
	 * Foreground color for keywords.
	 */
	private Color keyWordforeGroundColor;

	/**
	 * Foreground color for strings.
	 */
	private Color stringforeGroundColor;

	/**
	 * background color.
	 */
	private Color backgroundColor;

	/**
	 * IntentModelingUnitScanner constructor : sets the rules used by this scanner.
	 * 
	 * @param colorManager
	 *            the color manager to use for rendering this zone.
	 */
	public IntentModelingUnitScanner(ColorManager colorManager) {
		super(colorManager);

		defaultforeGroundColor = colorManager.getColor(IntentColorConstants.getMuDefaultForeground());
		keyWordforeGroundColor = colorManager.getColor(IntentColorConstants.getMuKeywordForeground());
		stringforeGroundColor = colorManager.getColor(IntentColorConstants.getMuStringForeground());
		backgroundColor = colorManager.getColor(IntentColorConstants.getMuBackground());

		List<IRule> rules = new ArrayList<IRule>();
		rules.add(computeMUKeyWordsRule());
		rules.addAll(computeStringRules());
		rules.add(computeWhiteSpaceRule());
		setRules(rules.toArray(new IRule[rules.size()]));

	}

	/**
	 * Create the rule related to modeling unit keyWords.
	 * 
	 * @return the rule related to modeling unit keyWords
	 */
	private IRule computeMUKeyWordsRule() {
		IToken keyWordToken = new Token(new TextAttribute(keyWordforeGroundColor, backgroundColor, SWT.BOLD,
				IntentFontConstants.getModelingUnitFont()));
		IToken defaultToken = new Token(new TextAttribute(defaultforeGroundColor, backgroundColor, SWT.NONE,
				IntentFontConstants.getModelingUnitFont()));

		WordRule keyWordsRule = new WordRule(new IntentWordDetector(true), defaultToken);
		for (int i = 0; i < MU_KEYWORDS.length; i++) {
			keyWordsRule.addWord(MU_KEYWORDS[i], keyWordToken);
		}

		return keyWordsRule;
	}

	/**
	 * Create all the rules related to Strings (for example : "example" or 'example').
	 * 
	 * @return a list containing all the rules related to related to Strings
	 */
	private Collection<? extends IRule> computeStringRules() {
		IToken stringToken = new Token(new TextAttribute(stringforeGroundColor, backgroundColor, SWT.ITALIC,
				IntentFontConstants.getModelingUnitFont()));
		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new SingleLineRule("\"", "\"", stringToken, '\\'));
		rules.add(new MultiLineRule("'", "'", stringToken, '\\'));
		return rules;
	}

	/**
	 * Computes the Rule related to white spaces.
	 * 
	 * @return the Rule related to white spaces
	 */
	private IRule computeWhiteSpaceRule() {
		IToken whiteSpaceToken = new Token(new TextAttribute(null, backgroundColor, SWT.NONE,
				IntentFontConstants.getModelingUnitFont()));
		return new WhitespaceRule(new IWhitespaceDetector() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.text.rules.IWhitespaceDetector#isWhitespace(char)
			 */
			public boolean isWhitespace(char c) {
				return c == ' ' || c == '\t' || c == '\n' || c == '\r';
			}
		}, whiteSpaceToken);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.AbstractIntentScanner#getConfiguredContentType()
	 */
	@Override
	public String getConfiguredContentType() {
		return IntentPartitionScanner.INTENT_MODELINGUNIT;
	}

}
