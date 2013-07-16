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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;

/**
 * Location distance computation utilities.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class LocationDistanceUtils {

	/**
	 * Prevents instantiation.
	 */
	private LocationDistanceUtils() {
	}

	/**
	 * Returns the level of the given object inside of the model tree.
	 * 
	 * @param object
	 *            the object
	 * @return the level of the given object inside of the model tree
	 */
	public static String computeLevel(EObject object) {
		String level = null;
		if (object instanceof IntentStructuredElement) {
			level = ((IntentStructuredElement)object).getCompleteLevel();
		} else {
			EObject container = object.eContainer();
			if (container != null) {
				int index = container.eContents().indexOf(object);
				String containerLevel = computeLevel(container);
				if (containerLevel != null) {
					level = containerLevel + ".";
				} else {
					level = "";
				}
				level += index;
			}
		}
		return level;
	}
}
