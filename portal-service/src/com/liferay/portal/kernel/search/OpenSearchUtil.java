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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.text.Format;

import java.util.Date;

/**
 * @author Charles May
 * @author Brian Wing Shun Chan
 */
public class OpenSearchUtil {

	public static final int DEFAULT_NAMESPACE = 0;

	public static final int LIFERAY_NAMESPACE = 4;

	public static final int NO_NAMESPACE = 3;

	public static final int OS_NAMESPACE = 1;

	public static final int RELEVANCE_NAMESPACE = 2;

	public static Element addElement(
		Element el, String name, int namespaceType) {

		return el.addElement(getQName(name, namespaceType));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, Date value) {

		return addElement(el, name, namespaceType, _dateFormat.format(value));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, double value) {

		return addElement(el, name, namespaceType, String.valueOf(value));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, int value) {

		return addElement(el, name, namespaceType, String.valueOf(value));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, long value) {

		return addElement(el, name, namespaceType, String.valueOf(value));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, String value) {

		Element returnElement = el.addElement(getQName(name, namespaceType));

		returnElement.addCDATA(value);

		return returnElement;
	}

	public static void addLink(
		Element root, String searchURL, String rel, String keywords, int page,
		int itemsPerPage) {

		Element link = addElement(root, "link", DEFAULT_NAMESPACE);

		link.addAttribute("rel", rel);
		link.addAttribute(
			"href",
			searchURL + "?keywords=" + HttpUtil.encodeURL(keywords) + "&p=" +
				page + "&c=" + itemsPerPage + "&format=atom");
		link.addAttribute("type", "application/atom+xml");
	}

	public static Namespace getNamespace(int namespaceType) {
		Namespace namespace = null;

		if (namespaceType == DEFAULT_NAMESPACE) {
			namespace = SAXReaderUtil.createNamespace(
				"", "http://www.w3.org/2005/Atom");
		}
		else if (namespaceType == LIFERAY_NAMESPACE) {
			namespace = SAXReaderUtil.createNamespace(
				"liferay", "http://liferay.com/spec/liferay-search/1.0/");
		}
		else if (namespaceType == OS_NAMESPACE) {
			namespace = SAXReaderUtil.createNamespace(
				"opensearch", "http://a9.com/-/spec/opensearch/1.1/");
		}
		else if (namespaceType == RELEVANCE_NAMESPACE) {
			namespace = SAXReaderUtil.createNamespace(
				"relevance",
				"http://a9.com/-/opensearch/extensions/relevance/1.0/");
		}

		return namespace;
	}

	public static QName getQName(String name, int namespaceType) {
		if (NO_NAMESPACE == namespaceType) {
			return SAXReaderUtil.createQName(name);
		}
		else {
			return SAXReaderUtil.createQName(name, getNamespace(namespaceType));
		}
	}

	private static Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:sszzz");

}