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

import org.hibernate.PropertyNotFoundException;
import org.hibernate.property.BasicPropertyAccessor;
import org.hibernate.property.Getter;
import org.hibernate.property.Setter;

/**
 * @author Brian Wing Shun Chan
 */
@SuppressWarnings("rawtypes")
public class CamelCasePropertyAccessor extends BasicPropertyAccessor {

	@Override
	public Getter getGetter(Class clazz, String propertyName)
		throws PropertyNotFoundException {

		propertyName = fixPropertyName(propertyName);

		return super.getGetter(clazz, propertyName);
	}

	@Override
	public Setter getSetter(Class clazz, String propertyName)
		throws PropertyNotFoundException {

		propertyName = fixPropertyName(propertyName);

		return super.getSetter(clazz, propertyName);
	}

	protected String fixPropertyName(String propertyName) {
		if (propertyName.length() < 3) {
			return propertyName;
		}

		char[] chars = propertyName.toCharArray();

		char c0 = chars[0];
		char c1 = chars[1];
		char c2 = chars[2];

		if (Character.isLowerCase(c0) && Character.isUpperCase(c1) &&
			Character.isLowerCase(c2)) {

			return Character.toUpperCase(c0) + propertyName.substring(1);
		}

		return propertyName;
	}

}