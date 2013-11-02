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

import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FriendlyURLNormalizer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.Normalizer;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
@DoPrivileged
public class FriendlyURLNormalizerImpl implements FriendlyURLNormalizer {

	@Override
	public String normalize(String friendlyURL) {
		return normalize(friendlyURL, _friendlyURLPattern);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #normalize(String, Pattern)}
	 */
	@Override
	public String normalize(String friendlyURL, char[] replaceChars) {
		if (Validator.isNull(friendlyURL)) {
			return friendlyURL;
		}

		friendlyURL = GetterUtil.getString(friendlyURL);
		friendlyURL = StringUtil.toLowerCase(friendlyURL);
		friendlyURL = Normalizer.normalizeToAscii(friendlyURL);

		StringBuilder sb = null;

		int index = 0;

		for (int i = 0; i < friendlyURL.length(); i++) {
			char c = friendlyURL.charAt(i);

			if ((Arrays.binarySearch(_REPLACE_CHARS, c) >= 0) ||
				((replaceChars != null) &&
				 ArrayUtil.contains(replaceChars, c))) {

				if (sb == null) {
					sb = new StringBuilder();
				}

				if (i > index) {
					sb.append(friendlyURL.substring(index, i));
				}

				sb.append(CharPool.DASH);

				index = i + 1;
			}
		}

		if (sb != null) {
			if (index < friendlyURL.length()) {
				sb.append(friendlyURL.substring(index));
			}

			friendlyURL = sb.toString();
		}

		while (friendlyURL.contains(StringPool.DOUBLE_DASH)) {
			friendlyURL = StringUtil.replace(
				friendlyURL, StringPool.DOUBLE_DASH, StringPool.DASH);
		}

		/*if (friendlyURL.startsWith(StringPool.DASH)) {
			friendlyURL = friendlyURL.substring(1);
		}

		if (friendlyURL.endsWith(StringPool.DASH)) {
			friendlyURL = friendlyURL.substring(0, friendlyURL.length() - 1);
		}*/

		return friendlyURL;
	}

	@Override
	public String normalize(String friendlyURL, Pattern friendlyURLPattern) {
		if (Validator.isNull(friendlyURL)) {
			return friendlyURL;
		}

		friendlyURL = StringUtil.toLowerCase(friendlyURL);
		friendlyURL = Normalizer.normalizeToAscii(friendlyURL);
		friendlyURL = friendlyURL.replaceAll(
			friendlyURLPattern.pattern(), StringPool.DASH);
		friendlyURL = friendlyURL.replaceAll(
			_friendlyURLHyphenPattern.pattern(), StringPool.DASH);

		return friendlyURL;
	}

	private static final char[] _REPLACE_CHARS;

	static {
		char[] replaceChars = new char[] {
			' ', ',', '\\', '\'', '\"', '(', ')', '[', ']', '{', '}', '?', '#',
			'@', '+', '~', ';', '$', '%', '!', '=', ':', '&', '\u00a3',
			'\u2013', '\u2014', '\u2018', '\u2019', '\u201c', '\u201d'
		};

		Arrays.sort(replaceChars);

		_REPLACE_CHARS = replaceChars;
	}

	private static Pattern _friendlyURLHyphenPattern = Pattern.compile(
		"(-)\\1+");
	private static Pattern _friendlyURLPattern = Pattern.compile(
		"[^a-z0-9./_-]");

}