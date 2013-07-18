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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.synchronizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractZipBasedTest;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.serializer.ParsedElementPosition;

/**
 * Ensures that the Intent project synchronizer is correctly notified.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class SynchronizerTest extends AbstractZipBasedTest {

	/**
	 * The current intent editor.
	 */
	protected IntentEditor editor;

	/**
	 * The document associated to the current intent editor.
	 */
	protected IntentEditorDocument document;

	/**
	 * Cache on the markers associated to the intent project.
	 */
	private IMarker[] markers;

	/**
	 * Constructor.
	 */
	public SynchronizerTest() {
		super("data/unit/documents/synchronizer/synchronizer.zip", "synchronizer");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractZipBasedTest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		editor = openIntentEditor();
		document = (IntentEditorDocument)(editor.getDocumentProvider().getDocument(editor.getEditorInput()));
	}

	/**
	 * Tests the synchronization status.
	 * 
	 * @throws Exception
	 */
	// CHECKSTYLE:OFF
	public void testSynchronizationStatus() throws Exception {
		markers = intentProject.findMarkers("org.eclipse.core.resources.problemmarker", true,
				IResource.DEPTH_INFINITE);

		assertMessageExists(
				"[Sync] The EPackage OnlyInCurrentDocument is defined in the Documentation model<br/>but not in the Working Copy model.",
				552, 35);
		assertMessageExists(
				"[Sync] The EClass OnlyInWorkingCopy is defined in the Working Copy model<br/>but not in the Documentation model.",
				222, 25);
		assertMessageExists("[Sync] The EClass A has been removed from the reference 'eSuperTypes'", 991, 28);
		assertMessageExists("[Sync] The EClass A has been removed from the reference 'eSuperTypes'", 1121, 28);
		assertMessageExists("[Sync] The EClass A has been removed from the reference 'eSuperTypes'", 991, 28);
		assertMessageExists("[Sync] The EClass A has been removed from the reference 'eSuperTypes'", 1121, 28);
		assertMessageExists("[Sync] The EClass B has been added to the reference 'eSuperTypes'", 1173, 1);
		assertMessageExists("[Sync] The EClass A has been removed from the reference 'eSuperTypes'", 991, 28);
		assertMessageExists("[Sync] The EClass A has been removed from the reference 'eSuperTypes'", 1121, 28);
		assertMessageExists("[Sync] The EClass A has been removed from the reference 'eSuperTypes'", 991, 28);
		assertMessageExists("[Sync] The EClass A has been removed from the reference 'eSuperTypes'", 1121, 28);
		assertMessageExists("[Sync] The EClass A has been added to the reference 'eSuperTypes'", 881, 1);
		assertMessageExists(
				"[Sync] The attribute 'abstract' in Attributes has changed.<br/>Documentation : true<br/>Working Copy : false",
				1326, 6);
	}

	// CHECKSTYLE:ON

	/**
	 * Checks that the given message exists at the given position.
	 * 
	 * @param message
	 *            the message
	 * @param offset
	 *            the sync warning offset in the document
	 * @param length
	 *            the sync warning length in the document
	 * @throws CoreException
	 *             if issue occur while getting markers
	 */
	private void assertMessageExists(String message, int offset, int length) throws CoreException {
		boolean found = false;
		for (IMarker marker : markers) {
			SynchronizerCompilationStatus status = (SynchronizerCompilationStatus)editor.getIntentContent()
					.eResource().getResourceSet()
					.getEObject(URI.createURI((String)marker.getAttribute(IMarker.LOCATION)), false);
			ParsedElementPosition position = document.getIntentPosition(status.getTarget());
			String markerMessage = ((String)marker.getAttribute(IMarker.MESSAGE)).trim();
			if (position.getOffset() == offset && position.getDeclarationLength() == length) {
				assertEquals(message, markerMessage);
				found = true;
			}
		}
		assertTrue("No message found at offset " + offset, found);
	}
}
