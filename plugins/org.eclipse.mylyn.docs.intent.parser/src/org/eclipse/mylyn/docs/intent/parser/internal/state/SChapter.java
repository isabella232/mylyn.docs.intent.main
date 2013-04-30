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
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.serializer.IntentPositionManager;

/**
 * Represents the behavior of the parser when the current element is a Chapter.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class SChapter extends SSection {

	/**
	 * SChapter constructor.
	 * 
	 * @param offset
	 *            the offset of the current chapter
	 * @param declarationLength
	 *            the declaration length of the current chapter
	 * @param previous
	 *            the previous state of the parser
	 * @param chapter
	 *            the chapter currently being parsed
	 * @param positionManager
	 *            the positionManager where to register positions
	 * @param title
	 *            the section title
	 * @param containerLevel
	 *            the resolved container level
	 * @throws ParseException
	 *             if the title cannot be parsed
	 */
	public SChapter(int offset, int declarationLength, IntentGenericState previous, IntentSection chapter,
			IntentPositionManager positionManager, String title, String containerLevel) throws ParseException {
		super(offset, declarationLength, previous, chapter, positionManager, title, containerLevel);
	}

}
