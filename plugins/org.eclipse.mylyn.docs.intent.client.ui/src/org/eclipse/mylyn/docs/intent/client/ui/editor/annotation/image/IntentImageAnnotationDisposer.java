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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Iterator;

import org.eclipse.jface.text.source.AnnotationModelEvent;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelListener;
import org.eclipse.jface.text.source.IAnnotationModelListenerExtension;
import org.eclipse.swt.graphics.Image;

/**
 * In charge of disposing the images drawn by {@link IntentImageAnnotation}s.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentImageAnnotationDisposer implements IAnnotationModelListener, IAnnotationModelListenerExtension {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.source.IAnnotationModelListener#modelChanged(org.eclipse.jface.text.source.IAnnotationModel)
	 */
	public void modelChanged(IAnnotationModel model) {

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.source.IAnnotationModelListenerExtension#modelChanged(org.eclipse.jface.text.source.AnnotationModelEvent)
	 */
	public void modelChanged(AnnotationModelEvent event) {
		Iterator<IntentImageAnnotation> removedImageAnnotations = Iterables.filter(
				Lists.newArrayList(event.getRemovedAnnotations()), IntentImageAnnotation.class).iterator();
		while (removedImageAnnotations.hasNext()) {
			disposeImage(removedImageAnnotations.next());
		}
	}

	/**
	 * Disposes the image held by the given {@link IntentImageAnnotation}.
	 * 
	 * @param annotation
	 *            the {@link IntentImageAnnotation} helding the image to dispose
	 */
	public static void disposeImage(IntentImageAnnotation annotation) {
		Image image = annotation.getImage(false);
		if (image != null) {
			image.dispose();
		}
	}
}
