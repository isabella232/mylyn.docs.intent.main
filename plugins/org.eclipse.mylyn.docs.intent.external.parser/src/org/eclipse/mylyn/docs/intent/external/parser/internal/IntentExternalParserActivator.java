/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.external.parser.internal;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class IntentExternalParserActivator extends Plugin {

	/**
	 * External parser.
	 */
	private IntentExternalParserContributionRegistryListener externalParserContributionsRegistryListener = new IntentExternalParserContributionRegistryListener();

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		// Initializing registry listener for all extension points
		externalParserContributionsRegistryListener.init();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		// Disposing registry listener for all extension points
		externalParserContributionsRegistryListener.dispose();
		externalParserContributionsRegistryListener = null;
		super.stop(context);
	}

}
