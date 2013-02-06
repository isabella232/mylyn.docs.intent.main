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

import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.AnnotationPainter.IDrawingStrategy;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.IntentFontConstants;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

/**
 * An {@link IDrawingStrategy} used to display images as described in {@link IntentImageAnnotation}s.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentImageAnnotationDrawingStrategy implements IDrawingStrategy {

	private IntentEditor editor;

	private final ISourceViewer viewer;

	/**
	 * Default constructor.
	 * 
	 * @param editor
	 *            the {@link IntentEditor} holding the {@link IntentImageAnnotation}s to paint
	 * @param viewer
	 *            the {@link ISourceViewer} holding the {@link IntentImageAnnotation}s to paint
	 */
	public IntentImageAnnotationDrawingStrategy(IntentEditor editor, ISourceViewer viewer) {
		this.editor = editor;
		this.viewer = viewer;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.source.AnnotationPainter.IDrawingStrategy#draw(org.eclipse.jface.text.source.Annotation,
	 *      org.eclipse.swt.graphics.GC, org.eclipse.swt.custom.StyledText, int, int,
	 *      org.eclipse.swt.graphics.Color)
	 */
	public void draw(Annotation annotation, GC gc, StyledText textWidget, int eventOffset, int length,
			Color color) {
		// If the Intent editor has not yet initiated its folding structure, we do not paint images (only
		// paint annotations that will actually be displayed to the end-user)
		if (!(annotation instanceof IntentImageAnnotation) || !editor.isInitialFoldingStructureComplete()) {
			return;
		}
		try {
			int offset = eventOffset;
			if (gc != null) {
				Position position = viewer.getAnnotationModel().getPosition(annotation);

				// Step 1: get the image to paint
				IntentImageAnnotation imageAnnotation = (IntentImageAnnotation)annotation;
				Image image = imageAnnotation.getImage();
				// If image is not available, get the default SWT ICON_QUESTION
				if (image != null && !image.isDisposed()) {

					// Step 2: get position
					if (position != null) {
						offset = position.offset;
					}
					Point imagePosition = computeImagePosition(textWidget, offset, length, position);

					// Step 3: paint the image background as rectangle
					Color foreground = gc.getForeground();
					Color background = gc.getBackground();
					gc.setForeground(textWidget.getForeground());
					gc.setBackground(textWidget.getBackground());
					Rectangle bounds = image.getBounds();
					gc.fillRectangle(new Rectangle(imagePosition.x, imagePosition.y, bounds.width,
							bounds.height));

					// Step 4: update style range so that the font size is equals to the image height
					updateStyleRange(textWidget, offset, bounds.height, gc);

					// Step 5: draw image
					gc.setForeground(foreground);
					gc.setBackground(background);
					gc.drawImage(image, imagePosition.x, imagePosition.y);
				} else {
					updateStyleRange(textWidget, offset, 1, gc);
				}
			} else {
				// Calling redraw on the text widget
				textWidget.redrawRange(offset, length, true);
			}
		} catch (IllegalArgumentException e) {
			IntentUiLogger.logError(e);
		}
	}

	/**
	 * Computes the position of the image to draw.
	 * 
	 * @param textWidget
	 *            the {@link StyledText} on which the image will be painted
	 * @param offset
	 *            the offset
	 * @param length
	 *            the length
	 * @param position
	 *            the position
	 * @return the position of the image to draw
	 */
	private Point computeImagePosition(StyledText textWidget, int offset, int length, Position position) {
		Point imagePosition = textWidget.getLocationAtOffset(offset);
		if (position == null && length > 0) {
			Point right = textWidget.getLocationAtOffset(offset + length);
			if (imagePosition.x > right.x) {
				imagePosition.x = 0;
				imagePosition.y = right.y;
			}
		}
		return imagePosition;
	}

	/**
	 * Updates the style range to use a font with sufficient height to cover the whole drawn image.
	 * 
	 * @param textWidget
	 *            the text widget
	 * @param offset
	 *            the draw image offset
	 * @param expectedHeight
	 *            the draw image heigh (in px)
	 * @param gc
	 *            the current gc
	 */
	private void updateStyleRange(StyledText textWidget, int offset, int expectedHeight, GC gc) {
		// Step 1: determine if we have already overridden style range
		Iterable<IntentImageStyleRange> newArrayList = Iterables.filter(
				Lists.newArrayList(textWidget.getStyleRangeAtOffset(offset)), IntentImageStyleRange.class);

		int length = 1;
		if (!newArrayList.iterator().hasNext()) {
			// Step 2: create a new style range at the image offset
			StyleRange styleRange = new IntentImageStyleRange(offset, length, gc.getForeground(),
					gc.getBackground(), SWT.NONE);

			// Step 3: create a font having a height allowing to cover the whole image
			Font oldFont = gc.getFont();
			Font referenceFont = IntentFontConstants.getImageReferenceFont();
			gc.setFont(referenceFont);
			float referenceFontHeightInPX = gc.getFontMetrics().getAscent();
			float expectedFontSizeFloat = expectedHeight / referenceFontHeightInPX;
			expectedFontSizeFloat += 0.5;
			int expectedFontSizeInPoints = Math.round(expectedFontSizeFloat);
			gc.setFont(oldFont);
			Font coverringImageFont = new Font(referenceFont.getDevice(),
					referenceFont.getFontData()[0].getName(), expectedFontSizeInPoints
							* referenceFont.getFontData()[0].getHeight(), SWT.NONE);
			styleRange.font = coverringImageFont;

			// Step 4: replace style range
			StyleRange[] ranges = new StyleRange[1];
			ranges[0] = styleRange;
			textWidget.replaceStyleRanges(offset, length, ranges);
		}
	}
}
