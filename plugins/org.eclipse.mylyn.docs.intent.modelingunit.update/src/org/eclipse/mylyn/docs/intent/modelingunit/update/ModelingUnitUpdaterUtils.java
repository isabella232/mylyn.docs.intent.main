/*******************************************************************************
 * Copyright (c) 2012 Obeo.

 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.modelingunit.update;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.ModelElementChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;

/**
 * Modeling unit updater utilities methods.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class ModelingUnitUpdaterUtils {

	/**
	 * Constructor.
	 */
	private ModelingUnitUpdaterUtils() {
		// prevents instantiation
	}

	/**
	 * Returns the root EObject to generate.
	 * 
	 * @param status
	 *            the sync status
	 * @return the root EObject to generate
	 */
	public static EObject getRootEObjectToGenerate(ModelElementChangeStatus status) {
		ResourceSetImpl rs = new ResourceSetImpl();
		return rs.getEObject(URI.createURI(status.getWorkingCopyElementURIFragment()), true);
	}

	/**
	 * Checks whether a status can be fixed or not.
	 * 
	 * @param status
	 *            the status to test
	 * @return true if the status can be fixed
	 */
	public static boolean canFix(SynchronizerCompilationStatus status) {
		boolean res = false;
		switch (status.eClass().getClassifierID()) {
			case CompilerPackage.MODEL_ELEMENT_CHANGE_STATUS:
				res = ((ModelElementChangeStatus)status).getChangeState().equals(
						SynchronizerChangeState.WORKING_COPY_TARGET);
				break;

			default:
				break;
		}
		return res;
	}

	/**
	 * Checks whether a status can be fixed or not.
	 * 
	 * @param status
	 *            the status to test
	 * @return true if the status can be fixed
	 */
	public static String getFixMessage(SynchronizerCompilationStatus status) {
		// TODO accurate messages
		return "Update modeling unit";
	}
}
