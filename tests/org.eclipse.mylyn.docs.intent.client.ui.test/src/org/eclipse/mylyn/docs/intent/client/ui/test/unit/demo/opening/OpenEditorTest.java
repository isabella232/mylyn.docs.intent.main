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
		openIntentEditor(getIntentChapter(2)); // "2. Architecture"

		// Opens a section in a separate editor
		openIntentEditor(getIntentSection(2, 1)); // "2.1 Comparison process"

		// Ensures that all 3 editors are opened
		int i = 0;
		for (IEditorReference reference : PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getEditorReferences()) {
			assertEquals(EDITOR_REFERENCES_NAMES[i], reference.getName());
			i++;
		}
	}

}
