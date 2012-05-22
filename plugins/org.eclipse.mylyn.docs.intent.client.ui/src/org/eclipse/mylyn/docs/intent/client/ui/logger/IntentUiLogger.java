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
package org.eclipse.mylyn.docs.intent.client.ui.logger;

import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;

/**
 * Logger provinding facilies for loggin error or debug.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentUiLogger {

	/**
	 * IntentUiLogger constructor.
	 */
	private IntentUiLogger() {

	}

	/**
	 * Log the specified information.
	 * 
	 * @param message
	 *            a human-readable message, localized to the current locale.
	 */
	public static void logInfo(String message) {
		IntentLogger.getInstance().log(LogType.INFO, message);
	}

	/**
	 * Log the specified informations (for debug only).
	 * 
	 * @param message
	 *            a human-readable message, localized to the current locale.
	 */
	public static void logForDebug(String message) {
		IntentLogger.getInstance().log(LogType.LIFECYCLE, message);
	}

	/**
	 * Log the specified error.
	 * 
	 * @param exception
	 *            , a low-level exception.
	 */
	public static void logError(Throwable exception) {
		IntentLogger.getInstance().log(LogType.ERROR, "Unexpected Exception", exception);
	}

	/**
	 * Log the specified error.
	 * 
	 * @param message
	 *            , a human-readable message, localized to the current locale.
	 * @param exception
	 *            , a low-level exception, or <code>null</code> if not applicable.
	 */
	public static void logError(String message, Throwable exception) {
		IntentLogger.getInstance().log(LogType.ERROR, message, exception);
	}

}
