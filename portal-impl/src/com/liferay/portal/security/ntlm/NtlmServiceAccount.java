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

package com.liferay.portal.security.ntlm;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Marcellus Tavares
 */
public class NtlmServiceAccount {

	public NtlmServiceAccount(String account, String password) {
		setAccount(account);
		setPassword(password);
	}

	public String getAccount() {
		return _account;
	}

	public String getAccountName() {
		return _accountName;
	}

	public String getComputerName() {
		return _computerName;
	}

	public String getPassword() {
		return _password;
	}

	public void setAccount(String account) {
		_account = account;

		_accountName = _account.substring(0, _account.indexOf(CharPool.AT));
		_computerName = _account.substring(
			0, _account.indexOf(StringPool.DOLLAR));
	}

	public void setPassword(String password) {
		_password = password;
	}

	private String _account;
	private String _accountName;
	private String _computerName;
	private String _password;

}