/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.swt.graphics.RGB;

/**
 * Initializes the default preference for the Intent UI.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentPreferenceInitializer extends AbstractPreferenceInitializer {
	// TODO extract to a color manager
	/**
	 * Default value for brackets color.
	 */
	private static final RGB MATCHING_BRACKET_COLOR = new RGB(192, 192, 192);

	/**
	 * Default value for Modeling Units keywordxs color.
	 */
	private static final RGB MU_KEYWORD_COLOR = new RGB(139, 10, 80);

	/**
	 * Default value for Modeling Unit default color.
	 */
	private static final RGB MU_DEFAULT_COLOR = new RGB(0, 0, 0);

	/**
	 * Default value for Strings color.
	 */
	private static final RGB STRING_COLOR = new RGB(0, 0, 180);

	/**
	 * Default value for Description Units keywords color.
	 */
	private static final RGB DU_KEYWORD_COLOR = new RGB(139, 10, 80);

	/**
	 * Default value for Description Units default color.
	 */
	private static final RGB DU_DEFAULT_COLOR = new RGB(0, 0, 0);

	/**
	 * Default value for Titles color.
	 */
	private static final RGB DU_TITLE_COLOR = new RGB(0, 0, 0);

	/**
	 * Default value for Lists color.
	 */
	private static final RGB DU_LIST_COLOR = new RGB(84, 84, 84);

	/**
	 * Default value for code color.
	 */
	private static final RGB CODE_COLOR = STRING_COLOR;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences defaultScope = DefaultScope.INSTANCE.getNode(IntentEditorActivator.PLUGIN_ID);
		// Appearance
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.TEXT_WRAP, Boolean.TRUE.toString());
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.COLLAPSE_MODELING_UNITS,
				Boolean.FALSE.toString());
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.MATCHING_BRACKETS,
				Boolean.TRUE.toString());
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.SHOW_PREVIEW_PAGE,
				Boolean.FALSE.toString());

		// Colors
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.MATCHING_BRACKETS_COLOR,
				StringConverter.asString(MATCHING_BRACKET_COLOR));
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.DU_DEFAULT_FOREGROUND,
				StringConverter.asString(DU_DEFAULT_COLOR));
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.DU_KEYWORD_FOREGROUND,
				StringConverter.asString(DU_KEYWORD_COLOR));
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.DU_TITLE_FOREGROUND,
				StringConverter.asString(DU_TITLE_COLOR));
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.DU_LIST_FOREGROUND,
				StringConverter.asString(DU_LIST_COLOR));
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.MU_DEFAULT_COLOR,
				StringConverter.asString(MU_DEFAULT_COLOR));
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.MU_KEYWORD_COLOR,
				StringConverter.asString(MU_KEYWORD_COLOR));
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.STRING_COLOR,
				StringConverter.asString(STRING_COLOR));
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.CODE_FOREGROUND,
				StringConverter.asString(CODE_COLOR));

		// Drag and drop
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.DND_DISPLAY_POP_UP,
				Boolean.FALSE.toString());
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.DND_USE_EXTERNAL_REFERENCES,
				Boolean.TRUE.toString());

		// Export
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.EXPORT_DISPLAY_REFERENCES_INLINE,
				Boolean.FALSE.toString());

		// Other
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING,
				Boolean.FALSE.toString());
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.ACTIVATE_BACKUP, Boolean.FALSE.toString());
		setDefaultPrefValue(defaultScope, IntentPreferenceConstants.SHOW_CHEAT_SHEET_ON_PROJECT_CREATION,
				Boolean.TRUE.toString());

	}

	/**
	 * Sets the default value for the preference with the given id.
	 * 
	 * @param defaultScope
	 *            default preference node
	 * @param prefKey
	 *            the preference key
	 * @param defaultValue
	 *            the default value for this preference
	 */
	private void setDefaultPrefValue(IEclipsePreferences defaultScope, String prefKey, String defaultValue) {
		defaultScope.put(prefKey, defaultValue);
	}
}
