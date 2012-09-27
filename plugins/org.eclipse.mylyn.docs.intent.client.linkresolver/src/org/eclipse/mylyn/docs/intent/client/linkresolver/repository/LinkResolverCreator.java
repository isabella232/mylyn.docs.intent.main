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
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;

/**
 * Utility class allowing to create {@link LinkResolverClient}s.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class LinkResolverCreator {
	/**
	 * SynchronizerCreator constructor.
	 */
	private LinkResolverCreator() {

	}

	/**
	 * Creates a {@link LinkResolverClient}.
	 * 
	 * @param repository
	 *            the repository containing the document in which links should be resolved
	 * @return the created {@link LinkResolverClient}
	 * @throws RepositoryConnectionException
	 *             if a connection to the given repository cannot be established
	 * @throws ReadOnlyException
	 *             if no sufficient rights to write on the repository
	 */
	public static LinkResolverClient createLinkResolverClient(Repository repository)
			throws RepositoryConnectionException, ReadOnlyException {

		// Step 1: initialize the listened types
		Set<EStructuralFeature> listenedTypes = new LinkedHashSet<EStructuralFeature>();
		listenedTypes.addAll(TypeNotificator.getStructuralFeaturesForEClass(GenericUnitPackage.eINSTANCE
				.getIntentReference()));
		listenedTypes.addAll(TypeNotificator.getStructuralFeaturesForEClass(IntentDocumentPackage.eINSTANCE
				.getIntentSection()));
		listenedTypes.addAll(TypeNotificator.getStructuralFeaturesForEClass(IntentDocumentPackage.eINSTANCE
				.getIntentChapter()));
		listenedTypes.addAll(TypeNotificator.getStructuralFeaturesForEClass(ModelingUnitPackage.eINSTANCE
				.getModelingUnit()));

		// Step 2: create the adapter and the handler for these types
		final RepositoryAdapter repositoryAdapter = repository.createRepositoryAdapter();

		RepositoryObjectHandler handler = new ReadWriteRepositoryObjectHandlerImpl(repositoryAdapter);

		Notificator typeNotificator = new TypeNotificator(listenedTypes);
		handler.addNotificator(typeNotificator);

		// Step 3: create the link resolver
		LinkResolverClient linkResolver = new LinkResolverClient();
		linkResolver.addRepositoryObjectHandler(handler);

		return linkResolver;

	}
}
