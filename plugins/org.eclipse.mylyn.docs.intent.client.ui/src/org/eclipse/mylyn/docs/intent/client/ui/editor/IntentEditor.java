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
package org.eclipse.mylyn.docs.intent.client.ui.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.ColorManager;
import org.eclipse.mylyn.docs.intent.client.ui.editor.outline.IntentQuickOutlineControl;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * A multi-page editor allowing:
 * <ul>
 * <li>to interact with IntentElements stored in a repository (through IntentEditorImpl)</li>
 * <li>to display the exported version of the current editor (e.g. in HTML)</li>
 * </ul>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public interface IntentEditor extends ITextEditor {
	/**
	 * Returns the Intent Element on which the current editor is opened.
	 * 
	 * @return the Intent Element on which the current editor is opened
	 */
	EObject getIntentContent();

	/**
	 * Indicates if this editor contains the given element.
	 * 
	 * @param elementToOpen
	 *            the element to determine if it's contained in this editor
	 * @return true if this editor contains the given element, false otherwise
	 */
	boolean containsElement(IntentGenericElement elementToOpen);

	/**
	 * Sets the highlighted range of this text editor to the specified element.
	 * 
	 * @param element
	 *            the element to highlight
	 * @return true if the editor was able to select the given element, false otherwise
	 */
	boolean selectRange(IntentGenericElement elementToSelectRangeWithLoadedFromAdapter);

	/**
	 * Returns the {@link IntentPairMatcher} associated to the current editor.
	 * 
	 * @return the {@link IntentPairMatcher} associated to the current editor
	 */
	IntentPairMatcher getBlockMatcher();

	/**
	 * Returns the {@link ColorManager} associated to the current editor.
	 * 
	 * @return the {@link ColorManager} associated to the current editor
	 */
	ColorManager getColorManager();

	/**
	 * Returns the current {@link IntentQuickOutlineControl} associated to the current editor.
	 * 
	 * @return the current {@link IntentQuickOutlineControl} associated to the current editor (can be null)
	 */
	IntentQuickOutlineControl getCurrentQuickOutline();

	/**
	 * Refreshes the outline view.
	 * 
	 * @param newAST
	 *            the new value of the AST to use for refreshing this view
	 */
	void refreshOutlineView(EObject newAST);

	/**
	 * Refreshes the current editor's title according to the new AST.
	 * 
	 * @param newAST
	 *            the new AST to compute the title from
	 */
	void refreshTitle(EObject localAST);

	/**
	 * Indicates if the {@link IntentEditor} has already collapsed structures that should be collapsed at
	 * opening. Typically used to determine whether Images should be painted.
	 * 
	 * @return true if the {@link IntentEditor} has already collapsed structures that should be collapsed at
	 *         opening, false otherwise
	 */
	boolean isInitialFoldingStructureComplete();

	/**
	 * Updates the folding structure of the template. This will be called from the Atl template reconciler in
	 * order to allow the folding of blocks to the user.
	 * 
	 * @param addedAnnotations
	 *            These annotations have been added since the last reconciling operation.
	 * @param deletedAnnotations
	 *            This list represents the annotations that were deleted since we last reconciled.
	 * @param modifiedAnnotations
	 *            These annotations have seen their positions updated.
	 */
	void updateFoldingStructure(Map<Annotation, Position> addedAnnotations,
			List<Annotation> deletedAnnotations, Map<Annotation, Position> modifiedAnnotations);

	/**
	 * Returns the {@link ProjectionViewer} associated to the current editor.
	 * 
	 * @return the {@link ProjectionViewer} associated to the current editor
	 */
	ProjectionViewer getProjectionViewer();

	/**
	 * Returns the {@link SourceViewerConfiguration} associated to the current editor.
	 * 
	 * @return the {@link SourceViewerConfiguration} associated to the current editor
	 */
	SourceViewerConfiguration getViewerConfiguration();

	/**
	 * Creates the quick outline presenter and install it on the current editor.
	 * 
	 * @return The quick outline presenter.
	 */
	IInformationPresenter createQuickOutlinePresenter();
}
