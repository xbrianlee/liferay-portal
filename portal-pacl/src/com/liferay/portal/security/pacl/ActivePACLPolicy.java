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

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.pacl.checker.Checker;
import com.liferay.portal.security.pacl.checker.JNDIChecker;
import com.liferay.portal.security.pacl.checker.SQLChecker;

import java.security.Permission;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class ActivePACLPolicy extends BasePACLPolicy {

	public ActivePACLPolicy(
		String servletContextName, ClassLoader classLoader,
		Properties properties) {

		super(servletContextName, classLoader, properties);

		try {
			initJNDIChecker();
			initSQLChecker();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public JNDIChecker getJndiChecker() {
		return _jndiChecker;
	}

	public SQLChecker getSqlChecker() {
		return _sqlChecker;
	}

	@Override
	public boolean hasJNDI(String name) {
		return _jndiChecker.hasJNDI(name);
	}

	@Override
	public boolean hasSQL(String sql) {
		return _sqlChecker.hasSQL(sql);
	}

	@Override
	public boolean implies(Permission permission) {
		Checker checker = getChecker(permission.getClass());

		return checker.implies(permission);
	}

	@Override
	public boolean isActive() {
		return true;
	}

	protected void initJNDIChecker() {
		_jndiChecker = new JNDIChecker();

		initChecker(_jndiChecker);
	}

	protected void initSQLChecker() {
		_sqlChecker = new SQLChecker();

		initChecker(_sqlChecker);
	}

	private static Log _log = LogFactoryUtil.getLog(ActivePACLPolicy.class);

	private JNDIChecker _jndiChecker;
	private SQLChecker _sqlChecker;

}