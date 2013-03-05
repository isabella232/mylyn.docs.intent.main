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
package org.eclipse.mylyn.docs.intent.modelingunit.update;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;

/**
 * A {@link MergeUpdater} that creates {@link ExternalContentReference} for the given collection of elements
 * to synchronize.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ExternalContentReferencesMergeUpdater extends MergeUpdater {

	/**
	 * Default constructor.
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use
	 */
	public ExternalContentReferencesMergeUpdater(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.modelingunit.update.MergeUpdater#internalCreate(org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit,
	 *      org.eclipse.emf.ecore.EObject, java.util.List)
	 */
	@Override
	protected void internalCreate(ModelingUnit modelingUnit, EObject sibling, List<EObject> elements) {
		for (EObject element : elements) {
			ExternalContentReference externalContentRef = ModelingUnitFactory.eINSTANCE
					.createExternalContentReference();
			externalContentRef.setUri(EcoreUtil.getURI(element).toString());
			int siblingIndex = modelingUnit.getInstructions().indexOf(sibling);
			if (siblingIndex != -1) {
				modelingUnit.getInstructions().add(siblingIndex, externalContentRef);
			} else {
				modelingUnit.getInstructions().add(0, externalContentRef);
			}
		}
	}

}
