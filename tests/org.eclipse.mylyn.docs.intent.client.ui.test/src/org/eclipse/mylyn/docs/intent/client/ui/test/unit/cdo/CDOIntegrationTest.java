/*******************************************************************************
 * Copyright (c) 2010, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.cdo;

import org.eclipse.mylyn.docs.intent.client.ui.test.unit.cdo.util.AbstractIntentCDOTest;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;

/**
 * Basic tests ensuring that Intent works correctly when the document is stored on a CDO Repository.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class CDOIntegrationTest extends AbstractIntentCDOTest {

	private static final String INTENT_ABSTRACT_RESOURCE_DOCUMENT_PATH = "data/unit/documents/scenario/abstract_resources.intent";

	/**
	 * A test that ensure that a local user can :
	 * <ul>
	 * <li>Create/get an intent document located on a cdo repository</li>
	 * <li>Receive changes made by remote users.</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	public void testBasicRelationshipBetweenLocalAndRemoteUser() throws Exception {
		// Local user creates and get the intent project
		setUpIntentProject("myIntentProject", INTENT_ABSTRACT_RESOURCE_DOCUMENT_PATH, false);
		final IntentDocument cdoIntentDocument = getIntentDocument();
		repositoryAdapter.save();
		final int initialChapterNumber = cdoIntentDocument.getChapters().size();
		assertNotNull("Intent document has not been correctly created on the repository", cdoIntentDocument);

		// Some remote user modifies the intent document by adding a chapter
		final RepositoryAdapter remoteUser = IntentRepositoryManager.INSTANCE.getRepository(
				getIntentRepositoryIdentifier()).createRepositoryAdapter();
		remoteUser.openSaveContext();
		remoteUser.execute(new IntentCommand() {

			public void execute() {
				IntentDocument remoteIntentDocument;
				try {
					remoteIntentDocument = (IntentDocument)remoteUser
							.getOrCreateResource(IntentLocations.INTENT_INDEX).getContents().iterator()
							.next();
					assertFalse("Remote and Local user should not share the same instance",
							remoteIntentDocument == cdoIntentDocument);
					remoteIntentDocument.getChapters().add(
							IntentDocumentFactory.eINSTANCE.createIntentChapter());
				} catch (ReadOnlyException e) {
					fail(e.getMessage());
				}
			}
		});
		assertEquals("Remote modification should not have been received by the local user",
				initialChapterNumber, cdoIntentDocument.getChapters().size());
		remoteUser.save();
		remoteUser.closeContext();

		assertEquals("Remote modification should have been received by the local user",
				initialChapterNumber + 1, cdoIntentDocument.getChapters().size());
	}

	/**
	 * Ensures that when remote users make modifications and commit.
	 * 
	 * @throws Exception
	 */
	// public void testRemoteChangesIntegrationInsideEditor() throws Exception {
	// Local user opens an editor on an Intent document
	// setUpIntentProject("myIntentProject", INTENT_ABSTRACT_RESOURCE_DOCUMENT_PATH, false);
	// waitForAllOperationsInUIThread();
	// IntentEditor editor = openIntentEditor(getIntentDocument());
	// waitForAllOperationsInUIThread();
	// String initialContent = ((IntentEditorDocument)editor.getDocumentProvider().getDocument(
	// editor.getEditorInput())).get();

	// Remote user adds an untitle chapter
	// final RepositoryAdapter remoteUser = IntentRepositoryManager.INSTANCE.getRepository(
	// "cdo:/myIntentProject").createRepositoryAdapter();
	// remoteUser.openSaveContext();
	// remoteUser.execute(new IntentCommand() {
	//
	// public void execute() {
	// IntentDocument remoteIntentDocument;
	// try {
	// remoteIntentDocument = (IntentDocument)remoteUser
	// .getOrCreateResource(IntentLocations.INTENT_INDEX).getContents().iterator()
	// .next();
	// remoteIntentDocument.getChapters().add(
	// IntentDocumentFactory.eINSTANCE.createIntentChapter());
	// } catch (ReadOnlyException e) {
	// fail(e.getMessage());
	// }
	// }
	// });
	// remoteUser.save();
	// remoteUser.closeContext();
	// waitForAllOperationsInUIThread();

	// Local user should see the new chapter
	// assertNotSame(initialContent,
	// ((IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput()))
	// .get());
	//
	// editor.close(false);
	// }
}
