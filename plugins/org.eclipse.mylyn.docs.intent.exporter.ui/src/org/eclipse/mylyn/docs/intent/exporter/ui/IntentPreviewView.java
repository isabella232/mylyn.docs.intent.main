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
package org.eclipse.mylyn.docs.intent.exporter.ui;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorInput;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceService;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * A view allowing to get an HTML Preview of the currently opened Intent documentation part.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentPreviewView extends ViewPart {

	public static final String ID = "org.eclipse.mylyn.docs.intent.exporter.ui.preview";

	private static final String PREF_NOT_ACTIVATED_MESSAGE = "<h3> Intent HTML Preview is not activated.</h3><p> You can activate it through Intent Preferences (Window > Preferences > Mylyn > Intent > Appearance > Show HTML Preview Page).</p>";

	private static IntentPreviewView INSTANCE;

	private Browser browser;

	private IntentSerializer intentSerializer;

	private IntentPreviewViewPartListener previewViewListener;

	private IEditorPart lastRefreshedEditor;

	/**
	 * Default constructor.
	 */
	public IntentPreviewView() {
		INSTANCE = this;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		try {
			browser = new Browser(parent, SWT.NONE);
			intentSerializer = new IntentSerializer();

			// Register a part listener allowing to detect when setting focus on an Intent editor
			previewViewListener = new IntentPreviewViewPartListener();
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.addPartListener(previewViewListener);

			// Refresh the preview view
			refreshPreviewView(getActiveEditorPart(), true);
		} catch (SWTError e) {
			// Can happen when browser cannot be created due to platform issues (missing xulrunner)
			// see bugzilla 409465
			IntentUiLogger.logError(
					"Could not initialize browser for Intent real-time preview, preference is deactivated.",
					e);

			// Disabling preference
			InstanceScope.INSTANCE.getNode(IntentEditorActivator.PLUGIN_ID).putBoolean(
					IntentPreferenceConstants.SHOW_PREVIEW_PAGE, false);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		refreshPreviewView();

	}

	/**
	 * Refreshes the preview view according to the currently active editor.
	 */
	public static void refreshPreviewView() {
		if (INSTANCE != null) {
			if (Display.getCurrent() == null) {
				Display.getDefault().asyncExec(new Runnable() {

					public void run() {
						INSTANCE.refreshPreviewView(INSTANCE.lastRefreshedEditor, true);
					}

				});
			} else {
				INSTANCE.refreshPreviewView(INSTANCE.lastRefreshedEditor, true);
			}
		}
	}

	private void refreshPreviewView(IWorkbenchPart activeEditor, boolean setURL) {
		if (!browser.isDisposed() && activeEditor instanceof IEditorPart
				&& ((IEditorPart)activeEditor).getEditorInput() instanceof IntentEditorInput) {
			if (!IntentPreferenceService.getBoolean(IntentPreferenceConstants.SHOW_PREVIEW_PAGE)) {
				browser.setText(PREF_NOT_ACTIVATED_MESSAGE);
			} else {
				if (setURL) {
					browser.setUrl(getHTMLPreviewURL((IntentEditorInput)((IEditorPart)activeEditor)
							.getEditorInput()));
				} else {
					browser.refresh();
				}
			}
			lastRefreshedEditor = (IEditorPart)activeEditor;
		}
	}

	private String getHTMLPreviewURL(IntentEditorInput editorInput) {
		String htmlPreviewLocation = "file:///" + editorInput.getRepository().getRepositoryLocation()
				+ "generated/html/";
		EObject container = editorInput.getIntentElement();
		if (container instanceof IntentDocument) {
			htmlPreviewLocation += "IntentDocumentation.html";
		} else {
			while (container != null
					&& !(container instanceof IntentStructuredElement && ((IntentStructuredElement)container)
							.getTitle() != null)) {
				container = container.eContainer();
			}
			if (container instanceof IntentStructuredElement) {
				htmlPreviewLocation += ((IntentStructuredElement)container).getCompleteLevel()
						+ "_"
						+ intentSerializer.serialize(((IntentStructuredElement)container).getTitle())
								.replace(" ", "") + ".html";
			}
		}
		return htmlPreviewLocation;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.removePartListener(previewViewListener);
		INSTANCE = null;
		super.dispose();
	}

	private IEditorPart getActiveEditorPart() {
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null) {
			IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
			if (activePage != null) {
				return activePage.getActiveEditor();
			}
		}
		return null;
	}

	/**
	 * An {@link IPartListener} that refreshes the preview view any time an Intent editor is focused.
	 * 
	 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
	 */
	public class IntentPreviewViewPartListener implements IPartListener {

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partOpened(IWorkbenchPart part) {
			refreshPreviewView(part, true);
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partDeactivated(IWorkbenchPart part) {
			if (part.equals(lastRefreshedEditor)) {
				lastRefreshedEditor = null;
			}
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partClosed(IWorkbenchPart part) {
			if (part.equals(lastRefreshedEditor)) {
				lastRefreshedEditor = null;
			}
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partBroughtToTop(IWorkbenchPart part) {
			refreshPreviewView(part, true);
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partActivated(IWorkbenchPart part) {
			refreshPreviewView(part, true);
		}
	}
}
