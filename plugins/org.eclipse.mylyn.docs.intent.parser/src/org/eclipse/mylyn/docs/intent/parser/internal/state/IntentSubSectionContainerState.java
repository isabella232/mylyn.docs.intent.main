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
package org.eclipse.mylyn.docs.intent.parser.internal.state;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionBloc;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentSubSectionContainer;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;
import org.eclipse.mylyn.docs.intent.markup.markup.Block;
import org.eclipse.mylyn.docs.intent.markup.markup.StructureElement;
import org.eclipse.mylyn.docs.intent.parser.descriptionunit.DescriptionUnitParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.serializer.IntentPositionManager;

/**
 * Factorise the behavior of SChapter and SSection states.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentSubSectionContainerState extends IntentDefaultState {

	/**
	 * Mapping between an identifier and the associated SubSectionContainer.
	 */
	private static Map<String, IntentSubSectionContainer> identifiersToSection;

	/**
	 * IntentSubSectionContainerState constructor.
	 * 
	 * @param offset
	 *            the current element offset
	 * @param declarationLength
	 *            the current element declaration length
	 * @param previous
	 *            the previous state of the parser
	 * @param currentElement
	 *            the intentSubSectionContainer currently being parsed
	 * @param positionManager
	 *            the positionManager where to register positions
	 * @param title
	 *            the element title
	 * @throws ParseException
	 *             it the title cannot be parsed
	 */
	public IntentSubSectionContainerState(int offset, int declarationLength, IntentGenericState previous,
			EObject currentElement, IntentPositionManager positionManager, String title)
			throws ParseException {
		super(offset, declarationLength, previous, currentElement, positionManager);
		setTitle(title);
	}

	/**
	 * Sets the title on the current element.
	 * 
	 * @param stringTitle
	 *            the title
	 * @throws ParseException
	 *             if the title cannot be parsed
	 */
	private void setTitle(String stringTitle) throws ParseException {
		if (stringTitle != null) {
			DescriptionUnit descriptionUnit = new DescriptionUnitParser().parse(stringTitle.trim());
			for (UnitInstruction title : descriptionUnit.getInstructions()) {
				if (title instanceof DescriptionBloc) {
					EList<StructureElement> contents = ((DescriptionBloc)title).getDescriptionBloc()
							.getContent();
					if (contents.size() != 1) {
						throw new ParseException("The title of this section isn't well formed", fOffset,
								stringTitle.trim().length());
					}

					Block titleBlock = (Block)contents.get(0);

					((IntentSubSectionContainer)this.currentElement).setTitle(titleBlock);
					((IntentSubSectionContainer)this.currentElement)
							.setFormattedTitle(createFormattedTitle(stringTitle));
				}
			}
		}
	}

	/**
	 * Returns the formatted title for the given sectionTitle.
	 * 
	 * @param title
	 *            the title to format
	 * @return the formatted title for the given sectionTitle
	 * @throws ParseException
	 *             if the identifier has already been associated to annoter element
	 */
	private String createFormattedTitle(String title) throws ParseException {
		String formattedTitle = "";

		for (int i = 0; i < title.length(); i++) {
			if (Character.isJavaIdentifierPart(title.charAt(i))) {
				formattedTitle += title.charAt(i);
			}
		}

		if (identifiersToSection == null) {
			identifiersToSection = new HashMap<String, IntentSubSectionContainer>();
		}
		if (identifiersToSection.get(formattedTitle) != null) {
			// throw new ParseException("This title is already taken.");
		}
		identifiersToSection.put(formattedTitle, (IntentSubSectionContainer)this.currentElement);
		return formattedTitle;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.parser.internal.state.IntentGenericState#beginSection(int, int,
	 *      java.lang.String)
	 */
	@Override
	public IntentGenericState beginSection(int offset, int declarationLength, String title)
			throws ParseException {
		IntentSection subSection = IntentDocumentFactory.eINSTANCE.createIntentSection();
		((IntentSubSectionContainer)currentElement).getIntentContent().add(subSection);
		return new SSection(offset, declarationLength, this, subSection, positionManager, title);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 *             if the description unit parser detect any parse error
	 * @see org.eclipse.mylyn.docs.intent.parser.internal.state.IntentGenericState#descriptionUnitContent(int,
	 *      int, java.lang.String)
	 */
	@Override
	public IntentGenericState descriptionUnitContent(int offset, int length, String descriptionUnitContent)
			throws ParseException {
		int titleLength = 0;
		String descriptionUnitDescription = descriptionUnitContent;

		// If the descriptionUnitContent isn't empty
		if (descriptionUnitDescription.trim().length() > 0) {
			DescriptionUnit descriptionUnit = new DescriptionUnitParser().parse(descriptionUnitDescription);
			((IntentSubSectionContainer)this.currentElement).getIntentContent().add(descriptionUnit);
			positionManager.setPositionForInstruction(descriptionUnit, offset + titleLength, length
					- titleLength);
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.parser.internal.state.IntentGenericState#endStructuredElement(int)
	 */
	@Override
	public IntentGenericState endStructuredElement(int offset) {
		positionManager.setPositionForInstruction(getCurrentElement(), getOffset(), offset - getOffset());
		return previousState();
	}

}
