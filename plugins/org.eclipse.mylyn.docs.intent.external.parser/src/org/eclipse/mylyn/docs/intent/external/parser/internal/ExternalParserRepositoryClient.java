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
package org.eclipse.mylyn.docs.intent.external.parser.internal;

import java.util.Collection;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser;

/**
 * In charge of communication between the repository and the external parsers ; launch a parsing operation
 * each time a modification on description units is detected.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class ExternalParserRepositoryClient extends AbstractRepositoryClient {
	/**
	 * External parser contribution.
	 */
	private Collection<IExternalParser> externalParserContributions;

	/**
	 * Constructor.
	 * 
	 * @param repository
	 *            the repository
	 * @param externalParserContributions
	 *            the contributed {@link IExternalParser}s to call when notified of changes on the document
	 */
	public ExternalParserRepositoryClient(Repository repository,
			Collection<IExternalParser> externalParserContributions) {
		super(repository);
		this.externalParserContributions = externalParserContributions;
		IntentLogger.getInstance().log(LogType.LIFECYCLE, "[External Parsers] Ready");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient#createNotificationJob(org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification)
	 */
	@Override
	protected Job createNotificationJob(RepositoryChangeNotification notification) {
		return new ExternalParserJob(this.repositoryObjectHandler, this.externalParserContributions);
	}

}
