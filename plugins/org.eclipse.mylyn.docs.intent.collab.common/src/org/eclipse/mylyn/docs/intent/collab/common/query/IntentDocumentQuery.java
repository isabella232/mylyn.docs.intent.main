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
package org.eclipse.mylyn.docs.intent.collab.common.query;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;

/**
 * An utility class allowing to query the {@link IntentDocument}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentDocumentQuery extends AbstractIntentQuery {

	private IntentDocument intentDocument;

	/**
	 * Creates the query.
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the repository.
	 */
	public IntentDocumentQuery(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * Returns the {@link IntentDocument} of the current repository. If none find, creates it.
	 * 
	 * @return the {@link IntentDocument} of the current repository. If none find, creates it
	 */
	public IntentDocument getOrCreateIntentDocument() {
		if (intentDocument == null) {
			Resource resource = repositoryAdapter.getResource(IntentLocations.INTENT_INDEX);
			if (resource.getContents().isEmpty()) {
				resource.getContents().add(IntentDocumentFactory.eINSTANCE.createIntentDocument());
			}
			intentDocument = (IntentDocument)resource.getContents().get(0);
		}
		return intentDocument;
	}

}
