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

package com.liferay.util.xml;

/**
 * @author Brian Wing Shun Chan
 */
public class XMLConverter {

	public static javax.xml.namespace.QName toJavaxQName(
		org.dom4j.QName dom4jQName) {

		javax.xml.namespace.QName javaxQName = new javax.xml.namespace.QName(
			dom4jQName.getNamespaceURI(), dom4jQName.getName(),
			dom4jQName.getNamespacePrefix());

		return javaxQName;
	}

	public static org.w3c.dom.Document toW3CDocument(
			org.dom4j.Document dom4jDoc)
		throws org.dom4j.DocumentException {

		org.dom4j.io.DOMWriter dom4jWriter = new org.dom4j.io.DOMWriter();

		org.w3c.dom.Document w3cDoc = dom4jWriter.write(dom4jDoc);

		return w3cDoc;
	}

	public static org.w3c.dom.Element toW3CElement(org.dom4j.Element dom4jEl)
		throws org.dom4j.DocumentException {

		org.dom4j.Document dom4jDoc =
			org.dom4j.DocumentFactory.getInstance().createDocument();

		dom4jDoc.setRootElement(dom4jEl.createCopy());

		org.w3c.dom.Document w3cDoc = toW3CDocument(dom4jDoc);

		return w3cDoc.getDocumentElement();
	}

}