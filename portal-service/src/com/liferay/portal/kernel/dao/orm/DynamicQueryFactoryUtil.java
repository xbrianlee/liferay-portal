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
public class DynamicQueryFactoryUtil {

	public static DynamicQuery forClass(Class<?> clazz) {
		return getDynamicQueryFactory().forClass(clazz);
	}

	public static DynamicQuery forClass(
		Class<?> clazz, ClassLoader classLoader) {

		return getDynamicQueryFactory().forClass(clazz, classLoader);
	}

	public static DynamicQuery forClass(Class<?> clazz, String alias) {
		return getDynamicQueryFactory().forClass(clazz, alias);
	}

	public static DynamicQuery forClass(
		Class<?> clazz, String alias, ClassLoader classLoader) {

		return getDynamicQueryFactory().forClass(clazz, alias, classLoader);
	}

	public static DynamicQueryFactory getDynamicQueryFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			DynamicQueryFactoryUtil.class);

		return _dynamicQueryFactory;
	}

	public void setDynamicQueryFactory(
		DynamicQueryFactory dynamicQueryFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_dynamicQueryFactory = dynamicQueryFactory;
	}

	private static DynamicQueryFactory _dynamicQueryFactory;

}