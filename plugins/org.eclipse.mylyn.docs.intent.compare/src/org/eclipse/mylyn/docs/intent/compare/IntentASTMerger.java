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
package org.eclipse.mylyn.docs.intent.compare;

import java.util.List;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Merges local and repository asts using EMF Compare.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentASTMerger {

	private static final boolean OVERRIDE = false;

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
		// TODO [COMPARE2] [MERGER] check merge direction
		if (OVERRIDE) {
			for (EStructuralFeature feature : repositoryRoot.eClass().getEAllStructuralFeatures()) {
				repositoryRoot.eSet(feature, localRoot.eGet(feature));
			}
		} else {
			for (Diff diff : getDifferences(localRoot, repositoryRoot)) {
				diff.copyLeftToRight();
			}
		}
	}

	/**
	 * Returns the differences between a given local object and the corresponding repository element.
	 * 
	 * @param localRoot
	 *            the local element to commit
	 * @param repositoryRoot
	 *            the repository element to update
	 * @return the differences between a given local object and the corresponding repository element
	 */
	public static List<Diff> getDifferences(EObject localRoot, EObject repositoryRoot) {
		return EMFCompareUtils.compare(localRoot, repositoryRoot).getDifferences();
	}
}
