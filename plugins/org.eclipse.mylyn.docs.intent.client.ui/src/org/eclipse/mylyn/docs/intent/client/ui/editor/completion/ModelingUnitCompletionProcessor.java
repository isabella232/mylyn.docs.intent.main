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
package org.eclipse.mylyn.docs.intent.client.ui.editor.completion;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentPairMatcher;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.IntentPartitionScanner;
import org.eclipse.mylyn.docs.intent.collab.common.query.TraceabilityInformationsQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndexEntry;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;

/**
 * Computes the completion proposal for ModelingUnits.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ModelingUnitCompletionProcessor extends AbstractIntentCompletionProcessor {

	private RepositoryAdapter repositoryAdapter;

	private TraceabilityInformationsQuery traceabilityInfoQuery;

	private static final String IDENTIFIER_REGEXP = "([a-zA-z0-9_-]+)";

	private static final String QUALIFIED_NAME_DELIMITER = "\\.";

	public ModelingUnitCompletionProcessor(RepositoryAdapter repositoryAdapter) {
		this.repositoryAdapter = repositoryAdapter;
		this.traceabilityInfoQuery = new TraceabilityInformationsQuery(repositoryAdapter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.completion.AbstractIntentCompletionProcessor#computeCompletionProposals()
	 */
	@Override
	protected ICompletionProposal[] computeCompletionProposals() {
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();

		try {
			// Step 1: get the focused instruction
			String text = document.get(0, offset);

			// get the currently considered modeling unit
			int startOffset = getLastIndexOf(text, Pattern.compile("@M"));
			if (startOffset > -1) {
				text = text.substring(startOffset);
			}

			// remove instructions inside closed brackets
			text = removeInstructionsInsideClosedBrackets(text);
			// remove instructions that are finished (ending with ";")
			text = removeEndedInstructions(text);

			// Step 2: get the last relevant keyword
			String lastRelevantKeyWord = getLastRelevantKeyWord(text);

			// Step 3: according to this keyword, compute the proposals
			if (lastRelevantKeyWord == null) {
				proposals.addAll(getProposalsForEmptyModelingUnit(true, ""));
			} else {
				if ("".equals(lastRelevantKeyWord)) {
					proposals.addAll(getProposalsForEmptyModelingUnit(false, text));
				} else if ("new".equals(lastRelevantKeyWord)) {
					proposals.addAll(getProposalsForNewInstruction(text));
				} else if ("{".equals(lastRelevantKeyWord)) {
					proposals.addAll(getProposalsForStructuralFeatureAffectation(text));
				} else if ("=".equals(lastRelevantKeyWord) || "+=".equals(lastRelevantKeyWord)) {
					proposals.addAll(getProposalsForStructuralFeatureValue(text));
				}
			}
		} catch (BadLocationException e) {
			// Nothing to do, no completion will be proposed
		} catch (ReadOnlyException e) {
			// Nothing to do, no completion will be proposed
		} catch (IllegalArgumentException e) {
			// Nothing to do, no completion will be proposed
		}
		if (proposals.isEmpty()) {
			proposals.add(createTemplateProposal("", "No completion available", "",
					"icon/outline/default.gif"));
		}
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	private Collection<? extends ICompletionProposal> getProposalsForEmptyModelingUnit(
			boolean isAtMUBeggining, String text) throws ReadOnlyException {
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();
		String prefix = "\n\t";
		if (isAtMUBeggining) {
			prefix = "@M" + prefix;
		}
		// First proposal : new Resource Declaration
		if (text.trim().length() == 0 || "Resource".startsWith(text.trim())) {
			proposals.add(createTemplateProposal("Resource", "Declaration of a new Resource", prefix
					+ "Resource myResource {\n\t\tURI = \"${}\";\n\t}",
					"icon/outline/modelingunit_resource.gif"));
		}
		// Second proposal : new entity
		if (text.trim().length() == 0 || "new".startsWith(text.trim())) {
			proposals.add(createTemplateProposal("new", "Declaration of a new entity", prefix
					+ "new ${Type} {}", "icon/outline/modelingunit_new_element.png"));
		}

		// Third proposal : contribute to an existing entity
		TraceabilityIndex traceabilityIndex = getTraceabilityIndex();
		String contributionBeginning = text.trim();
		for (TraceabilityIndexEntry entry : traceabilityIndex.getEntries()) {
			for (InstanciationInstruction instruction : Iterables.filter(entry
					.getContainedElementToInstructions().values(), InstanciationInstruction.class)) {
				if (instruction.getName() != null
						&& (contributionBeginning.length() == 0 || instruction.getName().startsWith(
								contributionBeginning))) {
					String description = "Contribute to the " + instruction.getName() + " ";
					if (instruction.getMetaType() != null
							&& instruction.getMetaType().getIntentHref() != null) {
						description += instruction.getMetaType().getIntentHref();
					} else {
						description += "entity";
					}
					proposals.add(createTemplateProposal(instruction.getName() + " (contribution)",
							description, prefix + instruction.getName() + " {\n\t\t${}\n\t}",
							"icon/outline/modelingunit_contribution.png"));
				}
			}
		}
		return proposals;
	}

	private Collection<? extends ICompletionProposal> getProposalsForNewInstruction(String text)
			throws ReadOnlyException {
		String classNameBeginning = text.substring(text.lastIndexOf("new")).replace("new", "").trim();
		return getProposalsForEClassifier(classNameBeginning);
	}

	private Collection<ICompletionProposal> getProposalsForStructuralFeatureAffectation(String text)
			throws ReadOnlyException {
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();
		// Step 1: extract contribution name
		String contributionName = "";
		String featureNameBeginning = "";
		boolean isContribution = true;
		boolean isResourceDeclaration = false;
		if (text.lastIndexOf("{") != -1) {
			contributionName = text.substring(0, text.lastIndexOf("{")).trim();
			featureNameBeginning = text.substring(text.lastIndexOf("{")).replace("{", "").trim();

			if (contributionName.contains("new")) {
				isContribution = false;
				contributionName = contributionName.substring(contributionName.lastIndexOf("new")).trim();
				contributionName = contributionName.substring(contributionName.indexOf(" ")).trim();
				if (contributionName.contains(" ")) {
					contributionName = contributionName.substring(0, contributionName.indexOf(" ")).trim();
				}
			}
			if (contributionName.contains("Resource")) {
				isResourceDeclaration = true;
				isContribution = false;
			}
		}

		// If the structural feature affectation is inside a contribution
		if (isContribution) {
			getProposalsForContribution(proposals, contributionName, featureNameBeginning);
		} else {
			if (isResourceDeclaration) {
				getProposalsForResourceDeclaration(proposals);
			} else {
				// If the structural feature affectation is inside an Instranciation instruction
				EClassifier classifierToConsider = getEClassifier(contributionName);
				if (classifierToConsider != null && classifierToConsider instanceof EClass) {
					for (EStructuralFeature feature : ((EClass)classifierToConsider)
							.getEAllStructuralFeatures()) {
						if (featureNameBeginning.length() == 0
								| feature.getName().startsWith(featureNameBeginning)) {
							proposals.add(createStructuralFeatureAffectationTemplateProposal(
									contributionName, feature));
						}
					}
				}
			}
		}
		return proposals;
	}

	private void getProposalsForResourceDeclaration(Collection<ICompletionProposal> proposals) {
		proposals.add(createTemplateProposal("Resource URI", "URI indicating the Resource location",
				"URI = \"${}\";", "icon/outline/modelingunit_resource.gif"));
		proposals.add(createTemplateProposal("Resource Content", "Add content to the Resource",
				"content += ${};", "icon/outline/modelingunit_resource.gif"));
	}

	private void getProposalsForContribution(Collection<ICompletionProposal> proposals,
			String contributionName, String featureNameBeginning) throws ReadOnlyException {
		TraceabilityIndex traceabilityIndex = getTraceabilityIndex();
		for (TraceabilityIndexEntry entry : traceabilityIndex.getEntries()) {
			for (InstanciationInstruction instruction : Iterables.filter(entry
					.getContainedElementToInstructions().values(), InstanciationInstruction.class)) {
				if (contributionName.equals(instruction.getName())) {
					if (instruction.getMetaType() != null
							&& instruction.getMetaType().getResolvedType() != null) {
						for (EStructuralFeature feature : instruction.getMetaType().getResolvedType()
								.getEAllStructuralFeatures()) {
							if (featureNameBeginning.length() == 0
									|| feature.getName().startsWith(featureNameBeginning)) {
								proposals.add(createStructuralFeatureAffectationTemplateProposal(
										contributionName, feature));
							}
						}
					}
				}
			}
		}
	}

	private Collection<? extends ICompletionProposal> getProposalsForStructuralFeatureValue(String text)
			throws ReadOnlyException {
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();

		// Step 1: extract the classifier holding this feature
		// and the feature name
		boolean isContribution = true;
		boolean isResourceContribution = false;
		EClassifier classifierToConsider = null;
		String classifierName = null;
		String featureName = null;
		String beginning = "";
		if (text.lastIndexOf("{") != -1) {
			classifierName = text.substring(0, text.lastIndexOf("{")).trim();
			featureName = text.substring(text.lastIndexOf("{")).replace("{", "").trim();
			if (featureName.contains("+=")) {
				beginning = featureName.substring(featureName.indexOf("+=") + 2).trim();
				featureName = featureName.substring(0, featureName.indexOf("+=")).trim();
			} else if (featureName.contains("=")) {
				beginning = featureName.substring(featureName.indexOf("=") + 1).trim();
				featureName = featureName.substring(0, featureName.indexOf("=")).trim();
			}

			if (classifierName.contains("new")) {
				isContribution = false;
				classifierName = classifierName.substring(classifierName.lastIndexOf("new")).trim();
				classifierName = classifierName.substring(classifierName.indexOf(" ")).trim();
				if (classifierName.contains(" ")) {
					classifierName = classifierName.substring(0, classifierName.indexOf(" ")).trim();
				}
			}
			if (classifierName.contains("Resource")) {
				isContribution = false;
				isResourceContribution = true;
				featureName = "Resource Content";
			}
		}

		if (isContribution) {
			TraceabilityIndex traceabilityIndex = getTraceabilityIndex();
			for (TraceabilityIndexEntry entry : traceabilityIndex.getEntries()) {
				for (InstanciationInstruction instruction : Iterables.filter(entry
						.getContainedElementToInstructions().values(), InstanciationInstruction.class)) {
					if (classifierName.equals(instruction.getName())) {
						if (instruction.getMetaType() != null
								&& instruction.getMetaType().getResolvedType() != null) {
							classifierToConsider = instruction.getMetaType().getResolvedType();
							break;
						}
					}
				}
			}
		} else {
			if (!isResourceContribution) {
				classifierToConsider = getEClassifier(classifierName);
			}
		}

		// Step 2: get the feature type
		if (isResourceContribution
				|| (classifierToConsider != null && classifierToConsider instanceof EClass)) {
			EStructuralFeature featureToConsider = null;
			if (!isResourceContribution) {
				featureToConsider = ((EClass)classifierToConsider).getEStructuralFeature(featureName);
			}
			if (isResourceContribution
					|| (featureToConsider != null && featureToConsider.getEType() != null && featureToConsider
							.getEType().getName() != null)) {

				// Step 3: if the feature is an EAttribute, we add a proposal with the default value of this
				// attribute type
				if (featureToConsider instanceof EAttribute) {
					String defaultAttributeValue = "";
					if (featureToConsider.getEType().getDefaultValue() != null) {
						defaultAttributeValue = featureToConsider.getEType().getDefaultValue().toString();
					}
					proposals.add(createTemplateProposal("value (of type "
							+ featureToConsider.getEType().getName() + ")", "Set a simple value of type "
							+ featureToConsider.getEType().getName(), '"' + defaultAttributeValue + "\";",
							"icon/outline/modelingunit_value.gif"));
				} else {
					// Propose to create a new Element of the feature type
					if (!isResourceContribution) {
						proposals.add(createTemplateProposal("new Element (of type "
								+ featureToConsider.getEType().getName() + ")",
								"Set this new Element as value for " + featureToConsider.getName(), "new "
										+ featureToConsider.getEType().getName() + "{\n\t${}\n};",
								"icon/outline/modelingunit_new_element.png"));
					}

					// Propose to reference an already defined element
					TraceabilityIndex traceabilityIndex = getTraceabilityIndex();
					for (TraceabilityIndexEntry entry : traceabilityIndex.getEntries()) {
						for (InstanciationInstruction instruction : Iterables
								.filter(entry.getContainedElementToInstructions().values(),
										InstanciationInstruction.class)) {
							if (instruction.getName() != null
									&& (beginning.length() == 0 || instruction.getName()
											.startsWith(beginning))) {
								if (isResourceContribution
										|| (instruction.getMetaType() != null && (featureToConsider
												.getEType().equals(
														instruction.getMetaType().getResolvedType()) || featureToConsider
												.getEType() instanceof EClass
												&& ((EClass)featureToConsider.getEType())
														.isSuperTypeOf(instruction.getMetaType()
																.getResolvedType())))) {
									proposals.add(createTemplateProposal(
											"Reference to " + instruction.getName(),
											"Set the " + instruction.getName() + " element as value for "
													+ featureName, instruction.getName(),
											"icon/outline/modelingunit_ref.png"));
								}
							}
						}
					}

					// If the expected eType is an EClassifier, also propose all available classifiers
					if (!isResourceContribution
							&& featureToConsider.getEType().equals(EcorePackage.eINSTANCE.getEClassifier())) {
						proposals.addAll(getProposalsForEClassifier(beginning));
					}
				}
			}
		}
		return proposals;
	}

	private Collection<? extends ICompletionProposal> getProposalsForEClassifier(String classNameBeginning)
			throws ReadOnlyException {
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();
		Iterator<EPackage> availablePackages = Iterables.filter(
				getTraceabilityIndex().eResource().getResourceSet().getPackageRegistry().values(),
				EPackage.class).iterator();
		int i = 0;
		while (availablePackages.hasNext() && i < 100) {
			EPackage availablePackage = availablePackages.next();
			for (EClassifier availableClass : availablePackage.getEClassifiers()) {
				if (availableClass.getName() != null
						&& (classNameBeginning.length() == 0 || availableClass.getName().startsWith(
								classNameBeginning))) {
					proposals.add(createTemplateProposal(availableClass.getName(), availableClass
							.getEPackage().getNsURI(), availableClass.getName(),
							"icon/outline/modelingunit_new_element.png"));
					i++;
				}
			}
		}
		return proposals;
	}

	/**
	 * Returns the last relevant keyword of the given text. For example :
	 * <ul>
	 * <li>"new Somethin" will return "new"</li>
	 * <li>"new Something {" will return "{"</li>
	 * <li>"new Something { attrib" will return "{"</li>
	 * <li>"new Something { attribute =" will return "="</li>
	 * <li>"new Something { attribute = 'value'; will return "{"</li>
	 * </ul>
	 * 
	 * @param text
	 *            the text to analyse
	 * @return the last relevant keyword of the given text
	 */
	private String getLastRelevantKeyWord(String text) {

		int lastNew = getLastIndexOf(text, Pattern.compile("new"));
		int lastOpeningBracket = text.lastIndexOf("{");
		int lastStructuralFeatureAffectation = text.lastIndexOf("=");
		int lastMultiValuedStructuralFeatureAffectation = text.lastIndexOf("+=");
		int lastKWIndex = Math.max(
				Math.max(lastStructuralFeatureAffectation, lastStructuralFeatureAffectation),
				Math.max(lastNew, lastOpeningBracket));
		if (lastKWIndex != -1) {
			if (lastKWIndex == lastNew) {
				return "new";
			} else if (lastKWIndex == lastOpeningBracket) {
				return "{";
			} else if (lastKWIndex == lastStructuralFeatureAffectation) {
				return "=";
			} else if (lastKWIndex == lastMultiValuedStructuralFeatureAffectation) {
				return "+=";
			}
		}
		int lastIndexOfSpaceCharacter = lastIndexOfSpaceCharacter(text);
		if (lastIndexOfSpaceCharacter > -1) {
			String textWithoutLastPart = text.substring(0, lastIndexOfSpaceCharacter + 1).trim();
			lastIndexOfSpaceCharacter = lastIndexOfSpaceCharacter(textWithoutLastPart);
			if (lastIndexOfSpaceCharacter > -1) {
				return textWithoutLastPart.substring(lastIndexOfSpaceCharacter + 1).trim();
			}
			return textWithoutLastPart;
		}
		return null;
	}

	private int lastIndexOfSpaceCharacter(String text) {
		int lastIndexOfSpace = text.lastIndexOf(" ");
		int lastIndexOfTab = text.lastIndexOf("\t");
		int lastIndexOfNewLine = text.lastIndexOf("\n");
		return Math.max(Math.max(lastIndexOfSpace, lastIndexOfTab), lastIndexOfNewLine);
	}

	/**
	 * Removes all lines that contains instructions inside closed bracket (e.g.
	 * "myFeature = new MyType { myOtherFeature='42';};").
	 * 
	 * @param text
	 *            the text to modify
	 * @return the text without any instruction inside closed brackets
	 */
	private String removeInstructionsInsideClosedBrackets(String text) {
		Document tempDoc = new Document(text);
		IntentPairMatcher pairMatcher = new IntentPairMatcher();
		if (tempDoc.get().indexOf("{") != -1) {
			IRegion match = pairMatcher.match(tempDoc, tempDoc.get().indexOf("{") + 1);
			try {
				while (tempDoc.get().indexOf("{") != -1 && match != null) {

					int beginLineToRemove = tempDoc.getLineOfOffset(match.getOffset());
					int beginOffSettoRemove = tempDoc.getLineOffset(beginLineToRemove);
					int endLineToRemove = tempDoc.getLineOfOffset(match.getOffset() + match.getLength());
					int endOffsetToRemove = tempDoc.getLineOffset(endLineToRemove)
							+ tempDoc.getLineLength(endLineToRemove);
					String newDocContent = tempDoc.get().substring(0, beginOffSettoRemove)
							+ tempDoc.get().substring(endOffsetToRemove);
					tempDoc.set(newDocContent);
					match = pairMatcher.match(tempDoc, tempDoc.get().indexOf("{") + 1);
				}
			} catch (BadLocationException e) {
				// Nothing to do : silent catch
			}
		}
		return tempDoc.get();
	}

	/**
	 * Removes all lines that contains ended instruction (e.g. "myFeature = 'myValue';").
	 * 
	 * @param text
	 *            the text to modify
	 * @return the text without any ended instruction
	 */
	private String removeEndedInstructions(String text) {
		Document tempDoc = new Document(text);
		try {
			while (tempDoc.get().lastIndexOf(";") > -1) {
				int lineToRemove = tempDoc.getLineOfOffset(tempDoc.get().lastIndexOf(";"));
				int lineToRemoveOffset = tempDoc.getLineOffset(lineToRemove);
				int lineToRemoveLength = tempDoc.getLineLength(lineToRemove);
				String newDocContent = tempDoc.get().substring(0, lineToRemoveOffset)
						+ tempDoc.get().substring(lineToRemoveOffset + lineToRemoveLength);
				tempDoc.set(newDocContent);
			}
		} catch (BadLocationException e) {
			// Nothing to do
		}

		return tempDoc.get();
	}

	private TemplateProposal createStructuralFeatureAffectationTemplateProposal(String contributionName,
			EStructuralFeature feature) {
		String affect = "=";
		if (feature.isMany()) {
			affect = "+" + affect;
		}
		String label = feature.getName();
		if (feature.getEType() != null && feature.getEType().getName() != null) {
			label += " : " + feature.getEType().getName();
			if (feature.getLowerBound() != 1 || feature.getUpperBound() != 1) {
				label += " [";
				if (feature.getLowerBound() == 0 && feature.getUpperBound() == 1) {
					label += "?";
				} else {
					label += feature.getLowerBound() + ",";
					if (feature.getUpperBound() != -1) {
						label += feature.getUpperBound();
					} else {
						label += "*";
					}
				}
				label += "]";
			}
		}
		String description = "Set the value " + contributionName + "." + feature.getName();
		return createTemplateProposal(label, description, feature.getName() + " " + affect + " ",
				"icon/outline/modelingunit_affect.png");
	}

	private TraceabilityIndex getTraceabilityIndex() throws ReadOnlyException {
		return traceabilityInfoQuery.getOrCreateTraceabilityIndex();
	}

	private EClassifier getEClassifier(String contributionName) throws ReadOnlyException {
		EClassifier classifierToConsider = null;
		Iterator<EPackage> availablePackages = Iterables.filter(
				getTraceabilityIndex().eResource().getResourceSet().getPackageRegistry().values(),
				EPackage.class).iterator();

		String packageName = null;
		String classifierName = contributionName;

		if (contributionName.matches(IDENTIFIER_REGEXP + QUALIFIED_NAME_DELIMITER + IDENTIFIER_REGEXP)) {
			String[] split = contributionName.split(QUALIFIED_NAME_DELIMITER);
			packageName = split[0];
			classifierName = split[1];
		}
		while (availablePackages.hasNext() && classifierToConsider == null) {
			EPackage availablePackage = availablePackages.next();
			if (packageName == null || packageName.equals(availablePackage.getName())) {
				classifierToConsider = availablePackage.getEClassifier(classifierName);
			}
		}
		return classifierToConsider;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.completion.AbstractIntentCompletionProcessor#getContextType()
	 */
	@Override
	public String getContextType() {
		return IntentPartitionScanner.INTENT_MODELINGUNIT;
	}

}
