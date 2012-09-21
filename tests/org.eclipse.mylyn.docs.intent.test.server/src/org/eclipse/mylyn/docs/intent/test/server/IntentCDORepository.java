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
package org.eclipse.mylyn.docs.intent.test.server;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.cdo.server.CDOServerUtil;
import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.IStore;
import org.eclipse.emf.cdo.server.db.CDODBUtil;
import org.eclipse.emf.cdo.server.db.mapping.IMappingStrategy;
import org.eclipse.emf.cdo.server.internal.db.mapping.horizontal.AbstractHorizontalMappingStrategy;
import org.eclipse.emf.cdo.server.net4j.CDONet4jServerUtil;
import org.eclipse.net4j.acceptor.IAcceptor;
import org.eclipse.net4j.db.DBUtil;
import org.eclipse.net4j.db.IDBAdapter;
import org.eclipse.net4j.db.IDBConnectionProvider;
import org.eclipse.net4j.db.h2.H2Adapter;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;
import org.h2.jdbcx.JdbcDataSource;

/**
 * A CDO repository used to test Intent.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class IntentCDORepository {

	private static IRepository repository;

	private static IAcceptor acceptor;

	private static final String SERVER_LOCATION = "localhost";

	private static final String SERVER_PORT_NUMBER = "2036";

	/**
	 * Private constructor.
	 */
	private IntentCDORepository() {

	}

	/**
	 * Starts the Intent test Server (if not already launched).
	 * 
	 * @param cleanStore
	 *            true if the store must be clean (i.e. database should be dropped)
	 * @param repositoryName
	 *            the name of the repository to launch
	 */
	public static void start(boolean cleanStore, String repositoryName) {
		if (acceptor == null) {
			// Step 1 : setting up the db
			// Step 1.1 : defining the datasource
			JdbcDataSource dataSource = new JdbcDataSource();
			dataSource.setURL("jdbc:h2:_database/" + repositoryName);

			// Step 1.2 : defining the mapping strategy
			IMappingStrategy mappingStrategy = CDODBUtil.createHorizontalMappingStrategy(true);
			Map<String, String> mappingProperties = new LinkedHashMap<String, String>();
			mappingProperties.put(AbstractHorizontalMappingStrategy.PROP_OBJECT_TYPE_CACHE_SIZE, "1000");
			mappingProperties.put(AbstractHorizontalMappingStrategy.PROP_QUALIFIED_NAMES,
					Boolean.TRUE.toString());
			mappingStrategy.setProperties(mappingProperties);

			// Step 1.3 : use a H2 database
			IDBAdapter dbAdapter = new H2Adapter();
			IDBConnectionProvider dbConnectionProvider = DBUtil.createConnectionProvider(dataSource);

			// Clean the store if needed
			if (cleanStore) {
				DBUtil.dropAllTables(dbConnectionProvider.getConnection(), repositoryName);
			}

			// Step 1.4 : creating the IStore from the specified DB
			IStore store = CDODBUtil.createStore(mappingStrategy, dbAdapter, dbConnectionProvider);

			// Step 2 : creating the repository
			Map<String, String> props = new HashMap<String, String>();
			props.put(IRepository.Props.OVERRIDE_UUID, repositoryName);
			props.put(IRepository.Props.SUPPORTING_AUDITS, "false");
			props.put(IRepository.Props.SUPPORTING_BRANCHES, "false");
			props.put(IRepository.Props.SUPPORTING_ECORE, "true");

			repository = CDOServerUtil.createRepository(repositoryName, store, props);

			CDOServerUtil.addRepository(IPluginContainer.INSTANCE, repository);
			CDONet4jServerUtil.prepareContainer(IPluginContainer.INSTANCE);

			// Step 3 : creating an acceptor on the server side
			acceptor = (IAcceptor)IPluginContainer.INSTANCE.getElement("org.eclipse.net4j.acceptors", "tcp",
					SERVER_LOCATION + ":" + SERVER_PORT_NUMBER);
		}
	}

	/**
	 * Stops the currently running Intent test server.
	 */
	public static void stop() {
		if (acceptor != null) {
			LifecycleUtil.deactivate(acceptor);
			LifecycleUtil.deactivate(repository);
			acceptor = null;
			repository = null;
		}
	}
}
