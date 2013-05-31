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
package org.eclipse.mylyn.docs.intent.client.ui.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.ColorManager;
import org.eclipse.mylyn.docs.intent.client.ui.editor.outline.IntentQuickOutlineControl;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceService;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;

/**
 * Editor allowing to interact with IntentElements stored in a repository.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentMultiPageEditor extends MultiPageEditorPart implements IntentEditor {

	/**
	 * The {@link IntentEditorImpl}.
	 */
	private IntentEditorImpl intentEditor;

	private Browser browser;

	private IntentSerializer intentSerializer = new IntentSerializer();

	/**
	 * Default constructor.
	 */
	public IntentMultiPageEditor() {
		intentEditor = new IntentEditorImpl();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
	 */
	@Override
	protected void createPages() {
		try {
			// Create Intent editor page
			addPage(intentEditor, getEditorInput());
			setPageText(0, "Intent editor ");
			setPageImage(0, IntentEditorActivator.getDefault().getImage("icon/outline/document.gif"));

			// Create browser page
			if (shouldDisplayPreviewPage()) {
				try {
					browser = new Browser(getContainer(), SWT.NONE);
					addPage(1, browser);
					setPageText(1, "Preview ");

					browser.setUrl(getHTMLPreviewURL());
					setPageImage(1, IntentEditorActivator.getDefault().getImage("icon/outline/html.png"));
				} catch (SWTError e) {
					// Can happen when browser cannot be created due to platform issues (missing xulrunner)
					// see bugzilla 409465
					IntentUiLogger
							.logError(
									"Could not initialize browser for Intent real-time preview, preference is deactivated.",
									e);

					// Disabling preference
					InstanceScope.INSTANCE.getNode(IntentEditorActivator.PLUGIN_ID).putBoolean(
							IntentPreferenceConstants.SHOW_PREVIEW_PAGE, false);
				}
			}
		} catch (PartInitException e) {
			IntentUiLogger.logError(e);
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.MultiPageEditorPart#init(org.eclipse.ui.IEditorSite,
	 *      org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setPartName(input.getName());
		super.init(site, input);
	}

	private boolean shouldDisplayPreviewPage() {
		return IntentPreferenceService.getBoolean(IntentPreferenceConstants.SHOW_PREVIEW_PAGE);
	}

	private String getHTMLPreviewURL() {
		String htmlPreviewLocation = "file:///"
				+ ((IntentEditorInput)getEditorInput()).getRepository().getRepositoryLocation()
				+ "generated/html/";
		EObject container = ((IntentEditorInput)getEditorInput()).getIntentElement();
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
	 * @see org.eclipse.ui.part.MultiPageEditorPart#pageChange(int)
	 */
	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (browser != null && newPageIndex == 1) {
			// Refresh browser URL in case section was renamed
			browser.setUrl(getHTMLPreviewURL());
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		intentEditor.doSave(monitor);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		intentEditor.doSaveAs();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return intentEditor.isSaveAsAllowed();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#getDocumentProvider()
	 */
	public IDocumentProvider getDocumentProvider() {
		return intentEditor.getDocumentProvider();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#close(boolean)
	 */
	public void close(boolean save) {
		intentEditor.close(save);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#isEditable()
	 */
	public boolean isEditable() {
		return intentEditor.isEditable();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#doRevertToSaved()
	 */
	public void doRevertToSaved() {
		intentEditor.doRevertToSaved();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#setAction(java.lang.String,
	 *      org.eclipse.jface.action.IAction)
	 */
	public void setAction(String actionID, IAction action) {
		intentEditor.setAction(actionID, action);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#getAction(java.lang.String)
	 */
	public IAction getAction(String actionId) {
		return intentEditor.getAction(actionId);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#setActionActivationCode(java.lang.String, char, int, int)
	 */
	public void setActionActivationCode(String actionId, char activationCharacter, int activationKeyCode,
			int activationStateMask) {
		intentEditor.setActionActivationCode(actionId, activationCharacter, activationKeyCode,
				activationStateMask);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#removeActionActivationCode(java.lang.String)
	 */
	public void removeActionActivationCode(String actionId) {
		intentEditor.removeActionActivationCode(actionId);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#showsHighlightRangeOnly()
	 */
	public boolean showsHighlightRangeOnly() {
		return intentEditor.showsHighlightRangeOnly();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#showHighlightRangeOnly(boolean)
	 */
	public void showHighlightRangeOnly(boolean showHighlightRangeOnly) {
		intentEditor.showHighlightRangeOnly(showHighlightRangeOnly);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#setHighlightRange(int, int, boolean)
	 */
	public void setHighlightRange(int offset, int length, boolean moveCursor) {
		intentEditor.setHighlightRange(offset, length, moveCursor);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#getHighlightRange()
	 */
	public IRegion getHighlightRange() {
		return intentEditor.getHighlightRange();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#resetHighlightRange()
	 */
	public void resetHighlightRange() {
		intentEditor.resetHighlightRange();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#getSelectionProvider()
	 */
	public ISelectionProvider getSelectionProvider() {
		return intentEditor.getSelectionProvider();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditor#selectAndReveal(int, int)
	 */
	public void selectAndReveal(int offset, int length) {
		intentEditor.selectAndReveal(offset, length);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#getIntentContent()
	 */
	public EObject getIntentContent() {
		return intentEditor.getIntentContent();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#containsElement(org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement)
	 */
	public boolean containsElement(IntentGenericElement elementToOpen) {
		return intentEditor.containsElement(elementToOpen);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#selectRange(org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement)
	 */
	public boolean selectRange(IntentGenericElement elementToSelectRangeWithLoadedFromAdapter) {
		setActivePage(0);
		return intentEditor.selectRange(elementToSelectRangeWithLoadedFromAdapter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#getBlockMatcher()
	 */
	public IntentPairMatcher getBlockMatcher() {
		return intentEditor.getBlockMatcher();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#getColorManager()
	 */
	public ColorManager getColorManager() {
		return intentEditor.getColorManager();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#getCurrentQuickOutline()
	 */
	public IntentQuickOutlineControl getCurrentQuickOutline() {
		return intentEditor.getCurrentQuickOutline();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#refreshOutlineView(org.eclipse.emf.ecore.EObject)
	 */
	public void refreshOutlineView(EObject newAST) {
		intentEditor.refreshOutlineView(newAST);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#refreshTitle(org.eclipse.emf.ecore.EObject)
	 */
	public void refreshTitle(EObject newAST) {
		intentEditor.refreshTitle(newAST);

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#isInitialFoldingStructureComplete()
	 */

	public boolean isInitialFoldingStructureComplete() {
		return intentEditor.isInitialFoldingStructureComplete();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#updateFoldingStructure(java.util.Map,
	 *      java.util.List, java.util.Map)
	 */
	public void updateFoldingStructure(Map<Annotation, Position> addedAnnotations,
			List<Annotation> deletedAnnotations, Map<Annotation, Position> modifiedAnnotations) {
		intentEditor.updateFoldingStructure(addedAnnotations, deletedAnnotations, modifiedAnnotations);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#getProjectionViewer()
	 */
	public ProjectionViewer getProjectionViewer() {
		return intentEditor.getProjectionViewer();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#getViewerConfiguration()
	 */
	public SourceViewerConfiguration getViewerConfiguration() {
		return intentEditor.getViewerConfiguration();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor#createQuickOutlinePresenter()
	 */
	public IInformationPresenter createQuickOutlinePresenter() {
		return intentEditor.createQuickOutlinePresenter();
	}

}
