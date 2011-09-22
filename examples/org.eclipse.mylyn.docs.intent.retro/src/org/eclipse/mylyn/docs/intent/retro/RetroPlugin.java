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
package org.eclipse.mylyn.docs.intent.retro;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class RetroPlugin implements BundleActivator {

	public static RetroPlugin INSTANCE;

	public RetroPlugin() {
		INSTANCE = this;
	}

	public void start(BundleContext context) throws Exception {
	}

	public void stop(BundleContext context) throws Exception {
	}

}
