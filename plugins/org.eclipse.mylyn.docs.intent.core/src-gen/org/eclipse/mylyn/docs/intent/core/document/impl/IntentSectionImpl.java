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
package org.eclipse.mylyn.docs.intent.core.document.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.mylyn.docs.intent.core.document.GenericUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Intent Section</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentSectionImpl#getIntentContent <em>Intent Content</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentSectionImpl#getSubSections <em>Sub Sections</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentSectionImpl#getUnits <em>Units</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentSectionImpl#getDescriptionUnits <em>Description Units</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.document.impl.IntentSectionImpl#getModelingUnits <em>Modeling Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentSectionImpl extends IntentStructuredElementImpl implements IntentSection {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected IntentSectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IntentDocumentPackage.Literals.INTENT_SECTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<EObject> getIntentContent() {
		return (EList<EObject>)eGet(IntentDocumentPackage.Literals.INTENT_SECTION__INTENT_CONTENT, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<IntentSection> getSubSections() {
		Collection<IntentSection> result = new ArrayList<IntentSection>();
		Iterator<EObject> it = getIntentContent().iterator();
		while (it.hasNext()) {
			EObject eObj = (EObject)it.next();
			if (eObj instanceof IntentSection)
				result.add((IntentSection)eObj);
		}
		return new EcoreEList.UnmodifiableEList<IntentSection>(eInternalContainer(),
				IntentDocumentPackage.eINSTANCE.getIntentSection_SubSections(), result.size(),
				result.toArray());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<GenericUnit> getUnits() {
		Collection<GenericUnit> result = new ArrayList<GenericUnit>();
		Iterator<EObject> it = getIntentContent().iterator();
		while (it.hasNext()) {
			EObject eObj = (EObject)it.next();
			if (eObj instanceof GenericUnit)
				result.add((GenericUnit)eObj);
		}
		return new EcoreEList.UnmodifiableEList<GenericUnit>(eInternalContainer(),
				IntentDocumentPackage.eINSTANCE.getIntentSection_Units(), result.size(), result.toArray());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DescriptionUnit> getDescriptionUnits() {
		Collection<DescriptionUnit> result = new ArrayList<DescriptionUnit>();
		Iterator<EObject> it = getIntentContent().iterator();
		while (it.hasNext()) {
			EObject eObj = (EObject)it.next();
			if (eObj instanceof DescriptionUnit)
				result.add((DescriptionUnit)eObj);
		}
		return new EcoreEList.UnmodifiableEList<DescriptionUnit>(eInternalContainer(),
				IntentDocumentPackage.eINSTANCE.getIntentSection_DescriptionUnits(), result.size(),
				result.toArray());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<ModelingUnit> getModelingUnits() {
		Collection<ModelingUnit> result = new ArrayList<ModelingUnit>();
		Iterator<EObject> it = getIntentContent().iterator();
		while (it.hasNext()) {
			EObject eObj = (EObject)it.next();
			if (eObj instanceof ModelingUnit)
				result.add((ModelingUnit)eObj);
		}
		return new EcoreEList.UnmodifiableEList<ModelingUnit>(eInternalContainer(),
				IntentDocumentPackage.eINSTANCE.getIntentSection_ModelingUnits(), result.size(),
				result.toArray());
	}

} // IntentSectionImpl
