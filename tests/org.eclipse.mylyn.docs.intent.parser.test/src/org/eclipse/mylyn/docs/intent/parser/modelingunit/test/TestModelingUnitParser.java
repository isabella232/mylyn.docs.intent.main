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
//CHECKSTYLE:OFF
package org.eclipse.mylyn.docs.intent.parser.modelingunit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitParserImpl;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.ModelingUnitParsingTestConfigurator;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.XMISaver;
import org.eclipse.mylyn.docs.intent.parser.test.utils.FileToStringConverter;
import org.junit.Before;
import org.junit.Test;

//CHECKSTYLE:ON
/**
 * Test the parsing of ModelingUnit files.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TestModelingUnitParser {

	/**
	 * Parser used to parse modeling units.
	 */
	private static ModelingUnitParser modelingUnitParser;

	/**
	 * Initialization of the Test environment : launching the StandaloneParsingManager.
	 */
	@Before
	public void setUp() {
		modelingUnitParser = new ModelingUnitParserImpl();
		// Registering resource factories so that test can be laucnhed in standalone mode
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore",
				new XMIResourceFactoryImpl());
	}

	/**
	 * Parses the content of the file located at the given filePath, and compare the parse result to the
	 * expected result.
	 * 
	 * @param fileToTest
	 *            path of the file starting from the DATATESTS_FOLDER and without any extension
	 * @param supposedToWork
	 *            indicates if the parser is supposed to succeed.
	 * @return the file's AST
	 */
	static EObject parseAndCompareToExpected(String fileToTest, boolean supposedToWork) {

		// Step 1 : we parse the model and obtain the AST
		ModelingUnit parsedAST = null;
		try {
			parsedAST = (ModelingUnit)ModelingUnitParsingTestConfigurator.parseFile(modelingUnitParser,
					fileToTest);
			/*
			 * Resource r = new XtextResource(URI.createFileURI(ModelingUnitParsingTestConfigurator
			 * .getDatatestsFolder() + fileToTest + ModelingUnitParsingTestConfigurator.getFileExtensions()));
			 * r.getContents().add(parsedAST); for (Issue issue : modelingUnitParser.getValidationErrors(r)) {
			 * System.err.println(issue.getLineNumber() + ":" + issue.getMessage()); }
			 */

			// Step 2 : we save this model in an xmi file
			String actual = XMISaver.saveASXMI(parsedAST);

			// Step 3 : we get the file corresponding to the expected result.
			File expectedASTFile = new File(ModelingUnitParsingTestConfigurator.getExpectedFolder()
					+ fileToTest + ".xmi");
			// -- if the File doesn't exist, we save this AST as Expected AST.
			if (!expectedASTFile.canRead()) {
				XMISaver.saveASXMI(parsedAST, expectedASTFile);
			}

			// Step 4 : we compare the textual version of the two xmiFiles.
			String expected = FileToStringConverter.getFileAsString(expectedASTFile);
			assertEquals(expected, actual);
			assertTrue("File should have raised parsing exception", supposedToWork);
		} catch (ParseException e) {
			System.err.println("----------------------------------------");
			System.err.println("--- Parsing errors for file : " + fileToTest);
			System.err.println(e.getMessage());
			assertFalse("File should not have raised parsing exception", supposedToWork);
		} catch (IOException e) {
			e.printStackTrace();
			assertEquals(supposedToWork, false);
		}
		return parsedAST;

	}

	/**
	 * Test which purpose is to test the correct parsing of a simple Intent file.
	 */
	@Test
	public void testSimpleParsing() {
		parseAndCompareToExpected("simpleTests/SimpleModelingUnit", true);
	}

	/**
	 * Test which purpose is to test the correct parsing of a special values.
	 */
	@Test
	public void testSpecialvalues() {
		parseAndCompareToExpected("simpleTests/SpecialValues", true);
	}

	/**
	 * Test which purpose is to test the correct parsing of a complete Intent file.
	 */
	@Test
	public void testCompleteParsing() {
		parseAndCompareToExpected("simpleTests/CompleteModelingUnit", true);
	}

	/**
	 * Test which purpose is to test the correct parsing of a complete Intent file.
	 */
	@Test
	public void testCompleteParsing2() {
		parseAndCompareToExpected("simpleTests/CompleteModelingUnit2", true);
	}

	/**
	 * Test which purpose is to test the correct parsing of a complete Intent file.
	 */
	@Test
	public void testCompleteParsing3() {
		parseAndCompareToExpected("simpleTests/CompleteModelingUnit3", true);
	}

	/**
	 * Test which purpose is to test the correct parsing of label declarations.
	 */
	@Test
	public void testLabelParsing() {
		parseAndCompareToExpected("simpleTests/AllKindOfLabels", true);
	}

	/**
	 * Test which purpose is to test the correct parsing of resource declarations.
	 */
	@Test
	public void resourceDeclaration() {
		parseAndCompareToExpected("resourcesRelatedTest/resourceDeclaration", true);
	}

	/* Parsings supposed to fail */

	/**
	 * Test which purpose is to test that an invalid linking (declaration of an Annotation with no possible
	 * target) is detected an throw an appropriate ParseError.
	 */
	@Test
	public void invalidAnnotationLinking() {
		parseAndCompareToExpected("incorrectFiles/invalidAnnotationLinking", false);
	}

	/**
	 * Test which purpose is to test that an invalid linking (declaration of an Label with no possible target)
	 * is detected an throw an appropriate ParseError.
	 */
	@Test
	public void invalidLabelLinking() {
		parseAndCompareToExpected("incorrectFiles/invalidLabelLinking", true);
	}

	/**
	 * Test which purpose is to test that an invalid linkin g(declaration of an Refrence with no possible
	 * target) is detected an throw an appropriate ParseError.
	 */
	@Test
	public void invalidSectionRefrenceLinking() {
		parseAndCompareToExpected("incorrectFiles/invalidSectionRefrenceLinking", false);
	}

	/**
	 * Test which purpose is to test that an parsing of invalid files throw an appropriate ParseError.
	 */
	@Test
	public void invalidParsing() {
		parseAndCompareToExpected("incorrectFiles/invalidModelingUnit", false);
	}

	/**
	 * Test which purpose is to test the given modeling unit, declaragin element and resource in the same
	 * time.
	 */
	@Test
	public void modelingAndResource() {
		parseAndCompareToExpected("simpleTests/ModelingAndResource", true);
	}

	/**
	 * Tests that {@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference} are
	 * correctly parsed.
	 */
	@Test
	public void externalReferencesParsing() {
		parseAndCompareToExpected("simpleTests/ExternalContentReferences", true);
	}
	// @Test
	// public void testDiagnostic() {
	//
	// ModelingUnit parsedAST = (ModelingUnit)parseAndCompareToExpected("simpleTests/CompleteModelingUnit",
	// true);
	// // ((LabelinModelingUnit)parsedAST.getInstructions().get(0)).setUnit(ModelingUnitFactory.eINSTANCE
	// // .createModelingUnit());
	// // modelingUnitParser.validateAST(parsedAST);
	// }
}
