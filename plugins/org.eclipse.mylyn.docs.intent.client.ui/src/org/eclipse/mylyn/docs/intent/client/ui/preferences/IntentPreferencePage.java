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
package org.eclipse.mylyn.docs.intent.client.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The Intent preference page, allowing user to change some the Intent preferences.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {
		setPreferenceStore(IntentEditorActivator.getDefault().getPreferenceStore());

		Composite parent = getFieldEditorParent();
		if (parent.getLayout() == null) {
			GridLayout gridLayout = new GridLayout(1, false);
			parent.setLayout(gridLayout);
		}
		addUIFieds(parent);
		addLogFields(parent);

	}

	private void addUIFieds(Composite parent) {
		// All preferences relative to UI
		Composite uiGroup = createGroup(parent, "Intent editor");
		addField(new BooleanFieldEditor(IntentPreferenceConstants.MATCHING_BRACKETS,
				"Activate bracket matching", new Composite(uiGroup, SWT.NONE)));
		addField(new ColorFieldEditor(IntentPreferenceConstants.MATCHING_BRACKETS_COLOR,
				"Matching brackets color", new Composite(uiGroup, SWT.NONE)));
	}

	private void addLogFields(Composite parent) {
		// All preferences relative to logging
		Composite logGroup = createGroup(parent, "Logging");
		addField(new BooleanFieldEditor(IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING,
				"Activate advanced logging", logGroup));

	}

	/**
	 * Creates a group holding related preferences.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param groupTitle
	 *            the group title
	 * @return a group
	 */
	private Group createGroup(Composite parent, String groupTitle) {
		Group group = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(1, false);
		group.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 1;
		group.setLayoutData(gridData);
		group.setText(groupTitle);

		return group;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {

	}

}
