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

package com.liferay.portal.search;

import com.liferay.portal.kernel.search.Collator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Daniela Zapata
 * @author David Gonzalez
 */
public class DefaultCollatorImpl implements Collator {

	@Override
	public String collate(
		Map<String, List<String>> suggestionsMap, List<String> tokens) {

		StringBundler sb = new StringBundler(tokens.size() * 2);

		for (String token : tokens) {
			List<String> suggestions = suggestionsMap.get(token);

			if ((suggestions != null) && !suggestions.isEmpty()) {
				String suggestion = suggestions.get(0);

				if (Character.isUpperCase(token.charAt(0))) {
					suggestion = StringUtil.toUpperCase(
						suggestion.substring(0, 1)).concat(
							suggestion.substring(1));
				}

				sb.append(suggestion);
				sb.append(StringPool.SPACE);
			}
			else {
				sb.append(token);
				sb.append(StringPool.SPACE);
			}
		}

		String collatedValue = sb.toString();

		return collatedValue.trim();
	}

}