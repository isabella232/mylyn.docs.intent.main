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
package org.eclipse.mylyn.docs.intent.serializer.internal;

import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.LabelDeclaration;
import org.eclipse.mylyn.docs.intent.core.document.LabelReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.document.util.IntentDocumentSwitch;
import org.eclipse.mylyn.docs.intent.serializer.genericunit.LabelDeclarationSerializer;
import org.eclipse.mylyn.docs.intent.serializer.genericunit.LabelOrSectionReferenceSerializer;

/**
 * Dispatch the serialization of an element according to its type (acts like an EMF switch).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentDocumentSerializerSwitch extends IntentDocumentSwitch<String> {

	/**
	 * The DocumentSerializer associated to this Switch.
	 */
	private IntentElementSerializer serializer;

	/**
	 * IntentDocumentSerializerSwitch constructor.
	 * 
	 * @param documentSerializer
	 *            the DocumentSerializer associated to this Switch
	 */
	public IntentDocumentSerializerSwitch(IntentElementSerializer documentSerializer) {
		this.serializer = documentSerializer;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.document.util.IntentDocumentSwitch#caseIntentDocument(org.eclipse.mylyn.docs.intent.core.document.IntentDocument)
	 */
	@Override
	public String caseIntentDocument(IntentDocument object) {
		return IntentDocumentSerializer.serialize(object, serializer);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.document.util.IntentDocumentSwitch#caseIntentSection(org.eclipse.mylyn.docs.intent.core.document.IntentSection)
	 */
	@Override
	public String caseIntentSection(IntentSection object) {
		return IntentSectionSerializer.serialize(object, serializer);
	}

	/**
	 * The Prefix to use for prefixing each line.
	 */
	private String tabulationPrefix;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.genericunit.util.GenericUnitSwitch#caseLabelDeclaration(org.eclipse.mylyn.docs.intent.core.genericunit.LabelDeclaration)
	 */
	@Override
	public String caseLabelDeclaration(LabelDeclaration object) {
		return LabelDeclarationSerializer.serialize(object, tabulationPrefix);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.genericunit.util.GenericUnitSwitch#caseLabelReferenceInstruction(org.eclipse.mylyn.docs.intent.core.genericunit.LabelReferenceInstruction)
	 */
	@Override
	public String caseLabelReferenceInstruction(LabelReferenceInstruction object) {
		return LabelOrSectionReferenceSerializer.serialize(object, tabulationPrefix);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.genericunit.util.GenericUnitSwitch#caseIntentReferenceInstruction(org.eclipse.mylyn.docs.intent.core.genericunit.IntentReferenceInstruction)
	 */
	@Override
	public String caseIntentReferenceInstruction(IntentReferenceInstruction object) {
		return LabelOrSectionReferenceSerializer.serialize(object, tabulationPrefix);
	}

	/**
	 * Sets the Prefix to use for prefixing each line.
	 * 
	 * @param tabulationPrefix
	 *            the Prefix to use for prefixing each line
	 */
	public void setTabulationPrefix(String tabulationPrefix) {
		this.tabulationPrefix = tabulationPrefix;
	}
}
