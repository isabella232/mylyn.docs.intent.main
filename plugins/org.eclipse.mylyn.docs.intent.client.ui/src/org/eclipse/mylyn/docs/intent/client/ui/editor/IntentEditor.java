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

import com.google.common.collect.Sets;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.ColorManager;
import org.eclipse.mylyn.docs.intent.client.ui.editor.configuration.IntentEditorConfiguration;
import org.eclipse.mylyn.docs.intent.client.ui.editor.outline.IntentOutlinePage;
import org.eclipse.mylyn.docs.intent.client.ui.editor.outline.IntentQuickOutlineControl;
import org.eclipse.mylyn.docs.intent.client.ui.editor.outline.QuickOutlineInformationProvider;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.ModelingUnitDecorationPainter;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;
import org.eclipse.mylyn.docs.intent.client.ui.repositoryconnection.EditorElementListAdapter;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryObjectHandler;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.ReadOnlyRepositoryObjectHandlerImpl;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.ReadWriteRepositoryObjectHandlerImpl;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.notification.elementList.ElementListAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.notification.elementList.ElementListNotificator;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.notification.typeListener.TypeNotificator;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.Notificator;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentGenericElement;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.query.IntentHelper;
import org.eclipse.mylyn.docs.intent.serializer.ParsedElementPosition;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * Editor allowing to interact with IntentElements stored in a repository.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentEditor extends TextEditor {

	/**
	 * The String representing this Editor context.
	 */
	private static final String EDITOR_CONTEXT = "org.eclipse.mylyn.docs.intent.client.ui.editor.context";

	/**
	 * Color manager for the syntax highlighting of this editor.
	 */
	private final ColorManager colorManager;

	/**
	 * The contentOutlinePage associated to this editor.
	 */
	private IntentOutlinePage contentOutlinePage;

	/**
	 * A listener which is notified each time the outline's selection changes.
	 */
	private ISelectionChangedListener selectionChangedListener;

	private IntentQuickOutlineControl currentQuickOutline;

	/**
	 * The editor's blocks matcher.
	 */
	private IntentPairMatcher blockMatcher;

	/**
	 * Default constructor.
	 */
	public IntentEditor() {
		super();
		colorManager = new ColorManager();
		blockMatcher = new IntentPairMatcher();
	}

	/**
	 * Initializes this editor with the given editor site and input. This method is automatically called
	 * shortly after the part is instantiated, marking the start of the part's life cycle. In our case, we
	 * assert that we are editing an Intent document.
	 * 
	 * @param site
	 *            the Editor site
	 * @param input
	 *            the editor input
	 * @throws PartInitException
	 *             if the given input is not a IntentEditorInput
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof IntentEditorInput)) {
			throw new PartInitException("Invalid Input: Must be IntentEditorInput");
		}
		super.init(site, input);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.editors.text.TextEditor#doSetInput(org.eclipse.ui.IEditorInput)
	 */
	@Override
	protected void doSetInput(IEditorInput input) throws CoreException {
		setSourceViewerConfiguration(new IntentEditorConfiguration(this, getPreferenceStore()));
		setDocumentProvider(createDocumentProvider());
		super.doSetInput(input);

		subscribeRepository((IntentEditorInput)getEditorInput(),
				(IntentDocumentProvider)getDocumentProvider(),
				((IntentEditorInput)getEditorInput()).getRepositoryAdapter());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		((ITextViewerExtension2)getSourceViewer()).addPainter(new ModelingUnitDecorationPainter(
				getSourceViewer(), this.colorManager));
	}

	/**
	 * Creates the document provider that will be used to handle the content of this editor.
	 * 
	 * @return the document provider
	 */
	protected IntentDocumentProvider createDocumentProvider() {
		IntentDocumentProvider provider = new IntentDocumentProvider(this);
		return provider;
	}

	/**
	 * Registers listeners in the repository used by the given editor input.
	 * 
	 * @param editorInput
	 *            the {@link IntentEditorInput}
	 * @param provider
	 *            the document provider
	 * @param repositoryAdapter
	 *            the repository adapter previously created by the editorInput
	 */
	private void subscribeRepository(IntentEditorInput editorInput, IntentDocumentProvider provider,
			RepositoryAdapter repositoryAdapter) {
		Repository newRepository = null;
		newRepository = editorInput.getRepository();
		provider.setRepository(newRepository);
		// Step 1 : creation of the Handler in the correct mode
		final RepositoryObjectHandler elementHandler = createElementHandler(repositoryAdapter, false);
		provider.addRepositoryObjectHandler(elementHandler);

		// Step 2 : creation of a Notificator listening changes on this element and compilation
		// errors.
		final Set<EObject> listenedObjects = new LinkedHashSet<EObject>();
		listenedObjects.add(editorInput.getIntentElement());
		final ElementListAdapter adapter = new EditorElementListAdapter();
		repositoryAdapter.execute(new IntentCommand() {

			public void execute() {
				Notificator listenedElementsNotificator = new ElementListNotificator(listenedObjects, adapter);
				Notificator compilationStatusNotificator = new TypeNotificator(Sets
						.newLinkedHashSet(CompilerPackage.eINSTANCE.getCompilationStatusManager()
								.getEAllStructuralFeatures()));
				elementHandler.addNotificator(listenedElementsNotificator);
				elementHandler.addNotificator(compilationStatusNotificator);
			}
		});
	}

	/**
	 * Creates the element handler matching the given mode.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 * @param readOnlyMode
	 *            the access mode
	 * @return the handler
	 */
	private static RepositoryObjectHandler createElementHandler(RepositoryAdapter repositoryAdapter,
			boolean readOnlyMode) {
		final RepositoryObjectHandler elementHandler;
		if (readOnlyMode) {
			elementHandler = new ReadOnlyRepositoryObjectHandlerImpl();
			elementHandler.setRepositoryAdapter(repositoryAdapter);
		} else {
			elementHandler = new ReadWriteRepositoryObjectHandlerImpl(repositoryAdapter);
		}
		return elementHandler;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.editors.text.TextEditor#dispose()
	 */
	@Override
	public void dispose() {
		((IntentDocumentProvider)this.getDocumentProvider()).close();
		super.dispose();
		/*
		 * Dispose the block matcher
		 */
		if (blockMatcher != null) {
			blockMatcher.dispose();
			blockMatcher = null;
		}
		colorManager.dispose();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.editors.text.TextEditor#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter.equals(IContentOutlinePage.class)) {
			return getOutlinePage();
		}
		return super.getAdapter(adapter);

	}

	/**
	 * Returns the content outline page associated to this editor.
	 * 
	 * @return the content outline page associated to this editor
	 */
	private Object getOutlinePage() {
		if (contentOutlinePage == null) {
			contentOutlinePage = new IntentOutlinePage(this);
			selectionChangedListener = createSelectionChangeListener();
			contentOutlinePage.addSelectionChangedListener(selectionChangedListener);
		}
		return contentOutlinePage;
	}

	/**
	 * Methods which is notified when the outline's selection changes.
	 * 
	 * @param event
	 *            is the selection changed event
	 */
	protected void selectionChangedDetected(SelectionChangedEvent event) {

		ISelection selection = event.getSelection();
		Object selectedElement = ((IStructuredSelection)selection).getFirstElement();
		if (selectedElement instanceof ModelingUnitInstructionReference) {
			selectedElement = ((ModelingUnitInstructionReference)selectedElement).eContainer();
		}
		selectRange((IntentGenericElement)selectedElement);

	}

	/**
	 * Creates a listener which is notified when the outline's selection changes.
	 * 
	 * @return the listener which is notified when the outline's selection changes
	 */
	protected ISelectionChangedListener createSelectionChangeListener() {
		return new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				selectionChangedDetected(event);
			}
		};
	}

	/**
	 * Returns the color manager of this editor.
	 * 
	 * @return the color manager of this editor
	 */
	public ColorManager getColorManager() {
		return colorManager;
	}

	/**
	 * This will create the quick outline presenter and install it on this editor.
	 * 
	 * @return The quick outline presenter.
	 */
	public IInformationPresenter getQuickOutlinePresenter() {

		InformationPresenter informationPresenter = new InformationPresenter(
				new IInformationControlCreator() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.text.IInformationControlCreator#createInformationControl(org.eclipse.swt.widgets.Shell)
					 */
					public IInformationControl createInformationControl(Shell parent) {
						// We active the context
						return new IntentQuickOutlineControl(parent, SWT.RESIZE, IntentEditor.this, true);
					}

				});
		informationPresenter.install(getSourceViewer());
		IInformationProvider provider = new QuickOutlineInformationProvider(this);
		informationPresenter.setInformationProvider(provider, IDocument.DEFAULT_CONTENT_TYPE);
		informationPresenter.setInformationProvider(provider, IntentDocumentProvider.INTENT_DESCRIPTIONUNIT);
		informationPresenter.setInformationProvider(provider, IntentDocumentProvider.INTENT_MODELINGUNIT);
		informationPresenter.setInformationProvider(provider,
				IntentDocumentProvider.INTENT_STRUCTURAL_CONTENT);
		informationPresenter.setInformationProvider(provider, IntentDocumentProvider.INTENT_TITLE);

		final int minimalWidth = 50;
		final int minimalHeight = 30;
		informationPresenter.setSizeConstraints(minimalWidth, minimalHeight, true, false);
		informationPresenter.setAnchor(AbstractInformationControlManager.ANCHOR_GLOBAL);

		return informationPresenter;
	}

	/**
	 * Returns the current quick Outline associated to this editor.
	 * 
	 * @return the current quick Outline associated to this editor (can be null)
	 */
	public IntentQuickOutlineControl getCurrentQuickOutline() {
		return currentQuickOutline;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
	 */
	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] {EDITOR_CONTEXT,
		});
	}

	/**
	 * Return the content of this editor as an Intent AST.
	 * 
	 * @return the content of this editor as an Intent AST
	 */
	public EObject getIntentContent() {
		IDocument document = this.getDocumentProvider().getDocument(this.getEditorInput());
		if (document instanceof IntentEditorDocument) {
			return (EObject)((IntentEditorDocument)document).getAST();
		}
		return null;
	}

	/**
	 * Sets the highlighted range of this text editor to the specified element ; if the element isn't in the
	 * given editor, open a new editor.
	 * 
	 * @param element
	 *            the element to highlight
	 */
	public void selectRange(final IntentGenericElement element) {

		// We first get the position of the element
		ParsedElementPosition position = null;
		int begin = 0;
		int length = 0;
		position = ((IntentEditorDocument)this.getDocumentProvider().getDocument(this.getEditorInput()))
				.getIntentPosition(element);
		// If the given element has no position, we take the position of its container
		// but not its length

		if (element != null) {

			EObject container = element;
			while ((position == null) && (container != null)) {
				length = -1;
				position = ((IntentEditorDocument)this.getDocumentProvider().getDocument(
						this.getEditorInput())).getIntentPosition(container);
				container = container.eContainer();
			}

			// If we found a position
			if (position != null) {
				begin = position.getOffset();
				if (length != -1) {
					length = position.getLength();
				} else {
					length = 1;
				}
			}

			if (begin > -1 && length >= 0) {
				ISourceViewer viewer = getSourceViewer();
				StyledText widget = viewer.getTextWidget();
				widget.setRedraw(false);
				setHighlightRange(begin, length, true);
				selectAndReveal(begin, length);
				widget.setRedraw(true);
			} else {

				if (element != null) {
					Repository repository = ((IntentDocumentProvider)this.getDocumentProvider())
							.getRepository();
					IntentEditorOpener.openIntentEditor(repository, element, false, element, false);
				}
			}
		}
	}

	/**
	 * Refresh the outline view.
	 * 
	 * @param newAST
	 *            the new value of the AST to use for refreshing this view
	 */
	public void refreshOutlineView(EObject newAST) {
		((IntentOutlinePage)getOutlinePage()).refresh(newAST);
	}

	/**
	 * Indicates if this editor contains the given element.
	 * 
	 * @param elementToOpen
	 *            the element to determine if it's contained in this editor
	 * @return true if this editor contains the given element, false otherwise
	 */
	public boolean containsElement(IntentGenericElement elementToOpen) {
		boolean containsElement = IntentHelper.containsElement((IntentGenericElement)this.getIntentContent(),
				elementToOpen);
		return containsElement;
	}

	/**
	 * Refreshes the title according to the new AST.
	 * 
	 * @param newAST
	 *            the new AST to compute the title from
	 */
	public void refreshTitle(EObject newAST) {
		String titleFromElement = ((IntentEditorInput)this.getEditorInput()).getTitleFromElement(newAST);
		setPartName(titleFromElement);
	}

	public IntentPairMatcher getBlockMatcher() {
		return blockMatcher;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#configureSourceViewerDecorationSupport(org.eclipse.ui.texteditor.SourceViewerDecorationSupport)
	 */
	@Override
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		support.setCharacterPairMatcher(blockMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(IntentPreferenceConstants.MATCHING_BRACKETS,
				IntentPreferenceConstants.MATCHING_BRACKETS_COLOR);
		IPreferenceStore pref = IntentEditorActivator.getDefault().getPreferenceStore();
		IPreferenceStore[] stores = {getPreferenceStore(), pref,
		};
		setPreferenceStore(new ChainedPreferenceStore(stores));
		support.install(getPreferenceStore());
		super.configureSourceViewerDecorationSupport(support);
	}
}
