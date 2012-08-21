/**
 */
package org.eclipse.mylyn.docs.intent.core.compiler.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.ResourceChangeStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Change Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ResourceChangeStatusImpl#getWorkingCopyResourceState <em>Working Copy Resource State</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ResourceChangeStatusImpl#getCompiledResourceState <em>Compiled Resource State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceChangeStatusImpl extends SynchronizerCompilationStatusImpl implements ResourceChangeStatus {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceChangeStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompilerPackage.Literals.RESOURCE_CHANGE_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronizerResourceState getWorkingCopyResourceState() {
		return (SynchronizerResourceState)eGet(
				CompilerPackage.Literals.RESOURCE_CHANGE_STATUS__WORKING_COPY_RESOURCE_STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingCopyResourceState(SynchronizerResourceState newWorkingCopyResourceState) {
		eSet(CompilerPackage.Literals.RESOURCE_CHANGE_STATUS__WORKING_COPY_RESOURCE_STATE,
				newWorkingCopyResourceState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronizerResourceState getCompiledResourceState() {
		return (SynchronizerResourceState)eGet(
				CompilerPackage.Literals.RESOURCE_CHANGE_STATUS__COMPILED_RESOURCE_STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompiledResourceState(SynchronizerResourceState newCompiledResourceState) {
		eSet(CompilerPackage.Literals.RESOURCE_CHANGE_STATUS__COMPILED_RESOURCE_STATE,
				newCompiledResourceState);
	}

} //ResourceChangeStatusImpl
