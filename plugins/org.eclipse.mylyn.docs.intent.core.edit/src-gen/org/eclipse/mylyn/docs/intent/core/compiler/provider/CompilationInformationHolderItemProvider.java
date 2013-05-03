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

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.mylyn.docs.intent.core.compiler.CompilationInformationHolder;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;

import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;

import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnitFactory;

import org.eclipse.mylyn.docs.intent.core.edit.IntentEditPlugin;

import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerFactory;

import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;

import org.eclipse.mylyn.docs.intent.markup.markup.MarkupFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.mylyn.docs.intent.core.compiler.CompilationInformationHolder} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CompilationInformationHolderItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationInformationHolderItemProvider(AdapterFactory adapterFactory) {
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

			addCurrentGeneratedElementListPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Current Generated Element List feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCurrentGeneratedElementListPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_CompilationInformationHolder_currentGeneratedElementList_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_CompilationInformationHolder_currentGeneratedElementList_feature",
						"_UI_CompilationInformationHolder_type"),
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__CURRENT_GENERATED_ELEMENT_LIST,
				true, false, true, null, null, null));
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
					.add(CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST);
			childrenFeatures
					.add(CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__ELEMENT_TO_UNRESOLVED_REFERENCE_MAP);
			childrenFeatures
					.add(CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__TYPE_TO_NAME_TO_ELEMENTS_MAP);
			childrenFeatures
					.add(CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__CREATED_ELEMENTS_TO_INSTRUCTIONS);
			childrenFeatures
					.add(CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__RESOURCE_TO_CONTAINED_ELEMENTS);
			childrenFeatures
					.add(CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__UNRESOLVED_CONTRIBUTIONS);
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
	 * This returns CompilationInformationHolder.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CompilationInformationHolder"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_CompilationInformationHolder_type");
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

		switch (notification.getFeatureID(CompilationInformationHolder.class)) {
			case CompilerPackage.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST:
			case CompilerPackage.COMPILATION_INFORMATION_HOLDER__ELEMENT_TO_UNRESOLVED_REFERENCE_MAP:
			case CompilerPackage.COMPILATION_INFORMATION_HOLDER__TYPE_TO_NAME_TO_ELEMENTS_MAP:
			case CompilerPackage.COMPILATION_INFORMATION_HOLDER__CREATED_ELEMENTS_TO_INSTRUCTIONS:
			case CompilerPackage.COMPILATION_INFORMATION_HOLDER__RESOURCE_TO_CONTAINED_ELEMENTS:
			case CompilerPackage.COMPILATION_INFORMATION_HOLDER__UNRESOLVED_CONTRIBUTIONS:
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
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.create(CompilerPackage.Literals.ESTRING_TO_EOBJECT)));

		newChildDescriptors
				.add(createChildParameter(
						CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
						CompilerFactory.eINSTANCE
								.create(CompilerPackage.Literals.TEXTUAL_REFERENCE_TO_CONTRIBUTIONS)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createStringToEObjectMap()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.create(CompilerPackage.Literals.ETYPE_TO_STRING_TO_EOBJECT_MAP)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.EOBJECT_TO_UNRESOLVED_REFERENCES_LIST)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.RESOURCE_TO_CONTAINED_ELEMENTS_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.create(CompilerPackage.Literals.MODELING_UNIT_TO_STATUS_LIST)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.CREATED_ELEMENT_TO_INSTRUCTION_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createUnresolvedReferenceHolder()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createCompilationStatus()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createCompilationStatusManager()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createCompilationInformationHolder()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createUnresolvedContributionHolder()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createTraceabilityIndex()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createTraceabilityIndexEntry()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.COMPILED_ELEMENT_TO_INSTRUCTION_ENTRY)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createInstructionTraceabilityEntry()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.create(CompilerPackage.Literals.FEATURE_TO_AFFECTATION_ENTRY)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createResourceChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createModelElementChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createReferenceChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				CompilerFactory.eINSTANCE.createAttributeChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				IntentDocumentFactory.eINSTANCE.createIntentGenericElement()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				IntentDocumentFactory.eINSTANCE.createIntentSection()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				IntentDocumentFactory.eINSTANCE.createIntentDocument()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				IntentDocumentFactory.eINSTANCE.createIntentReferenceInstruction()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				IntentDocumentFactory.eINSTANCE.createLabelDeclaration()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				IntentDocumentFactory.eINSTANCE.createLabelReferenceInstruction()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				DescriptionUnitFactory.eINSTANCE.createDescriptionUnit()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				DescriptionUnitFactory.eINSTANCE.createDescriptionBloc()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				IntentIndexerFactory.eINSTANCE.createIntentIndex()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				IntentIndexerFactory.eINSTANCE.createIntentIndexEntry()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createModelingUnit()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createResourceDeclaration()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createTypeReference()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createInstanciationInstruction()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createStructuralFeatureAffectation()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createNativeValue()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createNewObjectValue()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createReferenceValue()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createInstanciationInstructionReference()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createContributionInstruction()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createExternalContentReference()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createModelingUnitInstructionReference()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createIntentReferenceInModelingUnit()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createAnnotationDeclaration()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.createLabelInModelingUnit()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				ModelingUnitFactory.eINSTANCE.create(ModelingUnitPackage.Literals.KEY_VAL_FOR_ANNOTATION)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createDocument()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createSimpleContainer()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createSection()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createImage()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createText()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createEntity()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createLink()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createBlock()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createAnnotations()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createParagraph()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createTip()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createWarning()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createInformation()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createNote()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createPanel()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createDiv()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createFootNote()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createQuote()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createPreformatted()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createCode()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createList()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createListItem()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createTable()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createTableRow()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST,
				MarkupFactory.eINSTANCE.createTableCell()));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__ELEMENT_TO_UNRESOLVED_REFERENCE_MAP,
				CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.EOBJECT_TO_UNRESOLVED_REFERENCES_LIST)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__TYPE_TO_NAME_TO_ELEMENTS_MAP,
				CompilerFactory.eINSTANCE.create(CompilerPackage.Literals.ETYPE_TO_STRING_TO_EOBJECT_MAP)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__CREATED_ELEMENTS_TO_INSTRUCTIONS,
				CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.CREATED_ELEMENT_TO_INSTRUCTION_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(
				CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__RESOURCE_TO_CONTAINED_ELEMENTS,
				CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.RESOURCE_TO_CONTAINED_ELEMENTS_MAP_ENTRY)));

		newChildDescriptors
				.add(createChildParameter(
						CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__UNRESOLVED_CONTRIBUTIONS,
						CompilerFactory.eINSTANCE
								.create(CompilerPackage.Literals.TEXTUAL_REFERENCE_TO_CONTRIBUTIONS)));
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

		boolean qualify = childFeature == CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__GENERATED_ELEMENT_LIST
				|| childFeature == CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__UNRESOLVED_CONTRIBUTIONS
				|| childFeature == CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__TYPE_TO_NAME_TO_ELEMENTS_MAP
				|| childFeature == CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__ELEMENT_TO_UNRESOLVED_REFERENCE_MAP
				|| childFeature == CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__RESOURCE_TO_CONTAINED_ELEMENTS
				|| childFeature == CompilerPackage.Literals.COMPILATION_INFORMATION_HOLDER__CREATED_ELEMENTS_TO_INSTRUCTIONS;

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
