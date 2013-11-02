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
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class TokensTransformerListener extends BaseTransformerListener {

	public static final String TEMP_ESCAPED_AT_CLOSE =
		"[$_TEMP_ESCAPED_AT_CLOSE$]";

	public static final String TEMP_ESCAPED_AT_OPEN =
		"[$TEMP_ESCAPED_AT_OPEN$]";

	@Override
	public String onOutput(
		String output, String languageId, Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onOutput");
		}

		return replace(output, tokens);
	}

	@Override
	public String onScript(
		String script, String xml, String languageId,
		Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onScript");
		}

		return replace(script, tokens);
	}

	/**
	 * Replace the standard tokens in a given string with their values.
	 *
	 * @return the processed string
	 */
	protected String replace(String s, Map<String, String> tokens) {
		Set<Map.Entry<String, String>> tokensSet = tokens.entrySet();

		if (tokensSet.size() == 0) {
			return s;
		}

		List<String> escapedKeysList = new ArrayList<String>();
		List<String> escapedValuesList = new ArrayList<String>();

		List<String> keysList = new ArrayList<String>();
		List<String> valuesList = new ArrayList<String>();

		List<String> tempEscapedKeysList = new ArrayList<String>();
		List<String> tempEscapedValuesList = new ArrayList<String>();

		for (Map.Entry<String, String> entry : tokensSet) {
			String key = entry.getKey();
			String value = GetterUtil.getString(entry.getValue());

			if (Validator.isNotNull(key)) {
				String escapedKey =
					StringPool.AT + StringPool.AT + key + StringPool.AT +
						StringPool.AT;

				String actualKey = StringPool.AT + key + StringPool.AT;

				String tempEscapedKey =
					TEMP_ESCAPED_AT_OPEN + key + TEMP_ESCAPED_AT_CLOSE;

				escapedKeysList.add(escapedKey);
				escapedValuesList.add(tempEscapedKey);

				keysList.add(actualKey);
				valuesList.add(value);

				tempEscapedKeysList.add(tempEscapedKey);
				tempEscapedValuesList.add(actualKey);
			}
		}

		s = StringUtil.replace(
			s, escapedKeysList.toArray(new String[escapedKeysList.size()]),
			escapedValuesList.toArray(new String[escapedValuesList.size()]));

		s = StringUtil.replace(
			s, keysList.toArray(new String[keysList.size()]),
			valuesList.toArray(new String[valuesList.size()]));

		s = StringUtil.replace(
			s,
			tempEscapedKeysList.toArray(new String[tempEscapedKeysList.size()]),
			tempEscapedValuesList.toArray(
				new String[tempEscapedValuesList.size()]));

		return s;
	}

	private static Log _log = LogFactoryUtil.getLog(
		TokensTransformerListener.class);

}