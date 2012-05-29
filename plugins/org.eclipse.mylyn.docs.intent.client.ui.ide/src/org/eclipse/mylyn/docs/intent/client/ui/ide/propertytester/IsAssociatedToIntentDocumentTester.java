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
package org.eclipse.mylyn.docs.intent.client.ui.ide.propertytester;

import java.util.Collection;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.mylyn.docs.intent.client.ui.ide.builder.IntentNature;
import org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry;

/**
 * Returns true if the tester element is associated to an IntentDocument (i.e. is an Intent Document or can be
 * adapted as such or is an Intent project).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IsAssociatedToIntentDocumentTester extends PropertyTester {

	/**
	 * Default constructor.
	 */
	public IsAssociatedToIntentDocumentTester() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String,
	 *      java.lang.Object[], java.lang.Object)
	 */
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (isIntentProject(receiver)) {
			return true;
		}
		Object document = getIntentDocument(receiver);

		// If we are facing an index entry, we get the associated IntentDocument if any
		if (document instanceof IntentIndexEntry) {
			document = ((IntentIndexEntry)document).getReferencedElement();
		}
		return document instanceof IntentDocument;
	}

	private boolean isIntentProject(Object receiver) {
		Object any = receiver;
		if (receiver instanceof Collection<?> && ((Collection<?>)receiver).iterator().hasNext()) {
			any = ((Collection<?>)receiver).iterator().next();
		}
		if (receiver instanceof IStructuredSelection && !((IStructuredSelection)receiver).isEmpty()) {
			any = ((IStructuredSelection)receiver).iterator().next();
		}

		if (any instanceof IProject) {
			try {
				boolean hasIntentNature = ((IProject)any).hasNature(IntentNature.NATURE_ID);
				return hasIntentNature;
			} catch (CoreException e) {
				// Silent catch
			}
		}
		return false;
	}

	/**
	 * Returns the intent document associated to the given element.
	 * 
	 * @param any
	 *            the element from which get the Intent Document
	 * @return the intent document if the given element is an Intent Project, or directly the Intent Document;
	 *         null otherwise.
	 */
	public static IntentDocument getIntentDocument(Object any) {
		IntentDocument document = null;
		if (any instanceof Collection<?> && ((Collection<?>)any).iterator().hasNext()) {
			any = ((Collection<?>)any).iterator().next();
		}
		if (any instanceof IStructuredSelection && !((IStructuredSelection)any).isEmpty()) {
			any = ((IStructuredSelection)any).iterator().next();
		}
		if (any instanceof IProject) {

			// if the selected element is an IProject
			// we get the associated intent document if any
			IProject project = (IProject)any;
			RepositoryAdapter repositoryAdapter = null;
			try {
				Repository repository = IntentRepositoryManager.INSTANCE.getRepository(project.getName());
				if (repository != null) {
					repositoryAdapter = repository.createRepositoryAdapter();

					EList<EObject> contents = repositoryAdapter.getResource(IntentLocations.INTENT_INDEX)
							.getContents();
					if (!contents.isEmpty() && contents.iterator().next() instanceof IntentDocument) {
						document = (IntentDocument)contents.iterator().next();
					}
				}
			} catch (RepositoryConnectionException e) {
				// silently fail, test will return false
			} catch (CoreException e) {
				// silently fail, test will return false
			} finally {
				if (repositoryAdapter != null) {
					repositoryAdapter.closeContext();
				}
			}
		} else {
			// If the selected element can be adapted as an EObject
			if (any instanceof IAdaptable
					&& ((IAdaptable)any).getAdapter(EObject.class) instanceof IntentDocument) {
				// We consider the EObject (that may be the Intent document
				document = (IntentDocument)((IAdaptable)any).getAdapter(EObject.class);
			}
		}
		return document;
	}
}
