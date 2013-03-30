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
public class IntentExternalContentReferenceImageAnnotation extends AbstractIntentImageAnnotation {

	/**
	 * The {@link ExternalContentReference} associated to this {@link AbstractIntentImageAnnotation}.
	 */
	private ExternalContentReference reference;

	/**
	 * Default constructor.
	 * 
	 * @param reference
	 *            the {@link ExternalContentReference} to display as image for this annotation
	 */
	public IntentExternalContentReferenceImageAnnotation(ExternalContentReference reference) {
		super();
		this.reference = reference;
	}

	/**
	 * Returns the {@link ExternalContentReference} associated to this {@link AbstractIntentImageAnnotation}.
	 * 
	 * @return the {@link ExternalContentReference} associated to this {@link AbstractIntentImageAnnotation}
	 */
	public ExternalContentReference getExternalContentReference() {
		return reference;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image.AbstractIntentImageAnnotation#doCreateImage()
	 */
	@Override
	protected Image doCreateImage() {
		// creating image through provided IEditorRedenderExtensions
		for (IEditorRendererExtension rendererExtension : IEditorRendererExtensionRegistry
				.getEditorRendererExtensions(reference)) {
			Image renderedImage = rendererExtension.getImage(reference);
			if (renderedImage != null) {
				return renderedImage;
			}
		}
		return null;
	}
}
