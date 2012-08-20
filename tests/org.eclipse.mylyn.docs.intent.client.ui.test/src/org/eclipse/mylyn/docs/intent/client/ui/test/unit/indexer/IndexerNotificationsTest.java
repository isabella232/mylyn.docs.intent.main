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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.indexer;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.collab.common.query.TraceabilityInformationsQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;

/**
 * Ensures that the Intent {@link org.eclipse.mylyn.docs.intent.client.indexer.IndexerRepositoryClient} is
 * correctly notified.
 * <p>
 * Relevant issues :
 * <ul>
 * <li>Editor prevents content change after insert of the second section :
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=374698</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IndexerNotificationsTest extends AbstractIntentUITest {

	public void testIndexerNotifications() throws ReadOnlyException, SaveException {
		// Step 1: we initialize an intent project
		setUpIntentProject("intentProject",
				"data/unit/documents/editorupdates/changeEditorUpdateTest.intent", true);

		// Step 2: make a structural modification :
		IntentEditor editor = openIntentEditor();
		repositoryListener.clearPreviousEntries();
		IntentEditorDocument document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(
				editor.getEditorInput());
		document.set("Document {\n\tChapter {\n\t}\n" + document.get().replace("Document {", ""));
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		editor.close(false);
		waitForAllOperationsInUIThread();
		// -> indexer should be notified only once
		waitForIndexer(true);
		waitForIndexer(false);

		// Step 3: make a non structural modification
		final TraceabilityIndex index = new TraceabilityInformationsQuery(repositoryAdapter)
				.getOrCreateTraceabilityIndex();
		repositoryAdapter.execute(new IntentCommand() {
			public void execute() {
				index.getEntries().clear();
			}
		});
		repositoryListener.clearPreviousEntries();
		repositoryAdapter.save();
		// -> indexer should not be notified
		waitForIndexer(false);
	}

}
