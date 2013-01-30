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
package org.eclipse.mylyn.docs.intent.collab.common.uri;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * An extension allowing to initialize the content of an internal resource. For example, if user declares an
 * external reference @ref "intent:/intentProject/model.ecore", then the {@link IIntentResourceInitializer}
 * associated to the 'ecore' extension will be called and will create an empty EPackage.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public interface IIntentResourceInitializer {

	/**
	 * Returns the root to associate to the given empty resource.
	 * 
	 * @param emptyResourceURI
	 *            the {@link URI} of the empty resource to initialize
	 * @return the root to associate to the given empty resource (e.g. an EPackage for an ecore resource)
	 */
	EObject getInitialContent(URI emptyResourceURI);
}
