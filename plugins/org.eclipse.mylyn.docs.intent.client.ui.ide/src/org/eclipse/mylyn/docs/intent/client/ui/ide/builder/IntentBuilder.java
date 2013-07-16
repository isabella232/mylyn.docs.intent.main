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
package org.eclipse.mylyn.docs.intent.client.ui.ide.builder;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;

/**
 * Intent project builder.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentBuilder extends IncrementalProjectBuilder {

	/**
	 * The intent resource visitor.
	 */
	class IntentResourceVisitor implements IResourceVisitor {
		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.core.resources.IResourceVisitor#visit(org.eclipse.core.resources.IResource)
		 */
		public boolean visit(IResource resource) {
			// return true to continue visiting children.
			return true;
		}
	}

	/**
	 * ID of the builder.
	 */
	public static final String BUILDER_ID = "org.eclipse.mylyn.docs.intent.client.ui.ide.intentBuilder";

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder#build(int, java.util.Map,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IProject[] build(int kind, @SuppressWarnings("rawtypes") Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	/**
	 * Performs a full build of the project.
	 * 
	 * @param monitor
	 *            the progress monitor
	 * @throws CoreException
	 *             if this build fails.
	 */
	protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
		try {
			getProject().accept(new IntentResourceVisitor());
		} catch (CoreException e) {
			// do nothing
			IntentLogger.getInstance().log(LogType.ERROR,
					"Intent - failed to build project " + getProject().getName(), e);
		}
	}

	/**
	 * Performs an incremental build of the project according to the resource delta.
	 * 
	 * @param delta
	 *            the resource delta
	 * @param monitor
	 *            the progress monitor
	 * @throws CoreException
	 *             if this build fails.
	 */
	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new IntentBuilderDeltaVisitor());
	}
}
