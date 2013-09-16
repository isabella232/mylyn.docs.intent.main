/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.external.parser.client;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryObjectHandler;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.ReadWriteRepositoryObjectHandlerImpl;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.notification.typeListener.TypeNotificator;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.Notificator;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser;
import org.eclipse.mylyn.docs.intent.external.parser.internal.ExternalParserRepositoryClient;

/**
 * Creates external parser repository clients.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public final class ExternalParserCreator {

	/**
	 * CompilerCreator constructor.
	 */
	private ExternalParserCreator() {

	}

	/**
	 * Creates a {@link ExternalParserRepositoryClient}, in charge of calling contributed
	 * {@link IExternalParser}s when document changes.
	 * 
	 * @param repository
	 *            is the repository containing the modeling units to compile
	 * @param externalParserContribution
	 *            is the external parser contribution
	 * @throws RepositoryConnectionException
	 *             if a connection to the given repository cannot be established
	 * @return the created ExternalParserRepositoryClient
	 * @throws ReadOnlyException
	 *             if no sufficient rights to write on the repository
	 */
	public static ExternalParserRepositoryClient createParser(Repository repository,
			IExternalParser externalParserContribution) throws RepositoryConnectionException,
			ReadOnlyException {

		// Step 1: initialize the listened types
		Set<EStructuralFeature> listenedTypes = new LinkedHashSet<EStructuralFeature>();

		listenedTypes.add(IntentDocumentPackage.eINSTANCE.getIntentSection_DescriptionUnits());

		// Step 2: create the adapter and the handler for these types
		final RepositoryAdapter repositoryAdapter = repository.createRepositoryAdapter();
		repositoryAdapter.openSaveContext();

		RepositoryObjectHandler handler;
		handler = new ReadWriteRepositoryObjectHandlerImpl(repositoryAdapter);

		Notificator notificator = new TypeNotificator(listenedTypes);
		handler.addNotificator(notificator);

		// Step 3: create the external parser
		ExternalParserRepositoryClient externalParserClient = new ExternalParserRepositoryClient(repository,
				externalParserContribution);
		externalParserClient.addRepositoryObjectHandler(handler);

		return externalParserClient;
	}
}
