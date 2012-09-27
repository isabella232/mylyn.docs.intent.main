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

import java.io.IOException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.modelingunit.update.MergeUpdater;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;

/**
 * Tests the drag & drop updates.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class DragAndDropTest extends AbstractUpdateTest {

	private static final String INTENT_PROJECT_ARCHIVE = "data/unit/documents/dragdrop/dragdrop.zip";

	private static final String FINAL_INTENT_DOC = "data/unit/documents/dragdrop/final.intent";

	private ModelingUnit modelingUnit;

	/**
	 * Constructor.
	 */
	public DragAndDropTest() {
		super(INTENT_PROJECT_ARCHIVE, "dragdrop");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.AbstractDemoTest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		modelingUnit = getIntentSection(1, 1).getModelingUnits().get(0);
	}

	/**
	 * Test that the model is completed by addition of several elements.
	 * 
	 * @throws IOException
	 *             if comparison fails
	 */
	public void testDragAndDrop() throws IOException {
		// add all missing elements at once
		Resource resource = new ResourceSetImpl().getResource(
				URI.createURI("platform:/resource/dragdrop/test.ecore"), true);
		EObject root = resource.getContents().get(0);
		new MergeUpdater(repositoryAdapter).create(modelingUnit, root.eContents());
		document.set(new IntentSerializer().serialize((EObject)document.getAST()));
		editor.doSave(new NullProgressMonitor());
		waitForCompiler();
		waitForSynchronizer();

		// check result
		checkDocumentValidity(FINAL_INTENT_DOC);
	}
}
