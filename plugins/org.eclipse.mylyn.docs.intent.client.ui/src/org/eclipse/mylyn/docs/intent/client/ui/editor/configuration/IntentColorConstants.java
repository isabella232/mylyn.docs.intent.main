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
package org.eclipse.mylyn.docs.intent.client.ui.editor.configuration;

import org.eclipse.swt.graphics.RGB;

/**
 * Constants for all the colors used by an Intent Editor.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentColorConstants {

	// -----------------------------------
	// Color constant for MODELING UNITS
	// ----------------------------------
	private static final RGB MU_BACKGROUND = new RGB(221, 221, 221);

	private static final RGB MU_KEYWORD_FOREGROUND = new RGB(139, 10, 80);

	private static final RGB MU_DEFAULT_FOREGROUND = new RGB(0, 0, 0);

	private static final RGB MU_STRING_FOREGROUND = new RGB(0, 0, 180);

	private static final RGB MU_DECORATION_LINE_FOREGROUND = new RGB(84, 84, 84);

	private static final RGB MU_DECORATION_BACKGROUND = new RGB(195, 195, 195);

	private static final int MU_DECORATION_LINE_WIDTH = 2;

	// -----------------------------------
	// Color constant for DECRIPTION UNITS
	// ----------------------------------
	private static final RGB DU_BACKGROUND = null;

	private static final RGB DU_KEYWORD_FOREGROUND = new RGB(139, 10, 80);

	private static final RGB DU_DEFAULT_FOREGROUND = new RGB(0, 0, 0);

	private static final RGB DU_TITLE_FOREGROUND = new RGB(0, 0, 0);

	/**
	 * IntentColorConstant constructor.
	 */
	private IntentColorConstants() {

	}

	/*
	 * Colors managed through preferences
	 */

	/*
	 * Private colors
	 */
	public static RGB getDuBackground() {
		return DU_BACKGROUND;
	}

	public static RGB getDuDefaultForeground() {
		return DU_DEFAULT_FOREGROUND;
	}

	public static RGB getDuKeywordForeground() {
		return DU_KEYWORD_FOREGROUND;
	}

	public static RGB getDuTitleForeground() {
		return DU_TITLE_FOREGROUND;
	}

	public static RGB getMuBackground() {
		return MU_BACKGROUND;
	}

	public static RGB getMuDecorationBackground() {
		return MU_DECORATION_BACKGROUND;
	}

	public static RGB getMuDecorationLineForeground() {
		return MU_DECORATION_LINE_FOREGROUND;
	}

	public static int getMuDecorationLineWidth() {
		return MU_DECORATION_LINE_WIDTH;
	}

	public static RGB getMuDefaultForeground() {
		return MU_DEFAULT_FOREGROUND;
	}

	public static RGB getMuKeywordForeground() {
		return MU_KEYWORD_FOREGROUND;
	}

	public static RGB getMuStringForeground() {
		return MU_STRING_FOREGROUND;
	}

}
