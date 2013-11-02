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

package com.liferay.portal.util.comparator;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.model.PortletCategory;

import java.io.Serializable;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletCategoryComparator
	implements Comparator<PortletCategory>, Serializable {

	public PortletCategoryComparator(Locale locale) {
		_locale = locale;
	}

	@Override
	public int compare(
		PortletCategory portletCategory1, PortletCategory portletCategory2) {

		String name1 = LanguageUtil.get(_locale, portletCategory1.getName());

		String name2 = LanguageUtil.get(_locale, portletCategory2.getName());

		return name1.compareTo(name2);
	}

	private Locale _locale;

}