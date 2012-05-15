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
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentLoggerRegistry {

	/**
	 * All the registered loggers.
	 */
	private static final Map<IntentLoggerDescriptor, IIntentLogger> declaredLoggers = Maps.newLinkedHashMap();

	/**
	 * Utility classes don't need a default constructor.
	 */
	private IntentLoggerRegistry() {

	}

	/**
	 * Returns all declared loggers (instanciate the logger if needed).
	 */
	public static Collection<IIntentLogger> getDeclaredLoggers() {
		for (Entry<IntentLoggerDescriptor, IIntentLogger> descriptorToLogger : declaredLoggers.entrySet()) {
			if (descriptorToLogger.getValue() == null) {
				descriptorToLogger.setValue(descriptorToLogger.getKey().createLogger());
			}
		}
		return declaredLoggers.values();
	}

	/**
	 * Registers the given logger descriptor.
	 * 
	 * @param intentLoggerDescriptor
	 *            the logger descriptor to registor
	 */
	public static void addLogger(IntentLoggerDescriptor intentLoggerDescriptor) {
		declaredLoggers.put(intentLoggerDescriptor, null);
	}

	/**
	 * Removes a phantom from the registry.
	 * 
	 * @param extensionClassName
	 *            Qualified class name of the sync element which corresponding phantom is to be removed from
	 *            the registry.
	 */
	public static void removeExtension(String extensionClassName) {
		for (IntentLoggerDescriptor extension : declaredLoggers.keySet()) {
			if (extension.getExtensionClassName().equals(extensionClassName)) {
				declaredLoggers.remove(extension);
			}
		}
	}

	/**
	 * Clears all registered loggers.
	 */
	public static void clearContributedLoggers() {
		declaredLoggers.clear();

	}
}
