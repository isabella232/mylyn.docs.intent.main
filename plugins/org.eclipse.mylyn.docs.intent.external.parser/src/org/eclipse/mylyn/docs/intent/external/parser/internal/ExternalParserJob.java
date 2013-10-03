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
package org.eclipse.mylyn.docs.intent.external.parser.internal;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.common.query.IntentDocumentQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryObjectHandler;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionBloc;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.external.parser.contribution.IExternalParser;
import org.eclipse.mylyn.docs.intent.markup.markup.BlockContent;
import org.eclipse.mylyn.docs.intent.markup.markup.Paragraph;
import org.eclipse.mylyn.docs.intent.markup.markup.StructureElement;
import org.eclipse.mylyn.docs.intent.markup.markup.Text;

/**
 * Represents a external parser operation, that parses all the description units contained in the repository.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class ExternalParserJob extends Job {
	/**
	 * Name to associate to this job.
	 */
	public static final String EXTERNAL_PARSE_JOB_NAME = "Parsing Intent Document with contibuted external parsers";

	/**
	 * The repository object handler.
	 */
	private RepositoryObjectHandler repositoryObjectHandler;

	/**
	 * The contributed {@link IExternalParser}s to call when notified of changes on the document.
	 */
	private Collection<IExternalParser> externalParserContributions;

	/**
	 * {@link ExternalParserJob} constructor.
	 * 
	 * @param repositoryObjectHandler
	 *            the repository object handler
	 * @param externalParserContributions
	 *            the contributed {@link IExternalParser}s to call when notified of changes on the document
	 */
	public ExternalParserJob(RepositoryObjectHandler repositoryObjectHandler,
			Collection<IExternalParser> externalParserContributions) {
		super(EXTERNAL_PARSE_JOB_NAME);
		this.repositoryObjectHandler = repositoryObjectHandler;
		this.externalParserContributions = externalParserContributions;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus run(final IProgressMonitor monitor) {
		final RepositoryAdapter repositoryAdapter = repositoryObjectHandler.getRepositoryAdapter();
		// Parsing
		if (repositoryAdapter != null) {
			repositoryAdapter.execute(new IntentCommand() {

				public void execute() {
					parse(monitor, repositoryAdapter);
				}

			});
		}
		IntentLogger.getInstance().log(LogType.LIFECYCLE, "[External Parsers] External parsers called");
		return Status.OK_STATUS;
	}

	/**
	 * Compile.
	 * 
	 * @param monitor
	 *            the progress monitor
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
	private void parse(final IProgressMonitor monitor, final RepositoryAdapter repositoryAdapter) {
		try {
			repositoryAdapter.openSaveContext();

			for (IExternalParser externalParserContribution : externalParserContributions) {
				externalParserContribution.init();
			}

			IntentDocumentQuery query = new IntentDocumentQuery(repositoryAdapter);
			for (DescriptionUnit descriptionUnit : query.getAllDescriptionUnits()) {
				IntentSection intentSection = null;

				EObject container = descriptionUnit;
				while (container != null && !(container instanceof IntentSection)) {
					container = container.eContainer();
				}
				if (container instanceof IntentSection) {
					intentSection = (IntentSection)container;
				}
				for (UnitInstruction instruction : descriptionUnit.getInstructions()) {
					if (instruction instanceof DescriptionBloc) {
						DescriptionBloc bloc = (DescriptionBloc)instruction;
						for (StructureElement blocElement : bloc.getDescriptionBloc().getContent()) {
							if (blocElement instanceof Paragraph) {
								parseParagraph(monitor, repositoryAdapter, intentSection,
										(Paragraph)blocElement);
							}
						}
					}
				}
			}
			for (IExternalParser externalParserContribution : externalParserContributions) {
				externalParserContribution.parsePostOperations(repositoryAdapter);
			}
			repositoryAdapter.save();
			repositoryAdapter.closeContext();
		} catch (ReadOnlyException e) {
			IntentLogger.getInstance().logError(e);
		} catch (SaveException e) {
			IntentLogger.getInstance().logError(e);
		}
	}

	/**
	 * Parse.
	 * 
	 * @param monitor
	 *            the progress monitor
	 * @param repositoryAdapter
	 *            the repository adapter
	 * @param intentSection
	 *            the intent section
	 * @param paragraph
	 *            the paragraph to parse
	 */
	private void parseParagraph(IProgressMonitor monitor, RepositoryAdapter repositoryAdapter,
			IntentSection intentSection, Paragraph paragraph) {
		StringBuffer text = new StringBuffer();
		boolean isVariable = false;
		for (BlockContent element : paragraph.getContent()) {
			if (element instanceof Text) {
				String data = ((Text)element).getData();
				if ("#8249".equals(data)) {
					text.append("'");
					isVariable = true;
				} else if ("#8250".equals(data)) {
					text.append("'");
					isVariable = false;
				} else if (data.endsWith("$") || isVariable) {
					text.append(data);
					isVariable = false;
				} else {
					text.append(data + "\n");
					isVariable = false;
				}
			}
		}
		for (IExternalParser externalParserContribution : externalParserContributions) {
			externalParserContribution.parse(intentSection, text.toString());
		}
	}

}
