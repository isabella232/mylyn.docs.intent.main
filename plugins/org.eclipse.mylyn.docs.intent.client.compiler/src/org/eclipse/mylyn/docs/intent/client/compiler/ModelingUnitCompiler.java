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
package org.eclipse.mylyn.docs.intent.client.compiler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.AbstractRuntimeCompilationException;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.CompilationErrorType;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.CompilationException;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.InvalidReferenceException;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.InvalidValueException;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.PackageNotFoundResolveException;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.PackageRegistrationException;
import org.eclipse.mylyn.docs.intent.client.compiler.errors.ResolveException;
import org.eclipse.mylyn.docs.intent.client.compiler.generator.modelgeneration.ModelingUnitGenerator;
import org.eclipse.mylyn.docs.intent.client.compiler.generator.modelgeneration.StructuralFeatureGenerator;
import org.eclipse.mylyn.docs.intent.client.compiler.generator.modellinking.ModelingUnitLinkResolver;
import org.eclipse.mylyn.docs.intent.client.compiler.utils.IntentCompilerInformationHolder;
import org.eclipse.mylyn.docs.intent.client.compiler.validator.GeneratedElementValidator;
import org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedContributionHolder;
import org.eclipse.mylyn.docs.intent.core.compiler.UnresolvedReferenceHolder;
import org.eclipse.mylyn.docs.intent.core.genericunit.UnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ResourceDeclaration;

/**
 * Modeling Unit Compiler : generate the elements described in modeling units and register those elements in
 * an information Handler ; also in charged of handling compilation errors if the described models are
 * incorrect.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ModelingUnitCompiler {

	/**
	 * Compilation mode : compiling modeling units defining EPackages only.
	 */
	private static final boolean EPACKAGE_DECLARATION_ONLY = true;

	/**
	 * Compilation mode : compiling all the other modeling units (mutual exclusion with the previous mode).
	 */
	private static final boolean ALL_MODELING_UNITS_EXCEPT_EPACKAGES_DECLARATION = false;

	/**
	 * The ModelingUnitGenerator used to generate the element described in one
	 * org.eclipse.mylyn.docs.intent.core.modelingunit.
	 */
	private final ModelingUnitGenerator modelingUnitGenerator;

	/**
	 * The entity used to hold informations about compilation.
	 */
	private final IntentCompilerInformationHolder informationHolder;

	/**
	 * The linkResolver used to resolved the links in modelingUnits.
	 */
	private final ModelingUnitLinkResolver linkResolver;

	/**
	 * The repository registry containing the modeling units to compile (and define available EPackages).
	 */
	private EPackage.Registry packageRegistry;

	/**
	 * The progressMonitor to use for compilation ; if canceled, the compilation will stop immediately.
	 */
	private Monitor progressMonitor;

	/**
	 * ModelingUnitCompiler constructor.
	 * 
	 * @param packageRegistry
	 *            the repository package registry
	 * @param linkResolver
	 *            the linkResolver used to resolved the links in modelingUnits
	 * @param informationHolder
	 *            the entity used to hold informations about compilation
	 * @param progressMonitor
	 *            the progressMonitor to use for compilation
	 */
	public ModelingUnitCompiler(EPackage.Registry packageRegistry, ModelingUnitLinkResolver linkResolver,
			IntentCompilerInformationHolder informationHolder, Monitor progressMonitor) {
		this.packageRegistry = packageRegistry;
		this.informationHolder = informationHolder;
		this.modelingUnitGenerator = new ModelingUnitGenerator(linkResolver, informationHolder,
				progressMonitor);
		this.linkResolver = linkResolver;
		this.progressMonitor = progressMonitor;
	}

	/**
	 * Compile the given modelingUnits, compiling EPackages declaration first, and then other elements.
	 * 
	 * @param modelingUnits
	 *            the modeling units to compile
	 * @return the informationHolder containing all the needed informations (generated elements, errors,
	 *         mapping to resources).
	 */
	public IntentCompilerInformationHolder compile(List<ModelingUnit> modelingUnits) {
		if (!progressMonitor.isCanceled()) {
			// Step 0 : initialization of the information Holder
			informationHolder.initialize();
		}

		if (!progressMonitor.isCanceled()) {
			// Step 1 : compiling modeling units defining EPackages
			compileAllWithMode(modelingUnits, EPACKAGE_DECLARATION_ONLY);
		}

		if (!progressMonitor.isCanceled()) {
			// Step 2 : compiling all the other modeling units
			compileAllWithMode(modelingUnits, ALL_MODELING_UNITS_EXCEPT_EPACKAGES_DECLARATION);
		}

		if (!progressMonitor.isCanceled()) {
			// Step 3 : handle the unresolved contribution instructions
			for (String unresolvedName : informationHolder.getAllUnresolvedContributionsNames()) {
				// For each contribution instruction, we generate a compilationStatus
				for (UnresolvedContributionHolder unresolvedContributionHolder : informationHolder
						.getUnresolvedContributions(unresolvedName)) {
					if (!unresolvedContributionHolder.isResolved()) {
						informationHolder
								.registerCompilationExceptionAsCompilationStatus(new CompilationException(
										unresolvedContributionHolder.getReferencedContribution(),
										CompilationErrorType.INVALID_REFERENCE_ERROR,
										"The element "
												+ unresolvedName
												+ " cannot be resolved. This contribution instruction will be ignored. "));
					}
				}
			}
		}
		return IntentCompilerInformationHolder.getInstance();
	}

	/**
	 * Compile the given modeling units and register the generated objects into the informationHolder.
	 * 
	 * @param modelingUnits
	 *            the modelingUnits to compile
	 * @param generateOnlyEPackages
	 *            indicates if we only consider EPackages
	 */
	protected void compileAllWithMode(List<ModelingUnit> modelingUnits, boolean generateOnlyEPackages) {
		// Step 1 : initialization.

		modelingUnitGenerator.clearResourceDeclarations();

		// Step 2.1 : Compilation of each org.eclipse.mylyn.docs.intent.core.modelingunit contained in the
		// list (without resolving links)
		for (ModelingUnit modelingUnitToCompile : modelingUnits) {
			if (!progressMonitor.isCanceled()) {
				this.compileModelingUnit(modelingUnitToCompile, generateOnlyEPackages);
			}
		}

		// Step 2.3 : link Resolving
		if (!progressMonitor.isCanceled()) {
			resolveLinks();
		}

		// Step 2.4 : we associate each generated object in the given resource
		if (!progressMonitor.isCanceled()) {
			if (!generateOnlyEPackages) {
				mapResourceDeclarationToGeneratedObjects();
			}
		}
		// Step 2.5 : Validation
		if (!progressMonitor.isCanceled()) {
			validateGeneratedElements(generateOnlyEPackages);
		}
		// TODO Handle compilation Time.

	}

	/**
	 * Maps the resource declarations detected by the generator to the generatedElements ; if invalid
	 * reference are found, register those errors as compilationStatus.
	 */
	protected void mapResourceDeclarationToGeneratedObjects() {

		// For each declared resource
		List<ResourceDeclaration> resourcesDeclarations = modelingUnitGenerator.getResourceDeclarations();
		for (ResourceDeclaration resource : resourcesDeclarations) {
			if (resource.getContent().isEmpty()) {
				informationHolder.addResource(resource);
			}
			// For each reference to a generated Object
			for (ModelingUnitInstructionReference newContainedElementRefrence : resource.getContent()) {
				// We resolve this reference
				try {
					EObject newContainedElement = linkResolver.resolveReferenceInElementList(resource, null,
							newContainedElementRefrence.getIntentHref());
					// and add it to the resource mapping of the informationHolder
					informationHolder.addResourceToGeneratedElementMapping(resource, newContainedElement);
				} catch (InvalidReferenceException e) {
					// If the reference cannot be resolved, we register a new compilation status.
					informationHolder
							.registerCompilationExceptionAsCompilationStatus(new CompilationException(
									resource, CompilationErrorType.INVALID_REFERENCE_ERROR, e.getMessage()));
				}
			}
		}

	}

	/**
	 * Generates and return the elements described in the given modeling Unit, and register errors in the
	 * described model as compilationStatus.
	 * 
	 * @param modelingUnitToCompile
	 *            the modeling Unit to inspect
	 * @param generateOnlyEPackages
	 *            indicates if we only consider EPackages
	 * @return a list containing the elements described in the given modeling Unit
	 */
	protected List<EObject> compileModelingUnit(ModelingUnit modelingUnitToCompile,
			boolean generateOnlyEPackages) {

		List<EObject> generatedObjects = new ArrayList<EObject>();
		AbstractRuntimeCompilationException thrownException = null;
		CompilationErrorType compilationErrorType = null;
		informationHolder.setCurrentImportedPackages(getImportedPackages(modelingUnitToCompile,
				generateOnlyEPackages));

		try {
			modelingUnitGenerator.setGenerateOnlyEPackages(generateOnlyEPackages);
			modelingUnitGenerator.generate(modelingUnitToCompile);
			return generatedObjects;
		} catch (ResolveException e) {
			thrownException = e;
		} catch (PackageNotFoundResolveException e) {
			thrownException = e;
			compilationErrorType = CompilationErrorType.PACKAGE_NOT_FOUND_ERROR;
		} catch (PackageRegistrationException e) {
			thrownException = e;
			compilationErrorType = CompilationErrorType.PACKAGE_REGISTRATION_ERROR;
		} catch (InvalidReferenceException e) {
			thrownException = e;
			compilationErrorType = CompilationErrorType.INVALID_REFERENCE_ERROR;
		} catch (InvalidValueException e) {
			thrownException = e;
			compilationErrorType = CompilationErrorType.INVALID_VALUE_ERROR;
		}
		CompilationException compilationException = new CompilationException(
				thrownException.getInvalidInstruction(), compilationErrorType, thrownException.getMessage());
		compilationException.setStackTrace(thrownException.getStackTrace());

		return generatedObjects;
	}

	/**
	 * Returns the packages imported by the given modelingUnit.
	 * 
	 * @param modelingUnitToCompile
	 *            the modelingUnit to inspect
	 * @param generateOnlyEPackages
	 *            indicates if the compiler is currently generating EPackages only
	 * @return the packages imported by the given modelingUnit
	 */
	protected List<String> getImportedPackages(ModelingUnit modelingUnitToCompile,
			boolean generateOnlyEPackages) {
		// TODO define a priority between EPackages to consider
		List<String> importedPackages = new ArrayList<String>();
		importedPackages.add(EcorePackage.eINSTANCE.getNsURI());
		for (String ePackage : packageRegistry.keySet()) {
			importedPackages.add(ePackage);
		}
		return importedPackages;
	}

	/**
	 * Resolves the unresolvedReference (registered in the InformationHolder) using a linkResolver.
	 */
	protected void resolveLinks() {

		for (EObject elementContainingUnresolvedReference : informationHolder.getCurrentCreatedElements()) {

			for (Iterator<UnresolvedReferenceHolder> iterator = informationHolder
					.getUnresolvedReferencesByGeneratedElement(elementContainingUnresolvedReference)
					.iterator(); iterator.hasNext();) {
				UnresolvedReferenceHolder referenceHolder = iterator.next();

				// This list will contains the resolved value of the reference
				List<Object> referenceValue = new ArrayList<Object>();
				try {
					try {

						EObject referencedElement = linkResolver.resolveReferenceInElementList(
								referenceHolder.getInstructionContainer(),
								// should be referenceHolder.getConcernedFeature().eClass()
								null, referenceHolder.getTextualReference());
						referenceValue.add(referencedElement);

					} catch (InvalidReferenceException e) {
						// If the link to resolve is not an instance,
						// we can use the imported package to resolve it
						referenceValue.add(linkResolver.resolveEClassifierUsingPackage(
								referenceHolder.getInstructionContainer(),
								informationHolder.getCurrentImportedPackages(),
								referenceHolder.getTextualReference()));
					}

					StructuralFeatureGenerator.setFeatureValueInElement(
							referenceHolder.getInstructionContainer(), elementContainingUnresolvedReference,
							referenceHolder.getConcernedFeature(), referenceValue);
				} catch (InvalidValueException e) {
					// If the reference cannot be resolved with both ways
					// we register a compilation status
					informationHolder
							.registerCompilationExceptionAsCompilationStatus(new CompilationException(e
									.getInvalidInstruction(), CompilationErrorType.INVALID_REFERENCE_ERROR, e
									.getMessage()));
					iterator.remove();
				} catch (ResolveException e) {
					// If the reference cannot be resolved with both ways
					// we register a compilation status
					informationHolder
							.registerCompilationExceptionAsCompilationStatus(new CompilationException(e
									.getInvalidInstruction(), CompilationErrorType.INVALID_REFERENCE_ERROR, e
									.getMessage()));
					iterator.remove();
				}
			}
		}
	}

	/**
	 * Validate the generated Elements and create a Compilation Status if the generation Failed.
	 * 
	 * @param validateOnlyEPackages
	 *            if true, only EPackages are validated / registered. Otherwise EPackages are ignored
	 */
	private void validateGeneratedElements(boolean validateOnlyEPackages) {
		for (EObject generatedElement : informationHolder.getCurrentCreatedElements()) {
			if (validateOnlyEPackages && generatedElement instanceof EPackage) {
				EPackage ePackage = (EPackage)generatedElement;
				UnitInstruction instanciation = informationHolder
						.getInstanciationInstructionByCreatedElement(generatedElement);
				if (!validateGeneratedElement(generatedElement)) {
					linkResolver.registerAsInvalidPackage(instanciation, ePackage);
				} else {
					linkResolver.registerInPackageRegistry(instanciation, ePackage);
				}
			} else if (!(generatedElement instanceof EPackage)) {
				validateGeneratedElement(generatedElement);
			}
		}
	}

	/**
	 * Validate the generated Elements and create a Compilation Status if the generation Failed.
	 * 
	 * @param generatedElement
	 *            the element to validate
	 * @return true if the element is valid
	 */
	private boolean validateGeneratedElement(EObject generatedElement) {
		UnitInstruction instanciation = informationHolder
				.getInstanciationInstructionByCreatedElement(generatedElement);
		GeneratedElementValidator validator = new GeneratedElementValidator(instanciation, generatedElement);
		Diagnostic diagnostic;
		boolean hasErrors = false;
		try {
			diagnostic = validator.validate();
			informationHolder.registerDiagnosticAsCompilationStatusList(generatedElement, diagnostic);
		} catch (CompilationException e) {
			informationHolder.registerCompilationExceptionAsCompilationStatus(e);
			hasErrors = true;
		}
		return !hasErrors;
	}

}
