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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus;

/**
 * This is the item provider adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StructuralFeatureChangeStatusItemProvider extends SynchronizerCompilationStatusItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeatureChangeStatusItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addChangeStatePropertyDescriptor(object);
			addFeatureNamePropertyDescriptor(object);
			addCompiledElementPropertyDescriptor(object);
			addWorkingCopyElementURIFragmentPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Change State feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addChangeStatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_StructuralFeatureChangeStatus_changeState_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_StructuralFeatureChangeStatus_changeState_feature",
						"_UI_StructuralFeatureChangeStatus_type"),
				CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__CHANGE_STATE, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Feature Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFeatureNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_StructuralFeatureChangeStatus_featureName_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_StructuralFeatureChangeStatus_featureName_feature",
						"_UI_StructuralFeatureChangeStatus_type"),
				CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__FEATURE_NAME, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Compiled Element feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCompiledElementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_StructuralFeatureChangeStatus_compiledElement_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_StructuralFeatureChangeStatus_compiledElement_feature",
						"_UI_StructuralFeatureChangeStatus_type"),
				CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__COMPILED_ELEMENT, true, false,
				true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Working Copy Element URI Fragment feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWorkingCopyElementURIFragmentPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_StructuralFeatureChangeStatus_workingCopyElementURIFragment_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_StructuralFeatureChangeStatus_workingCopyElementURIFragment_feature",
						"_UI_StructuralFeatureChangeStatus_type"),
				CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__WORKING_COPY_ELEMENT_URI_FRAGMENT,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((StructuralFeatureChangeStatus)object).getFeatureName();
		return label == null || label.length() == 0 ? getString("_UI_StructuralFeatureChangeStatus_type")
				: getString("_UI_StructuralFeatureChangeStatus_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(StructuralFeatureChangeStatus.class)) {
			case CompilerPackage.STRUCTURAL_FEATURE_CHANGE_STATUS__CHANGE_STATE:
			case CompilerPackage.STRUCTURAL_FEATURE_CHANGE_STATUS__FEATURE_NAME:
			case CompilerPackage.STRUCTURAL_FEATURE_CHANGE_STATUS__WORKING_COPY_ELEMENT_URI_FRAGMENT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false,
						true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
