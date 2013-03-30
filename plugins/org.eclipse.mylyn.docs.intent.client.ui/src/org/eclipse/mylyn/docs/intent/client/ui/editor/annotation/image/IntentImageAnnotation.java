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
package org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.jface.text.source.Annotation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * An {@link Annotation} allowing the {@link org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor} to
 * display images corresponding to an image link in pure documentation zones.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentImageAnnotation extends AbstractIntentImageAnnotation {

	private org.eclipse.mylyn.docs.intent.markup.markup.Image imageLink;

	/**
	 * Default constructor.
	 * 
	 * @param imageLink
	 *            a reference to an image
	 */
	public IntentImageAnnotation(org.eclipse.mylyn.docs.intent.markup.markup.Image imageLink) {
		super();
		this.imageLink = imageLink;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image.AbstractIntentImageAnnotation#doCreateImage()
	 */
	@Override
	protected Image doCreateImage() {
		String imagePath = imageLink.getUrl();

		// TODO handle
		// - path relative to project
		// - URL images
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(imagePath);
			return new Image(Display.getDefault(), fileInputStream);
		} catch (FileNotFoundException e) {
			// Silent catch: image will not be created
		}
		return null;
	}
}
