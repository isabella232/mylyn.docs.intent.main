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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.hyperlink;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Iterator;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.hyperlinks.IntentHyperLinkDetector;
import org.eclipse.mylyn.docs.intent.client.ui.editor.hyperlinks.OpenWorkingCopyResourceHyperLink;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.WorkspaceUtils;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

/**
 * Tests ensuring that the Intent hyperlink detector works as expected.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentHyperLinkDetetectorTest extends AbstractIntentUITest {

	private static final int JAVA_METHOD_OFFSET = 1704;

	private static final String JAVA_PROJECT_PATH = "data/unit/java/java.example01.zip";

	private static final String INTENT_DOCUMENT_EXAMPLE_PATH = "data/unit/documents/java/doc_with_java.intent";

	private IntentEditor editor;

	private IntentEditorDocument document;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Step 1 : Generic set up
		WorkspaceUtils.importJavaProject(JAVA_PROJECT_PATH);
		setUpIntentProject("intentProject", INTENT_DOCUMENT_EXAMPLE_PATH, true);

		// Step 2 : open an editor on the root document
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	/**
	 * Ensures that the intent hyper link detector allows to open the java class/method/field described in an
	 * external content reference.
	 */
	public void testJavaHyperlink() {
		int offset = document
				.get()
				.indexOf(
						"ExampleJavaClass.java#//@methods[name='protectedMethodWithParameters(ExampleJavaClass,Object)']");
		doTestHyperLink(offset, "org.eclipse.jdt.ui.CompilationUnitEditor", JAVA_METHOD_OFFSET);
	}

	/**
	 * Ensures that the intent hyper link detector allows to open resources with an intent:/ URI (internal
	 * resources).
	 */
	public void testIntentURIHyperlink() {
		int offset = document.get().indexOf("intent:/intentProject/internalresource.ecore");
		doTestHyperLink(offset, "org.eclipse.emf.ecore.presentation.EcoreEditorID", -1);
	}

	/**
	 * Ensures that when searhcing for hyperlinks at the given offset, one is found and allows to open an
	 * editor with the given expectedEditorID, with a selection at the given expectedSelectedOffset.
	 */
	public void doTestHyperLink(int offset, String expectedEditorID, int expectedSelectedOffset) {
		IRegion region = new Region(offset, 1);

		// Step 1: call hyper link detector to get hyperlinks
		IntentHyperLinkDetector intentHyperlinkDetector = new IntentHyperLinkDetector();
		intentHyperlinkDetector.setContext(editor);
		Iterator<OpenWorkingCopyResourceHyperLink> detectedHyperlinks = Iterables.filter(
				Lists.newArrayList(intentHyperlinkDetector.detectHyperlinks(editor.getProjectionViewer(),
						region, true)), OpenWorkingCopyResourceHyperLink.class).iterator();
		assertTrue("Intent should provide an hyperlink", detectedHyperlinks.hasNext());
		OpenWorkingCopyResourceHyperLink hyperLink = detectedHyperlinks.next();

		// Step 2 : open hyperlink
		try {
			((OpenWorkingCopyResourceHyperLink)hyperLink).open();
			waitForAllOperationsInUIThread();
			IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().getEditorReferences();
			boolean isExpectedEditorOpened = false;
			// checking that the expected editor has been opened
			for (int i = 0; i < editorReferences.length && !isExpectedEditorOpened; i++) {
				if (expectedEditorID.equals(editorReferences[i].getId())) {
					isExpectedEditorOpened = true;
					// check editor selection
					ISelection selection = editorReferences[i].getEditor(true).getEditorSite()
							.getSelectionProvider().getSelection();
					if (expectedSelectedOffset != -1) {
						assertEquals("The opened editor should have the following selection active ",
								expectedSelectedOffset, ((TextSelection)selection).getOffset());
					}
				}
			}
			assertTrue("The hyperlink should have opened an editor with the following ID : "
					+ expectedEditorID, isExpectedEditorOpened);
			assertFalse("Only one hyperlink should be provided", detectedHyperlinks.hasNext());
		} finally {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
		}
	}
}
