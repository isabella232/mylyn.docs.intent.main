/**
 */
package org.eclipse.mylyn.docs.intent.core.compiler.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Change Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ReferenceChangeStatusImpl#getCompiledTargetURIFragment <em>Compiled Target URI Fragment</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.impl.ReferenceChangeStatusImpl#getWorkingCopyTargetURIFragment <em>Working Copy Target URI Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceChangeStatusImpl extends StructuralFeatureChangeStatusImpl implements ReferenceChangeStatus {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceChangeStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompilerPackage.Literals.REFERENCE_CHANGE_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCompiledTargetURIFragment() {
		return (String)eGet(CompilerPackage.Literals.REFERENCE_CHANGE_STATUS__COMPILED_TARGET_URI_FRAGMENT,
				true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompiledTargetURIFragment(String newCompiledTargetURIFragment) {
		eSet(CompilerPackage.Literals.REFERENCE_CHANGE_STATUS__COMPILED_TARGET_URI_FRAGMENT,
				newCompiledTargetURIFragment);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkingCopyTargetURIFragment() {
		return (String)eGet(
				CompilerPackage.Literals.REFERENCE_CHANGE_STATUS__WORKING_COPY_TARGET_URI_FRAGMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingCopyTargetURIFragment(String newWorkingCopyTargetURIFragment) {
		eSet(CompilerPackage.Literals.REFERENCE_CHANGE_STATUS__WORKING_COPY_TARGET_URI_FRAGMENT,
				newWorkingCopyTargetURIFragment);
	}

} //ReferenceChangeStatusImpl
