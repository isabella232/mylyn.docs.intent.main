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
package org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.mylyn.docs.intent.client.synchronizer.internal.contribution.ISynchronizerExtensionDescriptor;

/**
 * Registry containing all Lock Strategy extensions that have been parsed from the
 * {@link org.eclipse.mylyn.docs.intent.client.synchronizer.internal.contribution.ISynchronizerExtensionRegistryListener
 * ; #SYNCHRONIZER_EXTENSION_POINT} extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class ISynchronizerExtensionRegistry {

	/**
	 * The registered {@link ISaveDialogExtension}s.
	 */
	private static final Map<ISynchronizerExtension, Collection<ISynchronizerExtensionDescriptor>> EXTENSIONS = Maps
			.newHashMap();

	/**
	 * Utility classes don't need a default constructor.
	 */
	private ISynchronizerExtensionRegistry() {

	}

	/**
	 * Adds an extension to the registry, with the given behavior.
	 * 
	 * @param extension
	 *            The extension that is to be added to the registry
	 */
	public static void addExtension(ISynchronizerExtensionDescriptor extension) {
		ISynchronizerExtension synchronizerExtension = extension.getSynchronizerExtension();
		if (EXTENSIONS.get(synchronizerExtension) == null) {
			EXTENSIONS.put(synchronizerExtension, new HashSet<ISynchronizerExtensionDescriptor>());
		}
		EXTENSIONS.get(synchronizerExtension).add(extension);
	}

	/**
	 * Removes all extensions from the registry. This will be called at plugin stopping.
	 */
	public static void clearRegistry() {
		EXTENSIONS.clear();
	}

	/**
	 * Returns a copy of the registered extensions list.
	 * 
	 * @return A copy of the registered extensions list.
	 */
	public static Collection<ISynchronizerExtensionDescriptor> getRegisteredExtensions() {
		Set<ISynchronizerExtensionDescriptor> registeredExtensions = Sets.newHashSet();
		for (Collection<ISynchronizerExtensionDescriptor> extensions : EXTENSIONS.values()) {
			registeredExtensions.addAll(extensions);
		}
		return registeredExtensions;
	}

	/**
	 * Returns all the registered synchronizer extensions defined for the given uri.
	 * 
	 * @param uri
	 *            the URI of the element
	 * @return all the registered synchronizer extensions defined for the given uri
	 */
	public static Collection<ISynchronizerExtension> getSynchronizerExtensions(URI uri) {
		Set<ISynchronizerExtension> registeredExtensions = Sets.newHashSet();
		for (Collection<ISynchronizerExtensionDescriptor> extensions : EXTENSIONS.values()) {
			for (ISynchronizerExtensionDescriptor descriptor : extensions) {
				if (descriptor.getSynchronizerExtension().isExtensionFor(uri)) {
					registeredExtensions.add(descriptor.getSynchronizerExtension());
				}
			}
		}
		return registeredExtensions;
	}

	/**
	 * Removes a phantom from the registry.
	 * 
	 * @param extensionClassName
	 *            Qualified class name of the sync element which corresponding phantom is to be removed from
	 *            the registry.
	 */
	public static void removeExtension(String extensionClassName) {
		for (ISynchronizerExtensionDescriptor extension : getRegisteredExtensions()) {
			if (extension.getExtensionClassName().equals(extensionClassName)) {
				EXTENSIONS.get(extension).clear();
			}
		}
	}

}
