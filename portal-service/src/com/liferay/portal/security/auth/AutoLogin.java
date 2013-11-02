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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public interface AutoLogin {

	/**
	 * Set a request attribute with this variable to tell the AutoLoginFilter to
	 * stop processing filters and redirect the user to a specified location.
	 */
	public static final String AUTO_LOGIN_REDIRECT = "AUTO_LOGIN_REDIRECT";

	/**
	 * Set a request attribute with this variable to tell the AutoLoginFilter to
	 * continue processing filters and then redirect the user to a specified
	 * location.
	 */
	public static final String AUTO_LOGIN_REDIRECT_AND_CONTINUE =
		"AUTO_LOGIN_REDIRECT_AND_CONTINUE";

	public String[] handleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception e)
		throws AutoLoginException;

	public String[] login(
			HttpServletRequest request, HttpServletResponse response)
		throws AutoLoginException;

}