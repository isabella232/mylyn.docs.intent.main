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
package org.eclipse.mylyn.docs.intent.markup.resource.wikimedia;

import com.google.common.collect.Iterators;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.mylyn.docs.intent.markup.builder.ModelDocumentBuilder;
import org.eclipse.mylyn.docs.intent.markup.markup.Document;
import org.eclipse.mylyn.docs.intent.markup.markup.Image;
import org.eclipse.mylyn.docs.intent.markup.markup.Link;
import org.eclipse.mylyn.docs.intent.markup.markup.MarkupFactory;
import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
import org.eclipse.mylyn.wikitext.core.util.IgnoreDtdEntityResolver;
import org.eclipse.mylyn.wikitext.mediawiki.core.MediaWikiLanguage;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * A resource implementation for web-based pages on wikimedia.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class WikimediaResource extends ResourceImpl {

	/**
	 * Constant for the buffer size.
	 */
	private static final int BUFFER_SIZE = 0x10000;

	/**
	 * Creates a new {@link WikimediaResource}.
	 * 
	 * @param eUri
	 *            the {@link URI} used to create the resource
	 */
	public WikimediaResource(URI eUri) {
		super(eUri);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#load(java.util.Map)
	 */
	@Override
	public void load(Map<?, ?> options) throws IOException {
		// let's build the http URI...
		URI uri = getURI();

		WikimediaURI wURI = new WikimediaURI(uri);

		String pageName = wURI.pageName();
		String apiURI = wURI.baseServer() + "/api.php?format=xml&action=query&prop=revisions&titles="
				+ pageName + "&rvprop=content";
		URI eApiURI = URI.createURI(apiURI);

		Map<?, ?> response = null;
		if (options != null) {
			response = (Map<?, ?>)options.get(URIConverter.OPTION_RESPONSE);
		}
		if (response == null) {
			response = new HashMap<Object, Object>();
		}

		InputStream inputStream = getInputStream(eApiURI);

		URI eImgURI = URI.createURI(wURI.baseServer() + "/api.php?action=query&titles=" + pageName
				+ "&generator=images&prop=imageinfo&iiprop=url&format=xml");
		InputStream inputImage = getInputStream(eImgURI);

		try {
			wikimediaLoad(inputStream, options);
			/*
			 * Now let's get more information about the images
			 */

			handleImagesData(eImgURI, wURI.baseServer(), inputImage);

		} catch (SAXException e) {
			// TODO proper logging
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} finally {
			inputStream.close();
			inputImage.close();
			Long timeStamp = (Long)response.get(URIConverter.RESPONSE_TIME_STAMP_PROPERTY);
			if (timeStamp != null) {
				setTimeStamp(timeStamp);
			}
		}

		prepareProxyFromLinks();

	}

	/**
	 * Gets additional information about the image at the given URI.
	 * 
	 * @param eImgURI
	 *            the image URI
	 * @param baseServer
	 *            the base Server URL
	 * @param input
	 *            the input stream
	 * @throws ParserConfigurationException
	 *             if parser cannot be created
	 * @throws SAXException
	 *             if file is invalid
	 * @throws IOException
	 *             if file cannot be properly accessed
	 */
	private void handleImagesData(URI eImgURI, String baseServer, InputStream input)
			throws ParserConfigurationException, SAXException, IOException {

		final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		parserFactory.setNamespaceAware(true);
		parserFactory.setValidating(false);
		SAXParser saxParser = parserFactory.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setEntityResolver(IgnoreDtdEntityResolver.getInstance());

		ImageFetchingContentHandler contentHandler = new ImageFetchingContentHandler();
		xmlReader.setContentHandler(contentHandler);

		try {
			xmlReader.parse(new InputSource(input));
		} catch (IOException e) {
			throw new RuntimeException(
					String.format("Unexpected exception retrieving data from %s", eImgURI), e); //$NON-NLS-1$
		}

		if (contentHandler.imageTitleToUrl.size() > 0) {
			Iterator<Image> it = Iterators.filter(getAllContents(), Image.class);
			while (it.hasNext()) {
				Image cur = it.next();
				String completeURL = contentHandler.imageTitleToUrl.get("Image:" + cur.getUrl());
				if (completeURL != null) {
					cur.setUrl(baseServer + "/" + completeURL);
				}
			}
		}

	}

	/**
	 * Creates and return an input stream from the given URI.
	 * 
	 * @param eApiURI
	 *            the URI on which an input stream should be created
	 * @return an input stream from the given URI
	 * @throws IOException
	 *             if file cannot be properly accessed
	 */
	private InputStream getInputStream(URI eApiURI) throws IOException {
		// If an input stream can't be created, ensure that the resource is
		// still considered loaded after the failure,
		// and do all the same processing we'd do if we actually were able to
		// create a valid input stream.
		//
		InputStream inputStream = null;
		try {
			inputStream = getURIConverter().createInputStream(eApiURI);
		} catch (IOException exception) {
			Notification notification = setLoaded(true);
			isLoading = true;
			if (errors != null) {
				errors.clear();
			}
			if (warnings != null) {
				warnings.clear();
			}
			isLoading = false;
			if (notification != null) {
				eNotify(notification);
			}
			setModified(false);

			throw exception;
		}
		return inputStream;
	}

	/**
	 * Creates a proxy for all links, that will be used as long as they are not resolved.
	 */
	private void prepareProxyFromLinks() {

		Iterator<Link> it = Iterators.filter(getAllContents(), Link.class);
		while (it.hasNext()) {
			Link lnk = it.next();
			String href = lnk.getHrefOrHashName();
			if (lnk.getTarget() == null && href.startsWith("/wiki/")) {
				String targetPageName = href.substring(href.indexOf("/wiki/") + 6);
				URI uri = getURI();
				URI targetUri = uri.trimSegments(uri.segmentCount());
				targetUri = URI.createURI(targetUri.toString() + targetPageName + "#/0");
				Document proxifiedDoc = MarkupFactory.eINSTANCE.createDocument();
				((InternalEObject)proxifiedDoc).eSetProxyURI(targetUri);
				lnk.setTarget(proxifiedDoc);
			}
		}

	}

	/**
	 * Loads the resource (markup elements corresponding to the wiki file held by the given input stream will
	 * be parsed on the fly).
	 * 
	 * @param is
	 *            the input stream of the wiki file to load as a model
	 * @param options
	 *            loading options
	 * @throws SAXException
	 *             if file is invalid
	 * @throws IOException
	 *             if fille cannot be properly accessed
	 */
	private void wikimediaLoad(InputStream is, Map<?, ?> options) throws SAXException, IOException {

		final char[] buffer = new char[BUFFER_SIZE];
		StringBuilder out = new StringBuilder();
		Reader in = new InputStreamReader(is, "UTF-8");
		int read;
		do {
			read = in.read(buffer, 0, buffer.length);
			if (read > 0) {
				out.append(buffer, 0, read);
			}
		} while (read >= 0);

		String outString = out.toString();
		int begin = outString.indexOf("<rev>") + 4;
		int end = outString.indexOf("</rev>");

		String revisionContent = outString.substring(begin + 1, end - begin);

		MarkupParser parser = new MarkupParser(new MediaWikiLanguage());
		ModelDocumentBuilder builder = new ModelDocumentBuilder();
		parser.setBuilder(builder);
		parser.parse(revisionContent, true);

		Collection<EObject> roots = builder.getRoots();

		getContents().addAll(roots);

	}

}
