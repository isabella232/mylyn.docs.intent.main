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
package org.eclipse.mylyn.docs.intent.client.ui.internal.logger;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger;
import org.eclipse.swt.widgets.Display;

/**
 * An {@link IIntentLogger} that logs issues in the Eclipse Error log.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class EclipseBasedIntentLogger implements IIntentLogger {

	/**
	 * The {@link ILog} to delegate logging to.
	 */
	private ILog delegateLogger;

	/**
	 * Indicates whether lifecycle informations (like the fact that the compiler has done compiling resources,
	 * that the indexer indexed content...) should be displayed or not.
	 */
	private boolean shouldDisplayLifecycleInformations;

	/**
	 * Default constructor.
	 * 
	 * @param delegateLogger
	 *            the logger to delegate to
	 */
	public EclipseBasedIntentLogger() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger#log(org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType,
	 *      java.lang.String)
	 */
	public void log(LogType logType, String message) {
		log(logType, message, null);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger#log(org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType,
	 *      java.lang.String, java.lang.Throwable)
	 */
	public void log(LogType logType, String message, Throwable exception) {
		boolean shouldLog = true;
		int severity = 0;
		switch (logType) {
			case CRITICAL_ERROR:
				severity = IStatus.ERROR;
				MessageDialog.openError(Display.getCurrent().getActiveShell(), "Intent - An error occured",
						message);
			case ERROR:
				severity = IStatus.ERROR;
				break;
			case WARNING:
				severity = IStatus.WARNING;
				break;
			case INFO:
				severity = IStatus.INFO;
				break;
			case LIFECYCLE:
				shouldLog = shouldDisplayLifecycleInformations;
				severity = IStatus.INFO;
				break;
			default:
				break;
		}
		if (shouldLog) {
			IStatus status = null;
			if (exception != null) {
				status = new Status(severity, IntentEditorActivator.EDITOR_ID, message, exception);
			} else {
				status = new Status(severity, IntentEditorActivator.EDITOR_ID, message);
			}
			getLogger().log(status);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger#setDisplayLifecycleInformations(boolean)
	 */
	public void setDisplayLifecycleInformations(boolean shouldDisplayLifecycleInformations) {
		this.shouldDisplayLifecycleInformations = shouldDisplayLifecycleInformations;
	}

	private ILog getLogger() {
		if (this.delegateLogger == null) {
			this.delegateLogger = IntentEditorActivator.getDefault().getLog();
		}
		return this.delegateLogger;
	}

}
