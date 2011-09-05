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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.opening;

import org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.AbstractDemoTest;
import org.eclipse.mylyn.docs.intent.core.document.IntentChapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

/**
 * Tests the Intent demo, part 1: navigation behavior.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class OpenEditorTest extends AbstractDemoTest {

	private static final String[] EDITOR_REFERENCES_NAMES = new String[] {"IntentDocument", "Architecture",
			"Comparison process",
	};

	/**
	 * Ensures that several editors can be opened on the same document.
	 */
	public void testNavigation() {
		// Opens the main document
		openIntentEditor();

		// Opens a chapter in a separate editor
		IntentChapter chapter = getIntentDocument().getChapters().get(1); // "2. Architecture"
		openIntentEditor(chapter);

		// Opens a section in a separate editor
		IntentSection section = chapter.getSubSections().get(0); // "2.1 Comparison process"
		openIntentEditor(section);

		// Ensures that all 3 editors are opened
		int i = 0;
		for (IEditorReference reference : PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getEditorReferences()) {
			assertEquals(EDITOR_REFERENCES_NAMES[i], reference.getName());
			i++;
		}
	}

}
