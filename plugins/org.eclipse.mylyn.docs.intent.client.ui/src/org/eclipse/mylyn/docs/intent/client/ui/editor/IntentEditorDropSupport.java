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
package org.eclipse.mylyn.docs.intent.client.ui.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.modelingunit.update.MergeUpdater;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;

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
		if (event.data instanceof IStructuredSelection) {
			// TODO support resource drop ?
			List<EObject> droppedEObjects = new ArrayList<EObject>();
			for (Iterator<?> iterator = ((IStructuredSelection)event.data).iterator(); iterator.hasNext();) {
				Object data = iterator.next();
				if (data instanceof EObject) {
					droppedEObjects.add((EObject)data);
				}
			}

			IntentDocumentProvider documentProvider = (IntentDocumentProvider)editor.getDocumentProvider();
			final RepositoryAdapter repositoryAdapter = documentProvider.getListenedElementsHandler()
					.getRepositoryAdapter();
			MergeUpdater updater = new MergeUpdater(repositoryAdapter);

			EObject intentElement = document.getElementAtOffset(editor.getProjectionViewer().getTextWidget()
					.getCaretOffset());

			EObject parent = intentElement;
			while (parent != null && !(parent instanceof ModelingUnit || parent instanceof IntentSection)) {
				parent = parent.eContainer();
			}
			if (parent instanceof ModelingUnit) {
				updater.create((ModelingUnit)parent, droppedEObjects);
			} else if (parent instanceof IntentSection) {
				updater.create((IntentSection)parent, droppedEObjects);
			} else {
				IntentLogger.getInstance()
						.log(LogType.ERROR, "Only modeling units & sections support drops.");
			}
			document.reloadFromAST();
		}
	}

}
