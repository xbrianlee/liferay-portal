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

/**
 * @author Samuel Kong
 */
public class CSVUtil {

	public static String encode(Object obj) {
		Class<?> clazz = obj.getClass();

		if (!clazz.isArray()) {
			return encode(String.valueOf(obj));
		}

		Object[] array = (Object[])obj;

		if (array.length > 0) {
			return encode(String.valueOf(array[0]));
		}

		return null;
	}

	public static String encode(String s) {
		if (s == null) {
			return null;
		}

		if ((s.indexOf(CharPool.COMMA) < 0) &&
			(s.indexOf(CharPool.QUOTE) < 0) &&
			(s.indexOf(CharPool.NEW_LINE) < 0) &&
			(s.indexOf(CharPool.RETURN) < 0)) {

			return s;
		}

		s = StringUtil.replace(s, StringPool.QUOTE, StringPool.DOUBLE_QUOTE);

		return StringPool.QUOTE.concat(s.concat(StringPool.QUOTE));
	}

}