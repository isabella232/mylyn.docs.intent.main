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
package org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image;

import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;

/**
 * A {@link StyleRange} for intent images.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentImageStyleRange extends StyleRange {

	/**
	 * Default constructor.
	 * 
	 * @param start
	 *            start offset of the style
	 * @param length
	 *            length of the style
	 * @param foreground
	 *            foreground color of the style, null if none
	 * @param background
	 *            background color of the style, null if none
	 * @param fontStyle
	 *            font style of the style, may be SWT.NORMAL, SWT.ITALIC or SWT.BOLD
	 */
	public IntentImageStyleRange(int start, int length, Color foreground, Color background, int fontStyle) {
		super(start, length, foreground, background, fontStyle);
	}
}
