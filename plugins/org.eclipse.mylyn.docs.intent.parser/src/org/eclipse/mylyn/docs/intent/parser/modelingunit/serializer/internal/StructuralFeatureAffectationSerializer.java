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

import org.eclipse.mylyn.docs.intent.core.modelingunit.AffectationOperator;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.serializer.ModelingUnitSerializer;

/**
 * Returns the serialized form of the given ModelingUnit StructuralFeatureAffectation element.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class StructuralFeatureAffectationSerializer {

	/**
	 * StructuralFeatureAffectation constructor.
	 */
	private StructuralFeatureAffectationSerializer() {

	}

	/**
	 * Return the textual form of the given ModelingUnit StructuralFeatureAffectation.
	 * 
	 * @param structuralFeatureAffectation
	 *            the element to serialize
	 * @param dispatcher
	 *            the ModelingUnitElementDispatcher to call
	 * @return the textual form of the given ModelingUnit InstanciationInstruction.
	 */
	public static String render(StructuralFeatureAffectation structuralFeatureAffectation,
			ModelingUnitElementDispatcher dispatcher) {

		int initialOffset = dispatcher.getCurrentOffset();
		StringBuilder renderedForm = new StringBuilder();
		renderedForm.append(structuralFeatureAffectation.getName());
		int declarationLength = renderedForm.length();

		renderedForm.append(ModelingUnitSerializer.WHITESPACE);
		String affectationSymbol = "=";
		if (structuralFeatureAffectation.getUsedOperator().equals(
				AffectationOperator.MULTI_VALUED_AFFECTATION)) {
			affectationSymbol = "+=";
		}

		boolean isMultiValued = structuralFeatureAffectation.getValues().size() > 1;
		if (isMultiValued) {
			renderedForm.append(affectationSymbol + ModelingUnitSerializer.WHITESPACE + "[");
		} else {
			renderedForm.append(affectationSymbol);
		}
		renderedForm.append(ModelingUnitSerializer.WHITESPACE);
		int nbValues = 0;
		for (ValueForStructuralFeature value : structuralFeatureAffectation.getValues()) {
			if (nbValues > 0) {
				renderedForm.append(ModelingUnitSerializer.WHITESPACE + ","
						+ ModelingUnitSerializer.WHITESPACE);
			}

			dispatcher.setCurrentOffset(initialOffset + renderedForm.length());
			renderedForm.append(dispatcher.doSwitch(value));
			nbValues++;
		}
		if (isMultiValued) {
			renderedForm.append(ModelingUnitSerializer.WHITESPACE + "]");
		}
		renderedForm.append(";");

		if (structuralFeatureAffectation.isLineBreak()) {
			renderedForm.append(ModelingUnitSerializer.LINE_BREAK);
		}

		dispatcher.getPositionManager().setPositionForInstruction(structuralFeatureAffectation,
				initialOffset, renderedForm.length(), declarationLength);
		dispatcher.setCurrentOffset(initialOffset + renderedForm.length());
		return renderedForm.toString();
	}
}
