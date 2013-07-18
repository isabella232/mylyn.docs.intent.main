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
	/**
	 * Location of the test archive.
	 */
	private static final String INTENT_PROJECT_ARCHIVE = "data/unit/documents/quickfixes/quickfixes.zip";

	/**
	 * Location of the file containing the expected state of the document.
	 */
	private static final String FINAL_INTENT_DOC = "data/unit/documents/quickfixes/final.intent";

	/**
	 * Location of the file containing the expected state of the document.
	 */
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
		fixIssue("The EPackage toDelete is defined in the Documentation model<br/>but not in the Working Copy model.");
		fixIssue("The EClass A is defined in the Working Copy model<br/>but not in the Documentation model.");
		fixIssue("The EClass E is defined in the Documentation model<br/>but not in the Working Copy model.");
		fixIssue("The EPackage sub is defined in the Working Copy model<br/>but not in the Documentation model.");

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
		fixIssue("EAttribute upperBound in a2 has changed.<br/>Documentation : -1<br/>Working Copy : 4");
		fixIssue("EAttribute nsPrefix in sub has changed.<br/>Documentation : subTEST<br/>Working Copy : sub");
		fixIssue("EAttribute abstract in D has changed.<br/>Documentation : false<br/>Working Copy : true");
		fixIssue("D has been removed from reference eSuperTypes : EClass in B -> A, C");
		fixIssue("C has been added to reference eSuperTypes : EClass in B -> A, D");

		checkDocumentValidity(FINAL_INTENT_DOC);
	}

}
