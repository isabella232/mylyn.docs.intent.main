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
package org.eclipse.mylyn.docs.intent.retro;

import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

public class ProtocolFactory implements Resource.Factory {

	public Resource createResource(URI uri) {
		String projectName = extractProjectName(uri);
		String regExp = extractRegExp(uri);
		if (projectName != null) {
			IWorkspace wksps = ResourcesPlugin.getWorkspace();
			if (wksps != null) {
				IProject prj = wksps.getRoot().getProject(projectName);
				if (prj != null) {
					Resource result = new ResourceImpl(uri);
					Project rPrj = RetroFactory.eINSTANCE.createProject();
					rPrj.setId(projectName);
					try {
						fillProjectWithTests(rPrj, prj, regExp);
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result.getContents().add(rPrj);
					addListeners(prj);
					return result;
				}
			}

		}
		return null;
	}

	private void addListeners(IProject prj) {
		if (RetroGeneratedElementListener.getInstance() != null) {
			RetroGeneratedElementListener.getInstance().addElementToListen(
					URI.createURI("retro:/" + prj.getName()));
		}

	}

	private void fillProjectWithTests(final Project rPrj, IProject prj, final String regExp)
			throws CoreException {
		final Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		prj.accept(new IResourceVisitor() {

			public boolean visit(IResource resource) throws CoreException {
				// We consider :
				// java files
				if ("java".equals(resource.getFileExtension())) {
					String resourcePath = resource.getFullPath().removeFirstSegments(2)
							.removeFirstSegments(5).removeLastSegments(1).toString().replaceAll("/", ".");
					// that matches the given regExp
					if (pattern.matcher(resourcePath).matches()) {
						AcceptanceTest tst = RetroFactory.eINSTANCE.createAcceptanceTest();
						tst.setSwtBotClassName(resource.getFullPath().lastSegment().replace(".java", ""));
						tst.setPackage(resourcePath);
						rPrj.getAcceptanceTests().add(tst);
					}

				}
				return true;
			}

		});

	}

	/**
	 * Returns the project name for the given retro uri ("retro:/myProjectName[/myExpreg]").
	 * 
	 * @param uri
	 *            the retro uri ("retro:/myProjectName[/myExpreg]")
	 * @return the extracted project name or null if none found
	 */
	public static String extractProjectName(URI uri) {
		// expected URIS are : retro:/myProject[/expreg]
		if (uri.segmentCount() >= 1) {
			return uri.segment(0);
		}
		return null;
	}

	/**
	 * Returns the regular expression for the given retro uri ("retro:/myProjectName[/myExpreg]").
	 * 
	 * @param uri
	 *            the retro uri ("retro:/myProjectName[/myExpreg]")
	 * @return the extracted regular expression or ".*" if none found
	 */
	public static String extractRegExp(URI uri) {
		if (uri.segmentCount() > 1) {
			// Getting the expreg
			String regExp = "";
			for (int i = 1; i < uri.segmentCount(); i++) {
				if (i > 1) {
					regExp += "/";
				}
				regExp += uri.segment(i);
			}
			// Replacing "." by literal characters
			regExp = regExp.replace(".", "\\.");
			// Replacing "*" by ".*" and "?" by "."
			regExp = regExp.replace("*", ".*").replace("?", ".");
			return regExp;
		}
		// If no expreg found, we return ".*"
		return ".*";
	}

}
