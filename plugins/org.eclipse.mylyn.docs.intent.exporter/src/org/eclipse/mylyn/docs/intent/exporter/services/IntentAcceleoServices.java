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

import java.io.File;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceService;
import org.eclipse.mylyn.docs.intent.collab.common.query.TraceabilityInformationsQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.markup.gen.services.ImageServices;

/**
 * Regroups all services use during doc export.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentAcceleoServices {

	private static File outputFolder;

	private static String intentDocumentTitle;

	private static RepositoryAdapter repositoryAdapter;

	private static TraceabilityIndex traceabilityIndex;

	private static boolean shouldShowTableOfContents;

	private static ComposedAdapterFactory adapterFactory;

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
	 * @param any
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
		return new TraceabilityInformationsQuery(repositoryAdapter).getAllRelatedContributions(instruction);
	}

	public static TraceabilityIndex getTraceabilityIndex(RepositoryAdapter repositoryAdapter) {
		if (traceabilityIndex == null) {
			traceabilityIndex = new TraceabilityInformationsQuery(repositoryAdapter)
					.getOrCreateTraceabilityIndex();
		}
		return traceabilityIndex;
	}

	/**
	 * Indicates whether ExternalContentReference should be displayed inline or not (according to intent
	 * preferences).
	 * 
	 * @return true {@link ExternalContentReference} should be displayed inline or not (according to intent
	 *         preferences), false otherwise
	 */
	public static boolean shouldDisplayExternalRefInline() {
		return IntentPreferenceService.getBoolean(IntentPreferenceConstants.EXPORT_DISPLAY_REFERENCES_INLINE);
	}

	/**
	 * Returns the name to display for an {@link ExternalContentReference}.
	 * 
	 * @param ref
	 *            the {@link ExternalContentReference}
	 * @return the name to display for an {@link ExternalContentReference}
	 */
	public static String getName(ExternalContentReference ref) {
		String displayedName = ref.getUri().toString();
		if (ref.getExternalContent() != null) {
			Adapter labelProvider = getAdapterFactory().adapt(ref.getExternalContent(),
					IItemLabelProvider.class);
			if (labelProvider instanceof IItemLabelProvider) {
				displayedName = ((IItemLabelProvider)labelProvider).getText(ref.getExternalContent());
			}
		}
		return displayedName;
	}

	public static String getIndex(IntentStructuredElement structuredElement) {
		String index = "";
		if (structuredElement.getCompleteLevel() != null) {
			index = structuredElement.getCompleteLevel().replace(".", "_");
		}
		return index;
	}

	public static boolean shouldShowTableOfContents() {
		return shouldShowTableOfContents;
	}

	public static void initialize(String documentTitle, File generationOutputFolder,
			boolean showTableOfContents, RepositoryAdapter adapter) {
		intentDocumentTitle = documentTitle;
		outputFolder = generationOutputFolder;
		shouldShowTableOfContents = showTableOfContents;
		repositoryAdapter = adapter;
		ImageServices.setDestinationFolder(outputFolder.getAbsolutePath());
		ImageServices.setRelativeURLBase(repositoryAdapter.getRepository().getRepositoryLocation());
		ImageServices.setImageFolderRelativePath("images");
	}

	public static void dispose() {
		CopyImageUtils.dispose();
		if (adapterFactory != null) {
			adapterFactory.dispose();
		}
		adapterFactory = null;
		outputFolder = null;
		repositoryAdapter = null;
		traceabilityIndex = null;
		repositoryAdapter = null;
	}

	private static ComposedAdapterFactory getAdapterFactory() {
		if (adapterFactory == null) {
			adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}
		return adapterFactory;
	}
}
