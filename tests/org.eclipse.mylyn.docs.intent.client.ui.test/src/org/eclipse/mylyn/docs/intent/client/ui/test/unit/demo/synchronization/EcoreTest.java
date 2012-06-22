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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.synchronization;

import java.io.IOException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.AbstractDemoTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AnnotationUtils;

/**
 * Tests the Intent demo, part 3: Ecore synchronization behavior.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class EcoreTest extends AbstractDemoTest {

	private static final String SYNC_WARNING_MESSAGE_RIGHT = "EAttribute literal in Right has changed.<br/><b>Current Document</b> : Right<br/><b>Working Copy</b> : New";

	private static final String SYNC_WARNING_MESSAGE_LEFT = "EAttribute literal in Left has changed.<br/><b>Current Document</b> : Left<br/><b>Working Copy</b> : Old";

	private static final String SYNC_WARNING_MESSAGE_ANCESTOR = "The EEnumLiteral Ancestor is defined in the <b>Current Document</b> model<br/>but not in the <b>Working Copy</b> model.";

	private static final String MATCH_MODEL_URI = "platform:/resource/org.eclipse.emf.compare.match/model/match.ecore";

	private static final int INSERTION_INDEX = 912;

	private static final String NEW_LITERAL_STRING = "\n\t\t\t\teLiterals += new EEnumLiteral {\n\t\t\t\t\tname = \"Ancestor\";\n\t\t\t\t\tliteral = \"Ancestor\";\n\t\t\t\t\tvalue = \"2\";\n\t\t\t\t};";

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
		editor = openIntentEditor(getIntentSection(4, 3, 3));
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	/**
	 * Ensures that synchronization errors between an Ecore model and a document are detected and can be
	 * fixed.
	 * 
	 * @throws IOException
	 *             can happen during model saving
	 */
	public void testModelChangeSynchronization() throws IOException {
		// Step 1 : modify the match.ecore model
		ResourceSet rs = new ResourceSetImpl();
		URI modelURI = URI.createURI(MATCH_MODEL_URI);
		Resource modelResource = rs.getResource(modelURI, true);
		EPackage matchPackage = (EPackage)modelResource.getContents().get(0);
		EEnum sideEnum = (EEnum)matchPackage.getEClassifier("Side");
		sideEnum.getEEnumLiteral("Left").setLiteral("Old");
		sideEnum.getEEnumLiteral("Right").setLiteral("New");

		// we start recording for any modification made on the repository
		repositoryListener.clearPreviousEntries();
		// save the changes made on the match.ecore model
		modelResource.save(null);
		// and wait the synchronizer to be notified
		waitForSynchronizer();

		// Step 2 : ensure that synchronization issues are detected
		assertTrue(TEST_SYNCHRONIZER_NO_WARNING_MSG, AnnotationUtils.hasIntentAnnotation(editor,
				IntentAnnotationMessageType.SYNC_WARNING, SYNC_WARNING_MESSAGE_LEFT, true));
		assertTrue(TEST_SYNCHRONIZER_NO_WARNING_MSG, AnnotationUtils.hasIntentAnnotation(editor,
				IntentAnnotationMessageType.SYNC_WARNING, SYNC_WARNING_MESSAGE_RIGHT, true));
		waitForSynchronizer(false);
		
		// Step 3 : update the document
		String initialContent = document.get();
		String newContent = initialContent;
		newContent = newContent.replaceFirst("literal = \"Left\"", "literal = \"Old\"");
		newContent = newContent.replaceFirst("literal = \"Right\"", "literal = \"New\"");
		document.set(newContent);
		repositoryListener.clearPreviousEntries();
		editor.doSave(new NullProgressMonitor());

		waitForCompiler();
		waitForSynchronizer();

		// Step 4 : ensure that synchronization issues no longer exists
		assertFalse(TEST_SYNCHRONIZER_INVALID_WARNING_MSG, AnnotationUtils.hasIntentAnnotation(editor,
				IntentAnnotationMessageType.SYNC_WARNING, SYNC_WARNING_MESSAGE_LEFT, true));
		assertFalse(TEST_SYNCHRONIZER_INVALID_WARNING_MSG, AnnotationUtils.hasIntentAnnotation(editor,
				IntentAnnotationMessageType.SYNC_WARNING, SYNC_WARNING_MESSAGE_RIGHT, true));
		waitForCompiler(false);
		waitForSynchronizer(false);

	}

	/**
	 * Ensures that synchronization errors between a document and an Ecore model are detected and can be
	 * fixed.
	 * 
	 * @throws IOException
	 *             if applying fix fails
	 * @throws InterruptedException
	 *             if comparison fails
	 */
	public void testDocumentChangeSynchronization() throws IOException, InterruptedException {
		// Step 1 : make additions to the document
		String initialContent = document.get();
		String newContent = initialContent.substring(0, INSERTION_INDEX) + NEW_LITERAL_STRING
				+ initialContent.substring(INSERTION_INDEX, initialContent.length());
		document.set(newContent);

		// Step 2 : we start recording for any modification made on the repository
		repositoryListener.clearPreviousEntries();
		// save
		editor.doSave(new NullProgressMonitor());
		// and wait the synchronizer and the compiler to be notified
		waitForCompiler();
		waitForSynchronizer();

		// Step 3 : ensure that synchronization issues are detected
		IntentAnnotation annotation = AnnotationUtils.getIntentAnnotation(editor,
				IntentAnnotationMessageType.SYNC_WARNING, SYNC_WARNING_MESSAGE_ANCESTOR, true);
		assertNotNull(TEST_SYNCHRONIZER_NO_WARNING_MSG, annotation);
		waitForCompiler(false);
		waitForSynchronizer(false);

		// Step 4 : apply quick fix
		repositoryListener.clearPreviousEntries();
		AnnotationUtils.applyAnnotationFix(annotation);
		waitForSynchronizer();

		// Step 5 : ensure that synchronization issues no longer exists
		assertFalse(TEST_SYNCHRONIZER_INVALID_WARNING_MSG, AnnotationUtils.hasIntentAnnotation(editor,
				IntentAnnotationMessageType.SYNC_WARNING, SYNC_WARNING_MESSAGE_ANCESTOR, true));
		waitForCompiler(false);
		waitForSynchronizer(false);
	}

}
