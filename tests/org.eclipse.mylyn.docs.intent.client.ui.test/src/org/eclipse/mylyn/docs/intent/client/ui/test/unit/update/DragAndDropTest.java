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
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractZipBasedTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AnnotationUtils;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.modelingunit.update.MergeUpdater;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.FileToStringConverter;

/**
 * Tests the drag & drop updates.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class DragAndDropTest extends AbstractZipBasedTest {

	private static final String INTENT_PROJECT_ARCHIVE = "data/unit/documents/quickfixes/intentProject.zip";

	private static final String FINAL_INTENT_DOC = "data/unit/documents/quickfixes/final.intent";

	private static final String INITIAL_INTENT_DOC = "data/unit/documents/quickfixes/initial.intent";

	private IntentEditor editor;

	private IntentEditorDocument document;

	private ModelingUnit modelingUnit;

	/**
	 * Constructor.
	 */
	public DragAndDropTest() {
		super(INTENT_PROJECT_ARCHIVE, "intentProject");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.AbstractDemoTest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
		modelingUnit = (ModelingUnit)getIntentSection(1, 1).getModelingUnits().get(0);
	}

	/**
	 * Test that the model is completed by additions.
	 * 
	 * @throws IOException
	 *             if comparison fails
	 */
	public void testDragAndDrop() throws IOException {
		Resource resource = new ResourceSetImpl().getResource(
				URI.createURI("platform:/resource/intentProject/test.ecore"), true);
		EObject root = resource.getContents().get(0);
		for (EObject element : root.eContents()) {
			new MergeUpdater(repositoryAdapter).create(modelingUnit, Collections.singletonList(element));
		}

		document.reloadFromAST();
		editor.doSave(new NullProgressMonitor());
		waitForCompiler();
		waitForSynchronizer();

		checkDocumentValidity();
	}

	/**
	 * Test that the model is completed by on addition of several elements.
	 * 
	 * @throws IOException
	 *             if comparison fails
	 */
	public void testDragAndDropAllInOne() throws IOException {
		// reset changes
		document.set(FileToStringConverter.getFileAsString(new File(INITIAL_INTENT_DOC)));
		editor.doSave(new NullProgressMonitor());
		waitForCompiler();
		waitForSynchronizer();
		modelingUnit = (ModelingUnit)getIntentSection(1, 1).getModelingUnits().get(0);

		Resource resource = new ResourceSetImpl().getResource(
				URI.createURI("platform:/resource/intentProject/test.ecore"), true);
		EObject root = resource.getContents().get(0);
		new MergeUpdater(repositoryAdapter).create(modelingUnit, root.eContents());

		document.reloadFromAST();
		editor.doSave(new NullProgressMonitor());
		waitForCompiler();
		waitForSynchronizer();

		checkDocumentValidity();
	}

	/**
	 * Checks whether the doc is valid or not.
	 * 
	 * @throws IOException
	 *             the the final document cannot be read.
	 */
	private void checkDocumentValidity() throws IOException {
		// check that the document is valid
		List<IntentAnnotation> annotations = AnnotationUtils.getIntentAnnotations(editor,
				IntentAnnotationMessageType.SYNC_WARNING);
		if (!annotations.isEmpty()) {
			AnnotationUtils.displayAnnotations(editor);
		}
		assertEquals(FileToStringConverter.getFileAsString(new File(FINAL_INTENT_DOC)), document.get());
		assertTrue(annotations.isEmpty());
	}
}
