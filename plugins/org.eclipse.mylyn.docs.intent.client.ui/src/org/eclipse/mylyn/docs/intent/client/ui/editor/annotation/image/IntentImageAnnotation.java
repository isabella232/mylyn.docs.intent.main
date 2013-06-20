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
package org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image;

import org.eclipse.swt.graphics.Image;

/**
 * An {@link org.eclipse.jface.text.source.Annotation} allowing the
 * {@link org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor} to display images corresponding to an
 * image link in pure documentation zones.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentImageAnnotation extends AbstractIntentImageAnnotation {

	// private org.eclipse.mylyn.docs.intent.markup.markup.Image imageLink;

	/**
	 * Default constructor.
	 * 
	 * @param imageLink
	 *            a reference to an image
	 */
	public IntentImageAnnotation(org.eclipse.mylyn.docs.intent.markup.markup.Image imageLink) {
		super();
		// this.imageLink = imageLink;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.editor.annotation.image.AbstractIntentImageAnnotation#doCreateImage()
	 */
	@Override
	protected Image doCreateImage() {
		// String imagePath = imageLink.getUrl();
		// TODO enable this live rendering of images once position of markup.Image will be exact
		// return doCreateImageFromURL(imagePath);
		return null;
	}
	/*
	 * private Image doCreateImageFromURL(String imagePath) { String actualImagePath = imagePath; // Case 1:
	 * URL is a web URL try { InputStream fileInputStream; if (actualImagePath.startsWith("http")) {
	 * fileInputStream = new URL(actualImagePath).openStream(); } else { // Case 2: URL is project-relative if
	 * (actualImagePath.startsWith("./")) { URI uri = imageLink.eResource().getURI(); if
	 * (uri.isPlatformResource()) { Repository repository = IntentRepositoryManager.INSTANCE
	 * .getRepository(uri.segment(1)); actualImagePath = repository.getRepositoryLocation() +
	 * imagePath.replaceFirst("./", ""); } } // Case 3 (default): URL is absolute fileInputStream = new
	 * FileInputStream(actualImagePath); } return new Image(Display.getDefault(), fileInputStream); } catch
	 * (FileNotFoundException e) { // Silent catch: image will not be created } catch
	 * (RepositoryConnectionException e) { // Silent catch: image will not be created } catch (CoreException
	 * e) { // Silent catch: image will not be created } catch (MalformedURLException e) { // Silent catch:
	 * image will not be created } catch (IOException e) { // Silent catch: image will not be created } return
	 * null; }
	 */
}
