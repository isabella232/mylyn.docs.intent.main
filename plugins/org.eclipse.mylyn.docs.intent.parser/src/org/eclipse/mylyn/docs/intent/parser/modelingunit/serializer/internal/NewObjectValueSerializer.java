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

import org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValue;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.serializer.ModelingUnitSerializer;

/**
 * Returns the serialized form of the given {@link NewObjectValue}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class NewObjectValueSerializer {

	/**
	 * NewObjectValueForStructuralFeatureSerializerSerializer constructor.
	 */
	private NewObjectValueSerializer() {

	}

	/**
	 * Return the textual form of the given {@link NewObjectValue}.
	 * 
	 * @param newObjectValueForStructuralFeature
	 *            the element to serialize
	 * @param dispatcher
	 *            the ModelingUnitElementDispatcher to call
	 * @return the textual form of the given ModelingUnit NewObjectValueSerializer.
	 */
	public static String render(NewObjectValue newObjectValueForStructuralFeature,
			ModelingUnitElementDispatcher dispatcher) {

		int initialOffset = dispatcher.getCurrentOffset();
		String renderedForm = dispatcher.doSwitch(newObjectValueForStructuralFeature.getValue());

		if (newObjectValueForStructuralFeature.isLineBreak()) {
			renderedForm += ModelingUnitSerializer.LINE_BREAK;
		}

		dispatcher.getPositionManager().setPositionForInstruction(newObjectValueForStructuralFeature,
				initialOffset, renderedForm.length());
		dispatcher.setCurrentOffset(initialOffset + renderedForm.length());
		return renderedForm;
	}
}
