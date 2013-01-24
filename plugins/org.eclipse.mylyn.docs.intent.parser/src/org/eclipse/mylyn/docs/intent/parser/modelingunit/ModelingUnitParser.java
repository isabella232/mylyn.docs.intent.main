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
package org.eclipse.mylyn.docs.intent.parser.modelingunit;

import org.eclipse.emf.ecore.EObject;

/**
 * Parser of a Modeling Unit.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public interface ModelingUnitParser {

	/**
	 * Prefix that indicates the beginning of a Modeling Unit.
	 */
	String MODELING_UNIT_PREFIX = "@M";

	/**
	 * Suffix that indicates the end of a Modeling Unit.
	 */
	String MODELING_UNIT_SUFFIX = "M@";

	/**
	 * Keyword for declaring external content references.
	 */
	String EXTERNAL_CONTENT_REFERENCE = "@ref";

	/**
	 * Parse the given String and return its AST.
	 * 
	 * @param rootOffset
	 *            the offset of the string to parse
	 * @param stringToParse
	 *            String representing the Modeling Unit to parse
	 * @return the AST corresponding to the given String
	 * @throws ParseException
	 *             if the given String cannot be parsed
	 */
	EObject parseString(int rootOffset, String stringToParse) throws ParseException;

	/**
	 * Parse the given String and return its AST.
	 * 
	 * @param stringToParse
	 *            String representing the Modeling Unit to parse
	 * @return the AST corresponding to the given String
	 * @throws ParseException
	 *             if the given String cannot be parsed
	 */
	EObject parseString(String stringToParse) throws ParseException;
}
