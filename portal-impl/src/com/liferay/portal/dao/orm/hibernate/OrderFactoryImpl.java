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

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactory;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class OrderFactoryImpl implements OrderFactory {

	@Override
	public Order asc(String propertyName) {
		return new OrderImpl(org.hibernate.criterion.Order.asc(propertyName));
	}

	@Override
	public Order desc(String propertyName) {
		return new OrderImpl(org.hibernate.criterion.Order.desc(propertyName));
	}

}