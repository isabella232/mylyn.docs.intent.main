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
package org.eclipse.mylyn.docs.intent.client.ui.ide.navigator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry;

/**
 * A {@link ViewerSorter} used to display content provided by the {@link RepositoryContentProvider} with the
 * order defined in model instead of alphabetical order.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class RepositoryContentSorter extends ViewerSorter {

	private static final String DOT = ".";

	/**
	 * Default constructor.
	 */
	public RepositoryContentSorter() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof IntentIndexEntry && e2 instanceof IntentIndexEntry) {
			IntentIndexEntry entry1 = (IntentIndexEntry)e1;
			IntentIndexEntry entry2 = (IntentIndexEntry)e2;
			if (entry1.eContainer() != entry2.eContainer()) {
				String entry1Level = entry1.getName().substring(0, entry1.getName().indexOf(" "));
				entry1Level = entry1Level.substring(entry1.getName().lastIndexOf(DOT) + 1);
				String entry2Level = entry2.getName().substring(0, entry2.getName().indexOf(" "));
				if (entry1.getName().contains(DOT)) {
					entry2Level = entry2Level.substring(entry1.getName().lastIndexOf(DOT));
					int compareResult;
					if (entry1Level.length() != entry2Level.length()) {
						compareResult = entry1Level.length() - entry2Level.length();
					} else {
						compareResult = entry1Level.compareTo(entry2Level);
					}
					return compareResult;
				}
			}
		}
		return super.compare(viewer, e1, e2);
	}
}
