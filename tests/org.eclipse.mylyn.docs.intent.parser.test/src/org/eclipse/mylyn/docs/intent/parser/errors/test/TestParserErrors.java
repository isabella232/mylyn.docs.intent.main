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

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.eclipse.mylyn.docs.intent.parser.IntentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.FileToStringConverter;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the parsing errors.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class TestParserErrors {

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

	// CHECKSTYLE:ON

	/**
	 * Tests for the given error on the given file.
	 * 
	 * @param fileName
	 *            the file
	 * @param expected
	 *            the expected error
	 * @throws AssertionFailedError
	 */
	private void testErrorsOnFile(String fileName, ParseException expected) throws AssertionFailedError {
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
				Assert.assertEquals("different message", expected.getMessage(), actual.getMessage());
				Assert.assertEquals("different offset", expected.getErrorOffset(), actual.getErrorOffset());
				Assert.assertEquals("different length", expected.getErrorLength(), actual.getErrorLength());
				errorFound = true;
			}
		}
		if (!errorFound) {
			throw new AssertionFailedError("No parser errors");
		}
	}

	@Test
	public void testNoErrors() {
		try {
			final File file = new File("dataTests/intentDocuments/scalability/uml.intent");
			parser.parse(FileToStringConverter.getFileAsString(file));
		} catch (IOException e) {
			throw new AssertionFailedError(e.getMessage());
		} catch (ParseException actual) {
			throw new AssertionFailedError("There are parser errors");
		}
	}

	// CHECKSTYLE:OFF

	@Test
	public void testResource() {
		testErrorsOnFile("dataTests/intentDocuments/errors/resource.intent", new ParseException(
				"Unrecognized content", 77, 68));
	}

	@Test
	public void testProperty() {
		testErrorsOnFile("dataTests/intentDocuments/errors/property.intent", new ParseException(
				"Unrecognized content", 95, 44));
	}

	@Test
	public void testOperator() {
		testErrorsOnFile("dataTests/intentDocuments/errors/operator.intent", new ParseException(
				"Unrecognized content", 95, 44));
	}

	// CHECKSTYLE:ON

}
