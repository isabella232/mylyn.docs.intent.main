/*******************************************************************************
 * Copyright (c) 2011 Fabian Steeg. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors: Fabian Steeg - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.markup.ui.popup.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.mylyn.docs.intent.markup.resource.WikitextResourceFactory;

/**
 * Helper class to call a generator.
 * 
 * @author Fabian Steeg (fsteeg)
 */
public final class GenerateActions {

	/**
	 * Static helper class, not to be instantiated.
	 */
	private GenerateActions() {
	}

	/**
	 * Launches the given generator on the given selection.
	 * 
	 * @param selection
	 *            The selection to check for a contained file
	 * @param generator
	 *            The generator class to instantiate using a model parsed from the file in the selection
	 */
	static void run(ISelection selection, Class<? extends AbstractAcceleoGenerator> generator) {
		Object file;
		if (selection instanceof StructuredSelection) {
			file = ((StructuredSelection)selection).getFirstElement();
			if (file instanceof IFile) {
				generateFileWithGenerator((IFile)file, generator);
			}
		}
	}

	/**
	 * Parses the give file, and exports the resulting model through the given generator class.
	 * 
	 * @param iFile
	 *            the wikitext file to parse
	 * @param generatorClass
	 *            the generator to use
	 */
	private static void generateFileWithGenerator(IFile iFile,
			Class<? extends AbstractAcceleoGenerator> generatorClass) {
		try {
			File file = resolve(iFile.getLocationURI().toURL());
			InputStream input = new FileInputStream(file);
			URI textileUri = URI.createURI(iFile.getLocationURI().toString());
			Resource resourceTextile = new WikitextResourceFactory().createResource(textileUri);
			Map<String, String> options = new HashMap<String, String>();
			options.put(XMLResource.OPTION_ENCODING, "UTF8");
			resourceTextile.load(input, options);
			EObject model = resourceTextile.getContents().iterator().next();
			AbstractAcceleoGenerator generator = createGenerator(generatorClass, file, model);
			generator.doGenerate(new BasicMonitor());
			iFile.getParent().refreshLocal(IResource.DEPTH_ONE, null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instanciates a generator from the given class and with the given arguments.
	 * 
	 * @param generatorClass
	 *            the generator class ot instanciate
	 * @param file
	 *            the wikitext file (file will be generated in the samed folder)
	 * @param model
	 *            the model on which the generator will be launched
	 * @return a generator created from the given class and with the given arguments
	 */
	private static AbstractAcceleoGenerator createGenerator(
			Class<? extends AbstractAcceleoGenerator> generatorClass, File file, EObject model) {
		try {
			Constructor<? extends AbstractAcceleoGenerator> c = generatorClass.getConstructor(EObject.class,
					File.class, List.class);
			AbstractAcceleoGenerator generator = c.newInstance(model, file.getParentFile(),
					new ArrayList<String>());
			return generator;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the file located at the given URL.
	 * 
	 * @param url
	 *            the url of the file to get
	 * @return the file located at the given URL
	 */
	private static File resolve(final URL url) {
		File resultFile = null;
		URL resolved = url;
		try {
			/*
			 * If we don't check the protocol here, the FileLocator throws a NullPointerException if the URL
			 * is a normal file URL.
			 */
			if (!"file".equals(url.getProtocol())) { //$NON-NLS-1$
				resolved = FileLocator.resolve(resolved);
			}
			resultFile = new File(resolved.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultFile;
	}
}
