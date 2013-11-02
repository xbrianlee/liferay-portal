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

import java.io.Serializable;

/**
 * @author Marcellus Tavares
 */
public class NtlmUserAccount implements Serializable {

	public NtlmUserAccount(String userName) {
		_userName = userName;
	}

	public String getUserName() {
		return _userName;
	}

	private String _userName;

}