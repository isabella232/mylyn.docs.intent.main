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
package org.eclipse.mylyn.docs.intent.serializer;

/**
 * Represents the position of a parsed element.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ParsedElementPosition implements Comparable<ParsedElementPosition> {

	/**
	 * The offset (indice of the first character).
	 */
	private int offset;

	/**
	 * The length.
	 */
	private int length;

	/**
	 * The declaration length.
	 */
	private int declarationLength;

	/**
	 * constructor.
	 * 
	 * @param offset
	 *            the offset (indice of the first character)
	 * @param length
	 *            the length
	 */
	public ParsedElementPosition(int offset, int length) {
		this.offset = offset;
		this.length = length;
		this.declarationLength = length;
	}

	/**
	 * constructor.
	 * 
	 * @param offset
	 *            the offset (indice of the first character)
	 * @param length
	 *            the length
	 * @param declarationLength
	 *            the declaration length
	 */
	public ParsedElementPosition(int offset, int length, int declarationLength) {
		this.offset = offset;
		this.length = length;
		this.declarationLength = declarationLength;
	}

	/**
	 * Returns the offset (indice of the first character).
	 * 
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Returns the length.
	 * 
	 * @return the the length
	 */
	public int getLength() {
		return length;
	}

	public int getDeclarationLength() {
		return declarationLength;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ParsedElementPosition arg0) {
		int res = Integer.valueOf(offset).compareTo(arg0.offset);
		if (res == 0) {
			res = Integer.valueOf(length).compareTo(arg0.length);
		}
		return res;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[offset: " + offset + ", declaration length: "
				+ declarationLength + ", length: " + length + "]";
	}

}
