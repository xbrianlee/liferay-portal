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

package com.liferay.taglib.util;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class InlineUtil {

	public static String buildDynamicAttributes(
		Map<String, Object> dynamicAttributes) {

		if ((dynamicAttributes == null) || dynamicAttributes.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(dynamicAttributes.size() * 4);

		for (Map.Entry<String, Object> entry : dynamicAttributes.entrySet()) {
			String key = entry.getKey();
			String value = String.valueOf(entry.getValue());

			if (!key.equals("class")) {
				sb.append(key);
				sb.append("=\"");
				sb.append(value);
				sb.append("\" ");
			}
		}

		return sb.toString();
	}

}