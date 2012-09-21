/*******************************************************************************
 * Copyright (c) 2010, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.collab.cdo.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.view.CDOStaleObject;
import org.eclipse.emf.cdo.view.CDOStaleReferencePolicy;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * The {@link CDOStaleReferencePolicy} used by Intent to handle invalid cdo objects.
 * <p>
 * A stale object is represented as an EMF proxy : we just return true to eIsProxy.
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentStaleReferencePolicy implements CDOStaleReferencePolicy {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.cdo.view.CDOStaleReferencePolicy#processStaleReference(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, int, org.eclipse.emf.cdo.common.id.CDOID)
	 */
	public Object processStaleReference(EObject source, EStructuralFeature feature, int index, CDOID target) {
		if (feature == null) {
			return null;
		}
		final EClassifier type = feature.getEType();

		Class<?> instanceClass = type.getInstanceClass();
		Class<?>[] interfaces = null;

		// Be sure to have only interface
		if (instanceClass.isInterface()) {
			interfaces = new Class<?>[] {instanceClass, InternalEObject.class, CDOStaleObject.class,
			};
		} else {
			interfaces = new Class<?>[] {InternalEObject.class, CDOStaleObject.class,
			};
		}

		Object proxy = null;
		try {
			proxy = Proxy.newProxyInstance(instanceClass.getClassLoader(), interfaces,
					new IntentCDOInvocationHandler());
		} catch (IllegalArgumentException exception) {
			// If we cannot instanciate the Proxy, we log a warning
			// and return null
			interfaces = new Class<?>[] {instanceClass, InternalEObject.class,
			};
			proxy = Proxy.newProxyInstance(instanceClass.getClassLoader(), interfaces,
					new IntentCDOInvocationHandler());
		}

		return proxy;
	}

	/**
	 * An invocation handler in charge of "making believe" that the stale object is a proxy.
	 * 
	 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
	 */
	private static class IntentCDOInvocationHandler implements InvocationHandler {

		/**
		 * {@inheritDoc}
		 * 
		 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method,
		 *      java.lang.Object[])
		 */
		public Object invoke(Object proxy, Method method, Object[] args) {
			final String methodName = method.getName();
			Object result = null;

			if ("eIsProxy".equals(methodName)) { //$NON-NLS-1$
				result = true;
			}
			return result;
		}
	}
}
