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
public class Leaf implements ResponseElement {

	public static final String OPEN_LI = "<li>";

	public Leaf(String key, ResponseElement value) {
		this(key, StringPool.NEW_LINE + value.parse(), true, false);
	}

	public Leaf(String key, String value, boolean useEqualSymbol) {
		this(key, value, useEqualSymbol, true);
	}

	public Leaf(
		String key, String value, boolean useEqualSymbol, boolean newLine) {

		_key = key;
		_value = value;
		_useEqualSymbol = useEqualSymbol;
		_newLine = newLine;
	}

	@Override
	public String parse() {
		StringBundler sb = new StringBundler(7);

		if (_useEqualSymbol) {
			sb.append(OPEN_LI);

			sb.append(_key);
			sb.append(StringPool.EQUAL);
			sb.append(_value);
		}
		else {
			sb.append(OPEN_LI);
			sb.append(_key);

			sb.append(StringPool.NEW_LINE);

			sb.append(OPEN_LI);
			sb.append(_value);
		}

		if (_newLine) {
			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

	private String _key;
	private boolean _newLine;
	private boolean _useEqualSymbol;
	private String _value;

}