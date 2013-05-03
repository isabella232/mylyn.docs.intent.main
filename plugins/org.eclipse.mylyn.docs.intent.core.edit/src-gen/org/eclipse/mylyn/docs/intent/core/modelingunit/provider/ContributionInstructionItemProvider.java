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
package org.eclipse.mylyn.docs.intent.core.modelingunit.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContributionInstructionItemProvider extends ModelingUnitInstructionItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionInstructionItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures
					.add(ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTION_REFERENCE);
			childrenFeatures.add(ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS);
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
	 * This returns ContributionInstruction.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ContributionInstruction"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		ContributionInstruction contributionInstruction = (ContributionInstruction)object;
		return getString("_UI_ContributionInstruction_type") + " " + contributionInstruction.isLineBreak();
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

		switch (notification.getFeatureID(ContributionInstruction.class)) {
			case ModelingUnitPackage.CONTRIBUTION_INSTRUCTION__CONTRIBUTION_REFERENCE:
			case ModelingUnitPackage.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS:
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
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTION_REFERENCE,
				ModelingUnitFactory.eINSTANCE.createModelingUnitInstructionReference()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createResourceDeclaration()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createInstanciationInstruction()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createStructuralFeatureAffectation()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createNativeValue()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createNewObjectValue()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createReferenceValue()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createContributionInstruction()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createExternalContentReference()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createIntentReferenceInModelingUnit()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createAnnotationDeclaration()));

		newChildDescriptors.add(createChildParameter(
				ModelingUnitPackage.Literals.CONTRIBUTION_INSTRUCTION__CONTRIBUTIONS,
				ModelingUnitFactory.eINSTANCE.createLabelInModelingUnit()));
	}

}
