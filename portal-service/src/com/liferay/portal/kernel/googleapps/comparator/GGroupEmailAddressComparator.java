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

import com.liferay.portal.kernel.googleapps.GGroup;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class GGroupEmailAddressComparator implements Comparator<GGroup> {

	public GGroupEmailAddressComparator() {
		this(true);
	}

	public GGroupEmailAddressComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(GGroup user1, GGroup user2) {
		String emailAddress1 = user1.getEmailAddress();
		String emailAddress2 = user2.getEmailAddress();

		int value = emailAddress1.compareTo(emailAddress2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}