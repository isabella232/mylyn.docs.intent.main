/*******************************************************************************
 * Copyright (c) 2010, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.compare;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.ReferenceChange;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.mylyn.docs.intent.compare.debug.CustomizationOptions;
import org.eclipse.mylyn.docs.intent.compare.debug.DebugUtils;
import org.eclipse.mylyn.docs.intent.compare.utils.EMFCompareUtils;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;

/**
 * Merges local and repository asts using EMF Compare.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentASTMerger {
	private static List<EStructuralFeature> featuresToIgnore;

	private static final boolean OVERRIDE = false;

	/**
	 * Constructor.
	 */
	public IntentASTMerger() {
		featuresToIgnore = new ArrayList<EStructuralFeature>();
		featuresToIgnore.add(IntentDocumentPackage.eINSTANCE
				.getIntentSectionOrParagraphReference_ReferencedObject());
		featuresToIgnore.add(IntentDocumentPackage.eINSTANCE.getIntentSectionReference_ReferencedElement());
		featuresToIgnore.add(ModelingUnitPackage.eINSTANCE.getContributionInstruction_ReferencedElement());
		featuresToIgnore.add(ModelingUnitPackage.eINSTANCE
				.getModelingUnitInstructionReference_ReferencedElement());
		featuresToIgnore.add(ModelingUnitPackage.eINSTANCE.getResourceReference_ReferencedElement());
		featuresToIgnore.add(IntentDocumentPackage.eINSTANCE.getIntentGenericElement_CompilationStatus());
		featuresToIgnore.add(IntentDocumentPackage.eINSTANCE.getIntentStructuredElement_FormattedTitle());
		featuresToIgnore.add(IntentDocumentPackage.eINSTANCE.getIntentStructuredElement_CompleteLevel());
		featuresToIgnore.add(IntentDocumentPackage.eINSTANCE.getIntentGenericElement_IndexEntry());
	}

	/**
	 * Modify the repository elements according to the local elements ; this operation should occur during a
	 * transaction and be committed to be effective.
	 * 
	 * @param localRoot
	 *            the local element to commit
	 * @param repositoryRoot
	 *            the repository element to update
	 * @throws MergingException
	 *             if the mergin has encountered a problem.
	 */
	public void mergeFromLocalToRepository(EObject localRoot, EObject repositoryRoot) throws MergingException {
		if (OVERRIDE) {
			for (EStructuralFeature feature : repositoryRoot.eClass().getEAllStructuralFeatures()) {
				repositoryRoot.eSet(feature, localRoot.eGet(feature));
			}
		} else {
			// TODO remove debug instructions when ready
			System.out.println(" ------------------------ LOCAL -----------------------");
			System.out.println();
			DebugUtils.displayModel(localRoot);
			System.out.println();

			System.out.println(" ------------------------ REPO ------------------------");
			System.out.println();
			DebugUtils.displayModel(repositoryRoot);

			if (CustomizationOptions.USE_CUSTOM_DIFF_ENGINE) {
				System.out.println();
				System.out.println(" ---------------------- DISTANCES ---------------------");
				System.out.println();
			}
			Comparison comparison = EMFCompareUtils.compareDocuments(localRoot, repositoryRoot);
			System.out.println();

			System.out.println(" ----------------------- MATCHES ----------------------");
			System.out.println();
			DebugUtils.displayMatchModel(comparison);
			System.out.println();

			System.out.println(" ------------------------ DIFFS -----------------------");
			System.out.println();
			boolean failed = false;
			for (Diff diff : comparison.getDifferences()) {
				if (!filter(diff)) {
					if (failed) {
						System.out.println("ignoring " + diff.getKind() + " " + diff);
						System.out
								.println("\tbased on: " + DebugUtils.matchToReadableString(diff.getMatch()));
					} else {
						System.out.println("applying " + diff.getKind() + " " + diff);
						System.out
								.println("\tbased on: " + DebugUtils.matchToReadableString(diff.getMatch()));
						try {
							diff.copyLeftToRight();
							// CHECKSTYLE:OFF
						} catch (Throwable e) { // DEBUG - workaround noisy merge errors
							// CHECKSTYLE:ON
							System.err.println(e.getClass().getName());
							failed = true;
						}
					}
				}
			}
			if (failed) {
				throw new MergingException("An error occured when merging.");
			}
		}
	}

	/**
	 * Filters the diffs to ignore.
	 * 
	 * @param diff
	 *            the diff to filter or not
	 * @return true if the diff has to be ignored
	 */
	private boolean filter(Diff diff) {
		return (diff instanceof ReferenceChange && featuresToIgnore.contains(((ReferenceChange)diff)
				.getReference()))
				|| (diff instanceof AttributeChange && featuresToIgnore.contains(((AttributeChange)diff)
						.getAttribute()));
	}
}
