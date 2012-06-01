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
package org.eclipse.mylyn.docs.intent.client.compiler.test.suite;

import org.eclipse.mylyn.docs.intent.client.compiler.test.unit.ErrorsTest;
import org.eclipse.mylyn.docs.intent.client.compiler.test.unit.GeneratedResourcesTest;
import org.eclipse.mylyn.docs.intent.client.compiler.test.unit.WarningsTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This suite will launch all the tests relative to the Compiler behavior.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({GeneratedResourcesTest.class, ErrorsTest.class, WarningsTest.class,
})
public class CompilerTestSuite {

}
