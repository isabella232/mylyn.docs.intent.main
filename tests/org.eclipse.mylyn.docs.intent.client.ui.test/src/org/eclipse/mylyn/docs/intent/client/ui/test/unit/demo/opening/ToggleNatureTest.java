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

import org.eclipse.mylyn.docs.intent.client.ui.ide.builder.ToggleNatureAction;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.AbstractDemoTest;

/**
 * Tests the Intent demo, part 1: navigation behavior.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class ToggleNatureTest extends AbstractDemoTest {

	public void testToggle() {
		openAndWait();
		ToggleNatureAction.toggleNature(intentProject);

		int count = 10;
		for (int i = 0; i < count; i++) {
			ToggleNatureAction.toggleNature(intentProject);
			openAndWait();
		}
	}

	private void openAndWait() {
		openIntentEditor();
		waitForAllOperationsInUIThread();
	}

}
