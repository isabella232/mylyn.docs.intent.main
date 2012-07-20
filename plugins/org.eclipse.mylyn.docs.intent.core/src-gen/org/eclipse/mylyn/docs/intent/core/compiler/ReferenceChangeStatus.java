/**
 */
package org.eclipse.mylyn.docs.intent.core.compiler;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Change Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus#getCompiledTarget <em>Compiled Target</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus#getWorkingCopyTargetURIFragment <em>Working Copy Target URI Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getReferenceChangeStatus()
 * @model
 * @generated
 */
public interface ReferenceChangeStatus extends StructuralFeatureChangeStatus {
	/**
	 * Returns the value of the '<em><b>Compiled Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compiled Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compiled Target</em>' reference.
	 * @see #setCompiledTarget(EObject)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getReferenceChangeStatus_CompiledTarget()
	 * @model
	 * @generated
	 */
	EObject getCompiledTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus#getCompiledTarget <em>Compiled Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compiled Target</em>' reference.
	 * @see #getCompiledTarget()
	 * @generated
	 */
	void setCompiledTarget(EObject value);

	/**
	 * Returns the value of the '<em><b>Working Copy Target URI Fragment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Working Copy Target URI Fragment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Copy Target URI Fragment</em>' attribute.
	 * @see #setWorkingCopyTargetURIFragment(String)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getReferenceChangeStatus_WorkingCopyTargetURIFragment()
	 * @model
	 * @generated
	 */
	String getWorkingCopyTargetURIFragment();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus#getWorkingCopyTargetURIFragment <em>Working Copy Target URI Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Copy Target URI Fragment</em>' attribute.
	 * @see #getWorkingCopyTargetURIFragment()
	 * @generated
	 */
	void setWorkingCopyTargetURIFragment(String value);

} // ReferenceChangeStatus
