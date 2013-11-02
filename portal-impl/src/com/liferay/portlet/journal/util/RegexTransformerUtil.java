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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 */
public class RegexTransformerUtil {

	public static List<Pattern> getPatterns() {
		return _instance._patterns;
	}

	public static List<String> getReplacements() {
		return _instance._replacements;
	}

	private RegexTransformerUtil() {
		_patterns = new ArrayList<Pattern>();
		_replacements = new ArrayList<String>();

		for (int i = 0; i < 100; i++) {
			String regex = PropsUtil.get(
				"journal.transformer.regex.pattern." + i);
			String replacement = PropsUtil.get(
				"journal.transformer.regex.replacement." + i);

			if (Validator.isNull(regex) || Validator.isNull(replacement)) {
				break;
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					"Pattern " + regex + " will be replaced with " +
						replacement);
			}

			_patterns.add(Pattern.compile(regex));
			_replacements.add(replacement);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RegexTransformerUtil.class);

	private static RegexTransformerUtil _instance = new RegexTransformerUtil();

	private List<Pattern> _patterns;
	private List<String> _replacements;

}