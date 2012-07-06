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

/**
 * A standalone interface for a logger used in Intent to display informations to end-user (errors, but also
 * client behavior if options allow it).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public interface IIntentLogger {

	/**
	 * Logs the given message, displaying it differently according to the given {@link LogType}.
	 * 
	 * @param logType
	 *            the type of the message to display (critical error, warning, lifecycle information...)
	 * @param message
	 *            the message to log
	 */
	void log(LogType logType, String message);

	/**
	 * Logs the given message, displaying it differently according to the given {@link LogType}.
	 * 
	 * @param logType
	 *            the type of the message to display (critical error, warning, lifecycle information...)
	 * @param exception
	 *            the exception that caused the error to log
	 * @param message
	 *            the message to log
	 */
	void log(LogType logType, String message, Throwable exception);

	/**
	 * Logs the given {@link Throwable}, with a {@link LogType#ERROR} code.
	 * 
	 * @param throwable
	 *            the throwable to log
	 */
	void logError(Throwable throwable);

	/**
	 * Indicates whether lifecycle informations (like the fact that the compiler has done compiling resources,
	 * that the indexer indexed content...) should be displayed or not.
	 * 
	 * @param shouldDisplayLifecycleInformations
	 *            if true, these informations will be displayed by the logger. If not, they will not.
	 */
	void setDisplayLifecycleInformations(boolean shouldDisplayLifecycleInformations);

	public enum LogType {
		/**
		 * Indicates that a critical error occurred (should be displayed in both error log and pop-up).
		 */
		CRITICAL_ERROR,
		/**
		 * Indicates that a standard error occured (should be displayed in error log).
		 */
		ERROR,
		/**
		 * Indicates that a warning should be displayed.
		 */
		WARNING,
		/**
		 * Indicates that an information should be displayed.
		 */
		INFO,
		/**
		 * Information relative to Intent clients lifecycle (only displayed in debug mode).
		 */
		LIFECYCLE
	}
}
