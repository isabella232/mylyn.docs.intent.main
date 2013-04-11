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
package org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.modelingunit.update.SyncStatusUpdater;

/**
 * Proposal used to fix a Synchronization issue by opening the compare Editor.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class UpdateModelingUnitFix extends AbstractIntentFix {

	/**
	 * Default constructor.
	 * 
	 * @param annotation
	 *            the annotation describing the synchronization issue.
	 */
	public UpdateModelingUnitFix(IntentAnnotation annotation) {
		super(annotation);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.AbstractIntentFix#applyFix(org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter,
	 *      org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument)
	 */
	@Override
	protected void applyFix(final RepositoryAdapter repositoryAdapter, IntentEditorDocument document) {
		EObject modelingUnit = syncAnnotation.getCompilationStatus().getTarget();

		// If the target instruction is an ExternalContentReferece
		if (modelingUnit instanceof ExternalContentReference) {
			// We simply mark it as merged
			repositoryAdapter.execute(new IntentCommand() {

				public void execute() {
					((ExternalContentReference)syncAnnotation.getCompilationStatus().getTarget())
							.setMarkedAsMerged(true);
				}
			});
			// Mark document as dirty
			try {
				document.replace(0, 0, "");
			} catch (BadLocationException e) {
				// Silent catch
			}
		} else {
			// Otherwise, we use the SyncStatusUpdater to update the modeling unit according to the sync.
			// issue
			// TODO purpose new modeling unit creation
			while (modelingUnit != null && !(modelingUnit instanceof ModelingUnit)) {
				modelingUnit = modelingUnit.eContainer();
			}

			if (modelingUnit != null) {
				SyncStatusUpdater updater = new SyncStatusUpdater(repositoryAdapter);
				updater.fixSynchronizationStatus((SynchronizerCompilationStatus)syncAnnotation
						.getCompilationStatus());
				((IntentEditorDocument)document).reloadFromAST();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		if (syncAnnotation.getCompilationStatus() != null
				&& syncAnnotation.getCompilationStatus().getTarget() instanceof ExternalContentReference) {
			return "Mark Documentation as updated";
		} else {
			return "Update modeling unit";
		}
	}

}
