/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.test.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.merge.service.MergeService;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentDocumentProvider;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;

/**
 * Provide utilities to ease annotations manipulation.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class AnnotationUtils {

	/**
	 * Prevents instantiation.
	 */
	private AnnotationUtils() {
	}

	/**
	 * Indicates if the given editor contains an annotation of the given {@link IntentAnnotationMessageType},
	 * with the given expectedMessage exactly if the exactMessage parameter is true, or containing the given
	 * expectedMessage if false.
	 * 
	 * @param intentEditor
	 *            the editor to search into
	 * @param messageType
	 *            the searched {@link IntentAnnotationMessageType}
	 * @param expectedMessage
	 *            the searched message
	 * @param exactMessage
	 *            indicates if the annotation's message should be exactly the same as the expectedMessage (if
	 *            true), or should contain the given expectedMessage (if false)
	 * @return true if the given editor contains the searched annotation, false otherwise
	 */
	public static boolean hasIntentAnnotation(IntentEditor intentEditor,
			IntentAnnotationMessageType messageType, String expectedMessage, boolean exactMessage) {
		return getIntentAnnotation(intentEditor, messageType, expectedMessage, exactMessage) != null;
	}

	/**
	 * Returns an annotation of the given {@link IntentAnnotationMessageType}, with the given expectedMessage
	 * exactly if the exactMessage parameter is true, or containing the given expectedMessage if false.
	 * Returns null if the annotation cannot be found.
	 * 
	 * @param intentEditor
	 *            the editor to search into
	 * @param messageType
	 *            the searched {@link IntentAnnotationMessageType}
	 * @param expectedMessage
	 *            the searched message
	 * @param exactMessage
	 *            indicates if the annotation's message should be exactly the same as the expectedMessage (if
	 *            true), or should contain the given expectedMessage (if false)
	 * @return the annotation if found, null otherwise.
	 */
	public static IntentAnnotation getIntentAnnotation(IntentEditor intentEditor,
			IntentAnnotationMessageType messageType, String expectedMessage, boolean exactMessage) {
		Iterator annotationIterator = ((IntentDocumentProvider)intentEditor.getDocumentProvider())
				.getAnnotationModel(null).getAnnotationIterator();
		while (annotationIterator.hasNext()) {
			Object annotation = annotationIterator.next();
			if (annotation instanceof IntentAnnotation) {
				if (messageType.equals(((IntentAnnotation)annotation).getMessageType())) {
					String annotationMessage = ((Annotation)annotation).getText();
					if (exactMessage && expectedMessage.equals(annotationMessage)
							|| annotationMessage.contains(expectedMessage)) {
						return (IntentAnnotation)annotation;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Applies the given annotation quick fix.
	 * 
	 * @param syncAnnotation
	 *            the sync annotation
	 * @throws InterruptedException
	 *             if matching fails
	 * @throws IOException
	 *             if merging fails
	 */
	public static void applyAnnotationFix(IntentAnnotation syncAnnotation) throws IOException,
			InterruptedException {
		// Step 1 : getting the resources to compare URI
		String workingCopyResourceURI = ((String)syncAnnotation.getAdditionalInformations().toArray()[1])
				.replace("\"", "");
		String generatedResourceURI = ((String)syncAnnotation.getAdditionalInformations().toArray()[2])
				.replace("\"", "");

		// Step 2 : loading the resources
		ResourceSetImpl rs = new ResourceSetImpl();
		Resource generatedResource = rs.getResource(URI.createURI(generatedResourceURI), true);
		Resource workingCopyResource = rs.getResource(URI.createURI(workingCopyResourceURI), true);

		// Step 3.1 : making match and diff
		MatchModel match = MatchService.doResourceMatch(generatedResource, workingCopyResource, null);
		DiffModel diff = DiffService.doDiff(match, false);

		// Step 3.2 : Merges all differences from local to repository
		List<DiffElement> differences = new ArrayList<DiffElement>(diff.getOwnedElements());
		MergeService.merge(differences, true);

		// Step 3.3 : Save model
		workingCopyResource.save(null);
	}

	/**
	 * Displays the annotations for the given editor.
	 * 
	 * @param intentEditor
	 *            the editor
	 */
	public static void displayAnnotations(IntentEditor intentEditor) {
		System.err.println("Annotations in \"" + intentEditor.getPartName() + "\":");
		Iterator annotationIterator = ((IntentDocumentProvider)intentEditor.getDocumentProvider())
				.getAnnotationModel(null).getAnnotationIterator();
		while (annotationIterator.hasNext()) {
			Object o = annotationIterator.next();
			if (o instanceof IntentAnnotation) {
				IntentAnnotation annotation = (IntentAnnotation)o;
				System.err.println(annotation.getType() + " " + annotation.getText());
			}
		}
		System.err.println();
	}

}
