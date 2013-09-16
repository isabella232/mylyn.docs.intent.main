/*******************************************************************************
 * Copyright (c) 2011 Obeo.
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
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentPairMatcher;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.IntentPartitionScanner;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;

/**
 * Computes the completion proposals.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentCompletionProcessor extends AbstractIntentCompletionProcessor {

	// Patterns by contexts.
	/**
	 * All patterns allowing to detect the current context.
	 */
	public static final Pattern[] PATTERNS_BY_CONTEXT = new Pattern[] {
			Pattern.compile("Document\\s+[^{\r\n]*\\{"), Pattern.compile("Chapter\\s+[^{\r\n]*\\{"),
			Pattern.compile("Section\\s+[^{\r\n]*\\{"),
	};

	// Keywords by contexts.
	// CHECKSTYLE:OFF Keywords have the same name than templates names & templates description, but merging
	// their declaration have no sense.
	private static final String[][] KEYWORDS_BY_CONTEXT = new String[][] {
			// Document-level keywords
			new String[] {"Chapter",
			},
			// Chapter-level keywords
			new String[] {"Section",
			},
			// Section-level keywords
			new String[] {"Section", "@M",
			},
	};

	// CHECKSTYLE:ON

	// Accurate contexts.
	/**
	 * Constant representing default context.
	 */
	private static final int NULL_CONTEXT = -1;

	/**
	 * Constant representing document context.
	 */
	private static final int DOCUMENT_CONTEXT = 0;

	/**
	 * Constant representing chapter context.
	 */
	private static final int CHAPTER_CONTEXT = 1;

	/**
	 * Constant representing section context.
	 */
	private static final int SECTION_CONTEXT = 2;

	/**
	 * Current context ID.
	 */
	private int accurateContext;

	/**
	 * Block matcher used to determine contexts.
	 */
	private IntentPairMatcher blockMatcher;

	/**
	 * Completion processor for pure documentation zones.
	 */
	private MarkupCompletionProcessor markupCompletionProcessor;

	/**
	 * Completion processor for description units.
	 */
	private DescriptionUnitCompletionProcessor descriptionUnitCompletionProcessor;

	/**
	 * Creates a new {@link IntentCompletionProcessor} with the given {@link IntentPairMatcher}.
	 * 
	 * @param matcher
	 *            the block matcher
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} used to interact with Intent repository
	 */
	public IntentCompletionProcessor(IntentPairMatcher matcher, RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
		this.blockMatcher = matcher;
		this.markupCompletionProcessor = new MarkupCompletionProcessor(repositoryAdapter);
		this.descriptionUnitCompletionProcessor = new DescriptionUnitCompletionProcessor(repositoryAdapter);
	}

	/**
	 * Computes the context at the current offset.
	 * 
	 * @throws BadLocationException
	 *             if the current offset is incorrect
	 */
	private void computeAccurateContext() throws BadLocationException {
		int[] offsetsByContextType = new int[3];
		final String startText = document.get(0, offset);
		for (int i = 0; i < PATTERNS_BY_CONTEXT.length; i++) {
			offsetsByContextType[i] = getLastIndexOf(startText, PATTERNS_BY_CONTEXT[i]);
		}
		int res = NULL_CONTEXT;
		int maxValue = -1;
		for (int i = 0; i < offsetsByContextType.length; i++) {
			if (offsetsByContextType[i] > maxValue) {
				IRegion region = blockMatcher.match(document, offsetsByContextType[i]);
				if (region != null && region.getOffset() + region.getLength() > offset) {
					maxValue = offsetsByContextType[i];
					res = i;
				}
			}
		}
		accurateContext = res;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.completion.AbstractIntentCompletionProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int currentOffset) {
		ArrayList<ICompletionProposal> proposals = Lists.newArrayList();
		proposals.addAll(Lists.newArrayList(super.computeCompletionProposals(viewer, currentOffset)));
		proposals.addAll(Lists.newArrayList(descriptionUnitCompletionProcessor.computeCompletionProposals(
				viewer, currentOffset)));
		proposals.addAll(Lists.newArrayList(markupCompletionProcessor.computeCompletionProposals(viewer,
				currentOffset)));
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	/**
	 * Computes the completion proposals.
	 * 
	 * @return the completion proposals
	 */
	protected ICompletionProposal[] computeCompletionProposals() {
		try {
			computeAccurateContext();
		} catch (BadLocationException e) {
			IntentUiLogger.logError(e);
		}
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		proposals.addAll(createKeyWordsProposals());
		proposals.addAll(createTemplatesProposals());
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	/**
	 * Creates the keywords proposals.
	 * 
	 * @return the keywords proposals
	 */
	protected Collection<ICompletionProposal> createKeyWordsProposals() {
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		if (accurateContext >= 0) {
			for (String keyword : KEYWORDS_BY_CONTEXT[accurateContext]) {
				if (!"".equals(start) && keyword.startsWith(start)) {
					proposals.add(createKeyWordProposal(keyword));
				}
			}
		}
		return proposals;
	}

	/**
	 * Creates the templates proposals.
	 * 
	 * @return the templates proposals
	 */
	protected List<TemplateProposal> createTemplatesProposals() {
		TemplateProposal chapterProposal = createTemplateProposal("Chapter", "Chapter",
				"Chapter ${Title} {\n\t${Text}\n}\n", "icon/outline/chapter.gif");
		TemplateProposal sectionProposal = createTemplateProposal("Section", "Section",
				"Section ${Title} {\n\t${Text}\n}\n", "icon/outline/section.gif");
		TemplateProposal modelingUnitProposal = createTemplateProposal("Modeling Unit", "Modeling Unit",
				"@M\n${Code}\nM@\n", "icon/outline/modelingunit.png");

		List<TemplateProposal> proposals = new ArrayList<TemplateProposal>();

		switch (accurateContext) {
			case DOCUMENT_CONTEXT:
				proposals.add(chapterProposal);
				break;
			case CHAPTER_CONTEXT:
				proposals.add(sectionProposal);
				break;
			case SECTION_CONTEXT:
				proposals.add(sectionProposal);
				proposals.add(modelingUnitProposal);
				break;
			default:
				break;
		}

		return proposals;
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
