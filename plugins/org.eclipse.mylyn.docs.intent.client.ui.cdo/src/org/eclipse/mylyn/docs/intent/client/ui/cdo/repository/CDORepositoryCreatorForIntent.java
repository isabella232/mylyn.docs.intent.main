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
package org.eclipse.mylyn.docs.intent.client.ui.cdo.repository;

import org.eclipse.emf.cdo.eresource.EresourcePackage;
import org.eclipse.mylyn.docs.intent.collab.cdo.utils.CDORepositoryCreator;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnitPackage;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;

/**
 * Construct Repository according to configuration files.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class CDORepositoryCreatorForIntent extends CDORepositoryCreator {

	/**
	 * RepositoryCreator constructor.
	 */
	public CDORepositoryCreatorForIntent() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.cdo.utils.CDORepositoryCreator#initialisePackageRegistry(org.eclipse.mylyn.docs.intent.collab.repository.Repository)
	 */
	protected void initialisePackageRegistry(Repository repository) throws RepositoryConnectionException {
		repository.getPackageRegistry().put(IntentIndexerPackage.eNS_URI, IntentIndexerPackage.eINSTANCE);
		repository.getPackageRegistry().put(IntentDocumentPackage.eNS_URI, IntentDocumentPackage.eINSTANCE);
		repository.getPackageRegistry().put(ModelingUnitPackage.eNS_URI, ModelingUnitPackage.eINSTANCE);
		repository.getPackageRegistry().put(DescriptionUnitPackage.eNS_URI, DescriptionUnitPackage.eINSTANCE);
		repository.getPackageRegistry().put(MarkupPackage.eNS_URI, MarkupPackage.eINSTANCE);
		repository.getPackageRegistry().put(CompilerPackage.eNS_URI, CompilerPackage.eINSTANCE);
		repository.getPackageRegistry().put(IntentDocumentPackage.eNS_URI, IntentDocumentPackage.eINSTANCE);
		repository.getPackageRegistry().put(EresourcePackage.eNS_URI, EresourcePackage.eINSTANCE);

	}

}
