/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.markup.resource.wikimedia;

import org.eclipse.emf.common.util.URI;

/**
 * URI for for web-based pages on wikimedia.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class WikimediaURI {

	/**
	 * The base URI.
	 */
	private URI baseURI;

	/**
	 * Default constructor.
	 * 
	 * @param baseURI
	 *            the base URI
	 */
	public WikimediaURI(URI baseURI) {
		this.baseURI = baseURI;
	}

	/**
	 * Returns the page name from the base {@link URI}.
	 * 
	 * @return the page name from the base {@link URI}
	 */
	public String pageName() {

		int startingSegment = 0;
		for (int i = 0; i < baseURI.segmentCount(); i++) {
			String seg = baseURI.segment(i);
			if ("index.php".equals(seg)) {
				startingSegment = i + 1;
			}
		}

		String pageName = "";
		for (int i = startingSegment; i < baseURI.segmentCount(); i++) {
			if (i > startingSegment) {
				pageName += "/";
			}
			pageName += baseURI.segment(i);
		}
		return pageName;
	}

	/**
	 * Returns the server from the base {@link URI}.
	 * 
	 * @return the server from the base {@link URI}
	 */
	public String baseServer() {
		return baseURI.scheme() + "://" + baseURI.host();
	}

}
