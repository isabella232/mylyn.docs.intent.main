package org.eclipse.mylyn.docs.intent.compare.debug;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.compare.utils.IntentEqualityHelper;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.markup.markup.Annotations;
import org.eclipse.mylyn.docs.intent.markup.markup.Text;

// TODO remove this class/package when ready
public final class DebugUtils {

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
		} else if (element instanceof Annotations || element instanceof IntentDocument) {
			res = null;
		}
		// if (element != null && element.eResource() != null) {
		// res += "[Repo]";
		// }
		return res;
	}
}
