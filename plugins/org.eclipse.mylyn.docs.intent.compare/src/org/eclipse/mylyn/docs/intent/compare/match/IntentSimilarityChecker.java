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
package org.eclipse.mylyn.docs.intent.compare.match;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.match.statistic.MetamodelFilter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionBloc;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.document.IntentSubSectionContainer;
import org.eclipse.mylyn.docs.intent.markup.markup.Block;
import org.eclipse.mylyn.docs.intent.markup.markup.Paragraph;
import org.eclipse.mylyn.docs.intent.markup.markup.Text;

/**
 * Similarity checker using the Intent semantics to compare two Intent elements.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentSimilarityChecker extends StatisticBasedSimilarityChecker {

	/**
	 * Value taht represents a very strong similarity.
	 */
	private static final double MAX_SIMILARITY = 0.9999;

	private EObject localRoot;

	private EObject repositoryRoot;

	/**
	 * IntentSimilarityChecker constructor.
	 * 
	 * @param localRoot
	 *            the root of the local AST of a Intent Element.
	 * @param repositoryRoot
	 *            the root of the repository AST for a Intent Element
	 * @param metamodelFilter
	 *            a metamodel filter the checker can use to know whether a feature alwaas has the same value
	 *            or not in the models.
	 * @param bridge
	 *            utility class to keep API compatibility.
	 */
	public IntentSimilarityChecker(MetamodelFilter metamodelFilter,
			AbstractGenericMatchEngineToCheckerBridge bridge, EObject localRoot, EObject repositoryRoot) {
		super(metamodelFilter, bridge);
		this.localRoot = localRoot;
		this.repositoryRoot = repositoryRoot;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.compare.match.StatisticBasedSimilarityChecker#isSimilar(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public boolean isSimilar(EObject obj1, EObject obj2) throws FactoryException {
		boolean isSimilar = isSimilar(obj1, obj2, false);
		return isSimilar;
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
	 * @throws FactoryException
	 *             on error accessing features.
	 */
	public boolean isSimilar(EObject obj1, EObject obj2, boolean useGenericMatcher) throws FactoryException {
		boolean isSimilar = false;
		boolean haveSpecificMatcher = false;

		// Step 1 : if we should use specific matcher directly, we call super
		if (useGenericMatcher) {
			return super.isSimilar(obj1, obj2);
		}
		// Step 2 : If the two objects are the roots, we consider that they are similar in any circumstance
		if (areRoots(obj1, obj2)) {
			isSimilar = true;
			haveSpecificMatcher = true;
		} else {
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
		}
		if (!haveSpecificMatcher) {
			isSimilar = super.isSimilar(obj1, obj2);
		}
		return isSimilar;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.engine.AbstractSimilarityChecker#fastLookup(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public EObject fastLookup(EObject obj1) {
		if (obj1 == localRoot) {
			return repositoryRoot;
		}
		return super.fastLookup(obj1);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.compare.match.StatisticBasedSimilarityChecker#absoluteMetric(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public double absoluteMetric(EObject obj1, EObject obj2) throws FactoryException {
		// If the two objects are the roots, we consider that they are similar in any circumstance
		if (areRoots(obj1, obj2)) {
			return MAX_SIMILARITY;
		} else {
			if (obj1 instanceof IntentStructuredElement && obj2 instanceof IntentStructuredElement) {
				if (areSimilarStructuredElements((IntentStructuredElement)obj1, (IntentStructuredElement)obj2)) {
					return 1.0;
				} else {
					return 0;
				}
			}
		}
		return super.absoluteMetric(obj1, obj2);
	}

	/**
	 * Indicates if the two given objects represent the roots of the compared element.
	 * 
	 * @param obj1
	 *            the first element to determine if it's a root
	 * @param obj2
	 *            the second element to determine if it's a root
	 * @return true if the two given objects represent the roots of the compared element, false otherwise
	 */
	private boolean areRoots(EObject obj1, EObject obj2) {
		return (obj1 == localRoot) && (obj2 == repositoryRoot);
	}

	/**
	 * Indicates if the first session matches the second one.
	 * 
	 * @param element1
	 *            the element to match
	 * @param element2
	 *            the candidate element
	 * @return true if the first session matches the second one, false otherwise
	 * @throws FactoryException
	 *             - on error accessing features.
	 */
	protected boolean areSimilarStructuredElements(IntentStructuredElement element1,
			IntentStructuredElement element2) throws FactoryException {
		boolean areSimilarStructuredElements = false;

		// 2 structured element are equal if :
		// they have the same title
		Block title1 = element1.getTitle();
		Block title2 = element2.getTitle();

		if (title1 != null && title2 != null) {
			return isSimilar(title1, title2);
		} else {
			// if both title are null, we make a content match
			if (title1 == null && title2 == null) {
				areSimilarStructuredElements = isSimilar(element1, element2, true);
			}
		}
		return areSimilarStructuredElements;
	}

	@Override
	protected double contentSimilarity(EObject obj1, EObject obj2) throws FactoryException {
		if (obj1 instanceof IntentSubSectionContainer && obj2 instanceof IntentSubSectionContainer) {
			return 0.97;
		}
		return super.contentSimilarity(obj1, obj2);
	}

	@Override
	protected double relationsSimilarity(EObject obj1, EObject obj2) throws FactoryException {
		if (obj1 instanceof IntentSubSectionContainer && obj2 instanceof IntentSubSectionContainer) {
			final Double value = getSimilarityFromCache(obj1, obj2, 'r');
			double similarity = 0d;
			if (value != null) {
				similarity = value;
			} else {
				similarity = getIntentStructuredElementsSimilarity((IntentSubSectionContainer)obj1,
						(IntentSubSectionContainer)obj2);
				setSimilarityInCache(obj1, obj2, 'r', similarity);
			}
			return similarity;
		}
		return super.relationsSimilarity(obj1, obj2);
	}

	private double getIntentStructuredElementsSimilarity(IntentSubSectionContainer obj1,
			IntentSubSectionContainer obj2) throws FactoryException {
		double numberOfTotalElements = obj1.getIntentContent().size();
		double numberOfCommonElements = 0;
		Collection<EObject> obj1Elements = Lists.newArrayList(obj1.getIntentContent());

		Collection<EObject> obj2Elements = Lists.newArrayList(obj2.getIntentContent());

		for (EObject obj2Element : obj2Elements) {

			Iterator<EObject> obj1ElementsIterator = obj1Elements.iterator();
			while (obj1ElementsIterator.hasNext()) {
				EObject obj1Element = obj1ElementsIterator.next();

				if (isSimilar(obj1Element, obj2Element)) {
					numberOfCommonElements++;
					obj1ElementsIterator.remove();
					break;
				}
			}
		}
		if (obj2.getIntentContent().size() > obj1.getIntentContent().size()) {
			numberOfTotalElements += obj2.getIntentContent().size() - obj1.getIntentContent().size();
		}

		if (numberOfTotalElements > 0) {
			double similarity = numberOfCommonElements / numberOfTotalElements;
			if (similarity >= 0.5 && numberOfTotalElements <= 4) {
				similarity = Math.max(similarity, 0.99);
			} else {
				if (similarity >= 0.5) {
					similarity = 0.5 + (numberOfCommonElements / (numberOfTotalElements * 2));
				}
			}
			return similarity;
		}
		return 1.0;
	}

	// private double getIntentStructuredElementsSimilarity(IntentSubSectionContainer obj1,
	// IntentSubSectionContainer obj2) {
	// double numberOfTotalElements = obj1.getIntentContent().size();
	// double numberOfCommonElements = 0;
	// double numberOfAlmostCommonElements = 0;
	// Collection<String> obj1Elements = Sets.newLinkedHashSet();
	//
	// // Serialize obj1 children
	// for (EObject obj1Child : obj1.getIntentContent()) {
	// obj1Elements.add(new IntentSerializer().serialize(obj1Child));
	// }
	//
	// // Compare with obj2 children
	// Iterator<EObject> obj2Children = Lists.newArrayList(obj2.getIntentContent()).iterator();
	// while (obj2Children.hasNext()) {
	// String serializeChild = new IntentSerializer().serialize(obj2Children.next());
	// if (obj1Elements.remove(serializeChild)) {
	// numberOfCommonElements++;
	// obj2Children.remove();
	// }
	// }
	//
	// // If some elements have not been perfectly matched,
	// // we try partial match
	// obj2Children = Lists.newArrayList(obj2.getIntentContent()).iterator();
	// while (obj2Children.hasNext()) {
	// EObject next = obj2Children.next();
	// Iterator<String> obj1ElementsIterator = obj1Elements.iterator();
	// while (obj1ElementsIterator.hasNext()) {
	// String obj1Element = obj1ElementsIterator.next();
	//
	// double nameSimilarityMetric = NameSimilarity.nameSimilarityMetric(obj1Element,
	// new IntentSerializer().serialize(next));
	// if (nameSimilarityMetric > 0.95) {
	// numberOfAlmostCommonElements++;
	// obj1ElementsIterator.remove();
	// }
	// }
	// }
	// if (obj2.getIntentContent().size() > obj1.getIntentContent().size()) {
	// numberOfTotalElements += obj2.getIntentContent().size() - obj1.getIntentContent().size();
	// }
	//
	// if (numberOfTotalElements > 0) {
	// double similarity = numberOfCommonElements / numberOfTotalElements + numberOfAlmostCommonElements
	// * 0.75 / numberOfTotalElements;
	// if (similarity >= 0.5 && numberOfTotalElements <= 4) {
	// similarity = Math.max(similarity, 0.99);
	// } else {
	// if (similarity >= 0.5) {
	// similarity = 0.5 + (numberOfCommonElements / (numberOfTotalElements * 2));
	// }
	// }
	// return similarity;
	// }
	// return 1.0;
	// }

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
