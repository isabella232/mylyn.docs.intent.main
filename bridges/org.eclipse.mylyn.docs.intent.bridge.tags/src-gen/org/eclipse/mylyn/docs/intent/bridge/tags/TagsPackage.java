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
package org.eclipse.mylyn.docs.intent.bridge.tags;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.eclipse.mylyn.docs.intent.bridge.tags.TagsFactory
 * @model kind="package"
 * @generated
 */
public interface TagsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tags";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/intent/bridges/tags/0.7";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tags";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TagsPackage eINSTANCE = org.eclipse.mylyn.docs.intent.bridge.tags.impl.TagsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.tags.impl.TaggedBlockImpl <em>Tagged Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.tags.impl.TaggedBlockImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.tags.impl.TagsPackageImpl#getTaggedBlock()
	 * @generated
	 */
	int TAGGED_BLOCK = 0;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_BLOCK__TAG = 0;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_BLOCK__CONTENT = 1;

	/**
	 * The number of structural features of the '<em>Tagged Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_BLOCK_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mylyn.docs.intent.bridge.tags.impl.FileContentImpl <em>File Content</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mylyn.docs.intent.bridge.tags.impl.FileContentImpl
	 * @see org.eclipse.mylyn.docs.intent.bridge.tags.impl.TagsPackageImpl#getFileContent()
	 * @generated
	 */
	int FILE_CONTENT = 1;

	/**
	 * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_CONTENT__BLOCKS = 0;

	/**
	 * The number of structural features of the '<em>File Content</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_CONTENT_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.tags.TaggedBlock <em>Tagged Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tagged Block</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.tags.TaggedBlock
	 * @generated
	 */
	EClass getTaggedBlock();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.tags.TaggedBlock#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.tags.TaggedBlock#getTag()
	 * @see #getTaggedBlock()
	 * @generated
	 */
	EAttribute getTaggedBlock_Tag();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.mylyn.docs.intent.bridge.tags.TaggedBlock#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.tags.TaggedBlock#getContent()
	 * @see #getTaggedBlock()
	 * @generated
	 */
	EAttribute getTaggedBlock_Content();

	/**
	 * Returns the meta object for class '{@link org.eclipse.mylyn.docs.intent.bridge.tags.FileContent <em>File Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Content</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.tags.FileContent
	 * @generated
	 */
	EClass getFileContent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.mylyn.docs.intent.bridge.tags.FileContent#getBlocks <em>Blocks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Blocks</em>'.
	 * @see org.eclipse.mylyn.docs.intent.bridge.tags.FileContent#getBlocks()
	 * @see #getFileContent()
	 * @generated
	 */
	EReference getFileContent_Blocks();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TagsFactory getTagsFactory();

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
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.tags.impl.TaggedBlockImpl <em>Tagged Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.tags.impl.TaggedBlockImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.tags.impl.TagsPackageImpl#getTaggedBlock()
		 * @generated
		 */
		EClass TAGGED_BLOCK = eINSTANCE.getTaggedBlock();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAGGED_BLOCK__TAG = eINSTANCE.getTaggedBlock_Tag();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAGGED_BLOCK__CONTENT = eINSTANCE.getTaggedBlock_Content();

		/**
		 * The meta object literal for the '{@link org.eclipse.mylyn.docs.intent.bridge.tags.impl.FileContentImpl <em>File Content</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mylyn.docs.intent.bridge.tags.impl.FileContentImpl
		 * @see org.eclipse.mylyn.docs.intent.bridge.tags.impl.TagsPackageImpl#getFileContent()
		 * @generated
		 */
		EClass FILE_CONTENT = eINSTANCE.getFileContent();

		/**
		 * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILE_CONTENT__BLOCKS = eINSTANCE.getFileContent_Blocks();

	}

} //TagsPackage
