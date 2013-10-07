/*******************************************************************************
 * Copyright (c) 2010, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.bridge.java.resource.factory;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.ILocalVariable;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.mylyn.docs.intent.bridge.java.AbstractCapableElement;
import org.eclipse.mylyn.docs.intent.bridge.java.Classifier;
import org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind;
import org.eclipse.mylyn.docs.intent.bridge.java.Field;
import org.eclipse.mylyn.docs.intent.bridge.java.JavaFactory;
import org.eclipse.mylyn.docs.intent.bridge.java.Javadoc;
import org.eclipse.mylyn.docs.intent.bridge.java.Method;
import org.eclipse.mylyn.docs.intent.bridge.java.Parameter;
import org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind;
import org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement;

/**
 * A class allowing to represent Java classes as models.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class JavaClassExplorer {

	/**
	 * Constant identifying opening brackets.
	 */
	private static final String OPENING_BRACKET = "{";

	/**
	 * Constant identifying closing brackets.
	 */
	private static final String CLOSING_BRACKET = "}";

	/**
	 * Allows to represent all the {@link IType}s contained in the given {@link ICompilationUnit} as
	 * {@link Classifier}s.
	 * 
	 * @param path
	 *            the path of this element from the workspace root
	 * @param javaClass
	 *            the {@link ICompilationUnit} to inspect
	 * @return the {@link Classifier}s corresponding to the {@link IType}s contained in the given
	 *         {@link ICompilationUnit}
	 * @throws JavaModelException
	 *             if errors occur while querying the java class
	 */
	public Collection<Classifier> getJavaClassAsModel(String path, ICompilationUnit javaClass)
			throws JavaModelException {
		Collection<Classifier> eClassifiers = new LinkedHashSet<Classifier>();
		IType[] allTypes;
		allTypes = javaClass.getAllTypes();
		for (IType iType : allTypes) {
			eClassifiers.add(getTypeAsModel(path, iType));
		}
		return eClassifiers;
	}

	/**
	 * Allows to represent the given {@link IType} as a {@link Classifier}.
	 * 
	 * @param path
	 *            the path of this element from the workspace root
	 * @param iType
	 *            the {@link IType} to inspect
	 * @return a {@link Classifier} corresponding to the given {@link IType}
	 * @throws JavaModelException
	 *             if errors occur while querying the type
	 */
	private Classifier getTypeAsModel(String path, IType iType) throws JavaModelException {
		Classifier eClassifier = JavaFactory.eINSTANCE.createClassifier();

		eClassifier.setClassifierPath(path);
		eClassifier.setName(iType.getFullyQualifiedName());
		updateAbstractInformations(eClassifier, iType);
		updateVisibilityInformations(eClassifier, iType);
		eClassifier.setJavadoc(getJavadocAsModel(iType));

		// Is this class an implementation ?
		int flags = iType.getFlags();
		if (Flags.isInterface(flags)) {
			eClassifier.setKind(ClassifierKind.INTERFACE);
		} else if (Flags.isEnum(flags)) {
			eClassifier.setKind(ClassifierKind.ENUM);
		} else {
			eClassifier.setKind(ClassifierKind.CLASS);
		}

		// class supertypes and implemented interfaces
		eClassifier.setExtends(iType.getSuperclassName());
		for (String superInterface : iType.getSuperInterfaceNames()) {
			eClassifier.getImplements().add(superInterface);
		}

		for (IField field : iType.getFields()) {
			eClassifier.getFields().add(getFieldAsModel(path, field));
		}
		for (IMethod method : iType.getMethods()) {
			eClassifier.getMethods().add(getMethodAsModel(path, method));
		}
		return eClassifier;
	}

	/**
	 * Allows to represent the given {@link IMethod} as a {@link Method}.
	 * 
	 * @param path
	 *            the path of this element from the workspace root
	 * @param method
	 *            the {@link IMethod} to inspect
	 * @return a {@link Method} corresponding to the given {@link IMethod}
	 * @throws JavaModelException
	 *             if errors occur while querying the method
	 */
	private Method getMethodAsModel(String path, IMethod method) throws JavaModelException {
		Method eMethod = null;

		if (method.isConstructor()) {
			eMethod = JavaFactory.eINSTANCE.createConstructor();
		} else {
			eMethod = JavaFactory.eINSTANCE.createMethod();
		}
		JavaFactory.eINSTANCE.createMethod();
		eMethod.setSimpleName(method.getElementName());
		updateAbstractInformations(eMethod, method);
		updateVisibilityInformations(eMethod, method);

		eMethod.setJavadoc(getJavadocAsModel(method));

		eMethod.setReturnType(getTypeFromTypeSignature(method.getReturnType()));
		for (ILocalVariable parameter : method.getParameters()) {
			Parameter eParameter = JavaFactory.eINSTANCE.createParameter();
			eParameter.setName(parameter.getElementName());
			eParameter.setType(getTypeFromTypeSignature(parameter.getTypeSignature()));
			String attachedJavadoc = parameter.getAttachedJavadoc(new NullProgressMonitor());
			if (attachedJavadoc != null && attachedJavadoc.length() > 0) {
				Javadoc paramJavaDoc = JavaFactory.eINSTANCE.createJavadoc();
				paramJavaDoc.setContent(attachedJavadoc);
				eParameter.setJavadoc(paramJavaDoc);
			}
			eMethod.getParameters().add(eParameter);
		}

		for (String exceptionType : method.getExceptionTypes()) {
			eMethod.getExceptions().add(getTypeFromTypeSignature(exceptionType));
		}

		// Setting method content if neither abstract or interface
		String methodContentWithoutJavaDoc = method.getSource();
		if (methodContentWithoutJavaDoc.contains(OPENING_BRACKET)
				&& methodContentWithoutJavaDoc.contains(CLOSING_BRACKET)) {
			if (method.getJavadocRange() != null) {
				methodContentWithoutJavaDoc = method.getSource().substring(
						method.getJavadocRange().getLength());
			}
			String methodContentWithoutDeclaration = methodContentWithoutJavaDoc.trim();

			if (methodContentWithoutDeclaration.contains(OPENING_BRACKET)
					&& methodContentWithoutDeclaration.contains(CLOSING_BRACKET)) {
				methodContentWithoutDeclaration = methodContentWithoutJavaDoc
						.substring(methodContentWithoutJavaDoc.indexOf(OPENING_BRACKET) + 1);
				methodContentWithoutDeclaration = methodContentWithoutDeclaration.substring(0,
						methodContentWithoutDeclaration.lastIndexOf(CLOSING_BRACKET)).trim();

				// Normalize line delimiters and tabulations
				String methodContent = "";
				String[] methodLines = methodContentWithoutDeclaration.split("\n");
				for (int i = 0; i < methodLines.length; i++) {
					methodContent += methodLines[i].trim().replace("\t", "") + "\n";
				}
				methodContent = methodContent.trim();
				eMethod.setContent(methodContent);
			}
		}
		eMethod.setName(getMethodID(method));
		eMethod.setClassifierPath(path);
		return eMethod;
	}

	/**
	 * Allows to represent the given {@link IField} as a {@link Field}.
	 * 
	 * @param path
	 *            the path of this element from the workspace root
	 * @param field
	 *            the {@link field} to inspect
	 * @return a {@link Field} corresponding to the given {@link IField}
	 * @throws JavaModelException
	 *             if errors occur while querying the field
	 */
	private Field getFieldAsModel(String path, IField field) throws JavaModelException {
		Field eField = JavaFactory.eINSTANCE.createField();
		eField.setName(field.getElementName());
		eField.setJavadoc(getJavadocAsModel(field));
		eField.setType(getTypeFromTypeSignature(field.getTypeSignature()));
		eField.setClassifierPath(path);
		String fieldTypeSignature = Signature.toString(field.getTypeSignature());
		if (fieldTypeSignature != null) {
			String[][] resolvedType = field.getDeclaringType().resolveType(fieldTypeSignature);
			if (resolvedType != null) {
				eField.setQualifiedType(Signature.toQualifiedName(resolvedType[0]));
			}
		}
		updateVisibilityInformations(eField, field);
		return eField;
	}

	/**
	 * Allows to represent the javadoc of the given {@link IMember} as a {@link Javadoc}.
	 * 
	 * @param iMember
	 *            the {@link IMember} to inspect
	 * @return a {@link Javadoc} corresponding to the given {@link IMember}'s javadoc
	 * @throws JavaModelException
	 *             if errors occur while querying the member
	 */
	private Javadoc getJavadocAsModel(IMember iMember) throws JavaModelException {
		Javadoc eJavaDoc = null;
		if (iMember.getJavadocRange() != null) {
			eJavaDoc = JavaFactory.eINSTANCE.createJavadoc();
			eJavaDoc.setContent(iMember.getOpenable().getBuffer()
					.getText(iMember.getJavadocRange().getOffset(), iMember.getJavadocRange().getLength()));
		}
		return eJavaDoc;
	}

	/**
	 * Sets the given{@link AbstractCapableElement} as abstract if the given {@link IMember} is abstract.
	 * 
	 * @param abstactCapableElement
	 *            the {@link AbstractCapableElement} to update
	 * @param iMember
	 *            the {@link IMember} to inspect
	 * @throws JavaModelException
	 *             if errors occur while querying the member
	 */
	private void updateAbstractInformations(AbstractCapableElement abstactCapableElement, IMember iMember)
			throws JavaModelException {
		abstactCapableElement.setAbstract(Flags.isAbstract(iMember.getFlags()));
	}

	/**
	 * Updates the visibility informations of the given {@link VisibleElement} (is it public ? final ? static
	 * ?)
	 * 
	 * @param visiblementElement
	 *            the {@link VisibleElement} to update
	 * @param iMember
	 *            the {@link IMember} to inspect
	 * @throws JavaModelException
	 *             if errors occur while querying the member
	 */
	private void updateVisibilityInformations(VisibleElement visiblementElement, IMember iMember)
			throws JavaModelException {
		int flags = iMember.getFlags();
		visiblementElement.setFinal(Flags.isFinal(flags));
		visiblementElement.setStatic(Flags.isStatic(flags));
		if (Flags.isPublic(flags)) {
			visiblementElement.setVisibility(VisibilityKind.PUBLIC);
		} else if (Flags.isProtected(flags)) {
			visiblementElement.setVisibility(VisibilityKind.PROTECTED);
		} else if (Flags.isPrivate(flags)) {
			visiblementElement.setVisibility(VisibilityKind.PRIVATE);
		} else {
			visiblementElement.setVisibility(VisibilityKind.PACKAGE);
		}
	}

	/**
	 * Returns a normalized form of the given type signature (e.g. transforms 'QExampleJavaClass;' in
	 * 'ExampleJavaClass'.
	 * 
	 * @param typeSignature
	 *            the type signature as given by JDT
	 * @return the normalized form of the given type signature
	 */
	public static String getTypeFromTypeSignature(String typeSignature) {
		return Signature.toString(typeSignature);
	}

	/**
	 * Returns the identifier used to identify the given {@link IMember}.
	 * 
	 * @param member
	 *            the {@link IMember} to inspect
	 * @return the identifier used to identify the given {@link IMember}
	 * @throws JavaModelException
	 *             if errors occur while querying the member
	 */
	public static String getMemberID(IMember member) throws JavaModelException {
		if (member instanceof IMethod) {
			return getMethodID((IMethod)member);
		} else {
			return member.getElementName();
		}
	}

	/**
	 * Returns the identifier used to identify the given {@link IMethod}.
	 * 
	 * @param method
	 *            the {@link IMethod} to inspect
	 * @return the identifier used to identify the given {@link IMethod}
	 * @throws JavaModelException
	 *             if errors occur while querying the member
	 */
	public static String getMethodID(IMethod method) throws JavaModelException {
		String methodSignature = method.getElementName() + "(";
		for (ILocalVariable parameter : method.getParameters()) {
			methodSignature += "," + getTypeFromTypeSignature(parameter.getTypeSignature());
		}
		methodSignature = methodSignature.replaceFirst(",", "") + ")";
		return methodSignature;
	}
}
