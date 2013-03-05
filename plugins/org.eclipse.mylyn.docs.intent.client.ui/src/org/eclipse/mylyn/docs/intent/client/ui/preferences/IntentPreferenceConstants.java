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

/**
 * Intent preferences constants.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public interface IntentPreferenceConstants {

	/*
	 * Logging-relative preferences
	 */
	/**
	 * When advanced logging is active, each Intent repository client logs its activity.
	 */
	String ACTIVATE_ADVANCE_LOGGING = "org.eclipse.mylyn.docs.intent.client.ui.preferences.advanced_logging";

	/*
	 * UI-relative preferences.
	 */
	/**
	 * Indicates whether matching brackets should be displayed.
	 */
	String MATCHING_BRACKETS = "org.eclipse.mylyn.docs.intent.client.ui.preferences.matching_brackets";

	/**
	 * When matching brackets are displayed, specifies their color.
	 */
	String MATCHING_BRACKETS_COLOR = "org.eclipse.mylyn.docs.intent.client.ui.preferences.matching_brackets_color";

	/**
	 * Indicates whether Modeling units should be initially collapsed in the Intent editor.
	 */
	String COLLAPSE_MODELING_UNITS = "org.eclipse.mylyn.docs.intent.client.ui.preferences.collapse_mu";

	/*
	 * Drag and drop preferences.
	 */
	/**
	 * Indicates whether we should display a pop-up letting the end-user choose which drop mode should be
	 * used.
	 */
	String DND_DISPLAY_POP_UP = "org.eclipse.mylyn.docs.intent.client.ui.preferences.dnd_display_popup";

	/**
	 * Indicates if by default, when dropping an artifact (e.g. a Java class) inside an
	 * {@link org.eclipse.mylyn.docs.intent.core.document.IntentDocument} , the link should be represented
	 * using an {@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference} or a full
	 * copy.
	 */
	String DND_USE_EXTERNAL_REFERENCES = "org.eclipse.mylyn.docs.intent.client.ui.preferences.dnd_use_external_refs";

	/**
	 * Indicates if Intent should perform a textual back-up of the Intent documents.
	 */
	String ACTIVATE_BACKUP = "org.eclipse.mylyn.docs.intent.client.ui.preferences.activate_backup";

}
