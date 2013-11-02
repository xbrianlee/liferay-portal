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
import com.liferay.portal.model.ResourceAction;

/**
 * @author Shuyang Zhou
 */
public class ResourceActionBitwiseValueComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "bitwiseValue ASC";

	public static final String ORDER_BY_DESC = "bitwiseValue DESC";

	public static final String[] ORDER_BY_FIELDS = {"bitwiseValue"};

	public ResourceActionBitwiseValueComparator() {
		this(false);
	}

	public ResourceActionBitwiseValueComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		ResourceAction resourceAction1 = (ResourceAction)obj1;
		ResourceAction resourceAction2 = (ResourceAction)obj2;

		int value =
			(int)(resourceAction1.getBitwiseValue() -
				resourceAction2.getBitwiseValue());

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