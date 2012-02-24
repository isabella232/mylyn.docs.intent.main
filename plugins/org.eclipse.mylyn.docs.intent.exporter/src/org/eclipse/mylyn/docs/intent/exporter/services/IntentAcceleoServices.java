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
package org.eclipse.mylyn.docs.intent.exporter.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

/**
 * Regroups all services use during doc export.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentAcceleoServices {

	private static AdapterFactoryItemDelegator itemDelegator;

	private static File outputFolder;

	/**
	 * Returns the header size to apply to the section with the given ID. For example,
	 * getHeaderSizeForSection(3_2) will return "2", getHeaderSizeForSection(4_3_2_1) will return "4".
	 * 
	 * @param sectionID
	 *            the section ID
	 * @return the header size to apply to the section with the given ID
	 */
	public static String getHeaderSizeForSection(String sectionID) {
		return String.valueOf(sectionID.split("_").length);
	}

	/**
	 * Returns the title to associate to the given IntentDocument.
	 * 
	 * @param intentDocument
	 *            the intent document
	 * @return the title to associate to the given IntentDocument
	 */
	public static String getDocumentTitle(EObject any) {
		return any.eResource().getURI().segment(any.eResource().getURI().segmentCount() - 4).toString();
	}

	/**
	 * Determines the image associated to the given EObject, copies it inside the exported documentation and
	 * 
	 * @param any
	 *            the eobject to get the image from
	 * @return the image associated to the given EObject
	 */
	public static String getQualifiedImageID(EObject any) {
		String qualifiedImageID = "";
		Object imageURL = null;
		if (any instanceof EClass) {
			EObject instance = ((EClassifier)any).getEPackage().getEFactoryInstance().create((EClass)any);
			imageURL = getItemDelegator(any).getImage(instance);
		} else if (any instanceof EClassifier) {
			imageURL = getItemDelegator(any).getImage(any);
		}
		if (!(imageURL instanceof URL)) {
			if (imageURL instanceof URI) {
				try {
					imageURL = new URL(imageURL.toString());
				} catch (MalformedURLException e) {
					// silent catch
				}
			} else {
				if (imageURL instanceof org.eclipse.emf.edit.provider.ComposedImage) {
					imageURL = ((org.eclipse.emf.edit.provider.ComposedImage)imageURL).getImages().iterator()
							.next();
				}
			}
		}
		if (imageURL instanceof URL) {
			try {
				URL resolvedURL = FileLocator.resolve((URL)imageURL);
				// If default "Item" image has been
				if (resolvedURL.toString().contains("org.eclipse.emf.edit")
						&& resolvedURL.toString().endsWith("/icons/full/obj16/Item.gif")) {
					// we search for an "edit" plugin in the workspace
					// TODO
				}
				qualifiedImageID = copyImageIfNeeded((EClassifier)any, resolvedURL, resolvedURL.openStream());
			} catch (IOException e) {
				// Silent catch, default image will be used
			}
		}
		return qualifiedImageID;
	}

	private static String copyImageIfNeeded(EClassifier classifier, URL imageURL, InputStream sourceStream)
			throws IOException {
		File targetFile = new File(outputFolder.getAbsolutePath() + "/icons/generated/"
				+ classifier.getEPackage().getName()
				+ imageURL.getFile().substring(imageURL.getFile().lastIndexOf('/')));
		// create folders if needed :
		new File(outputFolder.getAbsolutePath() + "/icons/generated/" + classifier.getEPackage().getName())
				.mkdirs();
		if (!targetFile.exists()) {
			copyFile(sourceStream, targetFile);
		}
		String copiedImagePath = targetFile.getAbsolutePath().toString();
		String outputFolderPath = outputFolder.getAbsolutePath();
		return "." + copiedImagePath.substring(outputFolderPath.length());
	}

	private static AdapterFactoryItemDelegator getItemDelegator(EObject any) {
		if (itemDelegator == null) {
			ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

			adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
			itemDelegator = new AdapterFactoryItemDelegator(adapterFactory);
		}
		return itemDelegator;
	}

	public static void initialize(File generationOutputFolder) {
		outputFolder = generationOutputFolder;
	}

	public static void dispose() {
		if (itemDelegator != null) {
			((ComposedAdapterFactory)itemDelegator.getAdapterFactory()).dispose();
		}
		itemDelegator = null;
		outputFolder = null;
	}

	private static void copyFile(final InputStream sourceStream, final File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileOutputStream outputStream = new FileOutputStream(destFile);
		FileChannel destination = null;
		try {
			byte[] buf = new byte[1024];

			int len;

			while ((len = sourceStream.read(buf)) > 0) {

				outputStream.write(buf, 0, len);

			}

			sourceStream.close();

			outputStream.close();
		} finally {
			if (sourceStream != null) {
				sourceStream.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
}
