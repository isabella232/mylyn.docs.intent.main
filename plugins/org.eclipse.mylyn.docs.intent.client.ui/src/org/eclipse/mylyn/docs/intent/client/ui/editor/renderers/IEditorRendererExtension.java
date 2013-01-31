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

import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;

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
}
