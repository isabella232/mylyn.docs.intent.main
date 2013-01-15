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
package org.eclipse.mylyn.docs.intent.client.compiler.externalcontent;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.ExternalContent;

public class ExternalContentResourceFactory implements Resource.Factory {

	public Resource createResource(URI uri) {
		ExternalContent content = CompilerFactory.eINSTANCE.createExternalContent();
		content.setContent("MY NEW CONTENT");
		ResourceImpl resource = new ResourceImpl(uri);
		resource.getContents().add(content);
		return resource;
	}

}
