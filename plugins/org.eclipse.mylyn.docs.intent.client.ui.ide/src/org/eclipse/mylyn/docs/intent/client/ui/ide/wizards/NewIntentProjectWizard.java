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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.mylyn.docs.intent.client.ui.ide.builder.ToggleNatureAction;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceConstants;
import org.eclipse.mylyn.docs.intent.client.ui.preferences.IntentPreferenceService;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryInitializer;
import org.eclipse.mylyn.docs.intent.collab.common.repository.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetView;

/**
 * The Intent project creation wizard.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
@SuppressWarnings("restriction")
public class NewIntentProjectWizard extends Wizard implements INewWizard, IExecutableExtension {

	/**
	 * Wizard title.
	 */
	private static final String NEW_INTENT_PROJECT_TITLE = "New Intent project"; //$NON-NLS-1$

	/**
	 * Constant for an empty default document.
	 */
	private static final String DEFAULT_INTENT_DOCUMENT = "Document {\n}";

	/**
	 * Wizard page allowing to create the intent project.
	 */
	protected WizardNewProjectCreationPage page;

	/**
	 * Optional wizard page allowing to select a template to initialize the created intent document.
	 */
	protected IntentTemplateWizardPage templatePage;

	/**
	 * The {@link IConfigurationElement} from which this wizard has been created.
	 */
	protected IConfigurationElement configElement;

	/**
	 * Constructor.
	 */
	public NewIntentProjectWizard() {
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle("New Intent Project");
	}

	/**
	 * Adding the page to the wizard.
	 */
	@Override
	public void addPages() {
		page = new WizardNewProjectCreationPage(NEW_INTENT_PROJECT_TITLE) {
			@Override
			public void createControl(Composite parent) {
				super.createControl(parent);

				// Bug 365052 : Working Set selection should be available in the new Intent Project wizard
				createWorkingSetGroup((Composite)getControl(), new StructuredSelection(), new String[] {
						"org.eclipse.ui.resourceWorkingSetPage", "org.eclipse.jdt.ui.JavaWorkingSetPage",
				});
			}
		};
		page.setTitle(NEW_INTENT_PROJECT_TITLE);
		page.setDescription("Select project name"); //$NON-NLS-1$
		addPage(page);

		templatePage = new IntentTemplateWizardPage();
		templatePage.setTitle("Template Selection");
		addPage(templatePage);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		final String defaultContent = getDefaultContent();
		// Step 1: execute all operations that modify workspace
		WorkspaceModifyOperation workspaceOperation = new NewIntentProjectWizardRunnable(page, defaultContent);
		try {
			getContainer().run(false, false, workspaceOperation);

			// Step 2 : open an editor on the created document
			try {
				Repository repository = IntentRepositoryManager.INSTANCE.getRepository(page.getProjectName());
				if (repository != null) {
					IntentEditorOpener.openIntentEditor(repository, false);

					// Step 3: open the getting started cheat sheet (according to preferences)
					if (IntentPreferenceService
							.getBoolean(IntentPreferenceConstants.SHOW_CHEAT_SHEET_ON_PROJECT_CREATION)) {
						IViewPart cheatSheetView = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage().showView("org.eclipse.ui.cheatsheets.views.CheatSheetView");
						if (cheatSheetView instanceof CheatSheetView) {
							((CheatSheetView)cheatSheetView)
									.setInput("org.eclipse.mylyn.docs.intent.idoc.cheatsheet.getstarted");
						}
					}

					// Step 4: open the project explorer view
					// TODO: remove this work-around when bugzilla 365084 gets fixed
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
							.showView("org.eclipse.ui.navigator.ProjectExplorer");
				}
			} catch (RepositoryConnectionException e) {
				IntentUiLogger.logError(e);
			}
			return true;
		} catch (CoreException e) {
			IntentUiLogger.logError(e);
		} catch (InterruptedException e) {
			IntentUiLogger.logError(e);
		} catch (InvocationTargetException e) {
			IntentUiLogger.logError(e);
		}
		return false;
	}

	/**
	 * Returns the default content to associate to the Intent Document to create.
	 * 
	 * @return the default content to associate to the Intent Document to create
	 */
	private String getDefaultContent() {
		if (getContainer().getCurrentPage().equals(templatePage)) {
			final String templateContent = templatePage.getContent();
			if (templateContent != null) {
				return templateContent;
			}
		}
		return DEFAULT_INTENT_DOCUMENT;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) {
		this.configElement = config;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {
		return (getContainer().getCurrentPage().equals(page) && page.isPageComplete())
				|| (getContainer().getCurrentPage().equals(templatePage) && templatePage.isPageComplete());
	}

	/**
	 * An {@link WorkspaceModifyOperation} containing all operations impacting workspace realized when
	 * performing finish on the {@link NewIntentProjectWizard}.
	 * 
	 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
	 */
	static class NewIntentProjectWizardRunnable extends WorkspaceModifyOperation {

		/**
		 * The new project creation page.
		 */
		protected WizardNewProjectCreationPage page;

		/**
		 * Default initial content if no template is selected.
		 */
		private String defaultContent;

		/**
		 * Default constructor.
		 * 
		 * @param page
		 *            the {@link WizardNewProjectCreationPage} used by the wizard
		 * @param defaultContent
		 *            the default content to associate to the Intent Document to create
		 */
		public NewIntentProjectWizardRunnable(WizardNewProjectCreationPage page, String defaultContent) {
			this.page = page;
			this.defaultContent = defaultContent;
		}

		@Override
		protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
				InterruptedException {
			// Step 1 : create project
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(page.getProjectName());
			IPath location = page.getLocationPath();
			if (!project.exists()) {
				IProjectDescription desc = project.getWorkspace()
						.newProjectDescription(page.getProjectName());
				if (location != null
						&& ResourcesPlugin.getWorkspace().getRoot().getLocation().equals(location)) {
					location = null;
				}
				desc.setLocation(location);
				project.create(desc, monitor);
				project.open(monitor);
			}
			if (!project.isOpen()) {
				project.open(monitor);
			}

			// Step 2 : updating working sets
			// Bug 365052 : Working Set selection should be available in the new Intent Project wizard
			IWorkingSet[] workingSets = page.getSelectedWorkingSets();
			if (PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getWorkingSetManager() != null) {
				PlatformUI.getWorkbench().getWorkingSetManager().addToWorkingSets(project, workingSets);
			}

			// Step 3 : add Intent nature
			ToggleNatureAction.toggleNature(project);
			IntentRepositoryInitializer.initializeContent(project.getName(), defaultContent);
		}
	}
}
