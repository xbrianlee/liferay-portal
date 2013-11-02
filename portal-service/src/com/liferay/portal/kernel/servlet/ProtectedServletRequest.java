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

package com.liferay.portal.kernel.servlet;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class ProtectedServletRequest extends HttpServletRequestWrapper {

	public ProtectedServletRequest(
		HttpServletRequest request, String remoteUser) {

		this(request, remoteUser, null);
	}

	public ProtectedServletRequest(
		HttpServletRequest request, String remoteUser, String authType) {

		super(request);

		if (request instanceof ProtectedServletRequest) {
			ProtectedServletRequest parentRequest =
				(ProtectedServletRequest)request;

			setRequest(parentRequest.getRequest());
		}

		_remoteUser = remoteUser;

		if (remoteUser != null) {
			_userPrincipal = new ProtectedPrincipal(remoteUser);
		}

		_authType = authType;
	}

	@Override
	public String getAuthType() {
		if (_authType == null) {
			return super.getAuthType();
		}

		if (_authType.equals(HttpServletRequest.BASIC_AUTH)) {
			return HttpServletRequest.BASIC_AUTH;
		}
		else if (_authType.equals(HttpServletRequest.CLIENT_CERT_AUTH)) {
			return HttpServletRequest.CLIENT_CERT_AUTH;
		}
		else if (_authType.equals(HttpServletRequest.DIGEST_AUTH)) {
			return HttpServletRequest.DIGEST_AUTH;
		}
		else if (_authType.equals(HttpServletRequest.FORM_AUTH)) {
			return HttpServletRequest.FORM_AUTH;
		}

		return _authType;
	}

	@Override
	public String getRemoteUser() {
		if (_remoteUser != null) {
			return _remoteUser;
		}
		else {
			return super.getRemoteUser();
		}
	}

	@Override
	public Principal getUserPrincipal() {
		if (_userPrincipal != null) {
			return _userPrincipal;
		}
		else {
			return super.getUserPrincipal();
		}
	}

	private String _authType;
	private String _remoteUser;
	private Principal _userPrincipal;

}