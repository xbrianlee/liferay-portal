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

package com.liferay.portal.xml;

import com.liferay.portal.kernel.util.StringPool;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class StAXReaderUtil {

	public static XMLInputFactory getXMLInputFactory() {
		return _xmlInputFactory;
	}

	public static String read(XMLEventReader xmlEventReader)
		throws XMLStreamException {

		XMLEvent xmlEvent = xmlEventReader.peek();

		if (xmlEvent.isCharacters()) {
			xmlEvent = xmlEventReader.nextEvent();

			return xmlEvent.asCharacters().getData();
		}
		else {
			return StringPool.BLANK;
		}
	}

	private static XMLInputFactory _createXMLInputFactory() {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

		xmlInputFactory.setProperty(
			XMLInputFactory.IS_COALESCING, Boolean.TRUE);

		return xmlInputFactory;
	}

	private static XMLInputFactory _xmlInputFactory = _createXMLInputFactory();

}