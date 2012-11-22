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
package org.eclipse.mylyn.docs.intent.client.ui.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry;

/**
 * A class providing facilities for getting the {@link IntentStructuredElement}s corresponding to a selection.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentSelectionUtil {

	/**
	 * Private constructor.
	 */
	private IntentSelectionUtil() {

	}

	/**
	 * Returns the {@link IntentStructuredElement}s corresponding to the given object (can be an
	 * {@link IStructuredSelection}, a collection of selected objects...).
	 * 
	 * @param receiver
	 *            the object to get the {@link IntentStructuredElement}s from (can be an
	 *            {@link IStructuredSelection}, a collection of selected objects...)
	 * @return the {@link IntentStructuredElement}s corresponding to the given object (can be an
	 *         {@link IStructuredSelection}, a collection of selected objects...)
	 */
	public static Collection<IntentStructuredElement> getIntentElements(Object receiver) {
		Collection<?> selectedObjects = getSelectedObjects(receiver);
		Collection<IntentStructuredElement> correspondingIntentElements = Sets.newLinkedHashSet();
		for (Object selectedObject : selectedObjects) {
			// If the selected Object is an Intent project
			if (isIntentProject(selectedObject)) {
				IntentDocument intentDocumentFromProject = getIntentDocumentFromProject(getIntentProjectName(selectedObject));
				if (intentDocumentFromProject != null) {
					correspondingIntentElements.add(intentDocumentFromProject);
				}
			} else {
				IntentStructuredElement correspondingElement = null;
				EObject correspondingEObject = null;
				if (selectedObject instanceof EObject) {
					correspondingEObject = (EObject)selectedObject;
				}
				if (selectedObject instanceof IAdaptable) {
					correspondingEObject = (EObject)((IAdaptable)selectedObject).getAdapter(EObject.class);
				}
				if (correspondingEObject instanceof IntentStructuredElement) {
					correspondingElement = (IntentStructuredElement)correspondingEObject;
				}
				if (correspondingEObject instanceof IntentIndexEntry
						&& ((IntentIndexEntry)correspondingEObject).getReferencedElement() instanceof IntentStructuredElement) {
					correspondingElement = (IntentStructuredElement)((IntentIndexEntry)correspondingEObject)
							.getReferencedElement();
				}
				if (correspondingElement != null) {
					correspondingIntentElements.add(correspondingElement);
				}
			}
		}
		return correspondingIntentElements;
	}

	/**
	 * Returns the {@link IntentDocument} associated to the given {@link IProject} (if any).
	 * 
	 * @param projectName
	 *            the name of the project to get the {@link IntentDocument} from
	 * @return the {@link IntentDocument} associated to the given {@link IProject} (if any)
	 */
	private static IntentDocument getIntentDocumentFromProject(String projectName) {
		IntentDocument correspondingDocument = null;
		RepositoryAdapter repositoryAdapter = null;
		try {
			Repository repository = IntentRepositoryManager.INSTANCE.getRepository(projectName);
			if (repository != null) {
				repositoryAdapter = repository.createRepositoryAdapter();
				repositoryAdapter.openReadOnlyContext();
				Collection<EObject> contents = repositoryAdapter.getResource(IntentLocations.INTENT_INDEX)
						.getContents();
				if (!contents.isEmpty() && contents.iterator().next() instanceof IntentDocument) {
					correspondingDocument = (IntentDocument)contents.iterator().next();
				}
			}
		} catch (RepositoryConnectionException e) {
			// silently fail, null will be returned
		} catch (CoreException e) {
			// silently fail, null will be returned
		} finally {
			if (repositoryAdapter != null) {
				repositoryAdapter.closeContext();
			}
		}
		return correspondingDocument;
	}

	/**
	 * Returns all the selectec objects corresponding to the given object (i.e. the object itself, or each
	 * element of an {@link IStructuredSelection}...).
	 * 
	 * @param receiver
	 *            the object to get the selected objects from
	 * @return all the selectec objects corresponding to the given object
	 */
	private static Collection<? extends Object> getSelectedObjects(Object receiver) {
		Collection<Object> selectedObjects = Sets.newLinkedHashSet();
		if (receiver instanceof Collection<?>) {
			selectedObjects.addAll((Collection<?>)receiver);
		} else {
			if (receiver instanceof IStructuredSelection) {
				Iterator iterator = ((IStructuredSelection)receiver).iterator();
				while (iterator.hasNext()) {
					selectedObjects.add(iterator);
				}
			} else {
				selectedObjects.add(receiver);
			}
		}
		return selectedObjects;
	}

	/**
	 * Indicates whether the given object is an {@link IProject} with the Intent Nature.
	 * 
	 * @param receiver
	 *            the object to test
	 * @return true if the given object is an {@link IProject} with the Intent Nature, false otherwise
	 */
	public static boolean isIntentProject(Object receiver) {
		for (Class<?> interf : Lists.newArrayList(receiver.getClass().getInterfaces())) {
			if (interf.getName().contains("IProject")) {
				try {
					return (Boolean)receiver.getClass().getMethod("hasNature", String.class)
							.invoke(receiver, "org.eclipse.mylyn.docs.intent.client.ui.ide.intentNature");
					// CHECKSTYLE:OFF
				} catch (Exception e) {
					// CHECKSTYLE:ON
				}

			}
		}
		return false;
	}

	/**
	 * Returns the name of the given project.
	 * 
	 * @param project
	 *            the project
	 * @return the name of the given project
	 */
	private static String getIntentProjectName(Object project) {
		try {
			return (String)project.getClass().getMethod("getName").invoke(project);
			// CHECKSTYLE:OFF
		} catch (Exception e) {
			// CHECKSTYLE:ON
		}
		return null;
	}
}
