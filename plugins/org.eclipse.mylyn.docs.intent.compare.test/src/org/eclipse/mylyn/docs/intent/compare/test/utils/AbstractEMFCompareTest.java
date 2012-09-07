package org.eclipse.mylyn.docs.intent.compare.test.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.eclipse.mylyn.docs.intent.compare.IntentASTMerger;
import org.eclipse.mylyn.docs.intent.compare.MergingException;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.parser.IntentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;

public class AbstractEMFCompareTest extends TestCase {

	protected void compareAndMergeDiffs(IntentDocument left, IntentDocument right) {
		try {
			new IntentASTMerger().mergeFromLocalToRepository(left, right);
		} catch (MergingException e) {
			fail(e.getMessage());
		}
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
