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

import java.io.Serializable;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalCallbackHandler implements CallbackHandler, Serializable {

	public PortalCallbackHandler(String name, String password) {
		_name = name;
		_password = password;
	}

	@Override
	public void handle(Callback[] callbacks) {
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof NameCallback) {
				NameCallback nameCallback = (NameCallback)callbacks[i];
				nameCallback.setName(_name);
			}
			else if (callbacks[i] instanceof PasswordCallback) {
				PasswordCallback passCallback = (PasswordCallback)callbacks[i];
				passCallback.setPassword(_password.toCharArray());
			}
		}
	}

	private String _name;
	private String _password;

}