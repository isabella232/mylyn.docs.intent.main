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
package org.eclipse.mylyn.docs.intent.client.synchronizer;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;

/**
 * A job to launch the synchronization with the repository.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class SynchronizeRepositoryJob extends Job {

	/**
	 * Name to associate to this job.
	 */
	public static final String SYNCHRONIZE_REPOSITORY_JOB_NAME = "Synchronizing Repository";

	private SynchronizerRepositoryClient client;

	/**
	 * Constructor.
	 * 
	 * @param client
	 *            the synchronizer client
	 */
	public SynchronizeRepositoryJob(SynchronizerRepositoryClient client) {
		super(SYNCHRONIZE_REPOSITORY_JOB_NAME);
		this.client = client;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus run(final IProgressMonitor monitor) {
		if (client.getRepositoryObjectHandler() != null) {
			final RepositoryAdapter repositoryAdapter = client.getRepositoryObjectHandler()
					.getRepositoryAdapter();
			repositoryAdapter.execute(new IntentCommand() {

				public void execute() {
					repositoryAdapter.openSaveContext();
					client.setTraceabilityIndex((TraceabilityIndex)repositoryAdapter.reload(client
							.getTraceabilityIndex()));

					IntentLogger.getInstance().log(LogType.LIFECYCLE, "[Synchronizer] Start synchronization");
					synchronize(monitor, repositoryAdapter);
					repositoryAdapter.closeContext();

				}

			});
		} else {
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}

	/**
	 * Synchronize.
	 * 
	 * @param monitor
	 *            the progress monitor
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
	private void synchronize(final IProgressMonitor monitor, final RepositoryAdapter repositoryAdapter) {
		// We get all the compilation Status to add
		Collection<? extends CompilationStatus> statusList = new ArrayList<CompilationStatus>();
		try {
			statusList = client.getSynchronizer().synchronize(repositoryAdapter,
					client.getTraceabilityIndex(), BasicMonitor.toMonitor(monitor));
		} catch (InterruptedException e) {
			// Nothing to do : it means that the operation has been canceled
			IntentLogger.getInstance().log(LogType.LIFECYCLE, "[Synchronizer] Canceled.");
		}

		if (!monitor.isCanceled()) {

			try {
				// We add these status to the targets Element
				client.addAllStatusToTargetElement(statusList);
				if (monitor != null && !monitor.isCanceled()) {

					// A warning should be sent to the session so that the compiler cannot be notified of
					// changes made by the synchronizer on modeling units
					repositoryAdapter.setSendSessionWarningBeforeSaving(Lists
							.newArrayList(IntentLocations.INTENT_FOLDER));
					repositoryAdapter.save();

					IntentLogger.getInstance().log(
							LogType.LIFECYCLE,
							"[Synchronizer] Synchronization ended, detected " + statusList.size()
									+ " synchronization issues");
				}
			} catch (ReadOnlyException e) {
				// As we have just opened a save context, we are sure that this will never happens
			} catch (SaveException e) {
				IntentLogger.getInstance().log(LogType.ERROR, "Synchronizer failed to save changes", e);
				try {
					repositoryAdapter.undo();
				} catch (ReadOnlyException e1) {
					// As we have just opened a save context, we are sure that this will never happens
				}
			}
		}
	}
}
