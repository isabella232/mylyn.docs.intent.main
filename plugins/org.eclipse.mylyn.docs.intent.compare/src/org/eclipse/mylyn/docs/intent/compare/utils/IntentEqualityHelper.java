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
package org.eclipse.mylyn.docs.intent.compare.utils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.ecore.EObject;

/**
 * An {@link EqualityHelper} redefinition used to define accurate URIs.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentEqualityHelper extends EqualityHelper {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.utils.EqualityHelper#getURI(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public URI getURI(EObject object) {
		URI uri = super.getURI(object);
		String level = computeLevel(object);
		if (level != null) {
			uri = uri.trimFragment().appendFragment(level);
		}
		return uri;
	}

	/**
	 * Returns the level of the given object inside of the model tree.
	 * 
	 * @param object
	 *            the object
	 * @return the level of the given object inside of the model tree
	 */
	private static String computeLevel(EObject object) {
		String level = null;
		// TODO FIXME we should use the completeLevel attribute when possible in order to avoid the loading of
		// all the document
		EObject container = object.eContainer();
		if (container != null) {
			int index = container.eContents().indexOf(object);
			String containerLevel = computeLevel(container);
			if (containerLevel != null) {
				level = containerLevel + "_";
			} else {
				level = "";
			}
			level += index;
		}
		return level;
	}

}
