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

import java.sql.Connection;

import javax.sql.DataSource;

/**
 * @author Shuyang Zhou
 */
public class CurrentConnectionUtil {

	public static Connection getConnection(DataSource dataSource) {
		return getCurrentConnection().getConnection(dataSource);
	}

	public static CurrentConnection getCurrentConnection() {
		PortalRuntimePermission.checkGetBeanProperty(
			CurrentConnectionUtil.class);

		return _currentConnection;
	}

	public void setCurrentConnection(CurrentConnection currentConnection) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_currentConnection = currentConnection;
	}

	private static CurrentConnection _currentConnection;

}