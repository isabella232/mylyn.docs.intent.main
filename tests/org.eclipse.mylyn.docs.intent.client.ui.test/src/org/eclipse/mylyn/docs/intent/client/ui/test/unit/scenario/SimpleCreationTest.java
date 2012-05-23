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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.scenario;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;

/**
 * <p>
 * Ensures that the most simple creation test works.
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class SimpleCreationTest extends AbstractIntentUITest {

	private IntentEditor editor;

	private IntentEditorDocument document;

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Step 1 : Generic set up
		setUpIntentProject("intentProject");

		// Step 2 : open an editor on the root document
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	/**
	 * Ensures that abstract resources are not synchronized.
	 */
	public void testSimpleModifications() {
		document.set("Document {\n\tChapter Title {\n\t\tText\n\n\t\tSection Title {\n\t\t\tText\n\t\t}\n\t}\n\tChapter Title {\n\t\tText\n\t}\n}");
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set("Document {\n\tChapter C1 {\n\t\tText\n\n\t\tSection C11 {\n\t\t\tText\n\t\t}\n\t}\n\tChapter C2 {\n\t\tText\n\t}\n}");
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		if (editor != null) {
			editor.close(false);
		}
		super.tearDown();
	}
}
