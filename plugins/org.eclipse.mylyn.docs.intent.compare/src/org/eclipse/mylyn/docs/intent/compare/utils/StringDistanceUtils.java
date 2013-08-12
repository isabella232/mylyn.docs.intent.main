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

import org.eclipse.emf.compare.utils.DiffUtil;

/**
 * String distance computation utilities.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class StringDistanceUtils {

	/**
	 * Maximum distance between 2 strings.
	 */
	private static final double DEFAULT_MAX_STRING_DISTANCE = 500;

	/**
	 * Impact of a size difference.
	 */
	private static final double DEFAULT_STRING_SIZE_DISTANCE_IMPACT = 0.7;

	/**
	 * Sorensen-Dice coefficient.
	 */
	private static final double DEFAULT_STRING_DICE_DISTANCE_IMPACT = 0.3;

	/**
	 * Prevents instantiation.
	 */
	private StringDistanceUtils() {
	}

	/**
	 * Returns the distance between two strings.
	 * 
	 * @param a
	 *            the first string
	 * @param b
	 *            the second string
	 * @return the distance between two strings
	 */
	public static Double getStringDistance(String a, String b) {
		return getStringDistance(a, b, DEFAULT_STRING_DICE_DISTANCE_IMPACT,
				DEFAULT_STRING_SIZE_DISTANCE_IMPACT);
	}

	/**
	 * Returns the distance between two strings. Note: diceDistanceImpact + sizeDistanceImpact = 1
	 * 
	 * @param a
	 *            the first string
	 * @param b
	 *            the second string
	 * @param diceDistanceImpact
	 *            the impact of the diceCoefficient
	 * @param sizeDistanceImpact
	 *            the impact of the size distance
	 * @return the distance between two strings
	 */
	public static Double getStringDistance(String a, String b, double diceDistanceImpact,
			double sizeDistanceImpact) {
		if (diceDistanceImpact + sizeDistanceImpact != 1) {
			throw new AssertionError("dice and size impacts sum must be equal to 1, currently "
					+ diceDistanceImpact + sizeDistanceImpact);
		}
		Double res = DEFAULT_MAX_STRING_DISTANCE;
		if (a != null && b != null) {
			double sizeCoeff = 1 - (2d * Math.abs(a.length() - b.length())) / (a.length() + b.length());
			double diceCoefficient = DiffUtil.diceCoefficient(a, b);
			double average = diceCoefficient * diceDistanceImpact + sizeCoeff * sizeDistanceImpact;
			res = (double)((1 - average) * DEFAULT_MAX_STRING_DISTANCE);
		} else if (a == null && b == null) {
			res = Double.valueOf(0);
		}
		return res;
	}
}
