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
package org.eclipse.mylyn.docs.intent.client.ui.editor.drop;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * A {@link MessageDialogWithToggle} allowing the end-user to choose how the dropped elements should be linked
 * inside the document.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DropModeDialog extends MessageDialogWithToggle {

	private static final String DIALOG_MESSAGE = "You have dropped new artifacts to associate to the Intent document. Should we use External References to link these artifacts, or do you want to make a Full Copy?";
	private static final String DIALOG_TITLE = "Linking documentation with new artifacts";

	/**
	 * Default constructor.
	 * 
	 * @param parentShell
	 *            the {@link Shell} on which this pop-up will be displayed
	 */
	public DropModeDialog(Shell parentShell) {
		super(
				parentShell,
				DIALOG_TITLE,
				null,
				DIALOG_MESSAGE,
				MessageDialog.QUESTION, getButtons(), 0, "Always apply this choice", false);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.dialogs.MessageDialogWithToggle#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		final Button[] buttons = new Button[getButtons().length];
		for (int i = 0; i < getButtons().length; i++) {
			String label = getButtons()[i];

			Button button = createButton(parent, i, label, 0 == i);
			buttons[i] = button;

		}
		setButtons(buttons);
	}

	private static final String[] getButtons() {
		return new String[] {"External references", "Full Copy", "Cancel"
		};
	}

	/**
	 * Indicates whether end-user has cancelled this dialog.
	 * 
	 * @return true if the end-user has cancelled this dialog, false otherwise
	 */
	public boolean isCancelled() {
		return getReturnCode() == 2;
	}

	/**
	 * Indicates whether end-user has chosen to use an external content reference.
	 * 
	 * @return true if the end-user has chosen to use an external content reference, false otherwise
	 */
	public boolean shouldUseExternalReferences() {
		return getReturnCode() == 0;
	}

}
