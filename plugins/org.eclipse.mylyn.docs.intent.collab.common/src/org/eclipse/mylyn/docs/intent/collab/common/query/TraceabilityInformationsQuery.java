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
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;

/**
 * An utility class allowing to query the {@link TraceabilityIndex} to get useful traceability informations.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TraceabilityInformationsQuery extends AbstractIntentQuery {

	/**
	 * Creates the query.
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the repository.
	 */
	public TraceabilityInformationsQuery(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * Returns the traceability index of the current repository. If none find, creates it.
	 * 
	 * @return the traceability index of the current repository. If none find, creates it
	 */
	public TraceabilityIndex getOrCreateTraceabilityIndex() {
		final Resource traceabilityResource = repositoryAdapter
				.getResource(IntentLocations.TRACEABILITY_INFOS_INDEX_PATH);

		if (traceabilityResource.getContents().isEmpty()) {
			traceabilityResource.getContents().add(CompilerFactory.eINSTANCE.createTraceabilityIndex());
		}
		return (TraceabilityIndex)traceabilityResource.getContents().get(0);
	}
}
