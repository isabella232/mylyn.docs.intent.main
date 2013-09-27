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
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.externalparsers.sample.SampleExternalParser;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.external.parser.internal.IntentExternalParserActivator;
import org.eclipse.mylyn.docs.intent.external.parser.internal.IntentExternalParserContributionRegistryListener;
import org.junit.Test;

/**
 * Ensures that {@link org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser}s can be
 * contributed and are correctly handled.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ExternalParserActivationTest extends AbstractIntentUITest {

	/**
	 * The currently opened {@link IntentEditor}.
	 */
	private IntentEditor editor;

	/**
	 * The {@link IntentEditorDocument} associated to the currently opened {@link IntentEditor}.
	 */
	private IntentEditorDocument document;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setUpIntentProject("intentTest", INTENT_EMPTY_DOC_PATH, true);

	}

	/**
	 * Ensures that the external parsers extension point allows to add new external parsers.
	 * 
	 * @throws CoreException
	 *             if project cannot be reopened
	 */
	@Test
	public void testExternalParserActivation() throws CoreException {
		// Step 1: open an editor without the SampleExternalParser contributed
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());

		document.set(document.get() + " " + 0);
		editor.doSave(new NullProgressMonitor());
		editor.close(false);
		// => sample external parser should never be created
		waitForLifecycleMessage(false, SampleExternalParser.SAMPLE_EXTERNAL_PARSER_ID);
		waitForAllOperationsInUIThread();

		// Step 2: contribute the SampleExternalParser
		addExternalParserExtension(SampleExternalParser.class);
		// Reopen project so that client can be created
		reopenIntentProject();
		editor = openIntentEditor();
		document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(editor.getEditorInput());
		waitForAllOperationsInUIThread();
		document.set(document.get() + " " + 0);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		waitForLifecycleMessage(true, SampleExternalParser.SAMPLE_EXTERNAL_PARSER_ID);
		waitForAllOperationsInUIThread();
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
