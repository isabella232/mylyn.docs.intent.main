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
package org.eclipse.mylyn.docs.intent.external.parser.contribution;

import java.util.List;

import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;

/**
 * A parser which is called any time a section of interest for this parser is modified, and provides
 * additional behavior. For example, you can automatically create Java files or models out of a description in
 * natural langage.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public interface IExternalParser {

	/**
	 * Init parser.
	 */
	void init();

	/**
	 * Parse.
	 * 
	 * @param intentSection
	 *            Intent section parsed
	 * @param descriptionUnitToParse
	 *            The description unit to parse
	 */
	void parse(IntentSection intentSection, String descriptionUnitToParse);

	/**
	 * Post operations launched after the parse operation.
	 * 
	 * @param repositoryAdapter
	 *            Repository adapter
	 */
	void parsePostOperations(RepositoryAdapter repositoryAdapter);

	/**
	 * Get completion variables proposal.
	 * 
	 * @param currentSentences
	 *            Current parsed sentences
	 * @return List of completion proposals
	 */
	List<ExternalParserCompletionProposal> getCompletionVariablesProposals(Iterable<String> currentSentences);

	/**
	 * Get completion templates proposal.
	 * 
	 * @param currentSentences
	 *            Current parsed sentences
	 * @return List of completion templates
	 */
	List<ExternalParserCompletionProposal> getCompletionTemplatesProposals(Iterable<String> currentSentences);
}
