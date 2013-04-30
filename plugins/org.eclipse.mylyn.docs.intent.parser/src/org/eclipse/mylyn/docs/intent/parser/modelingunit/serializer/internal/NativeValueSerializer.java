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

import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValue;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.serializer.ModelingUnitSerializer;

/**
 * Returns the serialized form of the given {@link NativeValue}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class NativeValueSerializer {

	/**
	 * NativeValueForStructuralFeatureSerializerSerializer constructor.
	 */
	private NativeValueSerializer() {

	}

	/**
	 * Return the textual form of the given {@link NativeValue}.
	 * 
	 * @param nativeValueForStructuralFeature
	 *            the element to serialize
	 * @param modelingUnitElementDispatcher
	 *            the modelingUnitElementDispatcher
	 * @return the textual form of the given ModelingUnit NativeValueSerializer.
	 */
	public static String render(NativeValue nativeValueForStructuralFeature,
			ModelingUnitElementDispatcher modelingUnitElementDispatcher) {
		String renderedForm = "";
		renderedForm += nativeValueForStructuralFeature.getValue();

		if (nativeValueForStructuralFeature.isLineBreak()) {
			renderedForm += ModelingUnitSerializer.LINE_BREAK;
		}

		modelingUnitElementDispatcher.getPositionManager().setPositionForInstruction(
				nativeValueForStructuralFeature, modelingUnitElementDispatcher.getCurrentOffset(),
				renderedForm.length());
		modelingUnitElementDispatcher.setCurrentOffset(modelingUnitElementDispatcher.getCurrentOffset()
				+ renderedForm.length());

		return renderedForm;
	}
}
