/*******************************************************************************
 * Copyright (c) 2010-2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.collab.ide.adapters;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * A command in charge of deleting all dangling references.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class RemoveDanglingReferences extends RecordingCommand {

	/**
	 * The element that should be cleaned.
	 */
	private final EObject root;

	/**
	 * Create a new {@link RemoveDanglingReferences} command.
	 * 
	 * @param domain
	 *            the editing domain.
	 * @param root
	 *            the root element that should be cleaned
	 */
	public RemoveDanglingReferences(final TransactionalEditingDomain domain, final EObject root) {
		super(domain);
		this.root = root;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 */
	@Override
	protected void doExecute() {
		removeDanglingReferences(root);
	}

	/**
	 * Remove all dangling references of all objects that are contained by the root element.
	 * 
	 * @param root
	 *            the root element
	 */
	public void removeDanglingReferences(final EObject root) {
		if (root.eResource() != null && root.eResource().getResourceSet() != null) {
			removeDanglingReferences(new DanglingReferencesDetector(root.eResource()));
		}
	}

	/**
	 * Removes all dangling references from all the elements in the given resourceSet.
	 * 
	 * @param resourceSet
	 *            The resourceSet which is to be cleaned of dangling references.
	 */
	public void removeDanglingReferences(final ResourceSet resourceSet) {
		removeDanglingReferences(new DanglingReferencesDetector(resourceSet));
	}

	private void removeDanglingReferences(CrossReferencer referencer) {
		for (Map.Entry<EObject, Collection<Setting>> entry : referencer.entrySet()) {
			for (EStructuralFeature.Setting value : entry.getValue()) {
				try {
					EcoreUtil.remove(value, entry.getKey());
				} catch (final UnsupportedOperationException e) {
					// we know some time the setting is unsettable, just ignore
					// that cases
				} catch (final NullPointerException e) {
					// we don't want to clean unresolved proxies in the model,
					// so let's ignore Exceptions coming from that.
				}
			}
		}
	}

	/**
	 * Specific {@link CrossReferencer} to detect dangling references.
	 */
	private static class DanglingReferencesDetector extends EcoreUtil.CrossReferencer {

		private static final long serialVersionUID = 616050158241084372L;

		/**
		 * Creates an instance for the given resource.
		 * 
		 * @param resource
		 *            the resource to cross reference.
		 */
		public DanglingReferencesDetector(Resource eResource) {
			super(eResource);
			crossReference();
			done();
		}

		/**
		 * Creates an instance for the given resource set.
		 * 
		 * @param resourceSet
		 *            the resource set to cross reference.
		 */
		public DanglingReferencesDetector(ResourceSet resourceSet) {
			super(resourceSet);
			crossReference();
			done();
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#crossReference(org.eclipse.emf.ecore.EObject,
		 *      org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
		 */
		@Override
		protected boolean crossReference(final EObject eObject, final EReference eReference,
				final EObject crossReferencedEObject) {
			// A reference is dangling if the referenced object is not attached to a resource
			return crossReferencedEObject.eResource() == null;
		}
	}
}
