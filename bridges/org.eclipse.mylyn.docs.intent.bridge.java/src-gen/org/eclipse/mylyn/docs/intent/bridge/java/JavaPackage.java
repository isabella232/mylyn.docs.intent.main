/**
 * Copyright (c) 2010, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.mylyn.docs.intent.bridge.java;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.mylyn.docs.intent.bridge.java.JavaFactory
 * @model kind="package"
 * @generated
 */
public interface JavaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "java";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/intent/bridges/java/0.8";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "java";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JavaPackage eINSTANCE = org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.JavadocImpl <em>Javadoc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavadocImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getJavadoc()
	 * @generated
	 */
	int JAVADOC = 0;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC__CONTENT = 0;

	/**
	 * The number of structural features of the '<em>Javadoc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.DocumentedElementImpl <em>Documented Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.DocumentedElementImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getDocumentedElement()
	 * @generated
	 */
	int DOCUMENTED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_ELEMENT__JAVADOC = 0;

	/**
	 * The number of structural features of the '<em>Documented Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.NamedElementImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__JAVADOC = DOCUMENTED_ELEMENT__JAVADOC;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.VisibleElementImpl <em>Visible Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.VisibleElementImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getVisibleElement()
	 * @generated
	 */
	int VISIBLE_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_ELEMENT__JAVADOC = NAMED_ELEMENT__JAVADOC;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_ELEMENT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_ELEMENT__VISIBILITY = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_ELEMENT__STATIC = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_ELEMENT__FINAL = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Visible Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_ELEMENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.AbstractCapableElementImpl <em>Abstract Capable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.AbstractCapableElementImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getAbstractCapableElement()
	 * @generated
	 */
	int ABSTRACT_CAPABLE_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CAPABLE_ELEMENT__JAVADOC = VISIBLE_ELEMENT__JAVADOC;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CAPABLE_ELEMENT__NAME = VISIBLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CAPABLE_ELEMENT__VISIBILITY = VISIBLE_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CAPABLE_ELEMENT__STATIC = VISIBLE_ELEMENT__STATIC;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CAPABLE_ELEMENT__FINAL = VISIBLE_ELEMENT__FINAL;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CAPABLE_ELEMENT__ABSTRACT = VISIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Capable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT = VISIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.FieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.FieldImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getField()
	 * @generated
	 */
	int FIELD = 5;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__JAVADOC = VISIBLE_ELEMENT__JAVADOC;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__NAME = VISIBLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__VISIBILITY = VISIBLE_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__STATIC = VISIBLE_ELEMENT__STATIC;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__FINAL = VISIBLE_ELEMENT__FINAL;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__TYPE = VISIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_FEATURE_COUNT = VISIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.ClassifierImpl <em>Classifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.ClassifierImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getClassifier()
	 * @generated
	 */
	int CLASSIFIER = 6;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__JAVADOC = ABSTRACT_CAPABLE_ELEMENT__JAVADOC;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__NAME = ABSTRACT_CAPABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__VISIBILITY = ABSTRACT_CAPABLE_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__STATIC = ABSTRACT_CAPABLE_ELEMENT__STATIC;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__FINAL = ABSTRACT_CAPABLE_ELEMENT__FINAL;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__ABSTRACT = ABSTRACT_CAPABLE_ELEMENT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__KIND = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__EXTENDS = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Implements</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__IMPLEMENTS = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__FIELDS = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__METHODS = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Classifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_COUNT = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.MethodImpl <em>Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.MethodImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getMethod()
	 * @generated
	 */
	int METHOD = 7;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__JAVADOC = ABSTRACT_CAPABLE_ELEMENT__JAVADOC;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__NAME = ABSTRACT_CAPABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__VISIBILITY = ABSTRACT_CAPABLE_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__STATIC = ABSTRACT_CAPABLE_ELEMENT__STATIC;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__FINAL = ABSTRACT_CAPABLE_ELEMENT__FINAL;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__ABSTRACT = ABSTRACT_CAPABLE_ELEMENT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Simple Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__SIMPLE_NAME = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__RETURN_TYPE = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__PARAMETERS = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__CONTENT = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Exceptions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__EXCEPTIONS = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_FEATURE_COUNT = ABSTRACT_CAPABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.ConstructorImpl <em>Constructor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.ConstructorImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getConstructor()
	 * @generated
	 */
	int CONSTRUCTOR = 8;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__JAVADOC = METHOD__JAVADOC;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__NAME = METHOD__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__VISIBILITY = METHOD__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__STATIC = METHOD__STATIC;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__FINAL = METHOD__FINAL;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__ABSTRACT = METHOD__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Simple Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__SIMPLE_NAME = METHOD__SIMPLE_NAME;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__RETURN_TYPE = METHOD__RETURN_TYPE;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__PARAMETERS = METHOD__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__CONTENT = METHOD__CONTENT;

	/**
	 * The feature id for the '<em><b>Exceptions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR__EXCEPTIONS = METHOD__EXCEPTIONS;

	/**
	 * The number of structural features of the '<em>Constructor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR_FEATURE_COUNT = METHOD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.ParameterImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 9;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__JAVADOC = NAMED_ELEMENT__JAVADOC;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TYPE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind <em>Visibility Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getVisibilityKind()
	 * @generated
	 */
	int VISIBILITY_KIND = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind <em>Classifier Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getClassifierKind()
	 * @generated
	 */
	int CLASSIFIER_KIND = 11;


	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.Javadoc <em>Javadoc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Javadoc</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Javadoc
	 * @generated
	 */
	EClass getJavadoc();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.Javadoc#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Javadoc#getContent()
	 * @see #getJavadoc()
	 * @generated
	 */
	EAttribute getJavadoc_Content();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.DocumentedElement <em>Documented Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Element</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.DocumentedElement
	 * @generated
	 */
	EClass getDocumentedElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.mylyn.docs.intent.bridge.java.DocumentedElement#getJavadoc <em>Javadoc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Javadoc</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.DocumentedElement#getJavadoc()
	 * @see #getDocumentedElement()
	 * @generated
	 */
	EReference getDocumentedElement_Javadoc();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement <em>Visible Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Visible Element</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement
	 * @generated
	 */
	EClass getVisibleElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visibility</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#getVisibility()
	 * @see #getVisibleElement()
	 * @generated
	 */
	EAttribute getVisibleElement_Visibility();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#isStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#isStatic()
	 * @see #getVisibleElement()
	 * @generated
	 */
	EAttribute getVisibleElement_Static();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#isFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement#isFinal()
	 * @see #getVisibleElement()
	 * @generated
	 */
	EAttribute getVisibleElement_Final();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.AbstractCapableElement <em>Abstract Capable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Capable Element</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.AbstractCapableElement
	 * @generated
	 */
	EClass getAbstractCapableElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.AbstractCapableElement#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.AbstractCapableElement#isAbstract()
	 * @see #getAbstractCapableElement()
	 * @generated
	 */
	EAttribute getAbstractCapableElement_Abstract();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Field
	 * @generated
	 */
	EClass getField();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.Field#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Field#getType()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Classifier</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Classifier
	 * @generated
	 */
	EClass getClassifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getKind()
	 * @see #getClassifier()
	 * @generated
	 */
	EAttribute getClassifier_Kind();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getFields()
	 * @see #getClassifier()
	 * @generated
	 */
	EReference getClassifier_Fields();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Methods</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getMethods()
	 * @see #getClassifier()
	 * @generated
	 */
	EReference getClassifier_Methods();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extends</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getExtends()
	 * @see #getClassifier()
	 * @generated
	 */
	EAttribute getClassifier_Extends();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getImplements <em>Implements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Implements</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Classifier#getImplements()
	 * @see #getClassifier()
	 * @generated
	 */
	EAttribute getClassifier_Implements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.Method <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Method
	 * @generated
	 */
	EClass getMethod();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.Method#getSimpleName <em>Simple Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Simple Name</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Method#getSimpleName()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_SimpleName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.Method#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Type</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Method#getReturnType()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_ReturnType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.bridge.java.Method#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Method#getParameters()
	 * @see #getMethod()
	 * @generated
	 */
	EReference getMethod_Parameters();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.Method#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Method#getContent()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Content();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.mylyn.docs.intent.bridge.java.Method#getExceptions <em>Exceptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Exceptions</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Method#getExceptions()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Exceptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.Constructor <em>Constructor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constructor</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Constructor
	 * @generated
	 */
	EClass getConstructor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.java.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.java.Parameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.Parameter#getType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Type();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind <em>Visibility Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Visibility Kind</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind
	 * @generated
	 */
	EEnum getVisibilityKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind <em>Classifier Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Classifier Kind</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind
	 * @generated
	 */
	EEnum getClassifierKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	JavaFactory getJavaFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.JavadocImpl <em>Javadoc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavadocImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getJavadoc()
		 * @generated
		 */
		EClass JAVADOC = eINSTANCE.getJavadoc();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVADOC__CONTENT = eINSTANCE.getJavadoc_Content();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.DocumentedElementImpl <em>Documented Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.DocumentedElementImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getDocumentedElement()
		 * @generated
		 */
		EClass DOCUMENTED_ELEMENT = eINSTANCE.getDocumentedElement();

		/**
		 * The meta object literal for the '<em><b>Javadoc</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENTED_ELEMENT__JAVADOC = eINSTANCE.getDocumentedElement_Javadoc();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.NamedElementImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.VisibleElementImpl <em>Visible Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.VisibleElementImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getVisibleElement()
		 * @generated
		 */
		EClass VISIBLE_ELEMENT = eINSTANCE.getVisibleElement();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VISIBLE_ELEMENT__VISIBILITY = eINSTANCE.getVisibleElement_Visibility();

		/**
		 * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VISIBLE_ELEMENT__STATIC = eINSTANCE.getVisibleElement_Static();

		/**
		 * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VISIBLE_ELEMENT__FINAL = eINSTANCE.getVisibleElement_Final();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.AbstractCapableElementImpl <em>Abstract Capable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.AbstractCapableElementImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getAbstractCapableElement()
		 * @generated
		 */
		EClass ABSTRACT_CAPABLE_ELEMENT = eINSTANCE.getAbstractCapableElement();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CAPABLE_ELEMENT__ABSTRACT = eINSTANCE.getAbstractCapableElement_Abstract();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.FieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.FieldImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getField()
		 * @generated
		 */
		EClass FIELD = eINSTANCE.getField();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__TYPE = eINSTANCE.getField_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.ClassifierImpl <em>Classifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.ClassifierImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getClassifier()
		 * @generated
		 */
		EClass CLASSIFIER = eINSTANCE.getClassifier();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASSIFIER__KIND = eINSTANCE.getClassifier_Kind();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER__FIELDS = eINSTANCE.getClassifier_Fields();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER__METHODS = eINSTANCE.getClassifier_Methods();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASSIFIER__EXTENDS = eINSTANCE.getClassifier_Extends();

		/**
		 * The meta object literal for the '<em><b>Implements</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASSIFIER__IMPLEMENTS = eINSTANCE.getClassifier_Implements();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.MethodImpl <em>Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.MethodImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getMethod()
		 * @generated
		 */
		EClass METHOD = eINSTANCE.getMethod();

		/**
		 * The meta object literal for the '<em><b>Simple Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD__SIMPLE_NAME = eINSTANCE.getMethod_SimpleName();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD__RETURN_TYPE = eINSTANCE.getMethod_ReturnType();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD__PARAMETERS = eINSTANCE.getMethod_Parameters();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD__CONTENT = eINSTANCE.getMethod_Content();

		/**
		 * The meta object literal for the '<em><b>Exceptions</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD__EXCEPTIONS = eINSTANCE.getMethod_Exceptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.ConstructorImpl <em>Constructor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.ConstructorImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getConstructor()
		 * @generated
		 */
		EClass CONSTRUCTOR = eINSTANCE.getConstructor();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.ParameterImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__TYPE = eINSTANCE.getParameter_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind <em>Visibility Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getVisibilityKind()
		 * @generated
		 */
		EEnum VISIBILITY_KIND = eINSTANCE.getVisibilityKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind <em>Classifier Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind
		 * @see org.eclipse.mylyn.docs.intent.bridge.java.impl.JavaPackageImpl#getClassifierKind()
		 * @generated
		 */
		EEnum CLASSIFIER_KIND = eINSTANCE.getClassifierKind();

	}

} //JavaPackage
