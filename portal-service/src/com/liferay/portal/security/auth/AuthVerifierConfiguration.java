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

import java.util.Properties;

/**
 * @author Tomas Polesovsky
 */
public class AuthVerifierConfiguration {

	public AuthVerifier getAuthVerifier() {
		return _authVerifier;
	}

	public String getAuthVerifierClassName() {
		return _authVerifierClassName;
	}

	public Properties getProperties() {
		return _properties;
	}

	public void setAuthVerifier(AuthVerifier authVerifier) {
		_authVerifier = authVerifier;
	}

	public void setAuthVerifierClassName(String authVerifierClassName) {
		this._authVerifierClassName = authVerifierClassName;
	}

	public void setProperties(Properties properties) {
		_properties = properties;
	}

	private AuthVerifier _authVerifier;
	private String _authVerifierClassName;
	private Properties _properties;

}