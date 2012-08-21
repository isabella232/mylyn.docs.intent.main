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
package org.eclipse.mylyn.docs.intent.collab.common.internal.logger;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger;

/**
 * Stores the Intent loggers.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentLoggerRegistry {

	/**
	 * All the registered loggers.
	 */
	private static final Map<IntentLoggerDescriptor, IIntentLogger> DECLARED_LOGGERS = Maps
			.newLinkedHashMap();

	/**
	 * Utility classes don't need a default constructor.
	 */
	private IntentLoggerRegistry() {
	}

	/**
	 * Returns all declared loggers (instanciate the logger if needed).
	 * 
	 * @return the declared loggers
	 */
	public static Collection<IIntentLogger> getDeclaredLoggers() {
		for (Entry<IntentLoggerDescriptor, IIntentLogger> descriptorToLogger : DECLARED_LOGGERS.entrySet()) {
			if (descriptorToLogger.getValue() == null) {
				descriptorToLogger.setValue(descriptorToLogger.getKey().createLogger());
			}
		}
		return DECLARED_LOGGERS.values();
	}

	/**
	 * Registers the given logger descriptor.
	 * 
	 * @param intentLoggerDescriptor
	 *            the logger descriptor to registor
	 */
	public static void addLogger(IntentLoggerDescriptor intentLoggerDescriptor) {
		DECLARED_LOGGERS.put(intentLoggerDescriptor, null);
	}

	/**
	 * Removes a phantom from the registry.
	 * 
	 * @param extensionClassName
	 *            Qualified class name of the sync element which corresponding phantom is to be removed from
	 *            the registry.
	 */
	public static void removeExtension(String extensionClassName) {
		for (IntentLoggerDescriptor extension : DECLARED_LOGGERS.keySet()) {
			if (extension.getExtensionClassName().equals(extensionClassName)) {
				DECLARED_LOGGERS.remove(extension);
			}
		}
	}

	/**
	 * Clears all registered loggers.
	 */
	public static void clearContributedLoggers() {
		DECLARED_LOGGERS.clear();

	}
}
