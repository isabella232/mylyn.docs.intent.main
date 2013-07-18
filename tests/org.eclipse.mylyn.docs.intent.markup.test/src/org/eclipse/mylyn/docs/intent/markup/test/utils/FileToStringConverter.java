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
package org.eclipse.mylyn.docs.intent.markup.test.utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * File to String converter.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class FileToStringConverter {

	/**
	 * Private constructor.
	 */
	private FileToStringConverter() {
		// prevents instantiation
	}

	/**
	 * Returns the content of the given file as a string.
	 * 
	 * @param file
	 *            the file
	 * @return the content of the given file as a string
	 */
	public static String getFileAsString(File file) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		StringBuffer sb = new StringBuffer();
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			while (dis.available() != 0) {
				sb.append(dis.readLine() + "\n");
			}
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = sb.toString();
		return result;
	}

	/**
	 * Fix encoding issues in the given string.
	 * 
	 * @param stringToRedress
	 *            the string to redress
	 * @return the redressed string
	 */
	public static String encodingRedresser(String stringToRedress) {
		// CHECKSTYLE:OFF
		return stringToRedress.replace("Ã©", "é");
		// CHECKSTYLE:ON
	}
}
