/*******************************************************************************
 * Copyright (c) 2012 Obeo.
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
import java.util.List;

import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractZipBasedTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AnnotationUtils;
import org.eclipse.mylyn.docs.intent.parser.test.utils.FileToStringConverter;

/**
 * Abstract modeling unit updates test.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractUpdateTest extends AbstractZipBasedTest {

	/**
	 * The currently opened intent editor.
	 */
	protected IntentEditor editor;

	/**
	 * The document associated to the currently opened intent editor.
	 */
	protected IntentEditorDocument document;

	/**
	 * Constructor.
	 * 
	 * @param intentProjectArchivePath
	 *            path of the archive file
	 * @param intentProjectName
	 *            the intent project name
	 */
	public AbstractUpdateTest(String intentProjectArchivePath, String intentProjectName) {
		super(intentProjectArchivePath, intentProjectName);
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
	 * Ensures that the current document is equal to the file located at the given path.
	 * 
	 * @param expectedDocPath
	 *            the path of the file containing the expected document
	 * @throws IOException
	 *             the the final document cannot be read.
	 */
	protected void checkDocumentValidity(String expectedDocPath) throws IOException {
		// check that the document is valid
		List<IntentAnnotation> annotations = AnnotationUtils.getIntentAnnotations(editor,
				IntentAnnotationMessageType.SYNC_WARNING);
		if (!annotations.isEmpty()) {
			AnnotationUtils.displayAnnotations(editor);
		}
		assertEquals(FileToStringConverter.getFileAsString(new File(expectedDocPath)), document.get());
		assertTrue(annotations.isEmpty());
	}

	/**
	 * Fixes the issue associated to the Intent annotation with the given message using the quickfix.
	 * 
	 * @param message
	 *            the annotation message
	 */
	protected void fixIssue(String message) {
		fixIssueUsingQuickFix(editor, document, message);
	}

}
