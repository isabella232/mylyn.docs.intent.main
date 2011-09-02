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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractUITest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.WorkspaceUtils;

/**
 * Tests the Intent demo.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class DemoTest extends AbstractUITest {
	private static final String DEMO_ZIP_LOCATION = "data/unit/demo/demo.zip";

	private static final String BUNDLE_NAME = "org.eclipse.mylyn.docs.intent.client.ui.test";

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractUITest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		WorkspaceUtils.unzipAllProjects(BUNDLE_NAME, DEMO_ZIP_LOCATION, new NullProgressMonitor());
	}

	/**
	 * Tests the demo.
	 */
	public void testDemo() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractUITest#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			project.delete(true, true, new NullProgressMonitor());
		}
		super.tearDown();
	}
}
