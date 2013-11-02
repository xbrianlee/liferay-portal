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

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.security.pacl.PACLPolicy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.Statement;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLStatementHandler implements InvocationHandler {

	public PACLStatementHandler(Statement statement, PACLPolicy paclPolicy) {
		_statement = statement;
		_paclPolicy = paclPolicy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			String methodName = method.getName();

			if (methodName.equals("addBatch") || methodName.equals("execute") ||
				methodName.equals("executeQuery") ||
				methodName.equals("executeUpdate")) {

				if (ArrayUtil.isNotEmpty(arguments)) {
					String sql = (String)arguments[0];

					if (!_paclPolicy.hasSQL(sql)) {
						throw new SecurityException(
							"Attempted to execute unapproved SQL " + sql);
					}
				}
			}
			else if (methodName.equals("equals")) {
				if (proxy == arguments[0]) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (methodName.equals("hashCode")) {
				return System.identityHashCode(proxy);
			}

			return method.invoke(_statement, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	private PACLPolicy _paclPolicy;
	private Statement _statement;

}