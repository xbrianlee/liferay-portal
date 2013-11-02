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

package com.liferay.portal.kernel.dao.jdbc;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Properties;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public class DataSourceFactoryUtil {

	public static void destroyDataSource(DataSource dataSource)
		throws Exception {

		getDataSourceFactory().destroyDataSource(dataSource);
	}

	public static DataSourceFactory getDataSourceFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			DataSourceFactoryUtil.class);

		return _dataSourceFactory;
	}

	public static DataSource initDataSource(Properties properties)
		throws Exception {

		return getDataSourceFactory().initDataSource(properties);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #initDataSource(String,
	 *             String, String, String, String)}
	 */
	public static DataSource initDataSource(
			String driverClassName, String url, String userName,
			String password)
		throws Exception {

		return initDataSource(
			driverClassName, url, userName, password, StringPool.BLANK);
	}

	public static DataSource initDataSource(
			String driverClassName, String url, String userName,
			String password, String jndiName)
		throws Exception {

		return getDataSourceFactory().initDataSource(
			driverClassName, url, userName, password, jndiName);
	}

	public static void setDataSourceFactory(
		DataSourceFactory dataSourceFactory) {

		PortalRuntimePermission.checkSetBeanProperty(
			DataSourceFactoryUtil.class);

		_dataSourceFactory = dataSourceFactory;
	}

	private static DataSourceFactory _dataSourceFactory;

}