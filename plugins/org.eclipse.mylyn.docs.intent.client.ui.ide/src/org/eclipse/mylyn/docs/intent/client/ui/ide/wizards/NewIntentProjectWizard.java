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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRunnable;
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
import org.eclipse.mylyn.docs.intent.client.ui.ide.launcher.IDEApplicationManager;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * The Intent project creation wizard.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class NewIntentProjectWizard extends Wizard implements INewWizard, IExecutableExtension {

	private static final String NEW_INTENT_PROJECT_TITLE = "New Intent project"; //$NON-NLS-1$

	private static final String DEFAULT_INTENT_DOCUMENT = "Document {\n}";

	protected WizardNewProjectCreationPage page;

	protected IntentTemplateWizardPage templatePage;

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
		IWorkspaceRunnable create = new NewIntentProjectWizardRunnable(page, defaultContent);
		try {
			ResourcesPlugin.getWorkspace().run(create, null);
			return true;
		} catch (CoreException e) {
			IntentUiLogger.logError(e);
			return false;
		}
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
	 * An {@link IWorkspaceRunnable} containg all operations realized when performing finish on the
	 * {@link NewIntentProjectWizard}.
	 * 
	 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
	 */
	static class NewIntentProjectWizardRunnable implements IWorkspaceRunnable {

		protected WizardNewProjectCreationPage page;

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

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.core.resources.IWorkspaceRunnable#run(org.eclipse.core.runtime.IProgressMonitor)
		 */
		public void run(IProgressMonitor monitor) throws CoreException {

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

			// Step 3 : adding Intent nature
			ToggleNatureAction.toggleNature(project);
			IDEApplicationManager.initializeContent(project, defaultContent);

			// Step 4 : open an editor on the created document
			try {
				Repository repository = IntentRepositoryManager.INSTANCE.getRepository(project.getName());
				if (repository != null) {
					IntentEditorOpener.openIntentEditor(repository, false);
				}
			} catch (RepositoryConnectionException e) {
				IntentUiLogger.logError(e);
			}

		}
	}
}
