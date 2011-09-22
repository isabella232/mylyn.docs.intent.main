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
package org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.mylyn.docs.intent.client.synchronizer.SynchronizerRepositoryClient;

/**
 * An Extension to the default behavior of the Synchronizer. A SynchronizerExtension is :
 * <ul>
 * <li>a supported scheme (for example, "retro" will indicates that this extension is handling all URIs
 * starting with "retro:/..."</li>
 * <li>a mechanism that allows to detect any change made on the concrete artifacts described by the given URI
 * and notify the Synchronizer of these changes</li>
 * </ul>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public interface ISynchronizerExtension {

	/**
	 * Adds the elements located at the given URIs to the listened elements : this extension is now in charge
	 * of listening to any change made on the corresponding concrete artifacts.
	 * 
	 * @param synchronizer
	 *            a synchronizer that should be notified if the elements located at the given URIs change
	 * @param listenedElementsURIs
	 *            the URIs of the listened elements
	 */
	void addListenedElements(SynchronizerRepositoryClient synchronizer, Set<URI> listenedElementsURIs);

	/**
	 * Removes the elements located at the given URIs from the listened elements.
	 * 
	 * @param synchronizer
	 *            the synchronizer that does not wish to be notified of changes on the given URIs any more
	 * @param listenedElementsURIs
	 *            the URIs of the elements to stop listening
	 */
	void removeListenedElements(SynchronizerRepositoryClient synchronizer, Set<URI> listenedElementsURIs);
}
