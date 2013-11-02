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

package com.liferay.portal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class JavaFieldsParser {

	public static String parse(ClassLoader classLoader, String s) {
		int x = s.indexOf("${");

		if (x == -1) {
			return s;
		}

		List<String> replaceFrom = new ArrayList<String>();
		List<String> replaceWith = new ArrayList<String>();

		while (true) {
			if (x == -1) {
				break;
			}

			int y = s.indexOf("}", x);

			if (y == -1) {
				break;
			}

			String javaSnippet = s.substring(x + 2, y);

			if (_log.isDebugEnabled()) {
				_log.debug("Java snippet " + javaSnippet);
			}

			String className = _getClassName(javaSnippet);

			if (_log.isDebugEnabled()) {
				_log.debug("Class name " + className);
			}

			if (className == null) {
				break;
			}

			Class<?> clazz = null;

			try {
				clazz = classLoader.loadClass(className);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to load class " + className);
				}

				break;
			}

			String fieldName = _getFieldName(javaSnippet);

			if (_log.isDebugEnabled()) {
				_log.debug("Field name " + fieldName);
			}

			if (fieldName == null) {
				break;
			}

			String fieldValue = null;

			try {
				Field field = clazz.getField(fieldName);

				fieldValue = String.valueOf(field.get(null));

				if (_log.isDebugEnabled()) {
					_log.debug("Field value " + fieldValue);
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to load field " + fieldName);
				}

				break;
			}

			replaceFrom.add("${".concat(javaSnippet).concat("}"));
			replaceWith.add(fieldValue);

			x = s.indexOf("${", y);
		}

		if (replaceFrom.isEmpty()) {
			return s;
		}

		return StringUtil.replace(
			s, replaceFrom.toArray(new String[replaceFrom.size()]),
			replaceWith.toArray(new String[replaceWith.size()]));
	}

	private static String _getClassName(String javaSnippet) {
		int x = javaSnippet.lastIndexOf(".");

		if (x == -1) {
			return null;
		}

		return javaSnippet.substring(0, x);
	}

	private static String _getFieldName(String javaSnippet) {
		int x = javaSnippet.lastIndexOf(".");

		if (x == -1) {
			return null;
		}

		return javaSnippet.substring(x + 1);
	}

	private static Log _log = LogFactoryUtil.getLog(JavaFieldsParser.class);

}