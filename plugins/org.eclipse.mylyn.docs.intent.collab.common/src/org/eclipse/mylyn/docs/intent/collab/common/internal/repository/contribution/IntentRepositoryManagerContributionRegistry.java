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
package org.eclipse.mylyn.docs.intent.collab.common.internal.repository.contribution;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.mylyn.docs.intent.collab.common.repository.contribution.IntentRepositoryManagerContribution;

/**
 * Stores the repository manager contributions.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentRepositoryManagerContributionRegistry {

	/**
	 * All the registered IntentRepositoryManagerContributions.
	 */
	private static final Map<IntentRepositoryManagerContributionDescriptor, IntentRepositoryManagerContribution> DECLARED_CONTRIBUTIONS = Maps
			.newLinkedHashMap();

	/**
	 * Utility classes don't need a default constructor.
	 */
	private IntentRepositoryManagerContributionRegistry() {

	}

	/**
	 * Returns all declared Repository Manager Contributions (instanciates it if needed).
	 * 
	 * @return all the declared contributions
	 */
	public static Collection<IntentRepositoryManagerContribution> getRepositoryManagerContributions() {
		for (Entry<IntentRepositoryManagerContributionDescriptor, IntentRepositoryManagerContribution> descriptorToContribution : DECLARED_CONTRIBUTIONS
				.entrySet()) {
			if (descriptorToContribution.getValue() == null) {
				descriptorToContribution.setValue(descriptorToContribution.getKey()
						.createRepositoryManagerContribution());
			}
		}
		return DECLARED_CONTRIBUTIONS.values();
	}

	/**
	 * Registers the given repository manager contribution descriptor.
	 * 
	 * @param descriptor
	 *            the repository manager contribution descriptor to register
	 */
	public static void addRepositoryManagerContribution(
			IntentRepositoryManagerContributionDescriptor descriptor) {
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
		for (IntentRepositoryManagerContributionDescriptor extension : DECLARED_CONTRIBUTIONS.keySet()) {
			if (extension.getExtensionClassName().equals(extensionClassName)) {
				DECLARED_CONTRIBUTIONS.remove(extension);
			}
		}
	}

	/**
	 * Clears all registered IntentRepositoryManagerContributions.
	 */
	public static void clearContributedRepositoryManagerContributions() {
		DECLARED_CONTRIBUTIONS.clear();

	}
}
