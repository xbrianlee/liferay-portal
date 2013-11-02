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

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Eduardo Lundgren
 */
public class AggregateUtil {

	public static String updateRelativeURLs(String content, String baseURL) {
		content = StringUtil.replace(
			content, _CSS_PATH_TYPES, _CSS_PATH_PLACEHOLDERS);

		content = StringUtil.replace(
			content,
			new String[] {
				"[$RELATIVE_1$]", "[$RELATIVE_2$]", "[$RELATIVE_3$]"
			},
			new String[] {
				"url('" + baseURL, "url(\"" + baseURL, "url(" + baseURL
			});

		content = StringUtil.replace(
			content, _CSS_PATH_PLACEHOLDERS, _CSS_PATH_TYPES);

		return content;
	}

	private AggregateUtil() {
	}

	private static final String[] _CSS_PATH_PLACEHOLDERS = new String[] {
		"[$EMPTY_1$]", "[$EMPTY_2$]", "[$EMPTY_3$]", "[$TOKEN_1$]",
		"[$TOKEN_2$]", "[$TOKEN_3$]", "[$ABSOLUTE_1$]", "[$ABSOLUTE_2$]",
		"[$ABSOLUTE_3$]", "[$ABSOLUTE_4$]", "[$ABSOLUTE_5$]", "[$ABSOLUTE_6$]",
		"[$ABSOLUTE_7$]", "[$ABSOLUTE_8$]", "[$ABSOLUTE_9$]", "[$RELATIVE_1$]",
		"[$RELATIVE_2$]", "[$RELATIVE_3$]"
	};

	private static final String[] _CSS_PATH_TYPES = new String[] {
		"url('')", "url(\"\")", "url()", "url('@theme_image_path@",
		"url(\"@", "url(@", "url('http://", "url(\"http://", "url(http://",
		"url('https://", "url(\"https://", "url(https://", "url('/", "url(\"/",
		"url(/", "url('", "url(\"", "url("
	};

}