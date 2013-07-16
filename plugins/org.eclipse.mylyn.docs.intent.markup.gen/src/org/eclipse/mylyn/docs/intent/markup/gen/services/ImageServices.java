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
package org.eclipse.mylyn.docs.intent.markup.gen.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.mylyn.docs.intent.markup.markup.Image;

/**
 * Services to copy relative and URL images into the export folder.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class ImageServices {

	/**
	 * Size of the buffer used to read images.
	 */
	private static final int BUFFER_SIZE = 1024;

	/**
	 * The destination folder path.
	 */
	private static String desinationFolderPath;

	/**
	 * The image folder path.
	 */
	private static String imageFolderPath;

	/**
	 * The relative url base for images.
	 */
	private static String relativeURLBasePath;

	/**
	 * Private constructor.
	 */
	private ImageServices() {

	}

	/**
	 * Sets the destination folder absolute path.
	 * 
	 * @param desinationFolderAbsolutePath
	 *            the destination folder absolute path
	 */
	public static void setDestinationFolder(String desinationFolderAbsolutePath) {
		desinationFolderPath = desinationFolderAbsolutePath;
	}

	/**
	 * Sets the relative path of the folder which will contains the copied images (e.g. 'images').
	 * 
	 * @param imageFolderRelativePath
	 *            the relative path of the folder which will contains the copied images (e.g. 'images')
	 */
	public static void setImageFolderRelativePath(String imageFolderRelativePath) {
		imageFolderPath = imageFolderRelativePath;
	}

	/**
	 * Sets the absolute location that will be considered as reference for image with relative URIs.
	 * 
	 * @param relativeURLBaseAbsolutePath
	 *            the absolute location that will be considered as reference for image with relative URIs
	 */
	public static void setRelativeURLBase(String relativeURLBaseAbsolutePath) {
		relativeURLBasePath = relativeURLBaseAbsolutePath;
	}

	/**
	 * Copies the image located at the given URL into the given destination path.
	 * 
	 * @param image
	 *            the image declaration (URL can be relative, absolute or http://)
	 * @return the location of the copied image
	 */
	public static String copyImage(final Image image) {
		if (imageFolderPath != null && desinationFolderPath != null) {
			// Step 1: get the image input stream
			String imageURL = image.getUrl();
			InputStream imageInputStream;
			try {
				if (imageURL.startsWith("http")) {
					imageInputStream = new URL(imageURL).openStream();
				} else {
					// Case 2: URL is project-relative
					if (imageURL.startsWith("./")) {
						imageURL = relativeURLBasePath + imageURL;
					}
					// Case 3 (default): URL is absolute
					imageInputStream = new FileInputStream(imageURL);
				}

				// Step 2: get destination file
				File targetFolder = new File(desinationFolderPath + "/" + imageFolderPath);
				targetFolder.mkdirs();
				String fileName = "";
				if (imageURL.lastIndexOf('/') != -1) {
					fileName = imageURL.substring(imageURL.lastIndexOf('/'));
				} else {
					fileName = imageURL;
				}
				File targetFile = new File(targetFolder.getAbsolutePath() + "/" + fileName);

				// Step 3: copy image (only if it does not already exists)
				copyFile(imageInputStream, targetFile);
				if (imageInputStream != null) {
					imageInputStream.close();
				}
				return "../" + imageFolderPath + fileName;
			} catch (IOException e) {
				// Silent catch
			}
		}
		return image.getUrl();
	}

	/**
	 * Copies the file described by the given sourceStream into the given destination file.
	 * 
	 * @param sourceStream
	 *            the input stream of the source file to copy
	 * @param destFile
	 *            the destination file
	 * @throws IOException
	 *             if files cannot be properly accessed
	 */
	public static void copyFile(final InputStream sourceStream, final File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileOutputStream outputStream = new FileOutputStream(destFile);
		try {
			byte[] buf = new byte[BUFFER_SIZE];

			int len;

			while ((len = sourceStream.read(buf)) > 0) {

				outputStream.write(buf, 0, len);

			}

			sourceStream.close();

			outputStream.close();
		} finally {
			if (sourceStream != null) {
				sourceStream.close();
			}
		}
	}
}
