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
package org.eclipse.mylyn.docs.intent.compare.test.suite;

import org.eclipse.mylyn.docs.intent.compare.test.unit.MergingIssues;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Launch all the test files related to emf compare tests.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({MergingIssues.class,

})
public class TestCompareSuite {

}
