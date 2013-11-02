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

package com.liferay.portal.sharepoint;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Bruno Farache
 */
public class Property implements ResponseElement {

	public static final String OPEN_PARAGRAPH = "<p>";

	public Property(String key, ResponseElement value) {
		this(key, StringPool.NEW_LINE + value.parse(), false);
	}

	public Property(String key, String value) {
		this(key, value, true);
	}

	public Property(String key, String value, boolean newLine) {
		_key = key;
		_value = value;
		_newLine = newLine;
	}

	@Override
	public String parse() {
		StringBundler sb = new StringBundler(5);

		sb.append(OPEN_PARAGRAPH);
		sb.append(_key);
		sb.append(StringPool.EQUAL);
		sb.append(_value);

		if (_newLine) {
			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

	private String _key;
	private boolean _newLine;
	private String _value;

}