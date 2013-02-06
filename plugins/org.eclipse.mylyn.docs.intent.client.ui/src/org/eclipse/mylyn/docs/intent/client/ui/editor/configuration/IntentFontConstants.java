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

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.themes.ITheme;
import org.eclipse.ui.themes.IThemeManager;

/**
 * Constants for all the font used by an Intent Editor.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentFontConstants {

	private static final String TITLE_FONT_PREFERENCE_ID = "org.eclipse.mylyn.docs.intent.editor.font.title";

	private static final String DESCRIPTION_FONT_PREFERENCE_ID = "org.eclipse.mylyn.docs.intent.editor.font.text";

	private static final String MODEL_FRAGMENT_FONT_PREFERENCE_ID = "org.eclipse.mylyn.docs.intent.editor.font.modelfragment";

	private static FontRegistry fontRegistry;

	private static Font imageReferenceFont;

	/**
	 * IntentFontConstants constructor.
	 */
	private IntentFontConstants() {
	}

	/**
	 * Returns the font associated to description units.
	 * 
	 * @return the font associated to description units.
	 */
	public static Font getDescriptionFont() {
		return getCurrentFontRegistry().get(DESCRIPTION_FONT_PREFERENCE_ID);
	}

	/**
	 * Returns the font associated to structured elements title.
	 * 
	 * @return the font associated to structured elements title.
	 */
	public static Font getTitleFont() {
		return getCurrentFontRegistry().get(TITLE_FONT_PREFERENCE_ID);
	}

	/**
	 * Returns the default font associated to Modeling Unit content.
	 * 
	 * @return the default font associated to Modeling Unit content
	 */
	public static Font getModelingUnitFont() {
		return getCurrentFontRegistry().get(MODEL_FRAGMENT_FONT_PREFERENCE_ID);
	}

	/**
	 * Returns the {@link FontRegistry} to use for getting fonts as defined in user preferences.
	 * 
	 * @return the {@link FontRegistry} to use for getting fonts as defined in user preferences
	 */
	private static FontRegistry getCurrentFontRegistry() {
		if (fontRegistry == null) {
			IThemeManager themeManager = PlatformUI.getWorkbench().getThemeManager();
			ITheme currentTheme = themeManager.getCurrentTheme();

			fontRegistry = currentTheme.getFontRegistry();
		}
		return fontRegistry;
	}

	/**
	 * Returns the {@link Font} used as reference to fill a line with images.
	 * 
	 * @return the {@link Font} used as reference to fill a line with images
	 */
	public static Font getImageReferenceFont() {
		if (imageReferenceFont == null) {
			imageReferenceFont = new Font(Display.getDefault(), "Verdana", 1, SWT.NONE);
		}
		return imageReferenceFont;
	}

}
