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
package org.eclipse.mylyn.docs.intent.client.compiler.test.unit;

import org.eclipse.mylyn.docs.intent.client.compiler.test.util.AbstractIntentCompilerTest;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests the correct behavior of Intent compiler model generation.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class GeneratedResourcesTest extends AbstractIntentCompilerTest {

	/**
	 * Compiles the given intent document, and compares the result with expected.
	 */
	@Test
	public void testSeveralResources() {
		compile("dataTests/resources/severalResources.intent");
	}

	/**
	 * Compiles the given intent document, and compares the result with expected.
	 */
	@Test
	public void testResourceDeclaration() {
		compile("dataTests/resources/resourceDeclaration.intent");
	}

	/**
	 * Compiles the given intent document, and compares the result with expected.
	 */
	@Test
	public void testDynamicInstance() {
		compile("dataTests/resources/dynamicInstance.intent");
	}

	/**
	 * Compiles the given intent document, and compares the result with expected.
	 */
	@Test
	public void testDataType() {
		compile("dataTests/resources/dataType.intent");
	}

	/**
	 * Compiles the given intent document, and compares the result with expected.
	 */
	@Test
	public void testQualifiedName() {
		compile("dataTests/resources/qualifiedName.intent");
	}

	/**
	 * Compiles the given intent document, and compares the result with expected.
	 */
	@Test
	public void testQualifiedNameNewInstance() {
		compile("dataTests/resources/qualifiedNameNewInstance.intent");
	}

	/**
	 * Compiles the given intent document, and compares the result with expected.
	 */
	@Test
	public void testEnumerations() {
		compile("dataTests/resources/enumerations.intent");
	}

	/**
	 * Compiles the given intent document, and compares the result with expected.
	 */
	@Test
	@Ignore
	public void testReference() {
		compile("dataTests/resources/reference.intent");
	}
}
