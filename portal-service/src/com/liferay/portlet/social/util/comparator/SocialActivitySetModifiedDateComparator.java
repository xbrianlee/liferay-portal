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

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.social.model.SocialActivitySet;

/**
 * @author Matthew Kong
 */
public class SocialActivitySetModifiedDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"SocialActivitySet.modifiedDate ASC";

	public static final String ORDER_BY_DESC =
		"SocialActivitySet.modifiedDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedDate"};

	public SocialActivitySetModifiedDateComparator() {
		this(false);
	}

	public SocialActivitySetModifiedDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		SocialActivitySet activitySet1 = (SocialActivitySet)obj1;
		SocialActivitySet activitySet2 = (SocialActivitySet)obj2;

		long modifiedDate1 = activitySet1.getModifiedDate();
		long modifiedDate2 = activitySet2.getModifiedDate();

		int value = 0;

		if (modifiedDate1 == modifiedDate2) {
			value = 0;
		}
		else if (modifiedDate1 < modifiedDate2) {
			value = -1;
		}
		else {
			value = 1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}