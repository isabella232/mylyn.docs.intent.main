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
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.mylyn.docs.intent.client.ui.editor.scanner.IntentPartitionScanner;
import org.eclipse.mylyn.docs.intent.collab.common.query.TraceabilityInformationsQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.parser.IntentKeyWords;

/**
 * Computes the completion proposal for ModelingUnits.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ModelingUnitCompletionProcessor extends AbstractIntentCompletionProcessor {

	private static final String RIGHT_PAR = ")";

	private static final String DOT = ".";

	private static final String NEW_LINE = "\n\t";

	private static final String NEW_ENTITY_KEYWORD = "new";

	private static final String RESOURCE_DECLARATION_KEYWORD = "Resource";

	private static final String REF_KEYWORD = "@ref";

	private static final String MODELINGUNIT_NEW_ELEMENT_ICON = "icon/outline/modelingunit_new_element.png";

	private static final String MODELINGUNIT_RESOURCE_ICON = "icon/outline/modelingunit_resource.gif";

	private static final String IDENTIFIER_REGEXP = "([a-zA-z0-9_-]+)";

	private static final String QUALIFIED_NAME_DELIMITER = "\\.";

	private TraceabilityInformationsQuery traceabilityInfoQuery;

	private RepositoryAdapter repositoryAdapter;

	/**
	 * Creates the completion processor.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter
	 */
	public ModelingUnitCompletionProcessor(RepositoryAdapter repositoryAdapter) {
		this.repositoryAdapter = repositoryAdapter;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.completion.AbstractIntentCompletionProcessor#computeCompletionProposals()
	 */
	@Override
	protected ICompletionProposal[] computeCompletionProposals() {
		this.traceabilityInfoQuery = new TraceabilityInformationsQuery(repositoryAdapter);
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();

		try {
			// Step 1: get the focused instruction
			String text = document.get(0, offset);
			// get the currently considered modeling unit
			int startOffset = getLastIndexOf(text, Pattern.compile("@M"));
			if (startOffset > -1) {
				text = text.substring(startOffset);
			}
			String intialText = text;
			// remove instructions inside closed brackets
			text = removeInstructionsInsideClosedBrackets(text);
			// remove instructions that are finished (ending with ";")
			text = removeEndedInstructions(text);

			// Step 2: get the last relevant keyword
			String lastRelevantKeyWord = getLastRelevantKeyWord(text);

			// Step 3: according to this keyword, compute the proposals
			if (lastRelevantKeyWord == null) {
				proposals.addAll(getProposalsForEmptyModelingUnit("".equals(intialText), ""));
			} else {
				proposals.addAll(computeCompletionProposalsFromText(text, lastRelevantKeyWord));
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

	/**
	 * According to the given text and last relevant keyword, returns the available
	 * {@link ICompletionProposal}s.
	 * 
	 * @param text
	 *            the text of the current Modeling Unit
	 * @param lastRelevantKeyWord
	 *            the last relevant keyword typed by user
	 * @throws ReadOnlyException
	 *             the available {@link ICompletionProposal}s
	 * @return the available {@link ICompletionProposal}s
	 */
	private Collection<ICompletionProposal> computeCompletionProposalsFromText(String text,
			String lastRelevantKeyWord) throws ReadOnlyException {
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();
		if ("".equals(lastRelevantKeyWord)) {
			proposals.addAll(getProposalsForEmptyModelingUnit(false, text.trim()));
		} else if (NEW_ENTITY_KEYWORD.equals(lastRelevantKeyWord)) {
			proposals.addAll(getProposalsForNewInstruction(text));
		} else if (IntentKeyWords.INTENT_KEYWORD_OPEN.equals(lastRelevantKeyWord)) {
			proposals.addAll(getProposalsForStructuralFeatureAffectation(text));
		} else if (IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL.equals(lastRelevantKeyWord)
				|| IntentKeyWords.MODELING_UNIT_AFFECTATION_MULTI_VAL.equals(lastRelevantKeyWord)) {
			proposals.addAll(getProposalsForStructuralFeatureValue(text));
		}
		return proposals;
	}

	/**
	 * For an empty modeling unit, we provide the following {@link ICompletionProposal}s :
	 * <ul>
	 * <li>Resource declaration</li>
	 * <li>new entity</li>
	 * <li>contribute to an existing entity.</li>
	 * </ul>
	 * 
	 * @param isAtMUBeggining
	 *            if the cursor is at MU beginning
	 * @param text
	 *            the text current written (e.g. 'new ')
	 * @return the {@link ICompletionProposal}s
	 * @throws ReadOnlyException
	 *             if errors occur while reading repository content
	 */
	private Collection<? extends ICompletionProposal> getProposalsForEmptyModelingUnit(
			boolean isAtMUBeggining, String text) throws ReadOnlyException {
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();
		String prefix = NEW_LINE;
		if (isAtMUBeggining) {
			prefix = "@M" + NEW_LINE;
		}
		// First proposal : new Resource Declaration
		if (text.length() == 0 || RESOURCE_DECLARATION_KEYWORD.startsWith(text)) {
			proposals.add(createResourceDeclarationProposal(prefix));
		}

		// Second proposal : new entity
		if (text.length() == 0 || NEW_ENTITY_KEYWORD.startsWith(text)) {
			proposals.add(createNewEntityProposal(prefix));
		}

		// Third proposal : new internal resource
		if (text.length() == 0 || REF_KEYWORD.startsWith(text)) {
			proposals.add(createNewInternalRefProposal(prefix));
		}

		// Fourth proposal : contribute to an existing entity
		for (InstanciationInstruction instruction : traceabilityInfoQuery.getInstanciations()) {
			if (instruction.getName() != null
					&& (text.length() == 0 || instruction.getName().startsWith(text))) {
				String description = "Contribute to the " + instruction.getName()
						+ IntentKeyWords.INTENT_WHITESPACE;
				if (instruction.getMetaType() != null && instruction.getMetaType().getTypeName() != null) {
					description += instruction.getMetaType().getTypeName();
				} else {
					description += "entity";
				}
				proposals.add(createTemplateProposal(instruction.getName() + " (contribution)", description,
						prefix + instruction.getName() + " {\n\t\t${}\n\t}",
						"icon/outline/modelingunit_contribution.png"));
			}
		}
		return proposals;
	}

	/**
	 * Returns all the {@link ICompletionProposal}s allowing to create new entities which name matches the
	 * given text.
	 * 
	 * @param text
	 *            the beginning of the entity name to create
	 * @return all the {@link ICompletionProposal}s allowing to create new entities which name matches the
	 *         given text
	 * @throws ReadOnlyException
	 *             if errors occur while reading repository content
	 */
	private Collection<? extends ICompletionProposal> getProposalsForNewInstruction(String text)
			throws ReadOnlyException {
		String classNameBeginning = text.substring(text.lastIndexOf(NEW_ENTITY_KEYWORD))
				.replace(NEW_ENTITY_KEYWORD, "").trim();
		return getProposalsForEClassifier(classNameBeginning);
	}

	/**
	 * Returns all the {@link ICompletionProposal}s allowing to set a value to a feature starting wit the
	 * given name.
	 * 
	 * @param text
	 *            the beginning of the entity name to create
	 * @return all the {@link ICompletionProposal}s allowing to set a value to a feature starting wit the
	 *         given name
	 * @throws ReadOnlyException
	 *             if errors occur while reading repository content
	 */
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
			if (contributionName.contains(IntentKeyWords.INTENT_LINEBREAK)) {
				String[] split = contributionName.split(IntentKeyWords.INTENT_LINEBREAK);
				contributionName = split[split.length - 1].trim();
			}
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
						if (isSettableFeature(feature)
								&& (featureNameBeginning.length() == 0 || feature.getName().startsWith(
										featureNameBeginning))) {
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
		for (InstanciationInstruction instruction : traceabilityInfoQuery.getInstanciations()) {
			if (contributionName.equals(instruction.getName())) {
				if (instruction.getMetaType() != null && instruction.getMetaType().getResolvedType() != null) {
					for (EStructuralFeature feature : instruction.getMetaType().getResolvedType()
							.getEAllStructuralFeatures()) {
						if (isSettableFeature(feature)
								&& (featureNameBeginning.length() == 0 || feature.getName().startsWith(
										featureNameBeginning))) {
							proposals.add(createStructuralFeatureAffectationTemplateProposal(
									contributionName, feature));
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
			for (InstanciationInstruction instruction : traceabilityInfoQuery.getInstanciations()) {
				if (classifierName.equals(instruction.getName())) {
					if (instruction.getMetaType() != null
							&& instruction.getMetaType().getResolvedType() != null) {
						classifierToConsider = instruction.getMetaType().getResolvedType();
						break;
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

				proposals.addAll(doGetProposalsForStructuralFeatureValue(isResourceContribution, featureName,
						beginning, featureToConsider));
			}
		}
		return proposals;
	}

	private Collection<ICompletionProposal> doGetProposalsForStructuralFeatureValue(
			boolean isResourceContribution, String featureName, String beginning,
			EStructuralFeature featureToConsider) throws ReadOnlyException {
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();
		// Step 3: if the feature is an EAttribute, we add a proposal with the default value of this
		// attribute type
		if (featureToConsider instanceof EAttribute) {
			String defaultAttributeValue = "";
			if (featureToConsider.getEType().getDefaultValue() != null) {
				defaultAttributeValue = "Default: " + featureToConsider.getDefaultValue().toString();
			}
			if (featureToConsider.getEType() instanceof EEnum) {
				for (EEnumLiteral literal : ((EEnum)featureToConsider.getEType()).getELiterals()) {
					proposals.add(createTemplateProposal("'" + literal.getName() + "' value (of type "
							+ featureToConsider.getEType().getName() + RIGHT_PAR, defaultAttributeValue
							+ " - Set a simple value of type " + featureToConsider.getEType().getName(), '"'
							+ literal.getName() + "\";", "icon/outline/modelingunit_value.gif"));
				}
			} else {
				proposals.add(createTemplateProposal("value (of type "
						+ featureToConsider.getEType().getName() + RIGHT_PAR, defaultAttributeValue
						+ " - Set a simple value of type " + featureToConsider.getEType().getName(), '"'
						+ defaultAttributeValue + "\";", "icon/outline/modelingunit_value.gif"));
			}
		} else {
			// Propose to create a new Element of the feature type
			if (!isResourceContribution) {
				proposals.add(createTemplateProposal("new Element (of type "
						+ featureToConsider.getEType().getName() + RIGHT_PAR,
						"Set this new Element as value for " + featureToConsider.getName(), "new "
								+ getQualifiedName(featureToConsider.getEType().getEPackage()) + DOT
								+ featureToConsider.getEType().getName() + "{\n\t${}\n};",
						MODELINGUNIT_NEW_ELEMENT_ICON));
			}

			// Propose to reference an already defined element
			for (InstanciationInstruction instruction : traceabilityInfoQuery.getInstanciations()) {
				// Instruction's name should match the beginning
				boolean isMatchingInstruction = false;
				boolean hasMatchingName = instruction.getName() != null
						&& (beginning.length() == 0 || instruction.getName().startsWith(beginning));
				if (hasMatchingName && !isResourceContribution) {
					// Instruction's type should match the featureToConsider type
					isMatchingInstruction = instruction.getMetaType() != null
							&& (featureToConsider.getEType().equals(
									instruction.getMetaType().getResolvedType()) || featureToConsider
									.getEType() instanceof EClass
									&& ((EClass)featureToConsider.getEType()).isSuperTypeOf(instruction
											.getMetaType().getResolvedType()));
				} else if (hasMatchingName) {
					// Resource content has not type so all instructions should be displayed
					isMatchingInstruction = true;
				}
				if (isMatchingInstruction) {
					proposals.add(createTemplateProposal("Reference to " + instruction.getName(), "Set the "
							+ instruction.getName() + " element as value for " + featureName,
							instruction.getName(), "icon/outline/modelingunit_ref.png"));
				}
			}

			// If the expected eType is an EClassifier, also propose all available classifiers
			if (!isResourceContribution
					&& featureToConsider.getEType().equals(EcorePackage.eINSTANCE.getEClassifier())) {
				proposals.addAll(getProposalsForEClassifier(beginning));
			}
		}
		return proposals;
	}

	private Collection<? extends ICompletionProposal> getProposalsForEClassifier(String classNameBeginning)
			throws ReadOnlyException {
		boolean isPrefixedByPackageName = classNameBeginning.indexOf('.') != -1;
		String packageNamePrefix = null;
		String classNameBeginningWithoutPackageDeclaration = classNameBeginning;
		if (isPrefixedByPackageName) {
			String[] split = classNameBeginning.split("\\.");
			packageNamePrefix = split[0];
			if (split.length == 1) {
				classNameBeginningWithoutPackageDeclaration = "";
			} else {
				classNameBeginningWithoutPackageDeclaration = split[1];
			}
		}
		Collection<ICompletionProposal> proposals = Sets.newLinkedHashSet();
		Iterator<EPackage> availablePackages = Iterables.filter(
				traceabilityInfoQuery.getOrCreateTraceabilityIndex().eResource().getResourceSet()
						.getPackageRegistry().values(), EPackage.class).iterator();
		int i = 0;
		while (availablePackages.hasNext() && i < 100) {
			EPackage availablePackage = availablePackages.next();
			if (!isPrefixedByPackageName || isPrefixedByPackageName
					&& getQualifiedName(availablePackage).equals(packageNamePrefix)) {
				for (EClassifier availableClass : availablePackage.getEClassifiers()) {
					if (availableClass.getName() != null
							&& (classNameBeginningWithoutPackageDeclaration.length() == 0 || availableClass
									.getName().startsWith(classNameBeginningWithoutPackageDeclaration))) {
						String proposalContent = availableClass.getName();
						if (!isPrefixedByPackageName) {
							proposalContent = getQualifiedName(availablePackage) + DOT + proposalContent;
						}
						proposals.add(createTemplateProposal(availableClass.getName(),
								availablePackage.getNsURI(), proposalContent, MODELINGUNIT_NEW_ELEMENT_ICON));
						i++;
					}
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
		String lastRelevantKeyword = null;
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
				lastRelevantKeyword = NEW_ENTITY_KEYWORD;
			} else if (lastKWIndex == lastOpeningBracket) {
				lastRelevantKeyword = IntentKeyWords.INTENT_KEYWORD_OPEN;
			} else if (lastKWIndex == lastStructuralFeatureAffectation) {
				lastRelevantKeyword = IntentKeyWords.MODELING_UNIT_AFFECTATION_SINGLE_VAL;
			} else if (lastKWIndex == lastMultiValuedStructuralFeatureAffectation) {
				lastRelevantKeyword = IntentKeyWords.MODELING_UNIT_AFFECTATION_MULTI_VAL;
			}
		}
		return lastRelevantKeyword;
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
		String textWithClosedInstructionsRemoved = text;
		try {
			int nextOffsetForEndingInstruction = textWithClosedInstructionsRemoved.indexOf("}");
			int nextOffsetForEndingInstruction2 = textWithClosedInstructionsRemoved.indexOf("};");
			while (Math.max(nextOffsetForEndingInstruction, nextOffsetForEndingInstruction2) != -1) {
				String textToRemove = "";
				if (nextOffsetForEndingInstruction > nextOffsetForEndingInstruction2) {
					textToRemove = textWithClosedInstructionsRemoved.substring(0,
							nextOffsetForEndingInstruction + 1);
				} else {
					textToRemove = textWithClosedInstructionsRemoved.substring(0,
							nextOffsetForEndingInstruction + 2);
				}
				textToRemove = textToRemove.substring(textToRemove
						.substring(0, textToRemove.lastIndexOf("{")).lastIndexOf(
								IntentKeyWords.INTENT_LINEBREAK));
				textWithClosedInstructionsRemoved = textWithClosedInstructionsRemoved.replace(textToRemove,
						"");
				nextOffsetForEndingInstruction = textWithClosedInstructionsRemoved.indexOf("}");
				nextOffsetForEndingInstruction2 = textWithClosedInstructionsRemoved.indexOf("};");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// Nothing to do
		} catch (StringIndexOutOfBoundsException e) {
			// Nothing to do
		}
		return textWithClosedInstructionsRemoved;
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
				} else if (feature.getUpperBound() != -1) {
					label += feature.getLowerBound() + "," + feature.getUpperBound();
				} else {
					label += feature.getLowerBound() + ",*";
				}
				label += "]";
			}
		}
		String description = "Set the value " + contributionName + DOT + feature.getName();
		if (feature.getDefaultValue() != null) {
			description = "Default: " + feature.getDefaultValue() + " - " + description;
		}
		return createTemplateProposal(label, description, feature.getName()
				+ IntentKeyWords.INTENT_WHITESPACE + affect + IntentKeyWords.INTENT_WHITESPACE,
				"icon/outline/modelingunit_affect.png");
	}

	private EClassifier getEClassifier(String contributionName) throws ReadOnlyException {
		EClassifier classifierToConsider = null;
		Iterator<EPackage> availablePackages = Iterables.filter(
				traceabilityInfoQuery.getOrCreateTraceabilityIndex().eResource().getResourceSet()
						.getPackageRegistry().values(), EPackage.class).iterator();

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
	 * Indicates if the given feature can be modified and hence should be displayed in the proposal.
	 * 
	 * @param feature
	 *            the feature to consider
	 * @return true if the given feature can be modified and hence should be displayed in the proposal, false
	 *         otherwise
	 */
	private boolean isSettableFeature(EStructuralFeature feature) {
		return feature.isChangeable() && !feature.isDerived();
	}

	/**
	 * Returns the qualified name of the given ePackage.
	 * 
	 * @param ePackage
	 *            the ePackage
	 * @return the qualified name of the given ePackage
	 */
	private static String getQualifiedName(EPackage ePackage) {
		String res = ePackage.getName();
		EPackage tmp = (EPackage)ePackage.eContainer();
		while (tmp != null) {
			res = tmp.getName() + '.' + res;
			tmp = (EPackage)tmp.eContainer();
		}
		return res;
	}

	/**
	 * Creates a {@link ICompletionProposal} allowing to create a new entity.
	 * 
	 * @param prefix
	 *            the prefix to use
	 * @return a {@link ICompletionProposal} allowing to create a new entity.
	 */
	private ICompletionProposal createNewEntityProposal(String prefix) {
		return createTemplateProposal(NEW_ENTITY_KEYWORD, "Declaration of a new entity", prefix
				+ "new ${Type} {}", MODELINGUNIT_NEW_ELEMENT_ICON);
	}

	/**
	 * Creates a {@link ICompletionProposal} allowing to create a new resource declaration.
	 * 
	 * @param prefix
	 *            the prefix to use
	 * @return a {@link ICompletionProposal} allowing to create a new resource declaration.
	 */
	private ICompletionProposal createResourceDeclarationProposal(String prefix) {
		return createTemplateProposal(RESOURCE_DECLARATION_KEYWORD, "Declaration of a new Resource", prefix
				+ "Resource myResource {\n\t\tURI = \"${}\";\n\t}", MODELINGUNIT_RESOURCE_ICON);
	}

	/**
	 * Creates a {@link ICompletionProposal} allowing to create a new internal ref.
	 * 
	 * @param prefix
	 *            the prefix to use
	 * @return a {@link ICompletionProposal} allowing to create a new internal ref.
	 */
	private ICompletionProposal createNewInternalRefProposal(String prefix) {
		return createTemplateProposal(REF_KEYWORD,
				"Declaration of a new internal entity (stored only inside the intent repository)", prefix
						+ REF_KEYWORD + " \"intent:/" + repositoryAdapter.getRepository().getIdentifier()
						+ "/${newElementPath}\"", MODELINGUNIT_NEW_ELEMENT_ICON);
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
