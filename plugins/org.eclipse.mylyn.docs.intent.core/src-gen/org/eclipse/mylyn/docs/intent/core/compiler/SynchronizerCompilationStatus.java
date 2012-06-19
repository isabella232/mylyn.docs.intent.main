/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.mylyn.docs.intent.core.compiler;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Synchronizer Compilation Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getWorkingCopyResourceURI <em>Working Copy Resource URI</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getCompiledResourceURI <em>Compiled Resource URI</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getWorkingCopyElementURIFragment <em>Working Copy Element URI Fragment</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getCompiledElementURIFragment <em>Compiled Element URI Fragment</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getWorkingCopyResourceState <em>Working Copy Resource State</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getCompiledResourceState <em>Compiled Resource State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getSynchronizerCompilationStatus()
 * @model
 * @generated
 */
public interface SynchronizerCompilationStatus extends CompilationStatus {
	/**
	 * Returns the value of the '<em><b>Working Copy Resource URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Working Copy Resource URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Copy Resource URI</em>' attribute.
	 * @see #setWorkingCopyResourceURI(String)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getSynchronizerCompilationStatus_WorkingCopyResourceURI()
	 * @model
	 * @generated
	 */
	String getWorkingCopyResourceURI();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getWorkingCopyResourceURI <em>Working Copy Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Copy Resource URI</em>' attribute.
	 * @see #getWorkingCopyResourceURI()
	 * @generated
	 */
	void setWorkingCopyResourceURI(String value);

	/**
	 * Returns the value of the '<em><b>Compiled Resource URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compiled Resource URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compiled Resource URI</em>' attribute.
	 * @see #setCompiledResourceURI(String)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getSynchronizerCompilationStatus_CompiledResourceURI()
	 * @model
	 * @generated
	 */
	String getCompiledResourceURI();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getCompiledResourceURI <em>Compiled Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compiled Resource URI</em>' attribute.
	 * @see #getCompiledResourceURI()
	 * @generated
	 */
	void setCompiledResourceURI(String value);

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
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getSynchronizerCompilationStatus_WorkingCopyElementURIFragment()
	 * @model
	 * @generated
	 */
	String getWorkingCopyElementURIFragment();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getWorkingCopyElementURIFragment <em>Working Copy Element URI Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Copy Element URI Fragment</em>' attribute.
	 * @see #getWorkingCopyElementURIFragment()
	 * @generated
	 */
	void setWorkingCopyElementURIFragment(String value);

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
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getSynchronizerCompilationStatus_CompiledElementURIFragment()
	 * @model
	 * @generated
	 */
	String getCompiledElementURIFragment();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getCompiledElementURIFragment <em>Compiled Element URI Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compiled Element URI Fragment</em>' attribute.
	 * @see #getCompiledElementURIFragment()
	 * @generated
	 */
	void setCompiledElementURIFragment(String value);

	/**
	 * Returns the value of the '<em><b>Working Copy Resource State</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Working Copy Resource State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Copy Resource State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState
	 * @see #setWorkingCopyResourceState(SynchronizerResourceState)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getSynchronizerCompilationStatus_WorkingCopyResourceState()
	 * @model
	 * @generated
	 */
	SynchronizerResourceState getWorkingCopyResourceState();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getWorkingCopyResourceState <em>Working Copy Resource State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Copy Resource State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState
	 * @see #getWorkingCopyResourceState()
	 * @generated
	 */
	void setWorkingCopyResourceState(SynchronizerResourceState value);

	/**
	 * Returns the value of the '<em><b>Compiled Resource State</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * The literals are from the enumeration {@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compiled Resource State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compiled Resource State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState
	 * @see #setCompiledResourceState(SynchronizerResourceState)
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage#getSynchronizerCompilationStatus_CompiledResourceState()
	 * @model default=""
	 * @generated
	 */
	SynchronizerResourceState getCompiledResourceState();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus#getCompiledResourceState <em>Compiled Resource State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compiled Resource State</em>' attribute.
	 * @see org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerResourceState
	 * @see #getCompiledResourceState()
	 * @generated
	 */
	void setCompiledResourceState(SynchronizerResourceState value);

} // SynchronizerCompilationStatus
