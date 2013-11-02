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

package com.liferay.portal.kernel.search;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class CollatorUtil {

	public static String collate(
			Map<String, List<String>> suggestions, List<String> keywords)
		throws SearchException {

		return _getCollator().collate(suggestions, keywords);
	}

	public void setCollator(Collator collator) {
		_collator = collator;
	}

	private static Collator _getCollator() {
		return _collator;
	}

	private static Collator _collator;

}