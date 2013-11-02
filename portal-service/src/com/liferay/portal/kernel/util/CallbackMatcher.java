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

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-6872.
 * </p>
 *
 * @author Jonathan Potter
 */
public class CallbackMatcher {

	public String replaceMatches(CharSequence charSequence, Callback callback) {
		Matcher matcher = _pattern.matcher(charSequence);

		StringBuilder sb = new StringBuilder(charSequence);

		int offset = 0;

		while (matcher.find()) {
			MatchResult matchResult = matcher.toMatchResult();

			String replacement = callback.foundMatch(matchResult);

			if (replacement == null) {
				continue;
			}

			int matchStart = offset + matchResult.start();
			int matchEnd = offset + matchResult.end();

			sb.replace(matchStart, matchEnd, replacement);

			int matchLength = matchResult.end() - matchResult.start();
			int lengthChange = replacement.length() - matchLength;

			offset += lengthChange;
		}

		return sb.toString();
	}

	public void setRegex(String regex) {
		_pattern = Pattern.compile(regex);
	}

	public interface Callback {

		public String foundMatch(MatchResult matchResult);

	}

	private Pattern _pattern;

}