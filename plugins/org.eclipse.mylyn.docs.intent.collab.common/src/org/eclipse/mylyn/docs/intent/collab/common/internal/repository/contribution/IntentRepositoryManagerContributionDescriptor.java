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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.common.repository.contribution.IntentRepositoryManagerContribution;

/**
 * Describes an extension as contibuted to the
 * {@link IntentRepositoryManagerContributionRegistryListener#INTENT_REPOSITORY_MANAGER_CONTRIBUTION_EXTENSION_POINT}
 * extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentRepositoryManagerContributionDescriptor {

	/**
	 * Name of the attribute corresponding to the contributed class's path.
	 */
	public static final String REPOSITORY_MANAGER_CONTRIBUTION_CLASS_NAME = "class";

	/**
	 * Configuration element of this descriptor .
	 */
	private final IConfigurationElement element;

	/**
	 * The path of the contributed class.
	 */
	private String extensionClassName;

	/**
	 * The {@link IntentRepositoryManagerContribution} described by this descriptor.
	 */
	private IntentRepositoryManagerContribution extension;

	/**
	 * Instantiates a descriptor with all information.
	 * 
	 * @param configuration
	 *            Configuration element from which to create this descriptor.
	 */
	public IntentRepositoryManagerContributionDescriptor(IConfigurationElement configuration) {
		element = configuration;
		extensionClassName = configuration.getAttribute(REPOSITORY_MANAGER_CONTRIBUTION_CLASS_NAME);
	}

	/**
	 * Creates an instance of this descriptor's {@link IntentRepositoryManagerContribution} .
	 * 
	 * @return A new instance of this descriptor's {@link IntentRepositoryManagerContribution}.
	 */
	public IntentRepositoryManagerContribution createRepositoryManagerContribution() {
		try {
			extension = (IntentRepositoryManagerContribution)element
					.createExecutableExtension(REPOSITORY_MANAGER_CONTRIBUTION_CLASS_NAME);
		} catch (CoreException e) {
			IntentLogger.getInstance().log(LogType.ERROR,
					"Could not create repository manager contribution " + extensionClassName, e);
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
