/**
 * Copyright (c) 2010, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.mylyn.docs.intent.core.compiler.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.mylyn.docs.intent.core.compiler.util.CompilerAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CompilerItemProviderAdapterFactory extends CompilerAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilerItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStringToEObjectItemProvider eStringToEObjectItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEStringToEObjectAdapter() {
		if (eStringToEObjectItemProvider == null) {
			eStringToEObjectItemProvider = new EStringToEObjectItemProvider(this);
		}

		return eStringToEObjectItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextualReferenceToContributionsItemProvider textualReferenceToContributionsItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTextualReferenceToContributionsAdapter() {
		if (textualReferenceToContributionsItemProvider == null) {
			textualReferenceToContributionsItemProvider = new TextualReferenceToContributionsItemProvider(
					this);
		}

		return textualReferenceToContributionsItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.StringToEObjectMap} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StringToEObjectMapItemProvider stringToEObjectMapItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.StringToEObjectMap}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStringToEObjectMapAdapter() {
		if (stringToEObjectMapItemProvider == null) {
			stringToEObjectMapItemProvider = new StringToEObjectMapItemProvider(this);
		}

		return stringToEObjectMapItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ETypeToStringToEObjectMapItemProvider eTypeToStringToEObjectMapItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createETypeToStringToEObjectMapAdapter() {
		if (eTypeToStringToEObjectMapItemProvider == null) {
			eTypeToStringToEObjectMapItemProvider = new ETypeToStringToEObjectMapItemProvider(this);
		}

		return eTypeToStringToEObjectMapItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EObjectToUnresolvedReferencesListItemProvider eObjectToUnresolvedReferencesListItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEObjectToUnresolvedReferencesListAdapter() {
		if (eObjectToUnresolvedReferencesListItemProvider == null) {
			eObjectToUnresolvedReferencesListItemProvider = new EObjectToUnresolvedReferencesListItemProvider(
					this);
		}

		return eObjectToUnresolvedReferencesListItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceToContainedElementsMapEntryItemProvider resourceToContainedElementsMapEntryItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createResourceToContainedElementsMapEntryAdapter() {
		if (resourceToContainedElementsMapEntryItemProvider == null) {
			resourceToContainedElementsMapEntryItemProvider = new ResourceToContainedElementsMapEntryItemProvider(
					this);
		}

		return resourceToContainedElementsMapEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelingUnitToStatusListItemProvider modelingUnitToStatusListItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createModelingUnitToStatusListAdapter() {
		if (modelingUnitToStatusListItemProvider == null) {
			modelingUnitToStatusListItemProvider = new ModelingUnitToStatusListItemProvider(this);
		}

		return modelingUnitToStatusListItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CreatedElementToInstructionMapEntryItemProvider createdElementToInstructionMapEntryItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCreatedElementToInstructionMapEntryAdapter() {
		if (createdElementToInstructionMapEntryItemProvider == null) {
			createdElementToInstructionMapEntryItemProvider = new CreatedElementToInstructionMapEntryItemProvider(
					this);
		}

		return createdElementToInstructionMapEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedReferenceHolder} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnresolvedReferenceHolderItemProvider unresolvedReferenceHolderItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedReferenceHolder}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUnresolvedReferenceHolderAdapter() {
		if (unresolvedReferenceHolderItemProvider == null) {
			unresolvedReferenceHolderItemProvider = new UnresolvedReferenceHolderItemProvider(this);
		}

		return unresolvedReferenceHolderItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompilationStatusItemProvider compilationStatusItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCompilationStatusAdapter() {
		if (compilationStatusItemProvider == null) {
			compilationStatusItemProvider = new CompilationStatusItemProvider(this);
		}

		return compilationStatusItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusManager} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompilationStatusManagerItemProvider compilationStatusManagerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusManager}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCompilationStatusManagerAdapter() {
		if (compilationStatusManagerItemProvider == null) {
			compilationStatusManagerItemProvider = new CompilationStatusManagerItemProvider(this);
		}

		return compilationStatusManagerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.CompilationInformationHolder} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompilationInformationHolderItemProvider compilationInformationHolderItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.CompilationInformationHolder}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCompilationInformationHolderAdapter() {
		if (compilationInformationHolderItemProvider == null) {
			compilationInformationHolderItemProvider = new CompilationInformationHolderItemProvider(this);
		}

		return compilationInformationHolderItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedContributionHolder} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnresolvedContributionHolderItemProvider unresolvedContributionHolderItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedContributionHolder}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUnresolvedContributionHolderAdapter() {
		if (unresolvedContributionHolderItemProvider == null) {
			unresolvedContributionHolderItemProvider = new UnresolvedContributionHolderItemProvider(this);
		}

		return unresolvedContributionHolderItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceabilityIndexItemProvider traceabilityIndexItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTraceabilityIndexAdapter() {
		if (traceabilityIndexItemProvider == null) {
			traceabilityIndexItemProvider = new TraceabilityIndexItemProvider(this);
		}

		return traceabilityIndexItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndexEntry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceabilityIndexEntryItemProvider traceabilityIndexEntryItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndexEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTraceabilityIndexEntryAdapter() {
		if (traceabilityIndexEntryItemProvider == null) {
			traceabilityIndexEntryItemProvider = new TraceabilityIndexEntryItemProvider(this);
		}

		return traceabilityIndexEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompiledElementToInstructionEntryItemProvider compiledElementToInstructionEntryItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCompiledElementToInstructionEntryAdapter() {
		if (compiledElementToInstructionEntryItemProvider == null) {
			compiledElementToInstructionEntryItemProvider = new CompiledElementToInstructionEntryItemProvider(
					this);
		}

		return compiledElementToInstructionEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstructionTraceabilityEntryItemProvider instructionTraceabilityEntryItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.InstructionTraceabilityEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createInstructionTraceabilityEntryAdapter() {
		if (instructionTraceabilityEntryItemProvider == null) {
			instructionTraceabilityEntryItemProvider = new InstructionTraceabilityEntryItemProvider(this);
		}

		return instructionTraceabilityEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureToAffectationEntryItemProvider featureToAffectationEntryItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFeatureToAffectationEntryAdapter() {
		if (featureToAffectationEntryItemProvider == null) {
			featureToAffectationEntryItemProvider = new FeatureToAffectationEntryItemProvider(this);
		}

		return featureToAffectationEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceChangeStatusItemProvider resourceChangeStatusItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createResourceChangeStatusAdapter() {
		if (resourceChangeStatusItemProvider == null) {
			resourceChangeStatusItemProvider = new ResourceChangeStatusItemProvider(this);
		}

		return resourceChangeStatusItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.ModelElementChangeStatus} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelElementChangeStatusItemProvider modelElementChangeStatusItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.ModelElementChangeStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createModelElementChangeStatusAdapter() {
		if (modelElementChangeStatusItemProvider == null) {
			modelElementChangeStatusItemProvider = new ModelElementChangeStatusItemProvider(this);
		}

		return modelElementChangeStatusItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceChangeStatusItemProvider referenceChangeStatusItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createReferenceChangeStatusAdapter() {
		if (referenceChangeStatusItemProvider == null) {
			referenceChangeStatusItemProvider = new ReferenceChangeStatusItemProvider(this);
		}

		return referenceChangeStatusItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.mylyn.docs.intent.core.compiler.AttributeChangeStatus} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeChangeStatusItemProvider attributeChangeStatusItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.AttributeChangeStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAttributeChangeStatusAdapter() {
		if (attributeChangeStatusItemProvider == null) {
			attributeChangeStatusItemProvider = new AttributeChangeStatusItemProvider(this);
		}

		return attributeChangeStatusItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (eStringToEObjectItemProvider != null)
			eStringToEObjectItemProvider.dispose();
		if (textualReferenceToContributionsItemProvider != null)
			textualReferenceToContributionsItemProvider.dispose();
		if (stringToEObjectMapItemProvider != null)
			stringToEObjectMapItemProvider.dispose();
		if (eTypeToStringToEObjectMapItemProvider != null)
			eTypeToStringToEObjectMapItemProvider.dispose();
		if (eObjectToUnresolvedReferencesListItemProvider != null)
			eObjectToUnresolvedReferencesListItemProvider.dispose();
		if (resourceToContainedElementsMapEntryItemProvider != null)
			resourceToContainedElementsMapEntryItemProvider.dispose();
		if (modelingUnitToStatusListItemProvider != null)
			modelingUnitToStatusListItemProvider.dispose();
		if (createdElementToInstructionMapEntryItemProvider != null)
			createdElementToInstructionMapEntryItemProvider.dispose();
		if (unresolvedReferenceHolderItemProvider != null)
			unresolvedReferenceHolderItemProvider.dispose();
		if (compilationStatusItemProvider != null)
			compilationStatusItemProvider.dispose();
		if (compilationStatusManagerItemProvider != null)
			compilationStatusManagerItemProvider.dispose();
		if (compilationInformationHolderItemProvider != null)
			compilationInformationHolderItemProvider.dispose();
		if (unresolvedContributionHolderItemProvider != null)
			unresolvedContributionHolderItemProvider.dispose();
		if (traceabilityIndexItemProvider != null)
			traceabilityIndexItemProvider.dispose();
		if (traceabilityIndexEntryItemProvider != null)
			traceabilityIndexEntryItemProvider.dispose();
		if (compiledElementToInstructionEntryItemProvider != null)
			compiledElementToInstructionEntryItemProvider.dispose();
		if (instructionTraceabilityEntryItemProvider != null)
			instructionTraceabilityEntryItemProvider.dispose();
		if (featureToAffectationEntryItemProvider != null)
			featureToAffectationEntryItemProvider.dispose();
		if (resourceChangeStatusItemProvider != null)
			resourceChangeStatusItemProvider.dispose();
		if (modelElementChangeStatusItemProvider != null)
			modelElementChangeStatusItemProvider.dispose();
		if (referenceChangeStatusItemProvider != null)
			referenceChangeStatusItemProvider.dispose();
		if (attributeChangeStatusItemProvider != null)
			attributeChangeStatusItemProvider.dispose();
	}

}
