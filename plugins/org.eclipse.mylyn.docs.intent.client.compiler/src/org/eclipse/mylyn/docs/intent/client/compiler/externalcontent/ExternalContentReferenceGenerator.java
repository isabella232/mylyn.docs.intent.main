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
package org.eclipse.mylyn.docs.intent.client.compiler.externalcontent;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.mylyn.docs.intent.client.compiler.generator.modelgeneration.ModelingUnitGenerator;
import org.eclipse.mylyn.docs.intent.collab.common.uri.IntentResourceFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationMessageType;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusSeverity;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;

/**
 * Allows to get and store the content referenced by an {@link ExternalContentReference}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class ExternalContentReferenceGenerator {

	/**
	 * Private constructor.
	 */
	private ExternalContentReferenceGenerator() {
	}

	/**
	 * Gets and stores the content referenced by the given {@link ExternalContentReference}.
	 * 
	 * @param object
	 *            the {@link ExternalContentReference} to resolve
	 * @param modelingUnitGenerator
	 *            the {@link ModelingUnitGenerator} currently in use
	 * @return an empty list
	 */
	public static List<Object> generate(ExternalContentReference object,
			ModelingUnitGenerator modelingUnitGenerator) {
		// Clear previous compilation issues
		Iterator<CompilationStatus> previousCcompilationIssues = Iterables.filter(
				Sets.newLinkedHashSet(object.getCompilationStatus()), new Predicate<CompilationStatus>() {

					public boolean apply(CompilationStatus status) {
						return CompilationStatusSeverity.ERROR.equals(status.getSeverity());
					}
				}).iterator();

		while (previousCcompilationIssues.hasNext()) {
			object.getCompilationStatus().remove(previousCcompilationIssues.next());
		}

		// If the external content should be merged or if we never get the external content before
		if (object.getExternalContent() == null || object.getExternalContent().eIsProxy()
				|| object.isMarkedAsMerged()) {

			// We use the ExternalContentResourceFactory to get the content
			URI externalContentResourceURI = URI.createURI(object.getUri().toString().replace("\"", ""));

			// If the resource is internal (i.e. has an intent:/ URI)
			if (IntentResourceFactory.getIntentFactoryScheme().equals(externalContentResourceURI.scheme())) {
				// We indicate through the URI that the IntentResourceFactory should create it if needed
				externalContentResourceURI = URI.createURI(externalContentResourceURI.toString()
						+ IntentResourceFactory.getCreateResourceIfNeededTag());
			}
			try {
				object.setMarkedAsMerged(false);
				Resource externalContentResource = object.eResource().getResourceSet()
						.getResource(externalContentResourceURI.trimFragment(), true);
				try {
					EObject content = null;
					Copier copier = new Copier(false, true);
					if (externalContentResourceURI.hasFragment()) {
						content = copier.copy(externalContentResource.getEObject(externalContentResourceURI
								.fragment()));
					} else {
						content = EcoreUtil.copy(externalContentResource.getContents().iterator().next());
					}
					copier.copyReferences();
					object.setExternalContent(content);
				} finally {
					externalContentResource.unload();
					externalContentResource.getResourceSet().getResources().remove(externalContentResource);
				}
				// CHECKSTYLE:OFF
			} catch (Exception e) {
				object.setExternalContent(null);
				// CHECKSTYLE:ON
				CompilationStatus status = CompilerFactory.eINSTANCE.createCompilationStatus();
				status.setMessage("Could not find resource " + externalContentResourceURI);
				status.setTarget(object);
				status.setSeverity(CompilationStatusSeverity.ERROR);
				status.setType(CompilationMessageType.RESOLVE_ERROR);
				object.getCompilationStatus().add(status);
			}
		}

		// Update traceability informations
		modelingUnitGenerator.getInformationHolder().addResource(object);
		if (object.getExternalContent() != null) {
			modelingUnitGenerator.getInformationHolder().addResourceToGeneratedElementMapping(object,
					object.getExternalContent());
			modelingUnitGenerator.getInformationHolder().addCreatedElementsToCurrentList(object,
					object.getExternalContent());
		}
		return Lists.newArrayList();
	}
}
