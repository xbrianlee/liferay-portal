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

package com.liferay.portlet.social.util.comparator;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityCounterNameComparator implements Comparator<String> {

	public SocialActivityCounterNameComparator(Locale locale) {
		_locale = locale;
	}

	@Override
	public int compare(
		String activityCounterName1, String activityCounterName2) {

		String name1 = LanguageUtil.get(
			_locale, "social.counter." + activityCounterName1);
		String name2 = LanguageUtil.get(
			_locale, "social.counter." + activityCounterName2);

		return name1.compareTo(name2);
	}

	private Locale _locale;

}