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

package com.liferay.portal.security.permission.comparator;

import com.liferay.portal.security.permission.ResourceActionsUtil;

import java.io.Serializable;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class ModelResourceComparator
	implements Comparator<String>, Serializable {

	public ModelResourceComparator(Locale locale) {
		_locale = locale;
	}

	@Override
	public int compare(String resource1, String resource2) {
		resource1 = ResourceActionsUtil.getModelResource(_locale, resource1);
		resource2 = ResourceActionsUtil.getModelResource(_locale, resource2);

		return resource1.compareTo(resource2);
	}

	private Locale _locale;

}