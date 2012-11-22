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
package org.eclipse.mylyn.docs.intent.client.ui.ide.propertytester;

import java.util.Collection;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentSelectionUtil;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;

/**
 * Returns true if the tester element is associated to an {@link IntentStructuredElement}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IsAssociatedToIntentStructuredElementTester extends PropertyTester {

	/**
	 * Default constructor.
	 */
	public IsAssociatedToIntentStructuredElementTester() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String,
	 *      java.lang.Object[], java.lang.Object)
	 */
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		Collection<IntentStructuredElement> correspondingIntentElements = IntentSelectionUtil
				.getIntentElements(receiver);
		return (correspondingIntentElements.size() == 1)
				&& (correspondingIntentElements.iterator().next() instanceof IntentStructuredElement);
	}
}
