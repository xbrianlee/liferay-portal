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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.InitUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class XMLFormatter {

	public static void main(String[] args) {
		InitUtil.initWithSpring();

		String fileName = System.getProperty("xml.formatter.file");
		boolean stripComments = GetterUtil.getBoolean(
			System.getProperty("xml.formatter.strip.comments"));

		if (Validator.isNull(fileName)) {
			throw new IllegalArgumentException();
		}

		try {
			String xml = FileUtil.read(fileName);

			if (stripComments) {
				xml = HtmlUtil.stripComments(xml);
			}

			xml = com.liferay.util.xml.XMLFormatter.toString(xml);

			FileUtil.write(fileName, xml);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}