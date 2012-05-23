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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.compare;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.FileToStringConverter;

/**
 * Tests the correct behavior of Intent editor changes. Ensures that EMF Compare is able to maintain order and
 * structure of the Intent document.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class SimpleOrderTests extends AbstractIntentUITest {

	private static final String SAMPLES_PATH = "data/unit/documents/editorupdates/order/";

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
		setUpIntentProject("intentProject");
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	public void testNewSection() {
		checkSample("newSection");
	}

	public void testNewMU() {
		checkSample("newMU");
	}

	private void checkSample(String sampleName) {
		try {
			// inits the test
			String init = FileToStringConverter.getFileAsString(new File(SAMPLES_PATH + sampleName
					+ ".intent"));
			document.set(init);
			editor.doSave(new NullProgressMonitor());
			waitForAllOperationsInUIThread();

			// make the first change
			String update1 = FileToStringConverter.getFileAsString(new File(SAMPLES_PATH + sampleName
					+ ".update1.intent"));
			document.set(update1);
			editor.doSave(new NullProgressMonitor());
			waitForAllOperationsInUIThread();

			// make the second change
			String update2 = FileToStringConverter.getFileAsString(new File(SAMPLES_PATH + sampleName
					+ ".update2.intent"));
			document.set(update2);
			editor.doSave(new NullProgressMonitor());
			waitForAllOperationsInUIThread();

			assertEquals(update2, document.get());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
