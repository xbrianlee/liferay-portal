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

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class AUIUtil {

	/**
	 * @deprecated As of 6.2.0
	 */
	public static final String BUTTON_INPUT_PREFIX = "btn-input";

	public static final String BUTTON_PREFIX = "btn";

	public static final String FIELD_PREFIX = "field";

	/**
	 * @deprecated As of 6.2.0
	 */
	public static final String INPUT_PREFIX = "field-input";

	/**
	 * @deprecated As of 6.2.0
	 */
	public static final String LABEL_CHOICE_PREFIX = "choice-label";

	/**
	 * @deprecated As of 6.2.0
	 */
	public static final String LABEL_FIELD_PREFIX = "field-label";

	public static String buildCss(
		String prefix, boolean disabled, boolean first, boolean last,
		String cssClass) {

		StringBundler sb = new StringBundler();

		sb.append(prefix);

		if (disabled) {
			sb.append(StringPool.SPACE);
			sb.append("disabled");
		}

		if (first) {
			sb.append(StringPool.SPACE);
			sb.append(prefix);
			sb.append("-first");
		}
		else if (last) {
			sb.append(StringPool.SPACE);
			sb.append(prefix);
			sb.append("-last");
		}

		if (Validator.isNotNull(cssClass)) {
			sb.append(StringPool.SPACE);
			sb.append(cssClass);
		}

		return sb.toString();
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #buildCss(String, boolean,
	 *             boolean, boolean, String)}
	 */
	public static String buildCss(
			String prefix, String baseTypeCss, boolean disabled, boolean first,
			boolean last, String cssClass) {

		return buildCss(prefix, disabled, first, last, cssClass);
	}

	public static String buildData(Map<String, Object> data) {
		if ((data == null) || data.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(data.size() * 5);

		for (Map.Entry<String, Object> entry : data.entrySet()) {
			String dataKey = entry.getKey();
			String dataValue = String.valueOf(entry.getValue());

			sb.append("data-");
			sb.append(dataKey);
			sb.append("=\"");
			sb.append(dataValue);
			sb.append("\" ");
		}

		return sb.toString();
	}

	public static String buildLabel(
		String baseType, boolean inlineField, boolean showForLabel,
		String forLabel) {

		StringBundler sb = new StringBundler(7);

		if (baseType.equals("boolean")) {
			baseType = "checkbox";
		}

		if (baseType.equals("checkbox") || baseType.equals("radio")) {
			sb.append("class=\"");
			sb.append(baseType);

			if (inlineField) {
				sb.append(" inline");
			}

			sb.append("\" ");
		}
		else {
			sb.append("class=\"control-label\" ");
		}

		if (showForLabel) {
			sb.append("for=\"");
			sb.append(forLabel);
			sb.append("\"");
		}

		return sb.toString();
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #buildLabel(String, boolean,
	 *             boolean, String)}
	 */
	public static String buildLabel(
		String inlineLabel, boolean showForLabel, String forLabel,
		boolean choiceField) {

		return buildLabel(StringPool.BLANK, false, showForLabel, forLabel);
	}

}