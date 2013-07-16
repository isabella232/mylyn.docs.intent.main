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
package org.eclipse.mylyn.docs.intent.client.ui.internal.quickfix.provider;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.AbstractIntentFix;
import org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.provider.IntentQuickFixProvider;

/**
 * Registry containing all Lock Strategy extensions that have been parsed from the
 * {@link IntentQuickFixProviderRegistryListener#RENDERER_EXTENSION_POINT} extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentQuickFixProviderRegistry {

	/**
	 * The registered {@link ISaveDialogExtension}s.
	 */
	private static final Map<IntentQuickFixProvider, Collection<IntentQuickFixProviderDescriptor>> EXTENSIONS = Maps
			.newHashMap();

	/**
	 * Utility classes don't need a default constructor.
	 */
	private IntentQuickFixProviderRegistry() {

	}

	/**
	 * Adds an extension to the registry, with the given behavior.
	 * 
	 * @param extensionDescriptor
	 *            The extension that is to be added to the registry
	 */
	public static void addExtension(IntentQuickFixProviderDescriptor extensionDescriptor) {
		IntentQuickFixProvider extension = extensionDescriptor.getIntentQuickFixProvider();
		if (EXTENSIONS.get(extension) == null) {
			EXTENSIONS.put(extension, new HashSet<IntentQuickFixProviderDescriptor>());
		}
		EXTENSIONS.get(extension).add(extensionDescriptor);
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
	public static Collection<IntentQuickFixProviderDescriptor> getRegisteredExtensions() {
		Set<IntentQuickFixProviderDescriptor> registeredExtensions = Sets.newHashSet();
		for (Collection<IntentQuickFixProviderDescriptor> extensions : EXTENSIONS.values()) {
			registeredExtensions.addAll(extensions);
		}
		return registeredExtensions;
	}

	/**
	 * Returns all the registered {@link IntentFix}es that can be applied on the given
	 * {@link IntentAnnotation},.
	 * 
	 * @param intentAnnotation
	 *            the {@link IntentAnnotation} to apply a fix on
	 * @return all the registered {@link IntentFix}es that can be applied on the given
	 *         {@link IntentAnnotation}
	 */
	public static Collection<AbstractIntentFix> getAppliableIntentFixes(IntentAnnotation intentAnnotation) {
		List<AbstractIntentFix> quickFixes = Lists.newArrayList();

		for (Collection<IntentQuickFixProviderDescriptor> extensions : EXTENSIONS.values()) {
			for (IntentQuickFixProviderDescriptor descriptor : extensions) {
				if (descriptor.getIntentQuickFixProvider().canCreateQuickFix(intentAnnotation)) {
					quickFixes.add(descriptor.getIntentQuickFixProvider().createQuickFix(intentAnnotation));
				}
			}
		}
		return quickFixes;
	}

	/**
	 * Removes a phantom from the registry.
	 * 
	 * @param extensionClassName
	 *            Qualified class name of the sync element which corresponding phantom is to be removed from
	 *            the registry.
	 */
	public static void removeExtension(String extensionClassName) {
		for (IntentQuickFixProviderDescriptor extension : getRegisteredExtensions()) {
			if (extension.getExtensionClassName().equals(extensionClassName)) {
				EXTENSIONS.get(extension.getIntentQuickFixProvider()).clear();
			}
		}
	}

}
