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
package org.eclipse.mylyn.docs.intent.external.parser;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser;
import org.eclipse.mylyn.docs.intent.external.parser.internal.IntentExternalParserContributionDescriptor;

/**
 * Stores the external parser contributions.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public final class IntentExternalParserContributionRegistry {

	/**
	 * All the registered IntentExternalParserContributions.
	 */
	private static final Map<IntentExternalParserContributionDescriptor, IExternalParser> DECLARED_CONTRIBUTIONS = Maps
			.newLinkedHashMap();

	/**
	 * Utility classes don't need a default constructor.
	 */
	private IntentExternalParserContributionRegistry() {

	}

	/**
	 * Returns all declared external parser Contributions (instantiates it if needed).
	 * 
	 * @return all the declared contributions
	 */
	public static Collection<IExternalParser> getExternalParserContributions() {
		for (Entry<IntentExternalParserContributionDescriptor, IExternalParser> descriptorToContribution : DECLARED_CONTRIBUTIONS
				.entrySet()) {
			if (descriptorToContribution.getValue() == null) {
				descriptorToContribution.setValue(descriptorToContribution.getKey()
						.createExternalParserContribution());
			}
		}
		return DECLARED_CONTRIBUTIONS.values();
	}

	/**
	 * Registers the given external parser contribution descriptor.
	 * 
	 * @param descriptor
	 *            the external parser contribution descriptor to register
	 */
	public static void addExternalParserContribution(IntentExternalParserContributionDescriptor descriptor) {
		DECLARED_CONTRIBUTIONS.put(descriptor, null);
	}

	/**
	 * Removes a phantom from the registry.
	 * 
	 * @param extensionClassName
	 *            Qualified class name of the sync element which corresponding phantom is to be removed from
	 *            the registry.
	 */
	public static void removeExtension(String extensionClassName) {
		for (IntentExternalParserContributionDescriptor extension : DECLARED_CONTRIBUTIONS.keySet()) {
			if (extension.getExtensionClassName().equals(extensionClassName)) {
				DECLARED_CONTRIBUTIONS.remove(extension);
			}
		}
	}

	/**
	 * Clears all registered IntentExternalParserContributions.
	 */
	public static void clearContributedExternalParserContributions() {
		DECLARED_CONTRIBUTIONS.clear();
	}
}
