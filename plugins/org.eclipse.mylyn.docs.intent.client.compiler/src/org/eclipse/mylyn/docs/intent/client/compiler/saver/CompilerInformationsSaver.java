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
package org.eclipse.mylyn.docs.intent.client.compiler.saver;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.client.compiler.utils.IntentCompilerInformationHolder;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.common.query.CompilationStatusQuery;
import org.eclipse.mylyn.docs.intent.collab.common.query.TraceabilityInformationsQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryObjectHandler;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationMessageType;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusManager;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusSeverity;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndexEntry;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.document.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AbstractValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;

/**
 * Save all the compilation informations on the repository.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class CompilerInformationsSaver {

	/**
	 * A map associated each {@link ResourceDeclaration} with the corresponding {@link IntentGenericElement}s.
	 */
	private Map<ResourceDeclaration, Map<EObject, Collection<IntentGenericElement>>> resourceToTraceabilityElementIndexEntry;

	/**
	 * Progress monitor allowing to cancel a save operation if another notification has been received by the
	 * CompilerRepositoryClient.
	 */
	private IProgressMonitor progressMonitor;

	/**
	 * A {@link TraceabilityInformationsQuery} allowing to get traceability-related information.
	 */
	private TraceabilityInformationsQuery traceabilityInfoQuery;

	/**
	 * A {@link CompilationStatusQuery} allowing to get {@link CompilationStatus}-related information.
	 */
	private CompilationStatusQuery statusQuery;

	/**
	 * Default constructor.
	 * 
	 * @param progressMonitor
	 *            the progress monitor to use
	 */
	public CompilerInformationsSaver(IProgressMonitor progressMonitor) {
		this.progressMonitor = progressMonitor;
	}

	/**
	 * Save the useful informations contained in the given IntentCompilerInformationHolder on the repository,
	 * merging with the current repository content.
	 * 
	 * @param informationHolder
	 *            the informationHolder containing the informations to put on the repository
	 * @param handler
	 *            the repositoryObjectHandler to use for communicating with the repository
	 */
	public void saveOnRepository(IntentCompilerInformationHolder informationHolder,
			RepositoryObjectHandler handler) {

		this.traceabilityInfoQuery = new TraceabilityInformationsQuery(handler.getRepositoryAdapter());
		this.statusQuery = new CompilationStatusQuery(handler.getRepositoryAdapter());
		resourceToTraceabilityElementIndexEntry = Maps.newLinkedHashMap();
		try {

			// We first save the generated elements
			if (!progressMonitor.isCanceled()) {
				Map<ResourceDeclaration, String> resourceToGeneratedPath = saveGeneratedResources(
						informationHolder, handler);

				// We also save the status informations.
				if (!progressMonitor.isCanceled()) {
					saveStatusInformations(informationHolder, handler);
				}

				// We finally save the traceability informations
				if (!progressMonitor.isCanceled()) {
					saveTraceabilityInformations(resourceToGeneratedPath, handler, informationHolder);
				}
			}
			// CHECKSTYLE:OFF : for now on we would like to print any exception
		} catch (Exception e) {
			IntentLogger.getInstance().log(LogType.ERROR, "Compiler failed to save changes", e);
			try {
				handler.getRepositoryAdapter().undo();
			} catch (ReadOnlyException e1) {
				IntentLogger.getInstance().log(LogType.ERROR, "Compiler failed to save changes", e);
			}
		}
	}

	/**
	 * Save all the generated elements into internal Repository resources.
	 * 
	 * @param informationHolder
	 *            the informationHolder containing the information to save
	 * @param handler
	 *            the handler to use for saving on the repository
	 * @return a mapping between each resource declaration and the path where it has been saved.
	 * @throws ReadOnlyException
	 *             if the current context is read-only
	 */
	private Map<ResourceDeclaration, String> saveGeneratedResources(
			IntentCompilerInformationHolder informationHolder, RepositoryObjectHandler handler)
			throws ReadOnlyException {

		Map<ResourceDeclaration, String> resourceInfos = new HashMap<ResourceDeclaration, String>();
		// We save each generated resource in a CDOResource
		for (ResourceDeclaration resource : informationHolder.getDeclaredResources()) {
			if (!progressMonitor.isCanceled()) {
				String internalResourcePath = getInternalResourcePath(resource);
				Resource generatedResource = handler.getRepositoryAdapter().getOrCreateResource(
						internalResourcePath);
				resourceInfos.put(resource, internalResourcePath);
				generatedResource.getContents().clear();

				// We update the resourceToTraceabilityElementIndexEntry map using this resource content
				updateTraceabilityFromResourceContent(resource, informationHolder,
						informationHolder.getResourceContent(resource));
				generatedResource.getContents().addAll(informationHolder.getResourceContent(resource));
			}
		}
		return resourceInfos;
	}

	/**
	 * Associate the given resource with a mapping associating its contained compiled element with the
	 * instruction that created those elements.
	 * 
	 * @param resource
	 *            the resource to consider
	 * @param informationHolder
	 *            the informationHolder containing the informations needed
	 * @param elementsToConsider
	 *            a list of generated element contained in the given resource
	 */
	private void updateTraceabilityFromResourceContent(ResourceDeclaration resource,
			IntentCompilerInformationHolder informationHolder, EList<EObject> elementsToConsider) {

		// For each element contained in the resource
		for (EObject element : elementsToConsider) {
			if (!progressMonitor.isCanceled()) {
				// We add an entry to the traceability map
				if (resourceToTraceabilityElementIndexEntry.get(resource) == null) {
					resourceToTraceabilityElementIndexEntry.put(resource,
							Maps.<EObject, Collection<IntentGenericElement>> newLinkedHashMap());
				}

				// We get the instructions that defined or contributed to this element
				Collection<UnitInstruction> instructions = informationHolder
						.getAllInstructionsByCreatedElement(element);

				if (instructions != null && !instructions.isEmpty()) {

					if (resourceToTraceabilityElementIndexEntry.get(resource).get(element) == null) {
						resourceToTraceabilityElementIndexEntry.get(resource).put(element,
								Sets.<IntentGenericElement> newLinkedHashSet());
					}
					resourceToTraceabilityElementIndexEntry.get(resource).get(element).addAll(instructions);

					// We do the same for each contained element
					updateTraceabilityFromResourceContent(resource, informationHolder, element.eContents());
				}
			}
		}
	}

	/**
	 * Save all the status informations related to the generated elements.
	 * 
	 * @param informationHolder
	 *            the informationHolder containing the information to save
	 * @param handler
	 *            the handler to use for saving on the repository
	 * @throws ReadOnlyException
	 *             if the current context is read-only
	 */
	private void saveStatusInformations(IntentCompilerInformationHolder informationHolder,
			RepositoryObjectHandler handler) throws ReadOnlyException {
		CompilationStatusManager manager = statusQuery.getOrCreateCompilationStatusManager();
		if (!progressMonitor.isCanceled()) {
			mergeCompilationStatusManager(informationHolder.getStatusManager(), manager, manager.eResource());
		}
	}

	/**
	 * Save all the traceability informations.
	 * 
	 * @param resourceToGeneratedPath
	 *            the list of the resourceDeclaration associate to the internal path where it has been
	 *            generated
	 * @param handler
	 *            the handler to use for saving on the repository
	 * @param informationHolder
	 * @throws ReadOnlyException
	 *             if the current context is read-only
	 */
	private void saveTraceabilityInformations(Map<ResourceDeclaration, String> resourceToGeneratedPath,
			RepositoryObjectHandler handler, IntentCompilerInformationHolder informationHolder)
			throws ReadOnlyException {

		// We first get the Traceability index
		TraceabilityIndex traceIndex = traceabilityInfoQuery.getOrCreateTraceabilityIndex();

		List<TraceabilityIndexEntry> newTraceabilityEntries = new ArrayList<TraceabilityIndexEntry>();
		Set<IntentGenericElement> handledInstructions = Sets.newLinkedHashSet();

		// For each compiled resource
		for (Entry<ResourceDeclaration, String> resourceToGeneratedPathEntry : resourceToGeneratedPath
				.entrySet()) {
			ResourceDeclaration resourceDeclaration = resourceToGeneratedPathEntry.getKey();
			String resourcePath = resourceToGeneratedPathEntry.getValue();

			// We create a traceability entry
			TraceabilityIndexEntry entry = CompilerFactory.eINSTANCE.createTraceabilityIndexEntry();
			entry.setCompilationTime(BigInteger.valueOf(System.currentTimeMillis()));
			entry.setGeneratedResourcePath(resourcePath);
			entry.setResourceDeclaration(resourceDeclaration);
			EMap<EObject, EList<InstructionTraceabilityEntry>> entryElementsMap = entry
					.getContainedElementToInstructions();

			// For each entry, we define a mapping between contained elements and instructions
			if (resourceToTraceabilityElementIndexEntry.get(resourceDeclaration) != null) {
				for (Entry<EObject, Collection<IntentGenericElement>> traceabilityEntry : resourceToTraceabilityElementIndexEntry
						.get(resourceDeclaration).entrySet()) {
					entryElementsMap.put(traceabilityEntry.getKey(),
							new BasicEList<InstructionTraceabilityEntry>());
					for (IntentGenericElement intentGenericElement : traceabilityEntry.getValue()) {
						InstructionTraceabilityEntry instructionEntry = CompilerFactory.eINSTANCE
								.createInstructionTraceabilityEntry();
						instructionEntry.setInstruction(intentGenericElement);
						instructionEntry.getFeatures().putAll(getAffectations(intentGenericElement));
						entryElementsMap.get(traceabilityEntry.getKey()).add(instructionEntry);
					}

					handledInstructions.addAll(traceabilityEntry.getValue());
				}
			}
			newTraceabilityEntries.add(entry);
		}

		// We also define an entry for instantiation instructions that are not referenced inside
		// a Resource Declaration (useful for completion for example)
		SetView<UnitInstruction> instanciationsInstructionNotContainedInResource = Sets.difference(
				informationHolder.getAllInstanciationsInstructions(), handledInstructions);
		if (!instanciationsInstructionNotContainedInResource.isEmpty()) {
			TraceabilityIndexEntry entry = CompilerFactory.eINSTANCE.createTraceabilityIndexEntry();
			entry.setCompilationTime(BigInteger.valueOf(System.currentTimeMillis()));

			for (UnitInstruction instruction : instanciationsInstructionNotContainedInResource) {
				entry.getContainedElementToInstructions().put(instruction,
						new BasicEList<InstructionTraceabilityEntry>());
				InstructionTraceabilityEntry instructionEntry = CompilerFactory.eINSTANCE
						.createInstructionTraceabilityEntry();
				instructionEntry.setInstruction(instruction);
				instructionEntry.getFeatures().putAll(getAffectations(instruction));
				entry.getContainedElementToInstructions().get(instruction).add(instructionEntry);
			}
			newTraceabilityEntries.add(entry);
		}
		traceIndex.getEntries().clear();
		traceIndex.getEntries().addAll(newTraceabilityEntries);
	}

	/**
	 * Returns the affectations declared by the given element.
	 * 
	 * @param intentGenericElement
	 *            the element
	 * @return the affectations declared by the given element
	 */
	private BasicEMap<String, EList<AbstractValue>> getAffectations(IntentGenericElement intentGenericElement) {
		BasicEMap<String, EList<AbstractValue>> affectations = new BasicEMap<String, EList<AbstractValue>>();
		if (intentGenericElement instanceof InstanciationInstruction) {
			InstanciationInstruction instanciation = (InstanciationInstruction)intentGenericElement;
			for (StructuralFeatureAffectation affectation : instanciation.getStructuralFeatures()) {
				includeValues(affectations, affectation);
			}

		} else if (intentGenericElement instanceof ContributionInstruction) {
			ContributionInstruction contribution = (ContributionInstruction)intentGenericElement;
			for (ModelingUnitInstruction instruction : contribution.getContributions()) {
				if (instruction instanceof StructuralFeatureAffectation) {
					includeValues(affectations, (StructuralFeatureAffectation)instruction);
				}
			}
		}
		return affectations;
	}

	/**
	 * Includes the affectation values into the map.
	 * 
	 * @param affectations
	 *            the existing map
	 * @param affectation
	 *            the new affectation to include values from
	 */
	private void includeValues(BasicEMap<String, EList<AbstractValue>> affectations,
			StructuralFeatureAffectation affectation) {
		EList<AbstractValue> existing = affectations.get(affectation.getName());
		if (existing == null) {
			existing = new BasicEList<AbstractValue>();
		}
		existing.addAll(affectation.getValues());
		affectations.put(affectation.getName(), existing);
	}

	/**
	 * Returns the internal path corresponding to the given resource (i.e the path where the generated
	 * resource will be located).
	 * 
	 * @param resource
	 *            is the resource
	 * @return the internal path corresponding to the given resource (i.e the path where the generated
	 *         resource will be located)
	 */
	private String getInternalResourcePath(ResourceDeclaration resource) {
		String resourcePath = null;
		// If the resource is not associated to any URI
		if (resource.getUri() == null) {
			// We try to use its name to compute the internal path
			String resourceName = resource.getName();
			if (resourceName != null && resourceName.length() > 0) {
				resourcePath = resourceName;
			} else {
				// We add an error and return a sample name
				CompilationStatus status = CompilerFactory.eINSTANCE.createCompilationStatus();
				status.setSeverity(CompilationStatusSeverity.ERROR);
				status.setTarget(resource);
				status.setType(CompilationMessageType.VALIDATION_ERROR);
				status.setMessage("As this resource has no URI, it should have a name to be identifed.");
				resource.getCompilationStatus().add(status);
				return "unnamed-resource.xmi";
			}
		} else {
			// Here a concrete URI has been associated to this resource
			resourcePath = resource.getUri().toString();
			// we get the last Segment of this URI to compute the internal path
			if (resourcePath.contains("/")) {
				if (resourcePath.contains("#")) {
					String pathWithoutFragment = resourcePath.substring(0, resourcePath.lastIndexOf('#'));
					resourcePath = pathWithoutFragment.substring(pathWithoutFragment.lastIndexOf('/') + 1)
							+ "_"
							+ resourcePath.substring(resourcePath.lastIndexOf('#') + 1).replace("/", "@");
				} else {
					resourcePath = resourcePath.substring(resourcePath.lastIndexOf('/') + 1);
				}
			}
		}
		// Removing invalid characters
		resourcePath = resourcePath.replace("*", "").replace("?", "-");
		resourcePath = IntentLocations.GENERATED_RESOURCES_FOLDER_PATH + resourcePath;
		return resourcePath;
	}

	/**
	 * Returns true if the given compilationSatus is contained in the given CompilationStatus list (a status
	 * is contained in a status list if this list has a status with the same target, type and message).
	 * 
	 * @param statusList
	 *            the list of status to inspect
	 * @param status
	 *            the status which we want to know whether is contained in this list or not
	 * @return true if the given status is contained in the given list, false otherwise
	 */
	private boolean isContainedCompilationStatus(EList<CompilationStatus> statusList, CompilationStatus status) {
		boolean statusIsContainedInList = false;
		if (statusList != null) {
			Iterator<CompilationStatus> iterator = statusList.iterator();
			while (iterator.hasNext() && !statusIsContainedInList) {
				CompilationStatus containedStatus = iterator.next();
				statusIsContainedInList = isSimilarStatus(containedStatus, status);
			}
		}
		return statusIsContainedInList;
	}

	/**
	 * Indicates if the two given status have the same value for each attribute.
	 * 
	 * @param containedStatus
	 *            the first status to compare with the second one
	 * @param status
	 *            the second status to compare with the first one
	 * @return true if the two given status have the same value for each attribute, false otherwise.
	 */
	private boolean isSimilarStatus(CompilationStatus containedStatus, CompilationStatus status) {
		try {
			return containedStatus.getMessage().equals(status.getMessage())
					&& containedStatus.getSeverity().equals(status.getSeverity())
					&& containedStatus.getType().equals(status.getType());
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * Merge the local statusManager with the repository statusManager.
	 * 
	 * @param localStatusManager
	 *            the local statusManager
	 * @param repositoryStatusManager
	 *            the repository StatusManager
	 * @param statusManagerResource
	 *            the resource containing the repositoryStatusManager
	 */
	private void mergeCompilationStatusManager(CompilationStatusManager localStatusManager,
			CompilationStatusManager repositoryStatusManager, Resource statusManagerResource) {
		// Step 1 : Cleaning repositoryRootModel
		// Step 1.1 : removing dangling references
		removeDanglingReferences(repositoryStatusManager, statusManagerResource);

		// Step 2 : adding all the new compilation status
		for (ModelingUnit mu : localStatusManager.getModelingUnitToStatusList().keySet()) {
			for (CompilationStatus status : localStatusManager.getModelingUnitToStatusList().get(mu)) {
				if (!progressMonitor.isCanceled()) {
					if (!isContainedCompilationStatus(repositoryStatusManager.getModelingUnitToStatusList()
							.get(mu), status)) {

						if (!repositoryStatusManager.getCompilationStatusList().contains(status)) {
							if (status.getTarget() != null
									&& !status.getTarget().getCompilationStatus().contains(status)) {
								status.getTarget().getCompilationStatus().add(status);
							}
							repositoryStatusManager.getCompilationStatusList().add(status);

							if (repositoryStatusManager.getModelingUnitToStatusList().get(mu) == null) {
								repositoryStatusManager.getModelingUnitToStatusList().put(mu,
										new BasicEList<CompilationStatus>());
							}
							repositoryStatusManager.getModelingUnitToStatusList().get(mu).add(status);
						}
					}
				}
			}
		}

		repositoryStatusManager.setValidationTime(BigInteger.valueOf(System.currentTimeMillis()));
	}

	/**
	 * Removes all the dangling references contained in the given CompilationStatusManger.
	 * 
	 * @param repositoryStatusManager
	 *            the compilationSatusManager from which remove the dangling references
	 * @param statusManagerResource
	 *            the resource containing the repositoryStatusManager
	 */
	private void removeDanglingReferences(CompilationStatusManager repositoryStatusManager,
			Resource statusManagerResource) {
		CompilationStatusManager manager = repositoryStatusManager;
		Collection<URI> changedModelingUnits = Sets.newLinkedHashSet();

		ListIterator<Entry<ModelingUnit, EList<CompilationStatus>>> entries = manager
				.getModelingUnitToStatusList().listIterator();
		while (entries.hasNext()) {
			Entry<ModelingUnit, EList<CompilationStatus>> entry = entries.next();
			if (entry.getKey() == null || entry.getKey().eResource() == null) {
				manager.getCompilationStatusList().removeAll(entry.getValue());
				entries.remove();
			} else {
				ListIterator<CompilationStatus> statusList = entry.getValue().listIterator();
				while (statusList.hasNext()) {
					CompilationStatus status = statusList.next();
					if (status.getTarget() != null && status.getTarget().eResource() != null) {
						status.getTarget().getCompilationStatus().remove(status);
					}
					changedModelingUnits.add(entry.getKey().eResource().getURI());
					statusList.remove();
				}
			}
		}
	}

}
