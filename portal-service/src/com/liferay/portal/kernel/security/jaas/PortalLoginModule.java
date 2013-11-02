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

package com.liferay.portal.kernel.security.jaas;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalLoginModule implements LoginModule {

	public PortalLoginModule() {
		try {
			Class<?> clazz = Class.forName(
				_CLASS_NAME, true, PortalClassLoaderUtil.getClassLoader());

			_loginModule = (LoginModule)clazz.newInstance();
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public boolean abort() throws LoginException {
		return _loginModule.abort();
	}

	@Override
	public boolean commit() throws LoginException {
		return _loginModule.commit();
	}

	@Override
	public void initialize(
		Subject subject, CallbackHandler callbackHandler,
		Map<String, ?> sharedState, Map<String, ?> options) {

		_loginModule.initialize(subject, callbackHandler, sharedState, options);
	}

	@Override
	public boolean login() throws LoginException {
		return _loginModule.login();
	}

	@Override
	public boolean logout() throws LoginException {
		return _loginModule.logout();
	}

	private static final String _CLASS_NAME =
		"com.liferay.portal.security.jaas.PortalLoginModule";

	private static Log _log = LogFactoryUtil.getLog(PortalLoginModule.class);

	private LoginModule _loginModule;

}