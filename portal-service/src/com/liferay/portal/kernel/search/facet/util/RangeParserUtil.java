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

package com.liferay.portal.kernel.search.facet.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Raymond Augé
 */
public class RangeParserUtil {

	public static String[] parserRange(String range) {
		range = StringUtil.replace(
			range,
			new String[] {
				StringPool.OPEN_CURLY_BRACE, StringPool.CLOSE_CURLY_BRACE
			},
			new String[] {
				StringPool.OPEN_BRACKET, StringPool.CLOSE_BRACKET
			}
		);

		int x = range.indexOf(StringPool.OPEN_BRACKET);
		int y = range.indexOf(" TO ");
		int z = range.indexOf(StringPool.CLOSE_BRACKET);

		String lower = range.substring(x + 1, y).trim();
		String upper = range.substring(y + 4, z).trim();

		return new String[] {lower, upper};
	}

}