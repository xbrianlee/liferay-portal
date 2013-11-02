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

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.model.UserTracker;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class UserTrackerModifiedDateComparator
	implements Comparator<UserTracker>, Serializable {

	public UserTrackerModifiedDateComparator() {
		this(false);
	}

	public UserTrackerModifiedDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(UserTracker userTracker1, UserTracker userTracker2) {
		int value = DateUtil.compareTo(
			userTracker1.getModifiedDate(), userTracker2.getModifiedDate());

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}