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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.compilation;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.AbstractDemoTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AnnotationUtils;

/**
 * Tests the Intent demo, part 2: compilation behavior.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class CompileTest extends AbstractDemoTest {

	private static final String ERROR_TEXT_PATTERN = "nsPrefixx = \"match\";";

	private static final String NO_ERROR_TEXT_PATTERN = "nsPrefix = \"match\";";

	private static final String COMPILATION_ERROR_MESSAGE = "The feature nsPrefixx doesn't exists in EPackage";

	private static final String INFOS_TEXT_PATTERN = "nsURI = \"\";";

	private static final String NO_INFOS_TEXT_PATTERN = "nsURI = \"http://www.eclipse.org/emf/compare/match/1.1\";";

	private static final String COMPILATION_INFO_MESSAGE = "-The namespace URI '' is not well formed";

	private IntentEditor editor;

	private IntentEditorDocument document;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.AbstractDemoTest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Initialization : opening an editor on the document
		editor = openIntentEditor(getIntentSection(4, 1));
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	/**
	 * Ensures that compilation errors are detected and can be fixed.
	 */
	public void testCompilationErrors() {
		// Step 1 : update section by adding incorrect content
		String initialContent = document.get();
		String newContent = initialContent.replaceFirst(NO_ERROR_TEXT_PATTERN, ERROR_TEXT_PATTERN);
		document.set(newContent);
		editor.doSave(new NullProgressMonitor());

		waitForCompiler();

		// Step 2 : ensure that the compilation error has been detected
		assertTrue(TEST_COMPILER_NO_ERROR_MSG, AnnotationUtils.hasIntentAnnotation(editor,
				IntentAnnotationMessageType.COMPILER_ERROR, COMPILATION_ERROR_MESSAGE, true));

		// Step 3 : fix the error by resetting the content
		document.set(initialContent);
		editor.doSave(new NullProgressMonitor());

		waitForCompiler();

		// Step 4 : ensure that the compilation error no longer exists
		assertFalse(TEST_COMPILER_INVALID_ERROR_MSG, AnnotationUtils.hasIntentAnnotation(editor,
				IntentAnnotationMessageType.COMPILER_ERROR, COMPILATION_ERROR_MESSAGE, true));
	}

	/**
	 * Ensures that compilation informations are detected and can be fixed.
	 */
	public void testCompilationInfos() {
		// Step 1 : update section by adding incorrect content
		String initialContent = document.get();
		String newContent = initialContent.replaceFirst(NO_INFOS_TEXT_PATTERN, INFOS_TEXT_PATTERN);
		document.set(newContent);
		editor.doSave(new NullProgressMonitor());

		waitForCompiler();

		// Step 2 : ensure that the compilation info has been detected
		assertTrue(TEST_COMPILER_NO_INFO_MSG, AnnotationUtils.hasIntentAnnotation(editor,
				IntentAnnotationMessageType.COMPILER_INFO, COMPILATION_INFO_MESSAGE, true));

		// Step 3 : fix the info by resetting the content
		document.set(initialContent);
		editor.doSave(new NullProgressMonitor());

		waitForCompiler();

		// Step 4 : ensure that the compilation info no longer exists
		assertFalse(TEST_COMPILER_INVALID_INFO_MSG, AnnotationUtils.hasIntentAnnotation(editor,
				IntentAnnotationMessageType.COMPILER_INFO, COMPILATION_INFO_MESSAGE, true));
	}

}
