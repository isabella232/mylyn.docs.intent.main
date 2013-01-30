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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.mylyn.docs.intent.compare.utils.EMFCompareUtils;
import org.junit.Test;

/**
 * <p>
 * Ensures that the {@link org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference} concept
 * works as expected.
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ExternalContentReferencesTest extends AbstractIntentUITest {
	private static final String INTENT_DOCUMENT_EXAMPLE_PATH = "data/unit/documents/scenario/externalcontentreferences/external_content.intent";

	private IntentEditor editor;

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
	 * Ensures that invalid external content references raise the expected issues.
	 */
	@Test
	public void testInvalidExternalContentReference() {
		String invalidExternalContentReference = "@ref \"platform:/resource/missingProject/invalid.ecore\"";
		addExternalContentReference(invalidExternalContentReference, "1");
		waitForCompiler();
		waitForAllOperationsInUIThread();
		assertTrue("Compiler should have detected that the external content reference is invalid",
				AnnotationUtils.hasIntentAnnotation(editor, IntentAnnotationMessageType.COMPILER_ERROR,
						"Could not find resource platform:/resource/missingProject/invalid.ecore", true));
	}

	/**
	 * Ensures that external content references work as expected when referencing a model file.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testModelResourceSynchronizationThroughExternalContentReference() throws IOException {
		// Step 1: create a test model
		EPackage testModel = createTestModel();

		// Step 2: reference this model file inside the intent document
		String modelFileReference = "@ref \"platform:/resource/intentProject/model.ecore\"";
		addExternalContentReference(modelFileReference, "1");
		waitForSynchronizer();

		// the compiler should have created a copy of this model file, accessible through an Intent URI
		EObject intentRepositoryModel = new ResourceSetImpl()
				.getResource(URI.createURI("intent:/intentProject/model.ecore"), true).getContents()
				.iterator().next();
		assertEquals(
				"There should be no differences betwen the working copy model and the cache inside Intent repository",
				0, EMFCompareUtils.compare(testModel, intentRepositoryModel).getDifferences().size());

		// Step 3: modify the working copy model
		String expectedSyncError = "The EClass c2 has been removed from the reference 'eSuperTypes'";
		((EClass)testModel.getEClassifiers().get(0)).getESuperTypes().add(
				(EClass)testModel.getEClassifiers().get(1));
		repositoryListener.clearPreviousEntries();
		testModel.eResource().save(null);
		waitForSynchronizer();
		waitForAllOperationsInUIThread();
		assertTrue("Synchronizer should have detected that the ExternalContentReference has changed",
				AnnotationUtils.hasIntentAnnotation(editor, IntentAnnotationMessageType.SYNC_WARNING,
						expectedSyncError, true));

		// Step 4: apply quick-fix to fix this sync issue
		repositoryListener.clearPreviousEntries();
		fixIssueUsingQuickFix(editor, document, expectedSyncError);
		waitForAllOperationsInUIThread();
		assertEquals(
				"Synchronizer should not detect any change any more as issue have been resolved using quick-fix",
				0, AnnotationUtils.getIntentAnnotations(editor, IntentAnnotationMessageType.SYNC_WARNING)
						.size());
	}

	/**
	 * Ensures that external content references work as expected when referencing a specific element of a
	 * model file.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testModelFragmentSynchronizationThroughExternalContentReference() throws IOException {
		// Step 1: create a test model
		EPackage testModel = createTestModel();
		EClass class1 = (EClass)testModel.getEClassifier("c1");

		// Step 2: reference this model file inside the intent document
		String modelFileReference = "@ref \"platform:/resource/intentProject/model.ecore#//c1\"";
		addExternalContentReference(modelFileReference, "1");
		waitForSynchronizer();

		// the compiler should have created a copy of this model file, accessible through an Intent URI
		EObject intentRepositoryModel = new ResourceSetImpl()
				.getResource(URI.createURI("intent:/intentProject/model.ecore#//c1"), true).getContents()
				.iterator().next();
		assertEquals(
				"There should be no differences betwen the working copy model and the cache inside Intent repository",
				0, EMFCompareUtils.compare(class1, intentRepositoryModel).getDifferences().size());

		// Step 3: modify the working copy model
		String expectedSyncError = "The EClass c2 has been removed from the reference 'eSuperTypes'";
		class1.getESuperTypes().add((EClass)testModel.getEClassifier("c2"));
		repositoryListener.clearPreviousEntries();
		testModel.eResource().save(null);
		waitForSynchronizer();
		waitForAllOperationsInUIThread();
		assertTrue("Synchronizer should have detected that the ExternalContentReference has changed",
				AnnotationUtils.hasIntentAnnotation(editor, IntentAnnotationMessageType.SYNC_WARNING,
						expectedSyncError, true));

		// Step 4: apply quick-fix to fix this sync issue
		repositoryListener.clearPreviousEntries();
		fixIssueUsingQuickFix(editor, document, expectedSyncError);
		waitForAllOperationsInUIThread();
		assertEquals(
				"Synchronizer should not detect any change any more as issue have been resolved using quick-fix",
				0, AnnotationUtils.getIntentAnnotations(editor, IntentAnnotationMessageType.SYNC_WARNING)
						.size());

		// Step 5: modify a non-synchronized element of the working copy model
		repositoryListener.clearPreviousEntries();
		((EClass)testModel.getEClassifiers().get(1)).getEStructuralFeatures().add(
				EcoreFactory.eINSTANCE.createEAttribute());
		testModel.eResource().save(null);
		waitForSynchronizer();
		waitForAllOperationsInUIThread();
		assertEquals(
				"Synchronizer should not detect any change as the modification does not concern a synchronized element",
				0, AnnotationUtils.getIntentAnnotations(editor, IntentAnnotationMessageType.SYNC_WARNING)
						.size());
	}

	/**
	 * Ensures that external content references work as expected when referencing a model that is directly
	 * stored inside the intent repository.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testInternalModelSynchronizationThroughExternalContentReference() throws IOException {
		// Step 1: reference an internal model file inside the intent document
		String modelFileReference = "@ref \"intent:/intentProject/model.ecore\"";
		addExternalContentReference(modelFileReference, "1");
		waitForSynchronizer();
		// resource should have been automatically created and filled
		Resource intentRepositoryResource = new ResourceSetImpl().getResource(
				URI.createURI("intent:/intentProject/model.ecore"), true);
		assertEquals("Internal resource should have been automatically filled", 1, intentRepositoryResource
				.getContents().size());
		assertEquals("The automatically created EPackage is not correctly initialized", "model",
				((EPackage)intentRepositoryResource.getContents().iterator().next()).getName());
		// no sync issue should have been detected
		assertEquals(
				"Synchronizer should not detect any sync. issue as the internal resource is not synchronized",
				0, AnnotationUtils.getIntentAnnotations(editor, IntentAnnotationMessageType.SYNC_WARNING)
						.size());

		// Step 2: modify the model
		EPackage testModel = (EPackage)intentRepositoryResource.getContents().iterator().next();
		testModel.getEClassifiers().add(EcoreFactory.eINSTANCE.createEClass());
		intentRepositoryResource.save(null);
		waitForSynchronizer();
		waitForAllOperationsInUIThread();
		// no sync issue should have been detected
		assertEquals(
				"Synchronizer should not detect any sync. issue as the internal resource is not synchronized",
				0, AnnotationUtils.getIntentAnnotations(editor, IntentAnnotationMessageType.SYNC_WARNING)
						.size());

		// there should be only one version of the model
		Resource resourceThroughIntent = new ResourceSetImpl().getResource(
				URI.createURI("intent:/intentProject/model.ecore"), false);
		Resource resourceThroughWorkspace = new ResourceSetImpl().getResource(
				URI.createURI("platform:/resource/intentProject/.repository/"
						+ IntentLocations.GENERATED_RESOURCES_FOLDER_PATH + "model.ecore"), false);
		Resource resourceThroughRepository = repositoryAdapter
				.getResource(IntentLocations.GENERATED_RESOURCES_FOLDER_PATH + "model.ecore");

		assertEquals(0, EMFCompareUtils.compare(resourceThroughIntent, resourceThroughWorkspace)
				.getDifferences().size());
		assertEquals(0, EMFCompareUtils.compare(resourceThroughWorkspace, resourceThroughRepository)
				.getDifferences().size());

	}

	// La meme avec java

	/**
	 * Creates a resource holding a test model that will be used to test external content referencing models.
	 * 
	 * @return the created test model
	 */
	private EPackage createTestModel() {
		ResourceSet rs = new ResourceSetImpl();
		Resource resource = rs.createResource(URI.createURI("platform:/resource/intentProject/model.ecore"));
		EPackage model = EcoreFactory.eINSTANCE.createEPackage();
		model.setName("p1");
		EClass c1 = EcoreFactory.eINSTANCE.createEClass();
		c1.setName("c1");
		EClass c2 = EcoreFactory.eINSTANCE.createEClass();
		c2.setName("c2");
		model.getEClassifiers().add(c1);
		model.getEClassifiers().add(c2);
		resource.getContents().add(model);
		try {
			resource.save(null);
		} catch (IOException e) {
			fail("Could not create test model " + e.getMessage());
		}
		return model;
	}

	/**
	 * Add the given instruction at the given placeHolderID.
	 * 
	 * @param invalidExternalContentReference
	 *            the instruction to add
	 * @param placeHolderID
	 *            the place holder ID (see the example document)
	 */
	private void addExternalContentReference(String invalidExternalContentReference, String placeHolderID) {
		document.set(document.get().replace("//" + placeHolderID,
				"@M\n" + invalidExternalContentReference + "\nM@"));
		repositoryListener.clearPreviousEntries();
		editor.doSave(new NullProgressMonitor());
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
