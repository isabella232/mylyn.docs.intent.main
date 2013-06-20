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

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * An {@link IPredicateRule} defined to fix issues when using SingleLineRule.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class SingleLinePatternRule implements IRule, IPredicateRule {

	private final Token token;

	private int readCount;

	/**
	 * The string indicating the beginning of this rule.
	 */
	private String startToken;

	/**
	 * The string indicating the end of this rule.
	 */
	private String endToken;

	/**
	 * Constructor.
	 * 
	 * @param startToken
	 *            the string indicating the beginning of this rule
	 * @param endToken
	 *            the string indicating the end of this rule
	 * @param token
	 *            the token to apply if rule is correctly detected
	 */
	public SingleLinePatternRule(String startToken, String endToken, Token token) {
		this.token = token;
		this.startToken = startToken;
		this.endToken = endToken;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.rules.IPredicateRule#getSuccessToken()
	 */
	public IToken getSuccessToken() {
		return token;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
	 */
	public IToken evaluate(ICharacterScanner scanner) {
		return evaluate(scanner, false);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.rules.IPredicateRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner,
	 *      boolean)
	 */
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		readCount = 0;
		boolean endOrStartSequenceDetected = false;
		if (resume) {
			if (endSequenceDetected(scanner)) {
				endOrStartSequenceDetected = true;
			}
		} else {
			if (startSequenceDetected(scanner)) {
				if (endSequenceDetected(scanner)) {
					endOrStartSequenceDetected = true;
				}
			}
		}
		if (endOrStartSequenceDetected) {
			return token;
		}
		while (readCount > 0) {
			--readCount;
			scanner.unread();
		}
		return Token.UNDEFINED;
	}

	private boolean endSequenceDetected(ICharacterScanner scanner) {
		boolean endSequenceDetected = false;
		int read = read(scanner);
		int i = 0;
		while (read != ICharacterScanner.EOF && read != '\n' && read != endToken.charAt(i)) {
			read = read(scanner);
		}
		while (i < endToken.length() - 1 && read == endToken.charAt(i)) {
			i++;
			read = read(scanner);
			if (read == ICharacterScanner.EOF || read == '\n') {
				break;
			}
		}
		endSequenceDetected = read == endToken.charAt(i);
		return endSequenceDetected;
	}

	private boolean startSequenceDetected(ICharacterScanner scanner) {
		boolean startSequenceDetected = false;
		int read = read(scanner);
		int i = 0;
		while (i < startToken.length() && read == startToken.charAt(i)) {
			i++;
			read = read(scanner);
			if (read == ICharacterScanner.EOF) {
				break;
			}
		}
		startSequenceDetected = i == startToken.length();
		return startSequenceDetected;
	}

	private int read(ICharacterScanner scanner) {
		++readCount;
		return scanner.read();
	}

}
