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
package org.eclipse.mylyn.docs.intent.bridge.java.resource.factory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

/**
 * A {@link Factory} allowing to represent a java class as a model.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class JavaResourceFactory implements Resource.Factory {

	/**
	 * Used to build model out of a java file.
	 */
	private final JavaClassExplorer javaClassExplorer = new JavaClassExplorer();

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.resource.Resource.Factory#createResource(org.eclipse.emf.common.util.URI)
	 */
	public Resource createResource(URI uri) {
		Resource resource = new XMIResourceImpl(uri);
		IFile file = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(uri.trimFragment().toString()));
		if (file != null && file.exists()) {
			IJavaElement javaElement = JavaCore.create(file);
			if (javaElement instanceof ICompilationUnit) {
				try {
					resource.getContents().addAll(
							javaClassExplorer.getJavaClassAsModel((ICompilationUnit)javaElement));
				} catch (JavaModelException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return resource;
	}
}
