/*******************************************************************************
 * Copyright (c) 2012 Obeo.

 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.modelingunit.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.common.query.CompilationStatusQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;

/**
 * Utility which updates modeling units by merging elements into an existing model.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class MergeUpdater extends AbstractModelingUnitUpdater {

	/**
	 * The instanciations created during the process.
	 */
	private Map<EObject, InstanciationInstruction> newInstanciations;

	/**
	 * Creates a modeling unit updater.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
	public MergeUpdater(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * Creates instanciations for the given elements.
	 * 
	 * @param section
	 *            the section where to create a new modeling unit
	 * @param elements
	 *            the elements to instanciate
	 */
	public void create(final IntentSection section, final List<EObject> elements) {
		repositoryAdapter.execute(new IntentCommand() {
			public void execute() {
				ModelingUnit modelingUnit = ModelingUnitFactory.eINSTANCE.createModelingUnit();
				section.getIntentContent().add(modelingUnit);
				internalCreate(modelingUnit, elements);
				try {
					repositoryAdapter.save();
				} catch (ReadOnlyException e) {
					IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
				} catch (SaveException e) {
					IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
				}
			}

		});
	}

	/**
	 * Creates instanciations for the given elements.
	 * 
	 * @param modelingUnit
	 *            the modeling unit where to create the instanciations
	 * @param elements
	 *            the elements to instanciate
	 */
	public void create(final ModelingUnit modelingUnit, final List<EObject> elements) {
		repositoryAdapter.execute(new IntentCommand() {
			public void execute() {
				internalCreate(modelingUnit, elements);
				try {
					repositoryAdapter.save();
				} catch (ReadOnlyException e) {
					IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
				} catch (SaveException e) {
					IntentLogger.getInstance().log(LogType.ERROR, e.getMessage());
				}
			}
		});
	}

	/**
	 * Create a list of elements inside of the given modeling unit.
	 * 
	 * @param modelingUnit
	 *            the modeling unit
	 * @param elements
	 *            the elements
	 */
	private void internalCreate(final ModelingUnit modelingUnit, final List<EObject> elements) {
		setNewObjects(getAllNewObjects(elements));
		newInstanciations = new HashMap<EObject, InstanciationInstruction>();
		for (EObject workingCopyObject : elements) {
			Resource compiledResource = getMatchingCompiledResource(workingCopyObject);
			includeMatch(compiledResource, workingCopyObject.eResource());
			if (getExistingInstanciationFor(workingCopyObject) == null) {
				InstanciationInstruction containerInstanciation = getExistingInstanciationFor(workingCopyObject
						.eContainer());
				if (containerInstanciation == null) {
					// lookup in new instanciations
					containerInstanciation = newInstanciations.get(workingCopyObject.eContainer());
				}
				if (containerInstanciation != null) {
					ContributionInstruction contribution = generateContribution(containerInstanciation);
					StructuralFeatureAffectation containment = generateSingleAffectation(
							workingCopyObject.eContainingFeature(), workingCopyObject);
					contribution.getContributions().add(containment);
					modelingUnit.getInstructions().add(contribution);
					newInstanciations.put(workingCopyObject, ((NewObjectValueForStructuralFeature)containment
							.getValues().get(0)).getValue());
				} else {
					InstanciationInstruction instanciation = generateInstanciation(workingCopyObject);
					modelingUnit.getInstructions().add(instanciation);
					newInstanciations.put(workingCopyObject, instanciation);
				}
			}
		}
	}

	/**
	 * Gather all objects content.
	 * 
	 * @param roots
	 *            the root objects
	 * @return the objects and their content
	 */
	private List<EObject> getAllNewObjects(List<EObject> roots) {
		List<EObject> res = new ArrayList<EObject>();
		for (EObject root : roots) {
			res.add(root);
			res.addAll(getAllNewObjects(root.eContents()));
		}
		return res;
	}

	/**
	 * Finds a status associated to the given object.
	 * 
	 * @param eObject
	 *            the object
	 * @return a status associated to the given object
	 */
	private Resource getMatchingCompiledResource(EObject eObject) {
		String uriFragment = EcoreUtil.getURI(eObject).toString();
		CompilationStatusQuery query = new CompilationStatusQuery(repositoryAdapter);
		for (CompilationStatus compilationStatus : query.getOrCreateCompilationStatusManager()
				.getCompilationStatusList()) {
			if (compilationStatus instanceof StructuralFeatureChangeStatus) {
				StructuralFeatureChangeStatus status = (StructuralFeatureChangeStatus)compilationStatus;
				if (uriFragment.equals(status.getWorkingCopyElementURIFragment())) {
					return repositoryAdapter.getResource(status.getCompiledResourceURI());
				}
			}
		}
		return null;
	}

}
