/*******************************************************************************
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.collab.ide.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * Custom implementation enabling use of XMI ids.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class RepoModelResource extends XMIResourceImpl {

	/**
	 * Constructor.
	 */
	public RepoModelResource() {
		super();
	}

	/**
	 * Creates a resource from the given URI.
	 * 
	 * @param uri
	 *            the resource uri
	 */
	public RepoModelResource(URI uri) {
		super(uri);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#useUUIDs()
	 */
	protected boolean useUUIDs() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#setModified(boolean)
	 */
	public void setModified(boolean isModified) {
		// Do nothing: never used in case of a repository resource
	}
}
