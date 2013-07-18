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
package org.eclipse.mylyn.docs.intent.parser.errors.test;

//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import junit.framework.AssertionFailedError;

import org.eclipse.mylyn.docs.intent.parser.IntentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.parser.test.utils.FileToStringConverter;
import org.junit.Before;

//CHECKSTYLE:ON
/**
 * Utilities to test the parsing errors.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractTestParserErrors {

	/**
	 * Constant to indicate unrecognized content.
	 */
	protected static final String UNRECOGNIZED_CONTENT_ERROR = "Unrecognized content";

	/**
	 * Parser used to parse files.
	 */
	private static IntentParser parser;

	/**
	 * Initialization of the Test environment : launching the StandaloneParsingManager.
	 */
	@Before
	public void setUp() {
		parser = new IntentParser();
	}

	/**
	 * Tests for the given error on the given file.
	 * 
	 * @param fileName
	 *            the file
	 * @param expected
	 *            the expected error
	 * @throws AssertionFailedError
	 *             if the file doesn't contain the given error
	 */
	protected void testErrorsOnFile(String fileName, ParseException expected) throws AssertionFailedError {
		boolean errorFound = false;
		try {
			final File file = new File(fileName);
			parser.parse(FileToStringConverter.getFileAsString(file));
		} catch (IOException e) {
			throw new AssertionFailedError(e.getMessage());
		} catch (ParseException actual) {
			if (expected == null) {
				System.err.println("Error found on " + fileName + ", " + actual.getErrorOffset() + "("
						+ actual.getErrorLength() + ") : " + actual.getMessage());
			} else {
				assertEquals("different message", expected.getMessage(), actual.getMessage());
				assertEquals("different offset", expected.getErrorOffset(), actual.getErrorOffset());
				assertEquals("different length", expected.getErrorLength(), actual.getErrorLength());
				errorFound = true;
			}
		}
		if (!errorFound) {
			throw new AssertionFailedError("No parser errors");
		}
	}

	/**
	 * Tests that there are no errors on the given file.
	 * 
	 * @param fileName
	 *            the file to parse
	 */
	protected void testNoErrorsOnFile(String fileName) {
		try {
			final File file = new File(fileName);
			parser.parse(FileToStringConverter.getFileAsString(file));
		} catch (IOException e) {
			throw new AssertionFailedError(e.getMessage());
		} catch (ParseException actual) {
			throw new AssertionFailedError("There are parser errors");
		}
	}
}
