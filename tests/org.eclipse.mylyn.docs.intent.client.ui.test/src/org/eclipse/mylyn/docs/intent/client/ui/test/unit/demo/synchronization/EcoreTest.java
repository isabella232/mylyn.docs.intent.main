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
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.AbstractDemoTest;

/**
 * Tests the Intent demo, part 3: Ecore synchronization behavior.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class EcoreTest extends AbstractDemoTest {

	private static final int SYNC_DELAY = 1000;

	private static final String SYNC_WARNING_MESSAGE_RIGHT = "EAttribute literal in Right has changed.<br/><b>Current Document</b> : Right<br/><b>Working Copy</b> : New";

	private static final String SYNC_WARNING_MESSAGE_LEFT = "EAttribute literal in Left has changed.<br/><b>Current Document</b> : Left<br/><b>Working Copy</b> : Old";

	private static final String SYNCHRONIZATION_FAILED_MSG = "The synchronizer failed to detect the warning";

	private static final String MATCH_MODEL_URI = "platform:/resource/org.eclipse.emf.compare.match/model/match.ecore";

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

		waitForAllOperationsInUIThread(); // TODO CHECK CONSISTENCY

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
		modelResource.save(null);

		waitForSynchronizer(); // TODO CHECK CONSISTENCY
		waitForAllOperationsInUIThread();

		// Step 2 : ensure that synchronization issues are detected
		assertTrue(
				SYNCHRONIZATION_FAILED_MSG,
				hasIntentAnnotation(editor, IntentAnnotationMessageType.SYNC_WARNING,
						SYNC_WARNING_MESSAGE_LEFT, true));
		assertTrue(
				SYNCHRONIZATION_FAILED_MSG,
				hasIntentAnnotation(editor, IntentAnnotationMessageType.SYNC_WARNING,
						SYNC_WARNING_MESSAGE_RIGHT, true));

		// Step 3 : update the document
		String initialContent = document.get();
		String newContent = initialContent;
		newContent = newContent.replaceFirst("literal = \"Left\"", "literal = \"Old\"");
		newContent = newContent.replaceFirst("literal = \"Right\"", "literal = \"New\"");
		document.set(newContent);
		editor.doSave(new NullProgressMonitor());

		waitForSynchronizer(); // TODO CHECK CONSISTENCY
		waitForAllOperationsInUIThread();

		// Step 4 : ensure that synchronization issues no longer exists
		assertFalse(
				SYNCHRONIZATION_FAILED_MSG,
				hasIntentAnnotation(editor, IntentAnnotationMessageType.SYNC_WARNING,
						SYNC_WARNING_MESSAGE_LEFT, true));
		assertFalse(
				SYNCHRONIZATION_FAILED_MSG,
				hasIntentAnnotation(editor, IntentAnnotationMessageType.SYNC_WARNING,
						SYNC_WARNING_MESSAGE_RIGHT, true));

	}

	// TODO
	// /**
	// * Ensures that synchronization errors between a document and an Ecore model are detected and can be
	// * fixed.
	// */
	// public void testDocumentChangeSynchronization() {
	// // Step 1 : make additions to the document
	// // Step 2 : ensure that synchronization issues are detected
	// // Step 3 : apply quick fix
	// // Step 4 : ensure that synchronization issues no longer exists
	// }

	private static void waitForSynchronizer() {
		try {
			Thread.sleep(SYNC_DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
