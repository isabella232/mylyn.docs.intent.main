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
package org.eclipse.mylyn.docs.intent.client.ui.editor.renderers;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;

/**
 * An extension allowing to:
 * <ul>
 * <li>Override the behavior of the
 * {@link org.eclipse.mylyn.docs.intent.client.ui.editor.hyperlinks.OpenWorkingCopyResourceHyperLink} to
 * change the way editors are opened (e.g. open a Java editor when the
 * {@link org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction} references a Java element).
 * </li>
 * </ul>
 * .
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public interface IEditorRendererExtension {

	/**
	 * Indicates if this renderer applies on the given {@link ExternalContentReference}.
	 * 
	 * @param externalContentReference
	 *            the {@link ExternalContentReference} to render
	 * @return true if this renderer applies on the given {@link ExternalContentReference}, false otherwise
	 */
	boolean isRendererFor(ExternalContentReference externalContentReference);

	/**
	 * Overrides the default behavior of the
	 * {@link org.eclipse.mylyn.docs.intent.client.ui.editor.hyperlinks.OpenWorkingCopyResourceHyperLink}
	 * (which is to create an URIEditorInput and open the editor associated to the URI's file extension on
	 * this input).
	 * 
	 * @param externalContentReference
	 *            the {@link ExternalContentReference} to render
	 * @return true if the default behavior has been overridden, false if the default behavior should be
	 *         applied
	 */
	boolean openEditor(ExternalContentReference externalContentReference);

	/**
	 * Returns additional transfers that should be supported by the intent editor. This allow for example to
	 * drag a java class directly inside the intent document.
	 * 
	 * @return the additional transfers that should be supported by the Intent editor
	 */
	Collection<Transfer> getAdditionalTransfers();

	/**
	 * Returns the {@link EObject}s corresponding to the given {@link DropTargetEvent}, that will be added to
	 * the IntentDocument.
	 * 
	 * @param event
	 *            the {@link DropTargetEvent}
	 * @return the {@link EObject}s corresponding to the given {@link DropTargetEvent}
	 */
	Collection<? extends EObject> getEObjectsFromDropTargetEvent(DropTargetEvent event);

	/**
	 * Returns an image corresponding to the given {@link ExternalContentReference} (e.g. the content of a
	 * java class as an image), or null if no image should be displayed.
	 * 
	 * @param reference
	 *            the {@link ExternalContentReference} to render as image
	 * @return
	 */
	Image getImage(ExternalContentReference reference);

}
