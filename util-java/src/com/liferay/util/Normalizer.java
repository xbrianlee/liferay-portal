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

package com.liferay.util;

import com.liferay.ibm.icu.text.Transliterator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class Normalizer {

	public static String normalizeToAscii(String s) {
		if (!_hasNonASCIICode(s)) {
			return s;
		}

		String normalizedText = _transliterator.transform(s);

		return StringUtil.replace(
			normalizedText, _UNICODE_TEXT, _NORMALIZED_TEXT);
	}

	private static boolean _hasNonASCIICode(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) > 127) {
				return true;
			}
		}

		return false;
	}

	private static final String[] _NORMALIZED_TEXT = new String[] {
		"l", "'", "\""
	};

	private static final String[] _UNICODE_TEXT = new String[] {
		"\u0142", "\u02B9", "\u02BA"
	};

	private static Transliterator _transliterator = Transliterator.getInstance(
		"Greek-Latin; Cyrillic-Latin; NFD; [:Nonspacing Mark:] Remove; NFC");

}