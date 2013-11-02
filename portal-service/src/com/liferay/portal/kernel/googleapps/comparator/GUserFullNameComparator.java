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

package com.liferay.portal.kernel.googleapps.comparator;

import com.liferay.portal.kernel.googleapps.GUser;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class GUserFullNameComparator implements Comparator<GUser> {

	public GUserFullNameComparator() {
		this(true);
	}

	public GUserFullNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(GUser user1, GUser user2) {
		String fullName1 = user1.getFullName();
		String fullName2 = user2.getFullName();

		int value = fullName1.compareTo(fullName2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}