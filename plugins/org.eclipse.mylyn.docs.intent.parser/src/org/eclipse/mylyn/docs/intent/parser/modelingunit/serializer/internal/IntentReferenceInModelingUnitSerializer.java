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
package org.eclipse.mylyn.docs.intent.parser.modelingunit.serializer.internal;

import org.eclipse.mylyn.docs.intent.core.modelingunit.IntentReferenceInModelingUnit;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.serializer.ModelingUnitSerializer;

/**
 * Returns the serialized form of the given ModelingUnit ReferenceinModelingUnit element.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentReferenceInModelingUnitSerializer {

	/**
	 * ReferenceinModelingUnitSerializer constructor.
	 */
	private IntentReferenceInModelingUnitSerializer() {

	}

	/**
	 * Return the textual form of the given ModelingUnit ReferenceinModelingUnit.
	 * 
	 * @param ref
	 *            the element to serialize
	 * @param modelingUnitElementDispatcher
	 *            the modelingUnitElementDispatcher
	 * @return the textual form of the given ModelingUnit ReferenceinModelingUnit.
	 */
	public static String render(IntentReferenceInModelingUnit ref,
			ModelingUnitElementDispatcher modelingUnitElementDispatcher) {
		String renderedForm = "@see" + ModelingUnitSerializer.WHITESPACE;
		renderedForm += ModelingUnitSerializer.QUOTE + ref.getIntentHref() + ModelingUnitSerializer.QUOTE;

		if (ref.getTextToPrint() != null && ref.getTextToPrint().length() > 0) {
			renderedForm += ModelingUnitSerializer.WHITESPACE + ModelingUnitSerializer.QUOTE
					+ ref.getTextToPrint() + ModelingUnitSerializer.QUOTE;
		}

		if (ref.isLineBreak()) {
			renderedForm += ModelingUnitSerializer.LINE_BREAK;
		}

		modelingUnitElementDispatcher.getPositionManager().setPositionForInstruction(ref,
				modelingUnitElementDispatcher.getCurrentOffset(), renderedForm.length());
		modelingUnitElementDispatcher.setCurrentOffset(modelingUnitElementDispatcher.getCurrentOffset()
				+ renderedForm.length());
		return renderedForm;
	}
}
