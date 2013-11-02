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
import com.liferay.portal.model.UserGroup;

/**
 * @author Brian Wing Shun Chan
 */
public class UserGroupDescriptionComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"UserGroup.description ASC, UserGroup.name ASC";

	public static final String ORDER_BY_DESC =
		"UserGroup.description DESC, UserGroup.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"description", "name"};

	public UserGroupDescriptionComparator() {
		this(false);
	}

	public UserGroupDescriptionComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		UserGroup userGroup1 = (UserGroup)obj1;
		UserGroup userGroup2 = (UserGroup)obj2;

		String description1 = userGroup1.getDescription();
		String description2 = userGroup2.getDescription();

		int value = description1.compareTo(description2);

		if (value == 0) {
			String name1 = userGroup1.getName();
			String name2 = userGroup2.getName();

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