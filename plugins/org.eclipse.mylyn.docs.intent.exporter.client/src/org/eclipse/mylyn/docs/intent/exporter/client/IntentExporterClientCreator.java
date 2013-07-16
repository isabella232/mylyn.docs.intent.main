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
package org.eclipse.mylyn.docs.intent.exporter.client;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.collab.common.query.IntentDocumentQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryObjectHandler;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.ReadWriteRepositoryObjectHandlerImpl;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.notification.elementList.ElementListNotificator;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.Notificator;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;

/**
 * Utility class used to create {@link IntentExporterClient}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentExporterClientCreator {

	/**
	 * Private constructor.
	 */
	private IntentExporterClientCreator() {
		
	}
	/**
	 * Creates an {@link IntentExporterClient} allowing to generate a preview of the intent document held by
	 * the given repository when it is modified.
	 * 
	 * @param repository
	 *            the repository
	 * @return an {@link IntentExporterClient} allowing to generate a preview of the intent document held by
	 *         the given repository when it is modified
	 * @throws ReadOnlyException
	 *             if repository cannot be accessed properly
	 */
	public static IntentExporterClient createIntentExporterClient(Repository repository)
			throws ReadOnlyException {
		// Step 1 : adapter creation
		final RepositoryAdapter repositoryAdapter = repository.createRepositoryAdapter();
		repositoryAdapter.openSaveContext();

		// Step 2 : creating the handler
		RepositoryObjectHandler handler = new ReadWriteRepositoryObjectHandlerImpl(repositoryAdapter);

		Set<EObject> intentDocument = new LinkedHashSet<EObject>();
		intentDocument.add(new IntentDocumentQuery(repositoryAdapter).getOrCreateIntentDocument());
		Notificator listenedElementsNotificator = new ElementListNotificator(intentDocument,
				repositoryAdapter);
		handler.addNotificator(listenedElementsNotificator);

		// Step 4 : launching the exporter client
		IntentExporterClient exporter = new IntentExporterClient(repository);
		exporter.addRepositoryObjectHandler(handler);
		return exporter;
	}
}
