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
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.serializer.IntentPositionManager;

/**
 * Represents the behavior of the parser when the current element is a IntentDocument.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class SDocument extends IntentDefaultState {

	/**
	 * SDocument constructor.
	 * 
	 * @param offset
	 *            the begin offset of the document
	 * @param declarationLength
	 *            the declaration length of the document
	 * @param previous
	 *            the previous state of the parser
	 * @param document
	 *            the document currently being parsed
	 * @param positionManager
	 *            the positionManager where to register positions
	 */
	public SDocument(int offset, int declarationLength, IntentGenericState previous, IntentDocument document,
			IntentPositionManager positionManager) {
		super(offset, declarationLength, previous, document, positionManager);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.parser.internal.state.IntentDefaultState#beginChapter(int, int,
	 *      java.lang.String)
	 */
	@Override
	public IntentGenericState beginChapter(int offset, int declarationLength, String title)
			throws ParseException {
		IntentSection chapter = IntentDocumentFactory.eINSTANCE.createIntentSection();
		((IntentDocument)currentElement).getIntentContent().add(chapter);
		EList<IntentSection> chapters = ((IntentDocument)currentElement).getSubSections();
		return new SChapter(offset, declarationLength, this, chapter, positionManager, title,
				String.valueOf(chapters.size()));
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
