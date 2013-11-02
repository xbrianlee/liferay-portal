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

import javax.mail.Session;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public class InfrastructureUtil {

	public static DataSource getDataSource() {
		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "dataSource");

		return _dataSource;
	}

	public static Object getDynamicDataSourceTargetSource() {
		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "dynamicDataSourceTargetSource");

		return _dynamicDataSourceTargetSource;
	}

	public static Session getMailSession() {
		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "mailSession");

		return _mailSession;
	}

	public static Object getShardDataSourceTargetSource() {
		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "shardDataSourceTargetSource");

		return _shardDataSourceTargetSource;
	}

	public static Object getShardSessionFactoryTargetSource() {
		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "shardSessionFactoryTargetSource");

		return _shardSessionFactoryTargetSource;
	}

	public static Object getTransactionManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "transactionManager");

		return _transactionManager;
	}

	public void setDataSource(DataSource dataSource) {
		PortalRuntimePermission.checkSetBeanProperty(getClass(), "dataSource");

		_dataSource = dataSource;
	}

	public void setDynamicDataSourceTargetSource(
		Object dynamicDataSourceTargetSource) {

		PortalRuntimePermission.checkSetBeanProperty(
			getClass(), "dynamicDataSourceTargetSource");

		_dynamicDataSourceTargetSource = dynamicDataSourceTargetSource;
	}

	public void setMailSession(Session mailSession) {
		PortalRuntimePermission.checkSetBeanProperty(getClass(), "mailSession");

		_mailSession = mailSession;
	}

	public void setShardDataSourceTargetSource(
		Object shardDataSourceTargetSource) {

		PortalRuntimePermission.checkSetBeanProperty(
			getClass(), "shardDataSourceTargetSource");

		_shardDataSourceTargetSource = shardDataSourceTargetSource;
	}

	public void setShardSessionFactoryTargetSource(
		Object shardSessionFactoryTargetSource) {

		PortalRuntimePermission.checkSetBeanProperty(
			getClass(), "shardSessionFactoryTargetSource");

		_shardSessionFactoryTargetSource = shardSessionFactoryTargetSource;
	}

	public void setTransactionManager(Object transactionManager) {
		PortalRuntimePermission.checkSetBeanProperty(
			getClass(), "transactionManager");

		_transactionManager = transactionManager;
	}

	private static DataSource _dataSource;
	private static Object _dynamicDataSourceTargetSource;
	private static Session _mailSession;
	private static Object _shardDataSourceTargetSource;
	private static Object _shardSessionFactoryTargetSource;
	private static Object _transactionManager;

}