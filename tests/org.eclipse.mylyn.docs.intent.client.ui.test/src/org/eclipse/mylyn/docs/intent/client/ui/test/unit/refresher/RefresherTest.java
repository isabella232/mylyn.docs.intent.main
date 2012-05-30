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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.refresher;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;

/**
 * Ensures that the Intent project refresher is correctly notified and correctly applies changes.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class RefresherTest extends AbstractIntentUITest {

	public void testRefreshProblems() throws ReadOnlyException, SaveException {
		// Step 1: we initialize an intent project
		setUpIntentProject("intentProject", "data/unit/documents/editorupdates/refreshTest.intent", true);
		IntentEditor editor = openIntentEditor();
		repositoryListener.clearPreviousEntries();

		// Step 2: check that the error is present
		assertTrue(getProblems().contains(
				"The element test cannot be resolved. This contribution instruction will be ignored."));

		// Step 3: check that the error is updated
		IntentEditorDocument document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(
				editor.getEditorInput());
		document.set(document.get().replace("test", "modified_test"));
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		waitForCompiler();
		assertTrue(getProblems()
				.contains(
						"The element modified_test cannot be resolved. This contribution instruction will be ignored."));

		// Step 4: check that the errors disappears
		document.set("Document {\n\tChapter Title {\n\t\tSection Title {\n\t\t\tText\n\t\t}\n\t}\n}");
		repositoryListener.clearPreviousEntries();
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		waitForCompiler();
		assertTrue(getProblems().isEmpty());
	}

	private List<String> getProblems() {
		List<String> res = new ArrayList<String>();
		try {
			IMarker[] markers = intentProject.findMarkers("org.eclipse.core.resources.problemmarker", true,
					IResource.DEPTH_INFINITE);
			for (IMarker marker : markers) {
				res.add(marker.getAttribute(IMarker.MESSAGE, "").trim());
			}
		} catch (CoreException e) {
			fail(e.getMessage());
		}
		return res;
	}
}
