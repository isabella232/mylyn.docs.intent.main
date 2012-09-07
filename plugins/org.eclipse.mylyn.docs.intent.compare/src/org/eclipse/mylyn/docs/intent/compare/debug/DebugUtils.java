package org.eclipse.mylyn.docs.intent.compare.debug;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.compare.utils.IntentEqualityHelper;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.markup.markup.Text;

// TODO remove this class/package when ready
public final class DebugUtils {

	public static final boolean LOG_DEBUG_INFORMATIONS = true;

	public static final boolean SAVE_TESTS = false;

	public static final boolean USE_DEFAULT_COMPARE = false;

	private DebugUtils() {
	}

	public static void displayModel(EObject root) {
		displayModel(root, "");
	}

	public static void displayModel(EObject root, String tab) {
		String s = elementToReadableString(root);
		if (s != null) {
			System.out.println(tab + s);
		}
		for (EObject content : root.eContents()) {
			if (root instanceof IntentDocument) {
				displayModel(content, tab);
			} else {
				displayModel(content, tab + "\t");
			}
		}
	}

	public static void displayMatchModel(Comparison comparison) {
		for (Match root : comparison.getMatches()) {
			displayMatchModel(root, "");
		}
	}

	private static void displayMatchModel(Match root, String tab) {
		String matchString = matchToReadableString(root, tab);
		if (matchString != null) {
			System.out.println(matchString);
		}
		for (Match match : root.getSubmatches()) {
			if (root.getLeft() instanceof IntentDocument) {
				displayMatchModel(match, tab);
			} else {
				displayMatchModel(match, tab + "\t");
			}
		}
	}

	public static String matchToReadableString(Match match) {
		return matchToReadableString(match, "");
	}

	private static String matchToReadableString(Match match, String tab) {
		String res = null;
		String left = elementToReadableString(match.getLeft());
		String right = elementToReadableString(match.getRight());
		if (left != null && right != null) {
			res = tab + left + " == " + right;
		}
		return res;
	}

	public static String elementToReadableString(EObject element) {
		String res = null;
		if (element == null) {
			res = "?";
		} else {
			res = element.eClass().getName();
			String fragment = new IntentEqualityHelper().getURI(element).fragment();
			if (fragment != null) {
				res += "[" + fragment + "]";
			}
		}
		if (element instanceof Text) {
			res += "\"" + ((Text)element).getData() + "\"";
		} else if (element instanceof IntentStructuredElement) {
			String title = ((IntentStructuredElement)element).getFormattedTitle();
			if (title != null) {
				res += "\"" + title + "\"";
			}
		} else if (element instanceof IntentDocument) {
			res = null;
		}
		// if (element != null && element.eResource() != null) {
		// res += "[Repo]";
		// }
		return res;
	}

	public static void saveToFile(String file, String content) {
		File destination = new File(file);
		try {
			Files.write(content, destination, Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.err.println(destination.getAbsolutePath());
			e.printStackTrace();
		}
	}
}
