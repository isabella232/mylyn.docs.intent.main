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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.emf.ecore.EObject;

/**
 * Handle the position of all elements of an Intent element.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentPositionManager {

	/**
	 * Maps a unitInstruction with a position.
	 */
	private Map<EObject, ParsedElementPosition> instructionToPosition;

	/**
	 * Maps an offset with an instruction.
	 */
	private SortedMap<Integer, EObject> positionToInstruction;

	/**
	 * IntentPositionManager constructor.
	 */
	public IntentPositionManager() {
		instructionToPosition = new HashMap<EObject, ParsedElementPosition>();
		positionToInstruction = new TreeMap<Integer, EObject>();
	}

	/**
	 * Clear all the information contained in the position manager.
	 */
	public void clear() {
		instructionToPosition.clear();
		positionToInstruction.clear();
	}

	/**
	 * Returns the position of the given instruction element.
	 * 
	 * @param instruction
	 *            the element for witch we want the position
	 * @return the position of the given instruction element (null if no position).
	 */
	public ParsedElementPosition getPositionForElement(EObject instruction) {
		return instructionToPosition.get(instruction);
	}

	/**
	 * Returns the element corresponding to the given position.
	 * 
	 * @param offset
	 *            the current offset
	 * @return the element corresponding to the given position
	 */
	public EObject getElementAtPosition(int offset) {
		EObject foundElement = null;
		Iterator<Integer> offsetIterator = positionToInstruction.keySet().iterator();
		Integer currentOffsetValue = 0;

		while ((currentOffsetValue < offset) && offsetIterator.hasNext()) {
			currentOffsetValue = offsetIterator.next();
			foundElement = positionToInstruction.get(currentOffsetValue);
		}
		return foundElement;
	}

	/**
	 * Adds all the informations contained in the given PositionManager to this IntentPositionManager.
	 * 
	 * @param positionManager
	 *            the entity containing informations about positions.
	 */
	public void addIntentPositionManagerInformations(IntentPositionManager positionManager) {
		this.instructionToPosition.putAll(positionManager.instructionToPosition);
		this.positionToInstruction.putAll(positionManager.positionToInstruction);
	}

	/**
	 * Associates the given instruction to the given offset and length.
	 * 
	 * @param instruction
	 *            the instruction to associate with the given position
	 * @param offset
	 *            the offset of the given instruction
	 * @param length
	 *            the length of the given instruction
	 */
	public void setPositionForInstruction(EObject instruction, int offset, int length) {
		instructionToPosition.put(instruction, new ParsedElementPosition(offset, length));
		positionToInstruction.put(offset, instruction);
	}

	/**
	 * Associates the given instruction to the given offset and length.
	 * 
	 * @param instruction
	 *            the instruction to associate with the given position
	 * @param offset
	 *            the offset of the given instruction
	 * @param length
	 *            the length of the given instruction
	 * @param declarationLength
	 *            the length of the given instruction declaration
	 */
	public void setPositionForInstruction(EObject instruction, int offset, int length, int declarationLength) {
		instructionToPosition.put(instruction, new ParsedElementPosition(offset, length, declarationLength));
		positionToInstruction.put(offset, instruction);
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
		positionToInstruction.clear();
		for (Entry<EObject, ParsedElementPosition> entry : instructionToPosition.entrySet()) {
			ParsedElementPosition position = entry.getValue();
			int offset = position.getOffset();
			int length = position.getLength();
			if (offset <= tabOffset && (offset + length) >= tabOffset) {
				length += nbTabs;
			}
			if (offset >= tabOffset) {
				offset += nbTabs;
			}
			position.setOffset(offset);
			position.setLength(length);
			positionToInstruction.put(offset, entry.getKey());
		}
	}

}
