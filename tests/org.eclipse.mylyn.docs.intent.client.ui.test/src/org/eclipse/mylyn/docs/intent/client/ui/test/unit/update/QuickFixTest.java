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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.update;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractZipBasedTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AnnotationUtils;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.FileToStringConverter;

/**
 * Tests the quick fixes updates.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class QuickFixTest extends AbstractZipBasedTest {
	private IntentEditor editor;

	private IntentEditorDocument document;

	/**
	 * Constructor.
	 */
	public QuickFixTest() {
		super("data/unit/documents/quickfixes/intentProject.zip", "intentProject");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.AbstractDemoTest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	/**
	 * Test that the model element changes are fixed.
	 * 
	 * @throws IOException
	 *             if comparison fails
	 */
	public void testQuickFixes() throws IOException {
		AnnotationUtils.displayAnnotations(editor);
		fixIssue("The EClass A is defined in the <b>Working Copy</b> model<br/>but not in the <b>Current Document</b> model.");
		fixIssue("The EPackage sub is defined in the <b>Working Copy</b> model<br/>but not in the <b>Current Document</b> model.");
		assertTrue(AnnotationUtils.getIntentAnnotations(editor, IntentAnnotationMessageType.SYNC_WARNING)
				.isEmpty());
		String finalUpdatedDocText = FileToStringConverter.getFileAsString(new File(
				"data/unit/documents/quickfixes/final.intent"));
		assertEquals(finalUpdatedDocText, document.get());
	}

	/**
	 * Fix the issue associated to the given message.
	 * 
	 * @param message
	 *            the annotation message
	 */
	private void fixIssue(String message) {
		for (IntentAnnotation annotation : AnnotationUtils.getIntentAnnotations(editor,
				IntentAnnotationMessageType.SYNC_WARNING)) {
			if (annotation.getText().equals(message)) {
				AnnotationUtils.applyAnnotationFix(document, repositoryAdapter, annotation, 1);
				editor.doSave(new NullProgressMonitor());
				// and wait the synchronizer and the compiler to be notified
				waitForCompiler();
				waitForSynchronizer();
				return;
			}
		}
		fail("Annotation not found: " + message);
	}

}
