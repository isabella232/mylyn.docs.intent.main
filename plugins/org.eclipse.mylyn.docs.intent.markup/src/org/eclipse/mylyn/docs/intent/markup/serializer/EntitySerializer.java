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
package org.eclipse.mylyn.docs.intent.markup.serializer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.mylyn.docs.intent.markup.markup.Entity;

/**
 * Serializer of Entity elements.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class EntitySerializer {

	/**
	 * Constant to identify a specific character.
	 */
	private static final String COPYRIGHT_CODE = "#169";

	/**
	 * Constant corresponding to a specific code translation.
	 */
	private static final String COPYRIGHT_TRANSLATION = "(c)";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String TRADEMARK_CODE = "#8482";

	/**
	 * Constant corresponding to a specific code translation.
	 */
	private static final String TRADEMARK_TRANSLATION = "(tm)";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String REGISTERED_CODE = "#174";

	/**
	 * Constant corresponding to a specific code translation.
	 */
	private static final String REGISTERED_TRANSLATION = "(r)";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String DASH_CODE = "#8211";

	/**
	 * Constant corresponding to a specific code translation.
	 */
	private static final String DASH_TRANSLATION = "-";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String SIMPLEQUOTE_CODE = "#8217";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String SIMPLEQUOTE2_CODE = "#8249";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String SIMPLEQUOTE3_CODE = "#8250";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String SIMPLEQUOTE4_CODE = "#8216";

	/**
	 * Constant corresponding to a specific code translation.
	 */
	private static final String SIMPLEQUOTE_TRANSLATION = "'";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String LEFTDOUBLEQUOTE_CODE = "#171";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String LEFTDOUBLEQUOTE1_CODE = "#8220";

	/**
	 * Constant corresponding to a specific code translation.
	 */
	private static final String LEFTDOUBLEQUOTE_TRANSLATION = "\"";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String RIGHTDOUBLEQUOTE_CODE = "#187";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String RIGHTDOUBLEQUOTE1_CODE = "#8221";

	/**
	 * Constant corresponding to a specific code translation.
	 */
	private static final String RIGHTDOUBLEQUOTE_TRANSLATION = "\"";

	/**
	 * Constant to identify a specific character.
	 */
	private static final String TABULATION_SYMBOL = "<dd/>";

	/**
	 * Constant corresponding to a specific code translation.
	 */
	private static final String TABULATION_TRANSLATION = "<dd/>";

	/**
	 * HasMap mapping an entity code (example : '#169') to its TRANSLATION in Textile ('(c)').
	 */
	private static Map<String, String> entityMapping;

	/**
	 * EntitySerializer constructor.
	 */
	private EntitySerializer() {

	}

	/**
	 * Serialize the given Entity element according to its associated formats.
	 * 
	 * @param entity
	 *            a WikiText Entity element.
	 * @return the serialized form of the given element.
	 */
	public static String render(Entity entity) {
		// We ensure that the map has been initialized.
		initializeEntityMapping();
		if (entityMapping.containsKey(entity.getData())) {
			return entityMapping.get(entity.getData());
		} else {
			// By default, we return the unknowSymbol string;
			return entity.getData();
		}
	}

	/**
	 * Initialize the mapping between entity's codes and their TRANSLATIONs in textile.
	 */
	private static void initializeEntityMapping() {
		if (entityMapping == null) {
			entityMapping = new HashMap<String, String>();
			entityMapping.put(COPYRIGHT_CODE, COPYRIGHT_TRANSLATION);
			entityMapping.put(TRADEMARK_CODE, TRADEMARK_TRANSLATION);
			entityMapping.put(REGISTERED_CODE, REGISTERED_TRANSLATION);
			entityMapping.put(DASH_CODE, DASH_TRANSLATION);
			entityMapping.put(SIMPLEQUOTE_CODE, SIMPLEQUOTE_TRANSLATION);
			entityMapping.put(SIMPLEQUOTE2_CODE, SIMPLEQUOTE_TRANSLATION);
			entityMapping.put(SIMPLEQUOTE3_CODE, SIMPLEQUOTE_TRANSLATION);
			entityMapping.put(SIMPLEQUOTE4_CODE, SIMPLEQUOTE_TRANSLATION);
			entityMapping.put(LEFTDOUBLEQUOTE_CODE, LEFTDOUBLEQUOTE_TRANSLATION);
			entityMapping.put(LEFTDOUBLEQUOTE1_CODE, LEFTDOUBLEQUOTE_TRANSLATION);
			entityMapping.put(RIGHTDOUBLEQUOTE_CODE, RIGHTDOUBLEQUOTE_TRANSLATION);
			entityMapping.put(RIGHTDOUBLEQUOTE1_CODE, RIGHTDOUBLEQUOTE_TRANSLATION);

			entityMapping.put(TABULATION_SYMBOL, TABULATION_TRANSLATION);
		}
	}
}
