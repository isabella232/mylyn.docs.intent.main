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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentDocumentProvider;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.modelingunit.gen.ModelingUnitGenerator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * {@link ICompletionProposal} used to fix a Synchronization issue by opening the compare Editor.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class GenerateModelingUnitFix implements ICompletionProposal {

	/**
	 * The modeling units generator.
	 */
	private ModelingUnitGenerator generator = new ModelingUnitGenerator();

	private IntentAnnotation syncAnnotation;

	/**
	 * The root {@link EObject} to generate.
	 */
	private EObject root;

	/**
	 * Default constructor.
	 * 
	 * @param annotation
	 *            the {@link IntentAnnotation} describing the synchronization issue.
	 */
	public GenerateModelingUnitFix(Annotation annotation) {
		this.syncAnnotation = (IntentAnnotation)annotation;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#apply(org.eclipse.jface.text.IDocument)
	 */
	public void apply(IDocument document) {

		// retrieves the modeling unit

		URI targetInstructionURI = URI.createURI(((String)syncAnnotation.getAdditionalInformations()
				.toArray()[5]).replace("\"", ""));

		IntentEditorDocument intentEditorDocument = (IntentEditorDocument)document;
		IntentEditor editor = intentEditorDocument.getIntentEditor();
		IntentDocumentProvider documentProvider = (IntentDocumentProvider)editor.getDocumentProvider();

		String fragment = targetInstructionURI.fragment();
		URI resourceURI = URI.createURI(targetInstructionURI.toString().substring(0,
				targetInstructionURI.toString().length() - fragment.length() - 1));

		final RepositoryAdapter repositoryAdapter = documentProvider.getListenedElementsHandler()
				.getRepositoryAdapter();
		Resource resource = repositoryAdapter.getResource(getPath(resourceURI));
		EObject target = resource.getEObject(fragment);

		if (target instanceof InstanciationInstruction) {
			InstanciationInstruction instanciation = (InstanciationInstruction)target;

			while (target != null && !(target instanceof ModelingUnit)) {
				target = target.eContainer();
			}

			if (target != null) {
				// generates the addition

				final EObject root = getRootEObjectToGenerate();
				final ModelingUnit container = (ModelingUnit)target;

				final ContributionInstruction contribution = generator.generateContribution(instanciation,
						root);

				repositoryAdapter.execute(new IntentCommand() {

					public void execute() {
						try {
							container.getInstructions().add(contribution);
							repositoryAdapter.save();
						} catch (ReadOnlyException e) {
							IntentUiLogger.logError(e);
						} catch (SaveException e) {
							IntentUiLogger.logError(e);
						}
					}

				});

				((IntentEditorDocument)document).reloadFromAST();
			}
		}
	}

	/**
	 * Returns the root EObject to generate.
	 * 
	 * @return the root EObject to generate
	 */
	private EObject getRootEObjectToGenerate() {
		if (root == null) {
			String workingCopyResourceURI = ((String)syncAnnotation.getAdditionalInformations().toArray()[1])
					.replace("\"", "");
			ResourceSetImpl rs = new ResourceSetImpl();
			Resource workingCopyResource = rs.getResource(URI.createURI(workingCopyResourceURI), true);
			String workingCopyElementURIFragment = ((String)syncAnnotation.getAdditionalInformations()
					.toArray()[4]).replace("\"", "");
			root = workingCopyResource.getEObject(workingCopyElementURIFragment);
		}
		return root;
	}

	/**
	 * Retrieves the resource path from an URI. TODO merge, there are similar methods in intent.
	 * 
	 * @param uri
	 *            the uri
	 * @return the resource path
	 */
	private static String getPath(URI uri) {
		String path = null;
		if (uri.isPlatformResource()) {
			path = uri.trimFileExtension().toString().replaceFirst("platform:/resource/", "");
			path = path.substring(path.indexOf("INTENT") - 1);
		} else {
			// should not happen
		}
		return path;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getSelection(org.eclipse.jface.text.IDocument)
	 */
	public Point getSelection(IDocument document) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getAdditionalProposalInfo()
	 */
	public String getAdditionalProposalInfo() {
		return "";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		return "Generate missing " + getRootEObjectToGenerate().eClass().getName()
				+ " element (experimental)";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getImage()
	 */
	public Image getImage() {
		return IntentEditorActivator.getDefault().getImage("icon/annotation/sync-warning.gif");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getContextInformation()
	 */
	public IContextInformation getContextInformation() {
		return null;
	}

}
