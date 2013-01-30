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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.update;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.parser.test.utils.FileToStringConverter;

/**
 * Tests the quick fixes updates.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class QuickFixTest extends AbstractUpdateTest {

	private static final String INTENT_PROJECT_ARCHIVE = "data/unit/documents/quickfixes/quickfixes.zip";

	private static final String FINAL_INTENT_DOC = "data/unit/documents/quickfixes/final.intent";

	private static final String MODIFIED_INTENT_DOC = "data/unit/documents/quickfixes/modifications.intent";

	/**
	 * Constructor.
	 */
	public QuickFixTest() {
		super(INTENT_PROJECT_ARCHIVE, "quickfixes");
	}

	/**
	 * Test that the model element changes are fixed.
	 * 
	 * @throws IOException
	 *             if comparison fails
	 */
	public void testModelElementChanges() throws IOException {
		// apply all fixes (also acts as initialization for further tests)
		fixIssue("The EPackage toDelete is defined in the <b>Current Document</b> model<br/>but not in the <b>Working Copy</b> model.");
		fixIssue("The EClass A is defined in the <b>Working Copy</b> model<br/>but not in the <b>Current Document</b> model.");
		fixIssue("The EClass E is defined in the <b>Current Document</b> model<br/>but not in the <b>Working Copy</b> model.");
		fixIssue("The EPackage sub is defined in the <b>Working Copy</b> model<br/>but not in the <b>Current Document</b> model.");

		checkDocumentValidity(FINAL_INTENT_DOC);
	}

	/**
	 * Test that the model element changes are fixed.
	 * 
	 * @throws IOException
	 *             if comparison fails
	 */
	public void testStructuralFeaturesChanges() throws IOException {
		// change values in the document
		document.set(FileToStringConverter.getFileAsString(new File(MODIFIED_INTENT_DOC)));
		editor.doSave(new NullProgressMonitor());
		waitForCompiler();
		waitForSynchronizer();

		// apply all fixes
		fixIssue("EAttribute upperBound in a2 has changed.<br/><b>Current Document</b> : -1<br/><b>Working Copy</b> : 4");
		fixIssue("EAttribute nsPrefix in sub has changed.<br/><b>Current Document</b> : subTEST<br/><b>Working Copy</b> : sub");
		fixIssue("EAttribute abstract in D has changed.<br/><b>Current Document</b> : false<br/><b>Working Copy</b> : true");
		fixIssue("D has been removed from reference eSuperTypes : EClass in B -> A, C");
		fixIssue("C has been added to reference eSuperTypes : EClass in B -> A, D");

		checkDocumentValidity(FINAL_INTENT_DOC);
	}

}
