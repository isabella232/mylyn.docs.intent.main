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
package org.eclipse.mylyn.docs.intent.modelingunit.gen.test.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitFactory;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.modelingunit.gen.ModelingUnitGenerator;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;
import org.junit.Before;

/**
 * An abstract test class providing API for manage an modeling unit generation.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractModelingUnitGeneratorTest implements ILogListener {

	/**
	 * The generator.
	 */
	private static ModelingUnitGenerator modelingUnitGenerator = new ModelingUnitGenerator();

	/**
	 * The serializer.
	 */
	private static IntentSerializer serializer = new IntentSerializer();

	/**
	 * The resource set.
	 */
	private static ResourceSet resourceSet = new ResourceSetImpl();

	/**
	 * Constructor.
	 */
	public AbstractModelingUnitGeneratorTest() {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("ecore", new XMIResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(ModelingUnitPackage.eNS_PREFIX, ModelingUnitPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(GenericUnitPackage.eNS_PREFIX, GenericUnitPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(IntentDocumentPackage.eNS_PREFIX, GenericUnitPackage.eINSTANCE);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		resourceSet.getResources().clear();
	}

	/**
	 * Generates the given test as a modeling unit.
	 * 
	 * @param testName
	 *            the test
	 */
	protected void generate(String testName) {
		// generate modeling unit
		Resource resource = resourceSet.getResource(URI.createURI(testName), true);

		StringBuffer actualBuffer = new StringBuffer();

		for (EObject root : resource.getContents().get(0).eContents()) {
			// the root object in the model is not considered, we generate all of its content
			ModelingUnit modelingUnit = ModelingUnitFactory.eINSTANCE.createModelingUnit();
			modelingUnit.getInstructions().add(modelingUnitGenerator.genInstanciation(root));
			// serializes result
			actualBuffer.append(serializer.serialize(modelingUnit) + "\n\n");
		}

		// expected result lookup
		String expectedPath = testName.replace("dataTests/", "expectedResults/") + ".intent";
		File expectedFile = new File(expectedPath);
		if (!expectedFile.exists()) {
			try {
				System.out.println(expectedPath + " not found, initialized.");
				BufferedWriter out = new BufferedWriter(new FileWriter(expectedFile));
				out.write(actualBuffer.toString());
				out.close();
			} catch (IOException e) {
				throw new AssertionFailedError(e.getMessage());
			}
		} else {

			// compare with expected
			try {
				String expected = FileToStringConverter.getFileAsString(expectedFile);
				Assert.assertEquals(expected, actualBuffer.toString());
			} catch (IOException e) {
				throw new AssertionFailedError(e.getMessage());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.ILogListener#logging(org.eclipse.core.runtime.IStatus, java.lang.String)
	 */
	public void logging(IStatus status, String plugin) {
		if (status.getSeverity() == IStatus.ERROR) {
			throw new AssertionFailedError(status.getMessage());
		}
	}
}
