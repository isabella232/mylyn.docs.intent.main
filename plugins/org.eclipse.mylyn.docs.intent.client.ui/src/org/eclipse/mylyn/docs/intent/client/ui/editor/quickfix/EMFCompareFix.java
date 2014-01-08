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

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.CompareUI;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.EMFCompare.Builder;
import org.eclipse.emf.compare.domain.ICompareEditingDomain;
import org.eclipse.emf.compare.domain.impl.EMFCompareEditingDomain;
import org.eclipse.emf.compare.ide.ui.internal.configuration.EMFCompareConfiguration;
import org.eclipse.emf.compare.ide.ui.internal.editor.ComparisonScopeEditorInput;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.compare.scope.IntentComparisonScope;
import org.eclipse.mylyn.docs.intent.compare.utils.EMFCompareUtils;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.swt.graphics.Image;

/**
 * Proposal used to fix a Synchronization issue by opening the compare Editor.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
@SuppressWarnings("restriction")
public class EMFCompareFix extends AbstractIntentFix {

	/**
	 * Title of the compare editor opened when applying this fix.
	 */
	private static final String COMPARE_EDITOR_TITLE = "Comparing Intent Document and Working Copy";

	/**
	 * Default constructor.
	 * 
	 * @param annotation
	 *            the annotation describing the synchronization issue.
	 */
	public EMFCompareFix(IntentAnnotation annotation) {
		super(annotation);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.AbstractIntentFix#applyFix(org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter,
	 *      org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument)
	 */
	@Override
	protected void applyFix(RepositoryAdapter repositoryAdapter, IntentEditorDocument document) {
		// Step 1: get the elements to compare
		URI workingCopyElementURI = URI.createURI(((SynchronizerCompilationStatus)syncAnnotation
				.getCompilationStatus()).getWorkingCopyResourceURI().replace("\"", ""));
		String generatedResourcePath = ((SynchronizerCompilationStatus)syncAnnotation.getCompilationStatus())
				.getCompiledResourceURI().replace("\"", "");
		EObject docElement = EcoreUtil.copy(repositoryAdapter.getResource(generatedResourcePath)
				.getContents().iterator().next());
		ResourceSetImpl rs = new ResourceSetImpl();
		Resource workingCopyResource = rs.getResource(workingCopyElementURI.trimFragment(), true);
		EObject workingCopyElement = null;
		if (workingCopyElementURI.hasFragment()) {
			workingCopyElement = workingCopyResource.getEObject(workingCopyElementURI.fragment());
		} else {
			workingCopyElement = workingCopyResource.getContents().iterator().next();
		}

		// Step 2: prepare compare dialog configuration
		final CompareConfiguration compareConfig = new IntentCompareConfiguration(workingCopyElement,
				docElement);
		ICompareEditingDomain domain = EMFCompareEditingDomain.create(workingCopyElement, docElement, null);
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		IntentComparisonScope scope = new IntentComparisonScope(workingCopyElement, docElement);
		Builder builder = EMFCompare.builder();
		builder.setMatchEngineFactoryRegistry(EMFCompareUtils.getMatchEngineNeverUsingIdentifiers());
		EMFCompare comparator = builder.build();
		CompareEditorInput input = new ComparisonScopeEditorInput(new EMFCompareConfiguration(compareConfig),
				domain, adapterFactory, comparator, scope);
		input.setTitle(COMPARE_EDITOR_TITLE + " (" + workingCopyElementURI + ")");

		// Step 3: open comparaison dialog
		CompareUI.openCompareDialog(input);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		return "See all differences in Compare Editor";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.AbstractIntentFix#getImage()
	 */
	public Image getImage() {
		return IntentEditorActivator.getDefault().getImage("icon/annotation/compare_dialog.gif");

	}
}
