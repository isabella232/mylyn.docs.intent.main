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
package org.eclipse.mylyn.docs.intent.client.synchronizer.strategy;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.mylyn.docs.intent.client.synchronizer.factory.SynchronizerMessageProvider;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationMessageType;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusSeverity;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;

/**
 * In case of a null external resource, creates a new resource with the internal resource content; see
 * {@link SynchronizerStrategy} fore more details.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class CopyInternalResourceStrategy implements SynchronizerStrategy {
	/**
	 * {@inheritDoc}
	 * <p>
	 * The strategy applied here is to create and save a new external Resource and copy the internal Resource
	 * content.
	 * </p>
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.strategy.SynchronizerStrategy#handleNullExternalResource(org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration,
	 *      org.eclipse.emf.ecore.resource.Resource, java.lang.String)
	 */
	public Resource handleNullExternalResource(ResourceDeclaration resourceDeclaration,
			Resource internalResource, URI externalResourceURI) {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource externalResource = resourceSet.createResource(externalResourceURI);
		for (EObject root : internalResource.getContents()) {
			externalResource.getContents().add(EcoreUtil.copy(root));
		}
		try {
			Map<String, Boolean> options = new HashMap<String, Boolean>();
			options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
			options.put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
			externalResource.save(options);
		} catch (IOException e) {
			// We stop the synchronization on these resources
			return null;
		}
		return externalResource;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The strategy applied here is to stop this synchronization operation.
	 * </p>
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.strategy.SynchronizerStrategy#handleNullInternalResource(java.lang.String,
	 *      org.eclipse.emf.ecore.resource.Resource)
	 */
	public Resource handleNullInternalResource(String internalResourcePath, Resource externalResource) {
		// We just stop the synchronization on these resources
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.strategy.SynchronizerStrategy#getLeftResource(org.eclipse.emf.ecore.resource.Resource,
	 *      org.eclipse.emf.ecore.resource.Resource)
	 */
	public Resource getLeftResource(Resource internalResource, Resource externalResource) {
		// Here we consider that the latest version is from the repository in any case
		return internalResource;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.strategy.SynchronizerStrategy#getRightResource(org.eclipse.emf.ecore.resource.Resource,
	 *      org.eclipse.emf.ecore.resource.Resource)
	 */
	public Resource getRightResource(Resource internalResource, Resource externalResource) {
		// Here we consider that the latest version is from the repository in any case
		return externalResource;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.strategy.SynchronizerStrategy#getStatusForNullExternalResource(org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration,
	 *      java.lang.String)
	 */
	public Collection<? extends CompilationStatus> getStatusForNullExternalResource(
			ResourceDeclaration resourceDeclaration, String resourcePath) {

		ResourceChangeStatus status = CompilerFactory.eINSTANCE.createResourceChangeStatus();
		status.setSeverity(CompilationStatusSeverity.WARNING);
		status.setTarget(resourceDeclaration);
		status.setType(CompilationMessageType.SYNCHRONIZER_WARNING);
		status.setMessage(SynchronizerMessageProvider
				.createMessageForNullExternalResource(resourceDeclaration));
		status.setWorkingCopyResourceState(SynchronizerResourceState.NULL);
		return Lists.newArrayList(status);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.strategy.SynchronizerStrategy#handleEmptyExternalResource(org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration,
	 *      org.eclipse.emf.ecore.resource.Resource, java.lang.String)
	 */
	public Resource handleEmptyExternalResource(ResourceDeclaration resourceDeclaration,
			Resource internalResource, URI externalResourceURI) {
		return handleNullExternalResource(resourceDeclaration, internalResource, externalResourceURI);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.strategy.SynchronizerStrategy#getStatusForEmptyExternalResource(org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration,
	 *      java.lang.String)
	 */
	public Collection<? extends CompilationStatus> getStatusForEmptyExternalResource(
			ResourceDeclaration resourceDeclaration, String resourcePath) {
		ResourceChangeStatus status = CompilerFactory.eINSTANCE.createResourceChangeStatus();
		status.setCompiledResourceURI(resourcePath);
		status.setWorkingCopyResourceURI(resourceDeclaration.getUri().toString());
		status.setSeverity(CompilationStatusSeverity.WARNING);
		status.setTarget(resourceDeclaration);
		status.setType(CompilationMessageType.SYNCHRONIZER_WARNING);
		status.setMessage(SynchronizerMessageProvider
				.createMessageForEmptyExternalResource(resourceDeclaration));
		status.setWorkingCopyResourceState(SynchronizerResourceState.EMPTY);
		return Lists.newArrayList(status);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.strategy.SynchronizerStrategy#getStatusForEmptyInternalResource(org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration,
	 *      java.lang.String)
	 */
	public Collection<? extends CompilationStatus> getStatusForEmptyInternalResource(
			ResourceDeclaration resourceDeclaration, String resourcePath) {
		ResourceChangeStatus status = CompilerFactory.eINSTANCE.createResourceChangeStatus();
		status.setCompiledResourceURI(resourcePath);
		status.setWorkingCopyResourceURI(resourceDeclaration.getUri().toString());
		status.setSeverity(CompilationStatusSeverity.WARNING);
		status.setTarget(resourceDeclaration);
		status.setType(CompilationMessageType.SYNCHRONIZER_WARNING);
		status.setMessage(SynchronizerMessageProvider
				.createMessageForEmptyInternalResource(resourceDeclaration));
		status.setCompiledResourceState(SynchronizerResourceState.EMPTY);
		return Lists.newArrayList(status);
	}
}
