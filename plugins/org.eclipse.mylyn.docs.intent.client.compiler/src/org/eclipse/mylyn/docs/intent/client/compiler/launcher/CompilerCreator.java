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
package org.eclipse.mylyn.docs.intent.client.compiler.launcher;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.mylyn.docs.intent.client.compiler.repositoryconnection.CompilerRepositoryClient;
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
 * Creates Compiler repository clients.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class CompilerCreator {

	/**
	 * CompilerCreator constructor.
	 */
	private CompilerCreator() {

	}

	/**
	 * Creates a CompilerRepositoryClient.
	 * 
	 * @param repository
	 *            is the repository containing the modeling units to compile
	 * @throws RepositoryConnectionException
	 *             if a connection to the given repository cannot be established
	 * @return the created CompilerRepositoryClient
	 * @throws ReadOnlyException
	 *             if no sufficient rights to write on the repository
	 */
	public static CompilerRepositoryClient createCompilerClient(Repository repository)
			throws RepositoryConnectionException, ReadOnlyException {

		// Step 1: initialize the listened types
		Set<EStructuralFeature> listenedTypes = new LinkedHashSet<EStructuralFeature>();

		listenedTypes.add(IntentDocumentPackage.eINSTANCE.getIntentSection_ModelingUnits());

		for (EObject obj : ModelingUnitPackage.eINSTANCE.eContents()) {
			if (obj instanceof EClass && !(ModelingUnitPackage.eINSTANCE.getTypeReference().equals(obj))) {
				listenedTypes.addAll(TypeNotificator.getStructuralFeaturesForEClass((EClass)obj));
			}
		}
		listenedTypes.remove(IntentDocumentPackage.eINSTANCE.getIntentGenericElement_CompilationStatus());
		listenedTypes.remove(GenericUnitPackage.eINSTANCE.getGenericUnit_Instructions());
		listenedTypes.remove(GenericUnitPackage.eINSTANCE.getGenericUnit_UnitName());
		listenedTypes
				.remove(ModelingUnitPackage.eINSTANCE.getContributionInstruction_ContributionReference());
		listenedTypes.remove(ModelingUnitPackage.eINSTANCE
				.getModelingUnitInstructionReference_ReferencedInstruction());
		listenedTypes.remove(ModelingUnitPackage.eINSTANCE.getResourceReference_Declaration());
		listenedTypes.remove(ModelingUnitPackage.eINSTANCE.getInstanceLevelInstruction_MetaType());

		// Step 2: create the adapter and the handler for these types
		final RepositoryAdapter repositoryAdapter = repository.createRepositoryAdapter();
		repositoryAdapter.openSaveContext();

		RepositoryObjectHandler handler;
		handler = new ReadWriteRepositoryObjectHandlerImpl(repositoryAdapter);

		Notificator notificator = new TypeNotificator(listenedTypes);
		handler.addNotificator(notificator);

		// Step 3: create the compiler
		CompilerRepositoryClient compilerClient = new CompilerRepositoryClient(repository);
		compilerClient.addRepositoryObjectHandler(handler);

		return compilerClient;

	}
}
