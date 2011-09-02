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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.compare;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractUITest;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;

/**
 * Tests the correct behavior of Intent editor changes. Ensures that EMF Compare is able to maintain order and
 * structure of the Intent document.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class ChangeEditorUpdateTest extends AbstractUITest {

	private static final String PREFIX_STRING_1 = "* an element defined in the right model is not matching any element in the left model (added element)";

	private static final String PREFIX_STRING_2 = "Otherwise, it is a deleted element.";

	private static final String NEW_SECTION_1 = "\n\tSection {\n\t\t\tSubSection1\n\n\t\tMySubSection\n\t}";

	private static final String NEW_SECTION_2 = "\n\tSection {\n\t\t\tSubSection2\n\n\t\tMySubSection\n\t}";

	private IntentSection section;

	private IntentEditor editor;

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setUpIntentProject("intentProject", "data/unit/documents/editorupdates/changeEditorUpdateTest.intent");
		section = getIntentDocument().getChapters().iterator().next().getSubSections().iterator().next();
		editor = openIntentEditor(section);
	}

	/**
	 * Ensures that when typing new sections inside the Intent Document, when emf compare is used to update
	 * the repository the structure is respected.
	 */
	public void testSectionOrder() {
		IntentEditorDocument document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(
				editor.getEditorInput());

		String documentContent = document.get();
		String expectedDocumentContent = documentContent.replace(PREFIX_STRING_1, PREFIX_STRING_1
				+ NEW_SECTION_1);
		expectedDocumentContent = expectedDocumentContent.replace(PREFIX_STRING_2, PREFIX_STRING_2
				+ NEW_SECTION_2);
		document.set(expectedDocumentContent);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();

		String newDocumentContent = document.get();
		assertEquals("Editor update dit not occur has expected", expectedDocumentContent, newDocumentContent);

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		if (editor != null) {
			editor.close(false);
		}
		super.tearDown();
	}
}
