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

import org.eclipse.jface.text.source.Annotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationFactory;
import org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension;
import org.eclipse.mylyn.docs.intent.client.ui.internal.renderers.IEditorRendererExtensionRegistry;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.swt.graphics.Image;

/**
 * An {@link Annotation} allowing the {@link org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor} to
 * display images corresponding to
 * {@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference}s (e.g the text of a java
 * file).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentImageAnnotation extends Annotation {

	/**
	 * The {@link Image} that should be displayed by this annotation.
	 */
	private Image image;

	/**
	 * The {@link ExternalContentReference} associated to this {@link IntentImageAnnotation}.
	 */
	private ExternalContentReference reference;

	/**
	 * Indicates whether the next call to getImage() should recalculate the image or not (default value is
	 * false).
	 */
	private boolean imageShouldBeRedrawn;

	/**
	 * Default constructor.
	 * 
	 * @param reference
	 *            the {@link ExternalContentReference} to display as image for this annotation
	 */
	public IntentImageAnnotation(ExternalContentReference reference) {
		super(IntentAnnotationFactory.INTENT_IMAGE, false, "");
		this.reference = reference;
	}

	/**
	 * Returns the {@link Image} that should be displayed by this annotation.
	 * 
	 * @return the {@link Image} that should be displayed by this annotation
	 */
	public Image getImage() {
		return getImage(true);
	}

	/**
	 * Returns the {@link Image} that should be displayed by this annotation.
	 * 
	 * @param createImageIfNotExists
	 *            indicates whether the image should be created if not exists
	 * @return the {@link Image} that should be displayed by this annotation
	 */
	public Image getImage(boolean createImageIfNotExists) {
		// Step 1: if previous image is disposed, setting image to null
		if (this.image != null && this.image.isDisposed()) {
			this.image = null;
		}

		// Step 2: if we should create the image if it does not exists
		// AND if the image does not exist (is null)
		// or should be redrawn
		if (createImageIfNotExists && (imageShouldBeRedrawn || this.image == null)) {
			// Disposing previous image
			if (image != null) {
				image.dispose();
			}

			// creating image through provided IEditorRedenderExtensions
			for (IEditorRendererExtension rendererExtension : IEditorRendererExtensionRegistry
					.getEditorRendererExtensions(reference)) {
				image = rendererExtension.getImage(reference);
				if (image != null) {
					imageShouldBeRedrawn = false;
					break;
				}
			}
		}
		return image;
	}

	/**
	 * Returns the {@link ExternalContentReference} associated to this {@link IntentImageAnnotation}.
	 * 
	 * @return the {@link ExternalContentReference} associated to this {@link IntentImageAnnotation}
	 */
	public ExternalContentReference getExternalContentReference() {
		return reference;
	}

	/**
	 * Indicates whether the next call to getImage() should recalculate the image or not (default value is
	 * false).
	 * 
	 * @param imageShouldBeRedrawn
	 *            whether the next call to getImage() should recalculate the image or not
	 */
	public void setImageShouldBeRedrawn(boolean imageShouldBeRedrawn) {
		this.imageShouldBeRedrawn = imageShouldBeRedrawn;
	}
}
