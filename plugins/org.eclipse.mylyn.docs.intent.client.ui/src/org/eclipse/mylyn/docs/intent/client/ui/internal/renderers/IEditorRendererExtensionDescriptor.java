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
package org.eclipse.mylyn.docs.intent.client.ui.internal.renderers;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;

/**
 * Describes a extension as contributed to the
 * {@link IEditorRendererExtensionRegistryListener#RENDERER_EXTENSION_POINT} extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IEditorRendererExtensionDescriptor {

	/**
	 * Name of the attribute corresponding to the contributed class's path.
	 */
	public static final String RENDERER_EXTENSION_CONTRIBUTED_CLASS_NAME = "class";

	/**
	 * Configuration element of this descriptor .
	 */
	private final IConfigurationElement element;

	/**
	 * The path of the contributed class.
	 */
	private String extensionClassName;

	/**
	 * The {@link IEditorRendererExtension} described by this descriptor.
	 */
	private IEditorRendererExtension extension;

	/**
	 * Instantiates a descriptor with all information.
	 * 
	 * @param configuration
	 *            Configuration element from which to create this descriptor.
	 */
	public IEditorRendererExtensionDescriptor(IConfigurationElement configuration) {
		element = configuration;
		extensionClassName = configuration.getAttribute(RENDERER_EXTENSION_CONTRIBUTED_CLASS_NAME);
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
	 * Creates an instance of this descriptor's {@link IEditorRendererExtension} .
	 * 
	 * @return A new instance of this descriptor's {@link IEditorRendererExtension}.
	 */
	public IEditorRendererExtension getSynchronizerExtension() {
		if (extension == null) {
			try {
				extension = (IEditorRendererExtension)element
						.createExecutableExtension(RENDERER_EXTENSION_CONTRIBUTED_CLASS_NAME);
			} catch (CoreException e) {
				IntentLogger.getInstance().logError(e);
			}
		}
		return extension;
	}

}
