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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.compare.match.EditionDistance.CountingDiffEngine;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionBloc;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.markup.markup.Block;
import org.eclipse.mylyn.docs.intent.markup.markup.Paragraph;
import org.eclipse.mylyn.docs.intent.markup.markup.Text;

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
		int res = super.measureDifferences(a, b);
		boolean isSimilar = isSimilar(a, b, false);
		if (isSimilar) {
			res = 0;
		}
		return res;
	}

	/**
	 * Should determine whether an element is similar to the other one or not.
	 * 
	 * @param obj1
	 *            an element
	 * @param obj2
	 *            another element
	 * @param useGenericMatcher
	 *            indicates wheteher generic matcher should be directly called
	 * @return true if those elements have the same identity.
	 */
	private boolean isSimilar(EObject obj1, EObject obj2, boolean useGenericMatcher) {
		boolean isSimilar = false;
		boolean haveSpecificMatcher = false;

		// Step 1 : if we should use specific matcher directly, we call super
		if (useGenericMatcher) {
			return super.measureDifferences(obj1, obj2) == 0;
		}
		// Step 2 : If the two objects are the roots, we consider that they are similar in any circumstance
		if (obj1 instanceof DescriptionBloc && obj2 instanceof DescriptionBloc) {
			isSimilar = areSimilarDescriptionBlocs((DescriptionBloc)obj1, (DescriptionBloc)obj2);
			haveSpecificMatcher = true;
		} else {
			if (obj1 instanceof IntentStructuredElement && obj2 instanceof IntentStructuredElement) {
				isSimilar = areSimilarStructuredElements((IntentStructuredElement)obj1,
						(IntentStructuredElement)obj2);
				haveSpecificMatcher = true;
			} else {
				if (obj1 instanceof Paragraph && obj2 instanceof Paragraph) {
					isSimilar = areSimilarParagraphs((Paragraph)obj1, (Paragraph)obj2);
					haveSpecificMatcher = true;
				}
			}
		}
		if (!haveSpecificMatcher) {
			isSimilar = super.measureDifferences(obj1, obj2) == 0;
		}
		return isSimilar;
	}

	/**
	 * Indicates if the first session matches the second one.
	 * 
	 * @param element1
	 *            the element to match
	 * @param element2
	 *            the candidate element
	 * @return true if the first session matches the second one, false otherwise
	 */
	private boolean areSimilarStructuredElements(IntentStructuredElement element1,
			IntentStructuredElement element2) {
		boolean areSimilarStructuredElements = false;

		// 2 structured element are equal if :
		// they have the same title
		Block title1 = element1.getTitle();
		Block title2 = element2.getTitle();

		if (title1 != null && title2 != null) {
			return isSimilar(title1, title2, false);
		} else {
			// if both title are null, we make a content match
			if (title1 == null && title2 == null) {
				areSimilarStructuredElements = isSimilar(element1, element2, true);
			}
		}
		return areSimilarStructuredElements;
	}

	/**
	 * Indicates if the first description bloc matches the second one.
	 * 
	 * @param obj1
	 *            the description bloc to match
	 * @param obj2
	 *            the candidate description bloc
	 * @return true if the first description bloc matches the second one, false otherwise
	 */
	private boolean areSimilarDescriptionBlocs(DescriptionBloc obj1, DescriptionBloc obj2) {
		// FIXME : first verify that both description blocs are part of the same DU

		// Two description blocs are equals if they have the same position in their container
		int positionInContainer1 = obj1.eContainer().eContents().indexOf(obj1);
		int positionInContainer2 = obj2.eContainer().eContents().indexOf(obj2);

		boolean haveSamePositionInContainer = positionInContainer1 == positionInContainer2;
		return haveSamePositionInContainer;
	}

	/**
	 * Indicates if 2 paragraphs are equals.
	 * 
	 * @param obj1
	 *            the paragraph to match
	 * @param obj2
	 *            the candidate paragraph
	 * @return true if the first pargraph matches the second one, false otherwise
	 */
	private boolean areSimilarParagraphs(Paragraph obj1, Paragraph obj2) {
		LinkedHashSet<Text> obj1Texts = Sets
				.newLinkedHashSet(Iterables.filter(obj1.getContent(), Text.class));
		LinkedHashSet<Text> obj2Texts = Sets
				.newLinkedHashSet(Iterables.filter(obj2.getContent(), Text.class));

		String obj1AsString = "";
		for (Text t : obj1Texts) {
			obj1AsString += t.getData();
		}

		String obj2AsString = "";
		for (Text t : obj2Texts) {
			obj2AsString += t.getData();
		}
		return obj1AsString.equals(obj2AsString);
	}
}
