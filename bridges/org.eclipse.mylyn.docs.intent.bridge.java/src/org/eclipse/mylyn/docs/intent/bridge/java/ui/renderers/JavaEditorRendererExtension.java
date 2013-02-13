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
package org.eclipse.mylyn.docs.intent.bridge.java.ui.renderers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.ISourceReference;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.mylyn.docs.intent.bridge.java.Classifier;
import org.eclipse.mylyn.docs.intent.bridge.java.Field;
import org.eclipse.mylyn.docs.intent.bridge.java.Method;
import org.eclipse.mylyn.docs.intent.bridge.java.resource.factory.JavaClassExplorer;
import org.eclipse.mylyn.docs.intent.bridge.java.resource.factory.JavaResourceFactory;
import org.eclipse.mylyn.docs.intent.bridge.java.util.JavaBridgeSerializer;
import org.eclipse.mylyn.docs.intent.bridge.java.util.JavaBridgeUtils;
import org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ResourceTransfer;

/**
 * An {@link IEditorRendererExtension} customizing the way java elements referenced through
 * {@link ExternalContentReference}s are displayed and opened by the Intent hyperlink detector.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class JavaEditorRendererExtension implements IEditorRendererExtension {

	private static final int JAVA_IMAGE_HEIGHT_MARGIN = 5;

	private static final int JAVA_IMAGE_WIDTH = 800;

	/**
	 * Default constructor.
	 */
	public JavaEditorRendererExtension() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension#isRendererFor(org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference)
	 */
	public boolean isRendererFor(ExternalContentReference externalContentReference) {
		return JavaBridgeUtils.isHandledByJavaBridge(URI.createURI(externalContentReference.getUri()
				.toString().trim()));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension#openEditor(org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference)
	 */
	public boolean openEditor(ExternalContentReference externalContentReference) {
		URI javaElementURI = URI.createURI(externalContentReference.getUri().toString().trim());

		// Step 1: open editor
		IFile javaFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(javaElementURI.trimFragment().toString()));
		FileEditorInput editorInput = new FileEditorInput(javaFile);
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
				.getDefaultEditor(javaElementURI.trimFragment().lastSegment());
		IEditorPart openedEditor = null;
		if (desc != null) {
			try {
				openedEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.openEditor(editorInput, desc.getId());
			} catch (PartInitException e) {
				IntentUiLogger.logError(e);
			}
		}

		// Step 2: select element described by URI (if any)
		if (openedEditor != null && javaElementURI.hasFragment()) {
			updateOpenedEditorSelection(openedEditor, javaFile, javaElementURI);
		}
		// Always return true so that Intent does not try to open an editor with default behavior
		return true;
	}

	/**
	 * Selects the java element (e.g. method, field...) described by the given {@link URI}.
	 * 
	 * @param openedEditor
	 *            the editor to update
	 * @param javaFile
	 *            the java file
	 * @param javaElementURI
	 *            the {@link URI} of the element to select
	 */
	private void updateOpenedEditorSelection(IEditorPart openedEditor, IFile javaFile, URI javaElementURI) {
		EObject eJavaElement = new JavaResourceFactory().createResource(javaElementURI.trimFragment())
				.getEObject(javaElementURI.fragment());
		try {
			IType javaType = ((ICompilationUnit)JavaCore.create(javaFile)).getTypes()[0];

			ISourceReference matchingElement = null;
			if (eJavaElement instanceof Classifier) {
				matchingElement = javaType;
			} else if (eJavaElement instanceof Method) {
				for (IMethod method : javaType.getMethods()) {
					if (method.getElementName().equals(((Method)eJavaElement).getSimpleName())) {
						// Todo consider parameters
						matchingElement = method;
					}
				}
			} else if (eJavaElement instanceof Field) {
				matchingElement = javaType.getField(((Field)eJavaElement).getName());
			}
			if (matchingElement != null) {
				ITextSelection textSelection = new TextSelection(
						matchingElement.getSourceRange().getOffset(), matchingElement.getSourceRange()
								.getLength());
				openedEditor.getEditorSite().getSelectionProvider().setSelection(textSelection);
			}
		} catch (JavaModelException e) {
			// Silent catch, element will not be selected
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension#getAdditionalTransfers()
	 */
	public Collection<Transfer> getAdditionalTransfers() {
		// Adding the Resource transfer to be able to directly drop java files inside the Intent editor
		Collection<Transfer> transfers = new ArrayList<Transfer>();
		transfers.add(ResourceTransfer.getInstance());
		transfers.add(LocalSelectionTransfer.getTransfer());
		return transfers;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension#getEObjectsFromDropTargetEvent(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public Collection<? extends EObject> getEObjectsFromDropTargetEvent(DropTargetEvent event) {
		Collection<EObject> eObjects = new LinkedHashSet<EObject>();
		try {
			// If directly dropping an IFile
			if (event.data instanceof IResource[]) {
				IResource[] droppedResources = (IResource[])event.data;
				for (int i = 0; i < droppedResources.length; i++) {
					eObjects.add(getJavaFactoryResourceFromIResource(droppedResources[i]).getContents()
							.iterator().next());
				}
			} else if (event.data instanceof IStructuredSelection) {
				// If dropping a selection of IMember or ICompilation Unit
				Iterator iterator = ((IStructuredSelection)event.data).iterator();
				while (iterator.hasNext()) {
					Object element = iterator.next();

					if (element instanceof IMethod || element instanceof IField) {
						String elementID = JavaClassExplorer.getMemberID((IMember)element);
						String elementFragment = "//";
						if (element instanceof IMethod) {
							elementFragment += "@methods";
						} else if (element instanceof IField) {
							elementFragment += "@fields";
						}
						elementFragment += "[name='" + elementID + "']";
						Resource javaResource = getJavaFactoryResourceFromIResource(((IMember)element)
								.getResource());
						if (elementID != null) {
							eObjects.add(javaResource.getEObject(elementFragment));
						} else {
							eObjects.add(javaResource.getContents().iterator().next());
						}

					} else if (element instanceof ICompilationUnit) {
						eObjects.add(getJavaFactoryResourceFromIResource(
								((ICompilationUnit)element).getResource()).getContents().iterator().next());
					} else if (element instanceof IType) {
						eObjects.add(getJavaFactoryResourceFromIResource(((IType)element).getResource())
								.getContents().iterator().next());
					}
				}
			}
		} catch (IllegalArgumentException e) {
			// Nothing to do, the drop event was not valid for this java extension
		} catch (JavaModelException e) {
			IntentUiLogger.logError(e);
		}
		return eObjects;
	}

	/**
	 * Returns the {@link Resource} corresponding to the given java {@link IFile}.
	 * 
	 * @param resource
	 *            the {@link IResource}
	 * @return the {@link Resource} corresponding to the given java {@link IFile}
	 */
	Resource getJavaFactoryResourceFromIResource(IResource resource) {
		if (!"java".equals(resource.getFileExtension())) {
			throw new IllegalArgumentException();
		}
		return new JavaResourceFactory().createResource(URI.createURI(resource.getFullPath().toString()
				.replaceFirst("/", "")));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.renderers.IEditorRendererExtension#getImage(org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference)
	 */
	public Image getImage(ExternalContentReference externalContentReference) {
		Image image = null;
		if (externalContentReference.getExternalContent() != null) {

			// Determine string to render
			String javaFileAsText = new JavaBridgeSerializer().doSwitch(externalContentReference
					.getExternalContent());
			String javadoc = "";

			Display display = Display.getDefault();
			int fontHeight = new GC(new Image(display, 1, 1)).getFontMetrics().getHeight();
			if (javaFileAsText.startsWith("/**")) {
				javadoc = javaFileAsText.substring(0, javaFileAsText.indexOf("*/") + 3);
				javaFileAsText = javaFileAsText.replace(javadoc, "");
				javadoc = javadoc.replace("/**", "").replace("*/", "").trim();
			}
			int textWithoutJavaDocHeight = fontHeight + JAVA_IMAGE_HEIGHT_MARGIN;
			int imageHeight = fontHeight * (javadoc.split("\n").length) + textWithoutJavaDocHeight;
			image = new Image(display, JAVA_IMAGE_WIDTH, imageHeight);

			GC gc = new GC(image);

			// Render icon
			int iconWidth = 5;
			gc.setForeground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
			IItemLabelProvider labeProvider = (IItemLabelProvider)new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE).adapt(
					externalContentReference.getExternalContent(), IItemLabelProvider.class);
			if (labeProvider != null) {
				Object iconURL = labeProvider.getImage(externalContentReference.getExternalContent());
				Image icon = ExtendedImageRegistry.getInstance().getImage(iconURL);

				if (icon != null) {
					gc.drawImage(icon, 0, 5);
					iconWidth += icon.getImageData().width + 2;
				}
			}

			// Render text
			gc.drawText(javaFileAsText, iconWidth, 5);

			// Render javadoc
			if (javadoc.length() > 1) {
				gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				gc.drawText(javadoc, 2, textWithoutJavaDocHeight);

			}

			gc.dispose();
		}

		return image;
	}
}
