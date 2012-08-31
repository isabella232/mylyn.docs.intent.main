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
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.EMFCompareConfiguration;
import org.eclipse.emf.compare.EMFCompareConfiguration.Builder;
import org.eclipse.emf.compare.EMFCompareConfiguration.USE_IDS;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentDocumentProvider;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.modelingunit.update.SyncStatusUpdater;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;

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
	 * Returns all annotations of the given {@link IntentAnnotationMessageType}.
	 * 
	 * @param intentEditor
	 *            the editor to search into
	 * @param messageType
	 *            the searched {@link IntentAnnotationMessageType}
	 * @return the annotations
	 */
	public static List<IntentAnnotation> getIntentAnnotations(IntentEditor intentEditor,
			IntentAnnotationMessageType messageType) {
		List<IntentAnnotation> res = new ArrayList<IntentAnnotation>();
		Iterator<?> annotationIterator = ((IntentDocumentProvider)intentEditor.getDocumentProvider())
				.getAnnotationModel(null).getAnnotationIterator();
		while (annotationIterator.hasNext()) {
			Object annotation = annotationIterator.next();
			if (annotation instanceof IntentAnnotation) {
				if (messageType.equals(((IntentAnnotation)annotation).getMessageType())) {
					res.add((IntentAnnotation)annotation);
				}
			}
		}
		return res;
	}

	/**
	 * Applies the given annotation quick fix.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 * @param syncAnnotation
	 *            the sync annotation
	 * @throws InterruptedException
	 *             if matching fails
	 * @throws IOException
	 *             if merging fails
	 */
	public static void mergeToWorkingCopy(RepositoryAdapter repositoryAdapter, IntentAnnotation syncAnnotation)
			throws IOException, InterruptedException {
		// Step 1 : getting the resources to compare URI
		String workingCopyResourceURI = ((SynchronizerCompilationStatus)syncAnnotation.getCompilationStatus())
				.getWorkingCopyResourceURI().replace("\"", "");
		String generatedResourceURI = ((SynchronizerCompilationStatus)syncAnnotation.getCompilationStatus())
				.getCompiledResourceURI().replace("\"", "");

		// Step 2 : loading the resources
		Resource generatedResource = repositoryAdapter.getResource(generatedResourceURI);

		ResourceSetImpl rs = new ResourceSetImpl();
		Resource workingCopyResource = rs.getResource(URI.createURI(workingCopyResourceURI), true);

		// Step 3.1 : making match and diff
		// TODO [COMPARE2] [COMPARISON] factorize comparison launch
		Builder builder = EMFCompareConfiguration.builder();
		builder.shouldUseID(USE_IDS.NEVER);
		EMFCompareConfiguration configuration = builder.build();
		Comparison diff = EMFCompare.compare(generatedResource, workingCopyResource, configuration);

		// Step 3.2 : Merges all differences from local to repository
		List<Diff> differences = new ArrayList<Diff>(diff.getDifferences());
		// TODO [COMPARE2] [MERGE] find how to merge
		// MergeService.merge(differences, true);

		// Step 3.3 : Save model
		workingCopyResource.save(null);
	}

	/**
	 * Applies the annotation quick fix at the given index.
	 * 
	 * @param document
	 *            the intent editor document
	 * @param repositoryAdapter
	 *            the repository adapter
	 * @param syncAnnotation
	 *            the sync annotation
	 * @param index
	 *            the annotation index
	 */
	public static void applyAnnotationFix(IntentEditorDocument document, RepositoryAdapter repositoryAdapter,
			IntentAnnotation syncAnnotation, int index) {
		// NOTE: similar to
		// org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.UpdateModelingUnitFix.applyFix(RepositoryAdapter,
		// IntentEditorDocument)

		EObject modelingUnit = syncAnnotation.getCompilationStatus().getTarget();

		while (modelingUnit != null && !(modelingUnit instanceof ModelingUnit)) {
			modelingUnit = modelingUnit.eContainer();
		}

		if (modelingUnit != null) {
			SyncStatusUpdater updater = new SyncStatusUpdater(repositoryAdapter);
			updater.fixSynchronizationStatus((SynchronizerCompilationStatus)syncAnnotation
					.getCompilationStatus());
			document.reloadFromAST();
		}

		// FIXME WORKAROUND
		// workaround issue when applying quick fixes : editor doesn't reflect modifications
		document.set(new IntentSerializer().serialize((EObject)document.getAST()));
		// END WORKAROUND
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
