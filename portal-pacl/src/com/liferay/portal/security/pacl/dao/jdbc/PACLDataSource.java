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

package com.liferay.portal.security.pacl.dao.jdbc;

import com.liferay.portal.dao.jdbc.util.DataSourceWrapper;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.security.lang.DoPrivilegedFactory;
import com.liferay.portal.security.pacl.PACLPolicy;
import com.liferay.portal.security.pacl.PACLUtil;

import java.security.AccessController;
import java.security.PrivilegedAction;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLDataSource extends DataSourceWrapper {

	public PACLDataSource(DataSource dataSource) {
		super(dataSource);

		_dataSource = dataSource;
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = _dataSource.getConnection();

		PACLPolicy paclPolicy = PACLUtil.getPACLPolicy();

		if (paclPolicy == null) {
			return connection;
		}

		connection = DoPrivilegedFactory.wrap(connection);
		paclPolicy = DoPrivilegedFactory.wrap(paclPolicy);

		return AccessController.doPrivileged(
			new ConnectionPrivilegedAction(connection, paclPolicy));
	}

	private DataSource _dataSource;

	private class ConnectionPrivilegedAction
		implements PrivilegedAction<Connection> {

		public ConnectionPrivilegedAction(
			Connection connection, PACLPolicy paclPolicy) {

			_connection = connection;
			_paclPolicy = paclPolicy;
		}

		@Override
		public Connection run() {
			return (Connection)ProxyUtil.newProxyInstance(
				_paclPolicy.getClassLoader(), new Class<?>[] {Connection.class},
				new PACLConnectionHandler(_connection, _paclPolicy));
		}

		private Connection _connection;
		private PACLPolicy _paclPolicy;

	}

}