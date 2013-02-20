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

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.TextInvocationContext;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationFactory;
import org.eclipse.mylyn.docs.intent.client.ui.internal.quickfix.provider.IntentQuickFixProviderRegistry;
import org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState;

/**
 * {@link IntentQuickAssistProcessor} used by Intent to fix any issues.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentQuickAssistProcessor implements IQuickAssistProcessor {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.quickassist.QuickAssistAssistant#canFix(org.eclipse.jface.text.source.Annotation)
	 */
	public boolean canFix(Annotation annotation) {
		return IntentAnnotationFactory.INTENT_ANNOT_SYNC_WARNING.equals(annotation.getType())
				|| IntentAnnotationFactory.INTENT_ANNOT_COMPILER_WARNING.equals(annotation.getType());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.quickassist.IQuickAssistProcessor#computeQuickAssistProposals(org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext)
	 */
	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext quickAssistContext) {
		// Step 1: get relevant annotation to fixe
		Collection<IntentAnnotation> synchronizationIssues = Sets.newLinkedHashSet();
		Collection<IntentAnnotation> validationIssues = Sets.newLinkedHashSet();
		ISourceViewer viewer = quickAssistContext.getSourceViewer();
		int documentOffset = quickAssistContext.getOffset();
		int length = viewer.getSelectedRange().y;
		TextInvocationContext context = new TextInvocationContext(viewer, documentOffset, length);

		IAnnotationModel model = viewer.getAnnotationModel();

		int offset = context.getOffset();
		Iterator<?> iter = model.getAnnotationIterator();
		while (iter.hasNext()) {
			Annotation annotation = (Annotation)iter.next();
			if (canFix(annotation) && annotation instanceof IntentAnnotation) {
				Position pos = model.getPosition(annotation);
				if (pos != null && pos.includes(offset)) {

					if (((IntentAnnotation)annotation).getCompilationStatus() instanceof SynchronizerCompilationStatus) {
						synchronizationIssues.add((IntentAnnotation)annotation);
					} else {
						validationIssues.add((IntentAnnotation)annotation);
					}
				}
			}
		}

		// Step 2: compute proposals
		List<ICompletionProposal> proposals = Lists.newArrayList();
		// Automatically compute quick-fixes for Synchronization issues
		for (IntentAnnotation annotation : synchronizationIssues) {
			proposals.addAll(computeProposalsFromSynchronizationIssue(annotation));
		}

		// Adding all provided quick-fixes
		for (IntentAnnotation annotation : validationIssues) {
			proposals.addAll(computeProposalsFromValidationIssue(annotation));
		}

		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	/**
	 * Returns the proposals according to the given annotation containing a synchronization status.
	 * 
	 * @param annotation
	 *            the annotation containing a synchronization status
	 * @return the proposals according to the given annotation containing a synchronization status
	 */
	private List<ICompletionProposal> computeProposalsFromSynchronizationIssue(IntentAnnotation annotation) {
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		SynchronizerCompilationStatus status = (SynchronizerCompilationStatus)annotation
				.getCompilationStatus();
		if (status instanceof ResourceChangeStatus) {
			ResourceChangeStatus resourceChangeStatus = (ResourceChangeStatus)status;
			SynchronizerResourceState compiledResourceState = resourceChangeStatus.getCompiledResourceState();
			SynchronizerResourceState workingCopyResourceState = resourceChangeStatus
					.getWorkingCopyResourceState();

			if (SynchronizerResourceState.EMPTY.equals(compiledResourceState)) {
				proposals.add(new ClearResourceFix(annotation));
			} else if (SynchronizerResourceState.EMPTY.equals(workingCopyResourceState)) {
				proposals.add(new MergeEmptyResourceFix(annotation));
			} else if (SynchronizerResourceState.NULL.equals(workingCopyResourceState)) {
				proposals.add(new CreateResourceFix(annotation));
			}
		} else {
			proposals.add(new EMFCompareFix(annotation));
			proposals.add(new UpdateModelingUnitFix(annotation));
		}
		return proposals;
	}

	/**
	 * Returns all Quick-fixes provided through extension point and appliable in the context of the given
	 * {@link IntentAnnotation}.
	 * 
	 * @param annotation
	 *            the {@link IntentAnnotation} helding the issue to fix
	 * @return all Quick-fixes provided through extension point and appliable in the context of the given
	 *         {@link IntentAnnotation}
	 */
	private Collection<? extends ICompletionProposal> computeProposalsFromValidationIssue(
			IntentAnnotation annotation) {
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		for (AbstractIntentFix intentFix : IntentQuickFixProviderRegistry.getAppliableIntentFixes(annotation)) {
			proposals.add(intentFix);
		}
		return proposals;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.quickassist.IQuickAssistProcessor#canAssist(org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext)
	 */
	public boolean canAssist(IQuickAssistInvocationContext invocationContext) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.quickassist.IQuickAssistProcessor#getErrorMessage()
	 */
	public String getErrorMessage() {
		return null;
	}
}
