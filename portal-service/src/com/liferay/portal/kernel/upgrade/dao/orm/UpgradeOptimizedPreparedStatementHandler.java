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

package com.liferay.portal.kernel.upgrade.dao.orm;

import com.liferay.portal.kernel.util.ProxyUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Minhchau Dang
 */
public class UpgradeOptimizedPreparedStatementHandler
	implements InvocationHandler {

	public UpgradeOptimizedPreparedStatementHandler(
		PreparedStatement preparedStatement) {

		_preparedStatement = preparedStatement;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			String methodName = method.getName();

			if (methodName.equals("executeQuery")) {
				return executeQuery();
			}

			return method.invoke(_preparedStatement, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	protected ResultSet executeQuery() throws SQLException {
		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		ResultSet resultSet = _preparedStatement.executeQuery();

		return (ResultSet)ProxyUtil.newProxyInstance(
			classLoader, new Class[] {ResultSet.class},
			new UpgradeOptimizedResultSetHandler(resultSet));
	}

	private PreparedStatement _preparedStatement;

}