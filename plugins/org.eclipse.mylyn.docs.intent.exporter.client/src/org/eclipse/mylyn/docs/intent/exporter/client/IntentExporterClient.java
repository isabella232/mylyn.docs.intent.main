package org.eclipse.mylyn.docs.intent.exporter.client;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.mylyn.docs.intent.collab.common.query.IntentDocumentQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;

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

/**
 * An {@link AbstractRepositoryClient} which exports the documentation any time an element of the
 * {@link IntentDocument} is modified.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentExporterClient extends AbstractRepositoryClient {

	/**
	 * Default constructor.
	 * 
	 * @param repository
	 *            the {@link Repository}
	 */
	public IntentExporterClient(Repository repository) {
		super(repository);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient#createNotificationJob(org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification)
	 */
	@Override
	protected Job createNotificationJob(RepositoryChangeNotification notification) {
		String htmlPreviewLocation = getRepositoryObjectHandler().getRepositoryAdapter().getRepository()
				.getRepositoryLocation()
				+ "generated/";
		return new IntentExporterJob(new IntentDocumentQuery(getRepositoryObjectHandler()
				.getRepositoryAdapter()).getOrCreateIntentDocument(), htmlPreviewLocation);
	}
}
