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
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorImpl;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.IntentEditorConfiguration;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.IntentPartitionScanner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * The Intent preference page, allowing user to change the Intent behavior and rendering.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String LINK_DROPPED_ELEMENTS_USING_EXTERNAL_REFERENCES = "Link dropped elements using External References";

	private static final String INTENT_PREVIEW_EXAMPLE = "Section Title {\n\t Default text \n\t\"Strings\"\n\t* lists\n\t!images!\n\t\n\t@M\n\t\tnew Element{}\n\tM@\n}";

	private RefreshPreviewEditorListener refreshPreviewEditorListener;

	private SourceViewer sourceViewer;

	private IntentEditorConfiguration viewerConfiguration;

	private IDocumentPartitioner partitioner;

	private IntentEditorImpl editor;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {
		Composite parent = getFieldEditorParent();
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		gridLayout.verticalSpacing = 0;
		parent.setLayout(gridLayout);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		parent.redraw();
		TabFolder folder = new TabFolder(parent, SWT.NONE);
		folder.setLayout(new TabFolderLayout());
		folder.setLayoutData(new GridData(GridData.FILL_BOTH));

		setPreferenceStore(IntentEditorActivator.getDefault().getPreferenceStore());

		TabItem item = new TabItem(folder, SWT.NONE);
		item.setText("Appearance");
		item.setControl(createAppearancePage(folder));

		item = new TabItem(folder, SWT.NONE);
		item.setText("Colors");
		item.setControl(createColorsPage(folder));

		item = new TabItem(folder, SWT.NONE);
		item.setText("Behavior (UI)");
		item.setControl(createUIBehaviorPage(folder));

		item = new TabItem(folder, SWT.NONE);
		item.setText("Other");
		item.setControl(createOtherPage(folder));
	}

	private Control createAppearancePage(Composite parent) {
		Composite composite = createComposite(parent);
		addField(createBooleanFieldEditor(IntentPreferenceConstants.TEXT_WRAP, "Autowrap",
				"Intent editor should automatically wrap lines", composite));
		addField(createBooleanFieldEditor(IntentPreferenceConstants.SHOW_PREVIEW_PAGE,
				"Show HTML Preview page",
				"Intent editor should display a 'Preview' tab to get HTML preview of current editor",
				composite));
		addField(createBooleanFieldEditor(IntentPreferenceConstants.COLLAPSE_MODELING_UNITS,
				"Collapse ModelingUnits", "Intent editor should collapse Modeling Units at opening",
				composite));
		addField(createBooleanFieldEditor(IntentPreferenceConstants.MATCHING_BRACKETS, "Brackets matching",
				"Intent editor should match brackets", composite));
		return composite;
	}

	private Control createColorsPage(Composite parent) {
		Composite colorComposite = createComposite(parent);

		// Add links explaining that Intent font preferences can be customized through the General >
		// Appearance > Colors and Fonts preference page
		final Link link = new Link(colorComposite, SWT.NONE);
		link.setText("Note: Intent Font preferences can be configured through the <a href=\"org.eclipse.ui.preferencePages.ColorsAndFonts\">'Colors and Fonts'</a> preference page.");
		link.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if ("org.eclipse.ui.preferencePages.ColorsAndFonts".equals(e.text)) {
					PreferencesUtil.createPreferenceDialogOn(link.getShell(), e.text, null,
							"selectFont:org.eclipse.jface.textfont");
				}
			}
		});
		final Link link2 = new Link(colorComposite, SWT.NONE);
		link2.setText("See <a href=\"org.eclipse.ui.preferencePages.GeneralTextEditor\">'Text Editors'</a> for general text editor preferences.");
		link2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if ("org.eclipse.ui.preferencePages.GeneralTextEditor".equals(e.text)) {
					PreferencesUtil.createPreferenceDialogOn(link.getShell(), e.text, null, null);
				}
			}
		});

		// Create all color fields editors
		ColorFieldEditor bracketsColor = new ColorFieldEditor(
				IntentPreferenceConstants.MATCHING_BRACKETS_COLOR, "Brackets color", colorComposite);
		addField(bracketsColor);
		ColorFieldEditor stringColor = new ColorFieldEditor(IntentPreferenceConstants.STRING_COLOR,
				"String color", colorComposite);
		addField(stringColor);
		ColorFieldEditor textColor = new ColorFieldEditor(IntentPreferenceConstants.DU_DEFAULT_FOREGROUND,
				"Text color", colorComposite);
		addField(textColor);
		ColorFieldEditor codeColor = new ColorFieldEditor(IntentPreferenceConstants.CODE_FOREGROUND,
				"Code color", colorComposite);
		addField(codeColor);
		ColorFieldEditor duKeyWordColor = new ColorFieldEditor(
				IntentPreferenceConstants.DU_KEYWORD_FOREGROUND, "Keyword color", colorComposite);
		addField(duKeyWordColor);
		ColorFieldEditor titleColor = new ColorFieldEditor(IntentPreferenceConstants.DU_TITLE_FOREGROUND,
				"Title color", colorComposite);
		addField(titleColor);
		ColorFieldEditor listColor = new ColorFieldEditor(IntentPreferenceConstants.DU_LIST_FOREGROUND,
				"List color", colorComposite);
		addField(listColor);
		ColorFieldEditor muDefaultColor = new ColorFieldEditor(IntentPreferenceConstants.MU_DEFAULT_COLOR,
				"Modeling unit - default color", colorComposite);
		addField(muDefaultColor);
		ColorFieldEditor muKeywordColor = new ColorFieldEditor(IntentPreferenceConstants.MU_KEYWORD_COLOR,
				"Modeling unit - keyword color", colorComposite);
		addField(muKeywordColor);

		// Create preview viewer
		Label infoLabel2 = new Label(colorComposite, SWT.NONE);
		infoLabel2.setText("Preview: ");
		IntentEditorDocument document = createIntentPreviewViewer(colorComposite);

		// Add change listener so that preview is refreshed when changing a color
		this.refreshPreviewEditorListener = new RefreshPreviewEditorListener(document);
		getPreferenceStore().addPropertyChangeListener(refreshPreviewEditorListener);
		return colorComposite;
	}

	private Control createUIBehaviorPage(Composite parent) {
		Composite composite = createComposite(parent);

		Composite dragAndDropGroup = createGroup(composite, "Drag and Drop Support");
		Label fontInfo = new Label(dragAndDropGroup, SWT.NONE);
		fontInfo.setText("These preferences allow to specify how should Intent react when dropping elements (e.g. a Java class) inside an Intent editor");
		addField(createBooleanFieldEditor(
				IntentPreferenceConstants.DND_DISPLAY_POP_UP,
				"Always ask",
				"A pop-up should ask the behavior to apply. Otherwise the behavior checked below will be applied automatically.",
				dragAndDropGroup));
		addField(createBooleanFieldEditor(IntentPreferenceConstants.DND_USE_EXTERNAL_REFERENCES,
				LINK_DROPPED_ELEMENTS_USING_EXTERNAL_REFERENCES,
				"There are 2 ways of linking dropped elements (see documentation for further details)",
				dragAndDropGroup));
		return composite;
	}

	private Control createOtherPage(Composite parent) {
		Composite composite = createComposite(parent);

		addField(createBooleanFieldEditor(IntentPreferenceConstants.ACTIVATE_BACKUP,
				"Activate back-up mechanism",
				"Intent Documents should be backed-up in text files (only in Workspace mode)", composite));

		addField(createBooleanFieldEditor(IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING,
				"Advanced logging", "(for Debug only) Each Intent client should log its activity", composite));
		return composite;
	}

	/**
	 * Creates a composite to hold a tab of the preference page.
	 * 
	 * @param parent
	 *            the parent
	 * @return a composite to hold a tab of the preference page
	 */
	private Composite createComposite(Composite parent) {
		Font font = parent.getFont();
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(font);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		return composite;
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
	 * Creates a source viewer allowing to preview an Intent document (to see impact of color changes).
	 * 
	 * @param parent
	 *            the parent of the source viewer to create
	 * @return a source viewer allowing to preview an Intent document (to see impact of color changes)
	 */
	private IntentEditorDocument createIntentPreviewViewer(Composite parent) {
		editor = new IntentEditorImpl();
		IntentEditorDocument document = new IntentEditorDocument(editor);
		partitioner = new FastPartitioner(new IntentPartitionScanner(),
				IntentPartitionScanner.LEGAL_CONTENT_TYPES);
		partitioner.connect(document);
		document.setDocumentPartitioner(partitioner);

		viewerConfiguration = new IntentEditorConfiguration(editor, null);
		int styles = SWT.V_SCROLL;
		styles |= SWT.H_SCROLL;
		styles |= SWT.MULTI;
		styles |= SWT.BORDER;
		styles |= SWT.FULL_SELECTION;
		new Label(parent, SWT.NONE);
		sourceViewer = new SourceViewer(parent, null, null, false, styles);
		StyledText styledText = sourceViewer.getTextWidget();
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		sourceViewer.configure(viewerConfiguration);
		sourceViewer.setEditable(false);
		Cursor arrowCursor = sourceViewer.getTextWidget().getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
		sourceViewer.getTextWidget().setCursor(arrowCursor);
		sourceViewer.getTextWidget().setCaret(null);
		sourceViewer.getTextWidget().setFont(JFaceResources.getTextFont());
		sourceViewer.setDocument(document);
		document.set(INTENT_PREVIEW_EXAMPLE);
		return document;
	}

	/**
	 * Creates a boolean field editor allowing to change the preference with the given id.
	 * 
	 * @param preferenceID
	 *            the preference ID
	 * @param text
	 *            the text to display
	 * @param a
	 *            detailled explanation about the preference
	 * @param composite
	 *            the parent composite
	 * @return a boolean field editor allowing to change the preference with the given id
	 */
	private FieldEditor createBooleanFieldEditor(String preferenceID, String text, String explanations,
			Composite composite) {
		BooleanFieldEditor booleanFieldEditor = new BooleanFieldEditor(preferenceID, text, new Composite(
				composite, SWT.NONE)) {
			private Label labelControl;

			@Override
			public Label getLabelControl(Composite parent) {
				if (labelControl == null) {
					labelControl = super.getLabelControl(parent);
					FontData fontData = labelControl.getFont().getFontData()[0];
					Font font = new Font(Display.getCurrent(), new FontData(fontData.getName(),
							fontData.getHeight(), SWT.BOLD));
					labelControl.setFont(font);
				}
				return labelControl;
			}
		};
		createLabel(composite, explanations);
		return booleanFieldEditor;
	}

	/**
	 * Creates a label on the given composite with the given text.
	 * 
	 * @param composite
	 *            the composite
	 * @param text
	 *            the label text
	 * @return a label on the given composite with the given text
	 */
	private Label createLabel(Composite composite, String text) {
		Label label = new Label(composite, SWT.RIGHT);
		label.setText("- " + text);
		FontData fontData = label.getFont().getFontData()[0];
		Font font = new Font(Display.getCurrent(), new FontData(fontData.getName(), fontData.getHeight(),
				SWT.ITALIC));
		label.setFont(font);
		return label;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {

	}

	public class TabFolderLayout extends Layout {

		protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT)
				return new Point(wHint, hHint);

			Control[] children = composite.getChildren();
			int count = children.length;
			int maxWidth = 0, maxHeight = 0;
			for (int i = 0; i < count; i++) {
				Control child = children[i];
				Point pt = child.computeSize(SWT.DEFAULT, SWT.DEFAULT, flushCache);
				maxWidth = Math.max(maxWidth, pt.x);
				maxHeight = Math.max(maxHeight, pt.y);
			}

			if (wHint != SWT.DEFAULT)
				maxWidth = wHint;
			if (hHint != SWT.DEFAULT)
				maxHeight = hHint;

			return new Point(maxWidth, maxHeight);

		}

		protected void layout(Composite composite, boolean flushCache) {
			Rectangle rect = composite.getClientArea();

			Control[] children = composite.getChildren();
			for (int i = 0; i < children.length; i++) {
				children[i].setBounds(rect);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#dispose()
	 */
	@Override
	public void dispose() {
		if (refreshPreviewEditorListener != null) {
			getPreferenceStore().removePropertyChangeListener(refreshPreviewEditorListener);
		}
	}

	/**
	 * An {@link IPropertyChangeListener} that refreshes the preview viewer.
	 * 
	 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
	 */
	private class RefreshPreviewEditorListener implements IPropertyChangeListener {

		private IntentEditorDocument document;

		/**
		 * Constructor.
		 * 
		 * @param viewer
		 *            the preview Viewer to refresh
		 */
		public RefreshPreviewEditorListener(IntentEditorDocument document) {
			this.document = document;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent event) {
			// TODO REFRESH PREVIEW VIEW
		}

	}
}
