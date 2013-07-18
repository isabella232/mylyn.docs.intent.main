/*******************************************************************************
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.compare.test.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.eclipse.mylyn.docs.intent.compare.IntentASTMerger;
import org.eclipse.mylyn.docs.intent.compare.MergingException;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.parser.IntentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;

/**
 * Abstract definition of a comparison test.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractEMFCompareTest extends TestCase {

	/**
	 * Compares the two elements.
	 * 
	 * @param left
	 *            the left element
	 * @param right
	 *            the right element
	 */
	protected void compareAndMergeDiffs(IntentStructuredElement left, IntentStructuredElement right) {
		try {
			new IntentASTMerger().mergeFromLocalToRepository(left, right);
		} catch (MergingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Parses the given document.
	 * 
	 * @param content
	 *            the document as string
	 * @return the parser result
	 * @throws ParseException
	 *             if content cannot be properly parsed
	 */
	protected IntentStructuredElement parseIntentDocument(String content) throws ParseException {
		return (IntentStructuredElement)new IntentParser().parse(content);
	}

	/**
	 * Returns the content of the given file as a String.
	 * 
	 * @param file
	 *            file containing the information to extract.
	 * @return the content of the given file as a String
	 * @throws IOException
	 *             if the file doesn't exists
	 */
	protected static String getFileAsString(File file) throws IOException {
		String result = "";
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedReader dis = null;
		StringBuffer sb = new StringBuffer();

		fis = new FileInputStream(file);
		bis = new BufferedInputStream(fis);
		dis = new BufferedReader(new InputStreamReader(bis));

		while (dis.ready()) {
			sb.append(dis.readLine() + "\n");
		}

		fis.close();
		bis.close();
		dis.close();

		result = sb.toString();
		return result;

	}
}
