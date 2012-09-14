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

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
		// String localSerialized = new IntentSerializer().serialize(localRoot);
		// String repoSerialized = new IntentSerializer().serialize(repositoryRoot);

		if (OVERRIDE) {
			for (EStructuralFeature feature : repositoryRoot.eClass().getEAllStructuralFeatures()) {
				repositoryRoot.eSet(feature, localRoot.eGet(feature));
			}
		} else {
			Comparison comparison = EMFCompareUtils.compareDocuments(localRoot, repositoryRoot);
			for (Diff diff : comparison.getDifferences()) {
				diff.copyLeftToRight();
			}
		}

		// String newRepoSerialized = new IntentSerializer().serialize(repositoryRoot);
		// if (!localSerialized.equals(newRepoSerialized)) {
		// File dir = new File(
		// "D:/dev/git/intent/org.eclipse.mylyn.docs.intent.main/tests/org.eclipse.mylyn.docs.intent.compare.test/data/"
		// + System.currentTimeMillis());
		// dir.mkdir();
		// saveToFile(dir + "/IntentDocument.text", repoSerialized);
		// saveToFile(dir + "/IntentDocument.text.modifications", localSerialized);
		// throw new MergingException("diffs");
		// }
	}

	public static void saveToFile(String file, String content) {
		File destination = new File(file);
		try {
			Files.write(content, destination, Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.err.println(destination.getAbsolutePath());
			e.printStackTrace();
		}
	}
}
