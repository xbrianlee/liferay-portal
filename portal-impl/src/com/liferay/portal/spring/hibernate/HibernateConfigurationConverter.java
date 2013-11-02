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

package com.liferay.portal.spring.hibernate;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Converter;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-5363.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class HibernateConfigurationConverter implements Converter<String> {

	@Override
	public String convert(String input) {
		String output = input;

		try {
			output = doConvert(input);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return output;
	}

	public void setClassNames(Map<String, String> classNames) {
		_classNames = classNames;
	}

	protected String doConvert(String input) throws Exception {
		if ((_classNames == null) || _classNames.isEmpty()) {
			return input;
		}

		Document document = SAXReaderUtil.read(input);

		Element rootElement = document.getRootElement();

		Iterator<Element> itr = rootElement.elementIterator("class");

		while (itr.hasNext()) {
			Element classElement = itr.next();

			String oldName = classElement.attributeValue("name");

			String newName = _classNames.get(oldName);

			if (newName != null) {
				classElement.addAttribute("name", newName);
			}
		}

		return document.asXML();
	}

	private static Log _log = LogFactoryUtil.getLog(
		HibernateConfigurationConverter.class);

	private Map<String, String> _classNames;

}