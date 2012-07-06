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
package org.eclipse.mylyn.docs.intent.client.ui.cdo.repository;

import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;

/**
 * Structurer for a CDO repository containing Intent informations.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentRepositoryCDORestructurer implements RepositoryStructurer {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer#structure(org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter)
	 */

	public void structure(RepositoryAdapter repositoryAdapter) throws ReadOnlyException {
		// No need to structure the repository as CDO supports lazy-loading
	}
}
