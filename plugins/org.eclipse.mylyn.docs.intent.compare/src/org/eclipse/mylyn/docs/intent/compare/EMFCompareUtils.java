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
package org.eclipse.mylyn.docs.intent.compare;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.EMFCompareConfiguration;
import org.eclipse.emf.compare.EMFCompareConfiguration.Builder;
import org.eclipse.emf.compare.EMFCompareConfiguration.USE_IDS;

/**
 * Utilities for EMF Compare use.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class EMFCompareUtils {

	/**
	 * Prevents instantiation.
	 */
	private EMFCompareUtils() {
	}

	/**
	 * Returns the differences between the left element and the right element.
	 * 
	 * @param left
	 *            the left element
	 * @param right
	 *            the right element
	 * @return the differences between the left element and the right element
	 */
	public static Comparison compare(Notifier left, Notifier right) {
		Builder builder = EMFCompareConfiguration.builder();
		builder.shouldUseID(USE_IDS.NEVER);
		EMFCompareConfiguration configuration = builder.build();
		return EMFCompare.compare(left, right, configuration);
	}

}
