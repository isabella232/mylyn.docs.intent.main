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

import java.io.IOException;

import junit.framework.AssertionFailedError;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AnnotationUtils;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;

/**
 * <p>
 * Ensures that the Abstract Resource concept works as expected.
 * </p>
 * <b> Relevant specifications </b> :
 * <ul>
 * <li>org.eclipse.mylyn.docs.intent/discussion/specs/abstractResources.textile</li>
 * </ul>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentAbstractResourceTest extends AbstractIntentUITest {
	/**
	 * Path to test file.
	 */
	private static final String INTENT_DOCUMENT_EXAMPLE_PATH = "data/unit/documents/scenario/abstract_resources.intent";

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
		setUpIntentProject("intentProject", INTENT_DOCUMENT_EXAMPLE_PATH, true);

		// Step 2 : open an editor on the root document
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	/**
	 * Ensures that abstract resources are not synchronized.
	 */
	public void testAbstractResourceIsNotSynchronized() {
		// Step 1 : make a sample modification on modeling units, just to launch the synchronizer
		String documentContent = document.get();
		String expectedDocumentContent = documentContent.replace("myEClass", "myEClass2");
		document.set(expectedDocumentContent);

		// Step 2 : we start recording for any modification made on the repository
		repositoryListener.clearPreviousEntries();
		// save
		editor.doSave(new NullProgressMonitor());
		// and wait the synchronizer to be notified
		waitForCompiler();

		// Step 3 : we check that no synchronization error has been detected
		assertFalse("An abstract resource should not be handled by the Intent synchronizer",
				AnnotationUtils.hasIntentAnnotation(editor, IntentAnnotationMessageType.SYNC_WARNING, "",
						false));
		// FIXME add this condition
		// waitForCompiler(false);
	}

	/**
	 * Ensures that even if not synchronized, the abstract resources are correctly compiled.
	 */
	public void testAbstractResourceIsCompiled() {
		// Step 1 : make a sample modification on modeling units, just to launch the compiler
		String documentContent = document.get();
		String expectedDocumentContent = documentContent.replace("myEClass", "myEClass2");
		document.set(expectedDocumentContent);

		// Step 2 : we start recording for any modification made on the repository
		repositoryListener.clearPreviousEntries();
		// save
		editor.doSave(new NullProgressMonitor());
		// and wait the compiler to be notified
		waitForCompiler();

		// Step 3 : we check that the resource has correctly been compiled :
		// => no error should have been found
		assertFalse("The Abstract Resource was not correctly compiled", AnnotationUtils.hasIntentAnnotation(
				editor, IntentAnnotationMessageType.COMPILER_ERROR, "", false));

		// => a compiler warning should inform the end-user that the EPackage's URI and prefix are not
		// properly set
		assertTrue("The Abstract Resource was not correctly validated by Intent compiler",
				AnnotationUtils.hasIntentAnnotation(editor, IntentAnnotationMessageType.COMPILER_INFO, "URI",
						false));
		assertTrue("The Abstract Resource was not correctly validated by Intent compiler",
				AnnotationUtils.hasIntentAnnotation(editor, IntentAnnotationMessageType.COMPILER_INFO,
						"prefix", false));

		// => a cache of the resource should have been created inside the repository.
		Resource generatedResource = repositoryAdapter
				.getResource(IntentLocations.GENERATED_RESOURCES_FOLDER_PATH + "/abstractResource");
		assertNotNull("The Abstract Resource was not correctly generated by the compiler", generatedResource);
		assertTrue("The Abstract Resource content was not correctly generated by the compiler",
				generatedResource.getContents().iterator().next() instanceof EPackage);
		assertEquals("The Abstract Resource content was not correctly generated by the compiler",
				"myAbstractRoot", ((EPackage)generatedResource.getContents().iterator().next()).getName());
		// FIXME add this condition
		// waitForCompiler(false);

	}

	/**
	 * Ensures that abstract resources are not synchronized.
	 */
	public void testAbstractResourceIsSynchronizedWhenBecamingConcrete() {

		try {
			// Step 1 : we create a model file inside the intent project
			ResourceSet rs = new ResourceSetImpl();
			URI modelURI = URI.createURI("platform:/resource/" + intentProject.getName() + "/Model.ecore");

			Resource modelResource = rs.createResource(modelURI);
			modelResource.getContents().add(EcoreFactory.eINSTANCE.createEPackage());
			modelResource.save(null);

			// Step 2 : we make the abstract resource concrete by associating it to the created model URI
			final String resourceURIDeclaration = "\n\t\t\t\t\tURI = \"" + modelURI.toString() + "\";";

			String documentContent = document.get();
			String expectedDocumentContent = documentContent.replace("Resource abstractResource {",
					"Resource abstractResource {" + resourceURIDeclaration);
			document.set(expectedDocumentContent);

			// Step 3 : we start recording for any modification made on the repository
			repositoryListener.clearPreviousEntries();
			// save
			editor.doSave(new NullProgressMonitor());
			// and wait the synchronizer to be notified
			waitForSynchronizer();

			// Step 4 : we check that new synchronization errors have been detected
			assertTrue("A concrete resource should be handled by the Intent synchronizer",
					AnnotationUtils.hasIntentAnnotation(editor, IntentAnnotationMessageType.SYNC_WARNING, "",
							false));

			// Step 5 : we make this concrete resource abstract again
			document.set(document.get().replace(resourceURIDeclaration, ""));

			repositoryListener.clearPreviousEntries();
			editor.doSave(new NullProgressMonitor());
			waitForSynchronizer();

			// Step 6 : we check that no synchronization errors has been detected
			assertFalse("An abstract resource should not be handled by the Intent synchronizer",
					AnnotationUtils.hasIntentAnnotation(editor, IntentAnnotationMessageType.SYNC_WARNING, "",
							false));

		} catch (IOException e) {
			AssertionFailedError assertFailed = new AssertionFailedError(
					"Unexpected exception when creating model file : " + e.getMessage());
			assertFailed.setStackTrace(e.getStackTrace());
			throw assertFailed;
		}
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
