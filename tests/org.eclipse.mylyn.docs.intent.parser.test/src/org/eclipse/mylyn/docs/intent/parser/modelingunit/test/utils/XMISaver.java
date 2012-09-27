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
package org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * Saves a given Object in an xmi file.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class XMISaver {

	/**
	 * XMISaver constructor.
	 */
	private XMISaver() {

	}

	/**
	 * Saves the given EObject in the given xmiFile.
	 * 
	 * @param rootToSave
	 *            EObject representing the root to save
	 * @throws IOException
	 *             if the root cannot be saved in the given file.
	 * @return the resource as a string
	 */
	public static String saveASXMI(EObject rootToSave) throws IOException {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("*", new XMIResourceFactoryImpl());

		Resource temp = resourceSet.createResource(URI.createURI("temp.xmi"));
		temp.getContents().add(rootToSave);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		temp.save(bos, options);
		bos.close();
		return bos.toString().replaceAll("\r\n", "\n");
	}

	/**
	 * Saves the given EObject in the given xmiFile.
	 * 
	 * @param rootToSave
	 *            EObject representing the root to save
	 * @param xmiFile
	 *            file in which the root will be stored
	 * @throws IOException
	 *             if the root cannot be saved in the given file.
	 */
	public static void saveASXMI(EObject rootToSave, File xmiFile) throws IOException {
		Map<String, Boolean> options = new HashMap<String, Boolean>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("*", new XMIResourceFactoryImpl());

		Resource temp = resourceSet.createResource(URI.createURI("file:/" + xmiFile.getAbsolutePath()));
		temp.getContents().add(rootToSave);
		temp.save(options);
	}
}
