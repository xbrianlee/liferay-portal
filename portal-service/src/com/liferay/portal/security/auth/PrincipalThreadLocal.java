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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class PrincipalThreadLocal {

	public static String getName() {
		String name = _name.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getName " + name);
		}

		return name;
	}

	public static String getPassword() {
		return _password.get();
	}

	public static long getUserId() {
		return GetterUtil.getLong(getName());
	}

	public static void setName(long name) {
		setName(String.valueOf(name));
	}

	public static void setName(String name) {
		if (_log.isDebugEnabled()) {
			_log.debug("setName " + name);
		}

		_name.set(name);
	}

	public static void setPassword(String password) {
		_password.set(password);
	}

	private static Log _log = LogFactoryUtil.getLog(PrincipalThreadLocal.class);

	private static ThreadLocal<String> _name = new AutoResetThreadLocal<String>(
		PrincipalThreadLocal.class + "._name");
	private static ThreadLocal<String> _password =
		new AutoResetThreadLocal<String>(
			PrincipalThreadLocal.class + "._password");

}