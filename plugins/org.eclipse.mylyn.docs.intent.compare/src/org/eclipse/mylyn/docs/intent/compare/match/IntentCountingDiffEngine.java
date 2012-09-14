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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.compare.match.EditionDistance.CountingDiffEngine;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionBloc;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentChapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
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
		Integer distance = null;

		// TODO refactor to only compute possible distances
		// TODO refactor to limit serializations

		Integer titleDistance = null;
		if (a instanceof IntentChapter || a instanceof IntentSection) {
			titleDistance = getTitleDistance((IntentStructuredElement)a, (IntentStructuredElement)b);
		}
		Integer serializationDistance = getSerializationDistance(a, b);
		Integer uriDistance = getURIDistance(a, b);

		if (titleDistance != null && serializationDistance != null) {
			distance = (int)(titleDistance * 0.7 + serializationDistance * 0.2 + uriDistance * 0.1);
		} else if (serializationDistance != null && uriDistance != null) {
			distance = (int)(serializationDistance * 0.7 + uriDistance * 0.3);
		} else {
			distance = uriDistance;
		}
		return distance;
	}

	/**
	 * Returns the distance between document elements by comparing their titles.
	 * 
	 * @param a
	 *            the first element
	 * @param b
	 *            the second element
	 * @return the distance between two strings
	 */
	private Integer getTitleDistance(IntentStructuredElement a, IntentStructuredElement b) {
		Integer distance = null;
		String titleA = a.getFormattedTitle();
		String titleB = b.getFormattedTitle();
		if (titleA != null && titleB != null) {
			distance = StringDistanceUtils.getStringDistance(titleA, titleB);
		}
		return distance;
	}

	/**
	 * Returns the distance between document elements by comparing their uris.
	 * 
	 * @param a
	 *            the first element
	 * @param b
	 *            the second element
	 * @return the distance between two strings
	 */
	private Integer getURIDistance(EObject a, EObject b) {
		Integer distance = null;
		String fragmentA = helper.getURI(a).fragment();
		String fragmentB = helper.getURI(b).fragment();
		if (fragmentA != null && fragmentB != null) {
			distance = StringDistanceUtils.getStringDistance(fragmentA, fragmentB);
		}
		return distance;
	}

	/**
	 * Returns the distance between document elements by comparing their serialization.
	 * 
	 * @param a
	 *            the first element
	 * @param b
	 *            the second element
	 * @return the distance between two strings
	 */
	private Integer getSerializationDistance(EObject a, EObject b) {
		Integer distance = null;
		String serializedA = serialize(a);
		String serializedB = serialize(b);
		if (serializedA != null && serializedB != null) {
			distance = StringDistanceUtils.getStringDistance(serializedA, serializedB);
		}
		return distance;
	}

	/**
	 * Serializes the given element.
	 * 
	 * @param root
	 *            the element to serialize
	 * @return the serialized version
	 */
	private static String serialize(EObject root) {
		String res = null;
		if (root.eClass().getEPackage().equals(MarkupPackage.eINSTANCE)) {
			res = new WikiTextSerializer().serialize(root);
		} else if (root instanceof ModelingUnit || root instanceof DescriptionUnit
				|| root instanceof IntentStructuredElement) {
			res = new IntentSerializer().serialize(root);
		} else if (root instanceof DescriptionBloc) {
			DescriptionBloc bloc = (DescriptionBloc)root;
			res = serialize(bloc.getDescriptionBloc());
		}
		return res;
	}
}
