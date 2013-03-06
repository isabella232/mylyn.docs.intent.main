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

import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;

/**
 * Returns the serialized form of the given ModelingUnit {@link ExternalContentReference} element.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class ExternalContentReferenceSerializer {

	/**
	 * Private constructor.
	 */
	private ExternalContentReferenceSerializer() {

	}

	/**
	 * Return the textual form of the given ModelingUnit {@link ExternalContentReference}.
	 * 
	 * @param instruction
	 *            the element to serialize
	 * @param modelingUnitElementDispatcher
	 *            the modelingUnitElementDispatcher
	 * @return the textual form of the given ModelingUnit {@link ExternalContentReference}
	 */
	public static String render(ExternalContentReference instruction,
			ModelingUnitElementDispatcher modelingUnitElementDispatcher) {
		String renderedForm = "@ref \"" + instruction.getUri().toString() + "\"";
		modelingUnitElementDispatcher.getPositionManager().setPositionForInstruction(instruction,
				modelingUnitElementDispatcher.getCurrentOffset(), renderedForm.length());
		renderedForm += "\n \n";
		modelingUnitElementDispatcher.setCurrentOffset(modelingUnitElementDispatcher.getCurrentOffset()
				+ renderedForm.length());
		return renderedForm;
	}

}
