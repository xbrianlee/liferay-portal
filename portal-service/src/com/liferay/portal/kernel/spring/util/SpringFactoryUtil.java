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

package com.liferay.portal.kernel.spring.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class SpringFactoryUtil {

	public static SpringFactory getSpringFactory() {
		PortalRuntimePermission.checkGetBeanProperty(SpringFactoryUtil.class);

		return _springFactory;
	}

	public static Object newBean(String className)
		throws SpringFactoryException {

		return getSpringFactory().newBean(className);
	}

	public static Object newBean(
			String className, Map<String, Object> properties)
		throws SpringFactoryException {

		return getSpringFactory().newBean(className, properties);
	}

	public void setSpringFactory(SpringFactory springFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_springFactory = springFactory;
	}

	private static SpringFactory _springFactory;

}