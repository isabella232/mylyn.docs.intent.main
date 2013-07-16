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
package org.eclipse.mylyn.docs.intent.markup.gen.files;

import org.eclipse.emf.common.util.URI;
import org.eclipse.mylyn.docs.intent.markup.markup.Image;
import org.eclipse.swt.graphics.ImageData;

/**
 * Service for Acceleo generator to get Image information.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class ImageUtility {

	/**
	 * Width lower than this constant will be considered as small.
	 */
	private static final int SMALL_WIDTH = 600;

	/**
	 * Images with a ratio greater than this constant will be considered as long landscape.
	 */
	private static final double LONG_LANDSCAPE_RATIO = 1.3;

	/**
	 * Text width resolution.
	 */
	private static final double TEXT_WIDTH_RESOLUTION = 900d;

	/**
	 * Returns the given image's width.
	 * 
	 * @param imageDSL
	 *            the image
	 * @return the given image's width
	 */
	public String getImageWidth(Image imageDSL) {
		double ratio = getWidthRatio(imageDSL);
		return Double.valueOf(ratio).toString();
	}

	/**
	 * Returns the ratio of the image according to the resolution.
	 * 
	 * @param imageDSL
	 *            the image
	 * @return the ratio of the image according to the resolution
	 */
	public Double getWidthRatio(Image imageDSL) {
		ImageData data = getImageData(imageDSL);
		if (data != null) {
			double ratio = data.width / TEXT_WIDTH_RESOLUTION;
			return ratio;
		}
		return 1d;
	}

	/**
	 * Indicates whether the image should have a landscape ratio (i.e. its width ratio is > 1).
	 * 
	 * @param imageDSL
	 *            the image
	 * @return true if the image should have a landscape ratio (i.e. its width ratio is > 1), false otherwise
	 */
	public Boolean hasLandscapeRatio(Image imageDSL) {
		return getWidthRatio(imageDSL) > 1;
	}

	/**
	 * Indicates whether the image should have a long landscape ratio (i.e. its width ratio is >
	 * LONG_LANDSCAPE_RATIO).
	 * 
	 * @param imageDSL
	 *            the image
	 * @return true if the image should have a long landscape ratio (i.e. its width ratio is >
	 *         LONG_LANDSCAPE_RATIO), false otherwise
	 */
	public Boolean hasLongLandscapeRatio(Image imageDSL) {
		double widthRatio = getWidthRatio(imageDSL);
		return widthRatio > LONG_LANDSCAPE_RATIO;
	}

	/**
	 * Indicates if the image is small (i.e. <= SMALL_WIDTH).
	 * 
	 * @param imageDSL
	 *            the image
	 * @return true if the image is small (i.e. <= SMALL_WIDTH), false otherwise
	 */
	public Boolean isSmall(Image imageDSL) {
		ImageData data = getImageData(imageDSL);
		if (data != null) {
			return data.width <= SMALL_WIDTH;
		}
		return true;

	}

	/**
	 * Returns the {@link ImageData} corresponding to the given {@link Image}.
	 * 
	 * @param imageDSL
	 *            the image
	 * @return the {@link ImageData} corresponding to the given {@link Image}
	 */
	private ImageData getImageData(Image imageDSL) {
		try {
			URI imageURI = URI.createURI(imageDSL.getUrl());
			String absoluteImagePath;
			if (imageURI.hasAbsolutePath()) {
				absoluteImagePath = imageURI.toString();
			} else {
				URI modelPath = imageDSL.eResource().getURI().trimSegments(1);
				absoluteImagePath = modelPath.toFileString() + "/" + imageDSL.getUrl();
			}
			ImageData data = new ImageData(absoluteImagePath);
			return data;
			// CHECKSTYLE:OFF
		} catch (Exception e) {
			// CHECKSTYLE:ON
			e.printStackTrace();
		}
		return null;
	}

}
