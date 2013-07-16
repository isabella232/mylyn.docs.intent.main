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
package org.eclipse.mylyn.docs.intent.client.synchronizer.internal;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtensionRegistry;
import org.eclipse.mylyn.docs.intent.client.synchronizer.internal.contribution.ISynchronizerExtensionRegistryListener;
import org.osgi.framework.BundleContext;

/**
 * The Synchronizer plugin.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class SynchronizerPlugin extends Plugin {

	/**
	 * Registry listener to register when plugin starts.
	 */
	private ISynchronizerExtensionRegistryListener registryListener = new ISynchronizerExtensionRegistryListener();

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		// Initializing registry listener for extension points
		final IExtensionRegistry registry = Platform.getExtensionRegistry();
		registry.addRegistryChangeListener(registryListener,
				ISynchronizerExtensionRegistryListener.SYNCHRONIZER_EXTENSION_POINT);
		registryListener.parseInitialContributions();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		// Unregistering registry listeners
		final IExtensionRegistry registry = Platform.getExtensionRegistry();
		registry.removeRegistryChangeListener(registryListener);
		ISynchronizerExtensionRegistry.clearRegistry();

		super.stop(context);
	}
}
