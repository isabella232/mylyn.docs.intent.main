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

import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.junit.Test;

/**
 * Test the parsing errors on modeling units.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class TestModelingUnitErrors extends AbstractTestParserErrors {

	@Test
	public void testNoErrors() {
		testNoErrorsOnFile("dataTests/intentDocuments/scalability/uml.intent");
	}

	// CHECKSTYLE:OFF

	@Test
	public void testResource() {
		testErrorsOnFile("dataTests/intentDocuments/errors/resource.intent", new ParseException(
				UNRECOGNIZED_CONTENT_ERROR, 77, 68));
	}

	@Test
	public void testProperty() {
		testErrorsOnFile("dataTests/intentDocuments/errors/property.intent", new ParseException(
				UNRECOGNIZED_CONTENT_ERROR, 95, 44));
	}

	@Test
	public void testOperator() {
		testErrorsOnFile("dataTests/intentDocuments/errors/operator.intent", new ParseException(
				UNRECOGNIZED_CONTENT_ERROR, 95, 44));
	}

	@Test
	public void testContribution() {
		testErrorsOnFile("dataTests/intentDocuments/errors/contribution.intent", new ParseException(
				"Contribution instruction uml { does not end correctly.", 499, 5));
	}

	@Test
	public void testInstanciation() {
		testErrorsOnFile("dataTests/intentDocuments/errors/instanciation.intent", new ParseException(
				"Instanciation instruction new EPackage uml { does not end correctly.", 358, 18));
	}

	@Test
	public void testResourceDeclaration() {
		testErrorsOnFile("dataTests/intentDocuments/errors/resource_declaration.intent", new ParseException(
				"Resource declaration Resource r { does not end correctly.", 77, 12));
	}

	@Test
	public void testSingleAffectation() {
		testErrorsOnFile("dataTests/intentDocuments/errors/single_affectation.intent", new ParseException(
				"Affectation URI = does not end correctly.", 95, 6));
	}

	@Test
	public void testMultipleAffectation() {
		testErrorsOnFile("dataTests/intentDocuments/errors/multiple_affectation.intent", new ParseException(
				"Affectation content += does not end correctly.", 215, 11));
	}
	// CHECKSTYLE:ON

}
