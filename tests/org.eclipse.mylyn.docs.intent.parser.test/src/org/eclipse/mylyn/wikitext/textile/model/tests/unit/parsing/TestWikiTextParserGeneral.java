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
// CHECKSTYLE:OFF
package org.eclipse.mylyn.wikitext.textile.model.tests.unit.parsing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;
import org.eclipse.mylyn.docs.intent.markup.resource.WikitextResourceFactory;
import org.eclipse.mylyn.docs.intent.markup.serializer.WikiTextResourceSerializer;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.WikiTextParserTestConfigurator;
import org.eclipse.mylyn.docs.intent.parser.test.utils.FileToStringConverter;
import org.junit.Test;

//CHECKSTYLE:ON

/**
 * Test simple parsing of textile files.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TestWikiTextParserGeneral {

	/**
	 * From the given textile file, returns a table containing:
	 * <ul>
	 * <li>the expected result (as a textile)</li>
	 * <li>the actual result (from file)</li>
	 * <li>the actual result (textile serialization obtained from parsed model).</li>
	 * </ul>
	 * 
	 * @param fileToTest
	 *            the textile file to test
	 * @return a result table
	 */
	static String[] getTextSerializations(String fileToTest) {
		String fileToGenerate = fileToTest;

		// Step 1 : creation of an inputStream on the file to Test.
		InputStream input = null;
		String[] result = new String[3];
		try {
			input = new FileInputStream(new File(WikiTextParserTestConfigurator.getDatatestsFolder()
					+ fileToTest));

			// Step 2 : creation of a Wikitext Resource linked from this
			// inputStream.
			Resource resourceTextile = new WikitextResourceFactory().createResource(URI
					.createFileURI(WikiTextParserTestConfigurator.getGeneratedFolder() + fileToGenerate));

			// Step 3 : Generation of a textile file.
			Map<String, String> options = new HashMap<String, String>();
			options.put(XMLResource.OPTION_ENCODING, "UTF8");

			resourceTextile.load(input, options);

			resourceTextile.save(options);

			// Step 4 : Build the result
			String expected;

			expected = FileToStringConverter.getFileAsString(new File(WikiTextParserTestConfigurator
					.getDatatestsFolder() + fileToTest));

			EPackage.Registry.INSTANCE.put(MarkupPackage.eNS_URI, MarkupPackage.eINSTANCE);

			final File file = new File(WikiTextParserTestConfigurator.getGeneratedFolder() + fileToGenerate);
			String actual = FileToStringConverter.getFileAsString(file);
			String fromResource = WikiTextResourceSerializer.getSerializer().serialize(resourceTextile);

			result[0] = expected;
			result[1] = actual;
			result[2] = fromResource;
		} catch (IOException e) {
			fail(e.getMessage());
		}
		return result;
	}

	/**
	 * Ensures that parsing and re-serializing the given file to test allows to obtain the same file.
	 * 
	 * @param fileToTest
	 *            the file to test
	 */
	static void compareTextSerialization(String fileToTest) {

		String[] serialization = getTextSerializations(fileToTest);
		String expected = serialization[0];
		String actual = serialization[1];
		String fromResource = serialization[2];

		// We ensure that the 2 files are equals,
		// and that these files are also equals to the resource's serialisation.
		assertEquals(expected, actual);
		assertEquals(expected, fromResource);

	}

	/*
	 * @Test public void testSimpleDocument() { // Objectives : // parse a simple document(entities, images,
	 * code and TOC). compareTextSerialization("simpleDocument.textile"); }
	 */

	/**
	 * Tests parsing of all types of lits (embedded...).
	 */
	@Test
	public void testLists() {
		compareTextSerialization("lists.textile");
	}

	/**
	 * Tests parsing of embedded and complex formats.
	 */
	@Test
	public void testFormats() {
		compareTextSerialization("manyFormats.textile");

	}

	/**
	 * Tests parsing of Images embedded in lists.
	 */
	@Test
	public void testEmbededImages() {
		compareTextSerialization("new_noteworthy.textile");
	}

	/**
	 * Tests CharacterEscaped Managment (for all HTMLEntities like <br/>
	 * see the SpecificElement Tests).
	 */
	@Test
	public void testCharacterEscaped() {
		compareTextSerialization("User_Guide.textile");
	}

	/**
	 * Tests Table parsing (and also blocQuotes ended by new Sections).
	 */
	@Test
	public void testTable() {
		compareTextSerialization("Using_Compare_Services.textile");
		compareTextSerialization("tables.textile");
	}

	/**
	 * Tests external links parsing.
	 */
	@Test
	public void testLinks() {
		compareTextSerialization("testLink.textile");
	}

	/**
	 * Tests fancy paragraphs with padding, alignement...
	 */
	@Test
	public void testFancyText() {
		compareTextSerialization("fancyText.textile");
	}

	/**
	 * Test on a complete example.
	 */
	@Test
	public void testCompleteExamples() {
		compareTextSerialization("EMF_Compare_Export_Tutorial.textile");
		compareTextSerialization("Adapting_Comparison_Process.textile");
	}

	/**
	 * Ensures that parser is able to handle big Textile file correctly and quickly.
	 */
	@Test
	public void performanceTest() {
		// Objectives :
		//
		compareTextSerialization("specifier-guide.textile");
	}

}
