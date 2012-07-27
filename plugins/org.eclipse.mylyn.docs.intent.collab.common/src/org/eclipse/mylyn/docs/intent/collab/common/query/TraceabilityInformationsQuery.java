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
package org.eclipse.mylyn.docs.intent.collab.common.query;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndexEntry;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction;

/**
 * An utility class allowing to query the {@link TraceabilityIndex} to get useful traceability informations.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TraceabilityInformationsQuery extends AbstractIntentQuery {

	private TraceabilityIndex traceabilityIndex;

	/**
	 * Creates the query.
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the repository.
	 */
	public TraceabilityInformationsQuery(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * Returns the {@link TraceabilityIndex} of the queried repository. If none find, creates it.
	 * 
	 * @return the {@link TraceabilityIndex} index of the queried repository. If none find, creates it
	 */
	public TraceabilityIndex getOrCreateTraceabilityIndex() {
		if (traceabilityIndex == null) {
			Resource traceabilityResource;
			try {
				traceabilityResource = repositoryAdapter
						.getOrCreateResource(IntentLocations.TRACEABILITY_INFOS_INDEX_PATH);

				if (traceabilityResource.getContents().isEmpty()) {
					traceabilityResource.getContents().add(
							CompilerFactory.eINSTANCE.createTraceabilityIndex());
				}
				traceabilityIndex = (TraceabilityIndex)traceabilityResource.getContents().get(0);
			} catch (ReadOnlyException e) {
				throw new RuntimeException(e);
			}
		}
		return traceabilityIndex;
	}

	/**
	 * Returns all the {@link ContributionInstruction}s related to the same element than the given
	 * {@link UnitInstruction}.
	 * 
	 * @param instruction
	 *            the instruction to consider (can be whether an InstanciationInstruction or a
	 *            {@link ContributionInstruction}.
	 * @return all the {@link ContributionInstruction}s related to the given {@link UnitInstruction}
	 */
	public Collection<ContributionInstruction> getAllRelatedContributions(UnitInstruction instruction) {
		return Sets.newLinkedHashSet(Iterables.filter(getAllRelatedInstructions(instruction),
				ContributionInstruction.class));
	}

	/**
	 * Returns the instanciation associated to the given element.
	 * 
	 * @param instance
	 *            the instance element
	 * @return the instanciation associated to the given element
	 */
	public InstanciationInstruction getInstanciation(EObject instance) {
		for (TraceabilityIndexEntry entry : getOrCreateTraceabilityIndex().getEntries()) {
			EList<InstructionTraceabilityEntry> instructions = entry.getContainedElementToInstructions().get(
					instance);
			if (instructions != null) {
				for (InstructionTraceabilityEntry instructionTraceabilityEntry : instructions) {
					if (instructionTraceabilityEntry.getInstruction() instanceof InstanciationInstruction) {
						return (InstanciationInstruction)instructionTraceabilityEntry.getInstruction();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Returns the instance associated to the given instanciation.
	 * 
	 * @param instanciation
	 *            the instanciation
	 * @return the instance associated to the given instanciation
	 */
	public EObject getInstance(InstanciationInstruction instanciation) {
		for (TraceabilityIndexEntry entry : getOrCreateTraceabilityIndex().getEntries()) {
			for (Entry<EObject, EList<InstructionTraceabilityEntry>> instructionsEntry : entry
					.getContainedElementToInstructions()) {
				for (InstructionTraceabilityEntry instructionTraceabilityEntry : instructionsEntry.getValue()) {
					if (instructionTraceabilityEntry.getInstruction().equals(instanciation)) {
						return instructionsEntry.getKey();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Returns all the {@link ModelingUnitInstruction}s related to the same element than the given
	 * {@link UnitInstruction}.
	 * 
	 * @param instruction
	 *            the instruction to consider (can be whether an InstanciationInstruction or a
	 *            {@link ContributionInstruction}.
	 * @return all the {@link ContributionInstruction}s related to the given {@link UnitInstruction}
	 */
	private Collection<ModelingUnitInstruction> getAllRelatedInstructions(UnitInstruction instruction) {
		Collection<ModelingUnitInstruction> relatedInstructions = Sets.newLinkedHashSet();
		boolean foundContributions = false;
		for (Iterator<TraceabilityIndexEntry> traceabilityIterator = getOrCreateTraceabilityIndex()
				.getEntries().iterator(); traceabilityIterator.hasNext() && !foundContributions;) {
			TraceabilityIndexEntry entry = (TraceabilityIndexEntry)traceabilityIterator.next();

			for (Iterator<Entry<EObject, EList<InstructionTraceabilityEntry>>> iterator = entry
					.getContainedElementToInstructions().iterator(); iterator.hasNext()
					&& !foundContributions;) {
				Entry<EObject, EList<InstructionTraceabilityEntry>> element = (Entry<EObject, EList<InstructionTraceabilityEntry>>)iterator
						.next();

				final Set<IntentGenericElement> instructions = new HashSet<IntentGenericElement>(element
						.getValue().size());
				for (InstructionTraceabilityEntry instructionEntry : element.getValue()) {
					instructions.add(instructionEntry.getInstruction());
				}

				if (instructions.contains(instruction)) {
					relatedInstructions.addAll(Sets.newLinkedHashSet(Iterables.filter(instructions,
							ContributionInstruction.class)));
					foundContributions = true;
				}
			}

		}
		return relatedInstructions;
	}

}
