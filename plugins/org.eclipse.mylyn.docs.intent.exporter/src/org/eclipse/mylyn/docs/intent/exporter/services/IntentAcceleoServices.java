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

import org.eclipse.emf.ecore.EObject;

/**
 * Regroups all services use during doc export.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentAcceleoServices {

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
}
