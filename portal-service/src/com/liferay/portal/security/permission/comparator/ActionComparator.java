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
public class ActionComparator implements Comparator<String>, Serializable {

	public ActionComparator(Locale locale) {
		_locale = locale;
	}

	@Override
	public int compare(String action1, String action2) {
		action1 = ResourceActionsUtil.getAction(_locale, action1);
		action2 = ResourceActionsUtil.getAction(_locale, action2);

		return action1.compareTo(action2);
	}

	private Locale _locale;

}