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
package org.eclipse.mylyn.docs.intent.client.ui.editor.annotation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.texteditor.IAnnotationImageProvider;

/**
 * Provides icon for the annotations related to Intent document.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentAnnotationImageProvider implements IAnnotationImageProvider {

	/**
	 * Constant indicating the folder of the managed images.
	 */
	private static final String ANNOTATION_IMAGE_FOLDER_PATH = "icon/annotation/";

	/**
	 * Constant for the default path.
	 */
	private static final String DEFAULT_IMAGE_PATH = ANNOTATION_IMAGE_FOLDER_PATH + "compiler-info.gif";

	/**
	 * A map associating each annotation message type with its corresponding image's path.
	 */
	private Map<IntentAnnotationMessageType, String> annotationTypeToImagePath;

	/**
	 * IntentAnnotationImageProvider constructor.
	 */
	public IntentAnnotationImageProvider() {

		annotationTypeToImagePath = new HashMap<IntentAnnotationMessageType, String>();

		annotationTypeToImagePath.put(IntentAnnotationMessageType.COMPILER_ERROR,
				ANNOTATION_IMAGE_FOLDER_PATH + "compiler-error.gif");
		annotationTypeToImagePath.put(IntentAnnotationMessageType.COMPILER_WARNING,
				ANNOTATION_IMAGE_FOLDER_PATH + "compiler-warning.gif");
		annotationTypeToImagePath.put(IntentAnnotationMessageType.COMPILER_INFO, ANNOTATION_IMAGE_FOLDER_PATH
				+ "compiler-info.gif");
		annotationTypeToImagePath.put(IntentAnnotationMessageType.PARSER_ERROR, ANNOTATION_IMAGE_FOLDER_PATH
				+ "syntaxerror.gif");
		annotationTypeToImagePath.put(IntentAnnotationMessageType.SYNC_WARNING, ANNOTATION_IMAGE_FOLDER_PATH
				+ "sync-warning.gif");

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.IAnnotationImageProvider#getManagedImage(org.eclipse.jface.text.source.Annotation)
	 */
	public Image getManagedImage(Annotation annotation) {
		String imagePath = DEFAULT_IMAGE_PATH;
		if (annotation instanceof IntentAnnotation) {
			imagePath = annotationTypeToImagePath.get(((IntentAnnotation)annotation).getMessageType());
		}
		return IntentEditorActivator.getDefault().getImage(imagePath);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.IAnnotationImageProvider#getImageDescriptorId(org.eclipse.jface.text.source.Annotation)
	 */
	public String getImageDescriptorId(Annotation annotation) {

		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.IAnnotationImageProvider#getImageDescriptor(java.lang.String)
	 */
	public ImageDescriptor getImageDescriptor(String imageDescritporId) {
		return null;
	}
}
