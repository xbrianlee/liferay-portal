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

package com.liferay.util.axis;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * @author Brian Wing Shun Chan
 */
public class SimpleAuthenticator extends Authenticator {

	public SimpleAuthenticator(String userName, String password) {
		_authentication = new PasswordAuthentication(
			userName, password.toCharArray());
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return _authentication;
	}

	private PasswordAuthentication _authentication;

}