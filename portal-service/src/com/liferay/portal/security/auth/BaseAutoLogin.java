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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mate Thurzo
 */
public abstract class BaseAutoLogin implements AutoLogin {

	@Override
	public String[] handleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception e)
		throws AutoLoginException {

		return doHandleException(request, response, e);
	}

	@Override
	public String[] login(
			HttpServletRequest request, HttpServletResponse response)
		throws AutoLoginException {

		try {
			return doLogin(request, response);
		}
		catch (Exception e) {
			return handleException(request, response, e);
		}
	}

	protected String[] doHandleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception e)
		throws AutoLoginException {

		if (request.getAttribute(AutoLogin.AUTO_LOGIN_REDIRECT) == null) {
			throw new AutoLoginException(e);
		}

		_log.error(e, e);

		return null;
	}

	protected abstract String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception;

	private static Log _log = LogFactoryUtil.getLog(BaseAutoLogin.class);

}