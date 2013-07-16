package org.eclipse.mylyn.docs.intent.markup.resource.wikimedia;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * A specific content handler for images.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
class ImageFetchingContentHandler implements ContentHandler {

	/**
	 * Map associating each image's title with its corresponding URL.
	 */
	final Map<String, String> imageTitleToUrl = new HashMap<String, String>();

	/**
	 * The current page.
	 */
	private String currentPage;

	/**
	 * Indicates if the image has additional information.
	 */
	private boolean inImageInfo;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
	 *      org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if ("page".equals(localName)) { //$NON-NLS-1$
			currentPage = atts.getValue("title"); //$NON-NLS-1$
		} else if ("imageinfo".equals(localName)) { //$NON-NLS-1$
			inImageInfo = true;
		} else if (inImageInfo && "ii".equals(localName)) { //$NON-NLS-1$
			imageTitleToUrl.put(currentPage, atts.getValue("url")); //$NON-NLS-1$
			imageTitleToUrl.put(currentPage.replace(' ', '_'), atts.getValue("url"));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("page".equals(localName)) { //$NON-NLS-1$
			currentPage = null;
		} else if ("imageinfo".equals(localName)) { //$NON-NLS-1$
			inImageInfo = false;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	public void endDocument() throws SAXException {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 */
	public void endPrefixMapping(String prefix) throws SAXException {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
	 */
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
	 */
	public void processingInstruction(String target, String data) throws SAXException {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	public void setDocumentLocator(Locator locator) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	 */
	public void skippedEntity(String name) throws SAXException {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	public void startDocument() throws SAXException {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
	}

}
