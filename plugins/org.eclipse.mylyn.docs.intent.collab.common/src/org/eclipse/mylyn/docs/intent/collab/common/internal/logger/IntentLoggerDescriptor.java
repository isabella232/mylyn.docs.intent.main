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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;

/**
 * Describes an extension as contibuted to the
 * {@link IntentLoggerRegistryListener#INTENT_LOGGER_EXTENSION_POINT} extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentLoggerDescriptor {

	/**
	 * Name of the attribute corresponding to the contributed class's path.
	 */
	public static final String LOGGER_CLASS_NAME = "class";

	/**
	 * Configuration element of this descriptor .
	 */
	private final IConfigurationElement element;

	/**
	 * The path of the contributed class.
	 */
	private String extensionClassName;

	/**
	 * The {@link IIntentLogger} described by this descriptor.
	 */
	private IIntentLogger extension;

	/**
	 * Instantiates a descriptor with all information.
	 * 
	 * @param configuration
	 *            Configuration element from which to create this descriptor.
	 */
	public IntentLoggerDescriptor(IConfigurationElement configuration) {
		element = configuration;
		extensionClassName = configuration.getAttribute(LOGGER_CLASS_NAME);
	}

	/**
	 * Creates an instance of this descriptor's {@link IIntentLogger} .
	 * 
	 * @return A new instance of this descriptor's {@link IIntentLogger}.
	 */
	public IIntentLogger createLogger() {
		try {
			extension = (IIntentLogger)element.createExecutableExtension(LOGGER_CLASS_NAME);
		} catch (CoreException e) {
			IntentLogger.getInstance().log(LogType.ERROR, "Could not create logger " + extensionClassName, e);
		}
		return extension;
	}

	/**
	 * Returns the fully qualified name of the contributed class.
	 * 
	 * @return the fully qualified name of the contributed class
	 */
	public String getExtensionClassName() {
		return extensionClassName;
	}
}
