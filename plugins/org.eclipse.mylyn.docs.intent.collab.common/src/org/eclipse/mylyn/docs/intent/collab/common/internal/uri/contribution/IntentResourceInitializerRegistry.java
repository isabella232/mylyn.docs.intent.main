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

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.mylyn.docs.intent.collab.common.uri.IIntentResourceInitializer;

/**
 * Registry containing all {@link IIntentResourceInitializer}s that have been parsed from the
 * {@link IntentResourceInitializerRegistryListener#INITIALIZER_EXTENSION_POINT} extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentResourceInitializerRegistry {

	/**
	 * The registered {@link IIntentResourceInitializer}s.
	 */
	private static final Map<IIntentResourceInitializer, Collection<IntentResourceInitializerDescriptor>> EXTENSIONS = Maps
			.newHashMap();

	/**
	 * Utility classes don't need a default constructor.
	 */
	private IntentResourceInitializerRegistry() {

	}

	/**
	 * Adds an extension to the registry, with the given behavior.
	 * 
	 * @param extension
	 *            The extension that is to be added to the registry
	 */
	public static void addExtension(IntentResourceInitializerDescriptor extension) {
		IIntentResourceInitializer synchronizerExtension = extension.getIntentResourceInitializer();
		if (EXTENSIONS.get(synchronizerExtension) == null) {
			EXTENSIONS.put(synchronizerExtension, new HashSet<IntentResourceInitializerDescriptor>());
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
	public static Collection<IntentResourceInitializerDescriptor> getRegisteredExtensions() {
		Set<IntentResourceInitializerDescriptor> registeredExtensions = Sets.newHashSet();
		for (Collection<IntentResourceInitializerDescriptor> extensions : EXTENSIONS.values()) {
			registeredExtensions.addAll(extensions);
		}
		return registeredExtensions;
	}

	/**
	 * Returns all the registered {@link IIntentResourceInitializer} defined for the given file extension.
	 * 
	 * @param fileExtension
	 *            the file extension of the empty resource to initialize
	 * @return all the registered {@link IIntentResourceInitializer} defined for the given file extension
	 */
	public static Collection<IIntentResourceInitializer> getIntentResourceInitializers(String fileExtension) {
		Set<IIntentResourceInitializer> registeredExtensions = Sets.newHashSet();
		for (Collection<IntentResourceInitializerDescriptor> extensions : EXTENSIONS.values()) {
			for (IntentResourceInitializerDescriptor descriptor : extensions) {
				if (fileExtension.equals(descriptor.getFileExtension())) {
					registeredExtensions.add(descriptor.getIntentResourceInitializer());
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
		for (IntentResourceInitializerDescriptor extension : getRegisteredExtensions()) {
			if (extension.getExtensionClassName().equals(extensionClassName)) {
				EXTENSIONS.get(extension).clear();
			}
		}
	}

}
