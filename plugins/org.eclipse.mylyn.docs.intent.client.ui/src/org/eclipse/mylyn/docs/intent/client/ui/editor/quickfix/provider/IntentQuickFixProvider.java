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
package org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.provider;

import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.AbstractIntentFix;

/**
 * Allows to contribute additional quick-fixes for fixing Intent issues (e.g. externalize the String of a java
 * class...).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public interface IntentQuickFixProvider {

	/**
	 * Indicates if this {@link IntentQuickFixProvider} is able to create a quick fix for the given
	 * {@link IntentAnnotation}.
	 * 
	 * @param annotation
	 *            the {@link IntentAnnotation} helding the issue to fix
	 * @return true if this {@link IntentQuickFixProvider} is able to create a quick fix for the given
	 *         {@link IntentAnnotation}, false otheriwe
	 */
	boolean canCreateQuickFix(IntentAnnotation annotation);

	/**
	 * Returns an {@link AbstractIntentFix} allowing to fix the issue held by the given
	 * {@link IntentAnnotation}.
	 * 
	 * @param annotation
	 *            the {@link IntentAnnotation} helding the issue to fix
	 * @return an {@link AbstractIntentFix} allowing to fix the issue held by the given
	 *         {@link IntentAnnotation}
	 */
	AbstractIntentFix createQuickFix(IntentAnnotation annotation);
}
