package org.eclipse.mylyn.docs.intent.compare.test.unit;

import java.io.File;
import java.io.IOException;

import org.eclipse.mylyn.docs.intent.compare.test.utils.AbstractEMFCompareTest;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;

public class IndexOutOfBound extends AbstractEMFCompareTest {

	public void test1() throws IOException, ParseException {
		String modified = getFileAsString(new File("data/test1/IntentDocument.text.modifications"));
		IntentDocument left = parseIntentDocument(modified);
		IntentDocument right = getIntentDocument("data/test1/IntentDocument.repomodel");
		compareAndMergeDiffs(left, right);
		assertEquals(modified, new IntentSerializer().serialize(right));
	}

	public void test2() throws IOException, ParseException {
		String modified = getFileAsString(new File("data/test2/IntentDocument.text.modifications"));
		IntentDocument left = parseIntentDocument(modified);
		IntentDocument right = getIntentDocument("data/test2/IntentDocument.repomodel");
		compareAndMergeDiffs(left, right);
		assertEquals(modified, new IntentSerializer().serialize(right));
	}
}
