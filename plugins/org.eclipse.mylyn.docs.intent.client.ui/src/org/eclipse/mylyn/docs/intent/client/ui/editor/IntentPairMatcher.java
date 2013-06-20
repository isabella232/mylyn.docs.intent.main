/*******************************************************************************
 * Copyright (c) 2006, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Christian Plesner Hansen (plesner@quenta.org) - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.editor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ICharacterPairMatcher;

/**
 * Helper class for match pairs of special characters. Based on
 * org.eclipse.jface.text.source.DefaultCharacterPairMatcher, but without partitioning constraints: this allow
 * the pair matcher being efficient even without a correct partitioning.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentPairMatcher implements ICharacterPairMatcher {
	// CHECKSTYLE:OFF
	/**
	 * Blocks used by aggregated the matcher.
	 */
	protected static final char[] BLOCKS = {'(', ')', '[', ']', '{', '}',
	};

	private int fAnchor = -1;

	private final CharPairs fPairs;

	/**
	 * Creates a new character pair matcher that matches the specified characters within the specified
	 * partitioning. The specified list of characters must have the form <blockquote>{ <i>start</i>,
	 * <i>end</i>, <i>start</i>, <i>end</i>, ..., <i>start</i>, <i>end</i> }</blockquote> For instance:
	 * 
	 * <pre>
	 * char[] chars = new char[] {'(', ')', '{', '}', '[', ']'};
	 * new DefaultCharacterPairMatcher(chars, ...);
	 * </pre>
	 */
	public IntentPairMatcher() {
		Assert.isLegal(BLOCKS.length % 2 == 0);
		fPairs = new CharPairs(BLOCKS);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#match(org.eclipse.jface.text.IDocument, int)
	 */
	public IRegion match(IDocument doc, int offset) {
		if (doc == null || offset < 0 || offset > doc.getLength())
			return null;
		try {
			return performMatch(doc, offset);
		} catch (BadLocationException ble) {
			return null;
		}
	}

	/*
	 * Performs the actual work of matching for #match(IDocument, int).
	 */
	private IRegion performMatch(IDocument doc, int caretOffset) throws BadLocationException {
		final int charOffset = caretOffset - 1;
		final char prevChar = doc.getChar(Math.max(charOffset, 0));
		if (!fPairs.contains(prevChar))
			return null;
		final boolean isForward = fPairs.isStartCharacter(prevChar);
		fAnchor = isForward ? ICharacterPairMatcher.LEFT : ICharacterPairMatcher.RIGHT;
		final int searchStartPosition = isForward ? caretOffset : caretOffset - 2;
		final int adjustedOffset = isForward ? charOffset : caretOffset;
		int endOffset = findMatchingPeer(doc, prevChar, fPairs.getMatching(prevChar), isForward,
				isForward ? doc.getLength() : -1, searchStartPosition);
		if (endOffset == -1)
			return null;
		final int adjustedEndOffset = isForward ? endOffset + 1 : endOffset;
		if (adjustedEndOffset == adjustedOffset)
			return null;
		return new Region(Math.min(adjustedOffset, adjustedEndOffset), Math.abs(adjustedEndOffset
				- adjustedOffset));
	}

	/**
	 * Searches <code>doc</code> for the specified end character, <code>end</code>.
	 * 
	 * @param doc
	 *            the document to search
	 * @param start
	 *            the opening matching character
	 * @param end
	 *            the end character to search for
	 * @param searchForward
	 *            search forwards or backwards?
	 * @param boundary
	 *            a boundary at which the search should stop
	 * @param startPos
	 *            the start offset
	 * @return the index of the end character if it was found, otherwise -1
	 * @throws BadLocationException
	 *             if the document is accessed with invalid offset or line
	 */
	private int findMatchingPeer(IDocument doc, char start, char end, boolean searchForward, int boundary,
			int startPos) throws BadLocationException {
		int pos = startPos;
		while (pos != boundary) {
			final char c = doc.getChar(pos);
			if (doc.getChar(pos) == end) {
				return pos;
			} else if (c == start) {
				pos = findMatchingPeer(doc, start, end, searchForward, boundary,
						simpleIncrement(pos, searchForward));
				if (pos == -1)
					return -1;
			}
			pos = simpleIncrement(pos, searchForward);
		}
		return -1;
	}

	/* @see ICharacterPairMatcher#getAnchor() */
	public int getAnchor() {
		return fAnchor;
	}

	/* @see ICharacterPairMatcher#dispose() */
	public void dispose() {
	}

	/* @see ICharacterPairMatcher#clear() */
	public void clear() {
		fAnchor = -1;
	}

	/**
	 * Utility class that encapsulates access to matching character pairs.
	 */
	private static class CharPairs {

		private final char[] fPairs;

		public CharPairs(char[] pairs) {
			fPairs = pairs;
		}

		/**
		 * Returns true if the specified character pair occurs in one of the character pairs.
		 * 
		 * @param c
		 *            a character
		 * @return true exactly if the character occurs in one of the pairs
		 */
		public boolean contains(char c) {
			return getAllCharacters().contains(new Character(c));
		}

		private Set<Character> fCharsCache = null;

		/**
		 * @return A set containing all characters occurring in character pairs.
		 */
		private Set<Character> getAllCharacters() {
			if (fCharsCache == null) {
				Set<Character> set = new HashSet<Character>();
				for (int i = 0; i < fPairs.length; i++)
					set.add(new Character(fPairs[i]));
				fCharsCache = set;
			}
			return fCharsCache;
		}

		/**
		 * Returns true if the specified character opens a character pair when scanning in the specified
		 * direction.
		 * 
		 * @param c
		 *            a character
		 * @param searchForward
		 *            the direction of the search
		 * @return whether or not the character opens a character pair
		 */
		public boolean isOpeningCharacter(char c, boolean searchForward) {
			for (int i = 0; i < fPairs.length; i += 2) {
				if (searchForward && getStartChar(i) == c)
					return true;
				else if (!searchForward && getEndChar(i) == c)
					return true;
			}
			return false;
		}

		/**
		 * Returns true of the specified character is a start character.
		 * 
		 * @param c
		 *            a character
		 * @return true exactly if the character is a start character
		 */
		public boolean isStartCharacter(char c) {
			return this.isOpeningCharacter(c, true);
		}

		/**
		 * Returns the matching character for the specified character.
		 * 
		 * @param c
		 *            a character occurring in a character pair
		 * @return the matching character
		 */
		// CHECKSTYLE:OFF
		public char getMatching(char c) {
			for (int i = 0; i < fPairs.length; i += 2) {
				if (getStartChar(i) == c) {
					return getEndChar(i);
				} else if (getEndChar(i) == c) {
					return getStartChar(i);
				}
			}
			Assert.isTrue(false);
			return '\0';
		}

		private char getStartChar(int i) {
			return fPairs[i];
		}

		private char getEndChar(int i) {
			return fPairs[i + 1];
		}

	}

	private int simpleIncrement(int pos, boolean searchForward) {
		return pos + (searchForward ? 1 : -1);
	}
	// CHECKSTYLE:ON
}
