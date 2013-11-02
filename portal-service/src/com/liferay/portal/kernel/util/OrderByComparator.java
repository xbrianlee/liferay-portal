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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
@SuppressWarnings("rawtypes")
public abstract class OrderByComparator implements Comparator, Serializable {

	@Override
	public abstract int compare(Object obj1, Object obj2);

	public String getOrderBy() {
		return null;
	}

	public String[] getOrderByConditionFields() {
		return getOrderByFields();
	}

	public Object[] getOrderByConditionValues(Object obj) {
		String[] fields = getOrderByConditionFields();

		Object[] values = new Object[fields.length];

		for (int i = 0; i < fields.length; i++) {
			values[i] = BeanPropertiesUtil.getObject(obj, fields[i]);
		}

		return values;
	}

	public String[] getOrderByFields() {
		String orderBy = getOrderBy();

		if (orderBy == null) {
			return null;
		}

		String[] parts = StringUtil.split(orderBy);

		String[] fields = new String[parts.length];

		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];

			int x = part.indexOf(CharPool.PERIOD);
			int y = part.indexOf(CharPool.SPACE, x);

			if (y == -1) {
				y = part.length();
			}

			fields[i] = part.substring(x + 1, y);
		}

		return fields;
	}

	public boolean isAscending() {
		String orderBy = getOrderBy();

		if ((orderBy == null) ||
			StringUtil.toUpperCase(orderBy).endsWith(_ORDER_BY_DESC)) {

			return false;
		}
		else {
			return true;
		}
	}

	public boolean isAscending(String field) {
		return isAscending();
	}

	@Override
	public String toString() {
		return getOrderBy();
	}

	private static final String _ORDER_BY_DESC = " DESC";

}