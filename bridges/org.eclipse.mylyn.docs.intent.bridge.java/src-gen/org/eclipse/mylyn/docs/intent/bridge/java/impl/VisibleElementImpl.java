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
package org.eclipse.mylyn.docs.intent.bridge.java.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.mylyn.docs.intent.bridge.java.JavaPackage;
import org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind;
import org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Visible Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.VisibleElementImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.VisibleElementImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.VisibleElementImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.bridge.java.impl.VisibleElementImpl#getClassifierPath <em>Classifier Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VisibleElementImpl extends NamedElementImpl implements VisibleElement {
	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final VisibilityKind VISIBILITY_EDEFAULT = VisibilityKind.PUBLIC;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected VisibilityKind visibility = VISIBILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected boolean static_ = STATIC_EDEFAULT;

	/**
	 * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected boolean final_ = FINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassifierPath() <em>Classifier Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifierPath()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASSIFIER_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassifierPath() <em>Classifier Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifierPath()
	 * @generated
	 * @ordered
	 */
	protected String classifierPath = CLASSIFIER_PATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VisibleElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.Literals.VISIBLE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibilityKind getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(VisibilityKind newVisibility) {
		VisibilityKind oldVisibility = visibility;
		visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.VISIBLE_ELEMENT__VISIBILITY, oldVisibility, visibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {
		return static_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(boolean newStatic) {
		boolean oldStatic = static_;
		static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.VISIBLE_ELEMENT__STATIC, oldStatic, static_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinal() {
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal) {
		boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.VISIBLE_ELEMENT__FINAL, oldFinal, final_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassifierPath() {
		return classifierPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassifierPath(String newClassifierPath) {
		String oldClassifierPath = classifierPath;
		classifierPath = newClassifierPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.VISIBLE_ELEMENT__CLASSIFIER_PATH, oldClassifierPath, classifierPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaPackage.VISIBLE_ELEMENT__VISIBILITY:
				return getVisibility();
			case JavaPackage.VISIBLE_ELEMENT__STATIC:
				return isStatic();
			case JavaPackage.VISIBLE_ELEMENT__FINAL:
				return isFinal();
			case JavaPackage.VISIBLE_ELEMENT__CLASSIFIER_PATH:
				return getClassifierPath();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaPackage.VISIBLE_ELEMENT__VISIBILITY:
				setVisibility((VisibilityKind)newValue);
				return;
			case JavaPackage.VISIBLE_ELEMENT__STATIC:
				setStatic((Boolean)newValue);
				return;
			case JavaPackage.VISIBLE_ELEMENT__FINAL:
				setFinal((Boolean)newValue);
				return;
			case JavaPackage.VISIBLE_ELEMENT__CLASSIFIER_PATH:
				setClassifierPath((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaPackage.VISIBLE_ELEMENT__VISIBILITY:
				setVisibility(VISIBILITY_EDEFAULT);
				return;
			case JavaPackage.VISIBLE_ELEMENT__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
			case JavaPackage.VISIBLE_ELEMENT__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case JavaPackage.VISIBLE_ELEMENT__CLASSIFIER_PATH:
				setClassifierPath(CLASSIFIER_PATH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaPackage.VISIBLE_ELEMENT__VISIBILITY:
				return visibility != VISIBILITY_EDEFAULT;
			case JavaPackage.VISIBLE_ELEMENT__STATIC:
				return static_ != STATIC_EDEFAULT;
			case JavaPackage.VISIBLE_ELEMENT__FINAL:
				return final_ != FINAL_EDEFAULT;
			case JavaPackage.VISIBLE_ELEMENT__CLASSIFIER_PATH:
				return CLASSIFIER_PATH_EDEFAULT == null ? classifierPath != null : !CLASSIFIER_PATH_EDEFAULT.equals(classifierPath);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (visibility: ");
		result.append(visibility);
		result.append(", static: ");
		result.append(static_);
		result.append(", final: ");
		result.append(final_);
		result.append(", classifierPath: ");
		result.append(classifierPath);
		result.append(')');
		return result.toString();
	}

} //VisibleElementImpl
