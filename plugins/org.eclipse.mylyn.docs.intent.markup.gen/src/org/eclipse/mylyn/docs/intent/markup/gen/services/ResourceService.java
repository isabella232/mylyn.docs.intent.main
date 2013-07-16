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
package org.eclipse.mylyn.docs.intent.markup.gen.services;

import org.eclipse.emf.ecore.EObject;

/**
 * Services on resources.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ResourceService {

	/**
	 * Returns the name of the resource containing the given {@link EObject}.
	 * 
	 * @param any
	 *            the {@link EObject}
	 * @return the name of the resource containing the given {@link EObject}
	 */
	public String eResourceName(EObject any) {
		return any.eResource().getURI().lastSegment();
	}
}
