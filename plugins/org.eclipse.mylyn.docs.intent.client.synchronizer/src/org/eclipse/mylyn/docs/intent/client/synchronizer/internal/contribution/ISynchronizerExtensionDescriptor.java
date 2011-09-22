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
package org.eclipse.mylyn.docs.intent.client.synchronizer.internal.contribution;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtension;

/**
 * Describes a extension as contributed to the
 * {@link ISynchronizerExtensionRegistryListener#SYNCHRONIZER_EXTENSION_POINT} extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ISynchronizerExtensionDescriptor {

	/**
	 * Name of the attribute corresponding to the contributed class's path.
	 */
	public static final String SYNCHRONIZER_EXTENSION_CONTRIBUTED_CLASS_NAME = "class";

	private static final String SYNCHRONIZER_EXTENSION_CONTRIBUTED_URI_SCHEME = "handled_uri_scheme";

	/**
	 * Configuration element of this descriptor .
	 */
	private final IConfigurationElement element;

	/**
	 * The path of the contributed class.
	 */
	private String extensionClassName;

	/**
	 * The {@link ISynchronizerExtension} described by this descriptor.
	 */
	private ISynchronizerExtension extension;

	/**
	 * The scheme supported by this extension.
	 */
	private String uriScheme;

	/**
	 * Instantiates a descriptor with all information.
	 * 
	 * @param configuration
	 *            Configuration element from which to create this descriptor.
	 */
	public ISynchronizerExtensionDescriptor(IConfigurationElement configuration) {
		element = configuration;
		extensionClassName = configuration.getAttribute(SYNCHRONIZER_EXTENSION_CONTRIBUTED_CLASS_NAME);
		uriScheme = configuration.getAttribute(SYNCHRONIZER_EXTENSION_CONTRIBUTED_URI_SCHEME);
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
	 * Returns the scheme supported by this extension. For example, "retro" will indicates that this extension
	 * is handling all URIs starting with "retro:/...".
	 * 
	 * @return the scheme supported by this extension
	 */
	public String getURIScheme() {
		return uriScheme;
	}

	/**
	 * Creates an instance of this descriptor's {@link ISynchronizerExtension} .
	 * 
	 * @return A new instance of this descriptor's {@link ISynchronizerExtension}.
	 */
	public ISynchronizerExtension getSynchronizerExtension() {
		if (extension == null) {
			try {
				extension = (ISynchronizerExtension)element
						.createExecutableExtension(SYNCHRONIZER_EXTENSION_CONTRIBUTED_CLASS_NAME);
			} catch (CoreException e) {
				// TODO LOG THIS ERROR USING TOP-LEVEL LOGGER
			}
		}
		return extension;
	}

}
