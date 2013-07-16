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
package org.eclipse.mylyn.docs.intent.client.ui.editor.completion;

import com.google.common.collect.Lists;

import java.util.ArrayList;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.IntentPartitionScanner;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;

/**
 * Computes the completion proposal for DescriptionUnits.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DescriptionUnitCompletionProcessor extends AbstractIntentCompletionProcessor {

	/**
	 * Default constructor.
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} used to interact with Intent repository
	 */
	public DescriptionUnitCompletionProcessor(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.completion.AbstractIntentCompletionProcessor#computeCompletionProposals()
	 */
	@Override
	protected ICompletionProposal[] computeCompletionProposals() {
		ArrayList<ICompletionProposal> proposals = Lists.newArrayList();
		// TODO propose @see ...
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.completion.AbstractIntentCompletionProcessor#getContextType()
	 */
	@Override
	public String getContextType() {
		return IntentPartitionScanner.INTENT_DESCRIPTIONUNIT;
	}

}
