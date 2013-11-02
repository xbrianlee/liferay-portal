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

package com.liferay.portlet.trash.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.trash.model.TrashEntry;

/**
 * @author Sergio González
 */
public class EntryUserNameComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "TrashEntry.userName ASC";

	public static final String ORDER_BY_DESC = "TrashEntry.userName DESC";

	public static final String[] ORDER_BY_FIELDS = {"userName"};

	public EntryUserNameComparator() {
		this(false);
	}

	public EntryUserNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		TrashEntry entry1 = (TrashEntry)obj1;
		TrashEntry entry2 = (TrashEntry)obj2;

		String name1 = StringUtil.toLowerCase(entry1.getUserName());
		String name2 = StringUtil.toLowerCase(entry2.getUserName());

		int value = name1.compareTo(name2);

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