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
		node.put(IntentPreferenceConstants.MATCHING_BRACKETS_COLOR,
				StringConverter.asString(MATCHING_BRACKET_COLOR));
		node.put(IntentPreferenceConstants.SHOW_PREVIEW_PAGE, Boolean.TRUE.toString());
		node.put(IntentPreferenceConstants.DND_DISPLAY_POP_UP, Boolean.FALSE.toString());
		node.put(IntentPreferenceConstants.DND_USE_EXTERNAL_REFERENCES, Boolean.TRUE.toString());
	}
}
