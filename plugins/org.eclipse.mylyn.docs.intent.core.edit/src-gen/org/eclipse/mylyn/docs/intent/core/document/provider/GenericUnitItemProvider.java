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
package org.eclipse.mylyn.docs.intent.core.document.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.mylyn.docs.intent.core.document.GenericUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;

import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnitFactory;

import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.mylyn.docs.intent.core.document.GenericUnit} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenericUnitItemProvider extends IntentGenericElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericUnitItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_GenericUnit_name_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_GenericUnit_name_feature",
						"_UI_GenericUnit_type"), IntentDocumentPackage.Literals.GENERIC_UNIT__NAME, true,
				false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((GenericUnit)object).getName();
		return label == null || label.length() == 0 ? getString("_UI_GenericUnit_type")
				: getString("_UI_GenericUnit_type") + " " + label;
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

		switch (notification.getFeatureID(GenericUnit.class)) {
			case IntentDocumentPackage.GENERIC_UNIT__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false,
						true));
				return;
			case IntentDocumentPackage.GENERIC_UNIT__INSTRUCTIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true,
						false));
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

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				IntentDocumentFactory.eINSTANCE.createIntentReferenceInstruction()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				IntentDocumentFactory.eINSTANCE.createLabelDeclaration()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				IntentDocumentFactory.eINSTANCE.createLabelReferenceInstruction()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				DescriptionUnitFactory.eINSTANCE.createDescriptionBloc()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createResourceDeclaration()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createInstanciationInstruction()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createStructuralFeatureAffectation()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createNativeValue()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createNewObjectValue()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createReferenceValue()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createContributionInstruction()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createExternalContentReference()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createIntentReferenceInModelingUnit()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createAnnotationDeclaration()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.GENERIC_UNIT__INSTRUCTIONS,
				ModelingUnitFactory.eINSTANCE.createLabelInModelingUnit()));
	}

}
