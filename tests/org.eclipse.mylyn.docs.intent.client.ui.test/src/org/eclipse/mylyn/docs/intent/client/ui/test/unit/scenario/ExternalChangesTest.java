/*******************************************************************************
 * Copyright (c) 2013 Obeo.
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

import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.ide.repository.WorkspaceRepository;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;

/**
 * An test ensuring that external changes (i.e. changes on the document made without using the
 * RepositoryAdapter, for example git updates) are correctly handled.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ExternalChangesTest extends AbstractIntentUITest {
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
	 * URI of the {@link IntentDocument} to modify externally.
	 */
	private URI documentURI;

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setUpIntentProject("intentProject", INTENT_DOCUMENT_EXAMPLE_PATH, true);
		documentURI = URI.createURI("platform:/resource/" + intentProject.getName() + "/.repository/"
				+ IntentLocations.INTENT_INDEX + '.' + WorkspaceRepository.getWorkspaceResourceExtension());
	}

	/**
	 * Ensures that external changes (i.e. changes on the document made without using the RepositoryAdapter,
	 * for example git updates) are correctly handled.
	 */
	public void testExternalChangesOnCompiler() {
		// Step 1: modify the IntentDocument model without using the repository adapter
		// Adding a new modeling unit that contribute to the abstract root by setting its nsURI
		IntentDocument intentDocument = (IntentDocument)new ResourceSetImpl().getResource(documentURI, true)
				.getContents().iterator().next();
		IntentSection intentSection = intentDocument.getSubSections().get(0).getSubSections().get(0);
		assertEquals("Wrong initial state:expecting only 2 modeling units", 2, intentSection
				.getModelingUnits().size());
		ModelingUnit newModelingUnit = ModelingUnitFactory.eINSTANCE.createModelingUnit();
		ContributionInstruction contributionInstruction = ModelingUnitFactory.eINSTANCE
				.createContributionInstruction();
		ModelingUnitInstructionReference contributionReference = ModelingUnitFactory.eINSTANCE
				.createModelingUnitInstructionReference();
		contributionReference.setIntentHref("myAbstractRoot");
		contributionInstruction.setContributionReference(contributionReference);
		StructuralFeatureAffectation setNsURIInstruction = ModelingUnitFactory.eINSTANCE
				.createStructuralFeatureAffectation();
		setNsURIInstruction.setName("nsURI");
		NativeValue nsURIValue = ModelingUnitFactory.eINSTANCE.createNativeValue();
		nsURIValue.setValue("myNsURI");
		setNsURIInstruction.getValues().add(nsURIValue);
		contributionInstruction.getContributions().add(setNsURIInstruction);
		newModelingUnit.getInstructions().add(contributionInstruction);
		intentSection.getIntentContent().add(newModelingUnit);

		repositoryListener.clearPreviousEntries();
		try {
			intentDocument.eResource().save(null);
			Thread.sleep(1000);
		} catch (IOException e) {
			AssertionFailedException assertionFailed = new AssertionFailedException(e.getMessage());
			assertionFailed.setStackTrace(e.getStackTrace());
			throw assertionFailed;
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}

		// Step 2: check that intent model was updated
		EObject intentDocumentAsLoadedByRepositoryAdapter = repositoryAdapter
				.getResource(IntentLocations.INTENT_INDEX).getContents().iterator().next();
		IntentSection intentSectionAsLoadedByRepositoryAdapter = ((IntentDocument)intentDocumentAsLoadedByRepositoryAdapter)
				.getSubSections().get(0).getSubSections().get(0);
		assertEquals("Intent section should have been reloaded due to external changes", 3,
				intentSectionAsLoadedByRepositoryAdapter.getModelingUnits().size());

		// Step 3: check that compiler was called
		waitForCompiler();
		Resource generatedResource = repositoryAdapter
				.getResource(IntentLocations.GENERATED_RESOURCES_FOLDER_PATH + "/abstractResource");
		assertNotNull("Compiler did not re-generate the resource due to external changes", generatedResource);
		assertTrue("Compiler generated an empty resource",
				generatedResource.getContents().iterator().next() instanceof EPackage);
		assertEquals("Compiler did not take in acount external changes", "myNsURI",
				((EPackage)generatedResource.getContents().iterator().next()).getNsURI());

	}

	/**
	 * Ensures that external changes (i.e. changes on the document made without using the RepositoryAdapter,
	 * for example git updates) are correctly handled.
	 */
	public void testExternalChangesOnDocumentWithDocumentOpened() {
		// Step 1 : open an editor on the root document
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());

		// Step 2: modify the IntentDocument model without using the repository adapter
		IntentDocument intentDocument = (IntentDocument)new ResourceSetImpl().getResource(documentURI, true)
				.getContents().iterator().next();
		assertEquals("Wrong initial state: Intent document should have only one chapter", 1, intentDocument
				.eContents().size());
		assertEquals("Wrong initial state: Intent document should have only one chapter", 2, document.get()
				.split("Chapter").length);

		intentDocument.getIntentContent().add(IntentDocumentFactory.eINSTANCE.createIntentSection());
		try {
			intentDocument.eResource().save(null);
			Thread.sleep(1000);
		} catch (IOException e) {
			AssertionFailedException assertionFailed = new AssertionFailedException(e.getMessage());
			assertionFailed.setStackTrace(e.getStackTrace());
			throw assertionFailed;
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}

		// Step 3: check that intent model was updated
		EObject intentDocumentAsLoadedByRepositoryAdapter = repositoryAdapter
				.getResource(IntentLocations.INTENT_INDEX).getContents().iterator().next();
		assertEquals("Intent document should have been reloaded due to external changes", 2,
				intentDocumentAsLoadedByRepositoryAdapter.eContents().size());

		// Step 4: check that editor was updated
		waitForAllOperationsInUIThread();
		assertEquals("Editor should have been reloaded", 3, document.get().split("Chapter").length);
	}

	/**
	 * Ensures that external changes (i.e. changes on the document made without using the RepositoryAdapter,
	 * for example git updates) are correctly handled.
	 */
	public void testExternalChangesOnChapterWithDocumentOpened() {
		// Step 1 : open an editor on the root document
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());

		// Step 2: modify the IntentChapter without using the repository adapter
		IntentDocument intentDocument = (IntentDocument)new ResourceSetImpl().getResource(documentURI, true)
				.getContents().iterator().next();
		IntentSection intentSection = intentDocument.getSubSections().get(0).getSubSections().get(0);
		assertEquals("Wrong initial state: Intent section should have only 2 modeling units", 2,
				intentSection.getModelingUnits().size());
		assertEquals("Wrong initial state: Intent section should have only 2 modeling units", 3, document
				.get().split("@M").length);

		intentSection.getIntentContent().add(ModelingUnitFactory.eINSTANCE.createModelingUnit());
		try {
			intentDocument.eResource().save(null);
			Thread.sleep(1000);
		} catch (IOException e) {
			AssertionFailedException assertionFailed = new AssertionFailedException(e.getMessage());
			assertionFailed.setStackTrace(e.getStackTrace());
			throw assertionFailed;
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}

		// Step 3: check that intent model was updated
		EObject intentDocumentAsLoadedByRepositoryAdapter = repositoryAdapter
				.getResource(IntentLocations.INTENT_INDEX).getContents().iterator().next();
		IntentSection intentSectionAsLoadedByRepositoryAdapter = ((IntentDocument)intentDocumentAsLoadedByRepositoryAdapter)
				.getSubSections().get(0).getSubSections().get(0);
		assertEquals("Intent document should have been reloaded due to external changes", 3,
				intentSectionAsLoadedByRepositoryAdapter.getModelingUnits().size());

		// Step 4: check that editor was updated
		waitForAllOperationsInUIThread();
		assertEquals("Editor should have been reloaded", 4, document.get().split("@M").length);
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
