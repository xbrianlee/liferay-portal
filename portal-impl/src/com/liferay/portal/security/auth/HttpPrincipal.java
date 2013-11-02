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

import com.liferay.portal.PwdEncryptorException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.pwd.PasswordEncryptorUtil;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class HttpPrincipal implements Serializable {

	public HttpPrincipal() {
	}

	public HttpPrincipal(String url) {
		_url = url;
	}

	public HttpPrincipal(String url, String login, String password) {
		this(url, login, password, false);
	}

	public HttpPrincipal(
		String url, String login, String password, boolean digested) {

		_url = url;
		_login = login;

		if (digested) {
			_password = password;
		}
		else {
			try {
				_password = PasswordEncryptorUtil.encrypt(password);
			}
			catch (PwdEncryptorException pee) {
				_log.error(pee, pee);
			}
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getLogin() {
		return _login;
	}

	public String getPassword() {
		return _password;
	}

	public String getUrl() {
		return _url;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setPassword(String password) {
		_password = password;
	}

	private static Log _log = LogFactoryUtil.getLog(HttpPrincipal.class);

	private long _companyId;
	private String _login;
	private String _password;
	private String _url;

}