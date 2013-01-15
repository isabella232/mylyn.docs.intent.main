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
package org.eclipse.mylyn.docs.intent.compare.utils;

import com.google.common.collect.Lists;

import java.util.Collection;

import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;

/**
 * The {@link FeatureFilter} used by Intent to determine the features to ignore when comparing 2
 * {@link org.eclipse.mylyn.docs.intent.core.document.IntentDocument}s.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentFeatureFilter extends FeatureFilter {

	private static final Collection<EStructuralFeature> FEATURES_TO_IGNORE = getFeaturesToIgnore();

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.diff.FeatureFilter#isIgnoredReference(org.eclipse.emf.compare.Match,
	 *      org.eclipse.emf.ecore.EReference)
	 */
	@Override
	protected boolean isIgnoredReference(Match match, EReference reference) {
		return FEATURES_TO_IGNORE.contains(reference);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.diff.FeatureFilter#isIgnoredAttribute(org.eclipse.emf.ecore.EAttribute)
	 */
	@Override
	protected boolean isIgnoredAttribute(EAttribute attribute) {
		return FEATURES_TO_IGNORE.contains(attribute);
	}

	/**
	 * Returns all the references to ignore when comparing 2 Intent elements.
	 * 
	 * @return all the references to ignore when comparing 2 Intent elements
	 */
	protected static Collection<EStructuralFeature> getFeaturesToIgnore() {
		Collection<EStructuralFeature> featuresToIgnoreList = Lists.newArrayList();
		featuresToIgnoreList.add(ModelingUnitPackage.eINSTANCE
				.getInstanciationInstructionReference_Instanciation());
		featuresToIgnoreList.add(ModelingUnitPackage.eINSTANCE
				.getContributionInstruction_ContributionReference());
		featuresToIgnoreList.add(IntentDocumentPackage.eINSTANCE.getIntentGenericElement_CompilationStatus());
		featuresToIgnoreList.add(IntentDocumentPackage.eINSTANCE.getIntentStructuredElement_CompleteLevel());
		featuresToIgnoreList.add(ModelingUnitPackage.eINSTANCE.getExternalContentReference_ExternalContent());
		featuresToIgnoreList.add(ModelingUnitPackage.eINSTANCE.getExternalContentReference_MarkedAsMerged());
		return featuresToIgnoreList;
	}
}
