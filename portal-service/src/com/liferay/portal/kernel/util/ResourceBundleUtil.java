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

package com.liferay.portal.kernel.util;

import java.text.MessageFormat;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Shuyang Zhou
 * @author Neil Griffin
 */
public class ResourceBundleUtil {

	public static final String NULL_VALUE = "NULL_VALUE";

	public static String getString(
		ResourceBundle resourceBundle, Locale locale, String key,
		Object[] arguments) {

		String value = getString(resourceBundle, key);

		if (value == null) {
			return null;
		}

		// Get the value associated with the specified key, and substitute any
		// arguuments like {0}, {1}, {2}, etc. with the specified argument
		// values.

		if (ArrayUtil.isNotEmpty(arguments)) {
			MessageFormat messageFormat = new MessageFormat(value, locale);

			value = messageFormat.format(arguments);
		}

		return value;
	}

	public static String getString(ResourceBundle resourceBundle, String key) {
		ResourceBundleThreadLocal.setReplace(true);

		String value = null;

		try {
			value = resourceBundle.getString(key);
		}
		finally {
			ResourceBundleThreadLocal.setReplace(false);
		}

		if (NULL_VALUE.equals(value)) {
			value = null;
		}

		return value;
	}

}