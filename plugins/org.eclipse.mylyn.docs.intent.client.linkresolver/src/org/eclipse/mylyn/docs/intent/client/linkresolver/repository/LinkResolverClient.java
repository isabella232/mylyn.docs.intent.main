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
package org.eclipse.mylyn.docs.intent.client.linkresolver.repository;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification;

public class LinkResolverClient extends AbstractRepositoryClient {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient#createNotificationJob(org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification)
	 */
	@Override
	protected Job createNotificationJob(RepositoryChangeNotification notification) {
		return new LinkResolverJob(repositoryObjectHandler.getRepositoryAdapter());
	}

}
