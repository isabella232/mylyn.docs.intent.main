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
package org.eclipse.mylyn.docs.intent.collab.handlers.impl.notification;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification;

/**
 * Representation of a notification (use the EMF Compare Comparison).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class RepositoryChangeNotificationImpl implements RepositoryChangeNotification {

	/**
	 * The elements concerned by this {@link RepositoryChangeNotification}.
	 */
	private List<EObject> impactedElements;

	/**
	 * RepositoryChangeNotificationImpl default constructor.
	 */
	public RepositoryChangeNotificationImpl() {
		super();
		impactedElements = new ArrayList<EObject>();
	}

	/**
	 * RepositoryChangeNotificationImpl constructor.
	 * 
	 * @param comparison
	 *            the Comparison to copy.
	 */
	public RepositoryChangeNotificationImpl(Comparison comparison) {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#toString()
	 */
	@Override
	public String toString() {
		String toString = getImpactedElements().toString();
		return toString;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification#getImpactedElements()
	 */
	public List<EObject> getImpactedElements() {
		return impactedElements;
	}
}
