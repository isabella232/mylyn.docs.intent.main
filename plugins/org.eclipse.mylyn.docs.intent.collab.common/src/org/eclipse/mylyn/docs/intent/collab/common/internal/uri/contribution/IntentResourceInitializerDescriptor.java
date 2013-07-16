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
package org.eclipse.mylyn.docs.intent.collab.common.internal.uri.contribution;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.mylyn.docs.intent.collab.common.uri.IIntentResourceInitializer;

/**
 * Describes a extension as contributed to the
 * {@link IntentResourceInitializerRegistryListener#INITIALIZER_EXTENSION_POINT} extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentResourceInitializerDescriptor {

	/**
	 * Name of the attribute corresponding to the contributed class's path.
	 */
	public static final String INITIALIZER_CONTRIBUTED_CLASS_NAME = "class";

	/**
	 * Name of the attribute corresponding to the file extension on which this contribution should apply.
	 */
	private static final String INITIALIZER_CONTRIBUTED_FILE_EXTENSION = "file_extension";

	/**
	 * Configuration element of this descriptor .
	 */
	private final IConfigurationElement element;

	/**
	 * The path of the contributed class.
	 */
	private String extensionClassName;

	/**
	 * The {@link IIntentResourceInitializer} described by this descriptor.
	 */
	private IIntentResourceInitializer extension;

	/**
	 * The file extension supported by this extension.
	 */
	private String fileExtension;

	/**
	 * Instantiates a descriptor with all information.
	 * 
	 * @param configuration
	 *            Configuration element from which to create this descriptor.
	 */
	public IntentResourceInitializerDescriptor(IConfigurationElement configuration) {
		element = configuration;
		extensionClassName = configuration.getAttribute(INITIALIZER_CONTRIBUTED_CLASS_NAME);
		fileExtension = configuration.getAttribute(INITIALIZER_CONTRIBUTED_FILE_EXTENSION);
	}

	/**
	 * Returns the fully qualified name of the contributed class.
	 * 
	 * @return the fully qualified name of the contributed class
	 */
	public Object getExtensionClassName() {
		return extensionClassName;
	}

	/**
	 * Returns the file extension supported by this extension.
	 * 
	 * @return the file extension supported by this extension
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * Creates an instance of this descriptor's {@link IIntentResourceInitializer} .
	 * 
	 * @return A new instance of this descriptor's {@link IIntentResourceInitializer}.
	 */
	public IIntentResourceInitializer getIntentResourceInitializer() {
		if (extension == null) {
			try {
				extension = (IIntentResourceInitializer)element
						.createExecutableExtension(INITIALIZER_CONTRIBUTED_CLASS_NAME);
			} catch (CoreException e) {
				// TODO LOG THIS ERROR USING TOP-LEVEL LOGGER
			}
		}
		return extension;
	}

}
