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

package com.liferay.util.lucene;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Mirco Tamburini
 * @author Josiah Goh
 */
public class KeywordsUtil {

	public static final String[] SPECIAL = new String[] {
		"+", "-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^", "\"", "~",
		"*", "?", ":", "\\"
	};

	public static String escape(String text) {
		for (int i = SPECIAL.length - 1; i >= 0; i--) {
			text = StringUtil.replace(
				text, SPECIAL[i], StringPool.BACK_SLASH + SPECIAL[i]);
		}

		return text;
	}

	public static String toFuzzy(String keywords) {
		if (keywords == null) {
			return null;
		}

		if (!keywords.endsWith(StringPool.TILDE)) {
			keywords = keywords + StringPool.TILDE;
		}

		return keywords;
	}

	public static String toWildcard(String keywords) {
		if (keywords == null) {
			return null;
		}

		if (!keywords.endsWith(StringPool.STAR)) {
			keywords = keywords + StringPool.STAR;
		}

		return keywords;
	}

}