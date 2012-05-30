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
package org.eclipse.mylyn.docs.intent.client.ui.test.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;

/**
 * A listener used to detect if an event happened or not on the repository.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class RepositoryListenerForTests implements ILogListener {

	/**
	 * Delay to wait before checking again that an event occurred.
	 */
	private static final int WAITING_STEP_DELAY = 200;

	/**
	 * Delay to wait before considering that an expected event never occurred.
	 */
	private static final long TIME_OUT_DELAY = 2000;

	/**
	 * A map associating each client Identifier (Indexer, Compiler...) with the messages sent by this client.
	 */
	private Map<String, Collection<String>> clientsMessages = Maps.newLinkedHashMap();

	/**
	 * Indicates whether this Repository listener is recording or not.
	 */
	private boolean isRecording;

	/**
	 * Removes all registered notifications and start listening to the repository.
	 */
	public void clearPreviousEntries() {
		isRecording = true;
		// Changing preferences : activating logging
		IEclipsePreferences node = InstanceScope.INSTANCE.getNode(IntentEditorActivator.getDefault()
				.getBundle().getSymbolicName());
		node.putBoolean(IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING, true);

		clientsMessages.clear();
		clientsMessages.put("Indexer", Sets.<String> newLinkedHashSet());
		clientsMessages.put("Compiler", Sets.<String> newLinkedHashSet());
		clientsMessages.put("Synchronizer", Sets.<String> newLinkedHashSet());
	}

	/**
	 * Notifies this listener that given status has been logged by a plug-in. The listener is free to retain
	 * or ignore this status.
	 * 
	 * @param status
	 *            the status being logged
	 * @param plugin
	 *            the plugin of the log which generated this event
	 */
	public void logging(IStatus status, String plugin) {
		if (isRecording) {
			String clientIdentifier = getClientIdentifier(status);
			if (clientIdentifier != null) {
				clientsMessages.get(clientIdentifier).add(
						status.getMessage().replaceFirst("[" + clientIdentifier + "]", "").trim());

			}
		}
	}

	/**
	 * Returns the client identifier associated to the given status (null if none found).
	 * 
	 * @param status
	 *            the status to analyses
	 * @return the client identifier (e.g. "Indexer", "Compiler"...) associated to the given status (null if
	 *         none found)
	 */
	private String getClientIdentifier(IStatus status) {
		for (String clientID : clientsMessages.keySet()) {
			if (status.getMessage().startsWith("[" + clientID + "]")) {
				return clientID;
			}
		}
		return null;
	}

	/**
	 * Waits for a message sent by the given client. Returns true if the message was sent, false if it did not
	 * after a certain delay.
	 * 
	 * @param clientIdentifier
	 *            the client identifier (e.g. "Indexer", "Compiler")
	 * @return true if the message was sent by the expected client, false if it did not after a certain delay
	 */
	public boolean waitForModificationOn(String clientIdentifier) {
		long startTime = System.currentTimeMillis();
		boolean timeOutDetected = false;
		try {
			while (clientsMessages.get(clientIdentifier).isEmpty() && !timeOutDetected) {
				Thread.sleep(WAITING_STEP_DELAY);
				timeOutDetected = System.currentTimeMillis() - startTime > TIME_OUT_DELAY;
			}
			Thread.sleep(WAITING_STEP_DELAY);
			return !timeOutDetected;
		} catch (InterruptedException e) {
			return false;
		}
	}

	/**
	 * Returns all messages sent by the client with the given identifier (e.g. "Compiler", "Synchronizer"...)
	 * since the last call to startRecording().
	 * 
	 * @param clientIdentifier
	 *            the client identifier (e.g. "Indexer", "Compiler")
	 * @return all messages sent by the client with the given identifier
	 */
	public Collection<String> getClientMessage(String clientIdentifier) {
		return clientsMessages.get(clientIdentifier);
	}

}
