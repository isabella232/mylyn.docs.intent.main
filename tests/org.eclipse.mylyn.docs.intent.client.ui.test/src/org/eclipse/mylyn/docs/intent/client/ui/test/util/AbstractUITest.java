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
package org.eclipse.mylyn.docs.intent.client.ui.test.util;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentDocumentProvider;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.client.ui.ide.builder.ToggleNatureAction;
import org.eclipse.mylyn.docs.intent.client.ui.ide.launcher.IDEApplicationManager;
import org.eclipse.mylyn.docs.intent.client.ui.ide.launcher.IntentProjectManager;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.collab.utils.RepositoryCreatorHolder;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.FileToStringConverter;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * An abstract test class providing API for manage an Intent IDE projects and editors.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public abstract class AbstractUITest extends TestCase implements ILogListener {

	public static final String INTENT_NEW_PROJECT_WIZARD_ID = "org.eclipse.mylyn.docs.intent.client.ui.ide.wizards.NewIntentProjectWizard";

	protected IProject intentProject;

	protected Repository repository;

	protected RepositoryAdapter repositoryAdapter;

	private IntentDocument intentDocument;

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		cleanWorkspace();
		closeWelcomePage();
		IntentEditorActivator.getDefault().getLog().addLogListener(this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		if (intentProject != null) {
			intentProject.delete(true, true, new NullProgressMonitor());
		}
		IntentEditorActivator.getDefault().getLog().removeLogListener(this);
		cleanWorkspace();
		super.tearDown();
	}

	/**
	 * Close the welcomePage.
	 */
	protected void closeWelcomePage() {
		IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActivePart();
		if (activePart != null && "Welcome".equals(activePart.getTitle()) && activePart instanceof IViewPart) {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.hideView((IViewPart)activePart);
		}
		waitForAllOperationsInUIThread();
	}

	/**
	 * Creates a new Intent project using the intent document located at the given path.
	 * 
	 * @param projectName
	 *            the intent project name
	 * @param intentDocumentPath
	 *            the path of the intent document to use (relative to the
	 *            org.eclipse.mylyn.docs.intent.client.ui.test project).
	 */
	public void setUpIntentProject(final String projectName, String intentDocumentPath) {
		try {
			// Step 1 : getting the content of the intent document located at the given path.
			File file = new File(intentDocumentPath);
			final String intentDocumentContent = FileToStringConverter.getFileAsString(file);

			// Step 2 : creating the intent project
			IWorkspaceRunnable create = new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					IProject project = createProject(projectName, monitor);

					IDEApplicationManager.initializeContent(project, intentDocumentContent);
					ToggleNatureAction.toggleNature(project);

					// Step 3 : initializing all useful informations
					intentProject = project;
					try {
						repository = IntentProjectManager.getRepository(project);
						repositoryAdapter = RepositoryCreatorHolder.getCreator()
								.createRepositoryAdapterForRepository(repository);
					} catch (RepositoryConnectionException e) {
						AssertionFailedError error = new AssertionFailedError(
								"Cannot connect to the created IntentRepository");
						error.setStackTrace(e.getStackTrace());
						throw error;
					}
				}

			};
			ResourcesPlugin.getWorkspace().run(create, null);

			while (repositoryAdapter == null
			// && (repository == null || ((WorkspaceRepository)repository).getEditingDomain()
			// .getCommandStack() == null)
			) {
				Thread.sleep(10);
			}
		} catch (CoreException e) {
			AssertionFailedError error = new AssertionFailedError("Failed to create Intent project");
			error.setStackTrace(e.getStackTrace());
			throw error;
		} catch (IOException e) {
			AssertionFailedError error = new AssertionFailedError(
					"Failed to get content of intent document '" + intentDocumentPath + "'");
			error.setStackTrace(e.getStackTrace());
			throw error;
		} catch (InterruptedException e) {
			AssertionFailedError error = new AssertionFailedError("Failed to create Intent project");
			error.setStackTrace(e.getStackTrace());
			throw error;
		}
	}

	/**
	 * Returns the intentDocument associated to the current Intent project.
	 * 
	 * @return the intentDocument associated to the current Intent project
	 */
	public IntentDocument getIntentDocument() {
		if (intentDocument == null) {
			try {
				Resource documentResource = repositoryAdapter
						.getOrCreateResource(IntentLocations.INTENT_INDEX);
				assertTrue("Invalid content of resource '" + IntentLocations.INTENT_INDEX + "'",
						documentResource.getContents().iterator().next() instanceof IntentDocument);
				intentDocument = (IntentDocument)documentResource.getContents().iterator().next();
			} catch (ReadOnlyException e) {
				// Cannot happen in the test context : no readonly access
			}

		}
		return intentDocument;
	}

	/**
	 * Opens an editor on the Document contained in the intent project.
	 * 
	 * @return the opened editor
	 */
	public IntentEditor openIntentEditor() {
		return openIntentEditor(getIntentDocument());
	}

	/**
	 * Opens an editor on the given {@link IntentStructuredElement}.
	 * 
	 * @param element
	 *            the {@link IntentStructuredElement} to open an editor on
	 * @return the opened editor
	 */
	public IntentEditor openIntentEditor(IntentStructuredElement element) {
		IntentEditorOpener.openIntentEditor(repository, element, true, null, true);
		waitForAllOperationsInUIThread();
		return IntentEditorOpener.getAlreadyOpenedEditor(element);
	}

	/**
	 * Wait until the end of all asynchronous operations launched in the UI Thread.
	 */
	public static void waitForAllOperationsInUIThread() {
		while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// Nothing to do
			}
		}
	}

	/**
	 * Indicates if the given editor contains an annotation of the given {@link IntentAnnotationMessageType},
	 * with the given expectedMessage exactly if the exactMessage parameter is true, or containing the given
	 * expectedMessage if false.
	 * 
	 * @param intentEditor
	 *            the editor to search into
	 * @param messageType
	 *            the searched {@link IntentAnnotationMessageType}
	 * @param expectedMessage
	 *            the searched message
	 * @param exactMessage
	 *            indicates if the annotation's message should be exactly the same as the expectedMessage (if
	 *            true), or should contain the given expectedMessage (if false)
	 * @return true if the given editor contains the searched annotation, false otherwise
	 */
	public boolean hasIntentAnnotation(IntentEditor intentEditor, IntentAnnotationMessageType messageType,
			String expectedMessage, boolean exactMessage) {
		Iterator annotationIterator = ((IntentDocumentProvider)intentEditor.getDocumentProvider())
				.getAnnotationModel(null).getAnnotationIterator();
		while (annotationIterator.hasNext()) {
			Object annotation = annotationIterator.next();
			if (annotation instanceof IntentAnnotation) {
				if (messageType.equals(((IntentAnnotation)annotation).getMessageType())) {
					String annotationMessage = ((Annotation)annotation).getText();
					if (exactMessage && expectedMessage.equals(annotationMessage)
							|| annotationMessage.contains(expectedMessage)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.ILogListener#logging(org.eclipse.core.runtime.IStatus, java.lang.String)
	 */
	public void logging(IStatus status, String plugin) {
		if (status.getSeverity() == IStatus.ERROR) {
			fail(status.getMessage());
		}
	}

	/**
	 * Deletes every project in the workspace.
	 */
	protected void cleanWorkspace() {
		for (final IProject proj : Lists.newArrayList(ResourcesPlugin.getWorkspace().getRoot().getProjects())) {
			try {
				proj.delete(true, new NullProgressMonitor());
			} catch (CoreException e) {
				// Nothing we can do
			}
		}
	}

	/**
	 * Creates an IProject with the given name.
	 * 
	 * @param projectName
	 *            the name of the project to create
	 * @param monitor
	 *            the monitor to use when creating the project
	 * @return the create IProject
	 * @throws CoreException
	 *             can occur if the project cannot be created properly
	 */
	private static IProject createProject(final String projectName, IProgressMonitor monitor)
			throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		if (!project.exists()) {
			project.create(monitor);
			project.open(monitor);
		}
		if (!project.isOpen()) {
			project.open(monitor);
		}
		return project;
	}
}
