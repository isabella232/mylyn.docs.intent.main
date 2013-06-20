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
package org.eclipse.mylyn.docs.intent.client.ui.editor.annotation;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image.AbstractIntentImageAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image.IntentExternalContentReferenceImageAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image.IntentImageAnnotation;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.markup.markup.Image;
import org.eclipse.mylyn.docs.intent.serializer.ParsedElementPosition;

/**
 * Handles the management of annotation models used by an IntentDocumentProvider.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentAnnotationModelManager {

	/**
	 * The handled annotationModel.
	 */
	private AnnotationModel annotationModel;

	/**
	 * The currently handled compilation status list, mapped with its corresponding annotation (use for
	 * updating the annotations), sorted by each status's target.
	 */
	private Map<String, Map<CompilationStatus, Annotation>> handledCompilationStatus;

	/**
	 * IntentAnnotationModelManager constructor.
	 */
	public IntentAnnotationModelManager() {
		this.annotationModel = new AnnotationModel();
		this.handledCompilationStatus = new HashMap<String, Map<CompilationStatus, Annotation>>();
	}

	/**
	 * Adds the given compilation status as an Annotation in the handled annotationModel at the given
	 * position.
	 * 
	 * @param repositoryAdapter
	 *            the Repository Adapter to use
	 * @param status
	 *            the compilation status to add
	 * @param position
	 *            the position of this annotation
	 */
	public void addAnnotationFromStatus(RepositoryAdapter repositoryAdapter, CompilationStatus status,
			Position position) {
		String targetID = repositoryAdapter.getIDFromElement(status.getTarget()).toString();
		if (handledCompilationStatus.get(targetID) == null) {
			handledCompilationStatus.put(targetID, Maps.<CompilationStatus, Annotation> newLinkedHashMap());
		}
		if (!(handledCompilationStatus.get(targetID).containsKey(status))) {
			// We create an annotation from the status and add it to the annotation model
			Annotation annotation = IntentAnnotationFactory.createAnnotationFromCompilationStatus(status);
			addAnnotation(annotation, position);
			handledCompilationStatus.get(targetID).put(status, annotation);
		}
	}

	/**
	 * Adds the given annotation to the handled annotationModel at the given position.
	 * 
	 * @param annotation
	 *            the annotation to add
	 * @param position
	 *            the position of this annotation
	 */
	private synchronized void addAnnotation(Annotation annotation, Position position) {
		// synchronized(annotationModel.getLockObject()) {
		annotationModel.addAnnotation(annotation, position);
		// }
	}

	/**
	 * Returns the handled annotationModel.
	 * 
	 * @return the handled annotationModel
	 */
	public IAnnotationModel getAnnotationModel() {
		return annotationModel;
	}

	/**
	 * Removes all the compiler annotations from the handled annotationModel.
	 */
	public synchronized void removeAllCompilerAnnotations() {
		@SuppressWarnings("unchecked")
		Iterator<Annotation> annotationIterator = annotationModel.getAnnotationIterator();

		while (annotationIterator.hasNext()) {
			Annotation annotation = annotationIterator.next();
			if (isCompilerAnnotation(annotation.getType())) {
				annotationModel.removeAnnotation(annotation);
			}
		}
		handledCompilationStatus.clear();
	}

	/**
	 * Removes all the compiler annotations associated to the given element that have changed or have been
	 * deleted.
	 * 
	 * @param adapter
	 *            the RepositoryAdapter to use for determine if the stored status are still valid
	 * @param element
	 *            the element to inspect
	 */
	public synchronized void removeInvalidCompilerAnnotations(RepositoryAdapter adapter,
			IntentGenericElement element) {
		// For each compilationStatus associated to the given element
		String elementID = adapter.getIDFromElement(element).toString();
		Map<CompilationStatus, Annotation> statusToAnnotations = handledCompilationStatus.get(elementID);
		if (statusToAnnotations != null) {
			Iterator<Entry<CompilationStatus, Annotation>> statusToAnnotationsIterator = statusToAnnotations
					.entrySet().iterator();
			while (statusToAnnotationsIterator.hasNext()) {
				Entry<CompilationStatus, Annotation> statusToAnnotation = statusToAnnotationsIterator.next();
				boolean removeCurrentStatus = statusToAnnotation.getKey() == null
						|| statusToAnnotation.getKey().getTarget() == null;
				if (!removeCurrentStatus) {
					if (isCompilerAnnotation(statusToAnnotation.getValue().getType())) {
						// If the currentElement doesn't contain this status any more
						if (!element.getCompilationStatus().contains(statusToAnnotation.getKey())) {
							removeCurrentStatus = true;
						}
					}
				}
				if (removeCurrentStatus) {
					annotationModel.removeAnnotation(statusToAnnotation.getValue());
					statusToAnnotationsIterator.remove();
				}
			}
		}
	}

	/**
	 * Creates a syntax error annotation at the given offset, of the given length.
	 * 
	 * @param message
	 *            the message associated to this syntax error
	 * @param offset
	 *            offset of the syntax error annotation
	 * @param length
	 *            length of the syntax error annotation.
	 */
	public void createSyntaxErrorAnnotation(String message, int offset, int length) {
		IntentAnnotation syntaxErrorAnnotation = IntentAnnotationFactory.createSyntaxErrorAnnotation();
		syntaxErrorAnnotation.setText(message);
		Position position = new Position(offset, length);
		addAnnotation(syntaxErrorAnnotation, position);
	}

	/**
	 * Removes all the syntax error annotations from the manage annotation model.
	 */
	public synchronized void removeSyntaxErrorsAnnotations() {
		@SuppressWarnings("unchecked")
		Iterator<Annotation> annotationIterator = (Iterator<Annotation>)annotationModel
				.getAnnotationIterator();
		while (annotationIterator.hasNext()) {
			Annotation next = annotationIterator.next();
			if (IntentAnnotationFactory.INTENT_ANNOT_SYNTAX_ERROR.equals(next.getType())) {
				annotationModel.removeAnnotation(next);
			}
		}
	}

	/**
	 * Return true if the given annotationType indicates a compileAnnotation, false otherwise.
	 * 
	 * @param type
	 *            the type of an annotation
	 * @return true if the given annotationType indicates a compileAnnotation, false otherwise
	 */
	private boolean isCompilerAnnotation(String type) {
		return IntentAnnotationFactory.INTENT_ANNOT_COMPILER_ERROR.equals(type)
				|| IntentAnnotationFactory.INTENT_ANNOT_COMPILER_WARNING.equals(type)
				|| IntentAnnotationFactory.INTENT_ANNOT_GENERAL_INFO.equals(type)
				|| IntentAnnotationFactory.INTENT_ANNOT_SYNC_WARNING.equals(type);
	}

	/**
	 * Updates (e.g. creates or redraw) an
	 * {@link org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image.AbstractIntentImageAnnotation}
	 * corresponding to the given {@link ExternalContentReference} or {@link Image}.
	 * 
	 * @param reference
	 *            the {@link ExternalContentReference} or {@link Image} to render
	 * @param intentPosition
	 *            the {@link ParsedElementPosition} of this instruction
	 */
	public void updateAnnotationFromElementToRender(EObject elementToRender,
			ParsedElementPosition intentPosition) {
		if (intentPosition != null) {
			// Step 1: search for an already existing annotation
			boolean foundAlredyExistingAnnotation = false;
			Iterator<?> annotationIterator = getAnnotationModel().getAnnotationIterator();
			while (annotationIterator.hasNext() && !foundAlredyExistingAnnotation) {
				Object annotation = annotationIterator.next();
				if (annotation instanceof IntentExternalContentReferenceImageAnnotation
						&& elementToRender instanceof ExternalContentReference) {
					if (((IntentExternalContentReferenceImageAnnotation)annotation)
							.getExternalContentReference().getUri()
							.equals(((ExternalContentReference)elementToRender).getUri())) {
						// Make sure the matching annotation will be redrawn at next paint
						foundAlredyExistingAnnotation = true;
						((AbstractIntentImageAnnotation)annotation).setImageShouldBeRedrawn(true);
					}
				} else if (annotation instanceof IntentImageAnnotation && elementToRender instanceof Image) {
					// Make sure the matching annotation will be redrawn at next paint
					foundAlredyExistingAnnotation = true;
					((AbstractIntentImageAnnotation)annotation).setImageShouldBeRedrawn(true);
				}
			}

			// Step 2: create a new annotation if none found
			if (!foundAlredyExistingAnnotation) {
				if (elementToRender instanceof ExternalContentReference) {
					getAnnotationModel()
							.addAnnotation(
									IntentAnnotationFactory
											.createImageAnnotation((ExternalContentReference)elementToRender),
									new Position(intentPosition.getOffset() + intentPosition.getLength(), 0));
				} else if (elementToRender instanceof Image) {
					getAnnotationModel().addAnnotation(
							IntentAnnotationFactory.createImageAnnotation((Image)elementToRender),
							new Position(intentPosition.getOffset() + intentPosition.getLength(), 0));
				}
			}
		}
	}
}
