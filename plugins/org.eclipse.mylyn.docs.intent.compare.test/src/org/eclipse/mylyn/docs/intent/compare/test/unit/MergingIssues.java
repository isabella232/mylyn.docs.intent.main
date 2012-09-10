package org.eclipse.mylyn.docs.intent.compare.test.unit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.mylyn.docs.intent.compare.test.utils.AbstractEMFCompareTest;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;

public class MergingIssues extends AbstractEMFCompareTest {

	private static List<String> passed = new ArrayList<String>();

	public void test1() throws IOException, ParseException {
		compareAndMerge("test1");
	}

	public void test2() throws IOException, ParseException {
		compareAndMerge("test2");
	}

	public void test3() throws IOException, ParseException {
		compareAndMerge("test3");
	}

	public void test4() throws IOException, ParseException {
		compareAndMerge("test4");
	}

	public void test5() throws IOException, ParseException {
		compareAndMerge("test5");
	}

	public void test6() throws IOException, ParseException {
		compareAndMerge("test6");
	}

	public void test7() throws IOException, ParseException {
		compareAndMerge("test7");
	}

	public void test8() throws IOException, ParseException {
		compareAndMerge("test8");
	}

	public void test9() throws IOException, ParseException {
		compareAndMerge("test9");
	}

	public void test10() throws IOException, ParseException {
		compareAndMerge("test10");
	}

	public void test11() throws IOException, ParseException {
		compareAndMerge("test11");
	}

	public void test12() throws IOException, ParseException {
		compareAndMerge("test12");
	}

	// checks that no tests have been missed
	public void testMissing() throws IOException, ParseException {
		for (String testName : new File("data").list()) {
			if (!passed.contains(testName)) {
				System.err.println("WARNING: no specific tests method for " + testName);
				compareAndMerge(testName);
			}
		}
	}

	private void compareAndMerge(String testName) throws IOException, ParseException {
		passed.add(testName);
		String repository = getFileAsString(new File("data/" + testName + "/IntentDocument.text"));
		String modified = getFileAsString(new File("data/" + testName + "/IntentDocument.text.modifications"));
		IntentStructuredElement left = parseIntentDocument(modified);
		IntentStructuredElement right = parseIntentDocument(repository);
		compareAndMergeDiffs(left, right);
		assertEquals(modified, new IntentSerializer().serialize(right));
	}
}
