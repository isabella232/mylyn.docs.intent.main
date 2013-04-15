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
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
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
	private static final RGB MATCHING_BRACKET_COLOR = new RGB(192, 192, 192);

	private static final RGB MU_KEYWORD_COLOR = new RGB(139, 10, 80);

	private static final RGB MU_DEFAULT_COLOR = new RGB(0, 0, 0);

	private static final RGB STRING_COLOR = new RGB(0, 0, 180);

	private static final RGB DU_KEYWORD_COLOR = new RGB(139, 10, 80);

	private static final RGB DU_DEFAULT_COLOR = new RGB(0, 0, 0);

	private static final RGB DU_TITLE_COLOR = new RGB(0, 0, 0);

	private static final RGB DU_LIST_COLOR = new RGB(84, 84, 84);

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences node = InstanceScope.INSTANCE.getNode(IntentEditorActivator.PLUGIN_ID);
		node.put(IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING, Boolean.FALSE.toString());
		node.put(IntentPreferenceConstants.ACTIVATE_BACKUP, Boolean.FALSE.toString());
		node.put(IntentPreferenceConstants.TEXT_WRAP, Boolean.TRUE.toString());
		node.put(IntentPreferenceConstants.COLLAPSE_MODELING_UNITS, Boolean.FALSE.toString());
		node.put(IntentPreferenceConstants.MATCHING_BRACKETS, Boolean.TRUE.toString());
		node.put(IntentPreferenceConstants.SHOW_PREVIEW_PAGE, Boolean.TRUE.toString());
		node.put(IntentPreferenceConstants.DND_DISPLAY_POP_UP, Boolean.FALSE.toString());
		node.put(IntentPreferenceConstants.DND_USE_EXTERNAL_REFERENCES, Boolean.TRUE.toString());

		// Colors
		node.put(IntentPreferenceConstants.MATCHING_BRACKETS_COLOR,
				StringConverter.asString(MATCHING_BRACKET_COLOR));
		node.put(IntentPreferenceConstants.DU_DEFAULT_FOREGROUND, StringConverter.asString(DU_DEFAULT_COLOR));
		node.put(IntentPreferenceConstants.DU_KEYWORD_FOREGROUND, StringConverter.asString(DU_KEYWORD_COLOR));
		node.put(IntentPreferenceConstants.DU_TITLE_FOREGROUND, StringConverter.asString(DU_TITLE_COLOR));
		node.put(IntentPreferenceConstants.DU_LIST_FOREGROUND, StringConverter.asString(DU_LIST_COLOR));
		node.put(IntentPreferenceConstants.MU_DEFAULT_COLOR, StringConverter.asString(MU_DEFAULT_COLOR));
		node.put(IntentPreferenceConstants.MU_KEYWORD_COLOR, StringConverter.asString(MU_KEYWORD_COLOR));
		node.put(IntentPreferenceConstants.STRING_COLOR, StringConverter.asString(STRING_COLOR));
	}
}
