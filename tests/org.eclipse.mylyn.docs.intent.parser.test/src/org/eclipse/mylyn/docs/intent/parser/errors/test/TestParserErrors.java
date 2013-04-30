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
 * Test the parsing errors for all partitions but modeling units.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class TestParserErrors extends AbstractTestParserErrors {

	@Test
	public void testMissingBracket() {
		final int expectedErrorposition = 10;
		final int expectedErrorLength = 22;
		testErrorsOnFile("dataTests/intentDocuments/errors/missing_bracket.intent", new ParseException(
				"Cannot handle any description unit here : only in sections or chapters.",
				expectedErrorposition, expectedErrorLength));
	}

	@Test
	public void testNewLines() {
		final int expectedErrorposition = 10;
		final int expectedErrorLength = 21;
		testErrorsOnFile("dataTests/intentDocuments/errors/newlines.intent", new ParseException(
				"Cannot handle any description unit here : only in sections or chapters.",
				expectedErrorposition, expectedErrorLength));
	}
}
