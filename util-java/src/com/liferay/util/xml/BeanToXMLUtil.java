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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.xml.Element;

import java.lang.reflect.Method;

import java.util.List;

/**
 * @author Charles May
 */
public class BeanToXMLUtil {

	public static void addBean(Object obj, Element parentEl) {
		String classNameWithoutPackage = getClassNameWithoutPackage(
			obj.getClass().getName());

		Element el = parentEl.addElement(classNameWithoutPackage);

		addFields(obj, el);
	}

	public static void addFields(Object obj, Element parentEl) {
		Method[] methods = obj.getClass().getMethods();

		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];

			if (method.getName().startsWith("get") &&
				!method.getName().equals("getClass")) {

				String memberName = StringUtil.replace(
					method.getName(), "get", StringPool.BLANK);

				memberName = TextFormatter.format(memberName, TextFormatter.I);
				memberName = TextFormatter.format(memberName, TextFormatter.K);

				try {
					Object returnValue = method.invoke(obj, new Object[] {});

					if (returnValue instanceof List<?>) {
						List<Object> list = (List<Object>)returnValue;

						Element listEl = parentEl.addElement(memberName);

						for (int j = 0; j < list.size(); j++) {
							addBean(list.get(j), listEl);
						}
					}
					else {
						DocUtil.add(
							parentEl, memberName, returnValue.toString());
					}
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(e.getMessage());
					}
				}
			}
		}
	}

	public static String getClassNameWithoutPackage(String className) {
		String[] classNameArray = StringUtil.split(className, CharPool.PERIOD);

		String classNameWithoutPackage =
			classNameArray[classNameArray.length - 1];

		classNameWithoutPackage = TextFormatter.format(
			classNameWithoutPackage, TextFormatter.I);
		classNameWithoutPackage = TextFormatter.format(
			classNameWithoutPackage, TextFormatter.K);

		return classNameWithoutPackage;
	}

	private static Log _log = LogFactoryUtil.getLog(BeanToXMLUtil.class);

}