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
package org.eclipse.mylyn.docs.intent.bridge.java.util;

import org.eclipse.emf.common.util.URI;

/**
 * Utility class providing facilities for the Java bridge.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class JavaBridgeUtils {

	/**
	 * Private constructor.
	 */
	private JavaBridgeUtils() {

	}

	/**
	 * Indicates if the given {@link URI} is handled by the java bridge.
	 * 
	 * @param uri
	 *            the URI to inspect
	 * @return true if the given {@link URI} is handled by the java bridge
	 */
	public static boolean isHandledByJavaBridge(URI uri) {
		return "java".equals(uri.fileExtension());
	}
}
