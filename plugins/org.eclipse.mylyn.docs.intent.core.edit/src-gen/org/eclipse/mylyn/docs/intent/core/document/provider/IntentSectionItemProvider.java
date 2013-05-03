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
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerFactory;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnitFactory;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.query.StructuredElementHelper;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupFactory;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.mylyn.docs.intent.core.document.IntentSection} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IntentSectionItemProvider extends IntentStructuredElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentSectionItemProvider(AdapterFactory adapterFactory) {
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

			addSubSectionsPropertyDescriptor(object);
			addUnitsPropertyDescriptor(object);
			addDescriptionUnitsPropertyDescriptor(object);
			addModelingUnitsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Sub Sections feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSubSectionsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_IntentSection_subSections_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_IntentSection_subSections_feature",
						"_UI_IntentSection_type"),
				IntentDocumentPackage.Literals.INTENT_SECTION__SUB_SECTIONS, true, false, true, null, null,
				null));
	}

	/**
	 * This adds a property descriptor for the Units feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUnitsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_IntentSection_units_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_IntentSection_units_feature",
						"_UI_IntentSection_type"), IntentDocumentPackage.Literals.INTENT_SECTION__UNITS,
				true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Description Units feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionUnitsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_IntentSection_descriptionUnits_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_IntentSection_descriptionUnits_feature",
						"_UI_IntentSection_type"),
				IntentDocumentPackage.Literals.INTENT_SECTION__DESCRIPTION_UNITS, true, false, true, null,
				null, null));
	}

	/**
	 * This adds a property descriptor for the Modeling Units feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addModelingUnitsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_IntentSection_modelingUnits_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_IntentSection_modelingUnits_feature",
						"_UI_IntentSection_type"),
				IntentDocumentPackage.Literals.INTENT_SECTION__MODELING_UNITS, true, false, true, null, null,
				null));
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
			childrenFeatures.add(IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT);
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
		IntentSection intentSection = (IntentSection)object;
		return StructuredElementHelper.getTitle(intentSection, STRUCTURED_ELEMENT_TITLE_MAXLENGTH);
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

		switch (notification.getFeatureID(IntentSection.class)) {
			case IntentDocumentPackage.INTENT_SECTION__INTENT_CONTENT:
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
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				IntentDocumentFactory.eINSTANCE.createIntentGenericElement()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				IntentDocumentFactory.eINSTANCE.createIntentSection()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				IntentDocumentFactory.eINSTANCE.createIntentDocument()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				IntentDocumentFactory.eINSTANCE.createIntentReferenceInstruction()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				IntentDocumentFactory.eINSTANCE.createLabelDeclaration()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				IntentDocumentFactory.eINSTANCE.createLabelReferenceInstruction()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.create(CompilerPackage.Literals.ESTRING_TO_EOBJECT)));

		newChildDescriptors
				.add(createChildParameter(IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
						CompilerFactory.eINSTANCE
								.create(CompilerPackage.Literals.TEXTUAL_REFERENCE_TO_CONTRIBUTIONS)));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createStringToEObjectMap()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.create(CompilerPackage.Literals.ETYPE_TO_STRING_TO_EOBJECT_MAP)));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT, CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.EOBJECT_TO_UNRESOLVED_REFERENCES_LIST)));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT, CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.RESOURCE_TO_CONTAINED_ELEMENTS_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.create(CompilerPackage.Literals.MODELING_UNIT_TO_STATUS_LIST)));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT, CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.CREATED_ELEMENT_TO_INSTRUCTION_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createUnresolvedReferenceHolder()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createCompilationStatus()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createCompilationStatusManager()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createCompilationInformationHolder()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createUnresolvedContributionHolder()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createTraceabilityIndex()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createTraceabilityIndexEntry()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT, CompilerFactory.eINSTANCE
						.create(CompilerPackage.Literals.COMPILED_ELEMENT_TO_INSTRUCTION_ENTRY)));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createInstructionTraceabilityEntry()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.create(CompilerPackage.Literals.FEATURE_TO_AFFECTATION_ENTRY)));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createResourceChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createModelElementChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createReferenceChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				CompilerFactory.eINSTANCE.createAttributeChangeStatus()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				DescriptionUnitFactory.eINSTANCE.createDescriptionUnit()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				DescriptionUnitFactory.eINSTANCE.createDescriptionBloc()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				IntentIndexerFactory.eINSTANCE.createIntentIndex()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				IntentIndexerFactory.eINSTANCE.createIntentIndexEntry()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createModelingUnit()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createResourceDeclaration()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createTypeReference()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createInstanciationInstruction()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createStructuralFeatureAffectation()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createNativeValue()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createNewObjectValue()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createReferenceValue()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createInstanciationInstructionReference()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createContributionInstruction()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createExternalContentReference()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createModelingUnitInstructionReference()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createIntentReferenceInModelingUnit()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createAnnotationDeclaration()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.createLabelInModelingUnit()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				ModelingUnitFactory.eINSTANCE.create(ModelingUnitPackage.Literals.KEY_VAL_FOR_ANNOTATION)));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createDocument()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createSimpleContainer()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createSection()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createImage()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createText()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createEntity()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createLink()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createBlock()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createAnnotations()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createParagraph()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createTip()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createWarning()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createInformation()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createNote()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createPanel()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createDiv()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createFootNote()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createQuote()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createPreformatted()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createCode()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createList()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createListItem()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createTable()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createTableRow()));

		newChildDescriptors.add(createChildParameter(
				IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT,
				MarkupFactory.eINSTANCE.createTableCell()));
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
				|| childFeature == IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT
				|| childFeature == MarkupPackage.Literals.SECTION__TITLE
				|| childFeature == MarkupPackage.Literals.HAS_ATTRIBUTES__ATTRIBUTES
				|| childFeature == IntentDocumentPackage.Literals.INTENT_GENERIC_ELEMENT__COMPILATION_STATUS;

		if (qualify) {
			return getString("_UI_CreateChild_text2", new Object[] {getTypeText(childObject),
					getFeatureText(childFeature), getTypeText(owner)
			});
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
