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

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.WorkspaceUtils;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;

/**
 * Tests the Intent demo, part 1: navigation behavior.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractDemoTest extends AbstractIntentUITest {

	protected static final String TEST_COMPILER_NO_ERROR_MSG = "The compiler failed to detect errors";

	protected static final String TEST_COMPILER_INVALID_ERROR_MSG = "The compiler detected invalid errors";

	protected static final String TEST_COMPILER_NO_INFO_MSG = "The compiler failed to detect infos";

	protected static final String TEST_COMPILER_INVALID_INFO_MSG = "The compiler detected invalid infos";

	protected static final String TEST_SYNCHRONIZER_NO_WARNING_MSG = "The synchronizer failed to detect errors";

	protected static final String TEST_SYNCHRONIZER_INVALID_WARNING_MSG = "The synchronizer failed to detect errors";

	private static final int TIME_TO_WAIT = 300;

	private static final String DEMO_ZIP_LOCATION = "data/unit/demo/demo.zip";

	private static final String BUNDLE_NAME = "org.eclipse.mylyn.docs.intent.client.ui.test";

	private static final String INTENT_PROJECT_NAME = "org.eclipse.emf.compare.idoc";

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Step 1 : import the demo projects
		WorkspaceUtils.unzipAllProjects(BUNDLE_NAME, DEMO_ZIP_LOCATION, new NullProgressMonitor());
		intentProject = ResourcesPlugin.getWorkspace().getRoot().getProject(INTENT_PROJECT_NAME);

		// Step 2 : setting the intent repository
		// and wait its complete initialization
		setUpRepository(intentProject);
		boolean repositoryInitialized = false;
		while (!repositoryInitialized) {
			try {
				Thread.sleep(TIME_TO_WAIT);
				Resource resource = repositoryAdapter
						.getResource(IntentLocations.TRACEABILITY_INFOS_INDEX_PATH);
				repositoryInitialized = resource != null
						&& !resource.getContents().isEmpty()
						&& ((TraceabilityIndex)resource.getContents().iterator().next()).getEntries().size() >= 3;
			} catch (WrappedException e) {
				// Try again
			}
		}
		registerRepositoryListener();
	}
}
