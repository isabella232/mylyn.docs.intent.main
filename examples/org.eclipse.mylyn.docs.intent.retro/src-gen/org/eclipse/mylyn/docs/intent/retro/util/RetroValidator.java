/**
 * Copyright (c) 2010, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 	Obeo - initial API and implementation
 * 
 */
package org.eclipse.mylyn.docs.intent.retro.util;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.mylyn.docs.intent.retro.API;
import org.eclipse.mylyn.docs.intent.retro.AcceptanceTest;
import org.eclipse.mylyn.docs.intent.retro.Bundle;
import org.eclipse.mylyn.docs.intent.retro.Category;
import org.eclipse.mylyn.docs.intent.retro.DevelopperFeature;
import org.eclipse.mylyn.docs.intent.retro.EndUserFeature;
import org.eclipse.mylyn.docs.intent.retro.Feature;
import org.eclipse.mylyn.docs.intent.retro.Interaction;
import org.eclipse.mylyn.docs.intent.retro.NameSpace;
import org.eclipse.mylyn.docs.intent.retro.Product;
import org.eclipse.mylyn.docs.intent.retro.Project;
import org.eclipse.mylyn.docs.intent.retro.RetroPackage;
import org.eclipse.mylyn.docs.intent.retro.RetroPlugin;
import org.eclipse.mylyn.docs.intent.retro.UnitTest;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.mylyn.docs.intent.retro.RetroPackage
 * @generated
 */
public class RetroValidator extends EObjectValidator {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2010, 2011 Obeo.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License v1.0\r\nwhich accompanies this distribution, and is available at\r\nhttp://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\n\tObeo - initial API and implementation\r\n";

	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final RetroValidator INSTANCE = new RetroValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic
	 * {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.mylyn.docs.intent.retro";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants
	 * in a derived class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RetroValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return RetroPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		switch (classifierID) {
			case RetroPackage.BUNDLE:
				return validateBundle((Bundle)value, diagnostics, context);
			case RetroPackage.NAME_SPACE:
				return validateNameSpace((NameSpace)value, diagnostics, context);
			case RetroPackage.DEVELOPPER_FEATURE:
				return validateDevelopperFeature((DevelopperFeature)value, diagnostics, context);
			case RetroPackage.END_USER_FEATURE:
				return validateEndUserFeature((EndUserFeature)value, diagnostics, context);
			case RetroPackage.FEATURE:
				return validateFeature((Feature)value, diagnostics, context);
			case RetroPackage.API:
				return validateAPI((API)value, diagnostics, context);
			case RetroPackage.UNIT_TEST:
				return validateUnitTest((UnitTest)value, diagnostics, context);
			case RetroPackage.PROJECT:
				return validateProject((Project)value, diagnostics, context);
			case RetroPackage.ACCEPTANCE_TEST:
				return validateAcceptanceTest((AcceptanceTest)value, diagnostics, context);
			case RetroPackage.PRODUCT:
				return validateProduct((Product)value, diagnostics, context);
			case RetroPackage.INTERACTION:
				return validateInteraction((Interaction)value, diagnostics, context);
			case RetroPackage.CATEGORY:
				return validateCategory((Category)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateBundle(Bundle bundle, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)bundle, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateNameSpace(NameSpace nameSpace, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)nameSpace, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateDevelopperFeature(DevelopperFeature developperFeature,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)developperFeature, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEndUserFeature(EndUserFeature endUserFeature, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)endUserFeature, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateFeature(Feature feature, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)feature, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAPI(API api, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)api, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateUnitTest(UnitTest unitTest, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)unitTest, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateProject(Project project, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)project, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAcceptanceTest(AcceptanceTest acceptanceTest, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment((EObject)acceptanceTest, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms((EObject)acceptanceTest, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms((EObject)acceptanceTest, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained((EObject)acceptanceTest, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired((EObject)acceptanceTest, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves((EObject)acceptanceTest, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID((EObject)acceptanceTest, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique((EObject)acceptanceTest, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique((EObject)acceptanceTest, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateAcceptanceTest_IsValidAcceptanceTest(acceptanceTest, diagnostics, context);
		return result;
	}

	/**
	 * Validates the IsValidAcceptanceTest constraint of '<em>Acceptance Test</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated-not
	 */
	public boolean validateAcceptanceTest_IsValidAcceptanceTest(AcceptanceTest acceptanceTest,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		// Ends with the "Test" suffix
		if (acceptanceTest.getSwtBotClassName() == null
				|| !acceptanceTest.getSwtBotClassName().endsWith("Test")) {
			diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING, RetroPlugin.ID, Diagnostic.WARNING,
					"Wrong name for this Acceptance test : should end with 'Test' suffix", new Object[0]));
		}

		// Is located in an "acceptance" package
		if (acceptanceTest.getPackage() == null || !acceptanceTest.getPackage().contains("acceptance")) {
			diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING, RetroPlugin.ID, Diagnostic.WARNING,
					"Wrong package for this Acceptance test : should be located in a 'acceptance' package",
					new Object[0]));
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateProduct(Product product, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)product, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateInteraction(Interaction interaction, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)interaction, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCategory(Category category, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)category, diagnostics, context);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} // RetroValidator
