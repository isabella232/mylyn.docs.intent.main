/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.ide.wizards;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorImpl;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.IntentEditorConfiguration;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.IntentPartitionScanner;
import org.eclipse.mylyn.docs.intent.client.ui.ide.Activator;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * The Intent project creation wizard.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentTemplateWizardPage extends WizardPage {
	/**
	 * ID of the extension point allowing to provide templates.
	 */
	private static final String TEMPLATES_EXTENSION_POINT = "org.eclipse.mylyn.docs.intent.client.ui.ide.template.extension"; //$NON-NLS-1$

	/**
	 * Mapping between each contributed template name and its content.
	 */
	private static Map<String, String[]> templateExtensionsByName;

	/**
	 * Label describing the template.
	 */
	private Label descriptionLabel;

	/**
	 * The combo used to select the template to apply.
	 */
	private Combo combo;

	/**
	 * The document used to show a preview of the template.
	 */
	private IDocument document;

	/**
	 * The source viewer used to show a preview of the template.
	 */
	private SourceViewer sourceViewer;

	/**
	 * The configuration of the editor used to show a preview of the template.
	 */
	private IntentEditorConfiguration viewerConfiguration;

	/**
	 * Creates a new wizard page.
	 */
	public IntentTemplateWizardPage() {
		super("IntentTemplateWizardPage");
		setDescription("Initializes the Intent project using one of the installed templates.");
		initializeRegistry();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		// Main composite
		final Composite control = new Composite(parent, SWT.NULL);
		setControl(control);
		control.setLayout(new GridLayout(2, false));

		final Label extSelectionLabel = new Label(control, SWT.NONE);
		extSelectionLabel.setText("Select an installed template: ");

		combo = new Combo(control, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label infoLabel1 = new Label(control, SWT.NONE);
		infoLabel1.setText("Description: ");
		descriptionLabel = new Label(control, SWT.NONE);
		descriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		Label infoLabel2 = new Label(control, SWT.NONE);
		infoLabel2.setText("Preview: ");

		IntentEditor editor = new IntentEditorImpl();
		document = new IntentEditorDocument(editor);
		IDocumentPartitioner partitioner = new FastPartitioner(new IntentPartitionScanner(),
				IntentPartitionScanner.LEGAL_CONTENT_TYPES);
		partitioner.connect(document);
		document.setDocumentPartitioner(partitioner);

		viewerConfiguration = new IntentEditorConfiguration(editor, null);
		int styles = SWT.V_SCROLL;
		styles |= SWT.H_SCROLL;
		styles |= SWT.MULTI;
		styles |= SWT.BORDER;
		styles |= SWT.FULL_SELECTION;
		new Label(control, SWT.NONE);
		sourceViewer = new SourceViewer(control, null, null, false, styles);
		StyledText styledText = sourceViewer.getTextWidget();
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		sourceViewer.configure(viewerConfiguration);
		sourceViewer.setEditable(false);
		Cursor arrowCursor = sourceViewer.getTextWidget().getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
		sourceViewer.getTextWidget().setCursor(arrowCursor);
		sourceViewer.getTextWidget().setCaret(null);
		sourceViewer.getTextWidget().setFont(JFaceResources.getTextFont());
		sourceViewer.setDocument(document);

		combo.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				handleSelectionChanged();
			}

		});

		for (String templateName : templateExtensionsByName.keySet()) {
			combo.add(templateName);
		}

		combo.select(0);
		handleSelectionChanged();
	}

	/**
	 * Updates the template preview when selection changed.
	 */
	private void handleSelectionChanged() {
		String[] template = templateExtensionsByName.get(combo.getText());
		descriptionLabel.setText(template[0]);
		try {
			document.set(getContent(template[1]));
		} catch (IOException error) {
			IntentUiLogger.logError(error);
		}
		setPageComplete(validate());
	}

	/**
	 * Returns the initial content according to the selected template.
	 * 
	 * @return the initial content
	 */
	public String getContent() {
		try {
			return getContent(templateExtensionsByName.get(combo.getText())[1]);
		} catch (IOException e) {
			IntentUiLogger.logError(e);
		}
		return null;
	}

	/**
	 * Initializes the templateExtensionsByName map according to extension registry.
	 */
	private static void initializeRegistry() {
		if (templateExtensionsByName == null) {
			templateExtensionsByName = new HashMap<String, String[]>();
			IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(
					TEMPLATES_EXTENSION_POINT);
			for (IConfigurationElement element : elements) {
				String name = element.getAttribute("name");
				String description = element.getAttribute("description");
				String template = element.getAttribute("template");
				if (getURL(template) != null) {
					templateExtensionsByName.put(name, new String[] {description, template,
					});
				}
			}
		}
	}

	/**
	 * Returns the content of the file at the given path.
	 * 
	 * @param filePath
	 *            the file to read path
	 * @return the content of the file at the given path
	 * @throws IOException
	 *             if file cannot be accessed
	 */
	private static String getContent(String filePath) throws IOException {
		URL url = getURL(filePath);
		String result = "";
		InputStream fis = url.openStream();
		BufferedInputStream bis = null;
		BufferedReader dis = null;
		StringBuffer sb = new StringBuffer();

		bis = new BufferedInputStream(fis);
		dis = new BufferedReader(new InputStreamReader(bis));

		while (dis.ready()) {
			sb.append(dis.readLine() + "\n");
		}

		fis.close();
		bis.close();
		dis.close();

		result = sb.toString();
		return result;
	}

	/**
	 * Returns the {@link URL} corresponding to the given file path.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the {@link URL} corresponding to the given file path
	 */
	private static URL getURL(String filePath) {
		return Activator.getDefault().getBundle().getEntry(filePath);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		return validate();
	}

	/**
	 * Indicates whether the combo selection is valid.
	 * 
	 * @return true the combo selection is valid, false otherwise
	 */
	private boolean validate() {
		boolean isValid = combo.getText() != null && !"".equals(combo.getText());
		if (isValid) {
			setErrorMessage(null);
		} else {
			setErrorMessage("A template must be selected");
		}
		return isValid;
	}

}
