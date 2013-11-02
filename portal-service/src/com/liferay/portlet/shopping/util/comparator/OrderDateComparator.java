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

package com.liferay.portlet.shopping.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.shopping.model.ShoppingOrder;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "ShoppingOrder.createDate ASC";

	public static final String ORDER_BY_DESC = "ShoppingOrder.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public OrderDateComparator() {
		this(false);
	}

	public OrderDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		ShoppingOrder order1 = (ShoppingOrder)obj1;
		ShoppingOrder order2 = (ShoppingOrder)obj2;

		int value = DateUtil.compareTo(
			order1.getCreateDate(), order2.getCreateDate());

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