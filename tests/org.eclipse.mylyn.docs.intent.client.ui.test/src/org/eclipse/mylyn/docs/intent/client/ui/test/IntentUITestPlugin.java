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
package org.eclipse.mylyn.docs.intent.client.ui.test;

import org.eclipse.core.runtime.Plugin;

/**
 * Intent UI test plugin.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentUITestPlugin extends Plugin {

	/**
	 * The running instance.
	 */
	private static IntentUITestPlugin instance;

	/**
	 * Default constructor.
	 */
	public IntentUITestPlugin() {
		instance = this;
	}

	/**
	 * Returns the running instance of this plugin.
	 * 
	 * @return the running instance of this plugin
	 */
	public static IntentUITestPlugin getInstance() {
		return instance;
	}

}
