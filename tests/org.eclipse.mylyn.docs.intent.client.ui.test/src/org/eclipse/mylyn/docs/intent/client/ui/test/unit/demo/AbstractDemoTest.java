/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo;

import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractZipBasedTest;

/**
 * Tests the Intent demo, part 1: navigation behavior.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractDemoTest extends AbstractZipBasedTest {

	/**
	 * Constant used to create assertion failure messages.
	 */
	protected static final String TEST_COMPILER_NO_ERROR_MSG = "The compiler failed to detect errors";

	/**
	 * Constant used to create assertion failure messages.
	 */
	protected static final String TEST_COMPILER_INVALID_ERROR_MSG = "The compiler detected invalid errors";

	/**
	 * Constant used to create assertion failure messages.
	 */
	protected static final String TEST_COMPILER_NO_INFO_MSG = "The compiler failed to detect infos";

	/**
	 * Constant used to create assertion failure messages.
	 */
	protected static final String TEST_COMPILER_INVALID_INFO_MSG = "The compiler detected invalid infos";

	/**
	 * Constant used to create assertion failure messages.
	 */
	protected static final String TEST_SYNCHRONIZER_NO_WARNING_MSG = "The synchronizer failed to detect errors";

	/**
	 * Constant used to create assertion failure messages.
	 */
	protected static final String TEST_SYNCHRONIZER_INVALID_WARNING_MSG = "The synchronizer failed to detect errors";

	/**
	 * Location of the test archive file.
	 */
	private static final String DEMO_ZIP_LOCATION = "data/unit/demo/demo.zip";

	/**
	 * Name of the intent project.
	 */
	private static final String INTENT_PROJECT_NAME = "org.eclipse.emf.compare.idoc";

	/**
	 * Constructor.
	 */
	public AbstractDemoTest() {
		super(DEMO_ZIP_LOCATION, INTENT_PROJECT_NAME);
	}

}
