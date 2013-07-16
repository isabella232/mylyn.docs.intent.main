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
package org.eclipse.mylyn.docs.intent.bridge.java.util;

import org.eclipse.mylyn.docs.intent.bridge.java.AbstractCapableElement;
import org.eclipse.mylyn.docs.intent.bridge.java.Classifier;
import org.eclipse.mylyn.docs.intent.bridge.java.ClassifierKind;
import org.eclipse.mylyn.docs.intent.bridge.java.Field;
import org.eclipse.mylyn.docs.intent.bridge.java.Javadoc;
import org.eclipse.mylyn.docs.intent.bridge.java.Method;
import org.eclipse.mylyn.docs.intent.bridge.java.VisibilityKind;
import org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement;

/**
 * Allows to represent java model elements as images.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class JavaBridgeSerializer extends JavaSwitch<String> {

	/**
	 * Constant identifying new lines.
	 */
	private static final String NEW_LINE = "\n";

	/**
	 * Constant identifying spaces.
	 */
	private static final String SPACE = " ";

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.util.JavaSwitch#caseClassifier(org.eclipse.mylyn.docs.intent.bridge.java.Classifier)
	 */
	@Override
	public String caseClassifier(Classifier object) {
		String result = caseAbstractCapableElement(object);
		if (ClassifierKind.ENUM == object.getKind()) {
			result += "enum ";
		} else if (ClassifierKind.INTERFACE == object.getKind()) {
			result += "interface ";
		} else {
			result += "class ";
		}
		if (object.getName().contains(".")) {
			result += object.getName().substring(object.getName().lastIndexOf(".") + 1);
		} else {
			result += object.getName();
		}
		if (object.getExtends() != null) {
			result += " extends " + object.getExtends();
		}
		if (!object.getImplements().isEmpty()) {
			result += " implements ";
			for (String implementedInterface : object.getImplements()) {
				result += implementedInterface + ",";
			}
			result = result.substring(0, result.length() - 2);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.util.JavaSwitch#caseMethod(org.eclipse.mylyn.docs.intent.bridge.java.Method)
	 */
	@Override
	public String caseMethod(Method object) {
		String result = caseAbstractCapableElement(object);
		result += object.getReturnType() + SPACE + object.getName();
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.util.JavaSwitch#caseField(org.eclipse.mylyn.docs.intent.bridge.java.Field)
	 */
	@Override
	public String caseField(Field object) {
		return caseVisibleElement(object) + object.getType() + SPACE + object.getName();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.util.JavaSwitch#caseVisibleElement(org.eclipse.mylyn.docs.intent.bridge.java.VisibleElement)
	 */
	@Override
	public String caseVisibleElement(VisibleElement object) {
		String result = "";
		if (object.getJavadoc() != null) {
			result += caseJavadoc(object.getJavadoc()) + NEW_LINE;
		}

		if (VisibilityKind.PUBLIC == object.getVisibility()) {
			result += "public ";
		} else if (VisibilityKind.PRIVATE == object.getVisibility()) {
			result += "private ";
		} else if (VisibilityKind.PROTECTED == object.getVisibility()) {
			result += "protected ";
		}

		if (object.isStatic()) {
			result += "static ";
		}
		if (object.isFinal()) {
			result += "final ";
		}
		return result + SPACE;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.util.JavaSwitch#caseAbstractCapableElement(org.eclipse.mylyn.docs.intent.bridge.java.AbstractCapableElement)
	 */
	@Override
	public String caseAbstractCapableElement(AbstractCapableElement object) {
		String result = caseVisibleElement(object);
		if (object.isAbstract()) {
			result += "abstract ";
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.bridge.java.util.JavaSwitch#caseJavadoc(org.eclipse.mylyn.docs.intent.bridge.java.Javadoc)
	 */
	@Override
	public String caseJavadoc(Javadoc object) {
		String result = "";
		for (String javadocLine : object.getContent().replace("\t", "").split(NEW_LINE)) {
			result += javadocLine.replace("* ", "").trim() + NEW_LINE;
		}
		return result.trim();
	}
}
