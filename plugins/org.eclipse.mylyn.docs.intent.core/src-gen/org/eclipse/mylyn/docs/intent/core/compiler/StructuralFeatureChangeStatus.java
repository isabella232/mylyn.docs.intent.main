/**
 */
package org.eclipse.mylyn.docs.intent.core.compiler;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structural Feature Change Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus#getChangeState <em>Change State</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus#getFeatureName <em>Feature Name</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus#getCompiledElementURIFragment <em>Compiled Element URI Fragment</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus#getWorkingCopyElementURIFragment <em>Working Copy Element URI Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getStructuralFeatureChangeStatus()
 * @model abstract="true"
 * @generated
 */
public interface StructuralFeatureChangeStatus extends SynchronizerCompilationStatus {
	/**
	 * Returns the value of the '<em><b>Change State</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Change State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Change State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState
	 * @see #setChangeState(SynchronizerChangeState)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getStructuralFeatureChangeStatus_ChangeState()
	 * @model
	 * @generated
	 */
	SynchronizerChangeState getChangeState();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus#getChangeState <em>Change State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerChangeState
	 * @see #getChangeState()
	 * @generated
	 */
	void setChangeState(SynchronizerChangeState value);

	/**
	 * Returns the value of the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Name</em>' attribute.
	 * @see #setFeatureName(String)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getStructuralFeatureChangeStatus_FeatureName()
	 * @model
	 * @generated
	 */
	String getFeatureName();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus#getFeatureName <em>Feature Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Name</em>' attribute.
	 * @see #getFeatureName()
	 * @generated
	 */
	void setFeatureName(String value);

	/**
	 * Returns the value of the '<em><b>Compiled Element URI Fragment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compiled Element URI Fragment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compiled Element URI Fragment</em>' attribute.
	 * @see #setCompiledElementURIFragment(String)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getStructuralFeatureChangeStatus_CompiledElementURIFragment()
	 * @model
	 * @generated
	 */
	String getCompiledElementURIFragment();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus#getCompiledElementURIFragment <em>Compiled Element URI Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compiled Element URI Fragment</em>' attribute.
	 * @see #getCompiledElementURIFragment()
	 * @generated
	 */
	void setCompiledElementURIFragment(String value);

	/**
	 * Returns the value of the '<em><b>Working Copy Element URI Fragment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Working Copy Element URI Fragment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Copy Element URI Fragment</em>' attribute.
	 * @see #setWorkingCopyElementURIFragment(String)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getStructuralFeatureChangeStatus_WorkingCopyElementURIFragment()
	 * @model
	 * @generated
	 */
	String getWorkingCopyElementURIFragment();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.StructuralFeatureChangeStatus#getWorkingCopyElementURIFragment <em>Working Copy Element URI Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Copy Element URI Fragment</em>' attribute.
	 * @see #getWorkingCopyElementURIFragment()
	 * @generated
	 */
	void setWorkingCopyElementURIFragment(String value);

} // StructuralFeatureChangeStatus
