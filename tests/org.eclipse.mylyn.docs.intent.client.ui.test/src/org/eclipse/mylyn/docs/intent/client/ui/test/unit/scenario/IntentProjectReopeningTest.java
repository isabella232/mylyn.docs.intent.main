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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.scenario;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.ide.builder.ToggleNatureAction;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.ide.repository.WorkspaceRepository;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.parser.test.utils.FileToStringConverter;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;

/**
 * Ensures that when reopening Intent projects after modifications, there are no lost of contents.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentProjectReopeningTest extends AbstractIntentUITest {
	/**
	 * Path to test file.
	 */
	private static final String DOCUMENTS_FOLDER_PATH = "data/unit/documents/scenario/projectReopening/";

	/**
	 * The current Intent editor.
	 */
	private IntentEditor editor;

	/**
	 * The document associated to the current Intent editor.
	 */
	private IntentEditorDocument document;

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Step 1 : Generic set up
		setUpIntentProject("intentProject", INTENT_EMPTY_DOC_PATH, true);

		// Step 2 : open an editor on the root document
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	/**
	 * Ensures that when reopening Intent projects after modifications, there are no lost of contents.
	 * 
	 * @throws CoreException
	 *             if major issue occur during project reopening
	 * @throws IOException
	 *             if test file cannot be accessed
	 */
	public void testProjectReopeningWithSection() throws IOException, CoreException {
		String initalContent = FileToStringConverter.getFileAsString(new File(DOCUMENTS_FOLDER_PATH
				+ "projectReopening01.intent"));
		String newContent = initalContent.replace("Title", "A");
		doTestProjectReopening(initalContent, newContent);
	}

	/**
	 * Ensures that when reopening Intent projects after modifications, there are no lost of contents.
	 * 
	 * @throws CoreException
	 *             if major issue occur during project reopening
	 * @throws IOException
	 *             if test file cannot be accessed
	 */
	public void testProjectReopeningWithModelingUnitCreation() throws IOException, CoreException {
		String initalContent = FileToStringConverter.getFileAsString(new File(DOCUMENTS_FOLDER_PATH
				+ "projectReopening02.intent"));
		String newContent = initalContent.replace("Title", "A");
		doTestProjectReopening(initalContent, newContent);
	}

	/**
	 * Ensures that setting the new content, and then reopening the intent project does not cause any loss of
	 * content.
	 * 
	 * @param initalContent
	 *            the initial document content
	 * @param newContent
	 *            the new document content to set before reopening the project
	 * @throws CoreException
	 *             if major issue occur during project reopening
	 */
	protected void doTestProjectReopening(String initalContent, String newContent) throws CoreException {
		document.set(initalContent);
		editor.doSave(new NullProgressMonitor());
		final int timeToWait = 200;
		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			// Silent catch
		}
		waitForAllOperationsInUIThread();

		document.set(newContent);
		repositoryListener.clearPreviousEntries();
		editor.doSave(new NullProgressMonitor());
		waitForIndexer();
		waitForAllOperationsInUIThread();

		IntentDocument newDocument = reopenProjectAndGetDocument();
		assertEquals("When reopening Intent Project, some content was lost ", newContent,
				new IntentSerializer().serialize(newDocument));
	}

	/**
	 * Closes and reopens the Intent project, and return the {@link IntentDocument} once project is reopened.
	 * 
	 * @return the {@link IntentDocument} once project is reopened
	 * @throws CoreException
	 *             if major issue occur during project reopening
	 */
	private IntentDocument reopenProjectAndGetDocument() throws CoreException {
		editor.doSave(new NullProgressMonitor());
		ToggleNatureAction.toggleNature(intentProject);
		final int timeToWait = 500;
		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			// Silent catch
		}
		waitForAllOperationsInUIThread();
		intentProject.close(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		intentProject.open(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		ResourceSet rs = new ResourceSetImpl();
		IntentDocument newDocument = null;
		URI documentURI = URI.createURI("platform:/resource/" + intentProject.getName() + "/.repository/"
				+ IntentLocations.INTENT_INDEX + '.' + WorkspaceRepository.getWorkspaceResourceExtension());
		Resource documentResource = rs.getResource(documentURI, true);
		if (documentResource != null && documentResource.getContents().iterator().hasNext()
				&& documentResource.getContents().iterator().next() instanceof IntentStructuredElement) {
			if (documentResource.getContents().iterator().next() instanceof IntentDocument) {
				EcoreUtil.resolveAll(documentResource.getResourceSet());
				newDocument = (IntentDocument)documentResource.getContents().iterator().next();
			}
		}
		return newDocument;
	}
}
