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

package com.liferay.portal.dao.shard;

import com.liferay.portal.kernel.dao.shard.ShardUtil;
import com.liferay.portal.tools.javadocformatter.SinceJava;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @author Alexander Chow
 */
public class ShardDataSource implements DataSource {

	public static DataSource getInstance() {
		return _instance;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password)
		throws SQLException {

		return getDataSource().getConnection(username, password);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return getDataSource().getLoginTimeout();
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return getDataSource().getLogWriter();
	}

	@SinceJava(1.7)
	public Logger getParentLogger() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isWrapperFor(Class<?> clazz) {

		// Directly implement this method for JDK 5 compatibility. Logic is
		// copied from org.springframework.jdbc.datasource.AbstractDataSource.

		return DataSource.class.equals(clazz);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		getDataSource().setLoginTimeout(seconds);
	}

	@Override
	public void setLogWriter(PrintWriter printWriter) throws SQLException {
		getDataSource().setLogWriter(printWriter);
	}

	@Override
	public <T> T unwrap(Class<T> clazz) throws SQLException {

		// Directly implement this method for JDK 5 compatibility. Logic is
		// copied from org.springframework.jdbc.datasource.AbstractDataSource.

		if (!DataSource.class.equals(clazz)) {
			throw new SQLException("Invalid class " + clazz);
		}

		return (T)this;
	}

	protected DataSource getDataSource() {
		return ShardUtil.getDataSource();
	}

	private static ShardDataSource _instance = new ShardDataSource();

}