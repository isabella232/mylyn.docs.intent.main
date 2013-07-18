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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.repository;

import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;

/**
 * Ensures that {@link URI}s of the form 'intent:/' return the expected content:
 * <ul>
 * <li>intent:/INTENT_REPOSITORY_IDENTIFIER returns a Resource containing the IntentDocument</li>
 * <li>intent:/INTENT_REPOSITORY_IDENTIFIER#/ returns the IntentDocument</li>
 * <li>intent:/INTENT_REPOSITORY_IDENTIFIER/abstractResource returns a Resource containing the compiled
 * resource entitled 'abstractResource'</li>
 * <li>intent:/INTENT_REPOSITORY_IDENTIFIER/abstractResource#/ returns the content of the Resource containing
 * the compiled resource entitled 'abstractResource'</li>
 * </ul>
 * .
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
@SuppressWarnings("restriction")
public class IntentURITest extends AbstractIntentUITest {

	/**
	 * Constant used to create assertion failure messages.
	 */
	private static final String INVALID_URI_ERROR_MESSAGE = ": this URI is invalid, an error should have been thrown";

	/**
	 * Ensures that {@link URI}s of the form 'intent:/' return the expected content. Also checks error cases.
	 */
	public void testIntentURIHandlerOnIntentDocument() {
		setUpIntentProject("intentProject", "data/unit/documents/scenario/abstract_resources.intent", true);
		ResourceSetImpl rs = new ResourceSetImpl();

		// Check 1: URI like intent:/intentProject should return a Resource containing the intent document
		URI intentDocumentResourceURI = URI.createURI("intent:/intentProject");
		assertTrue(
				intentDocumentResourceURI + " should return an IntentDocument",
				rs.getResource(intentDocumentResourceURI, true).getContents().iterator().next() instanceof IntentDocument);

		// Check 2: URI like intent:/intentProject#/ should return the IntentDocument
		URI intentDocumentURI = URI.createURI("intent:/intentProject#/");
		assertTrue(intentDocumentURI + " should return an IntentDocument",
				rs.getEObject(intentDocumentURI, true) instanceof IntentDocument);

		// Check 3: URI like intent:/intentProject/abstractResource should return the resource containing the
		// compiled Intent Resource 'abstractResource'
		URI intentCompiledResourceURI = URI.createURI("intent:/intentProject/abstractResource");
		assertTrue(
				intentCompiledResourceURI + " should return the generated resource",
				rs.getResource(intentCompiledResourceURI, true).getContents().iterator().next() instanceof EPackage);

		// Check 4: URI like intent:/intentProject/abstractResource#/ should return the return the content of
		// the resource containing the compiled Intent Resource 'abstractResource'
		URI intentCompiledElementURI = URI.createURI("intent:/intentProject/abstractResource#/");
		assertTrue(intentCompiledElementURI + " should return the generated resource",
				rs.getEObject(intentCompiledElementURI, true) instanceof EPackage);

		// ERROR HANDLING
		// Check 5: make sure that we fail correctly if the URI references an invalid Intent Project
		try {
			URI invalidIntentRepositoryIdentifier = URI.createURI("intent:/intentInvalidProject#/");
			rs.getEObject(invalidIntentRepositoryIdentifier, true);
			fail(invalidIntentRepositoryIdentifier + INVALID_URI_ERROR_MESSAGE);
			// CHECKSTYLE:OFF
		} catch (RuntimeException e) {
			// CHECKSTYLE:ON
			assertTrue(e.getCause() instanceof RepositoryConnectionException);
		}

		// Check 6: make sure that we fail correctly if the URI references an invalid generated resource
		try {
			URI invalidGeneratedResourcePath = URI.createURI("intent:/intentProject/invalidResource#/");
			rs.getEObject(invalidGeneratedResourcePath, true);
			fail(invalidGeneratedResourcePath + INVALID_URI_ERROR_MESSAGE);
			// CHECKSTYLE:OFF
		} catch (Exception e) {
			// CHECKSTYLE:ON
			assertTrue(e.getCause() instanceof ResourceException);
		}

		// Check 7: make sure that we fail correctly if the URI is not well formed
		try {
			URI invalidGeneratedResourcePath = URI
					.createURI("intent:/intentProject/invalidURI/invalidPath#/");
			rs.getEObject(invalidGeneratedResourcePath, true);
			fail(invalidGeneratedResourcePath + INVALID_URI_ERROR_MESSAGE);
			// CHECKSTYLE:OFF
		} catch (Exception e) {
			// CHECKSTYLE:ON
			assertTrue(e.getCause() instanceof ResourceException);
		}

		// Check 8: make sure that we fail correctly if the URI is not well formed
		try {
			URI invalidGeneratedResourcePath = URI.createURI("intent:/#/");
			rs.getEObject(invalidGeneratedResourcePath, true);
			fail(invalidGeneratedResourcePath + INVALID_URI_ERROR_MESSAGE);
			// CHECKSTYLE:OFF
		} catch (Exception e) {
			// CHECKSTYLE:ON
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest#additionalSetUpOperations()
	 */
	@Override
	protected void additionalSetUpOperations() {
		waitForCompiler();
		waitForSynchronizer();
	}
}
