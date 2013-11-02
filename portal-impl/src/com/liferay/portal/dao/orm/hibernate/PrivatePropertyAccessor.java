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

import com.liferay.portal.kernel.util.StringPool;

import org.hibernate.PropertyNotFoundException;
import org.hibernate.property.DirectPropertyAccessor;
import org.hibernate.property.Getter;
import org.hibernate.property.Setter;

/**
 * @author Shuyang Zhou
 */
@SuppressWarnings("rawtypes")
public class PrivatePropertyAccessor extends DirectPropertyAccessor {

	@Override
	public Getter getGetter(Class clazz, String propertyName)
		throws PropertyNotFoundException {

		propertyName = StringPool.UNDERLINE.concat(propertyName);

		return super.getGetter(clazz, propertyName);
	}

	@Override
	public Setter getSetter(Class clazz, String propertyName)
		throws PropertyNotFoundException {

		propertyName = StringPool.UNDERLINE.concat(propertyName);

		return super.getSetter(clazz, propertyName);
	}

}