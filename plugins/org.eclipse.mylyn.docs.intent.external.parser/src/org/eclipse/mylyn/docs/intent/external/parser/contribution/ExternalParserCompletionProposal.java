/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.external.parser.contribution;

import org.eclipse.swt.graphics.Image;

/**
 * Completion proposal provided by external parsers.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class ExternalParserCompletionProposal {
	/**
	 * Completion proposal name.
	 */
	private String name;

	/**
	 * Completion proposal description.
	 */
	private String description;

	/**
	 * Completion proposal template pattern.
	 */
	private String pattern;

	/**
	 * Completion proposal image.
	 */
	private Image image;

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            Name
	 * @param description
	 *            Description
	 * @param pattern
	 *            Pattern
	 * @param image
	 *            Image
	 */
	public ExternalParserCompletionProposal(String name, String description, String pattern, Image image) {
		this.name = name;
		this.description = description;
		this.pattern = pattern;
		this.image = image;
	}

	/**
	 * Get completion proposal name.
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get completion proposal description.
	 * 
	 * @return Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get completion proposal pattern.
	 * 
	 * @return Pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * Get completion proposal image.
	 * 
	 * @return Image
	 */
	public Image getImage() {
		return image;
	}
}
