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

import org.eclipse.mylyn.docs.intent.core.document.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.IntentReferenceInModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.LabelInModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch;
import org.eclipse.mylyn.docs.intent.parser.IntentKeyWords;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.serializer.ModelingUnitSerializer;
import org.eclipse.mylyn.docs.intent.serializer.IntentPositionManager;

/**
 * Call the correct serializer according to a ModelingUnit element's type.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ModelingUnitElementDispatcher extends ModelingUnitSwitch<String> {

	/**
	 * The current Offset of this dispatcher.
	 */
	private int currentOffset;

	/**
	 * Entity used to map element to positions.
	 */
	private IntentPositionManager positionManager;

	/**
	 * ModelingUnitElementDispatcher constructor.
	 */
	public ModelingUnitElementDispatcher() {
		positionManager = new IntentPositionManager();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseAnnotationDeclaration(org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration)
	 */
	@Override
	public String caseAnnotationDeclaration(AnnotationDeclaration object) {
		return AnnotationDeclarationSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseContributionInstruction(org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction)
	 */
	@Override
	public String caseContributionInstruction(ContributionInstruction object) {
		return ContributionInstructionSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseInstanciationInstruction(org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction)
	 */
	@Override
	public String caseInstanciationInstruction(InstanciationInstruction object) {
		return InstanciationInstructionSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseLabelinModelingUnit(org.eclipse.mylyn.docs.intent.core.modelingunit.LabelinModelingUnit)
	 */
	@Override
	public String caseLabelInModelingUnit(LabelInModelingUnit object) {
		return LabelInModelingUnitSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseModelingUnit(org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit)
	 */
	@Override
	public String caseModelingUnit(ModelingUnit object) {
		positionManager.clear();
		StringBuilder renderedForm = new StringBuilder();
		int initialOffset = this.getCurrentOffset();
		renderedForm.append(ModelingUnitParser.MODELING_UNIT_PREFIX);

		if (object.getName() != null && object.getName().length() > 0) {
			renderedForm.append(ModelingUnitSerializer.WHITESPACE + object.getName()
					+ ModelingUnitSerializer.WHITESPACE);
		}

		renderedForm.append(IntentKeyWords.INTENT_LINEBREAK);
		this.setCurrentOffset(initialOffset + renderedForm.length());
		for (UnitInstruction instruction : object.getInstructions()) {
			renderedForm.append(doSwitch(instruction));
		}

		this.setCurrentOffset(initialOffset + renderedForm.length());
		this.getPositionManager().setPositionForInstruction(object, initialOffset, renderedForm.length());

		// Adding the suffix
		renderedForm.append(ModelingUnitParser.MODELING_UNIT_SUFFIX + IntentKeyWords.INTENT_LINEBREAK);
		return renderedForm.toString();

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseModelingUnitInstructionReference(org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference)
	 */
	@Override
	public String caseModelingUnitInstructionReference(ModelingUnitInstructionReference object) {
		return object.getIntentHref();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseNativeValue(org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValue)
	 */
	@Override
	public String caseNativeValue(NativeValue object) {
		return NativeValueSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseNewObjectValue(org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValue)
	 */
	@Override
	public String caseNewObjectValue(NewObjectValue object) {
		return NewObjectValueSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseReferenceValue(org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValue)
	 */
	@Override
	public String caseReferenceValue(ReferenceValue object) {
		return ReferenceValueSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseResourceDeclaration(org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration)
	 */
	@Override
	public String caseResourceDeclaration(ResourceDeclaration object) {
		return ResourceDeclarationSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseIntentReferenceinModelingUnit(org.eclipse.mylyn.docs.intent.core.modelingunit.IntentReferenceinModelingUnit)
	 */
	@Override
	public String caseIntentReferenceInModelingUnit(IntentReferenceInModelingUnit object) {
		return IntentReferenceInModelingUnitSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseStructuralFeatureAffectation(org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation)
	 */
	@Override
	public String caseStructuralFeatureAffectation(StructuralFeatureAffectation object) {
		return StructuralFeatureAffectationSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseExternalContentReference(org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference)
	 */
	@Override
	public String caseExternalContentReference(ExternalContentReference object) {
		return ExternalContentReferenceSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseTypeReference(org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference)
	 */
	@Override
	public String caseTypeReference(TypeReference object) {
		return object.getTypeName();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseInstanciationInstructionReference(org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference)
	 */
	@Override
	public String caseInstanciationInstructionReference(InstanciationInstructionReference object) {
		return object.getInstanceName();
	}

	/**
	 * Returns the current Offset of this dispatcher.
	 * 
	 * @return the current Offset of this dispatcher
	 */
	public int getCurrentOffset() {
		return currentOffset;
	}

	/**
	 * Sets the current Offset of this dispatcher.
	 * 
	 * @param offset
	 *            the current Offset of this dispatcher
	 */
	public void setCurrentOffset(int offset) {
		this.currentOffset = offset;
	}

	/**
	 * Returns the positionManager associated to this ElementDispatcher.
	 * 
	 * @return the positionManager associated to this ElementDispatcher
	 */
	public IntentPositionManager getPositionManager() {
		return positionManager;
	}

}
