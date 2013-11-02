/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.kernel.xml;

import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;

/**
 * @author Zsolt Berentey
 */
public class ElementHandler implements ContentHandler {

	public ElementHandler(
		ElementProcessor elementProcessor, String[] triggers) {

		_elementProcessor = elementProcessor;

		for (String trigger : triggers) {
			_triggers.add(trigger);
		}
	}

	@Override
	public void characters(char[] chars, int start, int length) {
		if (_element != null) {
			_element.setText(new String(chars, start, length));
		}
	}

	@Override
	public void endDocument() {
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (_element != null) {
			_elementProcessor.processElement(_element);

			_element = null;
		}
	}

	@Override
	public void endPrefixMapping(String prefix) {
	}

	@Override
	public void ignorableWhitespace(char[] chars, int start, int length) {
	}

	@Override
	public void processingInstruction(String target, String data) {
	}

	@Override
	public void setDocumentLocator(Locator locator) {
	}

	@Override
	public void skippedEntity(String name) {
	}

	@Override
	public void startDocument() {
	}

	@Override
	public void startElement(
		String uri, String localName, String qName, Attributes attributes) {

		if (_element != null) {
			_elementProcessor.processElement(_element);

			_element = null;
		}

		if (!_triggers.contains(localName)) {
			return;
		}

		Element element = SAXReaderUtil.createElement(qName);

		for (int i = 0; i < attributes.getLength(); i++) {
			element.addAttribute(
				attributes.getQName(i), attributes.getValue(i));
		}

		_element = element;
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) {
	}

	private Element _element;
	private ElementProcessor _elementProcessor;
	private Set<String> _triggers = new HashSet<String>();

}