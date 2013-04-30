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
package org.eclipse.mylyn.docs.intent.core.query;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;

/**
 * Class that provides useful services about Structured elements (for example : getting all the sections,
 * chapters or section titles contained in an element).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentStructuredElementGetter {

	/**
	 * IntentStructuredElementGetter constructor.
	 */
	private IntentStructuredElementGetter() {

	}

	/**
	 * Returns all the structured elements contained in the given element.
	 * 
	 * @param element
	 *            the element to inspect
	 * @return a list containing all the structured elements contained in the given element
	 */
	public static EList<IntentStructuredElement> getAllStructuredElement(EObject element) {
		EList<IntentStructuredElement> containedStructuredElements = new BasicEList<IntentStructuredElement>();
		if (element instanceof IntentSection) {
			containedStructuredElements
					.addAll(getAllStructuredElementContainedInSection((IntentSection)element));
		}
		return containedStructuredElements;
	}

	/**
	 * Returns all the structured elements contained in the given Section.
	 * 
	 * @param section
	 *            the section to inspect
	 * @return a list containing all the structured elements contained in the given section
	 */
	private static Collection<? extends IntentStructuredElement> getAllStructuredElementContainedInSection(
			IntentSection section) {
		EList<IntentStructuredElement> containedIntentStructuredElements = new BasicEList<IntentStructuredElement>();
		containedIntentStructuredElements.add(section);
		for (IntentSection subSection : section.getSubSections()) {
			containedIntentStructuredElements.addAll(getAllStructuredElementContainedInSection(subSection));
		}
		return containedIntentStructuredElements;
	}

}
