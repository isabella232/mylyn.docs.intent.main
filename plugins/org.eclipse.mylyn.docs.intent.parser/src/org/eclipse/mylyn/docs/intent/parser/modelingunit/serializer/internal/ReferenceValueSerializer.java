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

import org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValue;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.serializer.ModelingUnitSerializer;

/**
 * Returns the serialized form of the given {@link ReferenceValue}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class ReferenceValueSerializer {

	/**
	 * ReferenceValueForStructuralFeatureSerializerSerializer constructor.
	 */
	private ReferenceValueSerializer() {

	}

	/**
	 * Return the textual form of the given {@link ReferenceValue}.
	 * 
	 * @param referenceValueForStructuralFeature
	 *            the element to serialize
	 * @param dispatcher
	 *            the ModelingUnitElementDispatcher to call
	 * @return the textual form of the given ModelingUnit ReferenceValueSerializer.
	 */
	public static String render(ReferenceValue referenceValueForStructuralFeature,
			ModelingUnitElementDispatcher dispatcher) {

		int initialOffset = dispatcher.getCurrentOffset();
		String renderedForm = ""
				+ dispatcher.doSwitch(referenceValueForStructuralFeature.getInstanciationReference());

		if (referenceValueForStructuralFeature.isLineBreak()) {
			renderedForm += ModelingUnitSerializer.LINE_BREAK;
		}

		dispatcher.getPositionManager().setPositionForInstruction(referenceValueForStructuralFeature,
				initialOffset, renderedForm.length());
		dispatcher.setCurrentOffset(initialOffset + renderedForm.length());
		return renderedForm;
	}
}
