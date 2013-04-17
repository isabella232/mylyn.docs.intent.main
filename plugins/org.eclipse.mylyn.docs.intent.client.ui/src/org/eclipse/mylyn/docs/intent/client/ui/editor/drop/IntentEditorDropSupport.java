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
package org.eclipse.mylyn.docs.intent.client.ui.editor.drop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentDocumentProvider;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension;
import org.eclipse.mylyn.docs.intent.client.ui.internal.renderers.IEditorRendererExtensionRegistry;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceService;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionBloc;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentChapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentSubSectionContainer;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.modelingunit.update.ExternalContentReferencesMergeUpdater;
import org.eclipse.mylyn.docs.intent.modelingunit.update.MergeUpdater;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.widgets.Display;

/**
 * Implementation of the drop support in intent editor.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentEditorDropSupport extends DropTargetAdapter {

	private IntentEditor editor;

	private IntentEditorDocument document;

	/**
	 * Constructor.
	 * 
	 * @param editor
	 *            the intent editor
	 */
	public IntentEditorDropSupport(IntentEditor editor) {
		this.editor = editor;
		this.document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(
				editor.getEditorInput());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.swt.dnd.DropTargetAdapter#drop(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void drop(DropTargetEvent event) {
		// Step 1: get EObjects from drop event
		List<EObject> droppedEObjects = getDroppedEObjectsFromEvent(event);

		// Step 2: react to drop
		if (!droppedEObjects.isEmpty()) {
			IntentDocumentProvider documentProvider = (IntentDocumentProvider)editor.getDocumentProvider();
			final RepositoryAdapter repositoryAdapter = documentProvider.getListenedElementsHandler()
					.getRepositoryAdapter();
			int carretOffset = editor.getProjectionViewer().getTextWidget().getCaretOffset();

			// Step 2.1: get element at dropped offset (that will be the created elements previous sibling
			EObject intentElement = document.getElementAtOffset(carretOffset);
			EObject parent = intentElement;
			try {
				// if the dropped offset is an empty line, get the previous line
				while (!(intentElement instanceof DescriptionBloc)
						&& !((intentElement instanceof DescriptionUnit))
						&& document
								.get(carretOffset,
										document.getLineLength(document.getLineOfOffset(carretOffset)))
								.trim().length() < 1 && carretOffset > -1) {
					carretOffset--;
				}
				intentElement = document.getElementAtOffset(carretOffset);
			} catch (BadLocationException e1) {
				// Silent catch
			}

			// get parent in which element will be created
			while (parent != null
					&& !(parent instanceof ModelingUnit || parent instanceof IntentSubSectionContainer || parent instanceof IntentDocument)) {
				parent = parent.eContainer();
			}

			try {
				// Step 2.2: create elements
				reactToDrop(repositoryAdapter, parent, intentElement, droppedEObjects);
				document.reloadFromAST();
			} catch (CancellationException e) {
				// Nothing to do, drop was cancelled
			}
		}
	}

	/**
	 * Returns the EObjects corresponding to the given {@link DropTargetEvent}, using contributed
	 * {@link IEditorRendererExtension}s.
	 * 
	 * @param event
	 *            the {@link DropTargetEvent}
	 * @return the EObjects corresponding to the given {@link DropTargetEvent}
	 */
	private List<EObject> getDroppedEObjectsFromEvent(DropTargetEvent event) {
		List<EObject> droppedEObjects = new ArrayList<EObject>();

		// Default behavior : get EObjects from structured selection
		if (event.data instanceof IStructuredSelection) {
			for (Iterator<?> iterator = ((IStructuredSelection)event.data).iterator(); iterator.hasNext();) {
				Object data = iterator.next();
				if (data instanceof EObject) {
					droppedEObjects.add((EObject)data);
				} else {
					// Delegate drop to IEditorRendererExtensions
					for (IEditorRendererExtension editorRendererExtension : IEditorRendererExtensionRegistry
							.getEditorRendererExtensions()) {
						droppedEObjects.addAll(editorRendererExtension.getEObjectsFromDropTargetEvent(event));
					}
				}
			}
		} else {
			// Delegate drop to IEditorRendererExtensions
			for (IEditorRendererExtension editorRendererExtension : IEditorRendererExtensionRegistry
					.getEditorRendererExtensions()) {
				droppedEObjects.addAll(editorRendererExtension.getEObjectsFromDropTargetEvent(event));
			}
		}
		return droppedEObjects;
	}

	/**
	 * Reacts to the drop by updating the {@link org.eclipse.mylyn.docs.intent.core.document.IntentDocument}.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 * @param parent
	 *            the parent {@link org.eclipse.mylyn.docs.intent.core.document.IntentDocument} element in
	 *            which creating dropped elements
	 * @param sibling
	 *            the Intent element located right before the elements to create
	 * @param droppedEObjects
	 *            the dropped {@link EObject}s
	 * @throws CancellationException
	 *             if the drop was cancelled
	 */
	private void reactToDrop(RepositoryAdapter repositoryAdapter, EObject parent, EObject sibling,
			List<EObject> droppedEObjects) throws CancellationException {
		boolean shouldUseExternalContentReferencesDropMode = shouldUseExternalContentReferencesDropMode();
		// Step 1: display (if needed) a pop-up allowing end-user to choose which drop mode should be used
		if (shouldDisplayDropModePopUp()) {
			shouldUseExternalContentReferencesDropMode = displayDropModePopUp(shouldUseExternalContentReferencesDropMode);
		}
		// Step 2: perform drop
		MergeUpdater updater = null;
		if (shouldUseExternalContentReferencesDropMode) {
			// Using External content references
			updater = new ExternalContentReferencesMergeUpdater(repositoryAdapter);
		} else {
			// Using Merge Updater
			updater = new MergeUpdater(repositoryAdapter);
		}
		if (parent instanceof ModelingUnit) {
			updater.create((ModelingUnit)parent, sibling, droppedEObjects);
		} else if (parent instanceof IntentSection) {
			updater.create((IntentSection)parent, sibling, droppedEObjects);
		} else if (parent instanceof IntentDocument) {
			updater.create((IntentDocument)parent, sibling, droppedEObjects);
		} else if (parent instanceof IntentChapter) {
			updater.create((IntentChapter)parent, sibling, droppedEObjects);
		} else {
			IntentLogger.getInstance().log(LogType.ERROR,
					"Can't drop external references in this container:" + parent);
		}
	}

	/**
	 * Opens a drop mode pop-up letting the end-user choose which drop mode should be used .
	 * 
	 * @param shouldUseExternalContentReferencesDropMode
	 *            default preference value
	 * @return true if the end-user want to use external content reference, false otherwise
	 * @throws CancellationException
	 *             if the end-user has cancelled
	 */
	private boolean displayDropModePopUp(boolean shouldUseExternalContentReferencesDropMode)
			throws CancellationException {
		// Open dialog
		DropModeDialog dropModeDialog = new DropModeDialog(Display.getCurrent().getActiveShell());
		dropModeDialog.open();

		// Get end-user choice
		if (dropModeDialog.isCancelled()) {
			throw new CancellationException();
		}

		// Update preferences
		IEclipsePreferences node = InstanceScope.INSTANCE.getNode(IntentEditorActivator.getDefault()
				.getBundle().getSymbolicName());
		node.putBoolean(IntentPreferenceConstants.DND_DISPLAY_POP_UP, !dropModeDialog.getToggleState());
		node.putBoolean(IntentPreferenceConstants.DND_USE_EXTERNAL_REFERENCES,
				dropModeDialog.shouldUseExternalReferences());
		return dropModeDialog.shouldUseExternalReferences();
	}

	/**
	 * Indicates whether we should display a pop-up letting the end-user choose which drop mode should be
	 * used.
	 * 
	 * @return true if the pop-up should be displayed, false otherwise
	 */
	private boolean shouldDisplayDropModePopUp() {
		return IntentPreferenceService.getBoolean(IntentPreferenceConstants.DND_DISPLAY_POP_UP);
	}

	/**
	 * Indicates whether we should use external content references to link the dropped elements with the
	 * {@link org.eclipse.mylyn.docs.intent.core.document.IntentDocument}, according to preferences.
	 * 
	 * @return true if we should use external content references to link the dropped elements, false otherwise
	 */
	private boolean shouldUseExternalContentReferencesDropMode() {
		return IntentPreferenceService.getBoolean(IntentPreferenceConstants.DND_USE_EXTERNAL_REFERENCES);
	}
}
