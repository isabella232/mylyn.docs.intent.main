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

import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractUITest;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;

/**
 * Tests the correct behavior of Intent editor changes.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class ChangeEditorUpdateTest extends AbstractUITest {

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
	 * Ensures that changes are correctly managed by the editor.
	 */
	public void testChanges() {
		IntentEditorDocument document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(
				editor.getEditorInput());

		String string = document.get();
		document.set(string + "test");

		// Iterator annotationIterator = ((IntentDocumentProvider)editor.getDocumentProvider())
		// .getAnnotationModel(null).getAnnotationIterator();
		// while (annotationIterator.hasNext()) {
		// System.err.println(annotationIterator.next());
		// }
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
