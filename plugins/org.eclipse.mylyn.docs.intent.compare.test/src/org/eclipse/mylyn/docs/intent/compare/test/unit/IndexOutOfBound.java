package org.eclipse.mylyn.docs.intent.compare.test.unit;

import java.io.File;
import java.io.IOException;

import org.eclipse.mylyn.docs.intent.compare.test.utils.AbstractEMFCompareTest;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;

public class IndexOutOfBound extends AbstractEMFCompareTest {

	/**
	 * Throws {@link IndexOutOfBoundsException}
	 */
	public void testMerge() throws IOException, ParseException {
		IntentDocument left = parseIntentDocument(getFileAsString(new File(
				"data/indexoutofbound/IntentDocument.text.modifications")));
		IntentDocument right = getIntentDocument("data/indexoutofbound/IntentDocument.repomodel");
		compareAndMergeDiffs(left, right);
	}

}
