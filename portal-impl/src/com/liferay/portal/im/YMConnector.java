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

package com.liferay.portal.im;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;

import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 * @author Brett Randall
 * @author Bruno Farache
 * @author Shuyang Zhou
 */
public class YMConnector {

	public static void disconnect() {
		if (_instance != null) {
			_instance._disconnect();
		}
	}

	public static void send(String to, String msg)
		throws IllegalStateException {

		_instance._send(to, msg);
	}

	private YMConnector() {
	}

	private void _connect() {
		try {
			Class<?> clazz = Class.forName(_SESSION);

			_ym = clazz.newInstance();

			_loginMethod = clazz.getMethod("login", String.class, String.class);
			_logoutMethod = clazz.getMethod("logout");
			_sendMessageMethod = clazz.getMethod(
				"sendMessage", String.class, String.class);
		}
		catch (Exception e) {
			_jYMSGLibraryFound = false;

			if (_log.isWarnEnabled()) {
				_log.warn(
					"jYMSG library could not be loaded: " + e.getMessage());
			}
		}

		try {
			if (_jYMSGLibraryFound) {
				String login = PropsUtil.get(PropsKeys.YM_LOGIN);
				String password = PropsUtil.get(PropsKeys.YM_PASSWORD);

				_loginMethod.invoke(_ym, login, password);
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private void _disconnect() {
		try {
			if (_ym != null) {
				_logoutMethod.invoke(_ym);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}
		}
	}

	private void _send(String to, String msg) throws IllegalStateException {
		try {
			if (_ym == null) {
				_connect();
			}

			if (_jYMSGLibraryFound) {
				_sendMessageMethod.invoke(_ym, to, msg);
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static final String _SESSION = "ymsg.network.Session";

	private static Log _log = LogFactoryUtil.getLog(YMConnector.class);

	private static YMConnector _instance = new YMConnector();

	private boolean _jYMSGLibraryFound = true;
	private Method _loginMethod;
	private Method _logoutMethod;
	private Method _sendMessageMethod;
	private Object _ym;

}