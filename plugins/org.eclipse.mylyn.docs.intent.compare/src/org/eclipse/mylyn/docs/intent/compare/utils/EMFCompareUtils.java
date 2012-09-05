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

import com.google.common.base.Predicate;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.EMFCompareConfiguration;
import org.eclipse.emf.compare.EMFCompareConfiguration.Builder;
import org.eclipse.emf.compare.EMFCompareConfiguration.USE_IDS;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.diff.IDiffEngine;
import org.eclipse.emf.compare.diff.IDiffProcessor;
import org.eclipse.emf.compare.equi.DefaultEquiEngine;
import org.eclipse.emf.compare.equi.IEquiEngine;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.eobject.ProximityEObjectMatcher;
import org.eclipse.emf.compare.req.DefaultReqEngine;
import org.eclipse.emf.compare.req.IReqEngine;
import org.eclipse.mylyn.docs.intent.compare.debug.CustomizationOptions;
import org.eclipse.mylyn.docs.intent.compare.scope.IntentComparisonScope;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionBloc;
import org.eclipse.mylyn.docs.intent.markup.markup.Annotations;
import org.eclipse.mylyn.docs.intent.markup.markup.Paragraph;
import org.eclipse.mylyn.docs.intent.markup.markup.SimpleContainer;
import org.eclipse.mylyn.docs.intent.markup.markup.Text;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;

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

	/**
	 * Returns the differences between the left element and the right element.
	 * 
	 * @param left
	 *            the left element
	 * @param right
	 *            the right element
	 * @return the differences between the left element and the right element
	 */
	public static Comparison compareDocuments(Notifier left, Notifier right) {
		Builder builder = EMFCompareConfiguration.builder();
		builder.shouldUseID(USE_IDS.NEVER);
		builder.setEqualityHelper(new IntentEqualityHelper());

		EMFCompareConfiguration configuration = builder.build();
		IntentComparisonScope scope = new IntentComparisonScope(left, right);

		// TODO remove debug fork when ready
		if (CustomizationOptions.USE_REDUCED_SCOPE) {
			Predicate<Object> filter = not(or(
					instanceOf(CompilationStatus.class),
					instanceOf(SynchronizerCompilationStatus.class)
					//
					, instanceOf(SimpleContainer.class), instanceOf(Paragraph.class), instanceOf(Text.class),
					instanceOf(DescriptionBloc.class), instanceOf(Annotations.class)
			//
			));
			scope.setEObjectContentFilter(filter);
		} else {
			Predicate<Object> filter = not(or(instanceOf(CompilationStatus.class),
					instanceOf(SynchronizerCompilationStatus.class)));
			scope.setEObjectContentFilter(filter);
		}

		final IMatchEngine matchEngine = new DefaultMatchEngine() {

			protected org.eclipse.emf.compare.match.eobject.IEObjectMatcher createEObjectMatcher() {
				final IEObjectMatcher matchByContent = ProximityEObjectMatcher.builder(
						org.eclipse.mylyn.docs.intent.compare.match.EditionDistance.builder(
								getComparison().getConfiguration().getEqualityHelper()).build()).build();
				return matchByContent;
			};

		};

		Comparison comparison = matchEngine.match(scope, configuration);

		final IDiffProcessor diffBuilder = new DiffBuilder();
		final IDiffEngine diffEngine = new DefaultDiffEngine(diffBuilder);
		diffEngine.diff(comparison);

		final IReqEngine reqEngine = new DefaultReqEngine();
		reqEngine.computeRequirements(comparison);

		final IEquiEngine equiEngine = new DefaultEquiEngine();
		equiEngine.computeEquivalences(comparison);

		return EMFCompare.compare(scope, configuration);
	}
}
