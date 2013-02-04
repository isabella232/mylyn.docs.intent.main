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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.java;

import java.io.IOException;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.WorkspaceUtils;
import org.eclipse.mylyn.docs.intent.compare.utils.EMFCompareUtils;

/**
 * Ensures that the {@link org.eclipse.mylyn.docs.intent.bridge.java.resource.factory.JavaResourceFactory}
 * allows to represent java files as models.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class JavaResourceFactoryTest extends TestCase {

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		try {
			// Deactivate the auto build to avoid problem of test before build is
			// finish.
			ResourcesPlugin.getWorkspace().getDescription().setAutoBuilding(false);
			// Importing java project through an archive file
			WorkspaceUtils.unzipAllProjects("org.eclipse.mylyn.docs.intent.client.ui.test",
					"data/unit/java/java.example01.zip", new NullProgressMonitor());
			// Launch a manual build and wait the end of the workspace build
			ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD,
					new NullProgressMonitor());
		} catch (IOException e) {
			AssertionFailedError assertionFailedError = new AssertionFailedError(
					"Could not import java project in test workspace");
			assertionFailedError.setStackTrace(e.getStackTrace());
			throw assertionFailedError;
		} catch (CoreException e) {
			AssertionFailedError assertionFailedError = new AssertionFailedError(
					"Could not import java project in test workspace");
			assertionFailedError.setStackTrace(e.getStackTrace());
			throw assertionFailedError;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		WorkspaceUtils.cleanWorkspace();
		super.tearDown();
	}

	/**
	 * Ensures that the {@link org.eclipse.mylyn.docs.intent.bridge.java.resource.factory.JavaResourceFactory}
	 * allows to represent a simple Java file as a
	 * {@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier}.
	 */
	public void testSimpleJavaClass() {
		String javaFilePath = "org.eclipse.mylyn.docs.intent.java.example/src/org/eclipse/myly/docs/intent/java/example/ExampleJavaClass.java";
		compareJavaModelWithExpected(javaFilePath, "data/expected/java/ExampleJavaClass.xmi");
	}

	/**
	 * Ensures that the {@link org.eclipse.mylyn.docs.intent.bridge.java.resource.factory.JavaResourceFactory}
	 * allows to represent an abstract Java file as a
	 * {@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier}.
	 */
	public void testAbstractJavaClass() {
		String javaFilePath = "org.eclipse.mylyn.docs.intent.java.example/src/org/eclipse/myly/docs/intent/java/example/AbstractExampleJavaClass.java";
		compareJavaModelWithExpected(javaFilePath, "data/expected/java/AbstractExampleJavaClass.xmi");
	}

	/**
	 * Ensures that the {@link org.eclipse.mylyn.docs.intent.bridge.java.resource.factory.JavaResourceFactory}
	 * allows to represent an interface as a {@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier}.
	 */
	public void testJavaInterface() {
		String javaFilePath = "org.eclipse.mylyn.docs.intent.java.example/src/org/eclipse/myly/docs/intent/java/example/IExampleJavaClass.java";
		compareJavaModelWithExpected(javaFilePath, "data/expected/java/IExampleJavaClass.xmi");
	}

	/**
	 * Ensures that the {@link org.eclipse.mylyn.docs.intent.bridge.java.resource.factory.JavaResourceFactory}
	 * allows to represent an EEnum as a {@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier}.
	 */
	public void testJavaEEnum() {
		String javaFilePath = "org.eclipse.mylyn.docs.intent.java.example/src/org/eclipse/myly/docs/intent/java/example/ExampleEnum.java";
		compareJavaModelWithExpected(javaFilePath, "data/expected/java/ExampleEnum.xmi");
	}

	/**
	 * Represents the java file located at the given path as a model, and compares it with the model located
	 * at the given expected model location.
	 * 
	 * @param javaFilePath
	 *            the path of the java file to represent as a model
	 * @param expectedModelLocation
	 *            the location of the expected model
	 */
	protected void compareJavaModelWithExpected(String javaFilePath, String expectedModelLocation) {
		EObject javaClassAsEobject = new ResourceSetImpl().getResource(URI.createURI(javaFilePath), true)
				.getContents().iterator().next();
		ResourceSetImpl expectedRS = new ResourceSetImpl();
		expectedRS.getPackageRegistry().put(JavaPackage.eNS_URI, JavaPackage.eINSTANCE);
		EObject expected = expectedRS
				.getResource(
						URI.createURI("platform:/plugin/org.eclipse.mylyn.docs.intent.client.ui.test/"
								+ expectedModelLocation), true).getContents().iterator().next();
		EList<Diff> differences = EMFCompareUtils.compare(expected, javaClassAsEobject).getDifferences();
		if (!differences.isEmpty()) {
			assertEquals("The java class " + javaFilePath
					+ " was not represented as expected\nFirst difference : "
					+ differences.iterator().next().toString(), 0, differences.size());
		}
	}
}
