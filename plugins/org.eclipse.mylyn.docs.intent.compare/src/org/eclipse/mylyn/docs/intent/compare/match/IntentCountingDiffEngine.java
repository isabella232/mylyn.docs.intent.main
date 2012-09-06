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
package org.eclipse.mylyn.docs.intent.compare.match;

import org.eclipse.emf.compare.utils.DiffUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.compare.debug.DebugUtils;
import org.eclipse.mylyn.docs.intent.compare.match.EditionDistance.CountingDiffEngine;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;
import org.eclipse.mylyn.docs.intent.markup.serializer.WikiTextSerializer;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;

/**
 * An implementation of a diff engine which count and measure the detected changes into Intent documents.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentCountingDiffEngine extends CountingDiffEngine {

	/**
	 * We set a default distance here because if maxDistance is 0 results are invalid.
	 */
	private static final int DEFAULT_DISTANCE = 500;

	/**
	 * Constructor.
	 * 
	 * @param editionDistance
	 *            for instanciation
	 * @param maxDistance
	 *            the max distance
	 */
	public IntentCountingDiffEngine(
			org.eclipse.mylyn.docs.intent.compare.match.EditionDistance editionDistance, int maxDistance) {
		editionDistance.super(maxDistance);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.compare.match.EditionDistance.CountingDiffEngine#measureDifferences(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public int measureDifferences(EObject a, EObject b) {
		// TODO remove debug instructions when ready
		int distance;
		if (a instanceof ModelingUnit || a instanceof DescriptionUnit || a instanceof IntentStructuredElement) {
			String serializedA = new IntentSerializer().serialize(a);
			String serializedB = new IntentSerializer().serialize(b);
			distance = getStringDistance(serializedA, serializedB);
		} else if (a.eClass().getEPackage().equals(MarkupPackage.eINSTANCE)) {
			String serializedA = new WikiTextSerializer().serialize(a);
			String serializedB = new WikiTextSerializer().serialize(b);
			distance = getStringDistance(serializedA, serializedB);
		} else {
			System.out.println("DEFAULT for " + a.eClass().getName());
			distance = super.measureDifferences(a, b);
		}

		// DEBUG
		String aString = DebugUtils.elementToReadableString(a);
		String bString = DebugUtils.elementToReadableString(b);
		if (aString != null && bString != null) {
			System.out.println(distance + "\t" + aString + " <=> " + bString);
		}
		return distance;
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
	private int getStringDistance(String a, String b) {
		int res = DEFAULT_DISTANCE;
		if (a != null && b != null) {
			return (int)((1 - DiffUtil.diceCoefficient(a, b)) * DEFAULT_DISTANCE);
		}
		return res;
	}

}
