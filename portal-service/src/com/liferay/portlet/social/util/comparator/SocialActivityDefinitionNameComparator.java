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

import com.liferay.portlet.social.model.SocialActivityDefinition;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityDefinitionNameComparator
	implements Comparator<SocialActivityDefinition> {

	public SocialActivityDefinitionNameComparator(Locale locale) {
		_locale = locale;
	}

	@Override
	public int compare(
		SocialActivityDefinition activityDefinition1,
		SocialActivityDefinition activityDefinition2) {

		String name1 = activityDefinition1.getName(_locale);
		String name2 = activityDefinition2.getName(_locale);

		return name1.compareTo(name2);
	}

	private Locale _locale;

}