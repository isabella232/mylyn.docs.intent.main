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

/**
 * Tests the correct behavior of Intent compiler model generation.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class GeneratedResourcesTests extends AbstractIntentCompilerTest {

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testSeveralResources() {
		compile("dataTests/resources/severalResources.intent");
	}

	public void testResourceDeclaration() {
		compile("dataTests/resources/resourceDeclaration.intent");
	}
}
