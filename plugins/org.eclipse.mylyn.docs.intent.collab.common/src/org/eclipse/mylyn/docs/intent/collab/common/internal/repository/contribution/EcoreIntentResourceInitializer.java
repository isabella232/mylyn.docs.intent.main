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
package org.eclipse.mylyn.docs.intent.collab.common.internal.repository.contribution;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.mylyn.docs.intent.collab.common.uri.IIntentResourceInitializer;

/**
 * An {@link IIntentResourceInitializer} that create an empty {@link EPackage} for empty ecore resources.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class EcoreIntentResourceInitializer implements IIntentResourceInitializer {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.uri.IIntentResourceInitializer#getInitialContent(org.eclipse.emf.common.util.URI)
	 */
	public EObject getInitialContent(URI emptyResourceURI) {
		EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		String defaultPackageName = emptyResourceURI.trimFileExtension().lastSegment();
		ePackage.setName(defaultPackageName);
		ePackage.setNsPrefix(defaultPackageName);
		return ePackage;
	}

}
