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
package org.eclipse.mylyn.docs.intent.client.linkresolver.resolver;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.docs.intent.collab.common.query.IntentDocumentQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentSectionOrParagraphReference;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentSectionReferenceInstruction;

/**
 * Resolves all the references to Intent Structured Element (sections, chapters...).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class LinkResolver {

	/**
	 * The repository Adapter to use for manipulating the Intent repository.
	 */
	private RepositoryAdapter repositoryAdapter;

	private IntentDocumentQuery documentQuery;

	/**
	 * Creates a new {@link LinkResolver}.
	 * 
	 * @param repositoryAdapter
	 *            the repository Adapter to use for manipulating the Intent repository
	 */
	public LinkResolver(RepositoryAdapter repositoryAdapter) {
		this.repositoryAdapter = repositoryAdapter;
		this.documentQuery = new IntentDocumentQuery(repositoryAdapter);
	}

	/**
	 * Resolves all the links
	 * 
	 * @param monitor
	 *            the progress monitor (can be cancelled at any time)
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
	public void resolve(IProgressMonitor monitor) {
		Collection<IntentSectionReferenceInstruction> allIntentReferenceInstructions = new IntentDocumentQuery(
				repositoryAdapter).getAllIntentReferenceInstructions();
		for (IntentSectionReferenceInstruction referenceInstruction : allIntentReferenceInstructions) {
			resolveReference(referenceInstruction);
		}
	}

	/**
	 * Resolves the given {@link IntentSectionReferenceInstruction}.
	 * 
	 * @param referenceInstruction
	 *            the {@link IntentSectionReferenceInstruction} to resolve
	 */
	private void resolveReference(IntentSectionReferenceInstruction referenceInstruction) {
		if (referenceInstruction.getReferencedObject() != null) {
			IntentSectionOrParagraphReference reference = referenceInstruction.getReferencedObject();

			// We should be smarter and only resolve the link when needed
			String href = reference.getIntentHref();
			if (href != null && href.length() > 0) {
				try {
					// TODO resolve other references (currently only sections)
					IntentStructuredElement elementAtLevel = documentQuery.getAllIdentifiedSections().get(
							href);
					// desactivated: attempts to resolve titles
					// IntentStructuredElement elementAtLevel = documentQuery.getElementAtLevel(href);
					if (elementAtLevel != null) {
						reference.setReferencedObject(elementAtLevel);
					} else {
						// TODO : we should place a new Status on this reference instruction
						System.err.println("Unresolved " + href);
					}
				} catch (NumberFormatException e) {
					// TODO : we should place a new Status on this reference instruction
				}
			}
		}

	}
}
