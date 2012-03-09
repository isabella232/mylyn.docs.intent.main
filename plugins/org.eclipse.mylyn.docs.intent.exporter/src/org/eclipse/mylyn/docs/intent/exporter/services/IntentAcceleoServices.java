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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentSubSectionContainer;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;

/**
 * Regroups all services use during doc export.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentAcceleoServices {

	private static File outputFolder;

	private static String intentDocumentTitle;

	private static RepositoryAdapter repositoryAdapter;

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
		return intentDocumentTitle;
	}

	/**
	 * Determines the image associated to the given EObject, copies it inside the exported documentation and
	 * 
	 * @param any
	 *            the eobject to get the image from
	 * @return the image associated to the given EObject
	 */
	public static String getQualifiedImageID(EObject any) {
		return CopyImageUtils.copyImageAndGetImageID(any, repositoryAdapter, outputFolder);
	}

	public static String getContainingSectionID(EObject any) {
		String ID = "";
		if (any instanceof IntentSection) {
			EObject container = any;
			while (container != null & !(container instanceof IntentDocument)) {
				if (container.eContainer() instanceof IntentSubSectionContainer) {
					ID = (((IntentSubSectionContainer)container.eContainer()).getSubSections().indexOf(
							container) + 1)
							+ "_" + ID;
				} else {
					if (container.eContainer() instanceof IntentDocument) {
						ID = (((IntentDocument)container.eContainer()).getChapters().indexOf(container) + 1)
								+ "_" + ID;
					}
				}

				container = container.eContainer();
			}
		}
		return ID.substring(0, ID.lastIndexOf("_"));
	}

	public static IntentSection getContainingSection(EObject any) {
		EObject container = any;
		if (any instanceof UnitInstruction) {
			while (container != null && !(container instanceof IntentSection)) {
				container = container.eContainer();
			}
		}
		if (container instanceof IntentSection) {
			return (IntentSection)container;
		}
		return null;
	}

	public static void initialize(String documentTitle, File generationOutputFolder, RepositoryAdapter adapter) {
		intentDocumentTitle = documentTitle;
		outputFolder = generationOutputFolder;
		repositoryAdapter = adapter;
	}

	public static void dispose() {
		CopyImageUtils.dispose();
		outputFolder = null;
		repositoryAdapter = null;
	}

}
