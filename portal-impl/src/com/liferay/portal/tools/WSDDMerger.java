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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.util.InitUtil;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Brian Wing Shun Chan
 */
public class WSDDMerger {

	public static void main(String[] args) {
		InitUtil.initWithSpring();

		new WSDDMerger(args[0], args[1]);
	}

	public static void merge(String source, String destination)
		throws DocumentException, IOException {

		// Source

		File sourceFile = new File(source);

		Document document = SAXReaderUtil.read(sourceFile);

		Element rootElement = document.getRootElement();

		List<Element> sourceServiceElements = rootElement.elements("service");

		if (sourceServiceElements.isEmpty()) {
			return;
		}

		// Destination

		File destinationFile = new File(destination);

		document = SAXReaderUtil.read(destinationFile);

		rootElement = document.getRootElement();

		Map<String, Element> servicesMap = new TreeMap<String, Element>();

		List<Element> serviceElements = rootElement.elements("service");

		for (Element serviceElement : serviceElements) {
			String name = serviceElement.attributeValue("name");

			servicesMap.put(name, serviceElement);

			serviceElement.detach();
		}

		for (Element serviceElement : sourceServiceElements) {
			String name = serviceElement.attributeValue("name");

			servicesMap.put(name, serviceElement);

			serviceElement.detach();
		}

		for (Map.Entry<String, Element> entry : servicesMap.entrySet()) {
			Element serviceElement = entry.getValue();

			rootElement.add(serviceElement);
		}

		String content = document.formattedString();

		content = StringUtil.replace(content, "\"/>", "\" />");

		FileUtil.write(destination, content, true);
	}

	public WSDDMerger(String source, String destination) {
		try {
			merge(source, destination);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}