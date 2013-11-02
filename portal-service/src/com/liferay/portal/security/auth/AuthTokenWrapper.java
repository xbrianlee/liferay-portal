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

import com.liferay.portal.kernel.exception.PortalException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AuthTokenWrapper implements AuthToken {

	public AuthTokenWrapper(AuthToken authToken) {
		_authToken = authToken;
		_originalAuthToken = authToken;
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             #checkCSRFToken(HttpServletRequest, String)}
	 */
	@Override
	public void check(HttpServletRequest request) throws PortalException {
		_authToken.check(request);
	}

	@Override
	public void checkCSRFToken(HttpServletRequest request, String origin)
		throws PrincipalException {

		_authToken.checkCSRFToken(request, origin);
	}

	@Override
	public String getToken(HttpServletRequest request) {
		return _authToken.getToken(request);
	}

	@Override
	public String getToken(
		HttpServletRequest request, long plid, String portletId) {

		return _authToken.getToken(request, plid, portletId);
	}

	@Override
	public boolean isValidPortletInvocationToken(
		HttpServletRequest request, long plid, String portletId,
		String strutsAction, String tokenValue) {

		return _authToken.isValidPortletInvocationToken(
			request, plid, portletId, strutsAction, tokenValue);
	}

	public void setAuthToken(AuthToken authToken) {
		if (authToken == null) {
			_authToken = _originalAuthToken;
		}
		else {
			_authToken = authToken;
		}
	}

	private AuthToken _authToken;
	private AuthToken _originalAuthToken;

}