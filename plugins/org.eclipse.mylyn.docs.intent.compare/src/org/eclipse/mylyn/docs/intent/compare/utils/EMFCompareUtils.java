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

import com.google.common.base.Predicates;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.EMFCompare.Builder;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.eobject.ProximityEObjectMatcher;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.mylyn.docs.intent.compare.match.EditionDistance;
import org.eclipse.mylyn.docs.intent.compare.scope.IntentComparisonScope;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;

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
	 * Returns the differences between the left element and the right element. Comparison customization:
	 * <ul>
	 * <li>Ignore XMI Ids</li>
	 * <li>Resolve proxies</li>
	 * </ul>
	 * 
	 * @param left
	 *            the left element
	 * @param right
	 *            the right element
	 * @return the differences between the left element and the right element
	 */
	public static Comparison compare(Notifier left, Notifier right) {
		IntentComparisonScope scope = new IntentComparisonScope(left, right);
		Builder builder = EMFCompare.builder();
		builder.setMatchEngine(DefaultMatchEngine.create(UseIdentifiers.NEVER));
		return builder.build().compare(scope);
	}

	/**
	 * Returns the differences between the left element and the right element. Comparison customization:
	 * <ul>
	 * <li>Ignore XMI Ids</li>
	 * <li>Resolve proxies</li>
	 * <li>Intent specific match engine</li>
	 * </ul>
	 * 
	 * @param left
	 *            the left element
	 * @param right
	 *            the right element
	 * @return the differences between the left element and the right element
	 */
	public static Comparison compareDocuments(Notifier left, Notifier right) {
		IntentComparisonScope scope = new IntentComparisonScope(left, right);
		scope.setEObjectContentFilter(Predicates.not(Predicates.or(
				Predicates.instanceOf(CompilationStatus.class),
				Predicates.instanceOf(SynchronizerCompilationStatus.class))));

		IEObjectMatcher matcher = new ProximityEObjectMatcher(new EditionDistance(left, right));
		final IComparisonFactory comparisonFactory = new DefaultComparisonFactory(
				new DefaultEqualityHelperFactory());
		IMatchEngine matchEngine = new DefaultMatchEngine(matcher, comparisonFactory);

		Builder builder = EMFCompare.builder();
		builder.setDiffEngine(new DefaultDiffEngine(new DiffBuilder()) {
			@Override
			protected FeatureFilter createFeatureFilter() {
				return new IntentFeatureFilter();
			}
		});
		builder.setMatchEngine(matchEngine);
		return builder.build().compare(scope);
	}
}
