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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.document.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionBloc;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnit;
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
	 * @param completeLevel
	 *            the container complete level
	 * @throws ParseException
	 *             it the title cannot be parsed
	 */
	public IntentSubSectionContainerState(int offset, int declarationLength, IntentGenericState previous,
			EObject currentElement, IntentPositionManager positionManager, String title, String completeLevel)
			throws ParseException {
		super(offset, declarationLength, previous, currentElement, positionManager);
		setTitle(title);
		((IntentStructuredElement)this.currentElement).setCompleteLevel(completeLevel);
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

					((IntentSection)this.currentElement).setTitle(titleBlock);
				}
			}
		}
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
		((IntentSection)currentElement).getIntentContent().add(subSection);
		return new SSection(offset, declarationLength, this, subSection, positionManager, title,
				((IntentStructuredElement)currentElement).getCompleteLevel() + "." + getIndex(subSection));
	}

	/**
	 * Returns the index for this element, using its hierarchical level (for example "2.1.4").
	 * 
	 * @param element
	 *            a structured element
	 * @return the index of this element
	 */
	private String getIndex(IntentStructuredElement element) {
		int positionInContainer = 0;
		// If the element is contained in a document
		if (element.eContainer() instanceof IntentDocument) {
			// We get its position in this document
			positionInContainer = element.eContainer().eContents().indexOf(element) + 1;
		} else {
			// If the element is contained in a Section
			if (element.eContainer() instanceof IntentSection) {
				// we get its position in this container
				positionInContainer = ((IntentSection)element.eContainer()).getSubSections().indexOf(element) + 1;
			}
		}
		return Integer.toString(positionInContainer);
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
			((IntentSection)this.currentElement).getIntentContent().add(descriptionUnit);
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
		positionManager.setPositionForInstruction(getCurrentElement(), getOffset(), offset - getOffset(),
				getDeclarationLength());
		return previousState();
	}

}
