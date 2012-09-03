/*******************************************************************************
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.compare.scope;

import com.google.common.collect.ForwardingIterator;
import com.google.common.collect.Iterators;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * The comparison scope for intent documents. Derived from FilterComparisonScope: resolve content proxies.
 * 
 * @see org.eclipse.emf.compare.scope.FilterComparisonScope
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentComparisonScope extends DefaultComparisonScope {

	/**
	 * Creates the scope from the given parameters.
	 * 
	 * @param left
	 *            the left object
	 * @param right
	 *            the right object
	 */
	public IntentComparisonScope(Notifier left, Notifier right) {
		super(left, right, null);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This default implementation will return all direct and indirect content of the given {@link EObject},
	 * filtering out those {@link EObject}s that do not match {@link #eObjectContentFilter}.
	 * </p>
	 * 
	 * @see org.eclipse.emf.compare.scope.IComparisonScope#getChildren(org.eclipse.emf.ecore.EObject)
	 */
	public Iterator<? extends EObject> getChildren(EObject eObject) {
		if (eObject == null) {
			return Iterators.emptyIterator();
		}
		// original instruction (@see
		// org.eclipse.emf.compare.scope.FilterComparisonScope.getCoveredResources(ResourceSet))
		// final Iterator<EObject> properContent = EcoreUtil.getAllProperContents(eObject, false);

		// <modified>
		final Iterator<EObject> properContent = EcoreUtil.getAllContents(eObject, false);
		// </modified>

		final Iterator<EObject> filter = Iterators.filter(properContent, eObjectContentFilter);
		final Iterator<EObject> uriInitializingIt = new URIInitializingIterator<EObject>(eObject, filter);
		return Iterators.unmodifiableIterator(uriInitializingIt);
	}

	/**
	 * This iterator enables to add in the iteration the initialization of the namespace and resource uris
	 * set.
	 * 
	 * @author <a href="mailto:cedric.notot@obeo.fr">Cedric Notot</a>
	 * @param <T>
	 *            The kind of object to iterate on.
	 */
	private class URIInitializingIterator<T> extends ForwardingIterator<T> {

		/** The origin iterator. */
		private Iterator<T> delegate;

		/**
		 * Constructor.
		 * 
		 * @param delegate
		 *            The origin iterator.
		 */
		public URIInitializingIterator(Iterator<T> delegate) {
			this.delegate = delegate;
		}

		/**
		 * Constructor.
		 * 
		 * @param resource
		 *            The resource containing the elements to iterate on.
		 * @param delegate
		 *            The origin iterator.
		 */
		public URIInitializingIterator(Resource resource, Iterator<T> delegate) {
			this.delegate = delegate;
			addUri(resource);
		}

		/**
		 * Constructor.
		 * 
		 * @param eObject
		 *            The EObject containing the elements to iterate on.
		 * @param delegate
		 *            The origin iterator.
		 */
		public URIInitializingIterator(EObject eObject, Iterator<T> delegate) {
			this.delegate = delegate;
			addUri(eObject);
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see com.google.common.collect.ForwardingIterator#delegate()
		 */
		@Override
		protected Iterator<T> delegate() {
			return delegate;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see com.google.common.collect.ForwardingIterator#next()
		 */
		@Override
		public T next() {
			T obj = super.next();
			if (obj instanceof EObject) {
				addUri((EObject)obj);
			} else if (obj instanceof Resource) {
				addUri((Resource)obj);
			}
			return obj;
		}

		/**
		 * It registers the namespace and resource URI from the given <code>eObject</code>.
		 * 
		 * @param eObject
		 *            The given <code>eObject</code>.
		 */
		private void addUri(EObject eObject) {
			if (eObject.eResource() != null) {
				getResourceURIs().add(eObject.eResource().getURI().toString());
			}
			getNsURIs().add(eObject.eClass().getEPackage().getNsURI());
		}

		/**
		 * It registers the resource URI from the given <code>resource</code>.
		 * 
		 * @param resource
		 *            The given <code>resource</code>.
		 */
		private void addUri(Resource resource) {
			getResourceURIs().add(resource.getURI().toString());
		}
	}
}
