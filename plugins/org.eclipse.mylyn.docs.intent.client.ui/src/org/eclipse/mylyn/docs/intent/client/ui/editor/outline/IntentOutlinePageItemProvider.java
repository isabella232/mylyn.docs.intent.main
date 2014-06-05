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

import java.util.Iterator;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationMessageType;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusSeverity;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.query.IntentHelper;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Specific item provider for the outline view.
 * 
 * @author <a href="mailto:jonathan.musset@obeo.fr">Jonathan Musset</a>
 */
public class IntentOutlinePageItemProvider extends ReflectiveItemProvider {

	/**
	 * Indicates if this content provider will have to hide description units content.
	 */
	private boolean hideDescriptionUnitsContent;

	/**
	 * The {@link ComposedAdapterFactory} used by this item provider.
	 */
	private ComposedAdapterFactory adapterFactory;

	/**
	 * Constructor.
	 * 
	 * @param intentOutlinePageItemProviderAdapterFactory
	 *            is the adapter factory
	 * @param hideDescriptionUnitsContent
	 *            indicates if this content provider will have to hide description units content
	 */
	public IntentOutlinePageItemProvider(
			IntentOutlinePageItemProviderAdapterFactory intentOutlinePageItemProviderAdapterFactory,
			boolean hideDescriptionUnitsContent) {
		super(intentOutlinePageItemProviderAdapterFactory);
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		this.hideDescriptionUnitsContent = hideDescriptionUnitsContent;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.edit.provider.ReflectiveItemProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object object) {
		// We decorate the image according to errors and warning
		final IItemLabelProvider labelProvider = (IItemLabelProvider)adapterFactory.adapt(object,
				IItemLabelProvider.class);
		Image returnedImage = null;
		if (labelProvider != null) {
			ImageDescriptor descriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(
					labelProvider.getImage(object));
			if (descriptor == null) {
				descriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			returnedImage = ExtendedImageRegistry.getInstance().getImage(descriptor);
		}
		if (returnedImage != null) {
			return decorateImageAccordingToStatus(returnedImage, object);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.edit.provider.ReflectiveItemProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object object) {
		String text = "";
		if (this.hideDescriptionUnitsContent && object instanceof DescriptionUnit) {
			return "Paragraph";
		}
		IItemLabelProvider labelProvider = (IItemLabelProvider)adapterFactory.adapt(object,
				IItemLabelProvider.class);
		if (labelProvider != null) {
			text = labelProvider.getText(object);
		}
		return text;
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

			while (!foundError && statusIterator.hasNext()) {
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
}
