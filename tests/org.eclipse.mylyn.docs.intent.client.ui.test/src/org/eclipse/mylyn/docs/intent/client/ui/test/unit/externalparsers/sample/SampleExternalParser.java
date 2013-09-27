/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.externalparsers.sample;

import com.google.common.collect.Lists;

import java.util.List;

import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.external.parser.contribution.ExternalParserCompletionProposal;
import org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser;

/**
 * {@link IExternalParser} used for tests.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class SampleExternalParser implements IExternalParser {

	/**
	 * Id of this parser, used to log messages.
	 */
	public static final String SAMPLE_EXTERNAL_PARSER_ID = "Sample External Parser";

	/**
	 * Default constructor.
	 */
	public SampleExternalParser() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser#init()
	 */
	public void init() {
		IntentLogger.getInstance().log(LogType.LIFECYCLE, "[" + SAMPLE_EXTERNAL_PARSER_ID + "] initialized");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser#parse(org.eclipse.mylyn.docs.intent.core.document.IntentSection,
	 *      java.lang.String)
	 */
	public void parse(IntentSection intentSection, String descriptionUnitToParse) {

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser#parsePostOperations(org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter)
	 */
	public void parsePostOperations(RepositoryAdapter repositoryAdapter) {

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser#getCompletionVariablesProposals(java.lang.Iterable)
	 */
	public List<ExternalParserCompletionProposal> getCompletionVariablesProposals(
			Iterable<String> currentSentences) {
		return Lists.newArrayList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser#getCompletionTemplatesProposals(java.lang.Iterable)
	 */
	public List<ExternalParserCompletionProposal> getCompletionTemplatesProposals(
			Iterable<String> currentSentences) {
		return Lists.newArrayList();
	}

}
