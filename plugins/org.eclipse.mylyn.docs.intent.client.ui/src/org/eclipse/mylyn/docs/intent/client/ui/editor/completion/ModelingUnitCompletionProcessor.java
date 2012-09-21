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
import org.eclipse.mylyn.docs.intent.parser.IntentKeyWords;

/**
 * Computes the completion proposal for ModelingUnits.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ModelingUnitCompletionProcessor extends AbstractIntentCompletionProcessor {

	private static final String NEW_ENTITY_KEYWORD = "new";

	private static final String RESOURCE_DECLARATION_KEYWORD = "Resource";

	private static final String MODELINGUNIT_NEW_ELEMENT_ICON = "icon/outline/modelingunit_new_element.png";

	private static final String MODELINGUNIT_RESOURCE_ICON = "icon/outline/modelingunit_resource.gif";

	private static final String IDENTIFIER_REGEXP = "([a-zA-z0-9_-]+)";

	private static final String QUALIFIED_NAME_DELIMITER = "\\.";

	private RepositoryAdapter repositoryAdapter;

	private TraceabilityInformationsQuery traceabilityInfoQuery;

	/**
	 * Creates the completion processor.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
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
				} else if (NEW_ENTITY_KEYWORD.equals(lastRelevantKeyWord)) {
					proposals.addAll(getProposalsForNewInstruction(text));
				} else if (IntentKeyWords.INTENT_KEYWORD_OPEN.equals(lastRelevantKeyWord)) {
					proposals.addAll(getProposalsForStructuralFeatureAffectation(text));
				} else if (IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL.equals(lastRelevantKeyWord)
						|| IntentKeyWords.MODELING_UNIT_AFFECTATION_MULTI_VAL.equals(lastRelevantKeyWord)) {
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
		if (text.trim().length() == 0 || RESOURCE_DECLARATION_KEYWORD.startsWith(text.trim())) {
			proposals.add(createTemplateProposal(RESOURCE_DECLARATION_KEYWORD,
					"Declaration of a new Resource", prefix
							+ "Resource myResource {\n\t\tURI = \"${}\";\n\t}", MODELINGUNIT_RESOURCE_ICON));
		}
		// Second proposal : new entity
		if (text.trim().length() == 0 || NEW_ENTITY_KEYWORD.startsWith(text.trim())) {
			proposals.add(createTemplateProposal(NEW_ENTITY_KEYWORD, "Declaration of a new entity", prefix
					+ "new ${Type} {}", MODELINGUNIT_NEW_ELEMENT_ICON));
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
					String description = "Contribute to the " + instruction.getName()
							+ IntentKeyWords.INTENT_WHITESPACE;
					if (instruction.getMetaType() != null
							&& instruction.getMetaType().getTypeName() != null) {
						description += instruction.getMetaType().getTypeName();
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
		String classNameBeginning = text.substring(text.lastIndexOf(NEW_ENTITY_KEYWORD))
				.replace(NEW_ENTITY_KEYWORD, "").trim();
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
		if (text.lastIndexOf(IntentKeyWords.INTENT_KEYWORD_OPEN) != -1) {
			contributionName = text.substring(0, text.lastIndexOf(IntentKeyWords.INTENT_KEYWORD_OPEN)).trim();
			featureNameBeginning = text.substring(text.lastIndexOf(IntentKeyWords.INTENT_KEYWORD_OPEN))
					.replace(IntentKeyWords.INTENT_KEYWORD_OPEN, "").trim();

			if (contributionName.contains(NEW_ENTITY_KEYWORD)) {
				isContribution = false;
				contributionName = contributionName.substring(
						contributionName.lastIndexOf(NEW_ENTITY_KEYWORD)).trim();
				contributionName = contributionName.substring(
						contributionName.indexOf(IntentKeyWords.INTENT_WHITESPACE)).trim();
				if (contributionName.contains(IntentKeyWords.INTENT_WHITESPACE)) {
					contributionName = contributionName.substring(0,
							contributionName.indexOf(IntentKeyWords.INTENT_WHITESPACE)).trim();
				}
			}
			if (contributionName.contains(RESOURCE_DECLARATION_KEYWORD)) {
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
				// If the structural feature affectation is inside an Instanciation instruction
				EClassifier classifierToConsider = getEClassifier(contributionName);
				if (classifierToConsider instanceof EClass) {
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
				"URI = \"${}\";", MODELINGUNIT_RESOURCE_ICON));
		proposals.add(createTemplateProposal("Resource Content", "Add content to the Resource",
				"content += ${};", MODELINGUNIT_RESOURCE_ICON));
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
		if (text.lastIndexOf(IntentKeyWords.INTENT_KEYWORD_OPEN) != -1) {
			classifierName = text.substring(0, text.lastIndexOf(IntentKeyWords.INTENT_KEYWORD_OPEN)).trim();
			featureName = text.substring(text.lastIndexOf(IntentKeyWords.INTENT_KEYWORD_OPEN))
					.replace(IntentKeyWords.INTENT_KEYWORD_OPEN, "").trim();
			if (featureName.contains(IntentKeyWords.MODELING_UNIT_AFFECTATION_MULTI_VAL)) {
				beginning = featureName.substring(
						featureName.indexOf(IntentKeyWords.MODELING_UNIT_AFFECTATION_MULTI_VAL) + 2).trim();
				featureName = featureName.substring(0,
						featureName.indexOf(IntentKeyWords.MODELING_UNIT_AFFECTATION_MULTI_VAL)).trim();
			} else if (featureName.contains(IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL)) {
				beginning = featureName.substring(
						featureName.indexOf(IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL) + 1).trim();
				featureName = featureName.substring(0,
						featureName.indexOf(IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL)).trim();
			}

			if (classifierName.contains(NEW_ENTITY_KEYWORD)) {
				isContribution = false;
				classifierName = classifierName.substring(classifierName.lastIndexOf(NEW_ENTITY_KEYWORD))
						.trim();
				classifierName = classifierName.substring(
						classifierName.indexOf(IntentKeyWords.INTENT_WHITESPACE)).trim();
				if (classifierName.contains(IntentKeyWords.INTENT_WHITESPACE)) {
					classifierName = classifierName.substring(0,
							classifierName.indexOf(IntentKeyWords.INTENT_WHITESPACE)).trim();
				}
			}
			if (classifierName.contains(RESOURCE_DECLARATION_KEYWORD)) {
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
		if (isResourceContribution || classifierToConsider instanceof EClass) {
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
								MODELINGUNIT_NEW_ELEMENT_ICON));
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
							MODELINGUNIT_NEW_ELEMENT_ICON));
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
	 * <li>"new Something {" will return IntentKeyWords.INTENT_KEYWORD_OPEN</li>
	 * <li>"new Something { attrib" will return IntentKeyWords.INTENT_KEYWORD_OPEN</li>
	 * <li>"new Something { attribute =" will return IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL</li>
	 * <li>"new Something { attribute = 'value'; will return IntentKeyWords.INTENT_KEYWORD_OPEN</li>
	 * </ul>
	 * 
	 * @param text
	 *            the text to analyse
	 * @return the last relevant keyword of the given text
	 */
	private String getLastRelevantKeyWord(String text) {

		int lastNew = getLastIndexOf(text, Pattern.compile(NEW_ENTITY_KEYWORD));
		int lastOpeningBracket = text.lastIndexOf(IntentKeyWords.INTENT_KEYWORD_OPEN);
		int lastStructuralFeatureAffectation = text
				.lastIndexOf(IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL);
		int lastMultiValuedStructuralFeatureAffectation = text
				.lastIndexOf(IntentKeyWords.MODELING_UNIT_AFFECTATION_MULTI_VAL);
		int lastKWIndex = Math.max(
				Math.max(lastStructuralFeatureAffectation, lastStructuralFeatureAffectation),
				Math.max(lastNew, lastOpeningBracket));
		if (lastKWIndex != -1) {
			if (lastKWIndex == lastNew) {
				return NEW_ENTITY_KEYWORD;
			} else if (lastKWIndex == lastOpeningBracket) {
				return IntentKeyWords.INTENT_KEYWORD_OPEN;
			} else if (lastKWIndex == lastStructuralFeatureAffectation) {
				return IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL;
			} else if (lastKWIndex == lastMultiValuedStructuralFeatureAffectation) {
				return IntentKeyWords.MODELING_UNIT_AFFECTATION_MULTI_VAL;
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
		int lastIndexOfSpace = text.lastIndexOf(IntentKeyWords.INTENT_WHITESPACE);
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
		if (tempDoc.get().indexOf(IntentKeyWords.INTENT_KEYWORD_OPEN) != -1) {
			IRegion match = pairMatcher.match(tempDoc,
					tempDoc.get().indexOf(IntentKeyWords.INTENT_KEYWORD_OPEN) + 1);
			try {
				while (tempDoc.get().indexOf(IntentKeyWords.INTENT_KEYWORD_OPEN) != -1 && match != null) {

					int beginLineToRemove = tempDoc.getLineOfOffset(match.getOffset());
					int beginOffSettoRemove = tempDoc.getLineOffset(beginLineToRemove);
					int endLineToRemove = tempDoc.getLineOfOffset(match.getOffset() + match.getLength());
					int endOffsetToRemove = tempDoc.getLineOffset(endLineToRemove)
							+ tempDoc.getLineLength(endLineToRemove);
					String newDocContent = tempDoc.get().substring(0, beginOffSettoRemove)
							+ tempDoc.get().substring(endOffsetToRemove);
					tempDoc.set(newDocContent);
					match = pairMatcher.match(tempDoc,
							tempDoc.get().indexOf(IntentKeyWords.INTENT_KEYWORD_OPEN) + 1);
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
		String affect = IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL;
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
		return createTemplateProposal(label, description, feature.getName()
				+ IntentKeyWords.INTENT_WHITESPACE + affect + IntentKeyWords.INTENT_WHITESPACE,
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
