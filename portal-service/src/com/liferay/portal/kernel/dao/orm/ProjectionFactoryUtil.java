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
public class ProjectionFactoryUtil {

	public static Projection alias(Projection projection, String alias) {
		return getProjectionFactory().alias(projection, alias);
	}

	public static Projection avg(String propertyName) {
		return getProjectionFactory().avg(propertyName);
	}

	public static Projection count(String propertyName) {
		return getProjectionFactory().count(propertyName);
	}

	public static Projection countDistinct(String propertyName) {
		return getProjectionFactory().countDistinct(propertyName);
	}

	public static Projection distinct(Projection projection) {
		return getProjectionFactory().distinct(projection);
	}

	public static ProjectionFactory getProjectionFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ProjectionFactoryUtil.class);

		return _projectionFactory;
	}

	public static Projection groupProperty(String propertyName) {
		return getProjectionFactory().groupProperty(propertyName);
	}

	public static Projection max(String propertyName) {
		return getProjectionFactory().max(propertyName);
	}

	public static Projection min(String propertyName) {
		return getProjectionFactory().min(propertyName);
	}

	public static ProjectionList projectionList() {
		return getProjectionFactory().projectionList();
	}

	public static Projection property(String propertyName) {
		return getProjectionFactory().property(propertyName);
	}

	public static Projection rowCount() {
		return getProjectionFactory().rowCount();
	}

	public static Projection sqlProjection(
		String sql, String[] columnAliases, Type[] types) {

		return getProjectionFactory().sqlProjection(sql, columnAliases, types);
	}

	public static Projection sum(String propertyName) {
		return getProjectionFactory().sum(propertyName);
	}

	public void setProjectionFactory(ProjectionFactory projectionFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_projectionFactory = projectionFactory;
	}

	private static ProjectionFactory _projectionFactory;

}