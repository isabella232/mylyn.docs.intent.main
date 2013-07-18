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
package org.eclipse.mylyn.docs.intent.parser.test;

//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import junit.framework.AssertionFailedError;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.parser.IntentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.parser.test.utils.FileToStringConverter;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;
import org.junit.Test;

//CHECKSTYLE:ON
/**
 * Tests for the IntentParser.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentParserTest {

	/**
	 * The parser used to parse intent content.
	 */
	private IntentParser parser;

	/**
	 * The serializer used to serialize intent elements.
	 */
	private IntentSerializer serializer;

	/**
	 * IntentParserTest constructor.
	 */
	public IntentParserTest() {
		parser = new IntentParser();
		serializer = new IntentSerializer();
	}

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 */
	@Test
	public void testSerialization() {
		try {
			File file = new File("dataTests/intentDocuments/intentdocumentspecification/document.intent");

			String section = FileToStringConverter.getFileAsString(file);
			EObject generated = parser.parse(section);
			// XMISaver.saveASXMI(generated, new File("expectedResults/intentDocuments/intentDocument.xmi"));
			assertEquals(section, serializer.serialize(generated));
		} catch (IOException e) {
			throw new AssertionFailedError(e.getMessage());
		} catch (ParseException e) {
			throw new AssertionFailedError(e.getMessage());
		}
	}

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 */
	@Test
	public void testSerializationWithSingleReference() {
		try {
			File file = new File("dataTests/intentDocuments/intentdocumentspecification/indentation.intent");

			String section = FileToStringConverter.getFileAsString(file);
			EObject generated = parser.parse(section);
			assertEquals(section, serializer.serialize(generated));
		} catch (IOException e) {
			throw new AssertionFailedError(e.getMessage());
		} catch (ParseException e) {
			throw new AssertionFailedError(e.getMessage());
		}
	}

	/**
	 * Tests parser and serializer behavior when faced to big documents.
	 */
	@Test
	public void testScalability() {
		try {
			File file = new File("dataTests/intentDocuments/scalability/uml.intent");

			String section = FileToStringConverter.getFileAsString(file);
			EObject generated = parser.parse(section);
			assertEquals(section, serializer.serialize(generated));
		} catch (IOException e) {
			throw new AssertionFailedError(e.getMessage());
		} catch (ParseException e) {
			throw new AssertionFailedError(e.getMessage());
		}
	}

	/**
	 * Ensures that Description Units are correctly serialized.
	 */
	@Test
	public void testDescriptionUnitOrder() {
		try {
			File file = new File("dataTests/intentDocuments/intentdocumentspecification/du_order.intent");

			String section = FileToStringConverter.getFileAsString(file);
			EObject generated = parser.parse(section);
			// ArrayList<?> arrayList = new ArrayList<Object>(
			// ((Paragraph)((DescriptionBloc)((IntentDocument)generated).getChapters().iterator().next()
			// .getDescriptionUnits().iterator().next().getInstructions().iterator().next())
			// .getDescriptionBloc().getContent().iterator().next()).getContent());

			assertEquals(section, serializer.serialize(generated));
		} catch (IOException e) {
			throw new AssertionFailedError(e.getMessage());
		} catch (ParseException e) {
			throw new AssertionFailedError(e.getMessage());
		}
	}

	/**
	 * Tests parser and serializer behavior when faced to a line starting with spaces instead of tabs.
	 */
	@Test
	public void testSpaces() {
		try {
			File file = new File("dataTests/intentDocuments/intentdocumentspecification/spaces.intent");

			String section = FileToStringConverter.getFileAsString(file);
			EObject generated = parser.parse(section);
			assertEquals(section, serializer.serialize(generated));
		} catch (IOException e) {
			throw new AssertionFailedError(e.getMessage());
		} catch (ParseException e) {
			throw new AssertionFailedError(e.getMessage());
		}
	}

}
