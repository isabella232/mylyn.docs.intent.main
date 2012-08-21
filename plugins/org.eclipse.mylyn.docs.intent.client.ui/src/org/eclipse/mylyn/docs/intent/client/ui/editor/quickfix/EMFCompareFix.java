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
package org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix;

import java.util.Collection;
import java.util.HashMap;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.internal.Utilities;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSnapshot;
import org.eclipse.emf.compare.diff.metamodel.ComparisonSnapshot;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.compare.ui.editor.ModelCompareEditorInput;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.services.IServiceLocator;

/**
 * Proposal used to fix a Synchronization issue by opening the compare Editor.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class EMFCompareFix extends AbstractIntentFix {

	private static final String COMPARE_EDITOR_TITLE = "Comparing Intent Document and Working Copy";

	/**
	 * Default constructor.
	 * 
	 * @param annotation
	 *            the annotation describing the synchronization issue.
	 */
	public EMFCompareFix(Annotation annotation) {
		super(annotation);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.quickfix.AbstractIntentFix#applyFix(org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter,
	 *      org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument)
	 */
	@Override
	protected void applyFix(RepositoryAdapter repositoryAdapter, IntentEditorDocument document) {
		// Step 1 : getting the resources to compare URI
		String workingCopyResourceURI = ((SynchronizerCompilationStatus)syncAnnotation.getCompilationStatus())
				.getWorkingCopyResourceURI().replace("\"", "");
		String generatedResourceURI = ((SynchronizerCompilationStatus)syncAnnotation.getCompilationStatus())
				.getCompiledResourceURI().replace("\"", "");

		// Step 2 : loading the resources
		Resource generatedResource = repositoryAdapter.getResource(generatedResourceURI);
		ResourceSetImpl rs = new ResourceSetImpl();
		Resource workingCopyResource = rs.getResource(URI.createURI(workingCopyResourceURI), true);

		// Step 3 : opening a new Compare Editor on these two resources
		try {
			// Step 3.1 : making match and diff
			final HashMap<String, Object> options = new HashMap<String, Object>();
			options.put(MatchOptions.OPTION_IGNORE_XMI_ID, Boolean.TRUE);
			MatchModel match = MatchService.doResourceMatch(generatedResource, workingCopyResource, options);
			DiffModel diff = DiffService.doDiff(match, false);

			// Step 3.2 : creating a comparaison snapshot from this diff
			ComparisonResourceSnapshot snapshot = DiffFactory.eINSTANCE.createComparisonResourceSnapshot();
			snapshot.setDiff(diff);
			snapshot.setMatch(match);

			// Step 3.3 : open a compare dialog
			final CompareConfiguration compareConfig = new IntentCompareConfiguration(generatedResource,
					workingCopyResource);
			ModelCompareEditorInput input = new IntentCompareEditorInput(snapshot, compareConfig);
			compareConfig.setContainer(input);
			input.setTitle(COMPARE_EDITOR_TITLE + " (" + workingCopyResourceURI + ")");
			CompareUI.openCompareDialog(input);

		} catch (InterruptedException e) {
			// Editor will not be opened
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		return "See differences in Compare Editor";
	}

	/**
	 * A custom CompareEditorInput for Intent.
	 * 
	 * @author alagarde
	 */
	private class IntentCompareEditorInput extends ModelCompareEditorInput {

		private CompareConfiguration compareConfig;

		private ListenerList listenerList = new ListenerList();

		private boolean isDirty;

		/**
		 * This constructor takes a {@link ComparisonSnapshot} as input.
		 * 
		 * @param snapshot
		 *            The ComparisonSnapshot loaded from an emfdiff.
		 * @param compareConfig
		 *            the compare config
		 */
		public IntentCompareEditorInput(ComparisonSnapshot snapshot, CompareConfiguration compareConfig) {
			super(snapshot);
			this.compareConfig = compareConfig;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.compare.CompareEditorInput#getCompareConfiguration()
		 */
		@Override
		public CompareConfiguration getCompareConfiguration() {
			return compareConfig;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.compare.CompareEditorInput#getWorkbenchPart()
		 */
		@Override
		public IWorkbenchPart getWorkbenchPart() {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.compare.CompareEditorInput#getServiceLocator()
		 */
		@Override
		public IServiceLocator getServiceLocator() {
			return new IServiceLocator() {

				public boolean hasService(Class api) {
					if (api.equals(IHandlerService.class)) {
						return true;
					}
					return false;
				}

				public Object getService(Class api) {
					if (api.equals(IHandlerService.class)) {
						return new IntentCompareHandlerService();
					}
					return null;
				}
			};
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.compare.CompareEditorInput#addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
		 */
		@Override
		public void addPropertyChangeListener(IPropertyChangeListener listener) {
			if (listener != null) {
				listenerList.add(listener);
			}
			super.addPropertyChangeListener(listener);
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.compare.CompareEditorInput#removePropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
		 */
		@Override
		public void removePropertyChangeListener(IPropertyChangeListener listener) {
			if (listenerList != null) {
				listenerList.remove(listener);
			}
			super.removePropertyChangeListener(listener);
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.compare.CompareEditorInput#setDirty(boolean)
		 */
		@Override
		public void setDirty(boolean dirty) {
			boolean oldDirty = isDirty;
			isDirty = dirty;
			if (oldDirty != isDirty) {
				Utilities.firePropertyChange(listenerList, this, DIRTY_STATE, Boolean.valueOf(oldDirty),
						Boolean.valueOf(isSaveNeeded()));
			}

		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.compare.CompareEditorInput#isDirty()
		 */
		@Override
		public boolean isDirty() {
			return this.isDirty;
		}

	}

	/**
	 * A IHandlerService for Intent.
	 * 
	 * @author alagarde
	 */
	private class IntentCompareHandlerService implements IHandlerService {

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.services.IDisposable#dispose()
		 */
		public void dispose() {
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.services.IServiceWithSources#removeSourceProvider(org.eclipse.ui.ISourceProvider)
		 */
		public void removeSourceProvider(ISourceProvider provider) {
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.services.IServiceWithSources#addSourceProvider(org.eclipse.ui.ISourceProvider)
		 */
		public void addSourceProvider(ISourceProvider provider) {
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#setHelpContextId(org.eclipse.core.commands.IHandler,
		 *      java.lang.String)
		 */
		public void setHelpContextId(IHandler handler, String helpContextId) {
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#readRegistry()
		 */
		public void readRegistry() {
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#getCurrentState()
		 */
		public IEvaluationContext getCurrentState() {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#executeCommandInContext(org.eclipse.core.commands.ParameterizedCommand,
		 *      org.eclipse.swt.widgets.Event, org.eclipse.core.expressions.IEvaluationContext)
		 */
		// CHECKSTYLE:OFF
		public Object executeCommandInContext(ParameterizedCommand command, Event event,
				IEvaluationContext context) throws ExecutionException, NotDefinedException,
				NotEnabledException, NotHandledException {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#executeCommand(org.eclipse.core.commands.ParameterizedCommand,
		 *      org.eclipse.swt.widgets.Event)
		 */
		public Object executeCommand(ParameterizedCommand command, Event event) throws ExecutionException,
				NotDefinedException, NotEnabledException, NotHandledException {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#executeCommand(java.lang.String,
		 *      org.eclipse.swt.widgets.Event)
		 */
		public Object executeCommand(String commandId, Event event) throws ExecutionException,
				NotDefinedException, NotEnabledException, NotHandledException {
			return null;
		}

		// CHECKSTYLE:ON
		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#deactivateHandlers(java.util.Collection)
		 */
		public void deactivateHandlers(Collection activations) {
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#deactivateHandler(org.eclipse.ui.handlers.IHandlerActivation)
		 */
		public void deactivateHandler(IHandlerActivation activation) {
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#createExecutionEvent(org.eclipse.core.commands.ParameterizedCommand,
		 *      org.eclipse.swt.widgets.Event)
		 */
		public ExecutionEvent createExecutionEvent(ParameterizedCommand command, Event event) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#createExecutionEvent(org.eclipse.core.commands.Command,
		 *      org.eclipse.swt.widgets.Event)
		 */
		public ExecutionEvent createExecutionEvent(Command command, Event event) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#createContextSnapshot(boolean)
		 */
		public IEvaluationContext createContextSnapshot(boolean includeSelection) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String,
		 *      org.eclipse.core.commands.IHandler, org.eclipse.core.expressions.Expression, int)
		 */
		public IHandlerActivation activateHandler(String commandId, IHandler handler, Expression expression,
				int sourcePriorities) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String,
		 *      org.eclipse.core.commands.IHandler, org.eclipse.core.expressions.Expression, boolean)
		 */
		public IHandlerActivation activateHandler(String commandId, IHandler handler, Expression expression,
				boolean global) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String,
		 *      org.eclipse.core.commands.IHandler, org.eclipse.core.expressions.Expression)
		 */
		public IHandlerActivation activateHandler(String commandId, IHandler handler, Expression expression) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String,
		 *      org.eclipse.core.commands.IHandler)
		 */
		public IHandlerActivation activateHandler(String commandId, IHandler handler) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(org.eclipse.ui.handlers.IHandlerActivation)
		 */
		public IHandlerActivation activateHandler(IHandlerActivation activation) {
			return null;
		}

	}

}
