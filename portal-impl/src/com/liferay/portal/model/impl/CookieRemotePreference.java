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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.RemotePreference;

import javax.servlet.http.Cookie;

/**
 * @author Carlos Sierra Andrés
 */
public class CookieRemotePreference implements RemotePreference {

	public CookieRemotePreference(Cookie cookie) {
		_cookie = cookie;

		String cookieName = cookie.getName();

		_name = cookieName.substring(
			CookieKeys.REMOTE_PREFERENCE_PREFIX.length());
	}

	public Cookie getCookie() {
		return _cookie;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getValue() {
		return _cookie.getValue();
	}

	private Cookie _cookie;
	private String _name;

}