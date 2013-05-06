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
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.edit.IntentEditPlugin;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupFactory;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IntentStructuredElementItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentStructuredElementItemProvider(AdapterFactory adapterFactory) {
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

			addLevelPropertyDescriptor(object);
			addIndexEntryPropertyDescriptor(object);
			addCompleteLevelPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Level feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLevelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_Section_level_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Section_level_feature",
						"_UI_Section_type"), MarkupPackage.Literals.SECTION__LEVEL, true, false, false,
				ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Index Entry feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIndexEntryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_IntentGenericElement_indexEntry_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_IntentGenericElement_indexEntry_feature", "_UI_IntentGenericElement_type"),
				IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__INDEX_ENTRY, true, false, true, null,
				null, null));
	}

	/**
	 * This adds a property descriptor for the Complete Level feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCompleteLevelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_IntentStructuredElement_completeLevel_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_IntentStructuredElement_completeLevel_feature",
						"_UI_IntentStructuredElement_type"),
				IntentDocumentPackage.Literals.INTENT_STRUCTURED_ELEMENT__COMPLETE_LEVEL, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
			childrenFeatures.add(MarkupPackage.Literals.CONTAINER__CONTENT);
			childrenFeatures.add(MarkupPackage.Literals.HAS_ATTRIBUTES__ATTRIBUTES);
			childrenFeatures.add(MarkupPackage.Literals.SECTION__TITLE);
			childrenFeatures.add(IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__COMPILATION_STATUS);
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
		IntentStructuredElement intentStructuredElement = (IntentStructuredElement)object;
		return getString("_UI_IntentStructuredElement_type") + " " + intentStructuredElement.getLevel();
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

		switch (notification.getFeatureID(IntentStructuredElement.class)) {
			case IntentDocumentPackage.INTENT_STRUCTURED_ELEMENT__LEVEL:
			case IntentDocumentPackage.INTENT_STRUCTURED_ELEMENT__COMPLETE_LEVEL:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false,
						true));
				return;
			case IntentDocumentPackage.INTENT_STRUCTURED_ELEMENT__CONTENT:
			case IntentDocumentPackage.INTENT_STRUCTURED_ELEMENT__ATTRIBUTES:
			case IntentDocumentPackage.INTENT_STRUCTURED_ELEMENT__TITLE:
			case IntentDocumentPackage.INTENT_STRUCTURED_ELEMENT__COMPILATION_STATUS:
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

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				IntentDocumentFactory.eINSTANCE.createIntentSection()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				IntentDocumentFactory.eINSTANCE.createIntentDocument()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createSection()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createBlock()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createParagraph()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createTip()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createWarning()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createInformation()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createNote()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createPanel()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createDiv()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createFootNote()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createQuote()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createPreformatted()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createCode()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createList()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createListItem()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createTable()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createTableRow()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.CONTAINER__CONTENT,
				MarkupFactory.eINSTANCE.createTableCell()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.HAS_ATTRIBUTES__ATTRIBUTES,
				MarkupFactory.eINSTANCE.createAnnotations()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createBlock()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createParagraph()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createTip()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createWarning()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createInformation()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createNote()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createPanel()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createDiv()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createFootNote()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createQuote()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createPreformatted()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createCode()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createList()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createListItem()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createTable()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createTableRow()));

		newChildDescriptors.add(createChildParameter(MarkupPackage.Literals.SECTION__TITLE,
				MarkupFactory.eINSTANCE.createTableCell()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__COMPILATION_STATUS,
				CompilerFactory.eINSTANCE.createCompilationStatus()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__COMPILATION_STATUS,
				CompilerFactory.eINSTANCE.createResourceChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__COMPILATION_STATUS,
				CompilerFactory.eINSTANCE.createModelElementChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__COMPILATION_STATUS,
				CompilerFactory.eINSTANCE.createReferenceChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__COMPILATION_STATUS,
				CompilerFactory.eINSTANCE.createAttributeChangeStatus()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == MarkupPackage.Literals.CONTAINER__CONTENT
				|| childFeature == MarkupPackage.Literals.SECTION__TITLE;

		if (qualify) {
			return getString("_UI_CreateChild_text2", new Object[] {getTypeText(childObject),
					getFeatureText(childFeature), getTypeText(owner)
			});
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return IntentEditPlugin.INSTANCE;
	}

}
