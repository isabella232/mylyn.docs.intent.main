package org.eclipse.mylyn.docs.intent.collab.common;
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
import org.eclipse.core.runtime.Plugin;
import org.eclipse.mylyn.docs.intent.collab.common.internal.logger.IntentLoggerRegistryListener;
import org.osgi.framework.BundleContext;

/**
 * Activator of the collab.common plugin.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentRepositoryActivator extends Plugin {

	private IntentLoggerRegistryListener loggerRegistryListener = new IntentLoggerRegistryListener();

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		// Initializing registry listener for the logger extension point
		loggerRegistryListener.init();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		// Initializing registry listener for the logger extension point
		loggerRegistryListener.dispose();

		super.stop(context);
	}
}
