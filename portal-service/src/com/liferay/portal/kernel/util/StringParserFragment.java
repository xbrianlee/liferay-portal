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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Connor McKay
 * @author Brian Wing Shun Chan
 */
public class StringParserFragment {

	public StringParserFragment(String chunk) {
		chunk = chunk.substring(1, chunk.length() - 1);

		if (Validator.isNull(chunk)) {
			throw new IllegalArgumentException("Fragment is null");
		}

		String[] chunkParts = chunk.split(StringPool.COLON, 2);

		if (chunkParts.length == 2) {
			_name = chunkParts[0];
			String pattern = chunkParts[1];

			if (Validator.isNull(pattern)) {
				throw new IllegalArgumentException("Pattern is null");
			}

			_pattern = Pattern.compile(pattern);
		}
		else {
			_name = chunkParts[0];
			_pattern = _defaultPattern;
		}

		if (Validator.isNull(_name)) {
			throw new IllegalArgumentException("Name is null");
		}

		if (_name.startsWith(StringPool.PERCENT)) {
			_name = _name.substring(1);

			if (Validator.isNull(_name)) {
				throw new IllegalArgumentException("Name is null");
			}

			_raw = true;
		}

		_token = StringPool.OPEN_CURLY_BRACE.concat(_name).concat(
			StringPool.CLOSE_CURLY_BRACE);
	}

	public String getName() {
		return _name;
	}

	public String getPattern() {
		return _pattern.toString();
	}

	public String getToken() {
		return _token;
	}

	public boolean isRaw() {
		return _raw;
	}

	public boolean matches(String parameter) {
		Matcher matcher = _pattern.matcher(parameter);

		return matcher.matches();
	}

	private static Pattern _defaultPattern = Pattern.compile("[^/\\.]+");

	private String _name;
	private Pattern _pattern;
	private boolean _raw;
	private String _token;

}