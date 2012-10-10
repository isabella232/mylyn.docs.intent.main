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

import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentSectionVisibility;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitParserImpl;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.serializer.IntentPositionManager;

/**
 * Represents the behavior of the parser when the current element is a Section.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class SSection extends IntentSubSectionContainerState {

	/**
	 * The parser to use for parsing Modeling Units.
	 */
	private static ModelingUnitParser modelingUnitParser;

	/**
	 * SSection constructor.
	 * 
	 * @param offset
	 *            the begin offset of the section
	 * @param declarationLength
	 *            the declaration length of the section
	 * @param previous
	 *            the previous state of the parser
	 * @param section
	 *            the section currently being parsed
	 * @param positionManager
	 *            the positionManager where to register positions
	 * @param title
	 *            the section title
	 * @param containerLevel
	 *            the resolved container level
	 * @throws ParseException
	 *             if the title cannot be parsed
	 */
	public SSection(int offset, int declarationLength, IntentGenericState previous, IntentSection section,
			IntentPositionManager positionManager, String title, String containerLevel) throws ParseException {
		super(offset, declarationLength, previous, section, positionManager, title, containerLevel);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.parser.internal.state.IntentGenericState#sectionOptions(java.lang.String)
	 */
	@Override
	public IntentGenericState sectionOptions(String visibility) {
		// Visibility creation
		if (visibility != null) {
			if ("hidden".equals(visibility)) {
				((IntentSection)currentElement).setVisibility(IntentSectionVisibility.HIDDEN);
			} else {
				((IntentSection)currentElement).setVisibility(IntentSectionVisibility.INTERNAL);
			}
		} else {
			((IntentSection)currentElement).setVisibility(IntentSectionVisibility.PUBLIC);
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.parser.internal.state.IntentGenericState#modelingUnitContent(int,
	 *      int, java.lang.String)
	 */
	@Override
	public IntentGenericState modelingUnitContent(int offset, int length, String modelingUnitContent)
			throws ParseException {

		ModelingUnit modelingUnit = (ModelingUnit)getModelingUnitParser().parseString(offset,
				modelingUnitContent);

		((IntentSection)this.currentElement).getIntentContent().add(modelingUnit);

		positionManager.setPositionForInstruction(modelingUnit, offset, length);
		return this;
	}

	/**
	 * Returns the parser to use for parsing Modeling Units. If the parser hasn't been created, creates it.
	 * 
	 * @return the parser to use for parsing Modeling Units
	 */
	private ModelingUnitParser getModelingUnitParser() {
		if (modelingUnitParser == null) {
			modelingUnitParser = new ModelingUnitParserImpl();
		}
		return modelingUnitParser;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SSection - " + ((IntentSection)this.currentElement).getVisibility();
	}
}
