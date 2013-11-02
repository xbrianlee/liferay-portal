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

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;

import javax.naming.ldap.Control;

/**
 * @author Scott Lee
 */
public class LDAPAuthResult {

	public String getErrorMessage() {
		return _errorMessage;
	}

	public String getResponseControl() {
		return _responseControl;
	}

	public boolean isAuthenticated() {
		return _authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		_authenticated = authenticated;
	}

	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	public void setResponseControl(Control[] response) {
		if (ArrayUtil.isNotEmpty(response)) {
			_responseControl = response[0].getID();
		}
	}

	private boolean _authenticated;
	private String _errorMessage;
	private String _responseControl = StringPool.BLANK;

}