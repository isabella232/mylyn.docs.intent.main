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

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.mylyn.docs.intent.compare.debug.DebugUtils;
import org.eclipse.mylyn.docs.intent.compare.utils.EMFCompareUtils;

/**
 * Merges local and repository asts using EMF Compare.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentASTMerger {

	private static final boolean OVERRIDE = false;

	/**
	 * Constructor.
	 */
	public IntentASTMerger() {
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
			if (DebugUtils.USE_DEFAULT_COMPARE) {
				System.err.println("WARNING !!! default comparison activated");
				System.err.println();
			}

			// TODO remove debug instructions when ready
			if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
				System.out.println(" ------------------------ REPO ------------------------");
				System.out.println();
				DebugUtils.displayModel(repositoryRoot);
				System.out.println();
				System.out.println(" ------------------------ LOCAL -----------------------");
				System.out.println();
				DebugUtils.displayModel(localRoot);
				System.out.println();
			}

			Comparison comparison = null;
			if (DebugUtils.USE_DEFAULT_COMPARE) {
				comparison = EMFCompareUtils.compare(localRoot, repositoryRoot);
			} else {
				comparison = EMFCompareUtils.compareDocuments(localRoot, repositoryRoot);
			}

			if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
				System.out.println(" ----------------------- MATCHES ----------------------");
				System.out.println();
				DebugUtils.displayMatchModel(comparison);
				System.out.println();
				System.out.println(" ------------------------ DIFFS -----------------------");
				System.out.println();
			}

			Throwable exception = null;
			for (Diff diff : comparison.getDifferences()) {
				if (exception != null) {
					if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
						System.err.println("ignoring " + DebugUtils.diffToReadableString(diff));
						System.err.println();
					}
				} else {
					if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
						System.err.println("applying " + DebugUtils.diffToReadableString(diff));
						System.err.println();
					}
					try {
						diff.copyLeftToRight();
					} catch (Throwable e) { // DEBUG - workaround noisy merge errors
						exception = e;
					}
				}
			}
			if (exception != null) {
				throw new MergingException("An error occured when merging: " + exception.getClass().getName());
			}
		}
	}
}
