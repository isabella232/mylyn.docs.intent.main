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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotation;
import org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.IntentAnnotationMessageType;
import org.eclipse.mylyn.docs.intent.client.ui.ide.builder.IntentNature;
import org.eclipse.mylyn.docs.intent.client.ui.ide.builder.ToggleNatureAction;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;
import org.eclipse.mylyn.docs.intent.client.ui.test.IntentUITestPlugin;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.collab.common.query.IntentDocumentQuery;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryInitializer;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.parser.IntentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.parser.test.utils.FileToStringConverter;
import org.eclipse.ui.PlatformUI;

/**
 * An abstract test class providing API for managing an Intent IDE projects and editors.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public abstract class AbstractIntentUITest extends TestCase implements ILogListener {

	/**
	 * Id of the new Intent Project wizard.
	 */
	public static final String INTENT_NEW_PROJECT_WIZARD_ID = "org.eclipse.mylyn.docs.intent.client.ui.ide.wizards.NewIntentProjectWizard";

	/**
	 * Path of a file containing an empty Itnent document.
	 */
	protected static final String INTENT_EMPTY_DOC_PATH = "data/unit/documents/empty.intent";

	/**
	 * While waiting for a client to be notified, indicates the time to wait before testing again.
	 */
	private static final int WAITING_DELAY_MILLIS = 500;

	/**
	 * The tested Intent {@link IProject}.
	 */
	protected IProject intentProject;

	/**
	 * The tested Intent {@link Repository}.
	 */
	protected Repository repository;

	/**
	 * A {@link RepositoryAdapter} opened on the tested Repository.
	 */
	protected RepositoryAdapter repositoryAdapter;

	/**
	 * A repository listener allowing to determine wether Intent clients (compiler, synchronizer...) have been
	 * notified or not.
	 */
	protected RepositoryListenerForTests repositoryListener;

	/**
	 * The tested {@link IntentDocument}.
	 */
	private IntentDocument intentDocument;

	/**
	 * All the currently opened {@link IntentEditor}s.
	 */
	private List<IntentEditor> openedEditors;

	/**
	 * Indicates the number of dynamically added extensions.
	 */
	private int dynamicExtensionCounter;

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		initClassLoader();

		// Step 1 : printing testclass name (make hudson debugs easier)
		for (int i = 0; i < getClass().getName().length() - 1; i++) {
			System.out.print("=");
		}
		System.out.println("=");
		System.out.println(getClass().getName());
		super.setUp();

		// Step 2 : deactivating the automatic build of the workspace (if needed)
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceDescription description = workspace.getDescription();
		if (description.isAutoBuilding()) {
			description.setAutoBuilding(false);
			workspace.setDescription(description);
		}

		// Step 3 : clean workspace, close welcome page
		WorkspaceUtils.cleanWorkspace();
		WorkspaceUtils.closeWelcomePage();
		waitForAllOperationsInUIThread();
		IntentEditorActivator.getDefault().getLog().addLogListener(this);
		openedEditors = new ArrayList<IntentEditor>();
	}

	/**
	 * Forces OSGI to load ui.ide plugin first. Otherwise, IntentRepositoryManager.INSTANCE.getRepository is
	 * called twice at the same time (?). Happens only in the test suite.
	 */
	private void initClassLoader() {
		assertNotNull(IntentNature.class);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		waitForAllOperationsInUIThread();
		// Step 1: close editors
		for (IntentEditor editor : openedEditors) {
			editor.close(false);
		}

		// Step 2: clean workspace
		if (intentProject != null) {
			IntentRepositoryManager.INSTANCE.deleteRepository(intentProject.getName());
			waitForAllOperationsInUIThread();

			intentProject.delete(true, true, new NullProgressMonitor());
		}
		IntentEditorActivator.getDefault().getLog().removeLogListener(this);
		WorkspaceUtils.cleanWorkspace();

		// Step 3: unregister repository listener and deactivate advance loggin
		if (repositoryListener != null) {
			Platform.removeLogListener(repositoryListener);
		}
		IEclipsePreferences node = InstanceScope.INSTANCE.getNode(IntentEditorActivator.getDefault()
				.getBundle().getSymbolicName());
		node.putBoolean(IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING, false);

		// Step 4: setting all fields to null (to avoid memory leaks)
		setAllFieldsToNull();

		// Step 5: remove all dynamically added contributions
		while (dynamicExtensionCounter > 0) {
			removeLastExtension();
		}
		super.tearDown();
	}

	/**
	 * Creates and register an new {@link org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryClient } in
	 * charge of detecting that events happened on the repository.
	 */
	protected final void registerRepositoryListener() {
		this.repositoryListener = new RepositoryListenerForTests();
		// Changing preferences : activating logging
		IEclipsePreferences node = InstanceScope.INSTANCE.getNode(IntentEditorActivator.PLUGIN_ID);
		node.putBoolean(IntentPreferenceConstants.ACTIVATE_ADVANCE_LOGGING, true);
		Platform.addLogListener(repositoryListener);
	}

	/**
	 * Creates a new empty Intent project.
	 * 
	 * @param projectName
	 *            the intent project name
	 */
	protected final void setUpIntentProject(final String projectName) {
		setUpIntentProject(projectName, INTENT_EMPTY_DOC_PATH, false);
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
	protected final void setUpIntentProject(final String projectName, String intentDocumentPath) {
		setUpIntentProject(projectName, intentDocumentPath, false);
	}

	/**
	 * Creates a new Intent project using the intent document located at the given path.
	 * 
	 * @param projectName
	 *            the intent project name
	 * @param intentDocumentPath
	 *            the path of the intent document to use (relative to the
	 *            org.eclipse.mylyn.docs.intent.client.ui.test project)
	 * @param listenForRepository
	 *            indicates whether a repository listener should be registered. If you want to determine if a
	 *            client has done a job (by calling {@link AbstractIntentUITest#waitForIndexer() for example}
	 *            ), this must be true.
	 */
	protected void setUpIntentProject(final String projectName, String intentDocumentPath,
			boolean listenForRepository) {
		// Disabling preview mechanism for performances improvements
		IEclipsePreferences node = InstanceScope.INSTANCE.getNode(IntentEditorActivator.PLUGIN_ID);
		node.putBoolean(IntentPreferenceConstants.SHOW_PREVIEW_PAGE, false);
		try {
			// Step 1: getting the content of the intent document located at the given path.
			File file = new File(intentDocumentPath);
			final String intentDocumentContent = FileToStringConverter.getFileAsString(file);

			// Step 2: creating the intent project
			doCreateIntentProject(projectName, intentDocumentContent);

			while (repositoryAdapter == null) {
				Thread.sleep(10);
			}

			// Step 3: registering the repository listener
			if (listenForRepository) {
				registerRepositoryListener();
				repositoryListener.clearPreviousEntries();
			}

			// Step 4: additional setup operations
			additionalSetUpOperations();

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
	 * Creates the intent project with the given name, with the given content.
	 * 
	 * @param projectName
	 *            the name of the project to create
	 * @param intentDocumentContent
	 *            the content to associate to the created intent document
	 * @throws CoreException
	 *             if the project cannot be created properly
	 */
	protected void doCreateIntentProject(final String projectName, final String intentDocumentContent)
			throws CoreException {
		IWorkspaceRunnable create = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				IProject project = WorkspaceUtils.createProject(projectName, monitor);
				ToggleNatureAction.toggleNature(project);

				IntentRepositoryInitializer.initializeContent(projectName, intentDocumentContent);

				// Step 3 : initializing all useful informations
				intentProject = project;
				setUpRepository(project);
			}
		};
		ResourcesPlugin.getWorkspace().run(create, null);
	}

	/**
	 * Set up the repository for the given project.
	 * 
	 * @param project
	 *            the project
	 */
	protected final void setUpRepository(IProject project) {
		intentDocument = null;
		assertNotNull(project);
		try {
			repository = IntentRepositoryManager.INSTANCE.getRepository(project.getName());
			assertNotNull(repository);
			repositoryAdapter = repository.createRepositoryAdapter();
		} catch (RepositoryConnectionException e) {
			AssertionFailedError error = new AssertionFailedError(
					"Cannot connect to the created IntentRepository");
			error.setStackTrace(e.getStackTrace());
			throw error;
		} catch (CoreException e) {
			AssertionFailedError error = new AssertionFailedError(
					"Cannot retrieve the correct IntentRepository type");
			error.setStackTrace(e.getStackTrace());
			throw error;
		}
		// wait for initialization completed
		waitForAllOperationsInUIThread();
	}

	// /**
	// * Loads the {@link IntentStructuredElement} located at the given path. If it contains an
	// IntentDocument,
	// * also updates the intentDocument field.
	// *
	// * @param intentDocumentModelPath
	// * the path of the Intent document model (from
	// * org.eclipse.mylyn.docs.intent.client.ui.test/data)
	// * @return the loaded {@link IntentStructuredElement}
	// */
	// protected IntentStructuredElement loadIntentDocumentFromTests(String intentDocumentModelPath) {
	// ResourceSet rs = new ResourceSetImpl();
	// URI documentURI = URI.createURI("platform:/plugin/org.eclipse.mylyn.docs.intent.client.ui.test/data/"
	// + intentDocumentModelPath);
	// Resource documentResource = rs.getResource(documentURI, true);
	// if (documentResource != null && documentResource.getContents().iterator().hasNext()
	// && documentResource.getContents().iterator().next() instanceof IntentStructuredElement) {
	// if (documentResource.getContents().iterator().next() instanceof IntentDocument) {
	// intentDocument = (IntentDocument)documentResource.getContents().iterator().next();
	// }
	// return (IntentStructuredElement)documentResource.getContents().iterator().next();
	// }
	// throw new AssertionFailedError("Could not load Intent model at " + intentDocumentModelPath);
	// }

	/**
	 * Parses the {@link IntentStructuredElement} located at the given path. If it contains an IntentDocument,
	 * also updates the intentDocument field.
	 * 
	 * @param intentDocumentModelPath
	 *            the path of the Intent document model (from
	 *            org.eclipse.mylyn.docs.intent.client.ui.test/data)
	 * @return the loaded {@link IntentStructuredElement}
	 */
	protected IntentDocument parseIntentDocumentFromTests(String intentDocumentModelPath) {
		EObject res = null;
		try {
			res = new IntentParser().parse(FileToStringConverter.getFileAsString(new File(
					intentDocumentModelPath)));
		} catch (ParseException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
		if (res instanceof IntentDocument) {
			intentDocument = (IntentDocument)res;
		}
		return intentDocument;
	}

	/**
	 * Returns the intentDocument associated to the current Intent project.
	 * 
	 * @return the intentDocument associated to the current Intent project
	 */
	protected final IntentDocument getIntentDocument() {
		if (intentDocument == null) {
			intentDocument = new IntentDocumentQuery(repositoryAdapter).getOrCreateIntentDocument();
		}
		return intentDocument;
	}

	/**
	 * Return the section at the given number.
	 * 
	 * @param number
	 *            the number of the section
	 * @return the section
	 */
	protected final IntentSection getIntentSection(int... number) {
		IntentSection section = getIntentDocument().getSubSections().get(number[0] - 1);
		for (int i = 1; i < number.length; i++) {
			section = section.getSubSections().get(number[i] - 1);
		}
		return section;
	}

	/**
	 * Opens an editor on the Document contained in the intent project.
	 * 
	 * @return the opened editor
	 */
	protected final IntentEditor openIntentEditor() {
		return openIntentEditor(getIntentDocument());
	}

	/**
	 * Opens an editor on the given {@link IntentStructuredElement}.
	 * 
	 * @param element
	 *            the {@link IntentStructuredElement} to open an editor on
	 * @return the opened editor
	 */
	protected final IntentEditor openIntentEditor(IntentStructuredElement element) {
		IntentEditorOpener.openIntentEditor(repository, element, true, null, true);
		waitForAllOperationsInUIThread();
		IntentEditor editor = IntentEditorOpener.getAlreadyOpenedEditor(element);
		assertNotNull(editor);
		openedEditors.add(editor);
		return editor;
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
	 * Wait until the end of all asynchronous operations launched in the UI Thread.
	 */
	protected static void waitForAllOperationsInUIThread() {
		while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// Nothing to do
			}
		}
	}

	/**
	 * Wait for synchronizer to complete work.
	 */
	protected final void waitForSynchronizer() {
		waitForSynchronizer(true);
	}

	/**
	 * Wait for repository to complete work.
	 */
	protected final void waitForIndexer() {
		waitForIndexer(true);
	}

	/**
	 * Wait for compiler to complete work.
	 */
	protected final void waitForCompiler() {
		waitForCompiler(true);
	}

	/**
	 * Wait for the project explorer refresher to complete work.
	 */
	protected final void waitForProjectExplorerRefresher() {
		waitForProjectExplorerRefresher(true);
	}

	/**
	 * Ensures that the synchronizer has been launched or not, according to the given boolean.
	 * 
	 * @param synchronizerShouldBeNotified
	 *            indicates whether the synchronizer should be notified or not
	 */
	protected final void waitForSynchronizer(boolean synchronizerShouldBeNotified) {
		waitForAllOperationsInUIThread();
		assertNotNull(
				"Cannot wait for synchronizer : you need to initialize a repository listener by calling the registerRepositoryListener() method",
				repositoryListener);
		if (synchronizerShouldBeNotified) {
			assertTrue("Time out : synchronizer should have handle changes but did not",
					repositoryListener.waitForModificationOn(synchronizerShouldBeNotified, "Synchronizer"));
		} else {
			assertFalse("Synchonizer should not have been notifed",
					repositoryListener.waitForModificationOn(synchronizerShouldBeNotified, "Synchronizer"));
		}
		try {
			Thread.sleep(WAITING_DELAY_MILLIS);
		} catch (InterruptedException e) {
			// fail silently
		}
		waitForAllOperationsInUIThread();
	}

	/**
	 * Ensures that the indexer has been launched or not, according to the given boolean.
	 * 
	 * @param indexerShouldBeNotified
	 *            indicates whether the indexer should be notified or not
	 */
	protected final void waitForIndexer(boolean indexerShouldBeNotified) {
		waitForLifecycleMessage(indexerShouldBeNotified, "Indexer");
	}

	/**
	 * Ensures that the project explorer refreshed has been launched or not, according to the given boolean.
	 * 
	 * @param refresherShouldBeNotified
	 *            indicates whether the indexer should be notified or not
	 */
	protected final void waitForProjectExplorerRefresher(boolean refresherShouldBeNotified) {
		waitForLifecycleMessage(refresherShouldBeNotified, "Project Explorer Refresher");
	}

	/**
	 * Ensures that the compiler has been launched or not, according to the given boolean.
	 * 
	 * @param compilerShouldBeNotified
	 *            indicates whether the compiler should be notified or not
	 */
	protected final void waitForCompiler(boolean compilerShouldBeNotified) {
		waitForAllOperationsInUIThread();
		assertNotNull(
				"Cannot wait for compiler : you need to initialize a repository listener by calling the registerRepositoryListener() method",
				repositoryListener);
		if (compilerShouldBeNotified) {
			assertTrue("Time out : compiler should have handle changes but did not",
					repositoryListener.waitForModificationOn(compilerShouldBeNotified, "Compiler"));
		} else {
			assertFalse("Compiler should not have been notifed",
					repositoryListener.waitForModificationOn(compilerShouldBeNotified, "Compiler"));
		}
		try {
			Thread.sleep(WAITING_DELAY_MILLIS);
		} catch (InterruptedException e) {
			// fail silently
		}
		waitForAllOperationsInUIThread();
	}

	/**
	 * Ensures that the client with the given identifier has been notified or not, according to the given
	 * boolean.
	 * 
	 * @param shouldHaveBeenNotified
	 *            indicates whether the client should be notified or not
	 * @param intentClientID
	 *            the ID of the listened client
	 */
	protected final void waitForLifecycleMessage(boolean shouldHaveBeenNotified, String intentClientID) {
		waitForAllOperationsInUIThread();
		assertNotNull(
				"Cannot wait for "
						+ intentClientID
						+ ": you need to initialize a repository listener by calling the registerRepositoryListener() method",
				repositoryListener);
		if (shouldHaveBeenNotified) {
			assertTrue("Time out: " + intentClientID + " should have handle changes but did not",
					repositoryListener.waitForModificationOn(shouldHaveBeenNotified, intentClientID));
		} else {
			assertFalse(intentClientID + " should not have been notifed",
					repositoryListener.waitForModificationOn(shouldHaveBeenNotified, intentClientID));
		}
		waitForAllOperationsInUIThread();
	}

	/**
	 * Fixes the issue associated to the Intent annotation with the given message using the quickfix.
	 * 
	 * @param message
	 *            the annotation message
	 * @param editor
	 *            the editor containing the annotation to search
	 * @param document
	 *            the editor's {@link IntentEditorDocument}
	 */
	protected void fixIssueUsingQuickFix(IntentEditor editor, IntentEditorDocument document, String message) {
		for (IntentAnnotation annotation : AnnotationUtils.getIntentAnnotations(editor,
				IntentAnnotationMessageType.SYNC_WARNING)) {
			if (annotation.getText().equals(message)) {
				AnnotationUtils.applyAnnotationFix(document, repositoryAdapter, annotation, 1);
				editor.doSave(new NullProgressMonitor());
				waitForCompiler();
				waitForSynchronizer();
				return;
			}
		}
		AnnotationUtils.displayAnnotations(editor);
		fail("Annotation not found: " + message);
	}

	/**
	 * Additional operations performed at the end of set-up.
	 */
	protected void additionalSetUpOperations() {
		if (this.repositoryListener != null) {
			// waiting for synchronizer to pass
			waitForSynchronizer();
		}
	}

	/**
	 * Sets all fields of the current test case to null (to avoid memory leaks).
	 */
	private void setAllFieldsToNull() {
		// For all fields defined in the current test and all its superclasses
		for (Class<?> clazz = this.getClass(); clazz != TestCase.class; clazz = clazz.getSuperclass()) {
			for (Field field : clazz.getDeclaredFields()) {
				boolean isReference = !field.getType().isPrimitive();
				try {
					field.setAccessible(true);
					boolean isSet = field.get(this) != null;
					// We do not clean non set references
					if (isReference && isSet) {
						boolean isFinal = Modifier.isFinal(field.getModifiers());
						// We do not clean final fields
						if (!isFinal) {
							// Setting the field to null
							field.set(this, null);
						}
					}
				} catch (IllegalArgumentException e) {
					// Do nothing
				} catch (IllegalAccessException e) {
					// Do nothing
				}
			}
		}
	}

	/**
	 * Dynamically adds to the extension registry a contribution from the given string.
	 * <p>
	 * With the given examples, this method will create the following extension :<br/>
	 * < extension point="org.eclipse.mylyn.docs.intent.client.synchronizer.extension"><br/>
	 * < SynchronizerExtensionDescription class="com.my.exampleclass.java"> <br/>
	 * </ SynchronizerExtensionDescription > <br/>
	 * </ extension >
	 * </p>
	 * 
	 * @param extensionPointName
	 *            the contributed extension point name (e.g.
	 *            "org.eclipse.mylyn.docs.intent.client.synchronizer.extension")
	 * @param extensionName
	 *            the contributed extension (e.g. "SynchronizerExtensionDescription")
	 * @param extensionValues
	 *            the extension values (e.g. ["class" => "com.my.exampleclass.java"])
	 * @param extensionPointPlugin
	 *            the plugin helding the registry listener (e.g. SynchronizerPlugin)
	 * @param registryListenerFieldName
	 *            the name of the registry listener field (e.g. "registryListener")
	 */
	protected void addExtension(String extensionPointName, String extensionName,
			Map<String, String> extensionValues, Plugin extensionPointPlugin, String registryListenerFieldName) {
		try {
			dynamicExtensionCounter++;
			// Step 1: create the contribution as a string
			String contribution = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><plugin><extension point=\""
					+ extensionPointName + "\" id=\"contribution" + dynamicExtensionCounter + "\">\n";
			contribution += "<" + extensionName + "\n";
			for (Entry<String, String> extensionValue : extensionValues.entrySet()) {
				contribution += extensionValue.getKey() + "=\"" + extensionValue.getValue() + "\"\n";
			}
			contribution += "></" + extensionName + ">\n";
			contribution += "</extension></plugin>";

			// Step 2: register the extension through configuration registry
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			Object token = ((ExtensionRegistry)registry).getTemporaryUserToken();
			ByteArrayInputStream is = new ByteArrayInputStream(contribution.getBytes());
			try {
				IContributor contributor = ContributorFactoryOSGi.createContributor(IntentUITestPlugin
						.getInstance().getBundle());
				assertTrue(registry.addContribution(is, contributor, false, null, null, token));
				// Work-around because registry.addContribution does dot
				// notifies the registryListeners
				Field registryListenerField = extensionPointPlugin.getClass().getDeclaredField(
						registryListenerFieldName);
				registryListenerField.setAccessible(true);
				Object object = registryListenerField.get(extensionPointPlugin);
				Method parseContributionMethods = object.getClass().getDeclaredMethod(
						"parseInitialContributions");
				parseContributionMethods.setAccessible(true);
				parseContributionMethods.invoke(object);
			} catch (SecurityException e) {
				fail(e.getMessage());
			} catch (IllegalArgumentException e) {
				fail(e.getMessage());
			} catch (InvocationTargetException e) {
				fail(e.getMessage());
			} catch (NoSuchMethodException e) {
				fail(e.getMessage());
			} catch (NoSuchFieldException e) {
				fail(e.getMessage());
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					fail(e.getMessage());
				}
			}
		} catch (IllegalAccessException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Removes the last extension contributed through the
	 * {@link AbstractIntentUITest#addExtension(Class, String, boolean)} method.
	 */
	private void removeLastExtension() {
		try {
			ExtensionRegistry extensionRegistry = (ExtensionRegistry)Platform.getExtensionRegistry();
			IContributor contributor = ContributorFactoryOSGi.createContributor(IntentUITestPlugin
					.getInstance().getBundle());
			IExtension[] extensions = extensionRegistry.getExtensions(contributor);
			IExtension extensionToRemove = extensions[extensions.length - 1];
			dynamicExtensionCounter--;
			assertNotNull("Could not properly remove test extension. ", extensionToRemove);
			extensionRegistry.removeExtension(extensionToRemove, extensionRegistry.getTemporaryUserToken());
		} catch (SecurityException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Reopens the current intent project, and waits for its full reactivation.
	 */
	protected void reopenIntentProject() {
		try {
			intentProject.close(new NullProgressMonitor());

			waitForAllOperationsInUIThread();
			intentProject.open(new NullProgressMonitor());
			waitForAllOperationsInUIThread();
			setUpRepository(intentProject);
			waitForCompiler();
		} catch (CoreException e) {
			AssertionFailedError assertionFailedError = new AssertionFailedError(
					"Issues encountered while reopening Intent project: " + e.getMessage());
			assertionFailedError.setStackTrace(e.getStackTrace());
			throw assertionFailedError;
		}
	}
}
