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

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;

/**
 * @author Brian Wing Shun Chan
 */
public class GroupTypeComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "groupType ASC, groupName ASC";

	public static final String ORDER_BY_DESC = "groupType DESC, groupName DESC";

	public static final String[] ORDER_BY_FIELDS = {"groupType", "groupName"};

	public GroupTypeComparator() {
		this(false);
	}

	public GroupTypeComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		Group group1 = (Group)obj1;
		Group group2 = (Group)obj2;

		int value = 0;

		if (group1.getType() > group2.getType()) {
			value = 1;
		}
		else if (group1.getType() < group2.getType()) {
			value = -1;
		}

		if (value == 0) {
			String name1 = group1.getName();
			String name2 = group2.getName();

			value = name1.compareTo(name2);
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