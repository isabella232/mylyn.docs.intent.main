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

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.FileToStringConverter;

/**
 * Checks that replacing all contents through copy/paste works fine.
 * <p>
 * Relevant issues : Errors when pasting a big document into a small one
 * </p>
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=379390
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class MultipleReplacementInEditorTest extends AbstractIntentUITest {

	private static final String INTENT_SMALL_DOC_PATH = "data/unit/documents/scenario/abstract_resources.intent";

	private static final String INTENT_BIG_DOC_PATH = "data/unit/documents/editorupdates/compareTest-03.intent";

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
		setUpIntentProject("intentProject", INTENT_SMALL_DOC_PATH, true);

		// Step 2 : open an editor on the root document
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	/**
	 * Ensures that pasting several times content inside the document does not cause any issue.
	 * 
	 * @throws IOException
	 */
	public void testCopyPastBigDocuments() throws IOException {
		String smallDocumentContent = FileToStringConverter.getFileAsString(new File(INTENT_SMALL_DOC_PATH));
		String bigDocumentContent = FileToStringConverter.getFileAsString(new File(INTENT_BIG_DOC_PATH));

		document.set(bigDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(smallDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(bigDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(smallDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(bigDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(smallDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(bigDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(smallDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(bigDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(smallDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(bigDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(smallDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
	}

	/**
	 * Ensures that pasting several times content inside the document, and removing all document content does
	 * not cause any issue.
	 * 
	 * @throws IOException
	 */
	public void testCopyPastAndEmptyDocuments() throws IOException {
		String smallDocumentContent = FileToStringConverter.getFileAsString(new File(INTENT_SMALL_DOC_PATH));
		String bigDocumentContent = FileToStringConverter.getFileAsString(new File(INTENT_BIG_DOC_PATH));

		document.set(bigDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set("Document {\n}");
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(bigDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set(smallDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		document.set("Document {\n}");
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
	}
}
