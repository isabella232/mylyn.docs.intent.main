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

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.IntentPartitionScanner;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.external.parser.IntentExternalParserContributionRegistry;
import org.eclipse.mylyn.docs.intent.external.parser.contribution.ExternalParserCompletionProposal;
import org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser;

/**
 * Computes the completion proposal for DescriptionUnits.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
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
		// Get proposals provided by extended parsers
		proposals.addAll(getExtendedParsersCompletionProposals(getCurrentSentences()));

		// TODO propose @see ...
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	/**
	 * Get extended parser completion proposals.
	 * 
	 * @param currentSentences
	 *            Current sentence
	 * @return Completion proposals provided by extended parsers
	 */
	private Collection<? extends ICompletionProposal> getExtendedParsersCompletionProposals(
			Iterable<String> currentSentences) {
		List<ICompletionProposal> proposals = Lists.newArrayList();
		for (Iterator<IExternalParser> iterator = IntentExternalParserContributionRegistry
				.getExternalParserContributions().iterator(); iterator.hasNext();) {
			IExternalParser externalParserContribution = iterator.next();
			for (ExternalParserCompletionProposal proposal : externalParserContribution
					.getCompletionVariablesProposals(currentSentences)) {
				proposals.add(createVariableProposal(proposal));
			}
			for (ExternalParserCompletionProposal proposal : externalParserContribution
					.getCompletionTemplatesProposals(currentSentences)) {
				proposals.add(createTemplateProposalWithExternalImage(proposal.getName(),
						proposal.getDescription(), proposal.getPattern(), proposal.getImage()));
			}
		}
		return proposals;
	}

	/**
	 * Get parsed sentences.
	 * 
	 * @return Parsed sentences
	 */
	private Iterable<String> getCurrentSentences() {
		try {
			// Get the currently focused paragraph
			String text = document.get(0, offset);
			int paragraphStartOffset = -1;
			for (Pattern startPargraphPattern : IntentCompletionProcessor.PATTERNS_BY_CONTEXT) {
				paragraphStartOffset = Math.max(paragraphStartOffset,
						getLastIndexOf(text, startPargraphPattern));
			}
			if (paragraphStartOffset > -1) {
				text = text.substring(paragraphStartOffset);
			}
			return Splitter.on("\n").split(text);
		} catch (BadLocationException e) {
			// Silent catch: we return an empty collection
			return Sets.newLinkedHashSet();
		}
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
