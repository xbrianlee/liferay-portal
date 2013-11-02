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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class LocaleTransformerListener extends BaseTransformerListener {

	@Override
	public String onScript(
		String script, String xml, String languageId,
		Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onScript");
		}

		return StringUtil.replace(script, "@language_id@", languageId);
	}

	@Override
	public String onXml(
		String xml, String languageId, Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onXml");
		}

		return replace(xml, languageId);
	}

	protected void replace(Element root, String languageId) {
		List<Element> elements = root.elements();

		int listIndex = elements.size() - 1;

		while (listIndex >= 0) {
			Element element = elements.get(listIndex);

			String tempLanguageId = element.attributeValue(
				"language-id", languageId);

			if (!StringUtil.equalsIgnoreCase(tempLanguageId, languageId)) {
				root.remove(element);
			}
			else {
				replace(element, languageId);
			}

			listIndex--;
		}
	}

	protected String replace(String xml, String languageId) {
		if (xml == null) {
			return xml;
		}

		try {
			Document document = SAXReaderUtil.read(xml);

			Element rootElement = document.getRootElement();

			String defaultLanguageId = LocaleUtil.toLanguageId(
				LocaleUtil.getSiteDefault());

			String[] availableLocales = StringUtil.split(
				rootElement.attributeValue(
					"available-locales", defaultLanguageId));

			String defaultLocale = rootElement.attributeValue(
				"default-locale", defaultLanguageId);

			boolean supportedLocale = false;

			for (String availableLocale : availableLocales) {
				if (StringUtil.equalsIgnoreCase(availableLocale, languageId)) {
					supportedLocale = true;

					break;
				}
			}

			if (!supportedLocale) {
				replace(rootElement, defaultLocale);
			}
			else {
				replace(rootElement, languageId);
			}

			xml = DDMXMLUtil.formatXML(document);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return xml;
	}

	private static Log _log = LogFactoryUtil.getLog(
		LocaleTransformerListener.class);

}