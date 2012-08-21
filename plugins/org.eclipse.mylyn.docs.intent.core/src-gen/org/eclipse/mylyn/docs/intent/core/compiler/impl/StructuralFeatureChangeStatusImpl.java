/**
 */
package org.eclipse.mylyn.docs.intent.core.compiler.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structural Feature Change Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.StructuralFeatureChangeStatusImpl#getChangeState <em>Change State</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.StructuralFeatureChangeStatusImpl#getFeatureName <em>Feature Name</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.StructuralFeatureChangeStatusImpl#getCompiledElement <em>Compiled Element</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.StructuralFeatureChangeStatusImpl#getWorkingCopyElementURIFragment <em>Working Copy Element URI Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class StructuralFeatureChangeStatusImpl extends SynchronizerCompilationStatusImpl implements StructuralFeatureChangeStatus {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructuralFeatureChangeStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronizerChangeState getChangeState() {
		return (SynchronizerChangeState)eGet(
				CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__CHANGE_STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeState(SynchronizerChangeState newChangeState) {
		eSet(CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__CHANGE_STATE, newChangeState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFeatureName() {
		return (String)eGet(CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__FEATURE_NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeatureName(String newFeatureName) {
		eSet(CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__FEATURE_NAME, newFeatureName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getCompiledElement() {
		return (EObject)eGet(CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__COMPILED_ELEMENT,
				true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompiledElement(EObject newCompiledElement) {
		eSet(CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__COMPILED_ELEMENT, newCompiledElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkingCopyElementURIFragment() {
		return (String)eGet(
				CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__WORKING_COPY_ELEMENT_URI_FRAGMENT,
				true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingCopyElementURIFragment(String newWorkingCopyElementURIFragment) {
		eSet(CompilerPackage.Literals.STRUCTURAL_FEATURE_CHANGE_STATUS__WORKING_COPY_ELEMENT_URI_FRAGMENT,
				newWorkingCopyElementURIFragment);
	}

} //StructuralFeatureChangeStatusImpl
