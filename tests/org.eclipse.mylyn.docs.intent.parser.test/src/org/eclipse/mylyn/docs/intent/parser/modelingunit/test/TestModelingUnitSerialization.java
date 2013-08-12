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
package org.eclipse.mylyn.docs.intent.parser.modelingunit.test;

//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitFormatter;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitParserImpl;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.serializer.ModelingUnitSerializer;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.ModelingUnitParsingTestConfigurator;
import org.eclipse.mylyn.docs.intent.parser.test.utils.FileToStringConverter;
import org.junit.Ignore;
import org.junit.Test;

//CHECKSTYLE:ON
/**
 * Tests the serialization of Modeling Units.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TestModelingUnitSerialization {

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 * 
	 * @param fileToTest
	 *            the name of the file to test
	 * @param supposedToWork
	 *            if false, the file should contain syntax errors (and hence is not supposed to be parseable)
	 */
	public void parseAndCompareSerializationToExpected(String fileToTest, boolean supposedToWork) {
		// Step 1 : we parse the model and obtain the AST
		ModelingUnitParser modelingUnitParser = new ModelingUnitParserImpl();
		ModelingUnitSerializer modelingUnitSerializer = new ModelingUnitSerializer();
		ModelingUnit parsedAST;
		try {
			parsedAST = (ModelingUnit)ModelingUnitParsingTestConfigurator.parseFile(modelingUnitParser,
					fileToTest);

			// xtextRes.getContents().add(parsedAST);

			// Step 2 : we serialize this AST
			String actual = modelingUnitSerializer.serialize(parsedAST);
			actual = ModelingUnitFormatter.indentAccordingToBrackets(modelingUnitSerializer, actual);

			// Step 3 : we get the file as a String
			File expectedFile = new File(ModelingUnitParsingTestConfigurator.getDatatestsFolder()
					+ fileToTest + ModelingUnitParsingTestConfigurator.getFileExtensions());
			String expected = FileToStringConverter.getFileAsString(expectedFile);

			// Step 4 : we compare these to String
			assertEquals(expected, actual);
			assertEquals(supposedToWork, true);
		} catch (ParseException e) {
			System.err.println("----------------------------------------");
			System.err.println("Parsing errors for file : " + fileToTest);
			System.err.println(e.getMessage());
			assertEquals(supposedToWork, false);
		} catch (IOException e) {
			e.printStackTrace();
			assertEquals(supposedToWork, false);
		}
	}

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 */
	@Test
	public void testSimpleSerialization() {
		parseAndCompareSerializationToExpected("simpleTests/SimpleModelingUnit", true);
	}

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 */
	@Test
	public void testSpecialvalues() {
		parseAndCompareSerializationToExpected("simpleTests/SpecialValues", true);
	}

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 */
	@Test
	public void testCompleteSerialization() {
		parseAndCompareSerializationToExpected("simpleTests/CompleteModelingUnit", true);
		parseAndCompareSerializationToExpected("simpleTests/CompleteModelingUnit2", true);
	}

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 */
	@Test
	@Ignore
	// not supported
	public void testCommentsSerialization() {
		parseAndCompareSerializationToExpected("simpleTests/CompleteModelingUnit3", true);
	}

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 */
	@Test
	public void testLabelsSerialization() {
		parseAndCompareSerializationToExpected("simpleTests/AllKindOfLabels", true);
	}

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 */
	@Test
	public void testResourceDeclarationSerialization() {
		parseAndCompareSerializationToExpected("resourcesRelatedTest/resourceDeclaration", true);
	}

	/**
	 * Ensures that parsing and re-serializing the given file does not modify the file.
	 */
	@Test
	public void testQualifiedNamesSerialization() {
		parseAndCompareSerializationToExpected("qualifiedNames/qualifiedName", true);
		parseAndCompareSerializationToExpected("qualifiedNames/qualifiedNameNewInstance", true);
	}

	/**
	 * Tests that {@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference} are
	 * correctly serialized.
	 */
	@Test
	public void testExternalContentReferencesSerialization() {
		parseAndCompareSerializationToExpected("simpleTests/ExternalContentReferences", true);
	}
}
