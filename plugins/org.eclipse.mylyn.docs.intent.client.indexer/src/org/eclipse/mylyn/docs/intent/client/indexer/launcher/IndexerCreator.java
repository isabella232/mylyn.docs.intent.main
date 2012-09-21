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
package org.eclipse.mylyn.docs.intent.client.indexer.launcher;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.mylyn.docs.intent.client.indexer.IndexerRepositoryClient;
import org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryObjectHandler;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.ReadWriteRepositoryObjectHandlerImpl;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.notification.typeListener.TypeNotificator;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.Notificator;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;

/**
 * Creates a new Indexer client listening for any changes on the given document.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IndexerCreator {

	/**
	 * IndexerCreator constructor.
	 */
	private IndexerCreator() {

	}

	/**
	 * Creates an Indexer client listening for any changes on the document stored on the given repository.
	 * 
	 * @param repository
	 *            is the repository to index
	 * @return an Indexer client listening for any changes on the document stored on the given repository
	 * @throws RepositoryConnectionException
	 *             if a connection to the given repository cannot be established
	 * @throws ReadOnlyException
	 *             if no sufficient rights to write on the repository
	 */
	public static IndexerRepositoryClient createIndexer(Repository repository)
			throws RepositoryConnectionException, ReadOnlyException {

		// Step 1 : adapter creation
		final RepositoryAdapter repositoryAdapter = repository.createRepositoryAdapter();
		repositoryAdapter.openSaveContext();

		// Step 2 : creating the handler
		RepositoryObjectHandler handler = new ReadWriteRepositoryObjectHandlerImpl(repositoryAdapter);
		Set<EStructuralFeature> listenedFeatures = new LinkedHashSet<EStructuralFeature>();
		listenedFeatures.add(IntentDocumentPackage.eINSTANCE.getIntentDocument_Chapters());
		listenedFeatures.add(IntentDocumentPackage.eINSTANCE.getIntentSubSectionContainer_SubSections());

		Notificator listenedElementsNotificator = new TypeNotificator(listenedFeatures);
		handler.addNotificator(listenedElementsNotificator);

		// Step 4 : launching the indexer

		IndexerRepositoryClient indexer = new IndexerRepositoryClient();
		indexer.addRepositoryObjectHandler(handler);

		return indexer;

	}
}
