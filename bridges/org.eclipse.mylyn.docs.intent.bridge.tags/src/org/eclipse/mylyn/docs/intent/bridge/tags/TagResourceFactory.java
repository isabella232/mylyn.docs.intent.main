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
package org.eclipse.mylyn.docs.intent.bridge.tags;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

public class TagResourceFactory implements Resource.Factory {

	private static final String CLOSING_TAG_SYMBOL = ">";

	private static final String TAG_ID_REGEXP = "([a-zA-z0-9.:_-]+)";

	private static final String OPENING_TAG_REGEXP = "<" + TAG_ID_REGEXP + CLOSING_TAG_SYMBOL;

	private static final String CLOSING_TAG_REGEXP = "</" + TAG_ID_REGEXP + CLOSING_TAG_SYMBOL;

	private static final Pattern OPENING_TAG_PATTERN = Pattern.compile(OPENING_TAG_REGEXP);

	private static final Pattern CLOSING_TAG_PATTERN = Pattern.compile(CLOSING_TAG_REGEXP);

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.resource.Resource.Factory#createResource(org.eclipse.emf.common.util.URI)
	 */
	public Resource createResource(URI uri) {
		FileContent fileContent = getFileContent(uri);
		Resource resource = new ResourceImpl(uri);
		if (fileContent != null && !fileContent.getBlocks().isEmpty()) {
			resource.getContents().add(fileContent);
		}
		return resource;
	}

	private FileContent getFileContent(URI uri) {
		FileContent fileContent = TagsFactory.eINSTANCE.createFileContent();
		// Step 1: get the file corresponding to the given URI
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toString()));
		if (file != null && file.exists()) {
			InputStream inputStream;
			try {
				inputStream = file.getContents();

				DataInputStream dataInputStream = new DataInputStream(inputStream);
				try {
					boolean isReadingTag = false;
					String currentReadTag = "";
					// Step 2: read file and create tagged zones
					while (inputStream.available() != 0) {
						String line = dataInputStream.readLine();
						// If tag is null then we get the whole file
						// If a tag is opened
						Matcher openingTagMatcher = OPENING_TAG_PATTERN.matcher(line);
						if (!isReadingTag && openingTagMatcher.matches()) {
							isReadingTag = true;
						} else {
							Matcher closingTagMatcher = CLOSING_TAG_PATTERN.matcher(line);
							// If a tag is closed
							if (isReadingTag && closingTagMatcher.matches()) {
								TaggedBlock block = TagsFactory.eINSTANCE.createTaggedBlock();
								block.setTag(closingTagMatcher.group(0));
								block.setContent(currentReadTag);
								fileContent.getBlocks().add(block);
								currentReadTag = "";
							} else {
								// If we are currently reading a tag
								if (isReadingTag) {
									currentReadTag = currentReadTag + line + "\n";
								}
							}

						}
					}
					// If we were reading a tag and end of file was read, we add the currently read tag to the
					// liste of tagged zones
					// if (isReadingTag || tag == null) {
					// taggedZones.add(currentReadTag);
					// }
				} finally {
					try {
						dataInputStream.close();
						inputStream.close();
					} catch (IOException e) {
						// Silent catch
					}
				}
			} catch (CoreException e) {
				// Nothing to do, as no tagged zone was find the file will be considered as empty and a
				// synchronization issue indicating that the resource cannot be read will be raised
			} catch (IOException e) {
				// As no tagged zone was find the file will be considered as empty and a
				// synchronization issue indicating that the resource cannot be read will be raised
			}
		}
		return fileContent;
	}

}
