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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.externalparsers;

import com.google.common.collect.Maps;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.externalparsers.sample.SampleExternalParser;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.external.parser.internal.IntentExternalParserActivator;
import org.eclipse.mylyn.docs.intent.external.parser.internal.IntentExternalParserContributionRegistryListener;
import org.eclipse.mylyn.docs.intent.parser.IntentKeyWords;
import org.junit.Test;

/**
 * Ensures that {@link org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser}s can be
 * contributed and are correctly handled.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ExternalParsersTest extends AbstractIntentUITest {

	/**
	 * Path of the file containing the example intent document used by this test.
	 */
	private static final String INTENT_DOCUMENT_PATH = "data/unit/documents/externalparsers/externalparser.intent";

	/**
	 * The currently opened {@link IntentEditor}.
	 */
	private IntentEditor editor;

	/**
	 * The {@link IntentEditorDocument} associated to the currently opened {@link IntentEditor}.
	 */
	private IntentEditorDocument document;

	/**
	 * Ensures that the external parsers extension point allows to add new external parsers.
	 * 
	 * @throws CoreException
	 *             if project cannot be reopened
	 */
	@Test
	public void testExternalParserActivation() throws CoreException {
		setUpIntentProject("intentTest", INTENT_DOCUMENT_PATH, true);
		URI externalParserResourceURI = URI.createURI(repositoryAdapter.getRepository().getRepositoryURI()
				+ SampleExternalParser.EXTERNAL_PARSER_RESOURCE_NAME);
		// Step 1: open an editor without the SampleExternalParser contributed
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());

		document.set(document.get() + IntentKeyWords.INTENT_WHITESPACE);
		editor.doSave(new NullProgressMonitor());
		editor.close(false);
		// => sample external parser should never be created
		waitForLifecycleMessage(false, SampleExternalParser.SAMPLE_EXTERNAL_PARSER_ID);
		// => and hence should not create any resource
		try {
			new ResourceSetImpl().getResource(externalParserResourceURI, true);
			fail("The sample external parser should not have created any resource");
		} catch (WrappedException e) {
			// Silent catch: supposed to fail
		}
		waitForAllOperationsInUIThread();

		// Step 2: contribute the SampleExternalParser
		addExternalParserExtension(SampleExternalParser.class);
		// Reopen project so that client can be created
		reopenIntentProject();
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
		waitForAllOperationsInUIThread();
		document.set(document.get() + IntentKeyWords.INTENT_WHITESPACE + 0);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		// => sample external parser should be created & initialized
		waitForLifecycleMessage(true, SampleExternalParser.SAMPLE_EXTERNAL_PARSER_ID);
		// => and hence should create a resource
		assertNotNull("The sample external parser should have created a resource",
				new ResourceSetImpl().getResource(externalParserResourceURI, true));

	}

	/**
	 * Ensures that the external parsers can modify on-the-fly the intent document (e.g. by adding external
	 * content references).
	 * 
	 * @throws CoreException
	 *             if project cannot be reopened
	 */
	@Test
	public void testExternalParserCanModifyIntentDocument() throws CoreException {
		// Step 1: contribute and external parser and open an intent editor on a sub-sub-section
		addExternalParserExtension(SampleExternalParser.class);
		setUpIntentProject("intentTest", INTENT_DOCUMENT_PATH, true);
		editor = openIntentEditor(getIntentSection(1, 1, 1));
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
		assertFalse("External parsers should not have created reference yet", document.get().contains("@ref"));
		document.set(document.get().replace("Some description unit to parse", "SampleExternalParser"));
		repositoryListener.clearPreviousEntries();
		editor.doSave(new NullProgressMonitor());

		// => sample external parser should have been called
		waitForLifecycleMessage(true, SampleExternalParser.SAMPLE_EXTERNAL_PARSER_ID);
		// => and external parser should have modified the document
		waitForAllOperationsInUIThread();
		assertTrue(
				"External parsers should have modified the document by creating external content references",
				document.get().contains("@ref"));
	}

	/**
	 * Adds a contribution to the external parser extension point with the given values.
	 * 
	 * @param contributedExternalParser
	 *            the class of the
	 *            {@link org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser} to
	 *            contribute
	 */
	protected void addExternalParserExtension(Class<?> contributedExternalParser) {
		Map<String, String> extensionValues = Maps.newLinkedHashMap();
		extensionValues.put("class", contributedExternalParser.getCanonicalName());
		addExtension(
				IntentExternalParserContributionRegistryListener.INTENT_EXTERNAL_PARSER_CONTRIBUTION_EXTENSION_POINT,
				IntentExternalParserContributionRegistryListener.EXTERNAL_PARSER_CONTRIBUTION_TAG,
				extensionValues, IntentExternalParserActivator.getInstance(),
				"externalParserContributionsRegistryListener");
	}
}
