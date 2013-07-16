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
import org.eclipse.jface.text.source.AnnotationPainter;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationFactory;

/**
 * An {@link AnnotationPainter} allowing to paint {@link AbstractIntentImageAnnotation}s.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentImageAnnotationPainter extends AnnotationPainter {

	/**
	 * Default constructor.
	 * 
	 * @param intentEditor
	 *            the {@link IntentEditor} on which annotations will be painted
	 * @param viewer
	 *            the {@link ISourceViewer} on which annotations will be painted
	 */
	public IntentImageAnnotationPainter(IntentEditor intentEditor, ISourceViewer viewer) {
		super(viewer, IntentImageAnnotationAccess.getInstance());
		addDrawingStrategy(IntentAnnotationFactory.INTENT_IMAGE, new IntentImageAnnotationDrawingStrategy(
				intentEditor, viewer));
		addAnnotationType(IntentAnnotationFactory.INTENT_IMAGE, IntentAnnotationFactory.INTENT_IMAGE);
		setAnnotationTypeColor(IntentAnnotationFactory.INTENT_IMAGE, viewer.getTextWidget().getForeground());
	}

	/**
	 * An {@link IAnnotationAccess} for this {@link IntentImageAnnotationPainter}.
	 * 
	 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
	 */
	private static final class IntentImageAnnotationAccess implements IAnnotationAccess {

		/**
		 * Default instance.
		 */
		private static final IntentImageAnnotationAccess INSTANCE = new IntentImageAnnotationAccess();

		/**
		 * Default constructor.
		 */
		private IntentImageAnnotationAccess() {

		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.jface.text.source.IAnnotationAccess#getType(org.eclipse.jface.text.source.Annotation)
		 */
		public Object getType(Annotation annotation) {
			return annotation.getType();
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.jface.text.source.IAnnotationAccess#isMultiLine(org.eclipse.jface.text.source.Annotation)
		 */
		public boolean isMultiLine(Annotation annotation) {
			return false;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.jface.text.source.IAnnotationAccess#isTemporary(org.eclipse.jface.text.source.Annotation)
		 */
		public boolean isTemporary(Annotation annotation) {
			return true;
		}

		/**
		 * Returns the running instance.
		 * 
		 * @return the running instance
		 */
		public static IAnnotationAccess getInstance() {
			return INSTANCE;
		}
	}

}
