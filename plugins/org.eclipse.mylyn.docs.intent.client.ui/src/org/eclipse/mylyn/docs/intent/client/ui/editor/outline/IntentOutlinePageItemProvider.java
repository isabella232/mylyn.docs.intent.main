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
package org.eclipse.mylyn.docs.intent.client.ui.editor.outline;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationMessageType;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusSeverity;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionBloc;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnitPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentChapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.genericunit.LabelDeclaration;
import org.eclipse.mylyn.docs.intent.core.genericunit.LabelReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.IntentReferenceinModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.query.DescriptionUnitHelper;
import org.eclipse.mylyn.docs.intent.core.query.IntentHelper;
import org.eclipse.mylyn.docs.intent.core.query.StructuredElementHelper;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Specific item provider for the outline view.
 * 
 * @author <a href="mailto:jonathan.musset@obeo.fr">Jonathan Musset</a>
 */
public class IntentOutlinePageItemProvider extends ReflectiveItemProvider {

	private static final int STRUCTURED_ELEMENT_TITLE_MAXLENGTH = 50;

	/**
	 * Indicates if this content provider will have to hide description units content.
	 */
	private boolean hideDescriptionUnitsContent;

	/**
	 * Constructor.
	 * 
	 * @param adapterFactory
	 *            is the adapter factory
	 */
	public IntentOutlinePageItemProvider(IntentOutlinePageItemProviderAdapterFactory adapterFactory) {
		this(adapterFactory, false);
	}

	/**
	 * constructor.
	 * 
	 * @param adapterFactory
	 *            is the adapter factory
	 * @param hideDescriptionUnitsContent
	 *            indicates if this content provider will have to hide description units content
	 */
	public IntentOutlinePageItemProvider(IntentOutlinePageItemProviderAdapterFactory adapterFactory,
			boolean hideDescriptionUnitsContent) {
		super(adapterFactory);
		this.hideDescriptionUnitsContent = hideDescriptionUnitsContent;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.edit.provider.ReflectiveItemProvider#getImage(java.lang.Object)
	 */
	@Override
	public Object getImage(Object object) {
		String imagePath = null;
		Image returnedImage = null;

		if (object instanceof ExternalContentReference) {
			EObject externalContent = ((ExternalContentReference)object).getExternalContent();
			if (externalContent != null) {
				IItemLabelProvider labeProvider = (IItemLabelProvider)new ComposedAdapterFactory(
						ComposedAdapterFactory.Descriptor.Registry.INSTANCE).adapt(externalContent,
						IItemLabelProvider.class);
				if (labeProvider != null) {
					return labeProvider.getImage(externalContent);
				}
			}
		}

		imagePath = getImageForStructureElement(object);

		if (imagePath == null) {
			imagePath = getImageForDescriptionUnitElement(object);
		}

		if (imagePath == null) {
			imagePath = getImageForModelingUnitElement(object);
		}

		if (imagePath != null) {
			returnedImage = IntentEditorActivator.getDefault().getImage("icon/outline/" + imagePath); //$NON-NLS-1$
		} else {
			returnedImage = IntentEditorActivator.getDefault().getImage("icon/outline/default.gif");
		}

		// We decorate the image according to errors and warning
		returnedImage = decorateImageAccordingToStatus(returnedImage, object);
		return returnedImage;
	}

	/**
	 * Returns the image name for the given description unit element.
	 * 
	 * @param object
	 *            a description unit element
	 * @return the image name
	 */
	private String getImageForDescriptionUnitElement(Object object) {
		String imagePath = null;
		if (object instanceof DescriptionUnit) {
			imagePath = "descriptionunit.gif"; //$NON-NLS-1$
		}

		if (object instanceof DescriptionBloc) {
			imagePath = "descriptionBloc.gif"; //$NON-NLS-1$
		}

		if ((object instanceof LabelReferenceInstruction) || (object instanceof IntentReferenceInstruction)) {
			imagePath = "labelorreference.gif"; //$NON-NLS-1$
		}

		if (object instanceof LabelDeclaration) {
			imagePath = "labdef.gif"; //$NON-NLS-1$
		}
		return imagePath;
	}

	/**
	 * Returns the image name for the given modeling unit element.
	 * 
	 * @param object
	 *            a modeling unit element
	 * @return the image name
	 */
	private String getImageForModelingUnitElement(Object object) {
		String imagePath = null;
		if (object instanceof ModelingUnit) {
			imagePath = "modelingunit.png"; //$NON-NLS-1$
		}

		if (object instanceof StructuralFeatureAffectation) {
			imagePath = "modelingunit_affect.png"; //$NON-NLS-1$
		}

		if (object instanceof InstanciationInstruction) {
			imagePath = "modelingunit_new_element.png"; //$NON-NLS-1$
		}

		if (object instanceof ContributionInstruction) {
			imagePath = "modelingunit_contribution.png"; //$NON-NLS-1$
		}

		if (object instanceof ReferenceValueForStructuralFeature) {
			imagePath = "modelingunit_ref.png"; //$NON-NLS-1$
		}

		if (object instanceof NativeValueForStructuralFeature) {
			imagePath = "modelingunit_value.gif"; //$NON-NLS-1$
		}

		if (object instanceof ResourceDeclaration) {
			imagePath = "modelingunit_resource.gif"; //$NON-NLS-1$
		}

		if (object instanceof ModelingUnitInstructionReference) {
			imagePath = "modelingunit_ref.png"; //$NON-NLS-1$
		}
		return imagePath;
	}

	/**
	 * Returns the image name for the given structure element.
	 * 
	 * @param object
	 *            a structure element
	 * @return the image name
	 */
	private String getImageForStructureElement(Object object) {
		String imagePath = null;
		if (object instanceof IntentDocument) {
			imagePath = "document.gif"; //$NON-NLS-1$
		}
		if (object instanceof IntentChapter) {
			imagePath = "chapter.gif"; //$NON-NLS-1$
		}
		if (object instanceof IntentSection) {
			imagePath = "section.gif"; //$NON-NLS-1$
		}
		return imagePath;
	}

	/**
	 * Use the status list associated to the given element to create (or not) error and warning images.
	 * 
	 * @param baseImage
	 *            the base image
	 * @param element
	 *            the element that can contains status
	 * @return the decorated image
	 */
	// FIXME dispose the image when necessary
	private Image decorateImageAccordingToStatus(Image baseImage, Object element) {

		Image decoratedImage = baseImage;
		if (element instanceof IntentGenericElement) {

			Iterator<CompilationStatus> statusIterator = IntentHelper.getAllStatus(
					(IntentGenericElement)element).iterator();
			boolean foundError = false;
			boolean foundWarning = false;
			boolean foundSyncWarning = false;

			while (!foundError & statusIterator.hasNext()) {
				CompilationStatus status = statusIterator.next();
				foundError = status.getSeverity().equals(CompilationStatusSeverity.ERROR);
				if (status.getSeverity().equals(CompilationStatusSeverity.WARNING)) {
					foundSyncWarning = foundSyncWarning
							|| status.getType() == CompilationMessageType.SYNCHRONIZER_WARNING;

					if (status.getType() != CompilationMessageType.SYNCHRONIZER_WARNING) {
						foundWarning = true;
					}
				}
			}

			String imagePath = null;
			if (foundSyncWarning) {
				imagePath = ISharedImages.IMG_ELCL_SYNCED_DISABLED;
			}

			if (foundWarning) {
				imagePath = ISharedImages.IMG_DEC_FIELD_WARNING;
			}
			if (foundError) {
				imagePath = ISharedImages.IMG_DEC_FIELD_ERROR;
			}

			if (imagePath != null) {
				ImageDescriptor errorDescriptor = PlatformUI.getWorkbench().getSharedImages()
						.getImageDescriptor(imagePath);
				decoratedImage = new DecorationOverlayIcon(baseImage, errorDescriptor,
						IDecoration.BOTTOM_LEFT).createImage();
			}

		}
		return decoratedImage;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.edit.provider.ReflectiveItemProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object object) {
		StringBuffer text = new StringBuffer();
		if (object instanceof EObject) {
			EObject eObject = (EObject)object;
			EClass eClass = eObject.eClass();

			if (eClass.getEPackage() == ModelingUnitPackage.eINSTANCE) {
				getTextForModelingUnitElement(text, eObject, eClass);
			}
			if (eClass.getEPackage() == IntentDocumentPackage.eINSTANCE) {
				getTextForStructureElement(object, text, eClass);
			}

			if (eClass.getEPackage() == DescriptionUnitPackage.eINSTANCE) {
				getTextForDescriptionUnitElement(object, text, eClass);
			}

			if (eClass.getEPackage() == GenericUnitPackage.eINSTANCE) {
				getTextForGenericUnitElement(object, text, eClass);
			}
		}
		return text.toString().trim();
	}

	/**
	 * Sets the text for the given description unit element.
	 * 
	 * @param object
	 *            the description unit element
	 * @param text
	 *            the string buffer to fill
	 * @param eClass
	 *            the element type
	 */
	private void getTextForDescriptionUnitElement(Object object, StringBuffer text, EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DescriptionUnitPackage.DESCRIPTION_UNIT:
				// If we have to hide the content of the description unit, we return a sample text
				if (this.hideDescriptionUnitsContent) {
					text.append("Description zone");
				} else {
					text.append(DescriptionUnitHelper.getDescriptionUnitTitle((DescriptionUnit)object,
							STRUCTURED_ELEMENT_TITLE_MAXLENGTH));
				}
				break;
			case DescriptionUnitPackage.DESCRIPTION_BLOC:
				text.append(DescriptionUnitHelper.getDescriptionBlocTitle((DescriptionBloc)object,
						STRUCTURED_ELEMENT_TITLE_MAXLENGTH));
				break;
			default:
				text.append(eClass.getName());
				break;
		}
	}

	/**
	 * Sets the text for the given generic unit element.
	 * 
	 * @param object
	 *            the generic unit element
	 * @param text
	 *            the string buffer to fill
	 * @param eClass
	 *            the element type
	 */
	private void getTextForGenericUnitElement(Object object, StringBuffer text, EClass eClass) {
		switch (eClass.getClassifierID()) {
			case GenericUnitPackage.LABEL_DECLARATION:
				LabelDeclaration label = (LabelDeclaration)object;
				text.append("new Label " + label.getLabelValue());
				break;
			case GenericUnitPackage.LABEL_REFERENCE_INSTRUCTION:
				LabelReferenceInstruction labelRef = (LabelReferenceInstruction)object;
				text.append("reference to " + labelRef.getIntentHref());
				break;
			case GenericUnitPackage.INTENT_REFERENCE_INSTRUCTION:
				IntentReferenceInstruction ref = (IntentReferenceInstruction)object;
				text.append("reference to " + ref.getIntentHref());
				break;

			default:
				text.append(eClass.getName());
				break;

		}
	}

	/**
	 * Sets the text for the given structure unit element.
	 * 
	 * @param object
	 *            the structure unit element
	 * @param text
	 *            the string buffer to fill
	 * @param eClass
	 *            the element type
	 */
	private void getTextForStructureElement(Object object, StringBuffer text, EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IntentDocumentPackage.INTENT_CHAPTER:
			case IntentDocumentPackage.INTENT_SECTION:

				String title = StructuredElementHelper.getTitle((IntentStructuredElement)object,
						STRUCTURED_ELEMENT_TITLE_MAXLENGTH);

				if (title.length() > 0) {
					text.append(title);
				} else {
					text.append("Untitled " + eClass.getName().replace("Intent", ""));
				}
				break;

			default:
				text.append(eClass.getName().replace("Intent", ""));
				break;
		}
	}

	/**
	 * Sets the text for the given modeling unit element.
	 * 
	 * @param text
	 *            the string buffer to fill
	 * @param eObject
	 *            the modeling unit element
	 * @param eClass
	 *            the element type
	 */
	private void getTextForModelingUnitElement(StringBuffer text, EObject eObject, EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelingUnitPackage.MODELING_UNIT:
				String name = ((ModelingUnit)eObject).getUnitName();
				if (name != null && name.length() > 0) {
					text.append(name);
				} else {
					text.append("Untitled Modeling Unit");
				}
				break;

			case ModelingUnitPackage.MODELING_UNIT_INSTRUCTION_REFERENCE:
				text.append(((ModelingUnitInstructionReference)eObject).getIntentHref());
				break;
			case ModelingUnitPackage.STRUCTURAL_FEATURE_AFFECTATION:
				text.append(((StructuralFeatureAffectation)eObject).getName());
				break;

			case ModelingUnitPackage.NATIVE_VALUE_FOR_STRUCTURAL_FEATURE:
				String textValue = ((NativeValueForStructuralFeature)eObject).getValue();
				if (textValue.startsWith("\"")) {
					textValue = textValue.substring(1);
				}

				if (textValue.endsWith("\"")) {
					textValue = textValue.substring(0, textValue.length() - 1);
				}
				text.append(textValue);
				break;
			case ModelingUnitPackage.REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE:
				text.append(((ReferenceValueForStructuralFeature)eObject).getInstanciationReference()
						.getInstanceName() + " (Reference to)");
				break;

			case ModelingUnitPackage.INSTANCIATION_INSTRUCTION:
				InstanciationInstruction instruction = (InstanciationInstruction)eObject;
				String instanceName = instruction.getName();
				if (instanceName != null && instanceName.length() > 0) {
					text.append(instanceName + " : " + instruction.getMetaType().getTypeName());
				} else {
					text.append("? : " + instruction.getMetaType().getTypeName());
				}

				break;

			case ModelingUnitPackage.CONTRIBUTION_INSTRUCTION:
				text.append(((ContributionInstruction)eObject).getContributionReference().getIntentHref());
				break;

			case ModelingUnitPackage.EXTERNAL_CONTENT_REFERENCE:
				text.append(((ExternalContentReference)eObject).getUri());
				break;

			case ModelingUnitPackage.RESOURCE_DECLARATION:
				String resourceName = ((ResourceDeclaration)eObject).getName();
				if (resourceName != null && resourceName.length() > 0) {
					text.append(resourceName);
				} else {
					text.append("Untitled");
				}
				break;

			case ModelingUnitPackage.INTENT_REFERENCEIN_MODELING_UNIT:
				IntentReferenceinModelingUnit reference = (IntentReferenceinModelingUnit)eObject;
				text.append("Reference to Section " + reference.getIntentHref());
				if (reference.getReferencedElement() != null) {
					text.append(getText(reference.getReferencedElement()));
				}
				break;
			default:
				text.append(eClass.getName());
				break;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<?> getChildren(Object object) {
		Collection<IntentGenericElement> childrens = new LinkedHashSet<IntentGenericElement>();

		// An object has children only if it's a Genericelement
		if (object instanceof IntentGenericElement) {

			// A children is a IntentGenericElement
			for (EObject potentialChildren : ((EObject)object).eContents()) {
				if (potentialChildren instanceof IntentGenericElement) {
					childrens.add((IntentGenericElement)potentialChildren);
					childrens
							.addAll((Collection<? extends IntentGenericElement>)getChildren(potentialChildren));
				}
			}
		}
		return childrens;
	}
}
