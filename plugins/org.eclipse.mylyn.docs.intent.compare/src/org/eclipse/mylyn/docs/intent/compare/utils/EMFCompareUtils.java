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
import org.eclipse.emf.compare.match.IMatchEngine.Factory.Registry;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.eobject.ProximityEObjectMatcher;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.IComparisonScope;
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
	 * A {@link Registry} always returning a Match engine ignoring identifiers.
	 */
	private static Registry neverUsingIdentifiersMatchEngine;

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
		builder.setMatchEngineFactoryRegistry(getMatchEngineNeverUsingIdentifiers());
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
		// Step 1: create comparison scope
		IntentComparisonScope scope = new IntentComparisonScope(left, right);
		scope.setEObjectContentFilter(Predicates.not(Predicates.or(
				Predicates.instanceOf(CompilationStatus.class),
				Predicates.instanceOf(SynchronizerCompilationStatus.class))));

		// Step 2: initialize match & diff engine
		Registry matchEngine = getIntentDocumentMatchEngine(left, right);
		DefaultDiffEngine diffEngine = new DefaultDiffEngine(new DiffBuilder()) {
			@Override
			protected FeatureFilter createFeatureFilter() {
				return new IntentFeatureFilter();
			}
		};

		// Step 3: launch comparison
		Builder builder = EMFCompare.builder();
		builder.setDiffEngine(diffEngine);
		builder.setMatchEngineFactoryRegistry(matchEngine);
		return builder.build().compare(scope);
	}

	/**
	 * Returns a {@link Registry} always returning a Match engine ignoring identifiers.
	 * 
	 * @return a {@link Registry} always returning a Match engine ignoring identifiers
	 */
	private static Registry getMatchEngineNeverUsingIdentifiers() {
		if (neverUsingIdentifiersMatchEngine == null) {
			IMatchEngine matchEngine = DefaultMatchEngine.create(UseIdentifiers.NEVER);
			neverUsingIdentifiersMatchEngine = createMatchEngineRegistryFromMatchEngine(matchEngine);
		}
		return neverUsingIdentifiersMatchEngine;
	}

	/**
	 * Returns a {@link Registry} always returning a Match engine allowing to compare 2 intent documents with
	 * the given left and right roots.
	 * 
	 * @param left
	 *            the left root of the Intent element (parsed from current editor)
	 * @param right
	 *            the right root of the Intent element (from Intent repository)
	 * @return a {@link Registry} always returning a Match engine allowing to compare 2 intent documents with
	 *         the given left and right roots
	 */
	private static Registry getIntentDocumentMatchEngine(Notifier left, Notifier right) {
		IEObjectMatcher matcher = new ProximityEObjectMatcher(EditionDistance.builder(left, right).build());
		final IComparisonFactory comparisonFactory = new DefaultComparisonFactory(
				new DefaultEqualityHelperFactory());
		IMatchEngine matchEngine = new DefaultMatchEngine(matcher, comparisonFactory);
		return createMatchEngineRegistryFromMatchEngine(matchEngine);
	}

	/**
	 * Returns a {@link Registry} always returning the given {@link IMatchEngine}.
	 * 
	 * @param matchEngine
	 *            the {@link IMatchEngine} to return
	 * @return a {@link Registry} always returning the given {@link IMatchEngine}
	 */
	private static Registry createMatchEngineRegistryFromMatchEngine(final IMatchEngine matchEngineToUse) {
		Registry matchEngineFactoryRegistry = MatchEngineFactoryRegistryImpl.createStandaloneInstance();
		MatchEngineFactoryImpl matchEngineFactory = new MatchEngineFactoryImpl() {

			@Override
			public int getRanking() {
				return 99;
			}

			@Override
			public IMatchEngine getMatchEngine() {
				return matchEngineToUse;
			}

			@Override
			public boolean isMatchEngineFactoryFor(IComparisonScope scope) {
				return true;
			}
		};
		matchEngineFactoryRegistry.add(matchEngineFactory);
		return matchEngineFactoryRegistry;
	}
}
