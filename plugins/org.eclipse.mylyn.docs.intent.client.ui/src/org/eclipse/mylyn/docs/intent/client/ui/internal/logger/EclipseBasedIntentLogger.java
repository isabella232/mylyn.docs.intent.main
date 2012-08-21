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
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.swt.widgets.Display;

/**
 * An {@link IIntentLogger} that logs issues in the Eclipse Error log.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class EclipseBasedIntentLogger implements IIntentLogger, IPreferenceChangeListener {

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
	 */
	public EclipseBasedIntentLogger() {
		// Step 1: register a preference change listener so that if user decide to activate/deactive advanced
		// logging, this logger can be notified
		IEclipsePreferences node = InstanceScope.INSTANCE.getNode(IntentEditorActivator.getDefault()
				.getBundle().getSymbolicName());
		if (node != null) {
			node.addPreferenceChangeListener(this);

			// Step 2 : initializing the shouldDisplayLifecycleInformations according to preferences
			shouldDisplayLifecycleInformations = node.getBoolean(
					IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING, false);
		} else {
			getBundleLogger().log(
					new Status(IStatus.WARNING, IntentEditorActivator.EDITOR_ID,
							"Intent Logger: an error occured, logger will not react to preference changes"));
		}
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
			getBundleLogger().log(status);
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
	public void setDisplayLifecycleInformations(boolean value) {
		this.shouldDisplayLifecycleInformations = value;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener#preferenceChange(org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent)
	 */
	public void preferenceChange(PreferenceChangeEvent event) {
		if (IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING.equals(event.getKey())) {
			boolean newValue = false;
			if ("true".equals(event.getNewValue())) {
				newValue = true;
			}
			// If the advance logging preference changes, we notify the Root Intent logger
			IntentLogger.getInstance().setDisplayLifecycleInformations(newValue);
		}
	}

	/**
	 * Returns the bundle logger, initializes the field if necessary.
	 * 
	 * @return the bundle logger
	 */
	private ILog getBundleLogger() {
		if (this.delegateLogger == null) {
			this.delegateLogger = IntentEditorActivator.getDefault().getLog();
		}
		return this.delegateLogger;
	}
}
