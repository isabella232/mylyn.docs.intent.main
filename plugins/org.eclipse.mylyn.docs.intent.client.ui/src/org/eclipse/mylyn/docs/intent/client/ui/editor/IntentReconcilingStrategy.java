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
package org.eclipse.mylyn.docs.intent.client.ui.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.IntentPartitionScanner;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;
import org.eclipse.swt.widgets.Display;

/**
 * This reconciling strategy will allow us to enable folding support in the Intent editor.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class IntentReconcilingStrategy implements IReconcilingStrategy, IReconcilingStrategyExtension {

	/**
	 * This will hold the list of all annotations that have been added since the last reconciling.
	 */
	private final Map<Annotation, Position> addedAnnotations = new HashMap<Annotation, Position>();

	/** Current known positions of foldable block. */
	private final Map<Annotation, Position> currentAnnotations = new HashMap<Annotation, Position>();

	/**
	 * This will hold the list of all annotations that have been removed since the last reconciling.
	 */
	private final List<Annotation> deletedAnnotations = new ArrayList<Annotation>();

	/** Editor this provides folding support to. */
	private final IntentEditor editor;

	/**
	 * This will hold the list of all annotations that have been modified since the last reconciling.
	 */
	private final Map<Annotation, Position> modifiedAnnotations = new HashMap<Annotation, Position>();

	/** The document we'll seek foldable blocks in. */
	private IDocument document;

	/** Current offset. */
	private int offset;

	/**
	 * Pair matcher used to reconcile document.
	 */
	private IntentPairMatcher pairMatcher = new IntentPairMatcher();

	/**
	 * Instantiates the reconciling strategy for a given editor.
	 * 
	 * @param editor
	 *            Editor which we seek to provide folding support for.
	 */
	public IntentReconcilingStrategy(IntentEditor editor) {
		this.editor = editor;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension#initialReconcile()
	 */
	public void initialReconcile() {
		offset = 0;
		computePositions();
		updateFoldingStructure();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy#reconcile(org.eclipse.jface.text.reconciler.DirtyRegion,
	 *      org.eclipse.jface.text.IRegion)
	 */
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
		reconcile(subRegion);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy#reconcile(org.eclipse.jface.text.IRegion)
	 */
	public void reconcile(IRegion partition) {
		offset = partition.getOffset();
		computePositions();
		updateFoldingStructure();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy#setDocument(org.eclipse.jface.text.IDocument)
	 */
	public void setDocument(IDocument document) {
		this.document = document;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension#setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setProgressMonitor(IProgressMonitor monitor) {
		// none
	}

	/**
	 * This will compute the current block positions. The offset at which computations start is determined by
	 * {@link #offset}.
	 */
	private void computePositions() {
		deletedAnnotations.clear();
		modifiedAnnotations.clear();
		addedAnnotations.clear();
		deletedAnnotations.addAll(currentAnnotations.keySet());

		for (Map.Entry<Annotation, Position> entry : currentAnnotations.entrySet()) {
			final Position position = entry.getValue();
			if (position.getOffset() + position.getLength() < offset) {
				deletedAnnotations.remove(entry.getKey());
			}
		}
		try {
			boolean eof = seekBlockStart();
			int startOffset = offset;
			while (!eof) {
				offset++;
				// Case 1: Structural Content folding
				if (document.getLineOfOffset(startOffset) > 0
						&& document.getContentType(startOffset).equals(
								IntentPartitionScanner.INTENT_STRUCTURAL_CONTENT)) {
					IRegion match = pairMatcher.match(document, offset);
					if (match != null) {
						int endOffset = match.getOffset() + match.getLength() + 1;
						if (document.getNumberOfLines(startOffset, endOffset - startOffset) > 2) {
							createOrUpdateAnnotation(startOffset, endOffset - startOffset, false);
						}
					}
				} else if (document.getLineOfOffset(startOffset) > 0
						&& document.getContentType(startOffset).equals(
								IntentPartitionScanner.INTENT_MODELINGUNIT)) {
					// Case 2: Modeling Unit folding
					// Search for modeling unit end
					String documentZone = document.get().substring(startOffset);
					int endOffset = documentZone.indexOf("M@") + 2;
					if (endOffset > -1) {
						createOrUpdateAnnotation(startOffset - 1, endOffset,
								shouldCollapseModelingUnitByDefault());
					}
				}
				eof = seekBlockStart();
				startOffset = offset;
			}
		} catch (BadLocationException e) {
			// Nothing to do
		}
		for (Annotation deleted : deletedAnnotations) {
			currentAnnotations.remove(deleted);
		}
	}

	/**
	 * Eats chars away till we find a start char.
	 * 
	 * @return <code>true</code> if the end of file has been reached. <code>false</code> otherwise.
	 * @throws BadLocationException
	 *             Thrown if we try and get an out of range character. Should not happen.
	 */
	private boolean seekBlockStart() throws BadLocationException {
		char next = document.getChar(offset);
		char previous = ' ';
		boolean eof = offset + 1 >= document.getLength();
		boolean foundModelingUnit = false;
		while (!eof && next != '{' && !foundModelingUnit) {
			offset++;
			previous = next;
			next = document.getChar(offset);
			eof = offset + 1 == document.getLength();
			foundModelingUnit = next == 'M' && previous == '@';
		}
		return eof;
	}

	/**
	 * This will update lists {@link #deletedAnnotations}, {@link #addedAnnotations} and
	 * {@link #modifiedAnnotations} according to the given values.
	 * 
	 * @param newOffset
	 *            Offset of the text we want the annotation updated of.
	 * @param newLength
	 *            Length of the text we want the annotation updated of.
	 * @param initiallyCollapsed
	 *            Indicates that the created annotation should be folded from start.
	 * @throws BadLocationException
	 *             Thrown if we try and get an out of range character. Should not happen.
	 */
	private void createOrUpdateAnnotation(final int newOffset, final int newLength, boolean initiallyCollapsed)
			throws BadLocationException {
		boolean createAnnotation = true;
		final Map<Annotation, Position> copy = new HashMap<Annotation, Position>(currentAnnotations);
		final String text = document.get(newOffset, newLength);
		for (Iterator<Entry<Annotation, Position>> iterator = copy.entrySet().iterator(); iterator.hasNext();) {
			Entry<Annotation, Position> entry = iterator.next();
			// added checking to avoid same text elements to bo ignored
			if (entry.getKey().getText().equals(text) && entry.getValue().getOffset() == newOffset) {
				createAnnotation = false;
				final Position oldPosition = entry.getValue();
				if (oldPosition.getOffset() != newOffset || oldPosition.getLength() != newLength) {
					final Position newPosition = new Position(newOffset, newLength);
					modifiedAnnotations.put(entry.getKey(), newPosition);
					currentAnnotations.put(entry.getKey(), newPosition);
				}
				deletedAnnotations.remove(entry.getKey());
				break;
			}
		}
		if (createAnnotation) {
			Annotation annotation = null;
			annotation = new ProjectionAnnotation(initiallyCollapsed);
			annotation.setText(text);
			final Position position = new Position(newOffset, newLength);
			currentAnnotations.put(annotation, position);
			addedAnnotations.put(annotation, position);
		}
	}

	/**
	 * Updates the editor's folding structure.
	 */
	private void updateFoldingStructure() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				editor.updateFoldingStructure(addedAnnotations, deletedAnnotations, modifiedAnnotations);
			}
		});
	}

	/**
	 * Indicates whether Modeling units should be initially collapsed in the Intent editor.
	 * 
	 * @return true if Modeling units should be initially collapsed in the Intent editor, false otherwise
	 */
	private boolean shouldCollapseModelingUnitByDefault() {
		// We should collapse modeling units if

		return
		// - preference say so
		IntentEditorActivator.getDefault().getPreferenceStore()
				.getBoolean(IntentPreferenceConstants.COLLAPSE_MODELING_UNITS)
				&&
				// - and this is editor opening
				!editor.isInitialFoldingStructureComplete();
	}

}
