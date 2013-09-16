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
package org.eclipse.mylyn.docs.intent.external.parser.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser;

/**
 * Describes an extension as contibuted to the
 * {@link IntentExternalParserContributionRegistryListener#INTENT_EXTERNAL_PARSER_CONTRIBUTION_EXTENSION_POINT}
 * extension point.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class IntentExternalParserContributionDescriptor {

	/**
	 * Name of the attribute corresponding to the contributed class's path.
	 */
	public static final String EXTERNAL_PARSER_CONTRIBUTION_CLASS_NAME = "class";

	/**
	 * Configuration element of this descriptor .
	 */
	private final IConfigurationElement element;

	/**
	 * The path of the contributed class.
	 */
	private String extensionClassName;

	/**
	 * The {@link IExternalParser} described by this descriptor.
	 */
	private IExternalParser extension;

	/**
	 * Instantiates a descriptor with all information.
	 * 
	 * @param configuration
	 *            Configuration element from which to create this descriptor.
	 */
	public IntentExternalParserContributionDescriptor(IConfigurationElement configuration) {
		element = configuration;
		extensionClassName = configuration.getAttribute(EXTERNAL_PARSER_CONTRIBUTION_CLASS_NAME);
	}

	/**
	 * Creates an instance of this descriptor's {@link IExternalParser} .
	 * 
	 * @return A new instance of this descriptor's {@link IExternalParser}.
	 */
	public IExternalParser createExternalParserContribution() {
		try {
			extension = (IExternalParser)element
					.createExecutableExtension(EXTERNAL_PARSER_CONTRIBUTION_CLASS_NAME);
		} catch (CoreException e) {
			IntentLogger.getInstance().log(LogType.ERROR,
					"Could not create external parser manager contribution " + extensionClassName, e);
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
