package org.eclipse.mylyn.docs.intent.compare.test.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.mylyn.docs.intent.compare.IntentASTMerger;
import org.eclipse.mylyn.docs.intent.compare.MergingException;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnitPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.genericunit.GenericUnitPackage;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.parser.IntentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;

public class AbstractEMFCompareTest extends TestCase {

	private static ResourceSet resourceSet = new ResourceSetImpl();

	public AbstractEMFCompareTest() {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("*", new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(GenericUnitPackage.eNS_PREFIX, GenericUnitPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(IntentDocumentPackage.eNS_PREFIX,
				IntentDocumentPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(CompilerPackage.eNS_PREFIX, CompilerPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(DescriptionUnitPackage.eNS_PREFIX,
				DescriptionUnitPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(IntentIndexerPackage.eNS_PREFIX, IntentIndexerPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ModelingUnitPackage.eNS_PREFIX, ModelingUnitPackage.eINSTANCE);
	}

	protected void compareAndMergeDiffs(IntentDocument left, IntentDocument right) {
		try {
			new IntentASTMerger().mergeFromLocalToRepository(left, right);
		} catch (MergingException e) {
			fail(e.getMessage());
		}
	}

	protected IntentDocument getIntentDocument(String modelName) {
		IntentDocument intentDocument = (IntentDocument)resourceSet
				.getResource(URI.createFileURI(modelName), true).getContents().get(0);
		EcoreUtil.resolveAll(intentDocument);
		return intentDocument;
	}

	protected IntentDocument parseIntentDocument(String content) throws ParseException {
		return (IntentDocument)new IntentParser().parse(content);
	}

	/**
	 * Returns the content of the given file as a String.
	 * 
	 * @param file
	 *            file containing the information to extract.
	 * @return the content of the given file as a String
	 * @throws IOException
	 *             if the file doesn't exists
	 */
	protected static String getFileAsString(File file) throws IOException {
		String result = "";
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedReader dis = null;
		StringBuffer sb = new StringBuffer();

		fis = new FileInputStream(file);
		bis = new BufferedInputStream(fis);
		dis = new BufferedReader(new InputStreamReader(bis));

		while (dis.ready()) {
			sb.append(dis.readLine() + "\n");
		}

		fis.close();
		bis.close();
		dis.close();

		result = sb.toString();
		return result;

	}
}
