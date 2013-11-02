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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderByComparatorFactoryUtil {

	public static OrderByComparator create(
		String tableName, Object... columns) {

		return getOrderByComparatorFactory().create(tableName, columns);
	}

	public static OrderByComparatorFactory getOrderByComparatorFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			OrderByComparatorFactoryUtil.class);

		return _orderByComparatorFactory;
	}

	public void setOrderByComparatorFactory(
		OrderByComparatorFactory orderByComparatorFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_orderByComparatorFactory = orderByComparatorFactory;
	}

	private static OrderByComparatorFactory _orderByComparatorFactory;

}