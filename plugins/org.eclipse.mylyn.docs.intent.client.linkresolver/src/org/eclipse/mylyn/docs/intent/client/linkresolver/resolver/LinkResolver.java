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
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.common.query.IntentDocumentQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReferenceInstruction;

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
	 * Resolves all the links.
	 * 
	 * @param monitor
	 *            the progress monitor (can be cancelled at any time)
	 */
	public void resolve(IProgressMonitor monitor) {
		Collection<IntentReferenceInstruction> allIntentReferenceInstructions = new IntentDocumentQuery(
				repositoryAdapter).getAllIntentReferenceInstructions();
		for (IntentReferenceInstruction referenceInstruction : allIntentReferenceInstructions) {
			resolveReference(referenceInstruction);
		}
	}

	/**
	 * Resolves the given {@link IntentReferenceInstruction}.
	 * 
	 * @param referenceInstruction
	 *            the {@link IntentReferenceInstruction} to resolve
	 */
	private void resolveReference(IntentReferenceInstruction referenceInstruction) {
		if (referenceInstruction.getReferencedElement() != null) {

			// We should be smarter and only resolve the link when needed
			String href = referenceInstruction.getIntentHref();
			if (href != null && href.length() > 0) {
				try {
					// TODO resolve other references (currently only sections)
					IntentStructuredElement elementAtLevel = documentQuery.getAllIdentifiedSections().get(
							href);
					// desactivated: attempts to resolve titles
					// IntentStructuredElement elementAtLevel = documentQuery.getElementAtLevel(href);
					if (elementAtLevel != null) {
						referenceInstruction.setReferencedElement(elementAtLevel);
					} else {
						// TODO : we should place a new Status on this reference instruction
						IntentLogger.getInstance().log(LogType.WARNING, "Unresolved " + href);
					}
				} catch (NumberFormatException e) {
					// TODO : we should place a new Status on this reference instruction
				}
			}
		}

	}
}
