/**
 */
package org.eclipse.mylyn.docs.intent.export;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage
 * @generated
 */
public interface ExportFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExportFactory eINSTANCE = org.eclipse.mylyn.docs.intent.export.impl.ExportFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Intent Gen</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Intent Gen</em>'.
	 * @generated
	 */
	IntentGen createIntentGen();

	/**
	 * Returns a new object of class '<em>Latex Document</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Latex Document</em>'.
	 * @generated
	 */
	LatexDocument createLatexDocument();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExportPackage getExportPackage();

} //ExportFactory
