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
package org.eclipse.mylyn.docs.intent.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.markup.markup.Paragraph;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitFormatter;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.serializer.ModelingUnitSerializer;
import org.eclipse.mylyn.docs.intent.serializer.descriptionunit.DescriptionUnitSerializer;
import org.eclipse.mylyn.docs.intent.serializer.internal.IntentElementSerializer;

/**
 * High-level serializer that delegates the serialization to the correct serializer according to the possible
 * entry points for a Modeling Unit.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentSerializer {

	/**
	 * The position manager that handle the mapping between Intent element to positions.
	 */
	private IntentPositionManager positionManager;

	/**
	 * Serializer for IntentStructuredElements : IntentDocument, Chapter and Section.
	 */
	private IntentElementSerializer documentSerializer;

	/**
	 * Serializer for Modeling Units.
	 */
	private ModelingUnitSerializer modelingUnitSerializer;

	/**
	 * Serializer for Description Units.
	 */
	private DescriptionUnitSerializer descriptionUnitSerializer;

	/**
	 * IntentSerializer constructor.
	 */
	public IntentSerializer() {
		this.documentSerializer = new IntentElementSerializer(null, null);
		this.modelingUnitSerializer = new ModelingUnitSerializer();
		this.descriptionUnitSerializer = new DescriptionUnitSerializer();
		this.positionManager = new IntentPositionManager();
	}

	/**
	 * IntentSerializer constructor.
	 * 
	 * @param modelingUnitPrefixDecoration
	 *            the String to use for prefixing each Modeling Unit (leave null if no use)
	 * @param modelingUnitSuffixDecoration
	 *            the String to use for suffixing each Modeling Unit (leave null if no use)
	 */
	public IntentSerializer(String modelingUnitPrefixDecoration, String modelingUnitSuffixDecoration) {
		this.documentSerializer = new IntentElementSerializer(modelingUnitPrefixDecoration,
				modelingUnitSuffixDecoration);
		this.modelingUnitSerializer = new ModelingUnitSerializer();
		this.descriptionUnitSerializer = new DescriptionUnitSerializer();
		this.positionManager = new IntentPositionManager();
	}

	/**
	 * Clears all informations contained by all used serializers (elements positions, current offset...).
	 */
	private void clear() {
		this.positionManager.clear();
		this.documentSerializer.clear();
		this.descriptionUnitSerializer.clear();
	}

	/**
	 * Serialize the given content and return the textual form of the given element.
	 * 
	 * @param elementToSerialize
	 *            the Intent entity to serialize (can be a IntentDocument, a Section, a Chapter, a Modeling
	 *            Unit or a Description Unit).
	 * @return the textual form of the given elementToSerialize
	 */
	public synchronized String serialize(EObject elementToSerialize) {
		clear();
		if (elementToSerialize == null) {
			throw new IllegalArgumentException("Cannot serialize a null element.");
		}

		String serializedForm = null;

		// We have 3 possibilities for the type of the element to serialize :
		if (elementToSerialize instanceof ModelingUnit) {
			serializedForm = modelingUnitSerializer.serialize((ModelingUnit)elementToSerialize);
			serializedForm = ModelingUnitFormatter.indentAccordingToBrackets(modelingUnitSerializer,
					serializedForm);
			positionManager.addIntentPositionManagerInformations(modelingUnitSerializer.getPositionManager());

		}

		// If it starts with a IntentDocument's Keyword (like "Section, Document, Chapter..."
		if (elementToSerialize instanceof IntentStructuredElement) {
			documentSerializer.setCurrentOffset(0);
			documentSerializer.setCurrentIndendationLevel(0);
			serializedForm = documentSerializer.serialize(elementToSerialize);
			this.positionManager
					.addIntentPositionManagerInformations(documentSerializer.getPositionManager());

		}
		if (elementToSerialize instanceof DescriptionUnit || elementToSerialize instanceof Paragraph) {
			// In the other cases, we consider that the given contentToParse is a DescriptionUnit
			documentSerializer.setCurrentOffset(0);
			documentSerializer.setCurrentIndendationLevel(0);
			if (elementToSerialize instanceof DescriptionUnit) {
				serializedForm = descriptionUnitSerializer.serialize((DescriptionUnit)elementToSerialize);
				this.positionManager.addIntentPositionManagerInformations(descriptionUnitSerializer
						.getPositionManager());
			} else {
				serializedForm = descriptionUnitSerializer.serialize((Paragraph)elementToSerialize);
			}

		}

		if (serializedForm == null) {
			throw new IllegalArgumentException(
					"The element to serialize must be a valid Intent element, and not "
							+ elementToSerialize.eClass().getName());
		}
		return serializedForm;

	}

	public synchronized IntentPositionManager getPositionManager() {
		return positionManager;
	}
}
