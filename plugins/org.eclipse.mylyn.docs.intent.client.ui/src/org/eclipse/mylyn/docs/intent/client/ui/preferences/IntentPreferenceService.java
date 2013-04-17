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
package org.eclipse.mylyn.docs.intent.client.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;

/**
 * An utility class providing facilities for accessing intent preferences values.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentPreferenceService {

	/**
	 * Returns the values held by the given preference key as a boolean.
	 * 
	 * @param intentPreferenceKey
	 *            the intent preference key (on of IntentPreferenceConstants).
	 * @return the values held by the given preference key as a boolean
	 */
	public static Boolean getBoolean(String intentPreferenceKey) {
		IPreferenceStore preferenceStore = IntentEditorActivator.getDefault().getPreferenceStore();
		return preferenceStore.getBoolean(intentPreferenceKey);
	}

}
