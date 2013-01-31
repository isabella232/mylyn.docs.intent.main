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
package org.eclipse.mylyn.docs.intent.client.ui.editor.hyperlinks;

import java.util.Iterator;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IRegion;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension;
import org.eclipse.mylyn.docs.intent.client.ui.internal.renderers.IEditorRendererExtensionRegistry;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.common.query.TraceabilityInformationsQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * An {@link AbstractIntentHyperLink} allowing to open the Working copy resource corresponding to the
 * currently focused {@link ModelingUnitInstruction} (if any found).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class OpenWorkingCopyResourceHyperLink extends AbstractIntentHyperLink {

	private URI workingCopyResourceURI;

	private ExternalContentReference externalContentReference;

	/**
	 * Instantiates an hyperlink given the editor it appears on and the text region it spans to.
	 * 
	 * @param textEditor
	 *            Editor on which this hyperlink is shown.
	 * @param hyperlinkRegion
	 *            Region of the editor where this hyperlink appears.
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the Intent document
	 * @param element
	 *            the currently focused instruction
	 */
	public OpenWorkingCopyResourceHyperLink(IntentEditor textEditor, IRegion hyperlinkRegion,
			RepositoryAdapter repositoryAdapter, EObject element) {
		super(textEditor, hyperlinkRegion);
		this.workingCopyResourceURI = getWorkingCopyResourceURI(repositoryAdapter, element);
		if (element instanceof ExternalContentReference) {
			externalContentReference = (ExternalContentReference)element;
		}
	}

	/**
	 * Indicates if this hyperlink can be applied on the given element.
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the Intent document
	 * @param element
	 *            the element to test
	 * @return true if this hyperlink can be applied on the given element, false otherwise
	 */
	public static boolean canApply(RepositoryAdapter repositoryAdapter, EObject element) {
		return getWorkingCopyResourceURI(repositoryAdapter, element) != null;
	}

	/**
	 * Returns the URI of the working copy Resource corresponding to the given {@link ModelingUnitInstruction}
	 * . If no working copy resource is found, then we will return the URI of the compiled Resource .
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the Intent document
	 * @param element
	 *            the element to test
	 * @return the URI of the working copy Resource corresponding to the given {@link ModelingUnitInstruction}
	 */
	private static URI getWorkingCopyResourceURI(RepositoryAdapter repositoryAdapter, EObject element) {
		URI workingCopyResourceURI = null;
		// Element should be a modeling unit instruction
		if (element instanceof ResourceDeclaration) {
			workingCopyResourceURI = URI.createURI(
					((ResourceDeclaration)element).getUri().toString().replace("\"", "").trim())
					.trimFragment();
		} else {
			if (element instanceof ModelingUnitInstruction) {
				TraceabilityInformationsQuery traceabilityInformationsQuery = new TraceabilityInformationsQuery(
						repositoryAdapter);
				InstanciationInstruction correspondingInstanciationInstruction = traceabilityInformationsQuery
						.getInstanciationInstruction((ModelingUnitInstruction)element);
				if (correspondingInstanciationInstruction != null) {
					workingCopyResourceURI = traceabilityInformationsQuery.getWorkingCopyResourceURI(
							correspondingInstanciationInstruction, true);
				}
			}
		}
		return workingCopyResourceURI;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkText()
	 */
	public String getHyperlinkText() {
		return "Open Working copy Element";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#open()
	 */
	public void open() {
		boolean editorIsOpened = false;
		// Use a registered IEditorRendererExtension to open the editor (if any defined)
		if (externalContentReference != null) {
			Iterator<IEditorRendererExtension> rendererExtensions = IEditorRendererExtensionRegistry
					.getEditorRendererExtensions(externalContentReference).iterator();
			while (rendererExtensions.hasNext() && !editorIsOpened) {
				editorIsOpened = rendererExtensions.next().openEditor(externalContentReference);
			}
		}

		// If no extension was able to open the editor, use default behavior
		if (!editorIsOpened) {
			defaultOpen();
		}
	}

	/**
	 * Uses the file extension to determine the editor to open, and use an {@link URIEditorInput} to open this
	 * editor.
	 */
	protected void defaultOpen() {
		String fileExtension = workingCopyResourceURI.fileExtension();
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
				.getDefaultEditor(workingCopyResourceURI.lastSegment());
		if (desc != null) {
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.openEditor(new URIEditorInput(workingCopyResourceURI), desc.getId());
			} catch (PartInitException e) {
				IntentUiLogger.logError(e);
			}
		} else {
			IntentUiLogger.logError("Could not find editor for extension '" + fileExtension + "' ("
					+ workingCopyResourceURI + ")", new RuntimeException());
		}
	}
}
