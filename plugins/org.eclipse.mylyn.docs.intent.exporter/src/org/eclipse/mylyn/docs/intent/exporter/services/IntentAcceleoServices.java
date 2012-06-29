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
package org.eclipse.mylyn.docs.intent.exporter.services;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndexEntry;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentSubSectionContainer;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;

/**
 * Regroups all services use during doc export.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentAcceleoServices {

	private static File outputFolder;

	private static String intentDocumentTitle;

	private static RepositoryAdapter repositoryAdapter;

	/**
	 * Returns the header size to apply to the section with the given ID. For example,
	 * getHeaderSizeForSection(3_2) will return "2", getHeaderSizeForSection(4_3_2_1) will return "4".
	 * 
	 * @param sectionID
	 *            the section ID
	 * @return the header size to apply to the section with the given ID
	 */
	public static String getHeaderSizeForSection(String sectionID) {
		return String.valueOf(sectionID.split("_").length);
	}

	/**
	 * Returns the title to associate to the given IntentDocument.
	 * 
	 * @param intentDocument
	 *            the intent document
	 * @return the title to associate to the given IntentDocument
	 */
	public static String getDocumentTitle(EObject any) {
		return intentDocumentTitle;
	}

	/**
	 * Determines the image associated to the given EObject, copies it inside the exported documentation and
	 * 
	 * @param any
	 *            the eobject to get the image from
	 * @return the image associated to the given EObject
	 */
	public static String getQualifiedImageID(EObject any) {
		return CopyImageUtils.copyImageAndGetImageID(any, repositoryAdapter, outputFolder);
	}

	public static String getContainingSectionID(EObject any) {
		String ID = "";
		if (any instanceof IntentSection) {
			EObject container = any;
			while (container != null & !(container instanceof IntentDocument)) {
				if (container.eContainer() instanceof IntentSubSectionContainer) {
					ID = (((IntentSubSectionContainer)container.eContainer()).getSubSections().indexOf(
							container) + 1)
							+ "_" + ID;
				} else {
					if (container.eContainer() instanceof IntentDocument) {
						ID = (((IntentDocument)container.eContainer()).getChapters().indexOf(container) + 1)
								+ "_" + ID;
					}
				}

				container = container.eContainer();
			}
		}
		return ID.substring(0, ID.lastIndexOf("_"));
	}

	public static IntentSection getContainingSection(EObject any) {
		EObject container = any;
		if (any instanceof UnitInstruction) {
			while (container != null && !(container instanceof IntentSection)) {
				container = container.eContainer();
			}
		}
		if (container instanceof IntentSection) {
			return (IntentSection)container;
		}
		return null;
	}

	/**
	 * Returns all the {@link ContributionInstruction}s related to the given {@link UnitInstruction}.
	 * 
	 * @param instruction
	 *            the instruction to consider
	 * @return all the {@link ContributionInstruction}s related to the given {@link UnitInstruction}
	 */
	public static Collection<ContributionInstruction> getAllContributions(UnitInstruction instruction) {
		Collection<ContributionInstruction> contributionInstructions = Sets.newLinkedHashSet();
		TraceabilityIndex index = (TraceabilityIndex)repositoryAdapter
				.getResource(IntentLocations.TRACEABILITY_INFOS_INDEX_PATH).getContents().get(0);

		boolean foundContributions = false;
		for (Iterator<TraceabilityIndexEntry> iterator = index.getEntries().iterator(); iterator.hasNext()
				&& !foundContributions;) {
			TraceabilityIndexEntry entry = (TraceabilityIndexEntry)iterator.next();
			for (Iterator<Entry<EObject, EList<IntentGenericElement>>> elements = entry
					.getContainedElementToInstructions().iterator(); elements.hasNext()
					&& !foundContributions;) {

				Entry<EObject, EList<IntentGenericElement>> elementToInstruction = elements.next();
				if (elementToInstruction.getValue().contains(instruction)) {
					contributionInstructions.addAll(Sets.newLinkedHashSet(Iterables.filter(
							elementToInstruction.getValue(), ContributionInstruction.class)));
					foundContributions = true;
				}
			}
		}
		contributionInstructions.remove(instruction);
		return contributionInstructions;
	}

	public static void initialize(String documentTitle, File generationOutputFolder, RepositoryAdapter adapter) {
		intentDocumentTitle = documentTitle;
		outputFolder = generationOutputFolder;
		repositoryAdapter = adapter;
	}

	public static void dispose() {
		CopyImageUtils.dispose();
		outputFolder = null;
		repositoryAdapter = null;
	}

}
