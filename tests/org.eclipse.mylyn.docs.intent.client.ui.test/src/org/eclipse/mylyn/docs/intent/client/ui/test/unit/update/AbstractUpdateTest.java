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
public class AbstractUpdateTest extends AbstractZipBasedTest {

	protected IntentEditor editor;

	protected IntentEditorDocument document;

	/**
	 * Constructor.
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
	 * Checks whether the doc is valid or not.
	 * 
	 * @throws IOException
	 *             the the final document cannot be read.
	 */
	protected void checkDocumentValidity(String finalDocPath) throws IOException {
		// check that the document is valid
		List<IntentAnnotation> annotations = AnnotationUtils.getIntentAnnotations(editor,
				IntentAnnotationMessageType.SYNC_WARNING);
		if (!annotations.isEmpty()) {
			AnnotationUtils.displayAnnotations(editor);
		}
		assertEquals(FileToStringConverter.getFileAsString(new File(finalDocPath)), document.get());
		assertTrue(annotations.isEmpty());
	}

}
