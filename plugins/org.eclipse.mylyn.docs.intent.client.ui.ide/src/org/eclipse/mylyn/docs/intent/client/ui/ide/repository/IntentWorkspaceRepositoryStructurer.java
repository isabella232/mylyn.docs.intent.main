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
package org.eclipse.mylyn.docs.intent.client.ui.ide.repository;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.ide.adapters.DefaultWorkspaceRepositoryStructurer;
import org.eclipse.mylyn.docs.intent.collab.ide.adapters.WorkspaceAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentChapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentSubSectionContainer;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;

/**
 * Structurer for a Workspace repository containing Intent informations.
 * <p>
 * Resource are spited according to the following heuristic :
 * <ul>
 * <li>Each structured element (IntentDocument, IntentChapter, IntentSection) is sorted according to its type
 * (one folder for each type) and is placed in its own resource.</li>
 * <li>Each ModelingUnit is placed in the ModelingUnit folder and in its own resource.</li>
 * <li>Each DecriptionUnit is placed in the DecriptionUnit folder and in its own resource.</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentWorkspaceRepositoryStructurer extends DefaultWorkspaceRepositoryStructurer {

	/**
	 * A separator used to compute identifer (e.g. 5.3.2).
	 */
	private static final String IDENTIFIER_SEPARATOR = ".";

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.ide.adapters.DefaultWorkspaceRepositoryStructurer#structure(org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter)
	 */
	@Override
	public void structure(RepositoryAdapter repositoryAdapter) throws ReadOnlyException {
		super.structure(repositoryAdapter);

		// To split the document, uncomment the following code
		// WorkspaceAdapter workspaceAdapter = (WorkspaceAdapter)repositoryAdapter;
		// IntentDocument document = new IntentDocumentQuery(workspaceAdapter).getOrCreateIntentDocument();
		// splitElementAndSons(workspaceAdapter, document);
	}

	/**
	 * Place the given element in the correct resource and splits its contained elements.
	 * 
	 * @param workspaceAdapter
	 *            the RepositoryAdapter to use for restructuring the repository
	 * @param element
	 *            the element to split
	 * @return
	 * @throws ReadOnlyException
	 *             if the current context associated to the given adapter is read-only
	 */
	protected void splitElementAndSons(WorkspaceAdapter workspaceAdapter, EObject element)
			throws ReadOnlyException {

		if (isElementToSplit(element)) {

			// The resource path should check the following structure :
			// <INTENT_FOLDER> <CLASS_NAME> / <IDENTIFIER>
			String newResourcePath = IntentLocations.INTENT_FOLDER + element.eClass().getName() + "/"
					+ getIdentifierForElement(workspaceAdapter, element);

			// Fist we check if the given element is in the same resource than its container (if it is the
			// case, we will have to move it)
			boolean isInSameResourceThanContainer = isInSameResourceThanContainer(element);

			// Then we ensure that the element is stored at the expected location
			if (isInSameResourceThanContainer
					|| !(isStoredAtExpectedLocation(element, workspaceAdapter, newResourcePath))) {

				// Place the element in a new resource
				Resource newResource = workspaceAdapter.getOrCreateResource(newResourcePath);
				newResource.getContents().add(element);
			}

			// Do the same for all children of the given element
			for (EObject containedElement : element.eContents()) {
				if (!containedElement.eIsProxy()) {
					splitElementAndSons(workspaceAdapter, containedElement);
				}
			}
		}
	}

	/**
	 * Returns the resource identifier to associate with the given element.
	 * 
	 * @param workspaceAdapter
	 *            is the workspace adapter
	 * @param element
	 *            the element to consider
	 * @return the resource identifier to associate with the given element
	 */
	private String getIdentifierForElement(WorkspaceAdapter workspaceAdapter, EObject element) {
		String proposal = "";
		// If element is the Intent document
		if (element instanceof IntentDocument) {
			return "IntentDocument";
		}

		// Otherwise, we get an identifier based on the document structure
		// e.g "1.2.1"
		EObject container = element;
		while (!(container instanceof IntentDocument)) {
			if (container instanceof ModelingUnit) {
				proposal = (((IntentSection)container.eContainer()).getModelingUnits().indexOf(container) + 1)
						+ IDENTIFIER_SEPARATOR + proposal;
			} else {
				if (container instanceof IntentSection) {
					proposal = (((IntentSubSectionContainer)container.eContainer()).getSubSections().indexOf(
							container) + 1)
							+ IDENTIFIER_SEPARATOR + proposal;
				} else {
					if (container instanceof IntentChapter) {
						proposal = (((IntentDocument)container.eContainer()).getChapters().indexOf(container) + 1)
								+ IDENTIFIER_SEPARATOR + proposal;
					} else {
						proposal = (container.eContainer().eContents().indexOf(container) + 1)
								+ IDENTIFIER_SEPARATOR + proposal;
					}
				}
			}
			container = container.eContainer();
		}
		proposal = proposal.substring(0, proposal.length() - 1);
		return proposal;
	}

	/**
	 * Indicates whether the given element is stored at the expected resource path.
	 * 
	 * @param element
	 *            the element to test
	 * @param workspaceAdapter
	 *            the workspace adapter
	 * @param expectedResourceLocation
	 *            the element expected location
	 * @return true if the element is stored at the expected resource path
	 */
	private boolean isStoredAtExpectedLocation(EObject element, WorkspaceAdapter workspaceAdapter,
			String expectedResourceLocation) {
		boolean isCorrectlySplit = true;
		try {
			isCorrectlySplit = isCorrectlySplit
					&& element.eResource() == workspaceAdapter.getResource(expectedResourceLocation, false);
			// CHECKSTYLE:OFF
		} catch (Exception e) {
			// CHECKSTYLE:ON
			isCorrectlySplit = false;
		}
		return isCorrectlySplit || (element instanceof IntentDocument);
	}

	/**
	 * Indicates if the given element is associated to the same resource than its container.
	 * 
	 * @param element
	 *            the element to check
	 * @return true if the given element is associated to the same resource than its container, false
	 *         otherwise
	 */
	private boolean isInSameResourceThanContainer(EObject element) {
		boolean isInSameResourceThanContainer = element.eResource() == null;
		isInSameResourceThanContainer = isInSameResourceThanContainer || (element.eContainer() == null);
		isInSameResourceThanContainer = isInSameResourceThanContainer
				|| (element.eContainer().eResource() == element.eResource());
		return isInSameResourceThanContainer && !(element instanceof IntentDocument);
	}

	/**
	 * Indicates if the given element should be placed in its own resource.
	 * 
	 * @param element
	 *            the element to inspect
	 * @return true if the given element should be placed in its own resource, false otherwise
	 */
	protected boolean isElementToSplit(EObject element) {
		boolean isElementToSplit = (element instanceof IntentDocument) || (element instanceof IntentChapter)
				|| (element instanceof IntentSection);
		return isElementToSplit || element instanceof ModelingUnit;
	}
}
