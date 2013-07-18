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
package org.eclipse.mylyn.docs.intent.compare.test.unit;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.mylyn.docs.intent.compare.test.utils.AbstractEMFCompareTest;
import org.eclipse.mylyn.docs.intent.compare.utils.EMFCompareUtils;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;

/**
 * Tests the potential merging issues.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class MergingIssues extends AbstractEMFCompareTest {

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testFillEmptyDoc() throws IOException, ParseException {
		compareAndMerge("fillEmptyDoc");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testModelingUnitDeletion() throws IOException, ParseException {
		compareAndMerge("modelingUnitDeletion");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testSectionsAdditions() throws IOException, ParseException {
		compareAndMerge("sectionsAdditions");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testNewChapter() throws IOException, ParseException {
		compareAndMerge("newChapter");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testNewChapterWorking() throws IOException, ParseException {
		compareAndMerge("newChapterWorking");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testDoubleSectionInsertion() throws IOException, ParseException {
		compareAndMerge("doubleSectionInsertion");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testDoubleTopTextAddition() throws IOException, ParseException {
		compareAndMerge("doubleTopTextAddition");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testEndTextAddition() throws IOException, ParseException {
		compareAndMerge("endTextAddition");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testMuAddition() throws IOException, ParseException {
		compareAndMerge("muAddition");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testNewInstruction() throws IOException, ParseException {
		compareAndMerge("newInstruction");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testNewSection() throws IOException, ParseException {
		compareAndMerge("newSection");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testNewSectionUpdate1() throws IOException, ParseException {
		compareAndMerge("newSectionUpdate1");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testNewSectionUpdate2() throws IOException, ParseException {
		compareAndMerge("newSectionUpdate2");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testInversionIssue() throws IOException, ParseException {
		compareAndMerge("inversionIssue");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testRenameAll() throws IOException, ParseException {
		compareAndMerge("renameAll");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testSectionInsertion() throws IOException, ParseException {
		compareAndMerge("sectionInsertion");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testTextDeletion() throws IOException, ParseException {
		compareAndMerge("textDeletion");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testTextInsertion() throws IOException, ParseException {
		compareAndMerge("textInsertion");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testTopChapterAddition() throws IOException, ParseException {
		compareAndMerge("topChapterAddition");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testTopTextAddition() throws IOException, ParseException {
		compareAndMerge("topTextAddition");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testRename() throws IOException, ParseException {
		compareAndMerge("rename");
	}

	/**
	 * Ensures merge stability by ensuring that comparing and then merging the file with the given testName
	 * and the file with the given testName + "modifications" allows to obtain the modified file.
	 * 
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	public void testReverseRename() throws IOException, ParseException {
		compareAndMerge("reverseRename");
	}

	/**
	 * Ensures that comparing and then merging the file with the given testName and the file with the given
	 * testName + "modifications" allows to obtain the modified file.
	 * 
	 * @param testName
	 *            the name of the file to use for test
	 * @throws IOException
	 *             if file cannot be accessed
	 * @throws ParseException
	 *             if file cannot be properly parsed
	 */
	private void compareAndMerge(String testName) throws IOException, ParseException {

		String repository = getFileAsString(new File("data/" + testName + "/IntentDocument.text"));
		String modified = getFileAsString(new File("data/" + testName + "/IntentDocument.text.modifications"));
		IntentStructuredElement left = parseIntentDocument(modified);
		IntentStructuredElement right = parseIntentDocument(repository);

		Comparison comparison = EMFCompareUtils.compareDocuments(left, right);
		for (Diff diff : comparison.getDifferences()) {
			diff.copyLeftToRight();
		}

		assertEquals(modified, new IntentSerializer().serialize(right));
	}

}
