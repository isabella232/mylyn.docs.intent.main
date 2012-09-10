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

import java.io.File;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.mylyn.docs.intent.compare.debug.DebugUtils;
import org.eclipse.mylyn.docs.intent.compare.utils.EMFCompareUtils;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;

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
		String initialContent = new IntentSerializer().serialize(repositoryRoot);
		String modifiedContent = new IntentSerializer().serialize(localRoot);

		if (OVERRIDE) {
			for (EStructuralFeature feature : repositoryRoot.eClass().getEAllStructuralFeatures()) {
				repositoryRoot.eSet(feature, localRoot.eGet(feature));
			}
		} else {
			if (DebugUtils.USE_DEFAULT_COMPARE) {
				System.err.println("WARNING !!! default comparison activated");
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
			}

			if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
				System.out.println();
				System.out.println(" ---------------------- DISTANCES ---------------------");
				System.out.println();
			}
			Comparison comparison = null;
			if (DebugUtils.USE_DEFAULT_COMPARE) {
				comparison = EMFCompareUtils.compare(localRoot, repositoryRoot);
			} else {
				comparison = EMFCompareUtils.compareDocuments(localRoot, repositoryRoot);
			}
			if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
				System.out.println();
			}

			if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
				System.out.println(" ----------------------- MATCHES ----------------------");
				System.out.println();
				DebugUtils.displayMatchModel(comparison);
				System.out.println();
			}

			if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
				System.out.println(" ------------------------ DIFFS -----------------------");
				System.out.println();
			}

			Throwable exception = null;
			for (Diff diff : comparison.getDifferences()) {
				if (exception != null) {
					if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
						System.err.println("ignoring " + diff.getKind() + " " + diff);
						System.err
								.println("\tbased on: " + DebugUtils.matchToReadableString(diff.getMatch()));
					}
				} else {
					if (DebugUtils.LOG_DEBUG_INFORMATIONS) {
						System.out.println("applying " + diff.getKind() + " " + diff);
						System.out
								.println("\tbased on: " + DebugUtils.matchToReadableString(diff.getMatch()));
					}
					try {
						diff.copyLeftToRight();
					} catch (Throwable e) { // DEBUG - workaround noisy merge errors
						exception = e;
					}
				}
			}
			if (exception != null) {
				if (DebugUtils.SAVE_TESTS) {
					String base = "D:\\dev\\git\\intent\\org.eclipse.mylyn.docs.intent.main\\plugins\\org.eclipse.mylyn.docs.intent.compare.test\\data";
					String directory = base + "\\test" + System.currentTimeMillis();
					new File(directory).mkdir();
					DebugUtils.saveToFile(directory + "\\IntentDocument.text", initialContent);
					DebugUtils.saveToFile(directory + "\\IntentDocument.text.modifications", modifiedContent);
				}
				throw new MergingException("An error occured when merging: " + exception.getClass().getName());

			}
		}
	}

}
