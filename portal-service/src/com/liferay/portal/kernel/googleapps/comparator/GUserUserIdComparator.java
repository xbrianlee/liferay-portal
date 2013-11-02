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
public class GUserUserIdComparator implements Comparator<GUser> {

	public GUserUserIdComparator() {
		this(true);
	}

	public GUserUserIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(GUser user1, GUser user2) {
		long userId1 = user1.getUserId();
		long userId2 = user2.getUserId();

		int value = 0;

		if (userId1 < userId2) {
			value = -1;
		}
		else if (userId1 > userId2) {
			value = 1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}