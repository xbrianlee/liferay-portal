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

/**
 * @author Brian Wing Shun Chan
 */
public class PropertyFactoryUtil {

	public static Property forName(String propertyName) {
		return getPropertyFactory().forName(propertyName);
	}

	public static PropertyFactory getPropertyFactory() {
		PortalRuntimePermission.checkGetBeanProperty(PropertyFactoryUtil.class);

		return _projectionFactory;
	}

	public void setPropertyFactory(PropertyFactory projectionFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_projectionFactory = projectionFactory;
	}

	private static PropertyFactory _projectionFactory;

}