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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tomas Polesovsky
 * @author Raymond Augé
 */
public class AccessControlContext {

	public AuthVerifierResult getAuthVerifierResult() {
		return _authVerifierResult;
	}

	public HttpServletRequest getRequest() {
		return _request;
	}

	public HttpServletResponse getResponse() {
		return _response;
	}

	public Map<String, Object> getSettings() {
		return _settings;
	}

	public void setAuthVerifierResult(AuthVerifierResult authVerifierResult) {
		_authVerifierResult = authVerifierResult;
	}

	public void setRequest(HttpServletRequest request) {
		_request = request;
	}

	public void setResponse(HttpServletResponse response) {
		_response = response;
	}

	private AuthVerifierResult _authVerifierResult;
	private HttpServletRequest _request;
	private HttpServletResponse _response;
	private Map<String, Object> _settings = new HashMap<String, Object>();

}