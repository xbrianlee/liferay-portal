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
 * @author Daniel Reuther
 */
public class LayoutPriorityComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "Layout.priority ASC";

	public static final String ORDER_BY_DESC = "Layout.priority DESC";

	public static final String[] ORDER_BY_FIELDS = {"priority"};

	public LayoutPriorityComparator() {
		this(true);
	}

	public LayoutPriorityComparator(boolean ascending) {
		_ascending = ascending;
	}

	public LayoutPriorityComparator(Layout layout, boolean lessThan) {
		_layout = layout;
		_lessThan = lessThan;

		_ascending = true;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		Layout layout1 = (Layout)obj1;
		Layout layout2 = (Layout)obj2;

		int value = 0;

		int priority1 = layout1.getPriority();
		int priority2 = layout2.getPriority();

		if (priority1 > priority2) {
			value = 1;
		}
		else if (priority1 < priority2) {
			value = -1;
		}
		else {
			if (_layout != null) {
				if (_layout.equals(layout1)) {
					if (_lessThan) {
						value = 1;
					}
					else {
						value = -1;
					}
				}
				else if (_layout.equals(layout2)) {
					if (_lessThan) {
						value = -1;
					}
					else {
						value = 1;
					}
				}
			}
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
	private Layout _layout;
	private boolean _lessThan;

}