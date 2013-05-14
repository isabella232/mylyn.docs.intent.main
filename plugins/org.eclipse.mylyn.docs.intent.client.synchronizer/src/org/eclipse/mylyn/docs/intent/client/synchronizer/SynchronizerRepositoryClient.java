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
package org.eclipse.mylyn.docs.intent.client.synchronizer;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.mylyn.docs.intent.client.synchronizer.listeners.GeneratedElementListener;
import org.eclipse.mylyn.docs.intent.client.synchronizer.synchronizer.IntentSynchronizer;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusManager;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;

/**
 * In charge of communication between the repository and the synchronizer ; launch a synchronization operation
 * each time a modification on the compiler's generated elements index is detected.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class SynchronizerRepositoryClient extends AbstractRepositoryClient {

	/**
	 * The synchronizer to use.
	 */
	private IntentSynchronizer synchronizer;

	/**
	 * The listened TraceAbilityIndex.
	 */
	private TraceabilityIndex traceabilityIndex;

	/**
	 * The {@link CompilationStatusManager} to use for adding statuses.
	 */
	private CompilationStatusManager statusManager;

	/**
	 * SynchronizerRepositoryClient constructor.
	 * 
	 * @param traceabilityIndex
	 *            the listened {@link TraceabilityIndex}
	 * @param statusManager
	 *            the {@link CompilationStatusManager} to use for adding statuses
	 * @param repository
	 *            the repository to listen
	 */
	public SynchronizerRepositoryClient(TraceabilityIndex traceabilityIndex,
			CompilationStatusManager statusManager, Repository repository) {
		super(repository);
		IntentLogger.getInstance().log(LogType.LIFECYCLE, "[Synchronizer] Ready");
		this.synchronizer = new IntentSynchronizer(this);
		this.traceabilityIndex = traceabilityIndex;
		this.statusManager = statusManager;
	}

	/**
	 * Adds all the given compilationStatus to their targets instructions.
	 * 
	 * @param statusList
	 *            the list of status to add
	 */
	public void addAllStatusToTargetElement(final Collection<? extends CompilationStatus> statusList) {
		// Step 1: removing all invalid synchronization status (i.e. without target)
		Iterator<SynchronizerCompilationStatus> allPreviousStatus = Iterables.filter(
				statusManager.getCompilationStatusList(), SynchronizerCompilationStatus.class).iterator();
		Collection<CompilationStatus> toAdd = Sets.newLinkedHashSet();
		toAdd.addAll(statusList);
		Collection<SynchronizerCompilationStatus> toRemove = Sets.newLinkedHashSet();
		while (allPreviousStatus.hasNext()) {
			SynchronizerCompilationStatus oldStatus = allPreviousStatus.next();
			if (oldStatus.getTarget() == null) {
				toRemove.add(oldStatus);
			}
		}

		// Step 2: check if some statuses to add are actually old status
		// i.e. if a status with the same message did not already exist on the status target
		Iterator<CompilationStatus> toAddIterator = toAdd.iterator();
		while (toAddIterator.hasNext()) {
			CompilationStatus toAddCandidate = toAddIterator.next();
			boolean addCandidateIsActuallyNewStatus = toAddCandidate.getTarget() != null;
			if (addCandidateIsActuallyNewStatus) {
				Iterator<CompilationStatus> toAddCandidateTargetStatusesIterator = toAddCandidate.getTarget()
						.getCompilationStatus().iterator();
				while (addCandidateIsActuallyNewStatus && toAddCandidateTargetStatusesIterator.hasNext()) {
					addCandidateIsActuallyNewStatus = !toAddCandidate.getMessage().equals(
							toAddCandidateTargetStatusesIterator.next().getMessage());
				}
			}
			if (!addCandidateIsActuallyNewStatus) {
				statusManager.getCompilationStatusList().add(toAddCandidate);
				toAddIterator.remove();
			}
		}

		// Step 3: actually removing old statuses
		for (CompilationStatus oldSyncStatus : toRemove) {
			if (oldSyncStatus.getTarget() != null) {
				oldSyncStatus.getTarget().getCompilationStatus().remove(oldSyncStatus);
			}
		}
		statusManager.getCompilationStatusList().removeAll(toRemove);

		// Step 4 : add the new statuses
		for (CompilationStatus status : toAdd) {
			// We add it to its target and to the status manager
			if (status.getTarget() != null) {
				status.getTarget().getCompilationStatus().add(status);
				statusManager.getCompilationStatusList().add(status);
			}
		}
		statusManager.setSynchronizationTime(BigInteger.valueOf(System.currentTimeMillis()));
	}

	/**
	 * Sets the generatedElement listener, which will notify the Synchronizer if any generatedElement has
	 * changed.
	 * 
	 * @param generatedElementListener
	 *            the GeneratedElementListener
	 */
	public void setGeneratedElementListener(GeneratedElementListener generatedElementListener) {
		synchronizer.setGeneratedElementListener(generatedElementListener);
		generatedElementListener.setSynchronizer(this);

	}

	public TraceabilityIndex getTraceabilityIndex() {
		return traceabilityIndex;
	}

	public void setTraceabilityIndex(TraceabilityIndex traceabilityIndex) {
		this.traceabilityIndex = traceabilityIndex;
	}

	IntentSynchronizer getSynchronizer() {
		return synchronizer;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient#createNotificationJob(org.eclipse.mylyn.docs.intent.collab.handlers.notification.RepositoryChangeNotification)
	 */
	@Override
	protected Job createNotificationJob(RepositoryChangeNotification notification) {
		return new SynchronizeRepositoryJob(this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.impl.AbstractRepositoryClient#dispose()
	 */
	@Override
	public void dispose() {
		synchronizer.dispose();
		super.dispose();
	}
}
