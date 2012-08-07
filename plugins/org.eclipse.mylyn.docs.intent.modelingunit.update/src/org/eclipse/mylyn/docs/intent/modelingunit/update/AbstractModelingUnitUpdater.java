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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.compare.match.metamodel.MatchElement;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.mylyn.docs.intent.collab.common.query.TraceabilityInformationsQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.modelingunit.gen.AbstractModelingUnitGenerator;

/**
 * Utility which updates modeling units according to existing models.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractModelingUnitUpdater extends AbstractModelingUnitGenerator {

	/**
	 * The common traceability query.
	 */
	protected TraceabilityInformationsQuery query;

	private int lastIndex = -1;

	private Map<EObject, String> referenceNames = new HashMap<EObject, String>();

	/**
	 * The mapping between working copy objects and existing instanciation instructions.
	 */
	private Map<EObject, InstanciationInstruction> match = new HashMap<EObject, InstanciationInstruction>();

	/**
	 * Creates a modeling unit updater.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
	public AbstractModelingUnitUpdater(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
		query = new TraceabilityInformationsQuery(repositoryAdapter);
	}

	/**
	 * Initializes the match map from the given resources.
	 * 
	 * @param compiledResource
	 *            the compiled resource
	 * @param workingCopyResource
	 *            the working copy resource
	 */
	protected void includeMatch(Resource compiledResource, Resource workingCopyResource) {
		try {
			final HashMap<String, Object> options = new HashMap<String, Object>();
			if ((compiledResource instanceof XMIResource && !(workingCopyResource instanceof XMIResource))
					|| (workingCopyResource instanceof XMIResource && !(compiledResource instanceof XMIResource))) {
				options.put(MatchOptions.OPTION_IGNORE_XMI_ID, Boolean.TRUE);
			}
			MatchModel matchModel = MatchService.doResourceMatch(compiledResource, workingCopyResource,
					options);
			for (MatchElement matchElement : matchModel.getMatchedElements()) {
				collectAllMatches(matchElement);
			}
			// CHECKSTYLE:OFF we ignore comparison errors
		} catch (Exception e) {
			// CHECKSTYLE :ON
			e.printStackTrace();
		}
	}

	/**
	 * Fills the match map from the given match element.
	 * 
	 * @param matchElement
	 *            the match element
	 */
	private void collectAllMatches(MatchElement matchElement) {
		if (matchElement instanceof Match2Elements) {
			Match2Elements match2Elements = (Match2Elements)matchElement;
			EObject rightElement = match2Elements.getRightElement();
			EObject leftElement = match2Elements.getLeftElement();
			InstanciationInstruction instanciation = query.getInstanciation(leftElement);
			if (instanciation != null) {
				match.put(rightElement, instanciation);
			}
		}
		for (MatchElement subMatchElement : matchElement.getSubMatchElements()) {
			collectAllMatches(subMatchElement);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.modelingunit.gen.AbstractModelingUnitGenerator#getExistingInstanciationFor(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected InstanciationInstruction getExistingInstanciationFor(EObject o) {
		return match.get(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.modelingunit.gen.AbstractModelingUnitGenerator#getGeneratedElement(org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction)
	 */
	@Override
	protected EObject getGeneratedElement(InstanciationInstruction instanciation) {
		return query.getInstance(instanciation);
	}

	/**
	 * Load the object at the given uri.
	 * 
	 * @param uri
	 *            the uri
	 * @return the loaded object
	 */
	protected EObject getWorkingCopyEObject(String uri) {
		if (uri == null) {
			return null;
		}
		return resourceSet.getEObject(URI.createURI(uri), true);
	}

	/**
	 * Returns the container of the given type from a root element.
	 * 
	 * @param object
	 *            the root
	 * @param classifierId
	 *            the classifier ids to consider
	 * @return the parent instruction
	 */
	public static IntentGenericElement getContainer(IntentGenericElement object, int... classifierId) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Integer id : classifierId) {
			ids.add(id);
		}

		EObject tmp = object;
		while (tmp != null && !ids.contains(tmp.eClass().getClassifierID())) {
			tmp = tmp.eContainer();
		}
		if (ids.contains(tmp.eClass().getClassifierID())) {
			return (IntentGenericElement)tmp;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.modelingunit.gen.AbstractModelingUnitGenerator#getReferenceName(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected String getReferenceName(EObject eObject) {
		String res = referenceNames.get(eObject);
		if (res == null) {
			if (lastIndex < 0) {
				// we have to init the index from existing instanciations
				String regex = "REF[0-9]+";
				for (InstanciationInstruction instanciation : query.getInstanciations()) {
					String name = instanciation.getName();
					if (name != null && name.matches(regex)) {
						int current = Integer.valueOf(name.substring(3));
						lastIndex = Math.max(lastIndex, current);
					}
				}
			}
			lastIndex++;
			res = "REF" + lastIndex;
			referenceNames.put(eObject, res);
		}
		return res;
	}
}
