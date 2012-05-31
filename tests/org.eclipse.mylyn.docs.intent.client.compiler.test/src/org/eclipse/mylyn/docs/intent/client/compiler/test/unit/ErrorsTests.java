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
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusSeverity;

/**
 * Tests the correct behavior of Intent compiler errors.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class ErrorsTests extends AbstractIntentCompilerTest {

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testNoResourceCreated() {
		compile("dataTests/errors/noResourceCreated.intent");
		checkCompilationStatus(CompilationStatusSeverity.ERROR, "The reference p1 cannot be resolved. ");
	}

	public void testIncorrectType() {
		compile("dataTests/errors/featureMapEntry.intent");
	}
}
