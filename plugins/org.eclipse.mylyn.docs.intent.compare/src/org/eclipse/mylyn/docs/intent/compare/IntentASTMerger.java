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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

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
		if (OVERRIDE) {
			for (EStructuralFeature feature : repositoryRoot.eClass().getEAllStructuralFeatures()) {
				repositoryRoot.eSet(feature, localRoot.eGet(feature));
			}
		} else {
			List<Diff> differences = getDifferences(localRoot, repositoryRoot);
			// TODO [COMPARE2] find how to merge
			// MergeService.merge(differences, true);
		}
	}

	/**
	 * For now on, does nothing.
	 */
	public void mergeFromRepositoryToLocal() {

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
		// Step 0 : match preparation
		// Step 0.1 : we create a sample resource and add the localRoot to
		// its content.
		Resource sampleResource = new ResourceImpl(URI.createURI("http://mysampleuri.com"));
		sampleResource.getContents().add(localRoot);

		// TODO [COMPARE2] adapt to new APIs
		// // Step 0.2 Defining a scope provider
		// MatchModel match = null;
		// IMatchScopeProvider scopeProvider = new IntentScopeProvider(localRoot, repositoryRoot);
		// Map<String, Object> optionsMap = new HashMap<String, Object>();
		// optionsMap.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER, scopeProvider);
		//
		// // Step 1 : matching the local and the repository root using a custom
		// // MatcheEngine.
		// match = new IntentMatchEngine(localRoot, repositoryRoot).contentMatch(localRoot, repositoryRoot,
		// optionsMap);
		//
		// Comparison diff = DiffService.doDiff(match, false);
		// // Step 3 : Merges all differences from local to repository
		// List<Diff> differences = new ArrayList<Diff>(diff.getOwnedElements());
		//
		// return differences;

		return null;
	}
}
