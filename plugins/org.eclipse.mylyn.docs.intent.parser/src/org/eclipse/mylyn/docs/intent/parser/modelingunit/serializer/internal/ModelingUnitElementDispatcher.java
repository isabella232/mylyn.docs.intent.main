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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.IntentSectionReferenceinModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.LabelinModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference;
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
	public String caseLabelinModelingUnit(LabelinModelingUnit object) {
		return LabelinModelingUnitSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseModelingUnit(org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit)
	 */
	@Override
	public String caseModelingUnit(ModelingUnit object) {
		positionManager.clear();

		int initialOffset = this.getCurrentOffset();
		String renderedForm = ModelingUnitParser.MODELING_UNIT_PREFIX;

		if (object.getUnitName() != null && object.getUnitName().length() > 0) {
			renderedForm += ModelingUnitSerializer.WHITESPACE + object.getUnitName()
					+ ModelingUnitSerializer.WHITESPACE;
		}

		if (object.getResource() != null) {
			renderedForm += doSwitch(object.getResource());
		}
		renderedForm += IntentKeyWords.INTENT_LINEBREAK;
		this.setCurrentOffset(initialOffset + renderedForm.length());
		for (UnitInstruction instruction : object.getInstructions()) {
			renderedForm += doSwitch(instruction);
		}

		this.setCurrentOffset(initialOffset + renderedForm.length());
		this.setPositionForInstruction(object, initialOffset, renderedForm.length());

		// Adding the suffix
		return renderedForm + ModelingUnitParser.MODELING_UNIT_SUFFIX + IntentKeyWords.INTENT_LINEBREAK;

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
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseNativeValueForStructuralFeature(org.eclipse.mylyn.docs.intent.core.modelingunit.NativeValueForStructuralFeature)
	 */
	@Override
	public String caseNativeValueForStructuralFeature(NativeValueForStructuralFeature object) {
		return NativeValueForStructuralFeatureSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseNewObjectValueForStructuralFeature(org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature)
	 */
	@Override
	public String caseNewObjectValueForStructuralFeature(NewObjectValueForStructuralFeature object) {
		return NewObjectValueForStructuralFeatureSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseReferenceValueForStructuralFeature(org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature)
	 */
	@Override
	public String caseReferenceValueForStructuralFeature(ReferenceValueForStructuralFeature object) {
		return ReferenceValueForStructuralFeatureSerializer.render(object, this);
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
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseResourceReference(org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceReference)
	 */
	@Override
	public String caseResourceReference(ResourceReference object) {
		return ResourceReferenceSerializer.render(object, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseIntentSectionReferenceinModelingUnit(org.eclipse.mylyn.docs.intent.core.modelingunit.IntentSectionReferenceinModelingUnit)
	 */
	@Override
	public String caseIntentSectionReferenceinModelingUnit(IntentSectionReferenceinModelingUnit object) {
		return IntentSectionReferenceinModelingUnitSerializer.render(object, this);
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
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseTypeReference(org.eclipse.mylyn.docs.intent.core.modelingunit.TypeReference)
	 */
	@Override
	public String caseTypeReference(TypeReference object) {
		return object.getIntentHref();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseInstanciationInstructionReference(org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference)
	 */
	@Override
	public String caseInstanciationInstructionReference(InstanciationInstructionReference object) {
		return object.getIntentHref();
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
	 * Associates the given instruction to the given offset and length.
	 * 
	 * @param instruction
	 *            the instruction to associate with the given position
	 * @param offset
	 *            the offset of the given instruction
	 * @param length
	 *            the lenght of the given instruction
	 */
	public void setPositionForInstruction(EObject instruction, int offset, int length) {
		positionManager.setPositionForInstruction(instruction, offset, length);
	}

	/**
	 * Returns the positionManager associated to this ElementDispatcher.
	 * 
	 * @return the positionManager associated to this ElementDispatcher
	 */
	public IntentPositionManager getPositionManager() {
		return positionManager;
	}

	/**
	 * Handles tabulations added at the given tabOffset.
	 * 
	 * @param tabOffset
	 *            the offset where the tabulations are added
	 * @param nbTabs
	 *            number of tabulations added
	 */
	public void handleTabulations(int tabOffset, int nbTabs) {
		positionManager.handleTabulations(tabOffset, nbTabs);
	}
}
