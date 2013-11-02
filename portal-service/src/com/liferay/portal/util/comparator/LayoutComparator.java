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
import com.liferay.portal.model.Layout;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"Layout.groupId ASC, Layout.layoutId ASC";

	public static final String ORDER_BY_DESC =
		"Layout.groupId DESC, Layout.layoutId DESC";

	public static final String[] ORDER_BY_FIELDS = {"groupId", "layoutId"};

	public LayoutComparator() {
		this(false);
	}

	public LayoutComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		Layout layout1 = (Layout)obj1;
		Layout layout2 = (Layout)obj2;

		Long groupId1 = new Long(layout1.getGroupId());
		Long groupId2 = new Long(layout2.getGroupId());

		int value = groupId1.compareTo(groupId2);

		if (value != 0) {
			if (_ascending) {
				return value;
			}
			else {
				return -value;
			}
		}

		Long layoutId1 = new Long(layout1.getLayoutId());
		Long layoutId2 = new Long(layout2.getLayoutId());

		value = layoutId1.compareTo(layoutId2);

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