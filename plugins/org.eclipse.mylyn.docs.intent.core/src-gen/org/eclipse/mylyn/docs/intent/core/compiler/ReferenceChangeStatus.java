/**
 */
package org.eclipse.mylyn.docs.intent.core.compiler;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Change Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus#getCompiledTargetURIFragment <em>Compiled Target URI Fragment</em>}</li>
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
	 * Returns the value of the '<em><b>Compiled Target URI Fragment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compiled Target URI Fragment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compiled Target URI Fragment</em>' attribute.
	 * @see #setCompiledTargetURIFragment(String)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getReferenceChangeStatus_CompiledTargetURIFragment()
	 * @model
	 * @generated
	 */
	String getCompiledTargetURIFragment();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.ReferenceChangeStatus#getCompiledTargetURIFragment <em>Compiled Target URI Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compiled Target URI Fragment</em>' attribute.
	 * @see #getCompiledTargetURIFragment()
	 * @generated
	 */
	void setCompiledTargetURIFragment(String value);

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
