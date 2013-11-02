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

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class SafeProperties extends Properties {

	public SafeProperties() {
		super();
	}

	@Override
	public synchronized Object get(Object key) {
		Object value = super.get(key);

		value = _decode((String)value);

		return value;
	}

	public String getEncodedProperty(String key) {
		return super.getProperty(key);
	}

	@Override
	public String getProperty(String key) {
		return (String)get(key);
	}

	@Override
	public synchronized Object put(Object key, Object value) {
		if (key == null) {
			return null;
		}

		if (value == null) {
			return super.remove(key);
		}

		value = _encode((String)value);

		return super.put(key, value);
	}

	@Override
	public synchronized Object remove(Object key) {
		if (key == null) {
			return null;
		}
		else {
			return super.remove(key);
		}
	}

	private static String _decode(String value) {
		return StringUtil.replace(
			value, _SAFE_NEWLINE_CHARACTER, StringPool.NEW_LINE);
	}

	private static String _encode(String value) {
		return StringUtil.replace(
			value,
			new String[] {
				StringPool.RETURN_NEW_LINE, StringPool.NEW_LINE,
				StringPool.RETURN
			},
			new String[] {
				_SAFE_NEWLINE_CHARACTER, _SAFE_NEWLINE_CHARACTER,
				_SAFE_NEWLINE_CHARACTER
			});
	}

	private static final String _SAFE_NEWLINE_CHARACTER =
		"_SAFE_NEWLINE_CHARACTER_";

}