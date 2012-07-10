/**
 */
package org.eclipse.mylyn.docs.intent.core.compiler.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.ModelElementChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Element Change Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ModelElementChangeStatusImpl#getChangeState <em>Change State</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ModelElementChangeStatusImpl#getCompiledParentURIFragment <em>Compiled Parent URI Fragment</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ModelElementChangeStatusImpl#getWorkingCopyParentURIFragment <em>Working Copy Parent URI Fragment</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ModelElementChangeStatusImpl#getCompiledElementURIFragment <em>Compiled Element URI Fragment</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ModelElementChangeStatusImpl#getWorkingCopyElementURIFragment <em>Working Copy Element URI Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelElementChangeStatusImpl extends SynchronizerCompilationStatusImpl implements ModelElementChangeStatus {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelElementChangeStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronizerChangeState getChangeState() {
		return (SynchronizerChangeState)eGet(
				CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__CHANGE_STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeState(SynchronizerChangeState newChangeState) {
		eSet(CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__CHANGE_STATE, newChangeState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCompiledParentURIFragment() {
		return (String)eGet(
				CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__COMPILED_PARENT_URI_FRAGMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompiledParentURIFragment(String newCompiledParentURIFragment) {
		eSet(CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__COMPILED_PARENT_URI_FRAGMENT,
				newCompiledParentURIFragment);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkingCopyParentURIFragment() {
		return (String)eGet(
				CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__WORKING_COPY_PARENT_URI_FRAGMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingCopyParentURIFragment(String newWorkingCopyParentURIFragment) {
		eSet(CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__WORKING_COPY_PARENT_URI_FRAGMENT,
				newWorkingCopyParentURIFragment);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCompiledElementURIFragment() {
		return (String)eGet(
				CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__COMPILED_ELEMENT_URI_FRAGMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompiledElementURIFragment(String newCompiledElementURIFragment) {
		eSet(CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__COMPILED_ELEMENT_URI_FRAGMENT,
				newCompiledElementURIFragment);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkingCopyElementURIFragment() {
		return (String)eGet(
				CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__WORKING_COPY_ELEMENT_URI_FRAGMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingCopyElementURIFragment(String newWorkingCopyElementURIFragment) {
		eSet(CompilerPackage.Literals.MODEL_ELEMENT_CHANGE_STATUS__WORKING_COPY_ELEMENT_URI_FRAGMENT,
				newWorkingCopyElementURIFragment);
	}

} //ModelElementChangeStatusImpl
