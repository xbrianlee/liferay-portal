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

package com.liferay.portal.parsers.bbcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Iliyan Peychev
 */
public class BBCodeLexer {

	public BBCodeLexer(String data) {
		_matcher = _pattern.matcher(data);
	}

	public int getLastIndex() {
		return _matcher.end();
	}

	public BBCodeToken getNextBBCodeToken() {
		if (!_matcher.find()) {
			return null;
		}

		return new BBCodeToken(
			_matcher.group(1), _matcher.group(2), _matcher.group(3),
			_matcher.start(), _matcher.end());
	}

	private static Pattern _pattern = Pattern.compile(
		"(?:\\[((?:[a-z]|\\*){1,16})(?:=([^\\x00-\\x1F\"'()<>\\[\\]]" +
			"{1,2083}))?\\])|(?:\\[/([a-z]{1,16})\\])",
		Pattern.CASE_INSENSITIVE);

	private Matcher _matcher;

}