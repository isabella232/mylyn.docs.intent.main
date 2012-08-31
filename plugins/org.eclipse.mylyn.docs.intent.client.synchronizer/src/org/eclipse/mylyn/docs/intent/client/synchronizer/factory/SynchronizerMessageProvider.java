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
package org.eclipse.mylyn.docs.intent.client.synchronizer.factory;

import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.ComparePackage;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.ReferenceChange;
import org.eclipse.emf.compare.ResourceAttachmentChange;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;

/**
 * Provide messages created from a given Diff.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class SynchronizerMessageProvider {

	// TODO [COMPARE2] [SYNC] accurate sync status messages
	/**
	 * Represents a whitespace in a status message.
	 */
	private static final String SYNC_MESSAGES_WHITESPACE = " ";

	private static final String SYNC_MESSAGES_INTERNAL_MODEL = "<b>Current Document</b>";

	private static final String SYNC_MESSAGES_EXTERNAL_MODEL = "<b>Working Copy</b>";

	/**
	 * Represents the separation space between to status.
	 */
	private static final String SYNC_STATUS_SEPARATOR = "<br/><hr/><br/>";

	/**
	 * SynchronizerMessageProvider constructor.
	 */
	private SynchronizerMessageProvider() {
	}

	/**
	 * Create a message from the given {@link Diff}.
	 * 
	 * @param diff
	 *            the {@link Diff} used to create the returned message
	 * @return a message created from the given {@link Diff} element
	 */
	public static String createMessageFromDiff(Diff diff) {
		String returnedMessage = null;
		try {
			switch (diff.eClass().getClassifierID()) {
				case ComparePackage.ATTRIBUTE_CHANGE:
					returnedMessage = createMessageFromAttributeChange((AttributeChange)diff);
					break;

				case ComparePackage.REFERENCE_CHANGE:
					returnedMessage = createMessageFromReferenceChange((ReferenceChange)diff);
					break;

				case ComparePackage.RESOURCE_ATTACHMENT_CHANGE:
					returnedMessage = createMessageFromResourceAttachmentChange((ResourceAttachmentChange)diff);
					break;

				default:
					break;

			}
		} catch (IllegalArgumentException e) {
			returnedMessage = null;
		}
		if (returnedMessage == null) {
			returnedMessage = SynchonizerEObjectNameGetter.computeObjectName(diff);
		}
		return returnedMessage;
	}

	/**
	 * Create a message from the given AttributeChange element.
	 * 
	 * @param difference
	 *            the Diff used to create the returned message
	 * @return a message created from the given AttributeChange element
	 */
	public static String createMessageFromAttributeChange(AttributeChange difference) {
		return difference.toString();
	}

	/**
	 * Create a message from the given ReferenceChange element.
	 * 
	 * @param difference
	 *            the Diff used to create the returned message
	 * @return a message created from the given ReferenceChange element
	 */
	public static String createMessageFromReferenceChange(ReferenceChange difference) {
		return difference.toString();
	}

	/**
	 * Create a message from the given {@link ResourceAttachmentChange} element.
	 * 
	 * @param difference
	 *            the Diff used to create the returned message
	 * @return a message created from the given {@link ResourceAttachmentChange} element
	 */
	public static String createMessageFromResourceAttachmentChange(ResourceAttachmentChange difference) {
		return difference.toString();
	}

	/**
	 * Returns a String representing the separation space between to status.
	 * 
	 * @return a String representing the separation space between to status
	 */
	public static String getStatusSeparator() {
		return SYNC_STATUS_SEPARATOR;
	}

	/**
	 * Creates an error message indicating that the given resourceDeclaration hasn't been found externally.
	 * 
	 * @param resourceDeclaration
	 *            the resourceDeclaration that hasn't been found externally
	 * @return an error message indicating that the given resourceDeclaration hasn't been found externally
	 */
	public static String createMessageForNullExternalResource(ResourceDeclaration resourceDeclaration) {
		String returnedMessage = "";
		if (resourceDeclaration.getUri() != null) {
			returnedMessage += "Cannot locate Resource at URI : " + resourceDeclaration.getUri().toString();
		}
		returnedMessage += '.';
		return returnedMessage;
	}

	/**
	 * Creates an error message indicating that the given resourceDeclaration has been found externally but is
	 * empty.
	 * 
	 * @param resourceDeclaration
	 *            the resourceDeclaration that has been found externally but is empty
	 * @return an error message
	 */
	public static String createMessageForEmptyExternalResource(ResourceDeclaration resourceDeclaration) {
		String returnedMessage = "";
		if (resourceDeclaration.getUri() != null) {
			returnedMessage += "The Resource at URI : " + resourceDeclaration.getUri().toString()
					+ " is empty";
		}
		returnedMessage += '.';
		return returnedMessage;
	}

	/**
	 * Creates an error message indicating that the given resourceDeclaration is declared in the document,
	 * found externally, but is empty.
	 * 
	 * @param resourceDeclaration
	 *            the resourceDeclaration that has been found externally but is empty
	 * @return an error message
	 */
	public static String createMessageForEmptyInternalResource(ResourceDeclaration resourceDeclaration) {
		String returnedMessage = "";
		if (resourceDeclaration.getUri() != null) {
			returnedMessage += "The Resource " + resourceDeclaration.getName() + " is empty";
		}
		returnedMessage += '.';
		return returnedMessage;
	}
}
