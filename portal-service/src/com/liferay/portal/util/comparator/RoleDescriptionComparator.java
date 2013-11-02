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
import com.liferay.portal.model.Role;

/**
 * @author Brian Wing Shun Chan
 */
public class RoleDescriptionComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"Role_.description ASC, Role_.name ASC";

	public static final String ORDER_BY_DESC =
		"Role_.description DESC, Role_.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"description", "name"};

	public RoleDescriptionComparator() {
		this(false);
	}

	public RoleDescriptionComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		Role role1 = (Role)obj1;
		Role role2 = (Role)obj2;

		String description1 = role1.getDescription();
		String description2 = role2.getDescription();

		int value = description1.compareTo(description2);

		if (value == 0) {
			String name1 = role1.getName();
			String name2 = role2.getName();

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