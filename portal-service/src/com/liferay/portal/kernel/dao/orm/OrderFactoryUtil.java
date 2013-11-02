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

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderFactoryUtil {

	public static void addOrderByComparator(
		DynamicQuery dynamicQuery, OrderByComparator obc) {

		if (obc == null) {
			return;
		}

		String[] orderByFields = obc.getOrderByFields();

		for (String orderByField : orderByFields) {
			if (obc.isAscending(orderByField)) {
				dynamicQuery.addOrder(asc(orderByField));
			}
			else {
				dynamicQuery.addOrder(desc(orderByField));
			}
		}
	}

	public static Order asc(String propertyName) {
		return getOrderFactory().asc(propertyName);
	}

	public static Order desc(String propertyName) {
		return getOrderFactory().desc(propertyName);
	}

	public static OrderFactory getOrderFactory() {
		PortalRuntimePermission.checkGetBeanProperty(OrderFactoryUtil.class);

		return _orderFactory;
	}

	public void setOrderFactory(OrderFactory orderFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_orderFactory = orderFactory;
	}

	private static OrderFactory _orderFactory;

}