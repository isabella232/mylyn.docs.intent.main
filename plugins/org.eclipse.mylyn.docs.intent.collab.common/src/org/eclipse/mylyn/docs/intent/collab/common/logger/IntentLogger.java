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
package org.eclipse.mylyn.docs.intent.collab.common.logger;

import org.eclipse.mylyn.docs.intent.collab.common.internal.logger.IntentLoggerRegistry;
import org.eclipse.mylyn.docs.intent.collab.common.internal.logger.IntentLoggerRegistryListener;

/**
 * The Intent logger, that delegates log to all loggers contributed through the
 * {@link IntentLoggerRegistryListener#INTENT_LOGGER_EXTENSION_POINT} extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentLogger implements IIntentLogger {

	private static IntentLogger INSTANCE;

	/**
	 * Private constructor.
	 */
	private IntentLogger() {

	}

	/**
	 * Returns the instance of the current logger.
	 * 
	 * @return the instance of the current logger
	 */
	public static IntentLogger getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new IntentLogger();
		}
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger#log(org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType,
	 *      java.lang.String)
	 */
	public void log(LogType logType, String message) {
		for (IIntentLogger logger : IntentLoggerRegistry.getDeclaredLoggers()) {
			logger.log(logType, message);
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger#log(org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType,
	 *      java.lang.String, java.lang.Throwable)
	 */
	public void log(LogType logType, String message, Throwable exception) {
		for (IIntentLogger logger : IntentLoggerRegistry.getDeclaredLoggers()) {
			logger.log(logType, message, exception);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger#logError(java.lang.Throwable)
	 */
	public void logError(Throwable throwable) {
		log(LogType.ERROR, throwable.getMessage(), throwable);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger#setDisplayLifecycleInformations(boolean)
	 */
	public void setDisplayLifecycleInformations(boolean shouldDisplayLifecycleInformations) {
		for (IIntentLogger logger : IntentLoggerRegistry.getDeclaredLoggers()) {
			logger.setDisplayLifecycleInformations(shouldDisplayLifecycleInformations);
		}
	}

}
