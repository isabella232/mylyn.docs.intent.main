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

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.LinkedHashSet;

import org.eclipse.emf.compare.match.eobject.PairCharDistance;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.compare.debug.DebugUtils;
import org.eclipse.mylyn.docs.intent.compare.match.EditionDistance.CountingDiffEngine;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.markup.markup.Paragraph;
import org.eclipse.mylyn.docs.intent.markup.markup.Text;
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
		// TODO remove debug instructions when ready
		int distance;
		if (a instanceof ModelingUnit || a instanceof DescriptionUnit || a instanceof IntentStructuredElement) {
			String serializedA = new IntentSerializer().serialize(a);
			String serializedB = new IntentSerializer().serialize(b);
			distance = new PairCharDistance().distance(serializedA, serializedB);
		} else if (a instanceof Text) {
			Text textA = (Text)a;
			Text textB = (Text)b;
			distance = new PairCharDistance().distance(textA.getData(), textB.getData());
		} else if (a instanceof Paragraph) {
			LinkedHashSet<Text> obj1Texts = Sets.newLinkedHashSet(Iterables.filter(
					((Paragraph)a).getContent(), Text.class));
			LinkedHashSet<Text> obj2Texts = Sets.newLinkedHashSet(Iterables.filter(
					((Paragraph)b).getContent(), Text.class));
			String obj1AsString = "";
			for (Text t : obj1Texts) {
				obj1AsString += t.getData();
			}

			String obj2AsString = "";
			for (Text t : obj2Texts) {
				obj2AsString += t.getData();
			}
			distance = new PairCharDistance().distance(obj1AsString, obj2AsString);
		} else {
			System.err.println("DEFAULT for " + a.eClass().getName());
			distance = super.measureDifferences(a, b);
		}

		// DEBUG
		String aString = DebugUtils.elementToReadableString(a);
		String bString = DebugUtils.elementToReadableString(b);
		if (aString != null && bString != null) {
			System.err.println(distance + "\t" + aString + " <=> " + bString);
		}
		return distance;
	}

}
