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
package org.eclipse.mylyn.docs.intent.collab.cdo.adapters;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.CDOCommonSession.Options.PassiveUpdateMode;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.revision.CDORevisionKey;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CDOURIUtil;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.util.ReadOnlyException;
import org.eclipse.emf.cdo.view.CDOAdapterPolicy;
import org.eclipse.emf.cdo.view.CDOInvalidationPolicy;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.spi.cdo.InternalCDOObject;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.SaveException;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.Notificator;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.net4j.util.event.IListener;

/**
 * Adapter that allows the RepositoryObjectHandler to work with a CDO repository.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class CDOAdapter implements RepositoryAdapter {

	private static final CDOInvalidationPolicy INTENT_CDO_INVALIDATION_POLICY = new CDOInvalidationPolicy() {
		public void handleInvalidation(CDOObject object, CDORevisionKey key) {
			if (object instanceof InternalCDOObject) {
				((InternalCDOObject)object).cdoInternalSetRevision(null);
			}
		}

		public void handleInvalidObject(CDOObject object) {
			// We do no throw exception to catch these error silently
		}
	};

	/**
	 * The session used in this adapter.
	 */
	private CDOSession session;

	/**
	 * The current opened context.
	 */
	private CDOView currentContext;

	/**
	 * Indicates if the current opened context is ReadOnly (false if Read/Write).
	 */
	private boolean isReadOnlyContext;

	/**
	 * Map that associate Notificators to the typeListner created (used to close detach theses listeners).
	 */
	private Map<Notificator, Set<IListener>> notificatorToListener;

	/**
	 * Booleans indicating whether this adapter must allow changeSubscriptionPolicy on its transactions.
	 */
	private boolean allowChangeSubscriptionPolicy;

	/**
	 * The {@link Repository} from witch this adapter has been created.
	 */
	private Repository repository;

	/**
	 * CDOAdapter Constructor.
	 * 
	 * @param repository
	 *            the {@link Repository} from witch this adapter has been created
	 * @param object
	 *            the session to use.
	 */
	public CDOAdapter(Repository repository, Object object) {
		this.notificatorToListener = new HashMap<Notificator, Set<IListener>>();
		this.allowChangeSubscriptionPolicy = false;
		this.repository = repository;
		setSession(object);
	}

	/**
	 * Sets the session.
	 * 
	 * @param session
	 *            the session
	 */
	public void setSession(Object session) {
		if (session instanceof CDOSession) {
			this.session = (CDOSession)session;
		} else {
			throw new IllegalArgumentException("The session associated to CDOAdapter must be a CDOSession");
		}
	}

	/**
	 * Returns the session.
	 * 
	 * @return the session
	 */
	public Object getSession() {
		return session;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#openSaveContext()
	 */
	public Object openSaveContext() {
		if (this.currentContext == null) {
			this.currentContext = session.openTransaction(new ResourceSetImpl());
			currentContext.options().setInvalidationPolicy(INTENT_CDO_INVALIDATION_POLICY);
			setChangeSubscriptionPolicy();
			this.isReadOnlyContext = false;
		}
		return getContext();
	}

	/**
	 * Initialize a changeSubscriptionPolicy on the currentTransaction.
	 */
	private void setChangeSubscriptionPolicy() {
		if (this.allowChangeSubscriptionPolicy) {
			if (this.currentContext != null) {
				// The CDOAdapterPolicy.CDO means that we will only be notified by CDOAdapters (see CDO
				// documentation).
				this.currentContext.options().addChangeSubscriptionPolicy(CDOAdapterPolicy.ALL);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#openReadOnlyContext()
	 */
	public Object openReadOnlyContext() {
		this.isReadOnlyContext = true;
		if (this.currentContext == null) {
			this.currentContext = session.openView();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#save()
	 */
	public void save() throws SaveException, ReadOnlyException {
		if (isReadOnlyContext) {
			throw new ReadOnlyException(
					"Cannot save with a read-only context. The context should have been started with the 'openSaveContext' method.");
		}
		try {
			((CDOTransaction)this.currentContext).commit();
		} catch (CommitException ce) {
			SaveException e = new SaveException(ce.getMessage());
			e.setStackTrace(ce.getStackTrace());
			throw e;
		} catch (ReadOnlyException roe) {
			SaveException e = new SaveException(roe.getMessage());
			e.setStackTrace(roe.getStackTrace());
			throw e;
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#undo()
	 */
	public void undo() throws ReadOnlyException {
		if (this.isReadOnlyContext) {
			throw new ReadOnlyException(
					"Cannot undo action with a read-only context. The context should have been started with the 'openSaveContext' method.");
		}
		if (this.currentContext != null) {
			((CDOTransaction)this.currentContext).rollback();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#closeContext()
	 */
	public void closeContext() {
		if (this.currentContext != null) {
			this.currentContext.close();
			this.isReadOnlyContext = false;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#attachSessionListenerForTypes(org.eclipse.mylyn.docs.intent.collab.handlers.notification.Notificator,
	 *      java.util.Set)
	 */
	public void attachSessionListenerForTypes(Notificator typeNotificator, Set<EStructuralFeature> types) {
		this.session.options().setPassiveUpdateMode(PassiveUpdateMode.ADDITIONS);
		CDOTypeListener typeListener = new CDOTypeListener(typeNotificator, types);
		if (this.notificatorToListener.get(typeNotificator) == null) {
			this.notificatorToListener.put(typeNotificator, new LinkedHashSet<IListener>());
		}
		this.notificatorToListener.get(typeNotificator).add(typeListener);
		this.session.addListener(typeListener);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#detachSessionListenerForTypes(org.eclipse.mylyn.docs.intent.collab.handlers.notification.Notificator)
	 */
	public void detachSessionListenerForTypes(Notificator typeNotificator) {
		for (IListener listenerToRemove : this.notificatorToListener.get(typeNotificator)) {
			this.session.removeListener(listenerToRemove);
		}

		this.notificatorToListener.remove(typeNotificator);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#getContext()
	 */
	public Object getContext() {
		return this.currentContext;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#allowChangeSubscriptionPolicy()
	 */
	public void allowChangeSubscriptionPolicy() {
		this.allowChangeSubscriptionPolicy = true;
		setChangeSubscriptionPolicy();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#getResource(java.lang.String)
	 */
	public Resource getResource(String path) {
		return this.currentContext.getResource(path);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#getOrCreateResource(java.lang.String)
	 */
	public Resource getOrCreateResource(String path) {
		if (this.isReadOnlyContext) {
			throw new ReadOnlyException(
					"Cannot create any resource with a read-only context. The context should have been started with the 'openSaveContext' method.");
		}
		if (this.currentContext != null) {
			return ((CDOTransaction)this.currentContext).getOrCreateResource(path);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#getElementWithID(java.lang.Object)
	 */
	public EObject getElementWithID(Object id) {
		if (id instanceof CDOID) {
			return this.currentContext.getObject((CDOID)id);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#getIDFromElement(org.eclipse.emf.ecore.EObject)
	 */
	public Object getIDFromElement(EObject element) {
		if (element instanceof CDOObject) {
			return ((CDOObject)element).cdoID();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#attachRepositoryStructurer(org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryStructurer)
	 */
	public void attachRepositoryStructurer(RepositoryStructurer structurer) {
		// TODO if the use cases show this is useful, we should integrate a structurer to CDO Adapters.
		throw new UnsupportedOperationException("Can't define structurer on aCDO Repository.");

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#setSendSessionWarningBeforeSaving(boolean)
	 */
	public void setSendSessionWarningBeforeSaving(boolean notifySessionBeforeSaving) {
		// Such a warning mechanism is already implemented between CDOResources and CDOSessions
		// That's why we don't have anything to do here.

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#setSendSessionWarningBeforeSaving(java.util.Collection)
	 */
	public void setSendSessionWarningBeforeSaving(Collection<String> resourcesToIgnorePaths) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#reload(org.eclipse.emf.ecore.EObject)
	 */
	public EObject reload(EObject elementToReload) {
		if (elementToReload instanceof CDOObject) {
			((CDOObject)elementToReload).cdoReload();
			return elementToReload;
		}
		throw new IllegalArgumentException("Cannot reload an element which is not a CDOObject");

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#getResourcePath(org.eclipse.emf.common.util.URI)
	 */
	public String getResourcePath(URI resourceURI) {
		return CDOURIUtil.extractResourcePath(resourceURI);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#execute(org.eclipse.mylyn.docs.intent.collab.handlers.adapters.IntentCommand)
	 */
	public void execute(IntentCommand command) {
		command.execute();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#getRepository()
	 */
	public Repository getRepository() {
		return this.repository;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter#getResourceSet()
	 */
	public ResourceSet getResourceSet() {
		if (this.currentContext != null) {
			return this.currentContext.getResourceSet();
		}
		return null;
	}
}
