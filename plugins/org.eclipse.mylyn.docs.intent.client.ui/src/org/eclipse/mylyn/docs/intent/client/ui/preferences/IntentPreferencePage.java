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
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The Intent preference page, allowing user to change some the Intent preferences.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String LINK_DROPPED_ELEMENTS_USING_EXTERNAL_REFERENCES = "Link dropped elements using External References";

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
		addDnDFieds(parent);
		addOtherFields(parent);
	}

	/**
	 * Configure UI fields in the given parent composite.
	 * 
	 * @param parent
	 *            the parent composite
	 */
	private void addUIFieds(Composite parent) {
		// All preferences relative to UI
		Composite uiGroup = createGroup(parent, "Intent editor");
		addField(new BooleanFieldEditor(IntentPreferenceConstants.COLLAPSE_MODELING_UNITS,
				"Collapse ModelingUnits at editor opening", new Composite(uiGroup, SWT.NONE)));
		addField(new BooleanFieldEditor(IntentPreferenceConstants.MATCHING_BRACKETS, "Brackets matching",
				new Composite(uiGroup, SWT.NONE)));
		addField(new ColorFieldEditor(IntentPreferenceConstants.MATCHING_BRACKETS_COLOR, "Brackets color",
				new Composite(uiGroup, SWT.NONE)));

		// Adding a label explaining that Intent font preferences can be customized through the General >
		// Appearance > Colors and Fonts preference page
		Label fontInfo = new Label(uiGroup, SWT.NONE);
		fontInfo.setText("Note: Intent fonts can be customized using the 'General>Appearance>Colors and Fonts' preference page");

	}

	/**
	 * Configure fields related to Drag and Drop in the given parent composite.
	 * 
	 * @param parent
	 *            the parent composite
	 */
	private void addDnDFieds(Composite parent) {
		// All preferences relative to UI
		Composite uiGroup = createGroup(parent, "Drag and Drop Support'");
		Label fontInfo = new Label(uiGroup, SWT.NONE);
		fontInfo.setText("These preferences allow to specify how should Intent react when dropping elements (e.g. a Java class) inside an Intent editor");
		addField(new BooleanFieldEditor(IntentPreferenceConstants.DND_DISPLAY_POP_UP, "Always ask",
				new Composite(uiGroup, SWT.NONE)));
		addField(new BooleanFieldEditor(IntentPreferenceConstants.DND_USE_EXTERNAL_REFERENCES,
				LINK_DROPPED_ELEMENTS_USING_EXTERNAL_REFERENCES, new Composite(uiGroup, SWT.NONE)));
	}

	/**
	 * Configure intent other fields in the given parent composite.
	 * 
	 * @param parent
	 *            the parent composite
	 */
	private void addOtherFields(Composite parent) {
		// All preferences relative to logging
		Composite otherGroup = createGroup(parent, "Other");
		addField(new BooleanFieldEditor(IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING,
				"Advanced logging", new Composite(otherGroup, SWT.NONE)));
		addField(new BooleanFieldEditor(IntentPreferenceConstants.ACTIVATE_BACKUP,
				"Activate back-up mechanism", new Composite(otherGroup, SWT.NONE)));
		Label fontInfo = new Label(otherGroup, SWT.NONE);
		fontInfo.setText("If back-up is activated, Intent documents will be stored in text files (only in Workspace mode)");

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
